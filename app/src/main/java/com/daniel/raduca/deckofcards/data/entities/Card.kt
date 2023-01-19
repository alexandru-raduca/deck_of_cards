package com.daniel.raduca.deckofcards.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cards")
data class Card(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "code") var code: String,
    @ColumnInfo(name = "image") var image: String,
    @ColumnInfo(name = "value") var value: String,
    @ColumnInfo(name = "suit") var suit: String,
    @ColumnInfo(name = "isFavourite") var isFavourite: Boolean = false,
)