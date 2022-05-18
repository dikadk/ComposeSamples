package com.example.samplecomposetest.ui.screens.driverlicense.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.samplecomposetest.databinding.DlCredentialItemBinding
import com.example.samplecomposetest.ui.screens.driverlicense.CredValue
import com.example.samplecomposetest.ui.screens.driverlicense.util.BaseBindingAdapter

class DriverLicenseDetailsFieldsAdapter(
    context: Context
) : BaseBindingAdapter<CredValue, DriverLicenseDetailsFieldsAdapter.DashboardVH>(context) {

        override fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): DashboardVH {
            val bindingItem = DlCredentialItemBinding.inflate(inflater, parent, false)
            return DashboardVH(bindingItem)
        }

    override fun doBind(holder: DashboardVH, item: CredValue, position: Int) = holder.bind(item)

        inner class DashboardVH(
            private val binding: DlCredentialItemBinding
        ) : BindingVH<DlCredentialItemBinding>(binding) {

            fun bind(model: CredValue) {
                binding.tvLabel.text = model.label
                binding.tvValue.text = model.value
            }
        }
    }
