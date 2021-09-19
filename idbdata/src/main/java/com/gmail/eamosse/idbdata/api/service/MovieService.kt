package com.gmail.eamosse.idbdata.api.service

import com.gmail.eamosse.idbdata.api.response.CategoryResponse
import com.gmail.eamosse.idbdata.api.response.MovieListResponse
import com.gmail.eamosse.idbdata.api.response.MovieResponse
import com.gmail.eamosse.idbdata.api.response.TokenResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

internal interface MovieService {
    @GET("authentication/token/new")
    suspend fun getToken(): Response<TokenResponse>

    @GET("genre/movie/list")
    suspend fun getCategories(): Response<CategoryResponse>

    @GET("movie/{id}/lists")
    suspend fun getMovieLists( @Path(value = "id", encoded = false) key: Int): Response<MovieListResponse>

    @GET("movie/{id}")
    suspend fun getMovie( @Path(value = "id", encoded = false) key: Int): Response<MovieResponse>
}