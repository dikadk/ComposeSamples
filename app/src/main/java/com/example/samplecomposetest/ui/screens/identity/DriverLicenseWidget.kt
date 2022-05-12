package com.example.samplecomposetest.ui.screens.identity

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.samplecomposetest.R
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode
import kotlinx.coroutines.launch
import kotlin.math.ceil


data class DriverLicenseState(
    val catName: String,
    val firstMiddleName: String,
    val lastName: String,
    val imageUrl: String,
    val dataOfBirth: String,
    val dateOfIssue: String,
    val dateOfExpiration: String
)

data class DriverLicenseDetailsState(
    val catName: String,
    val firstMiddleName: String,
    val lastName: String,
    val imageUrl: String,
    val region: String,
    val country: String,
    val dataOfBirth: String,
    val dateOfIssue: String,
    val dateOfExpiration: String,
    val licenseNum: String,
    val gender: String,
    val docs: List<String>,
    val verificationDate: String,
    val verifiedBy: String,
    val issuesBy: String
)


@Preview(showSystemUi = true)
@Composable
fun DlPreview() {
    val item = DriverLicenseState(
        "Driver License",
        "Jesse Middlename",
        "Cardholder",
        "",
        "01/02/2000",
        "01/02/2018",
        "01/02/2024"
    )
    DriverLicenseCard(item)
}

@Preview(showSystemUi = true)
@Composable
fun DlDetailsPreview() {
    val item = DriverLicenseDetailsState(
        "Driver License",
        "Jesse Middlename",
        "Cardholder",
        "",
        "FL",
        "United States",
        "01/02/2000",
        "01/02/2018",
        "01/02/2024",
        "S123456789",
        "M",
        listOf("image1", "image2"),
        "February 25, 2021",
        "GlobaliD Platform",
        "GlobalID Platform"
    )
    DriverLicenseCardDetails(item, {}, {})
}

@Composable
fun DriverLicenseCard(item: DriverLicenseState) {
    val bgColor = Color(0xFF010080)
    val contentColor = Color(0xFFF7F7F7)

    Card(
        modifier = Modifier
            .widthIn(340.dp)
            .aspectRatio(1.6f),
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

                Box(contentAlignment = Alignment.TopCenter) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier
                            .height(IntrinsicSize.Min)
                            .fillMaxWidth(),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.avatar),
                            contentDescription = "category",
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                        )
                        Column(
                            modifier = Modifier
                                .padding(start = 24.dp)
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = item.firstMiddleName,
                                style = MaterialTheme.typography.body1
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = item.lastName, style = MaterialTheme.typography.body1)
                        }
                    }
                }


                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    ColumnLabelValueGroup("date of birth", item.dataOfBirth)
                    ColumnLabelValueGroup("date of expiration", item.dateOfExpiration)
                }
            }
        }
    }
}
@Composable
fun DriverLicenseCardDetails(
    item: DriverLicenseDetailsState,
    onBackClicked: () -> Unit,
    onMoreBtnClicked: () -> Unit
) {
    val bgColor = Color(0xFF010080)
    val contentColor = Color(0xFFF7F7F7)

    var expandedState by remember { mutableStateOf(false) }

    CompositionLocalProvider(LocalContentColor provides contentColor) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .animateContentSize()
                .verticalScroll(rememberScrollState())
                .background(bgColor)
                .padding(top = 18.dp, start = 18.dp, end = 18.dp)
        ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    BackBtn(modifier = Modifier.size(36.dp, 4.dp), onBackClicked)
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 32.dp)
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
                            tint = Color(0xFFF7F7F7),
                            modifier = Modifier.clickable { onMoreBtnClicked.invoke() }
                        )
                    }
                }


                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = 32.dp, bottom = 32.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.avatar),
                        contentDescription = "category",
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                    )
                    Column(
                        modifier = Modifier
                            .padding(start = 24.dp)
                            .wrapContentHeight(Alignment.CenterVertically),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = item.firstMiddleName.split(" ").first(),
                            style = MaterialTheme.typography.body1
                        )
                        Text(
                            text = item.firstMiddleName.split(" ").last(),
                            style = MaterialTheme.typography.body1
                        )
                        Text(text = item.lastName, style = MaterialTheme.typography.body1)
                    }
                }

                Column(modifier = Modifier.fillMaxWidth()) {
                    HorizontalBorderLine()
                    Text(item.region, style = MaterialTheme.typography.h1)
                    HorizontalBorderLine()
                    Spacer(Modifier.height(8.dp))
                    Text(item.country, style = MaterialTheme.typography.h4)
                }


                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(top = 40.dp)
                ) {
                    ColumnLabelValueGroup("date of birth", item.dataOfBirth)
                    ColumnLabelValueGroup("date of issue", item.dateOfIssue)
                    ColumnLabelValueGroup("date of expiration", item.dateOfExpiration)
                    ColumnLabelValueGroup("license number", item.licenseNum)
                    ColumnLabelValueGroup("gender", item.gender)
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    ExpandButton(expandedState, Modifier.fillMaxWidth()) {
                        expandedState = !expandedState
                    }
                }

                //Support Documents
                if (expandedState) {
                    SupportDocGrid(
                        item.docs, Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                    )
                }

                //Verification labels
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = if(expandedState) 25.dp else 0.dp, bottom = 8.dp)
                ) {
                    Text(
                        text = item.verifiedBy,
                        fontSize = 11.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Text(
                        text = item.dateOfIssue,
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

@Composable
private fun ExpandButton(
    expandedState: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val moreIconRotationAngle by animateFloatAsState(
        targetValue = if (expandedState) 0f else 180f,
        animationSpec = tween(durationMillis = 250)
    )

    TextButton(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.surface)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("SHOW SUPPORTING DOCUMENTS")
            Icon(
                painter = painterResource(id = R.drawable.ic_chevron_up),
                contentDescription = "Expand",
                modifier = Modifier.rotate(moreIconRotationAngle)
            )
        }
    }
}

@Preview
@Composable
fun SupportDocGridPreview() {
    val docs = listOf("image1", "image2", "image2", "image1")
    SupportDocGrid(docs)
}

@Composable fun SupportDocGrid(docs: List<String>, modifier: Modifier = Modifier) {
    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        mainAxisSpacing = 8.dp,
        crossAxisSpacing = 8.dp,
        crossAxisAlignment = FlowCrossAxisAlignment.Start,
        mainAxisAlignment = MainAxisAlignment.Start,
        mainAxisSize = SizeMode.Wrap
    ) {
        docs.forEach {
            Image(
                painter = painterResource(id = R.drawable.selfi),
                contentDescription = "selfi",
                alignment = Alignment.CenterStart,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .heightIn(120.dp, 240.dp)
                    .wrapContentWidth()
            )
        }
    }
    /*Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .wrapContentWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.doc_side),
            contentDescription = "Doc1",
            alignment = Alignment.CenterStart,
            modifier = Modifier
                .heightIn(120.dp)
                .aspectRatio(1.66f)
        )
        Image(
            painter = painterResource(id = R.drawable.selfi),
            contentDescription = "selfi",
            alignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.3f)
        )
    }*/
}


@Composable
private fun HorizontalBorderLine(modifier: Modifier = Modifier.fillMaxWidth()) {
    Surface(modifier.height(1.dp), color = Color.White.copy(0.5f)) {}
}

@Composable fun BackBtn(modifier: Modifier, onClick: () -> Unit) {
    Surface(
        modifier = modifier
            .clickable(role = Role.Button, onClick = onClick)
            .background(Color.White.copy(alpha = 0.4f))
            .clip(RoundedCornerShape(4.dp))
    ) {}
}

@Composable
fun ColumnLabelValueGroup(label: String, value: String) {
    Column {
        Text(label.uppercase(), style = MaterialTheme.typography.body1)
        Text(value.uppercase(), style = MaterialTheme.typography.body2)
    }
}

