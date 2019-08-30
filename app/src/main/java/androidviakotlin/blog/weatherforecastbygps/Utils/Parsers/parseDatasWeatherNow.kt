package androidviakotlin.blog.weatherforecastbygps.Utils.Parsers

import android.util.Log
import org.json.JSONException
import org.json.JSONObject

class parseDatasWeatherNow() {


    var datas = mutableListOf(
        mutableListOf<String>(
            "un",
            "deux",
            "trois",
            "quatre",
            "cinq",
            "six",
            "sept",
            "huit",
            "neuf",
            "dix",
            "onze",
            "douze",
            "treize",
            "quatorze",
            "quinze"
        )
    )


    fun parseDatasFromApi(jsonDataPreview: String): MutableList<MutableList<String>> {


        var json = jsonDataPreview


        try {

            var jo: JSONObject
            datas.clear()

            jo = JSONObject(json)
            Log.i("bingo", "jo : $jo")

            // get CityName
            val cityName = jo.getString("name")
            Log.i("bingo", "cityName : $cityName")


            // get CountryName
            val countryName = jo.getJSONObject("sys")
            val country = countryName.getString("country")
            Log.i("bingo", "country  : $country")


            // get Temperature
            val temperatureObject = jo.getJSONObject("main")
           val temperature = temperatureObject.getString("temp")

            Log.i("bingo", "temperatureCelciusString : $temperature")

            // Get Wind
            val windObject = jo.getJSONObject("wind")
            val speed = windObject.getString("speed")
            Log.i("bingo", "wind : $speed")


            val jaWeatherNow = jo.getJSONArray("weather")

            for (i in 0 until jaWeatherNow.length()) {
                val job = jaWeatherNow.getJSONObject(i)

                val icon_weather = job.getString("icon")
                Log.i("bingo", "icon : $icon_weather")


            val data =
                mutableListOf<String>(cityName, country, temperature, speed, icon_weather)
            datas.add(data)

        }

            Log.i("bingo", "returned datas : $datas")
            return datas


        } catch (e: JSONException) {
            e.printStackTrace()

        }
        Log.i("bingo", "returned CATCH datas : $datas")
        return datas
    }

}
