package com.daniel.raduca.deckofcards.data.local

import com.daniel.raduca.deckofcards.data.entities.Card
import com.daniel.raduca.deckofcards.data.entities.Deck
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

class LocalDataSource(
    private val database: DeckOfCardsDatabase
) {
    fun getCards(): Flowable<Card> {
        return database.cardDao().getCards()
    }

    fun getFavouriteCards(): Maybe<List<Card>> {
        return database.cardDao().getCards(true)
    }

    fun updateCard(card: Card): Completable {
        return database.cardDao().updateCard(card)
    }

    fun deleteAllCards(): Completable {
        return database.cardDao().deleteAll()
    }

    fun getDeck(): Maybe<Deck> {
        return database.deckDao().getDeck()
    }

    fun insertDeck(deck: Deck): Completable {
        return database.deckDao().insertDeck(deck)
    }

    fun insertCards(cards: List<Card>): Completable {
        return database.cardDao().insertCards(cards)
    }

    fun insertCard(card: Card): Completable {
        return database.cardDao().insertCard(card)
    }

    fun deleteDeck(): Completable {
        return database.deckDao().deleteAll()
    }
}