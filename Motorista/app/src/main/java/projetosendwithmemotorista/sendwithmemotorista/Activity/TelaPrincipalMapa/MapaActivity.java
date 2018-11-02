package projetosendwithmemotorista.sendwithmemotorista.Activity.TelaPrincipalMapa;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import projetosendwithmemotorista.sendwithmemotorista.R;

public class MapaActivity extends SupportMapFragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

        private GoogleMap mMap;
        private LocationManager locationManager;
        private static  final String TAG = "MapaActivity";
        private Context cont;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        try {

            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            //Toast.makeText(getActivity(), "Provider" +provider, Toast.LENGTH_LONG).show();//
            mMap = googleMap;
            mMap.setOnMapClickListener(this);
            mMap.getUiSettings().setZoomControlsEnabled(true);


            // Add a marker in Sydney and move the camera
            // LatLng sydney = new LatLng(-34, 151);
            //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            // mMap.moveCamera(CameraUpdateFactory.newLatLng());
            mMap.setMyLocationEnabled(true);

        }catch (SecurityException ex){

            Log.e(TAG, "Error", ex);
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

}
