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
import projetosendwithmemotorista.sendwithmemotorista.R;

public class ListaChat extends AppCompatActivity {


    private ArrayList<String> minhasconversas = new ArrayList<>();
    private ArrayList<Usuarios> usuarios = new ArrayList<>();
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

        final String idMotorista = user.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("usuario");
       // databaseReferenceConversa = FirebaseDatabase.getInstance().getReference().child("mensagens").child(idPassageiro);
        databaseReferenceConversa = FirebaseDatabase.getInstance().getReference().child("mensagens");


        databaseReferenceConversa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                minhasconversas.clear();

                for(DataSnapshot dados:dataSnapshot.getChildren()){
                    for(DataSnapshot ds:dados.getChildren()){
                        if(ds.getKey().equals(idMotorista)){
                            String valor = dados.getKey();
                            minhasconversas.add(valor);
                        }
                    }


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
                    Usuarios usuario = usuarios.get(position);
                    if (usuario.getId().equals(idMotorista)) {
                        Toast.makeText(ListaChat.this, "É você..", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent i = new Intent(ListaChat.this, ChatActivity.class);
                        i.putExtra("nome", usuariosnome.get(position));
                        i.putExtra("id", usuario.getId());
                        i.putExtra("idcurrentuser", idMotorista);
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
                        String nomecomplet = usuario.getNome() + " " + usuario.getSobrenome();
                        usuarios.add(usuario);
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
