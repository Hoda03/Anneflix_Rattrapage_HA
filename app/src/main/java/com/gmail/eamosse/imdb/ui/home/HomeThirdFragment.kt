package com.gmail.eamosse.imdb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.gmail.eamosse.imdb.databinding.FragmentHomeThirdBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeThirdFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentHomeThirdBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonHomeThird.setOnClickListener {
            val action = HomeFragmentDirections
                .actionHomeFragmentToHomeSecondFragment("From HomeSecondFragment")
            NavHostFragment.findNavController(this@HomeThirdFragment)
                .navigate(action)
        }

        with(homeViewModel) {
            token.observe(viewLifecycleOwner, Observer {
                //récupérer le film
                getMovie(550)
            })

            movie.observe(viewLifecycleOwner, Observer {
                binding.movieName.text = "${it.name}"
                binding.movieDescription.text = "${it.description}"
                binding.movieStatus.text = "${it.status}"
                binding.moviePopularity.text = "${it.popularity}"
            })

            error.observe(viewLifecycleOwner, Observer {
                //afficher l'erreur
                binding.movieName.text =  "Erreur $it"
                binding.movieDescription.text =  "Erreur $it"
                binding.movieStatus.text =  "Erreur $it"
                binding.moviePopularity.text =  "Erreur $it"
            })
        }
    }
}