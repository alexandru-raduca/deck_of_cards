package com.daniel.raduca.deckofcards

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DeckOfCardsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}