package com.example.emano.sendwithme.EncomendaPackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emano.sendwithme.R;
import com.example.emano.sendwithme.caronaPackage.EnderecoCarona;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

public class CadastrarEncomenda extends AppCompatActivity {

    private EditText cidadeOrigemEncomenda;
    private EditText enderecoOrigemencomenda;
    private String cidade;
    private String endereco;

    private Button avancar;
    private PlaceAutocompleteFragment autocompleteFragment;
    private PlaceAutocompleteFragment autocompleteFragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_encomenda);

        setView();

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                //Log.i(TAG, "Place: " + place.getName());
                //Toast.makeText(MainActivity.this, place.getAddress(),Toast.LENGTH_SHORT).show();
                cidade = place.getAddress().toString();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                //Log.i(TAG, "An error occurred: " + status);
                Toast.makeText(CadastrarEncomenda.this, "Add endereco origem",Toast.LENGTH_SHORT).show();
            }
        });

        autocompleteFragment2.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                //Log.i(TAG, "Place: " + place.getName());
                //Toast.makeText(MainActivity.this, place.getAddress(),Toast.LENGTH_SHORT).show();
                endereco = place.getAddress().toString();
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                //Log.i(TAG, "An error occurred: " + status);
                Toast.makeText(CadastrarEncomenda.this, "Add endereco saida",Toast.LENGTH_SHORT).show();
            }
        });

        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cidade.equals("1")){
                    Toast.makeText(CadastrarEncomenda.this, "Add endereco de cidade",Toast.LENGTH_SHORT).show();

                }else if(endereco.equals("1")){
                    Toast.makeText(CadastrarEncomenda.this, "Add endereco endereco",Toast.LENGTH_SHORT).show();
                }else{
                    avancarCadastroEncomenda();

                }


            }
        });



    }

    public void avancarCadastroEncomenda(){

        Intent intent = new Intent(CadastrarEncomenda.this, CadastrarEncomendaDestino.class);
        intent.putExtra("cidadeOrigem", String.valueOf(cidade));
        intent.putExtra("enderecoOrigem", String.valueOf(endereco));
        startActivity(intent);

    }


    public void setView(){

        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment2 = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment2);

        //cidadeOrigemEncomenda = (EditText) findViewById(R.id.editCidadeDestinoEncomenda);
        //enderecoOrigemencomenda = (EditText) findViewById(R.id.editEnderecoDestinoEncomenda);

        avancar = (Button) findViewById(R.id.btnAvancarCadastro1);
        cidade = "1";
        endereco = "1";

    }

}
