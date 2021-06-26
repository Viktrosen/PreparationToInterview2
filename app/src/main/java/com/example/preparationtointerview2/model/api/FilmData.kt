package com.example.preparationtointerview2.model.api

import com.example.preparationtointerview2.model.entity.ResponseApi
import com.example.preparationtointerview2.model.entity.ResponseFilmApi

open class FilmData {
    data class Success(val serverResponseData: ResponseFilmApi) : FilmData()
    data class Error(val error: Throwable) : FilmData()
    data class Loading(val progress: Int?) : FilmData()
}