package pl.podwikagrzegorz.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pl.podwikagrzegorz.data.model.MemeListEntry
import pl.podwikagrzegorz.data.model.toListEntries
import pl.podwikagrzegorz.data.repo.MemeRepository
import pl.podwikagrzegorz.util.Resource
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: MemeRepository
) : ViewModel() {
    private var _memeList = mutableStateOf<List<MemeListEntry>>(listOf())
    val memeList : State<List<MemeListEntry>>
        get() = _memeList

    var isLoading = mutableStateOf(false)
    var loadError = mutableStateOf("")

    fun loadMemes() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repo.getMemeList()
            when(result) {
                is Resource.Success -> {
                    val entries = result.data!!.data.memes.toListEntries()

                    isLoading.value = false
                    loadError.value = ""
                    _memeList.value += entries
                }

                is Resource.Error -> {
                    loadError.value = result.message!!
                    isLoading.value = false
                }
            }
        }
    }
}