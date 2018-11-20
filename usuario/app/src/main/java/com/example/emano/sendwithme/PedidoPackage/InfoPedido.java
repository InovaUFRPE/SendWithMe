package com.example.emano.sendwithme.pedidoPackage;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emano.sendwithme.R;
import com.google.android.gms.maps.model.LatLng;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import util.DesenhaRotaTask;

public class InfoPedido extends AppCompatActivity {

    private TextView titulo;
    private TextView nome;
    private MapView mapa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_pedido);

        setView();

        Intent intent = getIntent();
        String origem = intent.getStringExtra("origem");
        String destino = intent.getStringExtra("destino");
        String titulo1 = intent.getStringExtra("titulo");
        String nome1 = intent.getStringExtra("nome");

        titulo.setText(titulo1);
        nome.setText(nome1);

        String texto = origem;
        LatLng latLng = getLocationFromAddress(getApplicationContext(), texto);

        String texto2 = destino;
        LatLng latLng2 = getLocationFromAddress(getApplicationContext(), texto2);

        marcarPontos(latLng,latLng2);

    }



    private void setView(){

        mapa = (MapView) findViewById(R.id.mapaIdInfo);
        titulo = (TextView) findViewById(R.id.txtTituloPedido);
        nome = (TextView) findViewById(R.id.txtNomeObjeto);

    }

    private void setMapLocationDefault() {
        IMapController mapControllerDefault = mapa.getController();
        mapControllerDefault.setZoom(15);
        //Centraliza o mapa no ponto de referência
        mapControllerDefault.setCenter(new GeoPoint(-8.0175094, -34.9492219));

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

        tracarRota(GeoPointOrigem, GeoPointDestino);


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

    public void tracarRota(GeoPoint pontoInicial, GeoPoint pontoFinal){
        ArrayList<GeoPoint> waypoints = new ArrayList<GeoPoint>();
        waypoints.add(pontoInicial);
        waypoints.add(pontoFinal);

        RoadManager roadManager = new OSRMRoadManager(getApplicationContext());
        Road road = null;

        try {

            road = new DesenhaRotaTask(waypoints, roadManager).execute(roadManager).get();
        } catch (Exception e) {
            e.printStackTrace();
        }


        Polyline roadOverlay = RoadManager.buildRoadOverlay(road);


        this.mapa.getOverlays().add(roadOverlay);

        this.mapa.invalidate();
    }

}