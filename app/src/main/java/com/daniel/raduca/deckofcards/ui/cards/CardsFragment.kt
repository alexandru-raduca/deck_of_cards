package com.daniel.raduca.deckofcards.ui.cards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.daniel.raduca.deckofcards.R
import com.daniel.raduca.deckofcards.data.entities.Card
import com.daniel.raduca.deckofcards.databinding.FragmentCardsBinding
import com.daniel.raduca.deckofcards.util.adapters.BindingHandler
import com.daniel.raduca.deckofcards.util.adapters.GenericAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardsFragment : Fragment(), BindingHandler<Card> {
    private val viewModel: CardsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCardsBinding.inflate(inflater)

        binding.let {
            it.lifecycleOwner = this
            it.viewModel = viewModel
            it.cards.adapter = GenericAdapter(
                R.layout.card_item,
                this,
                CardsViewModel
            )

            it.drawnCards.adapter = GenericAdapter(
                R.layout.card_item,
                this,
                CardsViewModel
            )
        }

        return binding.root
    }

    override fun bindData(item: Card, view: View) {
        TODO("Not yet implemented")
    }

}