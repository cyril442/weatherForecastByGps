package androidviakotlin.blog.weatherforecastbygps.Utils.AsyncTask

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import androidviakotlin.blog.weatherforecastbygps.Fragments.NextThreeDaysFragment
import androidviakotlin.blog.weatherforecastbygps.Fragments.NowFragment
import androidviakotlin.blog.weatherforecastbygps.LatitudeViewModel
import androidviakotlin.blog.weatherforecastbygps.MainActivity
import androidviakotlin.blog.weatherforecastbygps.Utils.Downloaders.JSONDownloaderMeteoNow
import androidviakotlin.blog.weatherforecastbygps.Utils.Parsers.parseDatasWeatherNextFiveDays
import androidviakotlin.blog.weatherforecastbygps.Utils.Parsers.parseDatasWeatherNow
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class DownloadParsePrintUiTask (private var activity : FragmentActivity?, jsonWeatherNow : String ) :
    AsyncTask<String, String, String>() {

    var laString = jsonWeatherNow
    private lateinit var pd: ProgressDialog



    override fun onPreExecute() {
        super.onPreExecute()

        pd = ProgressDialog(activity)
        pd.setTitle("Download Json")
        pd.setMessage("Downloading... Please wait...")
        pd.show()

        // Check POints GPS
        // Check Internet
        // Check Battery low

    }


    override fun doInBackground(vararg params: String?): String {

        var result = ""





//
//                // 2) download
//               var jsonDataPreview = JSONDownloaderMeteoNow(activity!!, jsonWeatherNow).toString()
//
//                // 3) parse Now
//                parseDatasWeatherNow().parseDatasFromApi(jsonDataPreview)
//
//                // 4) parse 3 days
//                parseDatasWeatherNextFiveDays().parseDatasFromApi(jsonDataPreview)



                NowFragment().getTheNewLocation(laString )
           //     NextThreeDaysFragment().getTheNewLocation(laString)



//               })
//
//
//        })



        return result
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        pd.dismiss()
        Toast.makeText(
            activity,
            "MOST PROBABLY THE APP CANNOT CONNECT TO ANY NETWORK SINCE IOEXCEPTION WAS RAISED",
            Toast.LENGTH_LONG
        ).show()
    }



}