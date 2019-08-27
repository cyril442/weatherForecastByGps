package androidviakotlin.blog.weatherforecastbygps.Utils.Parsers

import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class parseDatasWeatherNextFiveDays() {

    val KELVIN_TO_CELCIUS: Double = 273.15
    val YEAR_MONTH_DAY = 10
    val YEAR_MONTH = 7
    val FIRST_FOUR = 4
    val LAST_TWO = 2
    val FIRST_ELEMENT_OF_THE_INDEX = 0
    val SECOND_ELEMENT_OF_THE_INDEX = 1
    val DUMB_PICTURE_WHEN_NO_PIC_TO_DOWNLOAD =
        "https://i5.photobucket.com/albums/y152/courtney210/wave-bashful_zps5ab77563.jpg"

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
//         OLD WAY   val cityName = jo.getString("name")
            // OK looks good
//            val cityNameObject = jo.getJSONObject("city")
//            val cityName = cityNameObject.getString("name")
//            Log.i("banga", "cityName : $cityName")
//
//            getCityName(jo)



            // get CountryName
            var country = getCountryName(jo)
            Log.i("banga1", "country : $country")
            // OK keep this
//            val countryName = jo.getJSONObject("city")
//            val country = countryName.getString("country")
//            Log.i("banga", "country  : $country")


//            // get Temperature
            var temperature = getTemperature(jo)
            Log.i("banga1", "temperature : $temperature")




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

                val timestamp = joTimestamp.getString("dt_txt")
                Log.i("banga1", "timestamp : $timestamp")


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
                        var icon_weather = "04d"
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




//    fun whatIsTheDateToPrint(updated_date : String) : String {
//
//        //***--- FORMATTING THE DATE ---***//
//        var date10char = updated_date.take(YEAR_MONTH_DAY)
//        var date7char = updated_date.take(YEAR_MONTH)
//        var dateYear = date10char.take(FIRST_FOUR)
//        var dateMonth = date7char.takeLast(LAST_TWO)
//        var dateDay = date10char.takeLast(LAST_TWO)
//        var dateToPrint = "$dateDay/$dateMonth/$dateYear"
//        //***--------------------------------***//
//
//        return dateToPrint
//    }
//
//    fun valueOfTheSubsectionReadyToPrint(subsection: String): String {
//
//        //***--- PREPARATION OF THE SUBSECTION TO PRINT with a " > " before the texte to print---***//
//        var subsectionReadyToPrint: String
//        when (subsection) {
//            "" -> subsectionReadyToPrint = subsection
//            else -> subsectionReadyToPrint = " > $subsection"
//        }
//
//        return subsectionReadyToPrint
//    }

    fun getCityName(jo : JSONObject) : String{
        val cityNameObject = jo.getJSONObject("city")
        val cityName = cityNameObject.getString("name")
        Log.i("banga", "cityName : $cityName")
        return cityName
    }

    fun getCountryName(jo : JSONObject) : String{
        val countryName = jo.getJSONObject("city")
        val country = countryName.getString("country")
        Log.i("banga", "country  : $country")
        return country

    }

    fun getTemperature(jo: JSONObject) : String {
        var temp = "14"
        val jaTemp = jo.getJSONArray("list")
       Log.i("banga2", "jaTemp : $jaTemp")

        for (i in 0 until jaTemp.length() ) {
            val temperatureMain = jaTemp.getJSONObject(i)
            Log.i("banga2", "temperatureMain : $temperatureMain")

            val temperatureObj = temperatureMain.getJSONObject("main")
            Log.i("banga2", "temperatureObj : $temperatureObj")

            val temperature = temperatureObj.getString("temp")
            Log.i("banga2", "temperature : $temperature")

           temp = temperature
    }

        return temp



    }


}
