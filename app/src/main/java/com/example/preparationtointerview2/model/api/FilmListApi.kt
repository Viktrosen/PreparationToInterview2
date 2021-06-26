package com.example.preparationtointerview2.model.api

import com.example.preparationtointerview2.model.entity.ResponseApi
import com.example.preparationtointerview2.model.entity.ResponseFilmApi
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmListApi {

    @GET("/3/discover/movie")
    fun getFilms(@Query("api_key") api_key:String): Call<ResponseApi>

    @GET("/3/movie/{id}?api_key=274f828ad283bd634ef4fc1ee4af255f")
    fun getInformation(@Path("id") id: String): Call<ResponseFilmApi>
}