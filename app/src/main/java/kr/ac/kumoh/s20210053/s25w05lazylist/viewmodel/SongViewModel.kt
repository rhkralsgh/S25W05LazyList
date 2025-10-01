package kr.ac.kumoh.s20210053.s25w05lazylist.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.ac.kumoh.s20210053.s25w05lazylist.model.Song

class SongViewModel : ViewModel() {
    private val _songs = MutableStateFlow<List<Song>>(emptyList())
    val songs: StateFlow<List<Song>> = _songs.asStateFlow()

    // 기존 값에 song을 더해서 새로 List<Song> 만들고 할당하는 방법
    fun add(song: Song) {
        _songs.value = _songs.value + song
    }

//    fun add(song: Song) {
//        // update() 함수를 사용하여 여러 Thread로부터 요청이 한꺼번에 몰릴 때도 안전하게 변경
//        // update()는 현재 값을 인자로 받아 새로운 값 반환
//        _songs.update { currentList ->
//            currentList + song
//        }
//    }

//    // it 사용 방법 (권장)
//    fun add(song: Song) {
//        _songs.update { it + song }
//    }

//    // Spread 연산자 (*) 사용 방법
//    fun add(song: Song) {
//        val newList = listOf(*_songs.value.toTypedArray(), song)
//        _songs.value = newList
//    }

//    // MutableList를 만든 후에 추가하는 방법
//    fun add(song: Song) {
//        val mutableList = _songs.value.toMutableList()
//        mutableList.add(song)
//
//        _songs.value = mutableList
//    }

//    // 비동기를 처리하기 위한 viewModelScope와
//    // multi-thread를 고려한 thread safety를 위한 update() 함수를
//    // 결합해서 사용하는 방법
//    fun add(song: Song) {
//        viewModelScope.launch {
//            _songs.update { it + song }
//        }
//    }
}