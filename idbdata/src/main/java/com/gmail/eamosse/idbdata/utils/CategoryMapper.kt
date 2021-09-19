package com.gmail.eamosse.idbdata.utils

import com.gmail.eamosse.idbdata.api.response.CategoryResponse
import com.gmail.eamosse.idbdata.api.response.MovieListResponse
import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.idbdata.data.MovieList


fun CategoryResponse.Genre.toCategory() = Category (
    id = id!!,
    name = name!!
)

