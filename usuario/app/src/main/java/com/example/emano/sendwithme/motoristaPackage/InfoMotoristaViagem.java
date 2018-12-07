package com.example.emano.sendwithme.MotoristaPackage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emano.sendwithme.ChatPackage.ChatActivity;
import com.example.emano.sendwithme.MotoristaPackage.ListarMotoristas;
import com.example.emano.sendwithme.MotoristaPackage.ListarMotoristas2;
import com.example.emano.sendwithme.R;
import com.example.emano.sendwithme.ViagemPackage.Viagem;
import com.example.emano.sendwithme.caronaPackage.SolicitacaoCarona;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class InfoMotoristaViagem extends AppCompatActivity {

    private FirebaseOptions options;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReference2;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    private DatabaseReference databaseReferenceViagem;
    private DatabaseReference teste;

    private String viagemId;

    TextView nome;

    TextView origem;
    TextView destino;
    TextView data;
    TextView hora;
    TextView encomenda;

    Button solicitaViagem;
    Button botaoChat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_motorista_viagem);

        setview();

        Intent intent = getIntent();


        final String nome1 = intent.getStringExtra("nome") + " " + intent.getStringExtra("sobrenome");
        String email = intent.getStringExtra("email");
        String sexo = intent.getStringExtra("sexo");

        viagemId = intent.getStringExtra("viagemId");
        String origem1 = intent.getStringExtra("endereco") + ", " +intent.getStringExtra("cidade");
        String destino1 = intent.getStringExtra("enderecodest") + ", " + intent.getStringExtra("cidadedest");
        String data1 = intent.getStringExtra("data");
        String hora1 = intent.getStringExtra("hora");
        String encomendas1 = intent.getStringExtra("encomendas");
        final String usuarioid = intent.getStringExtra("usuarioid");
        final String idPassageiro = user.getUid();

        nome.setText(nome1);

        origem.setText(origem1);
        destino.setText(destino1);
        data.setText(data1);
        hora.setText(hora1);
        encomenda.setText(encomendas1);

        databaseReferenceViagem = FirebaseDatabase.getInstance().getReference().child("Viagens");

        //databaseReference = FirebaseDatabase.getInstance().getReference(viagemId).child("solicitacoes");

        solicitaViagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //SolicitacaoCarona novaSolicitacao = new SolicitacaoCarona();

               // novaSolicitacao.setViagemId(viagemId);
                //novaSolicitacao.setStatus("Aguardando confirmação");
               // novaSolicitacao.setMotoristaId(usuarioid);
               // novaSolicitacao.setUsuarioId(user.getUid());

               // databaseReference.push().setValue(novaSolicitacao);
                incluiusuario();

                Toast.makeText(InfoMotoristaViagem.this,"Solicitação efetuada com sucesso!",Toast.LENGTH_SHORT).show();
                finish();


            }
        });

        botaoChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(InfoMotoristaViagem.this, ChatActivity.class);

                // recupera dados a serem passados


                // enviando dados para conversa activity
                intent.putExtra("nome", nome1 );
                intent.putExtra("id", usuarioid );
                intent.putExtra("idcurrentuser", idPassageiro);

                startActivity(intent);

            }
        });

    }
    public void incluiusuario(){
        databaseReferenceViagem.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dados:dataSnapshot.getChildren()){

                    Viagem viagem = dados.getValue(Viagem.class);
                    if (viagem.getViagemUID().equals(viagemId)){

                        //
                        Integer numpass = viagem.getAssentos();
                        if(numpass==0){
                            //não rola
                            Toast.makeText(InfoMotoristaViagem.this,"Não há vagas nessa viagem!",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            ArrayList<String> lista = new ArrayList<>();
                            lista = viagem.getListapass();
                            if(lista.contains(user.getUid())){
                                //o usuario já ta na lista dos passageiros
                                Toast.makeText(InfoMotoristaViagem.this,"Você já participa dessa viagem!",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                if(lista.contains("Controle")){
                                    lista.remove(0);
                                }
                                lista.add(user.getUid());
                                viagem.setAssentos(numpass-1);
                                viagem.setListapass(lista);
                                databaseReference.child("Viagens").child(viagem.getViagemUID()).setValue(viagem);
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void setview(){

       nome = (TextView) findViewById(R.id.textInfoNomeMotorista);

       origem = (TextView) findViewById(R.id.textOrigemViagemInfo);
       destino = (TextView) findViewById(R.id.textdestinoViagemInfo);
       data = (TextView) findViewById(R.id.textDataViagemInfo);
       hora = (TextView) findViewById(R.id.textHoraViagemInfo);
       encomenda = (TextView) findViewById(R.id.textEncomendasViagemInfo);
       solicitaViagem = (Button) findViewById(R.id.btnSolicitarCarona);
       botaoChat = findViewById(R.id.btnChatId);

    }

    public void voltarListaMotorista(View view){

        Intent intent2 = new Intent(InfoMotoristaViagem.this, ListarMotoristas.class);
        startActivity(intent2);

    }
    
}
