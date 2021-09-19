package com.gmail.eamosse.idbdata.repository


import com.gmail.eamosse.idbdata.api.response.*
import com.gmail.eamosse.idbdata.api.response.toEntity
import com.gmail.eamosse.idbdata.api.response.toMovieList
import com.gmail.eamosse.idbdata.api.response.toToken
import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.data.MovieList
import com.gmail.eamosse.idbdata.data.Token
import com.gmail.eamosse.idbdata.datasources.LocalDataSource
import com.gmail.eamosse.idbdata.datasources.OnlineDataSource
import com.gmail.eamosse.idbdata.utils.Result
import com.gmail.eamosse.idbdata.utils.toCategory
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.*

/**
 * La classe permettant de gérer les données de l'application
 */
class MovieRepository : KoinComponent {
    //Gestion des sources de données locales
    private val local: LocalDataSource by inject()
    //Gestion des sources de données en lignes
    private val online: OnlineDataSource by inject()

    /**
     * Récupérer le token permettant de consommer les ressources sur le serveur
     * Le résultat du datasource est converti en instance d'objets publiques
     */
    suspend fun getToken(): Result<Token> {
        return when(val result = online.getToken()) {
            is Result.Succes -> {
                //save the response in the local database
                local.saveToken(result.data.toEntity())
                //return the response
                Result.Succes(result.data.toToken())
            }
            is Result.Error -> result
        }
    }

    suspend fun getCategories(): Result<List<Category>> {
        return when(val result = online.getCategories()) {
            is Result.Succes -> {
                // On utilise la fonction map pour convertir les catégories de la réponse serveur
                // en liste de categories d'objets de l'application
                val categories = result.data.map {
                    it.toCategory()
                }
                Result.Succes(categories)
            }
            is Result.Error -> result
        }
    }

    suspend fun getMovieLists(id:Int): Result<List<MovieList>> {
        return when(val result = online.getMovieLists(id)) {
            is Result.Succes -> {
                // On utilise la fonction map pour convertir les catégories de la réponse serveur
                // en liste de categories d'objets de l'application
                val movieLists = result.data.map {
                    it.toMovieList()
                }
                Result.Succes(movieLists)
            }
            is Result.Error -> result
        }
    }
    suspend fun getMovie(id:Int): Result<Movie> {
        return when(val result = online.getMovie(id)) {
            is Result.Succes -> {
                // On utilise la fonction map pour convertir les catégories de la réponse serveur
                // en liste de categories d'objets de l'application
                val movie = result.data.toMovie()
               /* val productionCompanies = result.data.production_companies.map {
                    movie.productionCompanies = it.toProductionCompanies()
                }*/
                Result.Succes(movie)
            }
            is Result.Error -> result
        }
    }

}