package com.example.emano.sendwithme.caronaPackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emano.sendwithme.MotoristaPackage.ListarMotoristas2;
import com.example.emano.sendwithme.R;
import com.example.emano.sendwithme.MotoristaPackage.ListarMotoristas;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

public class EnderecoCarona extends AppCompatActivity {

    TextView origem;
    TextView destino;

    private String enderecoOrigem;
    private String enderecoDestino;
    private Button avancarCarona;
    private PlaceAutocompleteFragment autocompleteFragment;
    private PlaceAutocompleteFragment autocompleteFragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endereco_carona);
        setView();


        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                //Log.i(TAG, "Place: " + place.getName());
                //Toast.makeText(MainActivity.this, place.getAddress(),Toast.LENGTH_SHORT).show();
                enderecoOrigem = place.getAddress().toString();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                //Log.i(TAG, "An error occurred: " + status);
                Toast.makeText(EnderecoCarona.this, "Add endereco origem",Toast.LENGTH_SHORT).show();
            }
        });

        autocompleteFragment2.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                //Log.i(TAG, "Place: " + place.getName());
                //Toast.makeText(MainActivity.this, place.getAddress(),Toast.LENGTH_SHORT).show();
                enderecoDestino = place.getAddress().toString();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                //Log.i(TAG, "An error occurred: " + status);
                Toast.makeText(EnderecoCarona.this, "Add endereco saida",Toast.LENGTH_SHORT).show();
            }
        });


        avancarCarona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(enderecoDestino.equals("1")){
                    Toast.makeText(EnderecoCarona.this, "Add endereco saida",Toast.LENGTH_SHORT).show();

                }else if(enderecoDestino.equals("1")){
                    Toast.makeText(EnderecoCarona.this, "Add endereco destino",Toast.LENGTH_SHORT).show();
                }else{
                    avancarCarona();

                }

            }
        });
    }




    public void avancarCarona(){

        Intent intent = new Intent(EnderecoCarona.this, ListarMotoristas2.class);
        intent.putExtra("origem", enderecoOrigem);
        intent.putExtra("destino", enderecoDestino);
        startActivity(intent);

    }

    public void setView(){

        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment2 = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment2);

        avancarCarona = findViewById(R.id.button6);

        enderecoDestino = "1";
        enderecoOrigem = "1";

    }

}
