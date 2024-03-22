package com.example.composebasicojetpack

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composebasicojetpack.ui.theme.MySootheTheme


@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        var count by rememberSaveable { mutableStateOf(0) }
        if (count > 0) {

            Text("You've had $count glasses.")
        }

        Row(Modifier.padding(top = 8.dp)) {
            Button(onClick = { count++ }, enabled = count < 10) {
                Text("Add one")
            }
            Button(
                onClick = { count = 0 },
                Modifier.padding(start = 8.dp)) {
                Text("Clear water count")
            }
        }
    }
}


@Composable
fun StatelessCounter(count: Int, onIncrement: () -> Unit,onDecrement: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {

            Text("You've had $count glasses.")
        }
        Row(Modifier.padding(top = 8.dp)) {
            Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
                Text("Add one")
            }
            Button(onClick = onDecrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
                Text("Clear water count")
            }
        }
    }
}
@Composable
fun StatefulCounter() {
    var count by remember { mutableStateOf(0) }
    StatelessCounter(count, { count++ }, { count=0 })
}

@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    wellnessViewModel: WellnessViewModel = viewModel()
) {
    Column(modifier = modifier) {
        StatefulCounter()

        WellnessTasksList(
            list = wellnessViewModel.tasks,
            onCheckedTask = { task, checked ->
                wellnessViewModel.changeTaskChecked(task, checked)
            },
            onCloseTask = { task ->
                wellnessViewModel.remove(task)
            })
    }
}




@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun WellnessScreenPreview() {
    MySootheTheme { WellnessScreen() }
}