package com.example.samplecomposetest.ui.screens.identity

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.samplecompose.ui.screens.identity.EditProfileBtn
import com.example.samplecomposetest.ui.theme.GidTheme
import com.example.samplecomposetest.R
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode


@Preview
@Composable
fun IdentityScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color(0xFFF7F7F7))
            .padding(2.dp)
            .verticalScroll(rememberScrollState())
    ) {
        /*HeaderProfile()*/
        IdentityHeader()

        Text("Add to GlobalID", style = MaterialTheme.typography.h5)
        BoardingCards()

        DynamicOnboarding()
        BoardingCards()
        BoardingCards()

        DynamicOnboarding()
        DynamicOnboarding()

        Text("Knowledge Base", style = MaterialTheme.typography.h5)
        BoardingCards()
    }
}

@Composable
fun DynamicOnboarding() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text("Discover GlobalID", style = MaterialTheme.typography.h5)
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row() {
                for (i in 0..4) {
                    Surface(
                        modifier = Modifier
                            .background(Color.Gray)
                            .weight(1.0f)
                            .height(10.dp)
                    ) {}
                }
            }
            Column() {
                Text("Vaccination passport")
                Text("Add your vaccination card to your credentials and add it to your Apple Wallet.")
            }

            Button(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Check, contentDescription = "check")
                Text("Added")
            }
        }
    }
}

@Composable
fun HeaderProfile(modifier: Modifier = Modifier) {
    val count = 5
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Identity", style = MaterialTheme.typography.h4)
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Notifications,
                contentDescription = "Notifications $count",
                modifier = Modifier.size(32.dp)
            )
            Spacer(Modifier.width(8.dp))
            Icon(
                Icons.Default.MoreVert,
                contentDescription = "Settings",
                modifier = Modifier.size(32.dp)
            )
        }
    }
}


@Composable
fun IdentityHeader(modifier: Modifier = Modifier) {
    val avatarOffset = 50.dp

    Row(
        modifier = modifier
            .wrapContentHeight()
            .background(color = Color.Red)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.avatar),
            contentDescription = "avatar", modifier = Modifier
                .size(avatarOffset * 2)
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("John Morgan", style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(8.dp))
            Text("global.id/johnmorgan", style = MaterialTheme.typography.subtitle1)
        }
        EditProfileBtn()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BoardingCards() {
    val data = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6")

    FlowRow(
        mainAxisSize = SizeMode.Expand,
        mainAxisAlignment = FlowMainAxisAlignment.SpaceEvenly
    ) {
        for(item in data) {
            Card(
                modifier = Modifier.padding(4.dp),
                backgroundColor = MaterialTheme.colors.background
            ) {
                Text(
                    text = item,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

data class ProfileViewState(val fullName: String, val gid: String, val photoUuid: String)
