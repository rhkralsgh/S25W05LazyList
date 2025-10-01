package kr.ac.kumoh.s20210053.s25w05lazylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kr.ac.kumoh.s20210053.s25w05lazylist.model.Song
import kr.ac.kumoh.s20210053.s25w05lazylist.ui.theme.S25W05LazyListTheme
import kr.ac.kumoh.s20210053.s25w05lazylist.viewmodel.SongViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            S25W05LazyListTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(
    viewModel: SongViewModel = viewModel()
) {
    val listSong by viewModel.songs.collectAsState()

    repeat(30) { index ->
        viewModel.add(Song(index * 3, "Golden", "HUNTR/X"))
        viewModel.add(Song(index * 3 + 1, "Drowning", "WOODZ"))
        viewModel.add(Song(index * 3 + 2, "Soda Pop", "Saja Boys"))
    }

    viewModel.add(Song(1, "Golden", "HUNTR/X"))
    viewModel.add(Song(2, "Drowning", "WOODZ"))
    viewModel.add(Song(3, "Soda Pop", "Saja Boys"))

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        MyList(
            modifier = Modifier.padding(innerPadding),
            songs = listSong,
        )
    }
}

@Composable
fun SongItem(song: Song) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xffffffcc))
            .padding(16.dp)
    ) {
        TextTitle(song.title)
        TextSinger("이 노래를 부른 가수는 ${song.singer} 입니다")
    }
}

@Composable
fun TextTitle(title: String) {
    Text(title, fontSize = 30.sp)
}

@Composable
fun TextSinger(singer: String) {
    Text(singer, fontSize = 20.sp)
}

@Composable
fun MyList(
    modifier: Modifier = Modifier,
    songs: List<Song>,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(songs) { song ->
            SongItem(song)
        }
    }
}