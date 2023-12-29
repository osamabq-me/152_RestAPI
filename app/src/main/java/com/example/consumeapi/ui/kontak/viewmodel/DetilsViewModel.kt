package com.example.consumeapi.ui.kontak.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.consumeapi.model.Kontak
import com.example.consumeapi.repository.KontakRepository
import com.example.consumeapi.ui.kontak.screen.DetialsDestination
import kotlinx.coroutines.launch


sealed class DetailsKontakUiState {

    data class  Success(
        val kontak: Kontak
    ) : DetailsKontakUiState()

    object Error: DetailsKontakUiState()
    object Loading: DetailsKontakUiState()
}

class DetilsViewModel (
    savedStateHandle: SavedStateHandle,
    private val kontakRepository: KontakRepository
) : ViewModel(){
private val kontakId: Int = checkNotNull(savedStateHandle[DetialsDestination.kontakId])

    var detailsKontakUiState: DetailsKontakUiState by mutableStateOf(DetailsKontakUiState.Loading)
        private set


    init {
        getkontakById()
    }

    fun getkontakById(){
        viewModelScope.launch {
            detailsKontakUiState = DetailsKontakUiState.Loading
            detailsKontakUiState = try {
                DetailsKontakUiState.Success(
                    kontak = kontakRepository.getkontakById(kontakId)
                )
            }
            catch (e: Exception){
                DetailsKontakUiState.Error
            }
        }
    }

}
