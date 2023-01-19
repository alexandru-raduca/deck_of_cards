package com.daniel.raduca.deckofcards.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.daniel.raduca.deckofcards.data.entities.Card
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface CardDao {

    @Query("SELECT * FROM Cards")
    fun getCards(): Flowable<Card>

    @Insert
    fun insertCards(cards: List<Card>): Completable

    @Insert
    fun insertCard(card: Card): Completable

    @Query("SELECT * FROM Cards where isFavourite = :isFavourite")
    fun getCards(isFavourite: Boolean): Maybe<List<Card>>

    @Update
    fun updateCard(card: Card): Completable

    @Query("DELETE FROM Cards")
    fun deleteAll(): Completable
}