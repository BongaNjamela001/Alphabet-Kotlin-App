package com.csc2002s.assgnmnts.apps.alphabetapplication.compose.alphabetdetail

import android.text.method.LinkMovementMethod
import androidx.annotation.VisibleForTesting
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.text.HtmlCompat
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.csc2002s.android.material.composethemeadapter.MdcTheme
import com.csc2002s.assgnmnts.apps.alphabetapplication.R
import com.csc2002s.assgnmnts.apps.alphabetapplication.compose.Dimens
import com.csc2002s.assgnmnts.apps.alphabetapplication.compose.utils.TextSnackbarContainer
import com.csc2002s.assgnmnts.apps.alphabetapplication.compose.visible
import com.csc2002s.assgnmnts.apps.alphabetapplication.data.Alphabet
import com.csc2002s.assgnmnts.apps.alphabetapplication.databinding.ItemAlphabetDescriptionBinding
import com.csc2002s.assgnmnts.apps.alphabetapplication.viewmodels.AlphabetDetailViewModel

data class AlphabetDetailsCallbacks(
    val onFabClick: () -> Unit,
    val onBackClick: () -> Unit,
    val onShareClick: () -> Unit
)

@Composable
fun AlphabetDetailsScreen(
    alphabetDetailsViewModel: AlphabetDetailViewModel,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit
) {
    val plant = alphabetDetailsViewModel.plant.observeAsState().value
    val isPlanted = alphabetDetailsViewModel.isPlanted.observeAsState().value
    val showSnackbar = alphabetDetailsViewModel.showSnackbar.observeAsState().value

    if (alphabet != null && isAdded != null && showSnackbar != null) {
        Surface {
            TextSnackbarContainer(
                snackbarText = stringResource(R.string.added_alphabet_to_fav),
                showSnackbar = showSnackbar,
                onDismissSnackbar = { alphabetDetailsViewModel.dismissSnackbar() }
            ) {
                AlphabetDetails(
                    alphabet,
                    isAdded,
                    AlphabetDetailsCallbacks(
                        onBackClick = onBackClick,
                        onFabClick = {
                            alphabetDetailsViewModel.addAlphabetToFav()
                        },
                        onShareClick = onShareClick
                    )
                )
            }
        }
    }
}

@VisibleForTesting
@Composable
fun AlphabetDetails(
    alphabet: Alphabet,
    isAdded: Boolean,
    callbacks: AlphabetDetailsCallbacks,
    modifier: Modifier = Modifier
) {
    // AlphabetDetails owns the scrollerPosition to simulate CollapsingToolbarLayout's behavior
    val scrollState = rememberScrollState()
    var plantScroller by remember {
        mutableStateOf(AlphabetDetailsScroller(scrollState, Float.MIN_VALUE))
    }
    val transitionState =
        remember(alphabetScroller) { alphabetScroller.toolbarTransitionState }
    val toolbarState = alphabetScroller.getToolbarState(LocalDensity.current)

    val transition = updateTransition(transitionState, label = "")
    val toolbarAlpha = transition.animateFloat(
        transitionSpec = { spring(stiffness = Spring.StiffnessLow) }, label = ""
    ) { toolbarTransitionState ->
        if (toolbarTransitionState == ToolbarState.HIDDEN) 0f else 1f
    }
    val contentAlpha = transition.animateFloat(
        transitionSpec = { spring(stiffness = Spring.StiffnessLow) }, label = ""
    ) { toolbarTransitionState ->
        if (toolbarTransitionState == ToolbarState.HIDDEN) 1f else 0f
    }

    val toolbarHeightPx = with(LocalDensity.current) {
        Dimens.AlphabetDetailAppBarHeight.roundToPx().toFloat()
    }
    val toolbarOffsetHeightPx = remember { mutableStateOf(0f) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = toolbarOffsetHeightPx.value + delta
                toolbarOffsetHeightPx.value = newOffset.coerceIn(-toolbarHeightPx, 0f)
                return Offset.Zero
            }
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            .systemBarsPadding()
            // attach as a parent to the nested scroll system
            .nestedScroll(nestedScrollConnection)
    ) {
        AlphabetDetailsContent(
            scrollState = scrollState,
            toolbarState = toolbarState,
            onNamePosition = { newNamePosition ->
                // Comparing to Float.MIN_VALUE as we are just interested on the original
                // position of name on the screen
                if (alphabetScroller.namePosition == Float.MIN_VALUE) {
                    alphabetScroller =
                        alphabetScroller.copy(namePosition = newNamePosition)
                }
            },
            alphabet = alphabet,
            isAdded = isAdded,
            imageHeight = with(LocalDensity.current) {
                Dimens.AlphabetDetailAppBarHeight + toolbarOffsetHeightPx.value.toDp()
            },
            onFabClick = callbacks.onFabClick,
            contentAlpha = { contentAlpha.value }
        )
        PlantToolbar(
            toolbarState, alphabet.name, callbacks,
            toolbarAlpha = { toolbarAlpha.value },
            contentAlpha = { contentAlpha.value }
        )
    }
}

@Composable
private fun AlphabetDetailsContent(
    scrollState: ScrollState,
    toolbarState: ToolbarState,
    alphabet: Alphabet,
    isAdded: Boolean,
    imageHeight: Dp,
    onNamePosition: (Float) -> Unit,
    onFabClick: () -> Unit,
    contentAlpha: () -> Float,
) {
    Column(Modifier.verticalScroll(scrollState)) {
        ConstraintLayout {
            val (image, fab, info) = createRefs()

            AlphabetImage(
                imageUrl = alphabet.imageUrl,
                imageHeight = imageHeight,
                modifier = Modifier
                    .constrainAs(image) { top.linkTo(parent.top) }
                    .alpha(contentAlpha())
            )

            if (!isAdded) {
                val fabEndMargin = Dimens.PaddingSmall
                PlantFab(
                    onFabClick = onFabClick,
                    modifier = Modifier
                        .constrainAs(fab) {
                            centerAround(image.bottom)
                            absoluteRight.linkTo(
                                parent.absoluteRight,
                                margin = fabEndMargin
                            )
                        }
                        .alpha(contentAlpha())
                )
            }

            AlphabetInformation(
                name = alphabet.name,
                addingInterval = alphabet.addingInterval,
                description = alphabet.description,
                onNamePosition = { onNamePosition(it) },
                toolbarState = toolbarState,
                modifier = Modifier.constrainAs(info) {
                    top.linkTo(image.bottom)
                }
            )
        }
    }
}

@Composable
private fun AlphabetImage(
    imageUrl: String,
    imageHeight: Dp,
    modifier: Modifier = Modifier,
    placeholderColor: Color = MaterialTheme.colors.onSurface.copy(0.2f)
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(data = imageUrl)
            .crossfade(true)
            .build()
    )

    Image(
        painter = painter,
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .height(imageHeight)
    )

    if (painter.state is AsyncImagePainter.State.Loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(placeholderColor)
        )
    }
}

@Composable
private fun AlphabetFab(
    onFabClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val addPlantContentDescription = stringResource(R.string.add_alphabet)
    FloatingActionButton(
        onClick = onFabClick,
        shape = MaterialTheme.shapes.small,
        // Semantics in parent due to https://issuetracker.google.com/184825850
        modifier = modifier.semantics {
            contentDescription = addAlphabetContentDescription
        }
    ) {
        Icon(
            Icons.Filled.Add,
            contentDescription = null
        )
    }
}

@Composable
private fun AlpahebetToolbar(
    toolbarState: ToolbarState,
    alphabetName: String,
    callbacks: AlphabetDetailsCallbacks,
    toolbarAlpha: () -> Float,
    contentAlpha: () -> Float
) {
    if (toolbarState.isShown) {
        PlantDetailsToolbar(
            alphabetName = alphabetName,
            onBackClick = callbacks.onBackClick,
            onShareClick = callbacks.onShareClick,
            modifier = Modifier.alpha(toolbarAlpha())
        )
    } else {
        AlphabetHeaderActions(
            onBackClick = callbacks.onBackClick,
            onShareClick = callbacks.onShareClick,
            modifier = Modifier.alpha(contentAlpha())
        )
    }
}

@Composable
private fun AlphabetDetailsToolbar(
    plantName: String,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface {
        TopAppBar(
            modifier = modifier.statusBarsPadding(),
            backgroundColor = MaterialTheme.colors.surface
        ) {
            IconButton(
                onBackClick,
                Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.a11y_back)
                )
            }
            Text(
                text = alphabetName,
                style = MaterialTheme.typography.h6,
                // As title in TopAppBar has extra inset on the left, need to do this: b/158829169
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
            val shareContentDescription =
                stringResource(R.string.menu_item_share_plant)
            IconButton(
                onShareClick,
                Modifier
                    .align(Alignment.CenterVertically)
                    .semantics { contentDescription = shareContentDescription }
            ) {
                Icon(
                    Icons.Filled.Share,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
private fun AlphabetHeaderActions(
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(top = Dimens.ToolbarIconPadding),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val iconModifier = Modifier
            .sizeIn(
                maxWidth = Dimens.ToolbarIconSize,
                maxHeight = Dimens.ToolbarIconSize
            )
            .background(
                color = MaterialTheme.colors.surface,
                shape = CircleShape
            )

        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .padding(start = Dimens.ToolbarIconPadding)
                .then(iconModifier)
        ) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.bc_back)
            )
        }
        val shareContentDescription =
            stringResource(R.string.menu_item_share_alphabet)
        IconButton(
            onClick = onShareClick,
            modifier = Modifier
                .padding(end = Dimens.ToolbarIconPadding)
                .then(iconModifier)
                // Semantics in parent due to https://issuetracker.google.com/184825850
                .semantics {
                    contentDescription = shareContentDescription
                }
        ) {
            Icon(
                Icons.Filled.Share,
                contentDescription = null
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun AlphabetInformation(
    name: String,
    wateringInterval: Int,
    description: String,
    onNamePosition: (Float) -> Unit,
    toolbarState: ToolbarState,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(Dimens.PaddingLarge)) {
        Text(
            text = name,
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .padding(
                    start = Dimens.PaddingSmall,
                    end = Dimens.PaddingSmall,
                    bottom = Dimens.PaddingNormal
                )
                .align(Alignment.CenterHorizontally)
                .onGloballyPositioned { onNamePosition(it.positionInWindow().y) }
                .visible { toolbarState == ToolbarState.HIDDEN }
        )
        Text(
            text = stringResource(id = R.string.adding_needs_prefix),
            color = MaterialTheme.colors.primaryVariant,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = Dimens.PaddingSmall)
                .align(Alignment.CenterHorizontally)
        )
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = pluralStringResource(
                    R.plurals.adding_needs_suffix,
                    wateringInterval
                ),
                modifier = Modifier
                    .padding(
                        start = Dimens.PaddingSmall,
                        end = Dimens.PaddingSmall,
                        bottom = Dimens.PaddingNormal
                    )
                    .align(Alignment.CenterHorizontally)
            )
        }
        AlphabetDescription(description)
    }
}

@Composable
private fun AlphabetDescription(description: String) {
    AndroidViewBinding(ItemAlphabetDescriptionBinding::inflate) {
        alphabetDescription.text = HtmlCompat.fromHtml(
            description,
            HtmlCompat.FROM_HTML_MODE_COMPACT
        )
        alphabetDescription.movementMethod = LinkMovementMethod.getInstance()
        alphabetDescription.linksClickable = true
    }
}

@Preview
@Composable
private fun AlphabetDetailContentPreview() {
    MdcTheme {
        Surface {
            AlphabetDetails(
                Alphabet("alphabetId", "Aa", "HTML<br>description", 6),
                true,
                AlphabetDetailsCallbacks({ }, { }, { })
            )
        }
    }
}
