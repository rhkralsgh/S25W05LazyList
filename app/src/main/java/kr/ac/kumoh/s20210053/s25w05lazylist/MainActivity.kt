package kr.ac.kumoh.s20210053.s25w05lazylist

import android.content.res.Configuration
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
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
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

        viewModel.add(Song(index * 3 + 3, "흔들리는 꽃들 속에서 네 샴푸향이 느껴진거야", "장범준"))
        viewModel.add(Song(index * 3 + 4, "어떻게 이별까지 사랑하겠어, 널 사랑하는 거지", "악동뮤지션"))
        viewModel.add(Song(index * 3 + 5, "사랑하긴 했었나요 스쳐가는 인연이었나요 짧지 않은 우리 함께했던 시간들이 자꾸 내 마음을 가둬두네", "잔나비"))

    }

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
    Text(title,
        fontSize = 30.sp,
        lineHeight = 40.sp,)
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
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    if (isPortrait) {
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
    else {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalItemSpacing = 8.dp,
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(songs) { song ->
                SongItem(song)
            }
        }
    }
}