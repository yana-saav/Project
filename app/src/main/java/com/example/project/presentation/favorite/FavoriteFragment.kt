package com.example.project.presentation.favorite

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.data.remote.model.MovieItem
import com.example.project.databinding.FragmentFavoriteBinding
import com.example.project.databinding.FragmentFirstBinding
import com.example.project.main
import com.example.project.presentation.first.FirstAdapter
import com.example.project.presentation.first.FirstFragmentDirections
import com.example.project.presentation.first.FirstFragmentViewModel

class FavoriteFragment : Fragment() {

    private var eBinding: FragmentFavoriteBinding ?= null
    private val binding get() = eBinding!!
    lateinit var recyclerView: RecyclerView
    private val adapter by lazy {
        FavoriteAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        eBinding = FragmentFavoriteBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init(){
        val viewModel = ViewModelProvider(this).get(FavoriteFragmentViewModel::class.java)
        recyclerView = binding.rvFavorite
        recyclerView.adapter = adapter
        viewModel.getAllMovies().observe(viewLifecycleOwner,{
            adapter.setList(it.asReversed())
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        eBinding=null
    }
    companion object{
        fun clickMovieFavorite(model: MovieItem) {
            val bundle = Bundle()
            bundle.putSerializable("movie",model)
            main.navController.navigate(R.id.action_favoriteFragment_to_detailsFragment,bundle)
        }
    }

}