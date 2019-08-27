package androidviakotlin.blog.weatherforecastbygps

import android.arch.lifecycle.ViewModelProviders
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.view.View
import com.google.android.gms.location.*
import androidviakotlin.blog.weatherforecastbygps.Fragments.NowFragment
import androidviakotlin.blog.weatherforecastbygps.Fragments.NextThreeDaysFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    // Creation du lateInit viewModel de type LoginViewModel
    lateinit var viewModel: LatitudeViewModel


    lateinit var fusedLocationProviderClient : FusedLocationProviderClient
    lateinit var locationRequest : LocationRequest
    lateinit var locationCallback : LocationCallback



    val REQUEST_CODE = 1000

//    var latitudeChanging : String by Delegates.observable("latitude") {property, oldValue, newValue ->
//
//    }
//
//    var longitudeChanging : String by Delegates.observable("longitude") {property, oldValue, newValue ->
//
//    }


    lateinit var drawer: DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Manage the NavigationView with TabLayout and viewPager
//        var tabLayout = findViewById<TabLayout>(R.id.tabs)
//        var pager = findViewById<ViewPager>(R.id.viewPager)

        // On fait entrer viewModel dans le scope de l'activité
        viewModel = ViewModelProviders.of(this).get(LatitudeViewModel::class.java)

        // Page Adapter
        val adapter = MyViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(NowFragment(), "Now")
        adapter.addFragment(NextThreeDaysFragment(), "Next 3 days")
//        adapter.addFragment(FragmentSports(), "Sports")
////        adapter.addFragment(FragmentWeather(), "Meteo")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        // Check Permission for GPS point
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION))
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE)

        else
        {
            buildLocationRequest()
            buildLocationCallBack()





            // Create FusedProviderClient
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

            // Set Event Click on Update Button
            button_update_coordinates.setOnClickListener (View.OnClickListener {

                if(ActivityCompat.checkSelfPermission(this@MainActivity, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this@MainActivity, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED   )
                {
                    ActivityCompat.requestPermissions(this@MainActivity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE)
                    return@OnClickListener
                }
                fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())




            })
        }

   //     Log.i("MainActivity", "Value of Latitude : $latitudeChanging and of Longitude : $longitudeChanging")

    }

    override fun onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    // CLASS To MANAGE the PageAdapter
    class MyViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

        private val fragmentList: MutableList<Fragment> = ArrayList()
        private val titleList: MutableList<String> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            titleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }
    }

    private fun buildLocationCallBack() {
        locationCallback = object : LocationCallback() {

            override fun onLocationResult(p0: LocationResult?) {
                var location = p0!!.locations.get(p0!!.locations.size-1)
                yourCurrentLatitude.text = location.latitude.toString()
                yourCurrentLongitude.text = location.longitude.toString()
//

                var latt = location.latitude.toString()
                var longi = location.longitude.toString()

                viewModel.entryLatitude(latt)
                viewModel.entryLongitude((longi))




//                // Get the changes on the latitude and longitude
//                latitudeChanging = location.latitude.toString()
//
//             //   listener.onLocationChanged(latitudeChanging)
//                longitudeChanging = location.longitude.toString()
//
//                Log.i("MainActivity", "AFTER CLIC Value of Latitude : $latitudeChanging and of Longitude : $longitudeChanging")

            }

        }

    }

    private fun buildLocationRequest() {
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 5000
        locationRequest.fastestInterval = 3000
        locationRequest.smallestDisplacement = 10f

    }



}
