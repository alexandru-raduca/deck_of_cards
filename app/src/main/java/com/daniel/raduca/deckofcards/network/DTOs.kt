package com.daniel.raduca.deckofcards.network

import com.daniel.raduca.deckofcards.data.entities.Card
import com.daniel.raduca.deckofcards.data.entities.Deck
import com.google.gson.annotations.SerializedName
import io.reactivex.Flowable

data class NetworkDeck(
    @SerializedName("deck_id") val deckId: String,
    val success: Boolean,
    val remaining: Int
)

fun NetworkDeck.asLocalEntity(): Deck {
    return Deck(
        deckId = deckId,
        cardsNumber = remaining
    )
}

data class NetworkCards(
    @SerializedName("deck_id") val deckId: String,
    val success: Boolean,
    val cards: List<NetworkCard>
)

data class NetworkCard(
    val code: String,
    val image: String,
    val suit: String,
    val value: String
)

fun NetworkCards.asLocalEntity(): Flowable<Card> {
    return Flowable.fromIterable(cards.map {
        Card(
            code = it.code,
            image = it.image,
            value = it.value,
            suit = it.suit
        )
    }
    )
}