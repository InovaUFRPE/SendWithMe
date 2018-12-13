package com.example.emano.sendwithme.motoristaPackage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emano.sendwithme.ChatPackage.ChatActivity;
import com.example.emano.sendwithme.MotoristaPackage.Motorista;
import com.example.emano.sendwithme.R;
import com.example.emano.sendwithme.UsuarioPackage.Usuario;
import com.example.emano.sendwithme.ViagemPackage.Viagem;
import com.example.emano.sendwithme.homePackage.TelaInicial;
import com.example.emano.sendwithme.homePackage.TelaInicial;
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
    private DatabaseReference databaseReferenceViagemUsuario;
    private DatabaseReference databaseReferenceUsuarioViagem;
    private DatabaseReference databaseReferenceUsuario;
    private DatabaseReference databaseReferenceMotorista;

    private String viagemId;

    private ArrayList<String> usuariosCadastrados = new ArrayList<>();
    private Viagem essaViagem;
    private Usuario esseUsuario;
    private Motorista motorista;

    TextView nome;

    TextView origem;
    TextView destino;
    TextView data;
    TextView hora;
    TextView encomenda;

    Button solicitaViagem;
    Button botaoChat;
    Button maaisInformacoes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_motorista_viagem);

        setview();

        final Intent intent = getIntent();


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




        databaseReferenceUsuarioViagem = FirebaseDatabase.getInstance().getReference().child("UsuarioViagem");

        //ESSE NO DE VIAGEM TEM TODOS OS USUARIOS Q JA SE CADASTRARAM AQUI
        databaseReferenceViagemUsuario = FirebaseDatabase.getInstance().getReference().child("ViagemUsuario").child(viagemId);


        databaseReferenceViagem = FirebaseDatabase.getInstance().getReference().child("Viagens");

        databaseReferenceUsuario = FirebaseDatabase.getInstance().getReference().child("Usuarios");


        databaseReferenceUsuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dados:dataSnapshot.getChildren()){

                    Usuario usuario = dados.getValue(Usuario.class);

                    if(usuario.getId().equals(user.getUid())){
                        esseUsuario = usuario;
                    }



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        databaseReferenceViagem.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dados:dataSnapshot.getChildren()){

                    Viagem viagem = dados.getValue(Viagem.class);
                    if(dados.getKey().equals(viagemId)){
                        essaViagem = viagem;
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        databaseReferenceViagemUsuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                usuariosCadastrados.clear();

                for (DataSnapshot dados:dataSnapshot.getChildren()){

                    Usuario usuario = dados.getValue(Usuario.class);
                    if(usuario!=null){
                        addusuariosCadastrados(usuario.getId());

                    }





                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //databaseReference = FirebaseDatabase.getInstance().getReference(viagemId).child("solicitacoes");

        databaseReferenceMotorista = FirebaseDatabase.getInstance().getReference().child("usuario").child(usuarioid);

        databaseReferenceMotorista.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                motorista = dataSnapshot.getValue(Motorista.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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

        maaisInformacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(InfoMotoristaViagem.this, InfoMotorista.class);
                intent1.putExtra("nome", String.valueOf(motorista.getNome()));
                intent1.putExtra("sobrenome", String.valueOf(motorista.getSobrenome()));
                intent1.putExtra("sexo", String.valueOf(motorista.getSexo()));
                intent1.putExtra("email", String.valueOf(motorista.getEmail()));
                startActivity(intent1);

            }
        });

    }

    public void addusuariosCadastrados(String usuarioId){
        this.usuariosCadastrados.add(usuarioId);



    }


    public void incluiusuario(){
        Integer numeropassageiros = essaViagem.getAssentos();
        if(numeropassageiros==0){
            Toast.makeText(InfoMotoristaViagem.this, "Não há mais espaço nessa viagem!", Toast.LENGTH_SHORT).show();

        }else if(this.usuariosCadastrados.contains(user.getUid())){
            Toast.makeText(InfoMotoristaViagem.this, "Você ja participa dessa viagem!", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(InfoMotoristaViagem.this,"Solicitação efetuada com sucesso!",Toast.LENGTH_SHORT).show();
            essaViagem.setAssentos(numeropassageiros-1);
            databaseReferenceViagem.child(viagemId).setValue(essaViagem);
            databaseReferenceViagemUsuario.child(esseUsuario.getId()).setValue(esseUsuario);
            databaseReferenceUsuarioViagem.child(user.getUid()).child(viagemId).setValue(essaViagem);
            Intent intent = new Intent(InfoMotoristaViagem.this, TelaInicial.class);
            startActivity(intent);

        }



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
       maaisInformacoes = findViewById(R.id.btnMaisInfoMotorista);

    }

    public void voltarListaMotorista(View view){

        finish();

    }
    
}
