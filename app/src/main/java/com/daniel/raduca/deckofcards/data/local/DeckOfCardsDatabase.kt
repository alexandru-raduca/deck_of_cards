package com.daniel.raduca.deckofcards.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.daniel.raduca.deckofcards.data.entities.Card
import com.daniel.raduca.deckofcards.data.entities.Deck

@Database(
    entities = [Card::class, Deck::class],
    version = 1,
    exportSchema = false
)
abstract class DeckOfCardsDatabase : RoomDatabase() {

    abstract fun cardDao(): CardDao

    abstract fun deckDao(): DeckDao
}