package com.example.samplecomposetest.ui.screens.driverlicense.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.samplecomposetest.R
import com.example.samplecomposetest.databinding.DocItemBinding
import com.example.samplecomposetest.ui.screens.driverlicense.UiCredentialAttributeData
import com.example.samplecomposetest.ui.screens.driverlicense.util.BaseBindingAdapter

class DriverLicenseDetailsDocsAdapter(
    val context: Context,
) : BaseBindingAdapter<UiCredentialAttributeData.File, DriverLicenseDetailsDocsAdapter.DocVH>(context) {

        override fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): DocVH {
            val bindingItem = DocItemBinding.inflate(inflater, parent, false)
            return DocVH(bindingItem, context)
        }

    override fun doBind(holder: DocVH, item: UiCredentialAttributeData.File, position: Int) = holder.bind(item)

        inner class DocVH(
            private val binding: DocItemBinding,
            private val context: Context
        ) : BindingVH<DocItemBinding>(binding) {

            fun bind(item : UiCredentialAttributeData.File) {
                binding.supportDoc.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.avatar))
            }
        }
    }
