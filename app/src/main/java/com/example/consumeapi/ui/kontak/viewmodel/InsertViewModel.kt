package com.example.consumeapi.ui.kontak.viewmodel










data class InsertUiState(
    val  insertUiEvent: InsertUiEvent = InsertUiEvent(),
)


data class  InsertUiEvent(
    val id: Int = 0,
    val nama: String = "",
    val email: String = "",
    val nohp: String = "",
)