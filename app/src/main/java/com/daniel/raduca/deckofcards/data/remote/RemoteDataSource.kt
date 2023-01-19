package com.daniel.raduca.deckofcards.data.remote

import com.daniel.raduca.deckofcards.network.DeckOfCardsApiService
import com.daniel.raduca.deckofcards.network.NetworkCards
import com.daniel.raduca.deckofcards.network.NetworkDeck
import io.reactivex.Flowable
import io.reactivex.Single

class RemoteDataSource(
    private val apiService: DeckOfCardsApiService
) {
    fun getDeck(): Single<NetworkDeck> {
        return apiService.getDeck()
    }

    fun getCards(deckId: String, cardsNumber: Int): Flowable<NetworkCards> {
        return apiService.getCards(deckId, cardsNumber)
    }
}