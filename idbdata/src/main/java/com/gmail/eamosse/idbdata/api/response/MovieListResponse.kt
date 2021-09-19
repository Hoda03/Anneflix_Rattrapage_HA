package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.MovieList
import com.gmail.eamosse.idbdata.data.Token
import com.google.gson.annotations.SerializedName

data class MovieListResponse (
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<Results>
) {
    data class Results(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("iso_639_1")
        val iso_639_1: String,
        @SerializedName("poster_path")
        val poster_path: String

    )
}

/**
 * Une classe d'extension utilisée pour convertir la réponse en objet exploitable
 * par les autres composants de l'application
 */

internal fun MovieListResponse.Results.toMovieList() = MovieList(
    id = id!!,
    name = name!!,
)
