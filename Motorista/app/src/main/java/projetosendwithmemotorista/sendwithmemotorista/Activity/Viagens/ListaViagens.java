package projetosendwithmemotorista.sendwithmemotorista.Activity.Viagens;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import projetosendwithmemotorista.sendwithmemotorista.Helper.PreferenciasAndroid;
import projetosendwithmemotorista.sendwithmemotorista.R;

public class ListaViagens extends AppCompatActivity {

    private TextView edtcidade;
    private TextView edtendereço;
    private TextView edtdata;
    private TextView edthora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_viagens);
        setardadoslistaviagem();


    }
    private void setardadoslistaviagem(){

        PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(ListaViagens.this);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Viagens").child(preferenciasAndroid.getIdentificador());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                InicioViagem inicioviagem = dataSnapshot.getValue(InicioViagem.class);
                setView();
                edtcidade.setText(inicioviagem.getcidade());
                edtendereço.setText(inicioviagem.getendereco());
                edtdata.setText(inicioviagem.getdata());
                edthora.setText(inicioviagem.gethora());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void setView(){
        edtcidade = findViewById(R.id.Edtcity);
        edtendereço = findViewById(R.id.Edtend);
        edtdata = findViewById(R.id.Edtdata);
        edthora = findViewById(R.id.Edthora);
    }
}
