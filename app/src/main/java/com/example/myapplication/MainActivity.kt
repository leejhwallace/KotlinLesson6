package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.EaseOutCirc
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
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
import kotlinx.coroutines.delay


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
    var upgrade1 by remember { mutableStateOf(false) }
    var upgrade2 by remember { mutableStateOf(false) }
    var upgrade3 by remember { mutableStateOf(false) }
    var upgrade4 by remember { mutableStateOf(false) }


    LaunchedEffect(upgrade1) {
        if (upgrade1) { while (true) { delay(5000L); currency += 1 } }
    }
    LaunchedEffect(upgrade2) {
        if (upgrade2) { while (true) { delay(2000L); currency += 1 } }
    }
    LaunchedEffect(upgrade3) {
        if (upgrade3) { while (true) { delay(1000L); currency += 1 } }
    }

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
                    onClick = { currency += earnValue },
                    modifier = Modifier.padding(10.dp).size(100.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pretzel),
                        contentDescription = "Pretzel"
                    )
                }


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {

                    if (!upgrade1) {
                        OutlinedButton(
                            onClick = { currency -= 5; upgrade1 = true },
                            enabled = currency >= 10,
                            modifier = Modifier.padding(10.dp).size(100.dp)
                        ) { Text(text = "5p\n+1/5s", fontSize = 12.sp) }
                    } else {
                        OutlinedButton(
                            onClick = {}, enabled = false,
                            modifier = Modifier.padding(10.dp).size(100.dp)
                        ) { Text(text = "+1/5s", fontSize = 12.sp) }
                    }


                    if (upgrade1) {
                        if (!upgrade2) {
                            OutlinedButton(
                                onClick = { currency -= 18; upgrade2 = true },
                                enabled = currency >= 20,
                                modifier = Modifier.padding(10.dp).size(100.dp)
                            ) { Text(text = "18p\n+1/2s", fontSize = 12.sp) }
                        } else {
                            OutlinedButton(
                                onClick = {}, enabled = false,
                                modifier = Modifier.padding(10.dp).size(100.dp)
                            ) { Text(text = "+1/2s", fontSize = 12.sp) }
                        }
                    }


                    if (upgrade2) {
                        if (!upgrade3) {
                            OutlinedButton(
                                onClick = { currency -= 45; upgrade3 = true },
                                enabled = currency >= 50,
                                modifier = Modifier.padding(10.dp).size(100.dp)
                            ) { Text(text = "45p\n+1/1s", fontSize = 12.sp) }
                        } else {
                            OutlinedButton(
                                onClick = {}, enabled = false,
                                modifier = Modifier.padding(10.dp).size(100.dp)
                            ) { Text(text = "+1/1s", fontSize = 12.sp) }
                        }
                    }
                }


                if (upgrade3) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        if (!upgrade4) {
                            OutlinedButton(
                                onClick = { currency -= 120; upgrade4 = true; earnValue = 2 },
                                enabled = currency >= 120,
                                modifier = Modifier.padding(10.dp).size(100.dp)
                            ) { Text(text = "120p\n+2/click", fontSize = 12.sp) }
                        } else {
                            OutlinedButton(
                                onClick = {}, enabled = false,
                                modifier = Modifier.padding(10.dp).size(100.dp)
                            ) { Text(text = "+2/click", fontSize = 12.sp) }
                        }
                    }
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