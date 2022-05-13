package com.example.samplecomposetest.ui.screens.identity

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.samplecomposetest.R
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode


val RoundCornersShape12 = RoundedCornerShape(12.dp)

@Preview(showSystemUi = true)
@Composable
fun IdentityScreenPreview(){
    IdentityScreen(){

    }
}


@Composable
fun IdentityScreen(onViewAllClicked: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .background(Color(0xFFF7F7F7))
            .padding(horizontal = 16.dp)
            .wrapContentHeight()
            .verticalScroll(rememberScrollState())
    ) {
        HeaderProfile()
        IdentityHeader()

        FavCardStack {
            val credState = CardState("COVID-19 Vaccination", "Government ID", "Verif", true)
            val dlState = DriverLicenseState(
                "Driver License",
                "Jesse Middlename",
                "Cardholder",
                "",
                "01/02/2000",
                "01/02/2018",
                "01/02/2024"
            )
            CredentialCard(item = credState, modifier = Modifier.shadow(5.dp))
            DriverLicenseCard(item = dlState, modifier = Modifier.shadow(5.dp))
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            TextButton(onClick = onViewAllClicked) {
                Text(
                    "View all",
                    style = MaterialTheme.typography.button.copy(
                        color = Color(0xFF0D51FF)
                    )
                )
            }
        }

        BoardingCards()
        DynamicOnboarding()
        KnowledgeBase()
    }
}


@Composable
fun FavCardStack(content: @Composable() () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(-180.dp)) {
        content()
    }
}

@Composable
fun KnowledgeBase() {
    Column {
        Text("Knowledge Base", style = MaterialTheme.typography.h5)
        KnowledgeBaseCards()
    }
}

@Preview
@Composable
fun DynamicOnboarding() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()

    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Discover GlobalID", style = MaterialTheme.typography.h5)
            Text(
                "10%",
                style = MaterialTheme.typography.button,
                color = Color.White,
                modifier = Modifier.background(Color.Blue, RoundCornersShape12)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .clip(RoundCornersShape12)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ProgressSurface()
            Column() {
                Text(
                    "Vaccination passport",
                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.SemiBold)
                )
                Text(
                    "Add your vaccination card to your credentials and add it to your Apple Wallet.",
                    style = MaterialTheme.typography.caption.copy(color = Color(0xFF646466)),
                )
            }

            TextButton(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.textButtonColors(contentColor = Color(0xFF17C33D))
            ) {
                Icon(Icons.Default.Check, contentDescription = "check")
                Text("Added")
            }

        }
    }
}

@Preview(showSystemUi = true)
@Composable fun ProgressSurface() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(2.dp)) {
        val selectedIndex = 3
        for (i in 0..4) {
            Surface(
                color = if(i != selectedIndex ) Color(0xFFEFEFEF) else Color(0xFF101010),
                modifier = Modifier
                    .clip(RoundedCornerShape(9.dp))
                    .height(4.dp)
                    .weight(1.0f)

            ) {}
        }
    }
}

@Composable
fun HeaderProfile(modifier: Modifier = Modifier) {
    val count = 5
    Row(
        modifier = modifier
            .padding(top = 16.dp)
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
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundCornersShape12)
            .background(color = Color.White)
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "avatar", modifier = Modifier
                    .size(48.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text("John Morgan", style = MaterialTheme.typography.h5)
                Text("global.id/johnmorgan", style = MaterialTheme.typography.subtitle1)
            }
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_chevron_up),
                contentDescription = "details",
                modifier = Modifier.rotate(90.0f)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BoardingCards() {
    val data = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6")

    Column {
        Text("Add to GlobalID", style = MaterialTheme.typography.h6)
        Spacer(Modifier.height(8.dp))
        FlowRow(
            mainAxisSize = SizeMode.Expand,
            mainAxisAlignment = FlowMainAxisAlignment.SpaceEvenly
        ) {
            for (item in data) {
                Level1CardOnboardingCard(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(0.48f)
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun KnowledgeBaseCards() {
    val data = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6")

    Column {
        FlowRow(
            mainAxisSize = SizeMode.Expand,
            mainAxisAlignment = FlowMainAxisAlignment.SpaceEvenly
        ) {
            for (item in data) {
                KnowledgeBaseCard(
                    modifier = Modifier
                        .padding(2.dp)
                        .fillMaxWidth(0.31f)
                )
            }
        }
    }
}

@Composable fun Level1CardOnboardingCard(modifier: Modifier = Modifier) {
    val bgColor = Color(0xFF010080)
    val contentColor = Color(0xFFF7F7F7)
    CompositionLocalProvider(LocalContentColor provides contentColor) {
        Card(
            modifier = modifier
                .aspectRatio(1.6f),
            shape = RoundCornersShape12,
            backgroundColor = bgColor,
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.padding(top = 12.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_driver_license),
                    contentDescription = "catIcon",
                    tint = bgColor,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(contentColor)
                )
                Text(
                    "Driver's License",
                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                )
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Text(
                        "Veriff",
                        style = MaterialTheme.typography.caption.copy(
                            color = contentColor.copy(alpha = 0.6f)
                        )
                    )
                }
            }
        }
    }
}

@Composable fun KnowledgeBaseCard(modifier: Modifier = Modifier) {
    val bgColor = Color(0xFF0D51FF)
    val contentColor = Color(0xFFF7F7F7)
    CompositionLocalProvider(LocalContentColor provides contentColor) {
        Card(
            modifier = modifier
                .aspectRatio(1f),
            shape = RoundCornersShape12,
            backgroundColor = bgColor,
        ) {
            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Sharing of your GiD identity", style = MaterialTheme.typography.h6)
            }
        }
    }
}

data class ProfileViewState(val fullName: String, val gid: String, val photoUuid: String)
