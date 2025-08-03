package com.secay.dunkel.ui.layout

import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PlainTooltip
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleButtonDefaults
import androidx.compose.material3.TonalToggleButton
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold () {
  var checked by remember { mutableStateOf(false) }
  val size = ButtonDefaults.ExtraSmallIconSize

  TopAppBar(
    title = { Text("Dunkel")},
    actions = {
      TooltipBox (
        positionProvider = TooltipDefaults.rememberTooltipPositionProvider(
          TooltipAnchorPosition.Above
        ),
        tooltip = { PlainTooltip {
          Text( if (checked) "Stop Service" else "Start Service" )
        } },
        state = rememberTooltipState()
      ) {
        TonalToggleButton (
          checked = checked,
          onCheckedChange = { checked = it },
          modifier = Modifier
            .heightIn(size)
            .semantics{
              role = Role.Switch
              stateDescription = if (checked) "On" else "Off"
            },
          shapes = ToggleButtonDefaults.shapesFor(size),
          contentPadding = ButtonDefaults.contentPaddingFor(size),
        ) {
          Icon(
            if (checked) Icons.Filled.Stop else Icons.Outlined.PlayArrow,
            contentDescription = null,
          )
          Text(
            if (checked) "Stop" else "Start",
            fontSize = 12.sp
          )
        }
      }
      TooltipBox (
        positionProvider = TooltipDefaults.rememberTooltipPositionProvider(
          TooltipAnchorPosition.Above
        ),
        tooltip = { PlainTooltip { Text("Open Configuration") } },
        state = rememberTooltipState()
      ) {
        IconButton( onClick = {} ) {
          Icon(
            Icons.Filled.Settings,
            contentDescription = "Configuration"
          )
        }
      }
    }
  )
}
