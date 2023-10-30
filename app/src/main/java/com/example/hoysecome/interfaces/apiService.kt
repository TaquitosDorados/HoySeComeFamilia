package com.example.hoysecome.interfaces

import com.example.hoysecome.responses.ingredientsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface apiService {
    @GET
    suspend fun consultIngredient(@Url url:String):Response<ingredientsResponse>
}