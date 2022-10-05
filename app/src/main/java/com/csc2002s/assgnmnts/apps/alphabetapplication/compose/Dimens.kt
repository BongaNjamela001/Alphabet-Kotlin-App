package com.csc2002s.assgnmnts.apps.alphabetapplication.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.csc2002s.assgnmnts.apps.alphabetapplication.R

/**
 * Class that captures dimens used in Compose code. The dimens that need to be consistent with the
 * View system use [dimensionResource] and are marked as composable.
 *
 */
object Dimens {

    val PaddingSmall: Dp
        @Composable get() = dimensionResource(R.dimen.margin_small)

    val PaddingNormal: Dp
        @Composable get() = dimensionResource(R.dimen.margin_normal)

    val PaddingLarge: Dp = 24.dp

    val AlphabetDetailAppBarHeight: Dp
        @Composable get() = dimensionResource(R.dimen.alphabet_detail_app_bar_height)

    val ToolbarIconPadding = 12.dp

    val ToolbarIconSize = 32.dp
}
