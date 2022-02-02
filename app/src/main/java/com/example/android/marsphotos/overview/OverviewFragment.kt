/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.marsphotos.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import coil.load
import com.example.android.marsphotos.databinding.FragmentOverviewBinding

/** This fragment shows the the status of the Mars photos web services transaction. */
class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by activityViewModels()
    private lateinit var binding: FragmentOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOverviewBinding.inflate(inflater, container, false)
        viewModel.getMarsPhotos()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.photos.observe(viewLifecycleOwner, {
            val photos = viewModel.photos
            val imgUrl = photos.value?.imgSrcUrl

            imgUrl?.let {
                val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
                binding.imageMars.load(imgUri)
            }
        })

        viewModel.status.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), viewModel.status.value, Toast.LENGTH_LONG)
                .show()
        })


    }
}
