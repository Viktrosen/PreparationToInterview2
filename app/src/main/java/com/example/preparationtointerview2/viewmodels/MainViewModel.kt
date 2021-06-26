package com.example.preparationtointerview2.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.preparationtointerview2.model.api.FilmListData
import com.example.preparationtointerview2.model.api.RetrofitImpl
import com.example.preparationtointerview2.model.entity.ResponseApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(
    private val liveDataForViewToObserve: MutableLiveData<FilmListData> = MutableLiveData(),
    private val retrofitImpl: RetrofitImpl = RetrofitImpl()
):ViewModel() {

    fun getData(): LiveData<FilmListData> {
        sendServerRequest()
        return liveDataForViewToObserve
    }

    private fun sendServerRequest() {
        liveDataForViewToObserve.value = FilmListData.Loading(null)

            retrofitImpl.getListRetrofitImpl().getFilms("274f828ad283bd634ef4fc1ee4af255f")
                .enqueue(object :
                    Callback<ResponseApi> {
                    override fun onResponse(
                        call: Call<ResponseApi>,
                        response: Response<ResponseApi>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            liveDataForViewToObserve.value =
                                FilmListData.Success(response.body() ?: return)

                        } else {
                            val message = response.message()
                            if (message.isNullOrEmpty()) {
                                liveDataForViewToObserve.value =
                                    FilmListData.Error(Throwable("Unidentified error"))
                            } else {
                                liveDataForViewToObserve.value =
                                    FilmListData.Error(Throwable(message))
                            }
                        }
                    }

                    override fun onFailure(call: Call<ResponseApi>, t: Throwable) {
                        liveDataForViewToObserve.value = FilmListData.Error(t)
                    }
                })
    }
}