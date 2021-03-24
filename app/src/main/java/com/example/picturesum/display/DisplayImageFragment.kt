package com.example.picturesum.display

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.*
import com.example.picturesum.databinding.DisplayImageFragmentBinding

class DisplayImageFragment : Fragment() {

    private val viewModel: DisplayImageViewModel by lazy {
        ViewModelProvider(this).get(DisplayImageViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding=DisplayImageFragmentBinding.inflate(inflater)
        binding.photosGrid.adapter = PhotoGridAdapter()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

}