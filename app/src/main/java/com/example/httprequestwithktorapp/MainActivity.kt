package com.example.httprequestwithktorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.httprequestwithktorapp.data.remote.dto.PostResponse
import com.example.httprequestwithktorapp.data.remote.ktor.PostService
import com.example.httprequestwithktorapp.ui.theme.HttpRequestWithKtorAppTheme

//this project is just a sample how ktor works
//must not use this example in production apps
//this app made without viewmodel,dependency injection so its just an example of ktor workings


class MainActivity : ComponentActivity() {

    private val service = PostService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val posts = produceState<List<PostResponse>>(
                initialValue = emptyList(),
                producer = {
                    value = service.getPost()
                }
            )


            HttpRequestWithKtorAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    //Text("${posts.value}")

                    LazyColumn {
                        items(posts.value){
                            Text(text = it.title, fontSize = 20.sp)
                            Text(text = it.body)
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    }

                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HttpRequestWithKtorAppTheme {

    }
}