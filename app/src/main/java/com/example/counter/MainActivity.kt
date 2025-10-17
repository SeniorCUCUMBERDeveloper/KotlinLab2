package com.example.counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MessageSwitcher()
                }
            }
        }
    }
}

class Counter(initial: Int = 0) {
    var value by mutableIntStateOf(initial)
        private set
    fun increment() { value++ }

    companion object {
        val Saver: Saver<Counter, Int> = object : Saver<Counter, Int> {
            override fun restore(value: Int) = Counter(value)
            override fun SaverScope.save(value: Counter) = value.value
        }
    }
}

@Composable
fun MessageSwitcher() {
    val texts = listOf("Kotlin крут!", "Но C++ круче!", "А C ANSI еще круче!")
    var index by rememberSaveable { mutableStateOf(0) }
    val counter = rememberSaveable(saver = Counter.Saver) { Counter() }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "${texts[index]} — Вы нажали ${counter.value} раз",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
        }

        Button(
            onClick = {
                index = (index + 1) % texts.size
                counter.increment()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(bottom = 16.dp)
        ) {
            Text("Изменить")
        }
    }
}
