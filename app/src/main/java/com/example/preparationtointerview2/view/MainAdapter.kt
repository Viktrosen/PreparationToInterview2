package com.example.preparationtointerview2.view


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.preparationtointerview2.MainActivity
import com.example.preparationtointerview2.R
import com.example.preparationtointerview2.databinding.FragmentMainBinding
import com.example.preparationtointerview2.model.entity.Results

class MainAdapter(var results : List<Results>, var view: BaseFragment<FragmentMainBinding>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    inner class FilmListViewHolder(view: View) : RecyclerView.ViewHolder(view) {



        fun bind(position: Int) {

            val title = itemView.findViewById<TextView>(R.id.titleFilm)
            title.text = results[position].title
            val pictureView = itemView.findViewById<ImageView>(R.id.poster)
            pictureView.load("https://image.tmdb.org/t/p/w500/"+results[position].backdrop_path)
            val voteAverage = itemView.findViewById<TextView>(R.id.vote_average)
            voteAverage.text = "Рейтинг: "+results[position].vote_average.toString()
            itemView.setOnClickListener {
                NavHostFragment.findNavController(view).navigate(R.id.filmFragment)
                MainActivity.id = results[position].id.toString()
            }
            Log.d("MainAdapter", title.text as String)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return FilmListViewHolder(
            inflater.inflate(
                R.layout.item_film,
                parent,
                false
            ) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as FilmListViewHolder
        holder.bind(position)

    }

    override fun getItemCount(): Int {
        return results.size
    }
}