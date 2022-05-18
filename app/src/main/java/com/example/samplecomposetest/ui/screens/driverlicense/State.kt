package com.example.samplecomposetest.ui.screens.driverlicense

import java.time.ZonedDateTime


fun getDlPreviewState(): DriverLicenseDetailsState {
    val fields = listOf(
        CredValue("BIRTH DATA", "2023"),
        CredValue("Date of expiration", "2023"),
        CredValue("BIRTH DATA", "2023"),
        CredValue("BIRTH DATA", "2023"),
    )

    val docs = listOf(UiCredentialAttributeData.File(
        fileDescription = "JPEG Image",
        encryptedFileUrl = "https://encrypted.uc.globalid.construction/users//dc29d2e0-679f-400d-9a98-666866a1143c.jpeg",
        mimeType = "image/jpeg",
        decryptionKey = "847ee1994739e3fb1c118e444fe1f893720d14207c7f004445b6b1e6c6edaeeb",
        fileName = "5e85bf3b-bc91-4e3a-b542-c8d75b6698ab-photo_file_front.jpeg"
    )
    )
    return DriverLicenseDetailsState(
        "Driver License",
        fullName = "Mr\nMIddlename\nCardholder",
        "Ukraine",
        "",
        imageUrl = null,
        avatarUuid = "",
        fields = fields,
        files = docs,
        "February 25, 2021",
        "GlobaliD Platform",
        "GlobalID Platform"
    )
}

data class CredValue(val label: String, val value: String)

data class DriverLicenseDetailsState(
    val catName: String,
    val fullName: String,
    val bigLabel: String,
    val smallLabel: String,
    val imageUrl: String?,
    val avatarUuid: String,
    val fields: List<CredValue>,
    val files: List<UiCredentialAttributeData.File>,
    val verificationDate: String,
    val verifiedBy: String,
    val issuesBy: String
)

sealed class UiState {
    object ContentInProgress : UiState()
    object Loading : UiState()

    data class Success(val state: DriverLicenseDetailsState, val isOffer: Boolean) : UiState()
    data class Rejected(
        val state: DriverLicenseDetailsState,
        val updatedAt: ZonedDateTime?,
        val createdAt: ZonedDateTime?
    ) : UiState()
    data class Error(
        val updatedAt: ZonedDateTime?,
        val createdAt: ZonedDateTime?,
        val credentialIssuer: String?
    ) : UiState()
}

sealed class UiCredentialAttributeData {

    data class Value(
        val value: String,
    ) : UiCredentialAttributeData()

    data class AddressMatchValue(
        val value: Boolean,
    ) : UiCredentialAttributeData()

    data class File(
        val fileDescription: String,
        val encryptedFileUrl: String,
        val mimeType: String,
        val decryptionKey: String,
        val fileName: String,
    ) : UiCredentialAttributeData()
}
