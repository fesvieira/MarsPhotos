package com.example.android.marsphotos.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.android.marsphotos.databinding.FragmentOverviewBinding


class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by activityViewModels()
    private lateinit var binding: FragmentOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOverviewBinding.inflate(inflater, container, false)

        // Calls retrofit to get the photos and organize them in a List<MarsPhoto>
        viewModel.getMarsPhotos()

        binding.photosRecyclerView.adapter = PhotoGridAdapter()

        // To pass the photos to adapter it is necessary to observe the list lifecycle
        viewModel.photos.observe (viewLifecycleOwner, {
            val adapter = binding.photosRecyclerView.adapter as PhotoGridAdapter
            adapter.submitList(viewModel.photos.value)
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.status.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), viewModel.status.value, Toast.LENGTH_LONG)
                .show()
        })


    }
}
