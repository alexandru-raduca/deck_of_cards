package com.daniel.raduca.deckofcards.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.daniel.raduca.deckofcards.data.entities.Deck
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface DeckDao {

    @Query("SELECT * FROM Deck")
    fun getDeck(): Maybe<Deck>

    @Insert
    fun insertDeck(deck: Deck): Completable

    @Query("DELETE FROM Deck")
    fun deleteAll(): Completable
}