package com.example.app1.ui.components

import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun CustomTextField(value: String, onChange: (String) -> Unit){
    TextField(value = value, onValueChange = {v->onChange(v)})
}
