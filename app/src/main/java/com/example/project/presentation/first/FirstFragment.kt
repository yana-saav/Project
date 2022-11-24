package com.example.project.presentation.first

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.data.remote.model.MovieItem
import com.example.project.databinding.FragmentFirstBinding
import com.example.project.main


class FirstFragment : Fragment() {

    private var eBinding: FragmentFirstBinding ?= null
    private val binding get() = eBinding!!
    lateinit var recyclerView: RecyclerView
    private val adapter by lazy {
        FirstAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        eBinding = FragmentFirstBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init(){
        val viewModel = ViewModelProvider(this).get(FirstFragmentViewModel::class.java)
        viewModel.initDatabase()
        recyclerView = binding.rvFirst
        recyclerView.adapter = adapter
        try {
            viewModel.getMoviesRetrofit()
            viewModel.myMovies.observe(viewLifecycleOwner, {
                adapter.setList(it.body()!!.results)
            })
        }catch (e: Exception) {
            Log.e("Error", e.message.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        eBinding=null
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }



    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btn_menu_favorite -> {
                main.navController.navigate(R.id.action_firstFragment_to_favoriteFragment)
                true
            }
            else -> {super.onOptionsItemSelected(item)}
        }
    }

    companion object{
        fun clickMovie(model:MovieItem) {
            val bundle = Bundle()
            bundle.putSerializable("movie",model)
            main.navController.navigate(R.id.action_firstFragment_to_detailsFragment,bundle)
        }
    }

}