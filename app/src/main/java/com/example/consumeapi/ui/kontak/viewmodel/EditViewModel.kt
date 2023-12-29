package com.example.consumeapi.ui.kontak.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.consumeapi.repository.KontakRepository
import com.example.consumeapi.ui.kontak.screen.EditDestination
import kotlinx.coroutines.launch

class EditViewModel (
    savedStateHandle: SavedStateHandle,
    private val kontakRepository: KontakRepository
) : ViewModel(){

    var editkontakstate by mutableStateOf(InsertUiState())
        private set
    val konakId: Int = checkNotNull(savedStateHandle[EditDestination.kontakId])

    init {

        viewModelScope.launch {
            editkontakstate = kontakRepository.getkontakById(konakId).toUiStateKontak()
        }
    }



    fun updateInserkontakState(insertUiEvent: InsertUiEvent){
        editkontakstate = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun updatekontak(){
        viewModelScope.launch {
            try {
                kontakRepository.updatekontak(konakId, editkontakstate.insertUiEvent.toKontak())
            } catch (e:Exception){
                e.printStackTrace()
            }
        }
    }


}