package com.secay.dunkel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.secay.dunkel.ui.layout.MainScaffold
import com.secay.dunkel.ui.theme.DunkelTheme

class DunkelActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      DunkelTheme {
        MainScaffold()
      }
    }
  }
}
