package androidviakotlin.blog.weatherforecastbygps

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log


class LatitudeViewModel : ViewModel() {

    //LATITUDE

    // Declaration de la variable qui porte le liveData
    private val stateLatitude = MutableLiveData<String>()

    // la fonction getState() permet de dissocier le Mutable du LiveData
    // et donc d'obtenir un LIveData facilement utilisable
    fun getStateLatitude() : LiveData<String> = stateLatitude

    var liveDataLatitude : LiveData<String> = stateLatitude

    //fonction pour entrer la latitude
    fun entryLatitude (latitude : String) {
        stateLatitude.value = latitude
      //  Log.i("Letest", "funEntryLatitude : $stateLatitude")
    }

    //LONGITUDE

    private val stateLongitude = MutableLiveData<String> ()

    fun getStateLongitude() : LiveData<String> = stateLongitude

    fun entryLongitude (longitude : String){
        stateLongitude.value = longitude
    }


}