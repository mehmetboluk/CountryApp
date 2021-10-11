package com.example.countryapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.countryapp.R
import com.example.countryapp.databinding.FragmentCountryBinding
import com.example.countryapp.util.downloadFromUrl
import com.example.countryapp.util.placeHolderProgressBar
import com.example.countryapp.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.fragment_country.*


class CountryFragment : Fragment() {

    private lateinit var viewModel : CountryViewModel
    private var countryUuid = 0
    private lateinit var dataBinding : FragmentCountryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_country, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid
        }

        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)

        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {

                dataBinding.selectedCountry = country

            /*
                tvCountryNameAtCountryFragment.text = it.countryName
                tvCountryCapitalAtCountryFragment.text = it.capital
                tvCountryCurrencyAtCountryFragment.text = it.currency
                tvCountryRegionAtCountryFragment.text = it.region
                tvCountryLangAtCountryFragment.text = it.language
                context?.let {
                    ivCountryFlagAtCountryFragment.downloadFromUrl(country.imageURL, placeHolderProgressBar(it))
                }

                 */
            }
        })
    }


}