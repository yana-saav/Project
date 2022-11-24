package com.example.project.presentation.first

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project.data.remote.model.MovieItem
import com.example.project.databinding.ItemMovieBinding

class FirstAdapter(): RecyclerView.Adapter<FirstAdapter.FirstViewHolder>() {

    private var listMovies = emptyList<MovieItem>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstViewHolder {
        val view = ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FirstViewHolder(view)
    }

    override fun onBindViewHolder(holder: FirstViewHolder, position: Int) {
        val currentElement = listMovies[position]
        Glide.with(holder.binding.movieItem.context)
            .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/${currentElement.poster_path}")
            .into(holder.binding.imageItemMovie)
        holder.bindItem(currentElement)
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    fun setList(list: List<MovieItem>){
        listMovies = list
        notifyDataSetChanged()
    }

    inner class FirstViewHolder(
        val binding: ItemMovieBinding
    ):RecyclerView.ViewHolder(binding.root) {
        fun bindItem(model: MovieItem){
            binding.titleItemMovie.text=model.title
            binding.dateItemMovie.text=model.release_date

        }

    }

    override fun onViewAttachedToWindow(holder: FirstViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            FirstFragment.clickMovie(listMovies[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: FirstViewHolder) {
        holder.itemView.setOnClickListener(null)
    }


}