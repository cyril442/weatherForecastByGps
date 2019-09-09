package androidviakotlin.blog.weatherforecastbygps

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidviakotlin.blog.weatherforecastbygps.Utils.Weather


class ItemWeatherNextThreeDaysAdapter(val datas: List<MutableList<Weather>>) : androidx.recyclerview.widget.RecyclerView.Adapter<ItemWeatherNextThreeDaysAdapter.ViewHolder>() {


    class ViewHolder(itemView: View, var urlArticle : String = "") : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {


        val icon_weather = itemView.findViewById<ImageView>(R.id.icon_weather)
        val cityName = itemView.findViewById<TextView>(R.id.cityName)
        val country = itemView.findViewById<TextView>(R.id.country)
        val temperature = itemView.findViewById<TextView>(R.id.temperature)
        val speed = itemView.findViewById<TextView>(R.id.speed)
        val timestamp = itemView.findViewById<TextView>(R.id.timestamp)
        val humidity = itemView.findViewById<TextView>(R.id.humidity)

    }


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewItem = inflater.inflate(R.layout.itemweather_nextthreedays, parent, false)


        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val iconCode = datas[position][0].icon_weather
        val pictureToDownload =  "http://openweathermap.org/img/wn/$iconCode@2x.png"

//        val data = datas[position]

        when (iconCode) {
            //Sunny
            "01d" -> holder.icon_weather.setImageResource(R.drawable.ic_icons8_summer)
            // Sun Night
            "01n" -> holder.icon_weather.setImageResource(R.drawable.ic_icons8_night)
            // Partially cloudy
            "02n", "02d", "03n", "03d"  -> holder.icon_weather.setImageResource(R.drawable.partly_cloudly)
            // Mostly Cloudly
            "04d","04n" -> holder.icon_weather.setImageResource(R.drawable.mostly_cloudy)
            // Fog
            "50d", "50n" -> holder.icon_weather.setImageResource(R.drawable.ic_icons8_fog)
            //Snow
            "13d", "13n" -> holder.icon_weather.setImageResource(R.drawable.ic_icons8_winter)
            // Moderate Rain
            "10d", "10n" -> holder.icon_weather.setImageResource(R.drawable.ic_icons8_moderate_rain)
            // Rain
            "09d", "09n" -> holder.icon_weather.setImageResource(R.drawable.ic_icons8_rainy_weather)
            // Storm
            "11d", "11n" -> holder.icon_weather.setImageResource(R.drawable.ic_icons8_storm)

            else -> holder.icon_weather.setImageResource(R.drawable.launcher_weather)
            //downLoadPicassoIcon(pictureToDownload)

        }

        holder?.cityName?.text = datas[position][0].cityName
        holder?.country?.text = datas[position][0].country
        holder?.temperature?.text = datas[position][0].temperature
        holder?.speed?.text = datas[position][0].speed
        holder?.timestamp?.text = datas[position][0].timestamp
        holder?.humidity?.text = datas[position][0].humidity


    }

    override fun getItemCount(): Int {
        return datas.size

    }

}
