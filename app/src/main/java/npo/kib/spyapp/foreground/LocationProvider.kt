package npo.kib.spyapp.foreground

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LocationProvider(context: Context) {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    suspend fun getLastLocation(): Location? {
        return suspendCoroutine { continuation ->
            fusedLocationClient.lastLocation.addOnSuccessListener { continuation.resume(it) }
        }
    }
}