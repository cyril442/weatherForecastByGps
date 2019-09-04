package androidviakotlin.blog.weatherforecastbygps.Utils.Parsers


import android.util.Log
import androidviakotlin.blog.weatherforecastbygps.Fragments.NextThreeDaysFragment
import androidviakotlin.blog.weatherforecastbygps.Utils.Weather
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList


class parseDatasWeatherNextFiveDays() {


    val YEAR_MONTH_DAY = 10
    val YEAR_MONTH = 7
    val FIRST_FOUR = 4
    val LAST_TWO = 2
    val EIGHT = 8
    val FIVE = 5
    val FIRST_ELEMENT_OF_THE_INDEX = 0
    val SECOND_ELEMENT_OF_THE_INDEX = 1
    val DUMB_PICTURE_WHEN_NO_PIC_TO_DOWNLOAD =
        "https://i5.photobucket.com/albums/y152/courtney210/wave-bashful_zps5ab77563.jpg"

    var datas = mutableListOf(
        mutableListOf<Weather>(
            Weather()

        )
    )


    fun parseDatasFromApi(jsonDataPreview: String): MutableList<MutableList<Weather>> {


        var json = jsonDataPreview
        Log.i("banga1", "jsonDataPreview dans le parser : $json")

        try {

            var jo: JSONObject
            datas.clear()

            jo = JSONObject(json)
            Log.i("banga1", "jo : $jo")

            var jaList = jo.getJSONArray("list")
            Log.i("banga1", "jaLIST : $jaList")

            // get CityName
            var cityName = getCityName(jo)
            Log.i("banga1", "cityName : $cityName")


            // get CountryName
            var country = getCountryName(jo)
            Log.i("banga1", "country : $country")


            // Get TimeStamp and Temperature
            val jsonArrayList = jo.getJSONArray("list")
            for (i in 0 until jsonArrayList.length()) {
                val joTimestamp = jsonArrayList.getJSONObject(i)

                val timestampNotFormated = joTimestamp.getString("dt_txt")
                Log.i("banga8", "timestampNotFormated : $timestampNotFormated")

                val timestamp = getTheCorrectDateFormat(timestampNotFormated)

         //       val timestamp = joTimestamp.getString("dt_txt")

        //        val timestamp = Weather().setRealDateTime(jjtimestamp)


                // Temperature in JsonArrayList:
                var reformatTemp = getTemperature(jo)
                var temperature = reformatTemp.get(i)
                Log.i("banga3", "temperature : $temperature")

                //  Speed in JsonArrayList
                var reformatSpeed = getSpeed(jo)
                var speed = reformatSpeed.get(i)

                // Humidity in JsonArrayList
                var reformatHumidity = getHumidity(jo)
                var humidity = reformatHumidity.get(i)

                // Icon_Weather in JsonArrayList
                var reformatIcon = getIcon(jo)
                var icon_weather = reformatIcon.get(i)

                //From the Weather Model class
                var weather = Weather()
                weather.cityName = cityName
                weather.country = country
                weather.temperature = temperature
                weather.speed = speed
                weather.icon_weather = icon_weather
                weather.timestamp = timestamp
                weather.humidity = humidity


                val data =
                    mutableListOf<Weather>(
                        weather
                    )
                datas.add(data)

            }

            Log.i("banga1", "returned datas : $datas")
            return datas


        } catch (e: JSONException) {
            e.printStackTrace()

        }
        Log.i("banga1", "returned CATCH datas : $datas")
        return datas
    }


    fun getTheCorrectDateFormat(timestamp: String?): String {

        var letimestamp: String

        var dateTenChar = timestamp?.take(YEAR_MONTH_DAY)
        var day = dateTenChar?.takeLast(LAST_TWO)

        var monthLastFour = dateTenChar?.take(YEAR_MONTH)
        var month = monthLastFour?.takeLast(LAST_TWO)
        Log.i("banga8", "Month : $month")

        var monthToPrint = NextThreeDaysFragment().getTheCorrectMonthFormat(month)
        Log.i("banga8", "MonthToPrint : $monthToPrint")

        var hourLastEight = timestamp?.takeLast(EIGHT)
        var hours = hourLastEight?.take(FIVE)



        letimestamp = "le $day $monthToPrint a $hours"

        return letimestamp
    }




    fun getCityName(jo: JSONObject): String {
        val cityNameObject = jo.getJSONObject("city")
        val cityName = cityNameObject.getString("name")
        Log.i("banga", "cityName : $cityName")
        return cityName
    }

    fun getCountryName(jo: JSONObject): String {
        val countryName = jo.getJSONObject("city")
        val country = countryName.getString("country")
        Log.i("banga", "country  : $country")
        return country

    }

    fun getTemperature(jo: JSONObject): ArrayList<String> {


        var temp = ArrayList<String>()

        val jaTemp = jo.getJSONArray("list")
        Log.i("banga2", "jaTemp : $jaTemp")

        for (i in 0 until jaTemp.length()) {
            var temperatureMain = jaTemp.getJSONObject(i)
            Log.i("banga2", "temperatureMain : $temperatureMain")

            var temperatureObj = temperatureMain.getJSONObject("main")
            Log.i("banga2", "temperatureObj : $temperatureObj")

            var temperature = temperatureObj.getString("temp")
            Log.i("banga2", "temperature : $temperature")

            temp.add(temperature)
        }

        return temp

    }

    private fun getSpeed(jo: JSONObject): ArrayList<String> {

        var arraySpeed = ArrayList<String>()

        val jaSpeed = jo.getJSONArray("list")
        Log.i("banga2", "jaSpeed : $jaSpeed")

        for (i in 0 until jaSpeed.length()) {
            var speedWind = jaSpeed.getJSONObject(i)
            Log.i("banga2", "speedWind : $speedWind")

            var speedObj = speedWind.getJSONObject("wind")
            Log.i("banga2", "speedObj : $speedObj")

            var speed = speedObj.getString("speed")
            Log.i("banga2", "speed : $speed")

            arraySpeed.add(speed)
        }

        return arraySpeed

    }

    private fun getHumidity(jo: JSONObject): ArrayList<String> {

        var arrayHumidity = ArrayList<String>()

        val jaHumidity = jo.getJSONArray("list")
        Log.i("banga2", "jaHumidity : $jaHumidity")

        for (i in 0 until jaHumidity.length()) {
            var humidityGetObj = jaHumidity.getJSONObject(i)
            Log.i("banga2", "humidityGetObj : $humidityGetObj")

            var humidityObj = humidityGetObj.getJSONObject("main")
            Log.i("banga2", "humidityObj : $humidityObj")

            var humidity = humidityObj.getString("humidity")
            Log.i("banga2", "humidity : $humidity")

            arrayHumidity.add(humidity)
        }

        return arrayHumidity

    }

    fun getIcon(jo: JSONObject): ArrayList<String> {

        var arrayIcon = ArrayList<String>()

        val jaIconTop = jo.getJSONArray("list")

        for (i in 0 until jaIconTop.length()) {

            var jaIconBottom = jaIconTop.getJSONObject(i)

            var jaWeather = jaIconBottom.getJSONArray("weather")


            for (i in 0 until jaWeather.length()) {
                var iconGetObj = jaWeather.getJSONObject(i)
                Log.i("banga2", "humidityGetObj : $iconGetObj")

                var icon = iconGetObj.getString("icon")
                Log.i("banga2", "humidity : $icon")

                arrayIcon.add(icon)

            }
        }


        return arrayIcon
    }

}

