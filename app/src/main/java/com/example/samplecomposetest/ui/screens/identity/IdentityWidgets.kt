package com.example.samplecompose.ui.screens.identity

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.samplecomposetest.R


data class OnBoardingItemState(val title: String, val subtitle: String, val isDone: Boolean = false)
data class CardState(val title: String, val subtitle: String, val issuer: String, val isPinned: Boolean = false)
data class DynamicCardState(
    val isNew: Boolean,
    val title: String?,
    val type: String,
    val actionText: String,
)

sealed class DynamicCardLce{
    object Loading : DynamicCardLce()
    class Success(val dyn : DynamicCardState): DynamicCardLce()
}



@Preview
@Composable
fun DynamicItemPreviews() {
    val cards = listOf(
        OnBoardingItemState("Get your first credentials",
            "The power to act", true),
        OnBoardingItemState("Add profile photo",
            "Build your profile"))
    Column {
        cards.forEach { DynamicOnBoardingItem(it)
        Spacer(modifier = Modifier.height(20.dp))}
    }
}

@Preview
@Composable
fun CredentialCardPreview() {
    val item = CardState("COVID-19 Vaccination", "Government ID", "Verif", true)
    CredentialCard(item)
}

@Preview()
@Composable
fun EditProfileBtn() {
    Box(contentAlignment = Alignment.Center) {
        Button(onClick = {},
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .padding(4.dp)
                .width(130.dp)) {
            Text("Edit profile")
        }
    }
}

@Preview
@Composable
fun RoundAddBtn() {
    Button(onClick = { /*TODO*/ },
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black, contentColor = Color.White),
        modifier = Modifier.size(40.dp, 40.dp),
        contentPadding = PaddingValues(0.dp)) {
        Box {
            Icon(Icons.Default.Add, contentDescription = "Add", modifier = Modifier.size(30.dp))
        }
    }
}

@Composable
fun DynamicItemBtn(isDone: Boolean, modifier: Modifier = Modifier) {
    val zeroElevation = ButtonDefaults.elevation(defaultElevation = 0.dp,
        pressedElevation = 0.dp,
        hoveredElevation = 0.dp,
        focusedElevation = 0.dp)


    Button(onClick = { /*TODO*/ },
        colors = if (isDone) ButtonDefaults.buttonColors(
            backgroundColor = Color(0x341EEA4A),
            contentColor = Color(0xFF0A8616))
        else
            ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFF7F7F7),
                contentColor = Color.Black),
        shape = RoundedCornerShape(50),
        elevation = zeroElevation,
        contentPadding = PaddingValues(0.dp),
        modifier = modifier.size(120.dp, 32.dp)) {
        if (isDone) {
            Icon(Icons.Default.Check,
                contentDescription = "Done",
                modifier = Modifier.size(ButtonDefaults.IconSize))
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Done")
        } else
            Text("Get started")
    }
}

@Composable
fun CredentialCard(item: CardState) {
    Card(modifier = Modifier
        .height(210.dp)
        .aspectRatio(1.6f),
        backgroundColor = Color(0xFF404245)
    ) {
        CompositionLocalProvider(LocalContentColor provides Color(0xFFF7F7F7)) {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier.padding(start = 25.dp, top = 17.dp, end = 16.dp, bottom = 29.dp)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .fillMaxWidth(),
                ) {
                    Text(text = item.title, style = MaterialTheme.typography.body1)
                    Box(modifier = Modifier.size(ButtonDefaults.IconSize)) {
                        Icon(painter = painterResource(id = R.drawable.ic_pin),
                            contentDescription = "pin",
                            tint = Color(0xFFF7F7F7))
                    }
                }
                Column(modifier = Modifier.align(Alignment.BottomStart)) {
                    Row(horizontalArrangement = Arrangement.Start) {
                        Column {
                            Text(item.title, style = MaterialTheme.typography.h5)
                            Text("Issued by ${item.subtitle}", style = MaterialTheme.typography.body1)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DynamicOnBoardingItem(onBoardingItemState: OnBoardingItemState) {
    Card(modifier = Modifier
        .fillMaxWidth()
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(16.dp)) {
            Column {
                Text(onBoardingItemState.title, style = MaterialTheme.typography.body1)
                Text(onBoardingItemState.subtitle, style = MaterialTheme.typography.caption)
            }

            DynamicItemBtn(onBoardingItemState.isDone)
        }
    }
}
