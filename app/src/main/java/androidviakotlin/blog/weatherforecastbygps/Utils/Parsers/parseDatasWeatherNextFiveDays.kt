package androidviakotlin.blog.weatherforecastbygps.Utils.Parsers


import android.app.Activity
import android.content.res.Resources
import android.support.v4.content.res.TypedArrayUtils.getString
import android.support.v4.content.res.TypedArrayUtils.getText
import android.util.Log
import androidviakotlin.blog.weatherforecastbygps.R
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList

data class Months(

    var aout: String = (R.string.Aout).toString()

)

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

    lateinit var endTemperature: String

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



//            // get Temperature
            var temperature = getTemperature(jo).toString()
//         //   var temperature = getTheFeckingTemperature()
            Log.i("banga1", "temperature : $temperature")


            /// TEST TEMPERATURE
//
//            endTemperature = "test"
//            var endTemp = "testEndTemp"
//            var temp = ArrayList<String>()
//
//            val jaTemp = jo.getJSONArray("list")
//            Log.i("banga2", "jaTemp : $jaTemp")
//
//            for (i in 0 until jaTemp.length()) {
//                val temperatureMain = jaTemp.getJSONObject(i)
//                Log.i("banga2", "temperatureMain : $temperatureMain")
//
//                val temperatureObj = temperatureMain.getJSONObject("main")
//                Log.i("banga2", "temperatureObj : $temperatureObj")
//
//                val temperature = temperatureObj.getString("temp")
//                Log.i("banga2", "temperature : $temperature")
//
//                temp.add(temperature)
//
//            }
//
//        Log.i("banga2", "TEMP: $temp")
//        //  var arr = temp.size
//        var i = 0
//        var sizeJaTemp = jaTemp.length()
//        Log.i("banga2", "Value de sizeJaTemp: $sizeJaTemp")
//
//
//            while (i !== sizeJaTemp) {
//                i++
//                Log.i("banga2", "Value I: $i")
//                endTemperature = temp.get(i - 1)
//                Log.i("banga2", "Value Premier return: $endTemperature")
//
//                           }
//            var temperature = endTemperature

//            val temperatureObject = jo.getJSONObject("main")
//           val temperatureString = temperatureObject.getString("temp")
//            val temperatureKelvin = temperatureString.toDouble()
//            val temperatureCelciusDouble = (temperatureKelvin-KELVIN_TO_CELCIUS).toDouble()
//          //  val temperatureCelciusRound = String.format("%.2f", temperatureCelciusDouble).toDouble()
//            val temperatureCelciusString = temperatureCelciusDouble.toString()
//
//            Log.i("bingo", "temperatureCelciusString : $temperatureCelciusString")

//            // Get Wind
//            val windObject = jo.getJSONObject("wind")
//            val speed = windObject.getString("speed")
//            Log.i("banga", "wind : $speed")


//            // Get Icon Weather
//            val jaWeatherNow = jo.getJSONArray("weather")
//
//            for (i in 0 until jaWeatherNow.length()) {
//                val job = jaWeatherNow.getJSONObject(i)
//
//                val icon_weather = job.getString("icon")
//                Log.i("bingo", "icon : $icon_weather")


//                val section = jo.getString("section")
//                val subsection = jo.getString("subsection")
//                val updated_date = jo.getString("updated_date")
//                val urlArticle = jo.getString("url")


            // Get TimeStamp
            val jaTimestamp = jo.getJSONArray("list")
            for (i in 0 until jaTimestamp.length()) {
                val joTimestamp = jaTimestamp.getJSONObject(i)

                val timestampNotFormated = joTimestamp.getString("dt_txt")
                Log.i("banga1", "timestamp : $timestampNotFormated")

                val timestamp = getTheCorrectDateFormat(timestampNotFormated)


                // Get Temperature and Humidity
//
//                val jaTempHum = jo.getJSONArray("list")
//                for (i in 0 until jaTempHum.length() ) {
//                    val joTempHum = jaTempHum.getJSONObject(i)
//
//                    val jobTempHum = joTempHum.getJSONObject("main")
//
//                   // val temperature = jobTempHum.getString("temp")
//                    val humidity = jobTempHum.getString("humidity")
//
//
//                    Log.i("banga", "humidity : $humidity")
//
//
//                    Log.i("banga", "temprature : $temperature")

                // Get Wind

//
//                    val jaWind = jo.getJSONArray("list")
//                    for (i in 0 until jaWind.length()) {
//
//                        val joWind = jaWind.getJSONObject(i)
//                        val jobWind = joWind.getJSONObject("wind")
//
//                        var speed = jobWind.getString("speed")
//
//                        Log.i("banga", "Wind Speed : $speed")


                // get Icon Weather

//                        val jaIcon = jo.getJSONArray("list")
//                        for (i in 0 until jaIcon.length()-1) {
//                            val jaWeather = jaIcon.getJSONObject(i)
//
////                                    Log.i("banga", "JaWeather : $jaWeather")
////                                    Log.i("banga", "JaWeather : $jaWeather")
//
//                            var jabIcon = jaWeather.getJSONArray("weather")
//
//                            for (i in 0 until jabIcon.length()) {
//                                val joWeather = jabIcon.getJSONObject(i)
//                                val icon_weather = joWeather.getString("icon")
//
//                                Log.i("banga", "Icon Weather : $icon_weather")

//
//                // See the function Below to know know i the susbestion if prepared for printing
//            val subsectionReadyToPrint = valueOfTheSubsectionReadyToPrint(subsection)
//
//
//
//            // See the function bellow transforming the string
//            val dateToPrint = whatIsTheDateToPrint(updated_date)
//
//            val jam = jo.getJSONArray("multimedia")
//
//            var urlToPrint: String
//                if (jam.length() == 0) {
//                    urlToPrint = "https://i5.photobucket.com/albums/y152/courtney210/wave-bashful_zps5ab77563.jpg"
//                } else {
//
//                    var jom = jam[FIRST_ELEMENT_OF_THE_INDEX] as JSONObject
//                    var url = jom.getString("url")
//
//                    //***--- GET AN IMAGE EVEN WHEN MULTIMEDIA IS EMPTY  ---**//
//
//                    when (url) {
//                        "" -> urlToPrint = "$DUMB_PICTURE_WHEN_NO_PIC_TO_DOWNLOAD"
//                        else -> urlToPrint = url
//                    }


                var humidity = "42"
                var icon_weather = "01n"
                var speed = "12"

                val data =
                    mutableListOf<String>(
                        cityName,
                        country,
                        temperature,
                        speed,
                        icon_weather,
                        timestamp,
                        humidity
                    )
                datas.add(data)

            }
            //  }

            //   }


            //              }
            //         }
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

        var monthToPrint = getTheCorrectMonthFormat(month)


        var hourLastEight = timestamp?.takeLast(EIGHT)
        var hours = hourLastEight?.take(FIVE)



        letimestamp = "le $day $monthToPrint a $hours"

        return letimestamp
    }

    fun getTheCorrectMonthFormat(month: String?): String {



        var monthTo: String = ""

        when (month) {
            "01" -> monthTo = "Janvier"
            "02" -> monthTo = "Fevrier"
            "03" -> monthTo = "Mars"
            "04" -> monthTo = "Avril"
            "05" -> monthTo = "Mai"
            "06" -> monthTo = "Juin"
            "07" -> monthTo = "Juillet"
            "08" -> monthTo = Months().aout
            "09" -> monthTo = "Septembre"
            "10" -> monthTo = "Octobre"
            "11" -> monthTo = "Novembre"
            "12" -> monthTo = "Décembre"

        }


        return monthTo
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

    fun getTemperature(jo: JSONObject) {

        endTemperature = "test"
        var endTemp = "testEndTemp"
        var temp = ArrayList<String>()

        val jaTemp = jo.getJSONArray("list")
        Log.i("banga2", "jaTemp : $jaTemp")

        for (i in 0 until jaTemp.length()) {
            val temperatureMain = jaTemp.getJSONObject(i)
            Log.i("banga2", "temperatureMain : $temperatureMain")

            val temperatureObj = temperatureMain.getJSONObject("main")
            Log.i("banga2", "temperatureObj : $temperatureObj")

            val temperature = temperatureObj.getString("temp")
            Log.i("banga2", "temperature : $temperature")

            temp.add(temperature)

        }
        Log.i("banga2", "TEMP: $temp")
        //  var arr = temp.size
//        var i = 0
//        var sizeJaTemp = jaTemp.length()
//        Log.i("banga2", "Value de sizeJaTemp: $sizeJaTemp")
//        // ArrayList to Array Conversion
//        for (i in 0 until arr) {
//
//            var jet = temp.get(i)
//
//            //  return jet
//
//        }
//            return jet
//        }


        for (temmp in temp) {

            endTemperature = temmp


//        while (i !== sizeJaTemp) {
//            i++
//            Log.i("banga2", "Value I: $i")
//            endTemperature = temp.get(i-1)
//            Log.i("banga2", "Value Premier return: $endTemperature")
//
//            return endTemperature
//            // getTheFeckingTemperature()
//        }
//        Log.i("banga2", "Value endTempBefore: $endTemp")
//        return endTemperature
//        Log.i("banga2", "Value endTempAfter: $endTemp")

            //   }
//
//    private fun getTheFeckingTemperature() : String{
//
//        var temperature = endTemperature
//        Log.i("banga2", "Value what is the Fecking Temperature: $temperature")
//        return temperature
//    }
            Log.i("banga2", "Value Second return: $endTemperature")


        }
    }

}



