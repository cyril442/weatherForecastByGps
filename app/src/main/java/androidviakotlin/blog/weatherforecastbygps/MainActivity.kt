package androidviakotlin.blog.weatherforecastbygps

import android.app.Activity
import android.app.ProgressDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import com.google.android.material.snackbar.Snackbar
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import android.view.View
import com.google.android.gms.location.*
import androidviakotlin.blog.weatherforecastbygps.Fragments.NowFragment
import androidviakotlin.blog.weatherforecastbygps.Fragments.NextThreeDaysFragment
import androidviakotlin.blog.weatherforecastbygps.Utils.Downloaders.JSONDownloaderMeteoNow
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {

    // Creation du lateInit viewModel de type LoginViewModel
    lateinit var viewModel: LatitudeViewModel
    lateinit var mAdView : AdView

    lateinit var fusedLocationProviderClient : FusedLocationProviderClient
    lateinit var locationRequest : LocationRequest
    lateinit var locationCallback : LocationCallback

    val REQUEST_CODE = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Advertising
        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)


        // On fait entrer viewModel dans le scope de l'activité
        viewModel = ViewModelProviders.of(this).get(LatitudeViewModel::class.java)

        // Page Adapter
        val adapter = MyViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(NowFragment(), getString(R.string.titleNowFragment))
        adapter.addFragment(NextThreeDaysFragment(), getString(R.string.titleNextThreeDaysFragment))
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)


        // Check Permission for GPS point
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE
            )
        else {
            buildLocationRequest()
            buildLocationCallBack()


            // Create FusedProviderClient
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

            // Set Event Click on Update Button
            button_update_coordinates.setOnClickListener(View.OnClickListener {

                if (ActivityCompat.checkSelfPermission(
                        this@MainActivity,
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(
                        this@MainActivity,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this@MainActivity,
                        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                        REQUEST_CODE
                    )
                    return@OnClickListener
                }
                fusedLocationProviderClient.requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    Looper.myLooper()
                )

                Snackbar.make(it, "Mise à jour des données", Snackbar.LENGTH_SHORT).show()


            })

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
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

                var latt = location.latitude.toString()
                var longi = location.longitude.toString()

                viewModel.entryLatitude(latt)
                viewModel.entryLongitude((longi))

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

    override fun onResume() {
        /// On fait entrer viewModel dans le scope de l'activité
        viewModel = ViewModelProviders.of(this).get(LatitudeViewModel::class.java)

        //On s'abonne aux changements d'état et quand l'état change, on lance la fonction UpdateUI avec en valeur par défaut it
        viewModel.getStateLatitude().observe(this, Observer {
            yourCurrentLatitude.text = it

            viewModel.getStateLongitude().observe(this, Observer { itt : String? ->
              yourCurrentLongitude.text = itt
            })
        })
        super.onResume()
    }


}
