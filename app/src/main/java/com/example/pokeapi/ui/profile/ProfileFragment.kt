package com.example.pokeapi.ui.profile

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
import com.example.pokeapi.databinding.FragmentProfileBinding
import com.example.pokeapi.ui.BottomNavigationActivity
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val profileViewModel: ProfileViewModel by viewModels()

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val parentActivity get() =  activity as BottomNavigationActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupSearchButton()
    }

    private fun setupSearchButton() {
        binding.searchButton.setOnClickListener {
            val textEdit = binding.textSearchView.findViewById<EditText>(R.id.searchEditText)
            textEdit.onEditorAction(EditorInfo.IME_ACTION_DONE)
            val searchText = textEdit.text.toString().trim()
            profileViewModel.getPokemon(searchText)
        }
    }

    private fun setupViewModel() {
        profileViewModel.pokemon.observe(requireActivity(), pokemonObserver)
        profileViewModel.isViewLoading.observe(requireActivity(), loadingObserver)
        profileViewModel.onMessageError.observe(requireActivity(), messageObserver)
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