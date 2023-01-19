package com.daniel.raduca.deckofcards.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Deck")
data class Deck(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "deckId") var deckId: String,
    @ColumnInfo(name = "remaining") var cardsNumber: Int
)