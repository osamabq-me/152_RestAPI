package com.example.consumeapi.ui.kontak.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.consumeapi.navigation.DestinasiNavigasi
import com.example.consumeapi.ui.kontak.viewmodel.EditViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.consumeapi.ui.PenydiaViewModel
import com.example.consumeapi.ui.TopAppBarKontak
import com.example.consumeapi.ui.home.screen.DestinasiHome
import kotlinx.coroutines.launch

object EditDestination : DestinasiNavigasi {
    override val route = "Edit"
    override val titleRes = "Edit Kontak"
    const val kontakId = "itemId"
    val routeWithArgs = "$route/{$kontakId}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditScreen(
    navigateBack: () -> Unit,
    onNavigateUP: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditViewModel = viewModel(factory = PenydiaViewModel.Factory)
) {

    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBarKontak(
                title = DestinasiHome.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
            )
        },
        modifier = modifier
            .fillMaxWidth()
    )
    { innerPadding ->
        EntryKontakBody(
            insertUiState =viewModel.editkontakstate,
            onSiswaValueChange =viewModel::updateInserkontakState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updatekontak()
                    onNavigateUP()
                }
            },
            modifier = Modifier.padding(innerPadding)
            )
    }
}