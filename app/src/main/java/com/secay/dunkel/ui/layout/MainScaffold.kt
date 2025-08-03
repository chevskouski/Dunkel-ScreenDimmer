package com.secay.dunkel.ui.layout

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.secay.dunkel.ui.screens.dunkel.DunkelScreen
import com.secay.dunkel.ui.screens.settings.SettingsScreen
import kotlinx.serialization.Serializable

@Serializable
data object Dunkel: NavKey

@Serializable
data object Settings: NavKey
@Composable
fun MainScaffold () {
  val backStack = rememberNavBackStack(Dunkel)

  Scaffold (
    topBar = { AppBar(backStack = backStack) }
  ) { innerPadding ->
    NavDisplay(
      backStack = backStack,
      modifier = Modifier.padding(innerPadding),
      onBack = { backStack.removeLastOrNull() },
      entryProvider = entryProvider {
        entry<Dunkel>  {
          DunkelScreen()
        }
        entry<Settings> {
          SettingsScreen()
        }
      },
      transitionSpec = {
        slideInHorizontally(initialOffsetX = { it }) togetherWith
            slideOutHorizontally(targetOffsetX = { -it })
      },
      popTransitionSpec = {
        slideInHorizontally(initialOffsetX = { -it }) togetherWith
            slideOutHorizontally(targetOffsetX = { it })
      },
      predictivePopTransitionSpec = {
        slideInHorizontally(initialOffsetX = { -it }) togetherWith
            slideOutHorizontally(targetOffsetX = { it })
      }
    )
  }
}
