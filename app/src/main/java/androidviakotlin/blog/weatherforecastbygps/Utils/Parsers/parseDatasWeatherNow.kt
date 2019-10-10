package androidviakotlin.blog.weatherforecastbygps.Utils.Parsers

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import org.json.JSONException
import org.json.JSONObject

class parseDatasWeatherNow(private var c: Context, jsonDataPreview: String) : AsyncTask<String, Void,  MutableList<MutableList<String>>>() {

   var ping = jsonDataPreview
    private lateinit var pd: ProgressDialog

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

    // SHOW DIALOG WHILE DOWNLOADING DATAS
    override fun onPreExecute() {
        super.onPreExecute()

        pd = ProgressDialog(c)
        pd.setTitle("Parsing Datas For Now")
        pd.setMessage("Downloading... Please wait...")
        pd.show()
    }

    override fun doInBackground(vararg params: String?):  MutableList<MutableList<String>> {


        var datas = parseDatasFromApi(ping)

        return datas
    }

    override fun onPostExecute(result: MutableList<MutableList<String>>?) {
        super.onPostExecute(datas)

        pd.dismiss()

    }


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

            // Arrondir la Temperature


            var temperatureArrondie = temperature.toDouble()
            var tempArrondie = temperatureArrondie.toInt()
            var tempArrondieALadecimale = tempArrondie.toString()
            Log.i("banga2", "temperatureALaDecimale : $tempArrondieALadecimale")


            Log.i("bingo", "temperatureCelciusString : $temperature")

            // Get Wind
            val windObject = jo.getJSONObject("wind")
            val speed = windObject.getString("speed")

           // Arrondir wind speed
            var speedArrondi = speed.toDouble()
            var spedArrondir = speedArrondi.toInt()
            var speedArrondiALaDecimale = spedArrondir.toString()

            Log.i("bingo", "wind : $speed")


            val jaWeatherNow = jo.getJSONArray("weather")

            for (i in 0 until jaWeatherNow.length()) {
                val job = jaWeatherNow.getJSONObject(i)

                val icon_weather = job.getString("icon")
                Log.i("bingo", "icon : $icon_weather")


            val data =
                mutableListOf<String>(cityName, country, tempArrondieALadecimale, speedArrondiALaDecimale, icon_weather)
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
