package com.company.metrix.ui.servicesEmployee.diagnostic

import androidx.lifecycle.ViewModel
import com.company.metrix.data.model.Diagnostic
import com.company.metrix.data.repository.DiagnosticRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiagnosticViewModel @Inject constructor(private val diagnosticRepo: DiagnosticRepository) : ViewModel() {



    suspend fun addDiagnostic(diag : Diagnostic){
        diagnosticRepo.addDiagnostic(diag)
    }
}