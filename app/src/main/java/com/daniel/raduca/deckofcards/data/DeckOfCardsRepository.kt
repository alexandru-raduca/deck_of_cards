package com.daniel.raduca.deckofcards.data

import android.util.Log
import com.daniel.raduca.deckofcards.data.entities.Card
import com.daniel.raduca.deckofcards.data.entities.Deck
import com.daniel.raduca.deckofcards.data.local.LocalDataSource
import com.daniel.raduca.deckofcards.data.remote.RemoteDataSource
import com.daniel.raduca.deckofcards.network.NetworkCards
import com.daniel.raduca.deckofcards.network.NetworkDeck
import com.daniel.raduca.deckofcards.network.asLocalEntity
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import kotlin.concurrent.thread

class DeckOfCardsRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    fun getDeck(): Maybe<Deck> {
        return localDataSource.getDeck()
            .switchIfEmpty(
                getRemoteDeck()
                    .map { it.asLocalEntity() })
            .flatMapCompletable { localDataSource.insertDeck(it) }
            .andThen(localDataSource.getDeck())
    }

    fun getCards(deckId: String, cardsNumber: Int): Flowable<Card> {
        return localDataSource.getCards()
            .switchIfEmpty(
                getRemoteCards(deckId, cardsNumber)
                    .flatMap { it.asLocalEntity() }
            )
            .toList()
            .flatMapCompletable { it -> localDataSource.insertCards(it) }
            .andThen(localDataSource.getCards())
    }

    private fun getRemoteDeck(): Single<NetworkDeck> {
        return remoteDataSource.getDeck()
    }

    private fun getRemoteCards(deckId: String, cardsNumber: Int): Flowable<NetworkCards> {
        return remoteDataSource.getCards(deckId, cardsNumber)
    }

    fun getLocalDeck(): Maybe<Deck> {
        return localDataSource.getDeck()
    }

    fun getLocalCards(): Flowable<Card> {
        return localDataSource.getCards()
    }
}