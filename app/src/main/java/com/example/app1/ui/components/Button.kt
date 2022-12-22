package com.example.app1.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app1.ui.theme.Green100

@Composable
fun CustomButton(title: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = Modifier,
        border = BorderStroke(2.dp, Color.Black),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Green100
        ),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Image(
            painterResource(id = com.example.app1.R.drawable.ic_launcher_foreground),
            contentDescription ="Cart button icon",
            modifier = Modifier.size(50.dp))
        Text(text = title)
    }
}

@Preview(showBackground = true)
@Composable
fun TestCustomButton() {
    CustomButton(title = "Button", onClick = {})
}