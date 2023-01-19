package com.daniel.raduca.deckofcards.network

import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DeckOfCardsApiService {
    @GET("new/shuffle")
    fun getDeck(@Query("deck_count") deckCount: Int = 1): Single<NetworkDeck>;

    @GET("{deckId}/draw")
    fun getCards(
        @Path("deckId") deckId: String,
        @Query("count") cardsNumber: Int
    ): Flowable<NetworkCards>;
}
