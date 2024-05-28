package com.imbored

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BridgeModelFactory(
    private val repo: ResponseRepo
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BridgeModel(repo) as T
    }
}