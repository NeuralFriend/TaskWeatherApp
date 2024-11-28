package ru.mercanie.taskweatherapp.app

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import ru.mercanie.taskweatherapp.data.remote.utils.Constants
import ru.mercanie.taskweatherapp.presentation.screens.weather.WeatherScreen
import ru.mercanie.taskweatherapp.presentation.theme.TaskWeatherAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        requestLocationPermissions()

        setContent {
            TaskWeatherAppTheme {
                Column(modifier = Modifier.padding(16.dp)) {
                    WeatherScreen()
                }
            }
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val isLocationGranted = permissions[android.Manifest.permission.ACCESS_FINE_LOCATION] ?: false
        val isCoarseGranted = permissions[android.Manifest.permission.ACCESS_COARSE_LOCATION] ?: false
        if (isLocationGranted || isCoarseGranted) {
            fetchUserLocation()
        } else {
            Toast.makeText(this, "Приложению нужно разрешение на местоположение", Toast.LENGTH_SHORT).show()
        }
    }

    private fun requestLocationPermissions() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        } else {
            fetchUserLocation()
        }
    }

    private fun fetchUserLocation() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    saveCoordinates(location.latitude, location.longitude)
                } else {
                }
            }.addOnFailureListener {
            }
        }
    }

    private fun saveCoordinates(lat: Double, lon: Double) {
        val sharedPrefs = getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE)
        with(sharedPrefs.edit()) {
            putFloat(Constants.KEY_LAT, lat.toFloat())
            putFloat(Constants.KEY_LON, lon.toFloat())
            apply()
        }
    }
}
