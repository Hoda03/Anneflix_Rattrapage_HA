package com.gmail.eamosse.imdb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.gmail.eamosse.imdb.databinding.FragmentHomeSecondBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeSecondFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentHomeSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonHomeSecond.setOnClickListener {
            val action = HomeFragmentDirections
                .actionHomeFragmentToHomeSecondFragment("From HomeSecondFragment")
            NavHostFragment.findNavController(this@HomeSecondFragment)
                .navigate(action)
        }

        with(homeViewModel) {
            token.observe(viewLifecycleOwner, Observer {
                //récupérer la des films
                getMovieLists(18)
            })

            movielistes.observe(viewLifecycleOwner, Observer {
                binding.movieList.adapter = MovieListAdapter(it, MovieListAdapter.OnClickListener{
                    val action = HomeSecondFragmentDirections.actionNavigationHomeSecondToHomeThirdFragment()
                    NavHostFragment.findNavController(this@HomeSecondFragment)
                        .navigate(action)
                })

            })

            error.observe(viewLifecycleOwner, Observer {
                //afficher l'erreur
            })
        }
    }
}
