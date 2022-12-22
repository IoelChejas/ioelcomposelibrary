package com.example.app1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app1.ui.components.CustomButton
import com.example.app1.ui.components.CustomTextField
import com.example.app1.ui.theme.App1Theme
import com.example.ioelchejascomposelibrary.ui.CustomButtonIC

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App1Theme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "contacts"
                ) {
                    composable("contacts") {
                        ContactsScreen(onClick = {navController.navigate("addcontact")})
                    }
                    composable("addcontact") {
                        Column {
                            var formvalue by rememberSaveable { mutableStateOf("") }
                            CustomButton(title = "RESET", onClick = { formvalue = "" })
                            CustomTextField(value =  formvalue, onChange = {v->formvalue=v})
                            CustomButtonIC(title = "JIJIJA", onClick = {  })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ContactsScreen(onClick: ()->Unit){
    Button(
        onClick = onClick
    ){
        Text(text = "add contact")
    }
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
            }
        )
    }
}

@Composable
fun StatelessCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}

@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableStateOf(0) }
    StatelessCounter(count, { count++ }, modifier)
}

@Composable
fun WellnessTaskItem(taskName: String, onClose: () -> Unit, modifier: Modifier = Modifier) {
    var checkedState by rememberSaveable { mutableStateOf(false) }

    WellnessTaskItem(
        taskName = taskName,
        checked = checkedState,
        onCheckedChange = { newValue -> checkedState = newValue },
        onClose = onClose, // we will implement this later!
        modifier = modifier,
    )
}

@Composable
fun WellnessTaskItem(
    taskName: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = taskName
        )
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        var count by remember { mutableStateOf(0) }

        if (count > 0) {
            // This text is present if the button has been clicked
            // at least once; absent otherwise
            Text("You've had $count glasses.")
        }
        Button(onClick = { count++ }, Modifier.padding(top = 8.dp)) {
            Text("Add one")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

//@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    App1Theme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun MediaItem() {
    Column() {
        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        ){
            /*Image(
                painter = rememberImagePainter("https://lorempixel.com/400/400/people/1")
                ,contentDescription= null
            )*/
            /*AsyncImage(
                model = "https://example.com/image.jpg",
                contentDescription = null
            )*/
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.secondary)
                .padding(16.dp)
        ){
            Text(
                text = "Title 1. Este titulo puede ser muy largo y por lo tanto no se mostrara completo",
                color = Color.White,
                fontSize = 25.sp,
                maxLines = 2,
                softWrap = true,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 1.5.em,
                modifier = Modifier
                    .background(Color.Black)
                    .padding(bottom = 60.dp)
                ,
                textAlign = TextAlign.Left,
                style= MaterialTheme.typography.h4.copy(
                    shadow = Shadow(
                       offset = Offset(10f, 10f),
                        blurRadius = 10f,
                        color= Color.Red.copy(
                            alpha = 0.5f
                        )
                    )
                )
            )
        }
    }
}

//@Preview(showBackground = true, heightDp = 200, widthDp = 400)
@Composable
fun ButtonExample() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "Hello world",
                modifier = Modifier
                    .background(Color.Red)
                    .border(width = 1.dp, Color.Blue)
                    .padding(15.dp)
                    .clickable { /*TODO*/ }
                    .background(Color.Green)
                    .border(width = 1.dp, Color.Blue)
                    .padding(15.dp)
                    .background(Color.White)
            )
        }
}