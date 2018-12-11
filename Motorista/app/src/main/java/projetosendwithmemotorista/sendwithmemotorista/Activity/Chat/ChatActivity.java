package projetosendwithmemotorista.sendwithmemotorista.Activity.Chat;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import projetosendwithmemotorista.sendwithmemotorista.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private ListView listaChat;
    private Button botaoEnviar;
    private TextView nomeDestinatario;
    private EditText mensagemId;

    private ArrayList<Mensagem> mensagems = new ArrayList<>();
    private ArrayAdapter adapter;


    private String idDestinatario;
    private String idEnviador;

    private DatabaseReference firebase;
    private FirebaseDatabase firebase2;

    private ValueEventListener valueEventListenerMensagem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        setView();


        Bundle extra = getIntent().getExtras();
        String nome = extra.getString("nome");
        nomeDestinatario.setText(nome);

        adapter = new ChatAdapter(getApplicationContext(), mensagems, extra.getString("idcurrentuser"));
        listaChat.setAdapter(adapter);

        idDestinatario = extra.getString("id");
        idEnviador = extra.getString("idcurrentuser");

        firebase2 = FirebaseDatabase.getInstance();

        firebase = firebase2.getReference().child("mensagens").child(idDestinatario).child(idEnviador);


        valueEventListenerMensagem = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mensagems.clear();

                for ( DataSnapshot dados: dataSnapshot.getChildren() ){
                    Mensagem mensagem = dados.getValue( Mensagem.class );
                    mensagems.add( mensagem );
                }
                adapter.notifyDataSetChanged();
                scrollMyListViewToBottom();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        firebase.addValueEventListener( valueEventListenerMensagem );

        botaoEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoMensagem = mensagemId.getText().toString();
                if( textoMensagem.isEmpty() ){
                    Toast.makeText(ChatActivity.this, "Digite uma mensagem para enviar!", Toast.LENGTH_SHORT).show();
                }else{

                    Mensagem mensagem = new Mensagem();
                    mensagem.setIdUsuario(idEnviador);
                    mensagem.setMensagem( textoMensagem );

                    salvarMensagem(idEnviador, idDestinatario, mensagem );

                    salvarMensagem(idDestinatario, idEnviador, mensagem );

                    mensagemId.setText("");


                }





            }
        });



    }

    private boolean salvarMensagem(String idRemetente, String idDestinatario, Mensagem mensagem){
        try {

            firebase = FirebaseDatabase.getInstance().getReference().child("mensagens");

            firebase.child( idRemetente )
                    .child( idDestinatario )
                    .push()
                    .setValue( mensagem );

            return true;

        }catch ( Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListenerMensagem);
    }

    public void setView(){
        listaChat = findViewById(R.id.listaChatId);
        botaoEnviar = findViewById(R.id.botaoEnviarMensagemId);
        nomeDestinatario = findViewById(R.id.nomeDestinatarioId);
        mensagemId = findViewById(R.id.caixaTextoChatId);


    }
    private void scrollMyListViewToBottom() {
        listaChat.post(new Runnable() {
            @Override
            public void run() {
                // Select the last row so it will scroll into view...
                listaChat.setSelection(listaChat.getCount() - 1);
            }
        });
    }

}