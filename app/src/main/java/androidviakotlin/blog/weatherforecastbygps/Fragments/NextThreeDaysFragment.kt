package androidviakotlin.blog.weatherforecastbygps.Fragments

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidviakotlin.blog.weatherforecastbygps.ItemWeatherNextThreeDaysAdapter
import androidviakotlin.blog.weatherforecastbygps.LatitudeViewModel

import androidviakotlin.blog.weatherforecastbygps.R
import androidviakotlin.blog.weatherforecastbygps.Utils.Downloaders.JSONDownloaderMeteoNow
import androidviakotlin.blog.weatherforecastbygps.Utils.Parsers.parseDatasWeatherNextFiveDays
import androidviakotlin.blog.weatherforecastbygps.Utils.Weather

@Suppress("UNREACHABLE_CODE")
class NextThreeDaysFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    // Creation du lateInit viewModel de type LatitudeViewModel
    lateinit var viewModelLatitude: LatitudeViewModel

    var datas = mutableListOf(
        mutableListOf<Weather>(
            Weather()
//            "un",
//            "deux",
//            "trois",
//            "quatre",
//            "cinq",
//            "six",
//            "sept",
//            "huit",
//            "neuf",
//            "dix",
//            "onze",
//            "douze",
//            "treize",
//            "quatorze",
//            "quinze"
        )
    )

    var adapter = ItemWeatherNextThreeDaysAdapter(datas)

    var jsonWeatherNow =
        "http://api.openweathermap.org/data/2.5/forecast?lat=48.8534&lon=2.3488&units=metric&cnt=24&appid=467005a2981f9965ac02fa6dabd5fc2e"


    lateinit var swipeRefreshLayout: SwipeRefreshLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var theview = inflater.inflate(R.layout.fragment_nextthreedays, container, false)
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


    fun getTheNewLocation (jsonWeatherNow : String) {


        // to get the String JSonData, we use the class JSONDownloaderTopStories
        var jsonDataPreview = JSONDownloaderMeteoNow(
            context!!,
            jsonWeatherNow
        ).execute().get()
        Log.i("banga", " jsonDataPreview : $jsonDataPreview")


        // To Parse the result of the JSONDownloadTopStories using the external CLass parseDatas() which include the method parseDatasFromApi
        datas = parseDatasWeatherNextFiveDays(jsonDataPreview).parseDatasFromApi(jsonDataPreview)
        Log.i("banga", " datas parsed : $datas")


        adapter = ItemWeatherNextThreeDaysAdapter(datas)

        val recyclerView = view?.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recycler_view_in_layout_weather_today)
        recyclerView?.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        recyclerView?.adapter = adapter
    }

    fun getTheCorrectMonthFormat(month: String?): String {

        var monthTo: String = ""

           var leContext = this.context

        when (month) {
            "01" -> monthTo = (R.string.Janvier).toString()
            "02" -> monthTo = (R.string.Fevrier).toString()
            "03" -> monthTo = (R.string.Mars).toString()
            "04" -> monthTo = (R.string.Avril).toString()
            "05" -> monthTo = (R.string.Mai).toString()
            "06" -> monthTo = (R.string.Juin).toString()
            "07" -> monthTo = (R.string.Juillet).toString()
            "08" -> monthTo = "aout"
            "09" -> monthTo = "Septembre"
            "10" -> monthTo = (R.string.Octobre).toString()
            "11" -> monthTo = (R.string.Novembre).toString()
            "12" -> monthTo = (R.string.Décembre).toString()

        }
        Log.i("banga8", "MonthTo : $monthTo")

        return monthTo
    }
}

