package com.gmail.eamosse.idbdata.data

import com.gmail.eamosse.idbdata.api.response.MovieResponse
import com.google.gson.annotations.SerializedName

data class Movie (
    val id: Int,
    val name: String,
    val description: String,
    val language: String,
    val popularity: Int,
    val status: String,
    val voteAverage: Int,
   // val productionCompanies : ProductionCompanies,
)
/*class ProductionCompanies(
    val id: Int,
    val name: String,
    val logoPath: String,
    val originCountry: String
)*/