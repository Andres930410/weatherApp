package com.agutierrezt.weatherexample.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.agutierrezt.weatherexample.R
import com.agutierrezt.weatherexample.data.viewmodels.WeatherViewModel
import com.agutierrezt.weatherexample.databinding.FragmentHistoricalBinding
import com.agutierrezt.weatherexample.ui.adapters.WeatherAdapter
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [HistoricalFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoricalFragment : Fragment() {

    private var _binding: FragmentHistoricalBinding? = null
    private val binding: FragmentHistoricalBinding get() = _binding!!
    private lateinit var dateRangePicker: MaterialDatePicker<Pair<Long,Long>>
    private var startDate: String = ""
    private var endDate: String = ""
    private val weatherViewModel: WeatherViewModel by viewModel()
    private lateinit var weatherAdapter: WeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHistoricalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val calendar = Calendar.getInstance()
        val upTo = calendar.timeInMillis
        calendar.add(Calendar.YEAR, -1)
        val startFrom = calendar.timeInMillis
        val constraints =CalendarConstraints.Builder()
            .setStart(startFrom)
            .setEnd(upTo)
            .build()
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        dateRangePicker =
            MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText(getString(R.string.date_picker_layout))
                .setCalendarConstraints(constraints)
                .build()
        dateRangePicker.addOnPositiveButtonClickListener {
            startDate = formatter.format(it.first)
            endDate = formatter.format(it.second)
            binding.historicalDatesLayout.text = "${startDate} ${endDate}"
            binding.historicalDateButton.isEnabled = true
        }
        binding.historicalDateButton.setOnClickListener {
            weatherViewModel.historical(4.624335, -74.063644, startDate, endDate)
        }
        binding.historicalDatesLayout.setOnClickListener {
            dateRangePicker.show(childFragmentManager, "")
        }
        weatherAdapter = WeatherAdapter(null, "")
        binding.historicalRecycler.apply {
            adapter = weatherAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
        observeViewModels()
    }

    private fun observeViewModels() {
        weatherViewModel.forecast.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            weatherAdapter.unit = it.unit.unit
            weatherAdapter.list = it.hourly
            weatherAdapter.notifyDataSetChanged()
        })
    }

}