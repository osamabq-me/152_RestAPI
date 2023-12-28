package com.example.consumeapi.ui

import android.text.Spannable.Factory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.consumeapi.KontakAplikation
import com.example.consumeapi.ui.home.viewmodel.HomeViewModel
import com.example.consumeapi.ui.kontak.viewmodel.InsertViewModel

object PenydiaViewModel {
    val Factory = viewModelFactory {

        initializer {
            HomeViewModel(aplikasikontak().container.kontakRepository)
        }

        initializer {
            InsertViewModel(aplikasikontak().container.kontakRepository)
        }

    }
}


fun CreationExtras.aplikasikontak(): KontakAplikation = ( this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KontakAplikation)