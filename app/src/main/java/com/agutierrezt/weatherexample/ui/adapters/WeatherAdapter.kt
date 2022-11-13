package com.agutierrezt.weatherexample.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.agutierrezt.weatherexample.data.models.ForecastHourly
import com.agutierrezt.weatherexample.databinding.ItemWeatherBinding

class WeatherAdapter(var list: ForecastHourly?, var unit: String): RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {
    class ViewHolder(var item: ItemWeatherBinding): RecyclerView.ViewHolder(item.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val time = list!!.time[position]
        val temperature = list!!.temperature[position]
        holder.item.forecastWeather.text = "${temperature}${unit}"
        holder.item.forecastItemTime.text = time
    }

    override fun getItemCount(): Int {
        if(list == null) return 0
        return list!!.time.size
    }
}