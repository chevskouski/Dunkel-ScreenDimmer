package com.secay.dunkel.ui.layout

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleButtonDefaults
import androidx.compose.material3.TonalToggleButton
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import com.secay.dunkel.R
import com.secay.dunkel.ui.theme.bagelFatOneFamily

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
  @StringRes titleRes: Int,
  canNavigateBack: Boolean,
  isServiceRunning: Boolean,
  onServiceToggle: (Boolean) -> Unit,
  onNavigateBack: () -> Unit,
  onNavigateToSettings: () -> Unit,
  modifier: Modifier = Modifier
) {
  val appBarColors = if (isServiceRunning) {
    TopAppBarDefaults.topAppBarColors(
      containerColor = MaterialTheme.colorScheme.primaryContainer,
      titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
      navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
      actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
    )
  } else {
    TopAppBarDefaults.topAppBarColors(
      containerColor = MaterialTheme.colorScheme.surface,
      titleContentColor = MaterialTheme.colorScheme.onSurface,
      navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
      actionIconContentColor = MaterialTheme.colorScheme.onSurface
    )
  }

  val serviceButtonIcon = if (isServiceRunning) Icons.Filled.Stop else Icons.Outlined.PlayArrow
  val serviceButtonLabelRes = if (isServiceRunning) R.string.appbar_button_label_stop_service
  else R.string.appbar_button_label_start_service
  val serviceButtonTooltipRes = if (isServiceRunning) R.string.appbar_button_tooltip_stop_service
  else R.string.appbar_button_tooltip_start_service

  val serviceSemanticsDescription = stringResource(
    if (isServiceRunning) R.string.semantics_service_running
    else R.string.semantics_service_stopped
  )

  TopAppBar(
    modifier = modifier,
    title = {
      Text(
        text = stringResource(titleRes),
        fontFamily = bagelFatOneFamily
      )
    },
    navigationIcon = {
      if (canNavigateBack) {
        TooltipBox(
          positionProvider = TooltipDefaults
            .rememberTooltipPositionProvider(TooltipAnchorPosition.Below),
          tooltip = { PlainTooltip { Text(
            stringResource(R.string.tooltip_navigate_back)
          ) } },
          state = rememberTooltipState()
        ) {
          IconButton(onClick = onNavigateBack) {
            Icon(
              imageVector = Icons.Filled.ArrowBackIosNew,
              contentDescription = stringResource(R.string.content_desc_navigate_back)
            )
          }
        }
      }
    },
    actions = {
      AnimatedVisibility(
        visible = isServiceRunning || !canNavigateBack,
        enter = fadeIn(initialAlpha = 0.0f),
        exit = fadeOut(animationSpec = tween()),
      ) {
        TooltipBox(
          positionProvider = TooltipDefaults
            .rememberTooltipPositionProvider(TooltipAnchorPosition.Above),
          tooltip = { PlainTooltip { Text(stringResource(serviceButtonTooltipRes)) } },
          state = rememberTooltipState()
        ) {
          val size = ButtonDefaults.ExtraSmallIconSize
          TonalToggleButton(
            checked = isServiceRunning,
            onCheckedChange = onServiceToggle,
            modifier = Modifier
              .heightIn(size)
              .semantics {
                role = Role.Switch
                stateDescription = serviceSemanticsDescription
              },
            shapes = ToggleButtonDefaults.shapesFor(size),
            contentPadding = ButtonDefaults.contentPaddingFor(size),
          ) {
            Icon(
              imageVector = serviceButtonIcon,
              contentDescription = null
            )
            Text(
              text = stringResource(serviceButtonLabelRes),
              style = MaterialTheme.typography.labelSmall
            )
          }
        }
      }

      TooltipBox(
        positionProvider = TooltipDefaults
          .rememberTooltipPositionProvider(TooltipAnchorPosition.Above),
        tooltip = { PlainTooltip { Text(
          stringResource(R.string.appbar_tooltip_open_settings)
        ) } },
        state = rememberTooltipState()
      ) {
        IconButton(onClick = onNavigateToSettings) {
          Icon(
            imageVector = Icons.Filled.Settings,
            contentDescription = stringResource(R.string.content_desc_settings)
          )
        }
      }
    },
    colors = appBarColors
  )
}
