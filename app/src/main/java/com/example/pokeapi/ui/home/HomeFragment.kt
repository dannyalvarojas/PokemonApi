package com.example.pokeapi.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.pokeapi.R
import com.example.pokeapi.data.entities.NetworkMessage
import com.example.pokeapi.data.entities.PokemonResponse
import com.example.pokeapi.databinding.FragmentHomeBinding
import com.example.pokeapi.ui.BottomNavigationActivity
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val parentActivity get() =  activity as BottomNavigationActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupButton()
    }

    private fun setupButton() {
        binding.searchButton.setOnClickListener {
            val textEdit = binding.textSearchView.findViewById<EditText>(R.id.searchEditText)
            textEdit.onEditorAction(EditorInfo.IME_ACTION_DONE)
            val searchText = textEdit.text.toString().trim()
            homeViewModel.getPokemon(searchText)
        }
    }


    private fun setupViewModel() {
        homeViewModel.pokemon.observe(requireActivity(), pokemonObserver)
        homeViewModel.onMessageError.observe(requireActivity(), messageObserver)
        homeViewModel.isViewLoading.observe(requireActivity(), loadingObserver)
    }

    private val pokemonObserver = Observer<PokemonResponse> { response ->
        binding.nameTextView.text = response.name
        Picasso.get()
                .load(response.sprites.front_default)
                .into(binding.pokemonImageView)
    }

    private val messageObserver = Observer<Any> { error ->
        val messageError = error as NetworkMessage
        parentActivity.showDialogError(message = messageError.body)
    }

    private val loadingObserver = Observer<Boolean> { isLoading ->

        if (isLoading) {
            parentActivity.showLoader("Buscando pokemon...")
        } else {
            parentActivity.dismissLoader()
        }
    }

}