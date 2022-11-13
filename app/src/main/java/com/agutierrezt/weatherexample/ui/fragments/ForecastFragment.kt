package com.agutierrezt.weatherexample.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager

import com.agutierrezt.weatherexample.data.viewmodels.WeatherViewModel
import com.agutierrezt.weatherexample.databinding.FragmentForecastBinding
import com.agutierrezt.weatherexample.ui.adapters.WeatherAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForecastFragment : Fragment() {

    private var _binding: FragmentForecastBinding? = null
    private val binding: FragmentForecastBinding get() = _binding!!
    private val weatherViewModel: WeatherViewModel by viewModel()
    private lateinit var weatherAdapter: WeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        weatherViewModel.forecast( 4.624335, -74.063644)
        weatherAdapter = WeatherAdapter(null, "")
        binding.forecastRecycler.apply {
            adapter = weatherAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
        observeViewModels()
    }

    private fun observeViewModels() {
        weatherViewModel.forecast.observe(viewLifecycleOwner, Observer {
            if(it.currentWeather != null) {
                binding.forecastWeather.text = "${it.currentWeather.temperature}${it.unit.unit}"
                binding.forecastTime.text = it.currentWeather.time
            }
            weatherAdapter.unit = it.unit.unit
            weatherAdapter.list = it.hourly
            weatherAdapter.notifyDataSetChanged()
        })



    }

}