package com.example.emano.sendwithme.EncomendaPackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emano.sendwithme.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

public class CadastrarEncomendaDestino extends AppCompatActivity {

    private EditText cidadeDestinoEncomenda;
    private EditText enderecoDestinoencomenda;
    private String cidade1;
    private String endereco1;
    private String cidade;
    private String endereco;

    private Button avancar;

    private PlaceAutocompleteFragment autocompleteFragment;
    private PlaceAutocompleteFragment autocompleteFragment2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_encomenda_destino);

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
                Toast.makeText(CadastrarEncomendaDestino.this, "Add endereco origem",Toast.LENGTH_SHORT).show();
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
                Toast.makeText(CadastrarEncomendaDestino.this, "Add endereco saida",Toast.LENGTH_SHORT).show();
            }
        });

        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(cidade.equals("1")){
                    Toast.makeText(CadastrarEncomendaDestino.this, "Add endereco de cidade",Toast.LENGTH_SHORT).show();

                }else if(endereco.equals("1")){
                    Toast.makeText(CadastrarEncomendaDestino.this, "Add endereco endereco",Toast.LENGTH_SHORT).show();
                }else{
                    avancarCadastroEncomendaDestino();

                }


            }
        });



    }

    public void avancarCadastroEncomendaDestino(){

        Intent intent = new Intent(CadastrarEncomendaDestino.this, CadastrarEncomendaDescricao.class);
        intent.putExtra("cidadeDestino", String.valueOf(cidade));
        intent.putExtra("enderecoDestino", String.valueOf(endereco));
        intent.putExtra("enderecoOrigem", String.valueOf(endereco1));
        intent.putExtra("cidadeOrigem", String.valueOf(cidade1));

        startActivity(intent);

    }

    public void setView(){

        Bundle extras = getIntent().getExtras();
        cidade1 = extras.getString("cidadeOrigem");
        endereco1 = extras.getString("enderecoOrigem");

        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment2 = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment2);



        //cidadeDestinoEncomenda = (EditText) findViewById(R.id.editCidadeDestinoEncomenda);
        //enderecoDestinoencomenda = (EditText) findViewById(R.id.editEnderecoDestinoEncomenda);
        avancar = (Button) findViewById(R.id.btnAvancarCadastro2);
        cidade = "1";
        endereco = "1";

    }

}

