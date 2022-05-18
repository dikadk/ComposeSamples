package com.example.samplecomposetest.ui.screens.driverlicense

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.samplecomposetest.R
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode

@Composable
fun DriverLicenseCardDetails(
    item: DriverLicenseDetailsState,
    onBackClicked: () -> Unit,
    onMoreBtnClicked: () -> Unit
) {
    val bgColor = Color(0xFF002D9C)
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
                        tint = contentColor,
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
                Icon(
                    painter = painterResource(id = R.drawable.ic_driver_license),
                    contentDescription = "category",
                    tint = bgColor,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                )
                Column(
                    modifier = Modifier
                        .padding(start = 24.dp)
                        .wrapContentHeight(Alignment.CenterVertically),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item.fullName,
                        style = MaterialTheme.typography.body1
                    )
                }
            }

            //get country or region
            if(item.bigLabel.isNotEmpty()){
                Column(modifier = Modifier.fillMaxWidth()) {
                    HorizontalBorderLine()
                    Text(item.bigLabel, style = MaterialTheme.typography.h1.copy(fontWeight = FontWeight.SemiBold))
                    HorizontalBorderLine()
                    Spacer(Modifier.height(8.dp))
                }
            }

            if(item.smallLabel.isNotEmpty()){
                Text(item.smallLabel, style = MaterialTheme.typography.h4)
            }


            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(top = if(item.smallLabel.isNotEmpty()) 40.dp else 8.dp)
            ) {
                for (d in item.fields)
                    ColumnLabelValueGroup(d.label, d.value)
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
            if (expandedState && item.files.isNotEmpty()) {
                SupportDocGrid(
                    item.files, Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                )
            }

            //Verification labels
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = if (expandedState) 25.dp else 0.dp, bottom = 8.dp)
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


@Preview(showSystemUi = true)
@Composable
fun DlDetailsPreview() {
    DriverLicenseCardDetails(getDlPreviewState(), {}, {})
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
    val docs = listOf(UiCredentialAttributeData.File(
        fileDescription = "JPEG Image",
        encryptedFileUrl = "https://encrypted.uc.globalid.construction/users//dc29d2e0-679f-400d-9a98-666866a1143c.jpeg",
        mimeType = "image/jpeg",
        decryptionKey = "847ee1994739e3fb1c118e444fe1f893720d14207c7f004445b6b1e6c6edaeeb",
        fileName = "5e85bf3b-bc91-4e3a-b542-c8d75b6698ab-photo_file_front.jpeg"
    )
    )
    SupportDocGrid(docs)
}

@Composable fun SupportDocGrid(
    docs: List<UiCredentialAttributeData.File>,
    modifier: Modifier = Modifier
) {
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
                painter = painterResource(id = R.drawable.ic_driver_license),
                contentDescription = "selfi",
                alignment = Alignment.CenterStart,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .heightIn(120.dp, 240.dp)
                    .wrapContentWidth()
            )
        }
    }
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


@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ContentInProgressWidget(){

}
