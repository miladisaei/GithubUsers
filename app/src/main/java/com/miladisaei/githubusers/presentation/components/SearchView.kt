package com.miladisaei.githubusers.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.miladisaei.githubusers.R

@Composable
fun SearchView(searchQuery: MutableState<String>, onSearchClick: () -> Unit) {
    val focusManager = LocalFocusManager.current
    val isVisible = remember {
        derivedStateOf {
            searchQuery.value.isNotBlank()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.primary)
            .padding(15.dp, 5.dp, 15.dp, 15.dp)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .imePadding(),
            value = searchQuery.value,
            onValueChange = {
                searchQuery.value = it
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.searchbox_hint),
                    color = MaterialTheme.colors.primaryVariant,
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center,
                )
            },
            textStyle = MaterialTheme.typography.h5,
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClick()
                    focusManager.clearFocus()
                }
            ),
            shape = RoundedCornerShape(17.dp),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colors.primary,
                backgroundColor = MaterialTheme.colors.secondaryVariant,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            trailingIcon = {
                if (isVisible.value) {
                    IconButton(
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 7.dp, 0.dp),
                        onClick = {
                            searchQuery.value = ""
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear"
                        )
                    }
                }
            },
            leadingIcon = {
                IconButton(
                    modifier = Modifier
                        .padding(7.dp, 0.dp, 0.dp, 0.dp),
                    onClick = {
                        onSearchClick()
                        focusManager.clearFocus()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                }
            }
        )
    }
}