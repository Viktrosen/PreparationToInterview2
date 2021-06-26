package com.example.preparationtointerview2.viewmodels

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.preparationtointerview2.model.api.FilmData
import com.example.preparationtointerview2.model.api.RetrofitImpl
import com.example.preparationtointerview2.model.entity.ResponseApi
import com.example.preparationtointerview2.model.entity.ResponseFilmApi
import com.example.preparationtointerview2.view.FilmFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmViewModel(
    private val fragment:Fragment = FilmFragment(),
    private val liveDataForViewToObserve: MutableLiveData<FilmData> = MutableLiveData(),
    private val retrofitImpl: RetrofitImpl = RetrofitImpl()
):ViewModel() {
    fun getData(id: String): LiveData<FilmData> {
        sendServerRequest(id)
        return liveDataForViewToObserve
    }

    private fun sendServerRequest(id: String) {
        liveDataForViewToObserve.value = FilmData.Loading(null)

        retrofitImpl.getListRetrofitImpl().getInformation(id)
            .enqueue(object :
                Callback<ResponseFilmApi> {
                override fun onResponse(
                    call: Call<ResponseFilmApi>,
                    response: Response<ResponseFilmApi>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        liveDataForViewToObserve.value =
                            FilmData.Success(response.body() ?: return)

                    } else {
                        val message = response.message()
                        if (message.isNullOrEmpty()) {
                            liveDataForViewToObserve.value =
                                FilmData.Error(Throwable("Unidentified error"))
                        } else {
                            liveDataForViewToObserve.value =
                                FilmData.Error(Throwable(message))
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseFilmApi>, t: Throwable) {
                    liveDataForViewToObserve.value = FilmData.Error(t)
                }

            })
    }
}