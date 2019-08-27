package androidviakotlin.blog.weatherforecastbygps.Fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidviakotlin.blog.weatherforecastbygps.ItemWeatherNextFiveDaysAdapter
import androidviakotlin.blog.weatherforecastbygps.LatitudeViewModel

import androidviakotlin.blog.weatherforecastbygps.R
import androidviakotlin.blog.weatherforecastbygps.Utils.Downloaders.JSONDownloaderMeteoNow
import androidviakotlin.blog.weatherforecastbygps.Utils.Parsers.parseDatasWeatherNextFiveDays

@Suppress("UNREACHABLE_CODE")
class NextFiveDaysFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

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

    var adapter = ItemWeatherNextFiveDaysAdapter(datas)

    var jsonWeatherNow =
        "http://api.openweathermap.org/data/2.5/forecast?lat=48.8534&lon=2.3488&units=metric&cnt=24&appid=467005a2981f9965ac02fa6dabd5fc2e"


    lateinit var swipeRefreshLayout: SwipeRefreshLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var theview = inflater.inflate(R.layout.fragment_today, container, false)
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


                jsonWeatherNow = "http://api.openweathermap.org/data/2.5/forecast?lat=$lattitude&lon=$longgitude&units=metric&cnt=24&appid=467005a2981f9965ac02fa6dabd5fc2e"
                Log.i("NextFive", "la string : $jsonWeatherNow")

                getTheNewLocation(jsonWeatherNow)


            })
        })

    }


    override fun onRefresh() {
        //   Toast.makeText(context, "OnRefresh Pulled", Toast.LENGTH_SHORT).show()
        JSONDownloaderMeteoNow(context!!, jsonWeatherNow).execute()
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false)
        }
    }


    private fun getTheNewLocation (jsonWeatherNow : String) {


        // to get the String JSonData, we use the class JSONDownloaderTopStories
        var jsonDataPreview = JSONDownloaderMeteoNow(
            context!!,
            jsonWeatherNow
        ).execute().get()
        Log.i("banga", " jsonDataPreview : $jsonDataPreview")


        // To Parse the result of the JSONDownloadTopStories using the external CLass parseDatas() which include the method parseDatasFromApi
        datas = parseDatasWeatherNextFiveDays().parseDatasFromApi(jsonDataPreview)
        Log.i("banga", " datas parsed : $datas")


        adapter = ItemWeatherNextFiveDaysAdapter(datas)

        val recyclerView = view?.findViewById<RecyclerView>(R.id.recycler_view_in_layout_weather_today)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = adapter
    }
}

