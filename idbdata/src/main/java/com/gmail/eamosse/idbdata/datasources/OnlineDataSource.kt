package com.gmail.eamosse.idbdata.datasources

import com.gmail.eamosse.idbdata.api.response.CategoryResponse
import com.gmail.eamosse.idbdata.api.response.MovieListResponse
import com.gmail.eamosse.idbdata.api.response.MovieResponse
import com.gmail.eamosse.idbdata.api.response.TokenResponse
import com.gmail.eamosse.idbdata.api.service.MovieService
import com.gmail.eamosse.idbdata.data.Token
import com.gmail.eamosse.idbdata.utils.Result
import com.gmail.eamosse.idbdata.utils.parse
import com.gmail.eamosse.idbdata.utils.safeCall

/**
 * Manipule les données de l'application en utilisant un web service
 * Cette classe est interne au module, ne peut être initialisé ou exposé aux autres composants
 * de l'application
 */
internal class OnlineDataSource(private val service: MovieService) {

    /**
     * Récupérer le token du serveur
     * @return [Result<Token>]
     * Si [Result.Succes], tout s'est bien passé
     * Sinon, une erreur est survenue
     */
    suspend fun getToken(): Result<TokenResponse> {
        return safeCall {
            val response = service.getToken()
            response.parse()
        }
    }
    suspend fun getCategories(): Result<List<CategoryResponse.Genre>> {
        return try {
            val response = service.getCategories()
            if (response.isSuccessful) {
                Result.Succes(response.body()!!.genres)
            } else {
                Result.Error(
                    exception = Exception(),
                    message = response.message(),
                    code = response.code()
                )
            }
        } catch (e: Exception) {
            Result.Error(
                exception = e,
                message = e.message ?: "No message",
                code = -1
            )
        }
    }

    suspend fun getMovieLists(id: Int): Result<List<MovieListResponse.Results>> {
        return try {
            val response = service.getMovieLists(id)
            if (response.isSuccessful) {
                Result.Succes(response.body()!!.results)
            } else {
                Result.Error(
                    exception = Exception(),
                    message = response.message(),
                    code = response.code()
                )
            }
        } catch (e: Exception) {
            Result.Error(
                exception = e,
                message = e.message ?: "No message",
                code = -1
            )
        }
    }

    suspend fun getMovie(id: Int): Result<MovieResponse> {
        return try {
            val response = service.getMovie(id)
            if (response.isSuccessful) {
                Result.Succes(response.body()!!)
            } else {
                Result.Error(
                    exception = Exception(),
                    message = response.message(),
                    code = response.code()
                )
            }
        } catch (e: Exception) {
            Result.Error(
                exception = e,
                message = e.message ?: "No message",
                code = -1
            )
        }
    }
}


