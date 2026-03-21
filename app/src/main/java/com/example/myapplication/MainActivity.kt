package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.delay
import kotlin.math.max
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    Screen1()
                }
            }
        }
    }
}
//Give it global access yay!!
var currency by mutableIntStateOf(0)
@Composable
fun Screen1() {
    var earnValue by remember { mutableIntStateOf(1) }
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
                    text = "With each click of the button, you gain ${earnValue*(Pretzel.getEquippedPretzel().multi+Pretzel.getEquippedPretzel().upgradeMulti)} pretzels",
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedButton(
                    onClick = {
                        currency += (earnValue*(Pretzel.getEquippedPretzel().multi+Pretzel.getEquippedPretzel().upgradeMulti)).roundToInt()
                    },
                    modifier = Modifier
                        .padding(10.dp)
                        .size(100.dp)

                ) {
                    Image(
                        painter = painterResource(id = Pretzel.getEquippedPretzel().imageId),
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
                Row(modifier=Modifier.fillMaxWidth(0.75f), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                    OutlinedButton(
                        onClick = {
                            navController.navigate("Roll")
                        }
                    ) {
                        Text("Roll")
                    }
                    OutlinedButton(
                        onClick = {
                            navController.navigate("Inv")
                        }
                    ) {
                        Text("Inv")
                    }
                    OutlinedButton(
                        onClick = {
                            navController.navigate("Screen2")
                        }
                    ) {
                        Text("Credits")
                    }
                }
            }
        }
        composable("Roll") {
            Roll(navController)
        }
        composable("Inv") {
            Inv(navController = navController)
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
            Text(
                "Member 5: Poh Jun Zhe Matthew",
                fontSize = 20.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Inv(navController: NavController){
    var currentPretzel by remember { mutableIntStateOf(0) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Inventory") },
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
        Row (
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            val pretzel = Pretzel.getPretzels()[currentPretzel]
            val lenPretzels = Pretzel.getPretzels().size
            Button(onClick = {
                currentPretzel--;
            }, enabled = currentPretzel > 0, modifier = Modifier.weight(1f)) {
                Image(
                    painter = painterResource(id = R.drawable.arrowback),
                    contentDescription = "Go forward"
                )
            }
            Column(
                modifier = Modifier.weight(3f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (pretzel.isUnlocked) {
                    Image(
                        painter = painterResource(id = pretzel.imageId),
                        contentDescription = "",
                        modifier = Modifier.size(80.dp)
                    )
                    Row(modifier = Modifier.fillMaxWidth(1f), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                        Button({
                            Pretzel.setEquippedPretzel(pretzel)
                        }) {
                            Text(if (pretzel == Pretzel.getEquippedPretzel()) "Equipped" else "Equip")
                        }
                        Button({
                            if (currency>=pretzel.upgradeCost) {
                                currency -= pretzel.upgradeCost
                                pretzel.upgrade()
                            }
                        }) {
                            pretzel.upgradeCost = Pretzel.calculateCost(pretzel)
                            Text("Upg (${pretzel.upgradeCost})")
                        }
                    }
                    Text(
                        text = "${pretzel.name} x${(((pretzel.multi + pretzel.upgradeMulti) * 10.0).roundToInt() / 10.0)}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = pretzel.description,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )

                } else {
                    Box(
                        modifier = Modifier.size(80.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "???",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Text(
                        text = "???",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "???",
                        fontSize = 12.sp,
                    )
                }
            }
            Button(onClick = {
                currentPretzel++;
            }, enabled = currentPretzel+1 < lenPretzels, modifier = Modifier.weight(1f)) {
                Image(
                    painter = painterResource(id = R.drawable.arrowfront),
                    contentDescription = "Go forward"
                )
            }
        }
    }
}

var momentum:Float by mutableFloatStateOf(0f)
var idleFontSize by mutableFloatStateOf(40f)
var rolling by mutableStateOf(false)
var fontSize by mutableFloatStateOf(idleFontSize)
var currentPretzel: Pretzel by mutableStateOf(Pretzel.getPretzels()[0])
var rolls by mutableIntStateOf(0)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Roll(navController: NavController){

    LaunchedEffect(rolling) {
        if (rolling) {
            while (momentum > 0f) {
                momentum -= 0.1f
                fontSize = (fontSize + momentum).coerceAtMost(50f)
                if (fontSize >= 50f) {
                    fontSize = idleFontSize - 10
                    val pretzels = Pretzel.getPretzels()

                    val totalWeight = pretzels.sumOf { it.weight }
                    val rand = (0 until totalWeight).random()

                    var acc = 0
                    for (pretzel in pretzels) {
                        acc += pretzel.weight
                        if (rand < acc) {
                            currentPretzel = pretzel
                            break
                        }
                    }
                }

                delay(30)
            }
            currentPretzel.setUnlocked(true)
            rolling = false
            rolls++
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Inventory") },
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painterResource(currentPretzel.imageId),
                    currentPretzel.name)
                Spacer(modifier = Modifier.width(8.dp))
                Text(currentPretzel.name, fontSize = fontSize.sp,style = TextStyle(lineHeight = fontSize.sp))
            }
            OutlinedButton(
                onClick = {
                    if (currency >= max(rolls*2,1) && ! rolling) {
                        rolling = true
                        momentum = 10f
                        currency -= rolls*2
                    }
                }
            ) {
                Text("Roll")
            }
            Text("Cost: ${max(rolls*2,1)}")
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