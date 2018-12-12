package projetosendwithmemotorista.sendwithmemotorista.Activity.Chat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import projetosendwithmemotorista.sendwithmemotorista.Entidades.Usuarios;
import projetosendwithmemotorista.sendwithmemotorista.Helper.PreferenciasAndroid;
import projetosendwithmemotorista.sendwithmemotorista.R;

public class ListaChat extends AppCompatActivity {


    private ArrayList<String> minhasconversas = new ArrayList<>();
    private ArrayList<String> usuarios = new ArrayList<>();
    private ArrayList<String> usuariosnome = new ArrayList<>();
    private ArrayAdapter adapter;

    private ListView listachat;

    private DatabaseReference databaseReference;
    private DatabaseReference databaseReferenceConversa;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    private Toast erro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_chat);

        erro = Toast.makeText(this,"Algo deu errado", Toast.LENGTH_SHORT);

        listachat = findViewById(R.id.listviewchat);
        final PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(ListaChat.this);
        final String idMorotista = preferenciasAndroid.getIdentificador();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Usuarios");
        databaseReferenceConversa = FirebaseDatabase.getInstance().getReference().child("mensagens").child(idMorotista);

        databaseReferenceConversa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                minhasconversas.clear();

                for(DataSnapshot dados:dataSnapshot.getChildren()){

                    String valor = dados.getKey();
                    minhasconversas.add(valor);
                }
                carregarLista();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                erro.show();
            }
        });

        listachat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(usuariosnome.get(position).equals("Parece que nenhum passageiro abriu uma conversa com você :(")) {
                    Toast.makeText(ListaChat.this,"Você não tem conversas",Toast.LENGTH_SHORT).show();
                }
                else {
                    String idusu = usuarios.get(position);
                    if (idusu.equals(idMorotista)) {
                        Toast.makeText(ListaChat.this, "É você..", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent i = new Intent(ListaChat.this, ChatActivity.class);
                        i.putExtra("nome", usuariosnome.get(position));
                        i.putExtra("id", idusu);
                        i.putExtra("idcurrentuser", idMorotista);
                        startActivity(i);
                    }
                }
            }
        });


    }
    private void carregarLista(){
        adapter = new ArrayAdapter<String>(this,R.layout.infoviagens,R.id.textView8,usuariosnome);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usuarios.clear();
                usuariosnome.clear();
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    Usuarios usuario = ds.getValue(Usuarios.class);
                    if(minhasconversas.contains(usuario.getId())) {
                        String nomecomplet = usuario.getNome();
                        usuarios.add(usuario.getId());
                        usuariosnome.add(nomecomplet);
                    }
                }
                if(usuariosnome.isEmpty()){
                    usuariosnome.add("Parece que nenhum passageiro abriu uma conversa com você :(");
                }
                listachat.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                erro.show();
            }
        });

    }
}
