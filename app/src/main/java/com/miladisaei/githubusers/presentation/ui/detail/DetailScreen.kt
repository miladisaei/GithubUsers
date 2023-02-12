package com.miladisaei.githubusers.presentation.ui.detail

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.miladisaei.githubusers.R
import com.miladisaei.githubusers.presentation.components.*
import com.miladisaei.githubusers.presentation.theme.Pink
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(
    username: String?,
    navController: NavHostController,
    onNavigateToFavoriteScreen: () -> Unit,
    onNavigateToDetailScreen: (String) -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {

    if (!viewModel.favoriteState.value.checkedExist)
        viewModel.checkExistUserInFavoriteList(username!!)
    if (viewModel.userState.value.data == null)
        viewModel.getUserDetails(username!!)
    if (viewModel.followersState.value.data == null)
        viewModel.getFollowers(username!!)
    if (viewModel.followingState.value.data == null)
        viewModel.getFollowing(username!!)

    Scaffold(
        topBar = {
            AppBar(
                title = username.toString(),
                navController = navController,
                containBackButton = true,
                onFavoriteClick = {
                    onNavigateToFavoriteScreen()
                },
                onSettingClick = {
                    viewModel.toggleTheme()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.toggleFavoriteUser()
                },
                backgroundColor = MaterialTheme.colors.onSecondary,
                contentColor = if (viewModel.favoriteState.value.existInFavoriteList)
                    Pink
                else MaterialTheme.colors.onPrimary
            ) {
                Icon(Icons.Filled.Favorite, "addFavorite")
            }
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = MaterialTheme.colors.primary
        ) {
            BoxWithConstraints {
                val screenHeight = maxHeight
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .background(MaterialTheme.colors.primary),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    if (viewModel.userState.value.isLoading) {
                        CircularLoading()
                    } else {
                        viewModel.userState.value.data?.let { user ->
                            AvatarUser(
                                modifier = Modifier.size(140.dp),
                                user = user
                            )
                            Text(
                                modifier = Modifier.padding(0.dp, 5.dp, 0.dp, 5.dp),
                                text = user.username,
                                color = MaterialTheme.colors.onPrimary,
                                style = MaterialTheme.typography.h5
                            )
                            user.bio?.let {
                                Text(
                                    modifier = Modifier.padding(10.dp, 0.dp, 10.dp, 5.dp),
                                    text = it,
                                    color = MaterialTheme.colors.onPrimary,
                                    style = MaterialTheme.typography.h6
                                )
                            }
                            StatisticsUser(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(90.dp)
                                    .padding(15.dp, 5.dp, 15.dp, 15.dp)
                                    .clip(shape = RoundedCornerShape(15.dp))
                                    .background(MaterialTheme.colors.background),
                                user = user
                            )
                        }
                    }


                    Column(
                        modifier = Modifier
                            .height(screenHeight)
                    ) {
                        TabLayout(
                            scrollState = scrollState,
                            onNavigateToDetailScreen = onNavigateToDetailScreen,
                            followersState = viewModel.followersState,
                            followingState = viewModel.followingState
                        )
                    }


                }
            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayout(
    scrollState: ScrollState,
    onNavigateToDetailScreen: (String) -> Unit,
    followersState: State<DetailViewModel.DataStateFollowers>,
    followingState: State<DetailViewModel.DataStateFollowing>
) {
    val tabData = listOf(
        stringResource(R.string.followers_tab_title),
        stringResource(R.string.following_tab_title)
    )
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage
    ) {
        tabData.forEachIndexed { index, item ->
            val selected = (pagerState.currentPage == index)
            val modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .clip(
                    RoundedCornerShape(
                        topStart = 15.dp,
                        topEnd = 15.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
            Tab(
                modifier = if (selected)
                    modifier.background(MaterialTheme.colors.secondaryVariant)
                else
                    modifier.background(MaterialTheme.colors.primary),
                selected = selected,
                selectedContentColor = MaterialTheme.colors.onBackground,
                unselectedContentColor = MaterialTheme.colors.onPrimary,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = {
                    Text(
                        text = item,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
            )
        }
    }

    HorizontalPager(
        count = tabData.size,
        state = pagerState,
        modifier = Modifier
            .fillMaxHeight()
            .nestedScroll(remember {
                object : NestedScrollConnection {
                    override fun onPreScroll(
                        available: Offset,
                        source: NestedScrollSource
                    ): Offset {
                        return if (available.y > 0) Offset.Zero else Offset(
                            x = 0f,
                            y = -scrollState.dispatchRawDelta(-available.y)
                        )
                    }
                }
            })
    ) { tabId ->
        when (tabId) {
            0 -> ListFollowers(
                followersState = followersState,
                onNavigateToDetailScreen = onNavigateToDetailScreen
            )
            1 -> ListFollowing(
                followingState = followingState,
                onNavigateToDetailScreen = onNavigateToDetailScreen
            )
        }
    }
}


@Composable
fun ListFollowers(
    followersState: State<DetailViewModel.DataStateFollowers>,
    onNavigateToDetailScreen: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        if (followersState.value.isLoading) {
            ItemLoading()
        } else {
            followersState.value.data?.let { usersList ->
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(usersList) { user ->
                        UserItem(user = user) {
                            // OnItemClick
                            onNavigateToDetailScreen(user.username)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ListFollowing(
    followingState: State<DetailViewModel.DataStateFollowing>,
    onNavigateToDetailScreen: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        if (followingState.value.isLoading) {
            ItemLoading()
        } else {
            followingState.value.data?.let { usersList ->
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(usersList) { user ->
                        UserItem(user = user) {
                            // OnItemClick
                            onNavigateToDetailScreen(user.username)
                        }
                    }
                }
            }
        }
    }
}