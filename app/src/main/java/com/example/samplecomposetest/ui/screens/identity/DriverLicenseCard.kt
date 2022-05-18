package com.example.samplecomposetest.ui.screens.identity

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.samplecomposetest.R

data class DriverLicenseState(
    val catName: String,
    val verificationDate: String,
    val verifiedBy: String,
    val issuesBy: String
)

@Preview(showSystemUi = true)
@Composable
fun DlPreview() {
    val item = DriverLicenseState(
        "Driver License",
        "Feb 23 2021",
        "Cardholder",
        "Veriff",
    )
    DriverLicenseCard(item, {})
}


@Composable
fun DriverLicenseCard(item: DriverLicenseState, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val bgColor = Color(0xFF010080)
    val contentColor = Color(0xFFF7F7F7)

    Card(
        modifier = modifier
            .widthIn(340.dp)
            .aspectRatio(1.6f)
            .clickable(onClick = onClick),
        shape = RoundCornersShape12,
        backgroundColor = bgColor
    ) {
        CompositionLocalProvider(LocalContentColor provides contentColor) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(18.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_driver_license),
                            contentDescription = "category",
                            tint = bgColor,
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(contentColor)
                        )
                        Spacer(modifier = Modifier.width(9.dp))
                        Text(text = item.catName, style = MaterialTheme.typography.body1)
                    }
                    Box(
                        modifier = Modifier.size(ButtonDefaults.MinHeight),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_meatball_hor),
                            contentDescription = "more",
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    //Verification labels
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 0.dp, bottom = 8.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.verifications_card_attested_by, item.verifiedBy),
                            fontSize = 11.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Text(
                            text = item.verificationDate,
                            fontSize = 11.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Text(
                            text = "Issued by ${item.issuesBy}",
                            fontSize = 11.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

