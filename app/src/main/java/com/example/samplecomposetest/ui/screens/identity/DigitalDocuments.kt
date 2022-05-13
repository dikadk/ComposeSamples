package com.example.samplecomposetest.ui.screens.identity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.samplecomposetest.R


@Preview(showSystemUi = true)
@Composable
fun DigitalDocuments() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .background(Color(0xFFF7F7F7))
            .padding(horizontal = 16.dp)
            .wrapContentHeight()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) { Text("Digital documents", style = MaterialTheme.typography.h6) }
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

        MyCredentials()
    }
}

@Composable fun MyCredentials() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("My credentials", style = MaterialTheme.typography.h6)
        for (i in 0..4)
            Level2CredentialCard()
    }
}

@Composable fun Level2CredentialCard(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundCornersShape12)
            .background(color = Color.White)
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(Color.Red)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_gov_id),
                    contentDescription = "avatar",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Liveness check", style = MaterialTheme.typography.h6)
                Text("Biometric", style = MaterialTheme.typography.body1)
                Text("Data verified by Google", style = MaterialTheme.typography.subtitle1)
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
