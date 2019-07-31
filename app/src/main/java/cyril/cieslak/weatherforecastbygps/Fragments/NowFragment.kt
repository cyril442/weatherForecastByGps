package cyril.cieslak.weatherforecastbygps.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cyril.cieslak.weatherforecastbygps.ItemWeatherAdapter

import cyril.cieslak.weatherforecastbygps.R
import cyril.cieslak.weatherforecastbygps.Utils.JSONDownloaderMeteoNow
import cyril.cieslak.weatherforecastbygps.Utils.parseDatasWeatherNow

@Suppress("UNREACHABLE_CODE")
class NowFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {




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

    val jsonWeatherNow = "http://api.openweathermap.org/data/2.5/weather?lat=43.398796&lon=-0.4370218&appid=467005a2981f9965ac02fa6dabd5fc2e"


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


        // to get the String JSonData, we use the class JSONDownloaderTopStories
        var jsonDataPreview = JSONDownloaderMeteoNow(context!!, jsonWeatherNow).execute().get()
        Log.i("bingo", " jsonDataPreview : $jsonDataPreview")


        // To Parse the result of the JSONDownloadTopStories using the external CLass parseDatas() which include the method parseDatasFromApi
        datas = parseDatasWeatherNow().parseDatasFromApi(jsonDataPreview)
        Log.i("bingo", " datas parsed : $datas")


        adapter = ItemWeatherAdapter(datas)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_in_layout_weather)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

    }


    override fun onRefresh() {
        //   Toast.makeText(context, "OnRefresh Pulled", Toast.LENGTH_SHORT).show()
        JSONDownloaderMeteoNow(context!!, jsonWeatherNow).execute()
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false)
        }
    }





}