package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Movie

import com.google.gson.annotations.SerializedName

class MovieResponse (
    @SerializedName("id")
    val id: Int,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Int,
    @SerializedName("vote_average")
    val vote_average: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("original_language")
    val original_language: String,
    @SerializedName("production_companies")
    val production_companies: List<ProductionCompanies>
) {
    data class ProductionCompanies(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("description")
        val logo_path: String,
        @SerializedName("poster_path")
        val origin_country: String

    )
}
/*internal fun MovieResponse.ProductionCompanies.toProductionCompanies() = ProductionCompanies (
    id = id!!,
    name = name!!,
    logoPath = logo_path!!,
    originCountry = origin_country!!,
)
*/
internal fun MovieResponse.toMovie() = Movie(
    id = id!!,
    name = title!!,
    description = overview!!,
    popularity = popularity!!,
    status = status!!,
    language = original_language!!,
    voteAverage = vote_average!!,
  //  productionCompanies = production_companies!!,
)

