package com.example.project.presentation.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.project.R
import com.example.project.SaveShared
import com.example.project.data.remote.model.MovieItem
import com.example.project.databinding.FragmentDetailsBinding
import com.example.project.databinding.FragmentFavoriteBinding
import com.example.project.main
import com.example.project.presentation.favorite.FavoriteFragmentViewModel


class DetailsFragment : Fragment() {

    private var eBinding: FragmentDetailsBinding ?= null
    private val binding get() = eBinding!!
    lateinit var currentMovie: MovieItem
    private var isFavorite = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        eBinding = FragmentDetailsBinding.inflate(layoutInflater,container,false)
        currentMovie = arguments?.getSerializable("movie") as MovieItem
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init(){
        val valueBool = SaveShared.getFavorite(main,currentMovie.id.toString())
        val viewModel = ViewModelProvider(this).get(DetailsFragmentViewModel::class.java)

        if (isFavorite!=valueBool) {
            binding.imageDetailsFavorite.setImageResource(R.drawable.icon_like_fill)
        }else {
            binding.imageDetailsFavorite.setImageResource(R.drawable.icon_like_border)
        }

        Glide.with(main)
            .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/${currentMovie.poster_path}")

            .into(binding.imageDetails)
        binding.titleDetails.text = currentMovie.title
        binding.dateDetails.text = currentMovie.release_date
        binding.descriptionDetails.text = currentMovie.overview
        binding.imageDetailsFavorite.setOnClickListener {
            isFavorite = if (isFavorite==valueBool) {
                binding.imageDetailsFavorite.setImageResource(R.drawable.icon_like_fill)
                SaveShared.setFavorite(main,currentMovie.id.toString(),true)
                viewModel.insert(currentMovie){}
                true
            }else {
                binding.imageDetailsFavorite.setImageResource(R.drawable.icon_like_border)
                SaveShared.setFavorite(main,currentMovie.id.toString(),false)
                viewModel.delete(currentMovie){}
                false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        eBinding=null
    }
}