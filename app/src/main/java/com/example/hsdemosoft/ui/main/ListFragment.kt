package com.example.hsdemosoft.ui.main

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.hsdemosoft.R
import com.example.hsdemosoft.databinding.FragmentListBinding
import com.example.hsdemosoft.ui.MainActivity
import com.example.hsdemosoft.ui.details.DetailsFragment
import com.example.hsdemosoft.utils.Utilities


class ListFragment : Fragment() {

    lateinit var binding: FragmentListBinding
    private lateinit var viewModel: ListFragmentViewModel
    private var countryAdapter: ListFragmentAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        viewModel = ViewModelProviders.of(this).get(ListFragmentViewModel::class.java)


        if(context?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT){
            (context as MainActivity).binding.listFragment.visibility = View.VISIBLE
            (context as MainActivity).binding.detailsFragment.visibility = View.GONE
        } else {
            (context as MainActivity).binding.listFragment.visibility = View.VISIBLE
            (context as MainActivity).binding.detailsFragment.visibility = View.VISIBLE
        }

        if(Utilities.chechNetwork(context as MainActivity)){
            viewModel.getAllCountryData()
        } else{
            viewModel.getCachedData()
        }

        binding.refreshButton.setOnClickListener{
            if (Utilities.chechNetwork(context as MainActivity)){
                viewModel.getAllCountryData()
            } else{
                viewModel.getCachedData()
            }
        }




        countryAdapter = ListFragmentAdapter {
            val param = HashMap<String, String>()
            param["name"] = if(it.name.isNullOrEmpty()) "" else it.native
            param["native"] = if(it.native.isNullOrEmpty()) "" else it.native
            param["currency"] = if(it.currency.isNullOrEmpty()) "" else it.currency
            param["capital"] = if(it.capital.isNullOrEmpty()) "" else it.capital
            param["emoji"] = if(it.emoji.isNullOrEmpty()) "" else it.emoji
            param["languagesname"] = if(it.languages.isNullOrEmpty()) "" else it.languages[0].name
            param["languagescode"] = if(it.languages.isNullOrEmpty()) "" else it.languages[0].code

            (context as MainActivity).changeFragment(DetailsFragment(), param)
        }

        binding.countryRecycler.adapter = countryAdapter
        binding.countryRecycler.layoutManager =
            StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel._countryResponse.observe(viewLifecycleOwner
        ) {
            if (it.isNullOrEmpty()){
                showBanner(false)
            } else {
                showBanner(true)
                countryAdapter?.emitData(it)
            }
        }
    }


    private fun showBanner(status: Boolean){
        if(!status){
            Toast.makeText(context, "NetworkError and cached data not found", Toast.LENGTH_SHORT).show()
            binding.sadImage.visibility = View.VISIBLE
            binding.refreshButton.visibility = View.VISIBLE
            binding.countryRecycler.visibility = View.GONE
        } else {
            binding.sadImage.visibility = View.GONE
            binding.refreshButton.visibility = View.GONE
            binding.countryRecycler.visibility = View.VISIBLE
        }
    }

}