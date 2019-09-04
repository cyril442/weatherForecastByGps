package androidviakotlin.blog.weatherforecastbygps

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidviakotlin.blog.weatherforecastbygps.R
import com.squareup.picasso.Picasso


class ItemWeatherAdapter(val datas: List<MutableList<String>>) : androidx.recyclerview.widget.RecyclerView.Adapter<ItemWeatherAdapter.ViewHolder>() {


    class ViewHolder(itemView: View, var urlArticle : String = "") : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {


        val icon_weather = itemView.findViewById<ImageView>(R.id.icon_weather)
        val cityName = itemView.findViewById<TextView>(R.id.cityName)
        val country = itemView.findViewById<TextView>(R.id.country)
        val temperature = itemView.findViewById<TextView>(R.id.temperature)
        val speed = itemView.findViewById<TextView>(R.id.speed)

    }


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewItem = inflater.inflate(R.layout.itemweather_now, parent, false)


        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val iconCode = datas[position][4]
        var pictureToDownload =  "http://openweathermap.org/img/wn/$iconCode@2x.png"

        val data = datas[position]

        when (iconCode) {
            //Sunny
            "01d" -> holder.icon_weather.setImageResource(R.drawable.sunny_96)
            // Sun Night
            "01n" -> holder.icon_weather.setImageResource(R.drawable.sun_night)
            // Partially cloudy
            "02n", "02d", "03n", "03d"  -> holder.icon_weather.setImageResource(R.drawable.partly_cloudly)
            // Mostly Cloudly
            "04d","04n" -> holder.icon_weather.setImageResource(R.drawable.mostly_cloudy)
            // Fog
            "50d" -> holder.icon_weather.setImageResource(R.drawable.fog)
            //Snow
            "13d" -> holder.icon_weather.setImageResource(R.drawable.snow)
            // Moderate Rain
            "10d" -> holder.icon_weather.setImageResource(R.drawable.moderate_rain)
            // Rain
            "09d" -> holder.icon_weather.setImageResource(R.drawable.rainy)
            // Storm
            "11d" -> holder.icon_weather.setImageResource(R.drawable.storm)

            else -> holder.icon_weather.setImageResource(R.drawable.ic_launcher_background)
            //downLoadPicassoIcon(pictureToDownload)

        }



        holder?.cityName?.text = datas[position][0]
        holder?.country?.text = datas[position][1]
        holder?.temperature?.text = datas[position][2]
        holder?.speed?.text = datas[position][3]

       // holder?.urlArticle = datas[position][5]

    }

    override fun getItemCount(): Int {
        return datas.size

    }

}
