package com.example.preparationtointerview2.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.preparationtointerview2.MainActivity
import com.example.preparationtointerview2.databinding.FragmentFilmBinding
import com.example.preparationtointerview2.model.api.FilmData
import com.example.preparationtointerview2.model.api.FilmListData
import com.example.preparationtointerview2.viewmodels.FilmViewModel
import com.example.preparationtointerview2.viewmodels.MainViewModel


class FilmFragment : BaseFragment<FragmentFilmBinding>() {

    lateinit var viewModel:FilmViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FilmViewModel::class.java)
        viewModel.getData(MainActivity.id).observe(viewLifecycleOwner, { renderData(it) })
    }

    fun renderData(data: FilmData) {
        when (data) {
            is FilmData.Success -> {
                val serverResponseData = data.serverResponseData
                binding.title.text = serverResponseData.title
                binding.posterImage.load("https://image.tmdb.org/t/p/original/"+serverResponseData.poster_path)
                binding.overview.text = data.serverResponseData.overview

            }
            is FilmData.Loading -> {
                //showLoading()
            }
            is FilmData.Error -> {
                //showError(data.error.message)
                Log.d(activity?.localClassName, data.error.message.toString())
            }
        }

    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentFilmBinding.inflate(inflater, container, false)


}