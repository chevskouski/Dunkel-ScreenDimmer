package com.secay.dunkel.ui.screens.dunkel

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DunkelScreen (
  isServiceRunning: Boolean
) {
  Text("Dunkel Screen")
  /*Text(
    if (isServiceRunning) "Service läuft" else "Service läuft nicht"
  )
  Button(
    onClick = {
      onServiceToggle(!isServiceRunning)
    }
  ){
    Text(
      if (isServiceRunning) "Service beenden" else "Service starten"
    )
  }*/
}