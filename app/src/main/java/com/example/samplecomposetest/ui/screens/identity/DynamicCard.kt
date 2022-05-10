package com.example.samplecomposetest.ui.screens.identity

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.samplecompose.ui.screens.identity.DynamicCardState
import com.example.samplecomposetest.ui.theme.GidTheme
import com.example.samplecomposetest.R


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DynamicCardPreview() {
    val cards = listOf(
        DynamicCardState(true, null, "Self declared credentials", "Add my vaccination"),
        DynamicCardState(true, "Add your selfie credential", "Biometric", "Start"),
        DynamicCardState(true, "XRP Mastercard® Debit Card", "Get the card", "Get the card")
    )

    GidTheme {
        LazyRow(modifier = Modifier
            .padding(start = 4.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
            // content spacing
            horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(cards) {
                DynamicCard(item = it)
            }
        }
    }
}

@Composable
fun DynamicCard(item: DynamicCardState) {
    Card(modifier = Modifier
        .size(340.dp),
        backgroundColor = Color(0xFF0D2460)
    ) {
        CompositionLocalProvider(LocalContentColor provides Color.White) {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier.padding(start = 25.dp, top = 17.dp, end = 16.dp, bottom = 29.dp)) {
                Column(modifier = Modifier
                    .align(Alignment.TopEnd)
                    .fillMaxWidth()) {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Box(modifier = Modifier.size(ButtonDefaults.IconSize)) {
                            Icon(Icons.Filled.Close,
                                contentDescription = "close",
                                tint = Color(0xFFFFFFFF))
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                    ) {
                        Text(if (item.isNew) "NEW" else "", style = TextStyle(Color(0xFFF7F7F7)))
                        Box(modifier = Modifier.size(ButtonDefaults.IconSize * 4)) {
                            Image(painter = painterResource(id = R.drawable.dynamic_card2),
                                contentDescription = "logo")
                        }
                    }
                }
                Column(modifier = Modifier
                    .align(Alignment.BottomStart)
                    .wrapContentWidth(Alignment.Start)) {
                    Text("Issued by ${item.type}", style = MaterialTheme.typography.caption.copy(Color(0xFFF7F7F7)),)
                    item.title?.let{
                        Text(item.title, style = MaterialTheme.typography.h4)
                    }
                    Spacer(Modifier.height(8.dp))
                    ActionBtn(item)
                }
            }
        }
    }
}

@Composable
private fun ActionBtn(item: DynamicCardState) {
    OutlinedButton(onClick = {},
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent,
            contentColor = Color.White), border = BorderStroke(ButtonDefaults.OutlinedBorderSize,
            color = Color.White))
    {
        Text(item.actionText)
        Icon(Icons.Default.ArrowForward, contentDescription = null)
    }
}


