package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeListViewModel
//import com.udacity.shoestore.models.ShoeViewModel
import timber.log.Timber

class ShoeDetailFragment : Fragment() {

    private lateinit var viewModel: ShoeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail, container, false
        )
        binding.cancelButton.setOnClickListener { v: View ->
            v.findNavController().popBackStack()
        }

        viewModel = ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)

        binding.shoeViewModel = viewModel
        binding.setLifecycleOwner(this)

        binding.saveButton.setOnClickListener { v: View ->
            val currentShoe: Shoe = viewModel.shoe.value ?: Shoe("", 0.0, "", "", mutableListOf())
            val action =
                ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment()

            viewModel.addShoe(currentShoe)
            v.findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onDestroyView() {
        viewModel.resetShoe()
        super.onDestroyView()
    }
}