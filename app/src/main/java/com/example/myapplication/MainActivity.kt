package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Screen1()
                }
            }
        }
    }
}

@Composable
fun Screen1() {
    var currency by remember { mutableStateOf(0) }
    var earnValue by remember { mutableStateOf(1) }
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Screen1",
        modifier = Modifier.fillMaxSize()
    ) {
        composable("Screen1") {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "You currently have $currency pretzels",
                    fontSize = 25.sp
                )
                OutlinedButton(
                    onClick = {
                        currency += earnValue
                    },
                    modifier = Modifier
                        .padding(10.dp)
                        .size(100.dp)

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pretzel),
                        contentDescription = "Pretzel"
                    )
                }
            }
        }
        composable("Screen2") {
            Screen2(navController = navController)
        }
    }
}

@Composable
fun Screen2(navController: NavHostController) {

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Screen1()
    }
}