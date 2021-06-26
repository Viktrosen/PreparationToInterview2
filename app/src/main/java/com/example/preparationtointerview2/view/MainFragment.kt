package com.example.preparationtointerview2.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.preparationtointerview2.databinding.FragmentMainBinding
import com.example.preparationtointerview2.model.api.FilmListData
import com.example.preparationtointerview2.viewmodels.MainViewModel


class MainFragment : BaseFragment<FragmentMainBinding>() {

    lateinit var viewModel:MainViewModel




    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getData().observe(viewLifecycleOwner, { renderData(it) })
    }

    private fun renderData(data: FilmListData) {

        when (data) {
            is FilmListData.Success -> {
                val serverResponseData = data.serverResponseData
                binding.filmList.adapter = MainAdapter(serverResponseData.results,this)
                /*binding.filmList.addOnScrollListener(object : OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        val layoutManager =
                            LinearLayoutManager::class.java.cast(recyclerView.layoutManager)
                        val totalItemCount = layoutManager.itemCount
                        val lastVisible = layoutManager.findLastVisibleItemPosition()
                        val endHasBeenReached = lastVisible + 5 >= totalItemCount
                        if (totalItemCount > 0 && endHasBeenReached) {
                            viewModel.getData().observe(viewLifecycleOwner, { renderData(it) })
                        }
                    }
                })*/

            }

            is FilmListData.Loading -> {
                //showLoading()
            }
            is FilmListData.Error -> {
                //showError(data.error.message)
                Log.d(activity?.localClassName, data.error.message.toString())
            }
        }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentMainBinding.inflate(inflater, container, false)


}