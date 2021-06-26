package com.example.preparationtointerview2.model.api

import com.example.preparationtointerview2.model.entity.ResponseApi

open class FilmListData {
    data class Success(val serverResponseData: ResponseApi) : FilmListData()
    data class Error(val error: Throwable) : FilmListData()
    data class Loading(val progress: Int?) : FilmListData()
}