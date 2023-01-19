package com.daniel.raduca.deckofcards.ui.cards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import com.daniel.raduca.deckofcards.data.DeckOfCardsRepository
import com.daniel.raduca.deckofcards.data.entities.Card
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(
    private val repository: DeckOfCardsRepository
) : ViewModel() {

    private val _cards = MutableLiveData<List<Card>>(listOf())
    val cards: LiveData<List<Card>> = _cards

    private val _drawnCards = MutableLiveData<List<Card>>(listOf())
    val drawnCards: LiveData<List<Card>> = _drawnCards

    init {
        getCards()
    }

    private fun getCards() {
        repository.getDeck()
            .flatMapPublisher { repository.getCards(it.deckId, it.cardsNumber) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toList()
            .subscribe(Consumer { _cards.value = it }, Consumer { })
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Card>() {
        override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem == newItem
        }
    }
}