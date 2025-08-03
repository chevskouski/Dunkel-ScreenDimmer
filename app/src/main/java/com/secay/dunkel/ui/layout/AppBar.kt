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
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleButtonDefaults
import androidx.compose.material3.TonalToggleButton
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.sp
import com.secay.dunkel.R
import com.secay.dunkel.ui.theme.bagelFatOneFamily

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AppBar () {
  var checked by remember { mutableStateOf(false) }
  val size = ButtonDefaults.ExtraSmallIconSize

  TopAppBar(
    title = {
      Text(
        stringResource(R.string.app_name),
        fontFamily = bagelFatOneFamily
      )},
    actions = {
      TooltipBox (
        positionProvider = TooltipDefaults.rememberTooltipPositionProvider(
          TooltipAnchorPosition.Above
        ),
        tooltip = { PlainTooltip {
          Text( stringResource(
            if (checked) R.string.appbar_button_stop_service
            else R.string.appbar_button_start_service)
          )
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
              stateDescription =
                if (checked) "Service is currently running" else "Service is currently stopped"
            },
          shapes = ToggleButtonDefaults.shapesFor(size),
          contentPadding = ButtonDefaults.contentPaddingFor(size),
        ) {
          Icon(
            if (checked) Icons.Filled.Stop else Icons.Outlined.PlayArrow,
            contentDescription = null,
          )
          Text(
            stringResource(
              if (checked) R.string.appbar_button_label_stop_service
              else R.string.appbar_button_label_start_service
            ),
            fontSize = 12.sp
          )
        }
      }
      TooltipBox (
        positionProvider = TooltipDefaults.rememberTooltipPositionProvider(
          TooltipAnchorPosition.Above
        ),
        tooltip = { PlainTooltip {
          Text(stringResource(R.string.appbar_tooltip_open_settings))
        } },
        state = rememberTooltipState()
      ) {
        IconButton( onClick = {} ) {
          Icon(
            Icons.Filled.Settings,
            contentDescription = null
          )
        }
      }
    }
  )
}