package com.example.emano.sendwithme.HomePackage;


import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Address;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import android.Manifest;
import android.widget.Toast;

import com.example.emano.sendwithme.R;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragmentMap extends Fragment {

    private EditText cidadeDigitada1;
    private EditText cidadeDigitada2;
    private Button botaoAchar;
    private MapView mapa;


    public HomeFragmentMap() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home_fragment_map, container, false);

        cidadeDigitada1 = rootView.findViewById(R.id.cidadeDigitadaId);
        cidadeDigitada2 = rootView.findViewById(R.id.cidadeDigitadaId2);

        mapa = rootView.findViewById(R.id.mapaId);

        mapa.setTileSource(TileSourceFactory.MAPNIK);

        botaoAchar = rootView.findViewById(R.id.botaoAchaCidadeId);

        botaoAchar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SE AMBOS OS CAMPOS TÃO VAZIOS E EXISTEM!!!
                String texto = cidadeDigitada1.getText().toString();
                LatLng latLng = getLocationFromAddress(getContext(), texto);

                String texto2 = cidadeDigitada2.getText().toString();
                LatLng latLng2 = getLocationFromAddress(getContext(), texto2);

                marcarPontos(latLng, latLng2);


                //mapsFragment.setarLocalizacao(latLng);


                //Toast.makeText(getContext(), latLng.toString(), Toast.LENGTH_SHORT).show();
            }
        });



        return rootView;
    }


    private void marcarPontos(LatLng origem, LatLng destino) {


        GeoPoint GeoPointOrigem = new GeoPoint(origem.latitude, origem.longitude);
        GeoPoint GeoPointDestino = new GeoPoint(destino.latitude, destino.longitude);

        IMapController mapController = mapa.getController();

        Marker marcadorInicial = new Marker(mapa);
        Marker marcadorFinal = new Marker(mapa);

        marcadorInicial.setPosition(GeoPointOrigem);
        marcadorFinal.setPosition(GeoPointDestino);

        marcadorFinal.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        mapa.getOverlays().add(marcadorFinal);

        marcadorInicial.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        mapa.getOverlays().add(marcadorInicial);

        mapController.setZoom(15);
        //Centraliza o mapa no ponto de referência
        mapController.setCenter(GeoPointOrigem);


    }

    public LatLng getLocationFromAddress(Context context, String inputtedAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng resLatLng = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(inputtedAddress, 5);
            if (address == null) {
                return null;
            }

            if (address.size() == 0) {
                return null;
            }

            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            resLatLng = new LatLng(location.getLatitude(), location.getLongitude());

        } catch (IOException ex) {

            ex.printStackTrace();
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        return resLatLng;
    }













}
