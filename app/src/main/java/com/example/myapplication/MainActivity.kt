package com.example.myapplication

import android.R.attr.text
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.benchmark.traceprocessor.Row
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier


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
    var warn by remember {mutableStateOf("")}
    var upgradeDescription by remember {mutableStateOf("")}

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
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "With each click of the button, you gain $earnValue pretzels",
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
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
                Spacer(modifier = Modifier.height(16.dp))
                Text(warn)
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    OutlinedButton(
                        onClick = {
                            upgradeDescription = "You need at least 1 pretzel for this upgrade.  \n" +
                                    "This upgrade adds 1 pretzel to the amount \n" +
                                    "of pretzels you gain per click."
                            if (currency >= 1) {
                                earnValue += 1
                                currency -= 1
                                warn = ""
                            } else {
                                warn = "Not enough pretzels for upgrade 1"
                            }
                        }
                    ) {
                        Text("Upgrade 1")
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    OutlinedButton(
                        onClick = {
                            upgradeDescription = "You need at least 5 pretzels for this upgrade.  \n" +
                                    "This upgrade adds 5 pretzels to the amount \n" +
                                    "of pretzels you gain per click."
                            if (currency >= 5) {
                                earnValue += 5
                                currency -= 5
                                warn = ""
                            } else {
                                warn = "Not enough pretzels for upgrade 2"
                            }
                        }
                    ) {
                        Text("Upgrade 2")
                    }
                }
                Row {
                    OutlinedButton(
                        onClick = {
                            upgradeDescription = "You need at least 10 pretzels for this upgrade.  \n" +
                                    "This upgrade adds 10 pretzels to the amount \n" +
                                    "of pretzels you gain per click."
                            if (currency >= 10) {
                                earnValue += 10
                                currency -= 10
                                warn = ""
                            } else {
                                warn = "Not enough pretzels for upgrade 3"
                            }
                        }
                    ) {
                        Text("Upgrade 3")
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    OutlinedButton(
                        onClick = {
                            upgradeDescription = "You need at least 50 pretzels for this upgrade.  \n" +
                                    "This upgrade adds 50 pretzels to the amount \n" +
                                    "of pretzels you gain per click."
                            if (currency >= 50) {
                                earnValue += 50
                                currency -= 50
                                warn = ""
                            } else {
                                warn = "Not enough pretzels for upgrade 4"
                            }
                        }
                    ) {
                        Text("Upgrade 4")
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(upgradeDescription)
                Spacer(modifier = Modifier.height(80.dp))
                OutlinedButton(
                    onClick = {
                        navController.navigate("Screen2")
                    }
                ) {
                    Text("Go to credits page")
                }
            }
        }
        composable("Screen2") {
            Screen2(navController = navController)
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Screen2(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Another Page") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.arrowback),
                            contentDescription = "Pretzel"
                        )
                    }
                })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                "Member 1: Lee Jia Hang Wallace",
                fontSize = 20.sp
            )
            Text(
                "Member 2: Aaron Justin Quah",
                fontSize = 20.sp
            )
            Text(
                "Member 3: Tang Ke Wei Daveon",
                fontSize = 20.sp
            )
            Text(
                "Member 4: Arun Kumar Hemansrikar",
                fontSize = 20.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Screen1()
    }
}