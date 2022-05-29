package com.jp.gojekassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jp.gojekassignment.data.TaskStatus
import com.jp.gojekassignment.gitlist.GitRepoListViewModel
import com.jp.gojekassignment.ui.theme.GoJekAssignmentTheme
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainActivity : ComponentActivity() {
    private val gitRepoListViewModel: GitRepoListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setScreenContent(Greeting(name = "Amar"))
    }

    //    @Composable
    fun setScreenContent(content: @Composable () -> Unit) {
        setContent {
            GoJekAssignmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    content
                }
            }
        }
    }

    fun observeTaskStatusLiveData() {
        gitRepoListViewModel.taskStatusLiveData.observe(this) {
            it ?: return@observe
            when (it) {
                is TaskStatus.Error -> {

                }
                is TaskStatus.Loaded -> {

                }
                is TaskStatus.Loading -> {

                }
                is TaskStatus.Refreshing -> {

                }
            }
        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GoJekAssignmentTheme {
        Greeting("Android")
    }
}