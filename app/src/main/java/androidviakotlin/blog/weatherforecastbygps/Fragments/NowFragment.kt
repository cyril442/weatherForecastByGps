package androidviakotlin.blog.weatherforecastbygps.Fragments

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidviakotlin.blog.weatherforecastbygps.ItemWeatherAdapter
import androidviakotlin.blog.weatherforecastbygps.LatitudeViewModel

import androidviakotlin.blog.weatherforecastbygps.R
import androidviakotlin.blog.weatherforecastbygps.Utils.Downloaders.JSONDownloaderMeteoNow
import androidviakotlin.blog.weatherforecastbygps.Utils.Parsers.parseDatasWeatherNow
import java.util.concurrent.Executors

@Suppress("UNREACHABLE_CODE")
class NowFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    // Creation du lateInit viewModel de type LatitudeViewModel
    lateinit var viewModelLatitude: LatitudeViewModel



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

    var adapter = ItemWeatherAdapter(datas)

    var jsonWeatherNow = "http://api.openweathermap.org/data/2.5/weather?lat=48.8534&lon=2.3488&units=metric&appid=467005a2981f9965ac02fa6dabd5fc2e"


    lateinit var swipeRefreshLayout: SwipeRefreshLayout



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var theview = inflater.inflate(R.layout.fragment_now, container, false)
        swipeRefreshLayout = theview.findViewById(R.id.swiperefreshweather)
        swipeRefreshLayout.setOnRefreshListener(this)



        // Inflate the layout for this fragment
        return theview


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        getTheNewLocation(jsonWeatherNow)


        // On fait entrer viewModel dans le scope de l'activité
        viewModelLatitude = ViewModelProviders.of(activity!!).get(LatitudeViewModel::class.java)
      //  viewModelLongitude = ViewModelProviders.of(activity!!).get(LatitudeViewModel::class.java)

        //On s'abonne aux changements d'état et quand l'état change, on lance la fonction UpdateUI avec en valeur par défaut it
        viewModelLatitude.getStateLatitude().observe(this, Observer {

            var lattitude = it

            viewModelLatitude.getStateLongitude().observe(this, Observer { itt : String? ->

                var longgitude = itt


            jsonWeatherNow = "http://api.openweathermap.org/data/2.5/weather?lat=$lattitude&lon=$longgitude&units=metric&appid=467005a2981f9965ac02fa6dabd5fc2e"

            getTheNewLocation(jsonWeatherNow)


            })
      })


    }

    private fun updateJsonWeatherNow(newLatitude: String) : String {
        jsonWeatherNow = "http://api.openweathermap.org/data/2.5/weather?lat=$newLatitude&lon=-0.4370218&units=metric&appid=467005a2981f9965ac02fa6dabd5fc2e"
        Log.i("newJson", "$jsonWeatherNow")
        return jsonWeatherNow
    }


    override fun onRefresh() {
        //   Toast.makeText(context, "OnRefresh Pulled", Toast.LENGTH_SHORT).show()

        JSONDownloaderMeteoNow(context!!, jsonWeatherNow).execute()
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false)
        }
    }


    fun getTheNewLocation (jsonWeatherNow : String) {


    // to get the String JSonData, we use the class JSONDownloaderTopStories
    var jsonDataPreview = JSONDownloaderMeteoNow(
        context!!,
        jsonWeatherNow
    ).execute().get()
    Log.i("bingo", " jsonDataPreview : $jsonDataPreview")


    // To Parse the result of the JSONDownloadTopStories using the external CLass parseDatas() which include the method parseDatasFromApi
    datas = parseDatasWeatherNow().parseDatasFromApi(jsonDataPreview)
    Log.i("bingo", " datas parsed : $datas")


    adapter = ItemWeatherAdapter(datas)

    val recyclerView = view?.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler_view_in_layout_weather_now)
    recyclerView?.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
    recyclerView?.adapter = adapter
}


}