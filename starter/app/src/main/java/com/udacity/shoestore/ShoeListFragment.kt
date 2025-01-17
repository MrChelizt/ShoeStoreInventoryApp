package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_shoe_list.*
import timber.log.Timber

class ShoeListFragment : Fragment() {

    private lateinit var viewModel: ShoeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val toolbar = (activity as MainActivity).toolbar
        toolbar.inflateMenu(R.menu.logout_menu)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.logout -> view?.findNavController()
                    ?.navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
            }
            true
        }

        val binding: FragmentShoeListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list, container, false
        )
        binding.addShoeButton.setOnClickListener { v: View ->
            v.findNavController()
                .navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

        viewModel = ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)

        binding.shoeListViewModel = viewModel
        viewModel.shoeList.observe(viewLifecycleOwner, Observer {
            for (item in it) {
                Timber.i(item.name)
                addShoeCard(item)
            }
        })

        return binding.root
    }

    private fun addShoeCard(s: Shoe) {
        val view: View = layoutInflater.inflate(R.layout.shoe_list_item, null)
        val image: ImageView = view.findViewById(R.id.shoeImage)
        val name: TextView = view.findViewById(R.id.shoeName)
        val size: TextView = view.findViewById(R.id.shoeSize)
        val company: TextView = view.findViewById(R.id.shoeCompany)
        val description: TextView = view.findViewById(R.id.shoeDescription)
        name.text = s.name
        size.text = s.size.toString()
        company.text = s.company
        description.text = s.description
        shoeContainer.addView(view)
    }

    override fun onDestroyView() {
        val toolbar = (activity as MainActivity).toolbar
        toolbar.menu.clear()
        super.onDestroyView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> view?.findNavController()
                ?.navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
        }
        return super.onOptionsItemSelected(item)
    }

}