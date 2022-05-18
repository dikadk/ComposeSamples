package com.example.samplecomposetest.ui.screens.driverlicense.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.samplecomposetest.R
import com.example.samplecomposetest.databinding.FragmentDriverLicenseDetailsBinding
import com.example.samplecomposetest.ui.screens.driverlicense.DriverLicenseDetailsState
import com.example.samplecomposetest.ui.screens.driverlicense.DriverLicenseViewModel
import com.example.samplecomposetest.ui.screens.driverlicense.UiState
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.example.samplecomposetest.ui.screens.driverlicense.util.BindingFragment

class DriverLicenseDetailsFragment : BindingFragment<FragmentDriverLicenseDetailsBinding>(){

    private lateinit var adapter: DriverLicenseDetailsFieldsAdapter
    private lateinit var docsAdapter: DriverLicenseDetailsDocsAdapter

    private val viewModel = DriverLicenseViewModel()

    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDriverLicenseDetailsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycleView()

        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Loading -> {}
                UiState.ContentInProgress -> {}
                is UiState.Error -> {}
                is UiState.Rejected -> {}
                is UiState.Success -> {
                    showContent(it.state)
                }
            }
        }
        binding.backBtn.setOnClickListener {

        }
    }

    private fun setupRecycleView() {
        adapter = DriverLicenseDetailsFieldsAdapter(
            requireContext(),
        )
        binding.rvAriesCredentialDetails.adapter = adapter

        docsAdapter = DriverLicenseDetailsDocsAdapter(
            requireContext()
        )
        binding.rvDocs.adapter = docsAdapter

        val layoutManager = FlexboxLayoutManager(requireContext())
        layoutManager.justifyContent = JustifyContent.FLEX_START
        binding.rvDocs.layoutManager = layoutManager
    }

    private fun showContent(state: DriverLicenseDetailsState) {
        //hideLoading()

        binding.fullName.text = state.fullName
        binding.bigLabel.text = state.bigLabel
        binding.smallLabel.text = state.smallLabel

        if (state.bigLabel.isBlank()) {
            binding.stateBorderBottom.isGone = true
            binding.stateBorderTop.isGone = true
            binding.bigLabel.isGone = true
        }

        if(state.files.isNotEmpty())
        {
            binding.toggleSupportDocsBtn.isVisible = true
            binding.toggleSupportDocsBtn.setOnClickListener {
                val shouldBeVisible = !binding.rvDocs.isGone
                binding.rvDocs.isGone = shouldBeVisible
                binding.toggleSupportDocsBtn.setIconResource(
                    if(shouldBeVisible)
                        R.drawable.ic_chevron_down
                    else
                        R.drawable.ic_chevron_up
                )
            }
        }

        if (state.smallLabel.isBlank())
            binding.smallLabel.isGone = true

        binding.profilePicture.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.avatar))
        binding.categoryIc.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.car))
        binding.category.text = state.catName
        binding.moreMenu.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_meatball_hor))

        adapter.setData(state.fields)
        docsAdapter.setData(state.files)

        binding.verifiedBy.text = resources.getString(R.string.verifications_card_attested_by, state.verifiedBy)
        binding.dateOfIssueCred.text = state.verificationDate
        binding.issuedBy.text = resources.getString(R.string.verifications_card_issued_by, state.issuesBy)
    }

    companion object {
        fun newInstance() = DriverLicenseDetailsFragment()
    }
}
