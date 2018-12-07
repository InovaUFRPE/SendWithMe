package projetosendwithmemotorista.sendwithmemotorista.Activity.Viagens;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

import projetosendwithmemotorista.sendwithmemotorista.Activity.TelaPerfil.TelaPerfil;
import projetosendwithmemotorista.sendwithmemotorista.DAO.ConfiguracaoFirebase;
import projetosendwithmemotorista.sendwithmemotorista.Helper.Base64Custom;
import projetosendwithmemotorista.sendwithmemotorista.Helper.PreferenciasAndroid;
import projetosendwithmemotorista.sendwithmemotorista.Helper.preferencias;
import projetosendwithmemotorista.sendwithmemotorista.R;

public class ListaEncomendas extends AppCompatActivity {

    private Button btnavancar;
    private String cidadedest;
    private String endereçodest;
    private String cidade;
    private String endereço;
    private String data;
    private String hora;
    private String encomendas;
    private Integer assentos;
    private TextView encomendainfo;
    private EditText edtassdisp;
    private CheckBox encomendabox;
    private Button btnVoltar;


    private ListView listaencomendasListView;
    private ArrayList<String> lista;
    private ArrayList<Encomendas>listaEncomendas;
    private ArrayList<Encomendas> listaEncomendasSel = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ArrayList<String>listaIdUsuario;
    private DatabaseReference databaseReference;
    private AdapterEncomendas adapterEncomendas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_encomendas);
        btnavancar = findViewById(R.id.btnavancar);
        btnVoltar = findViewById(R.id.Btnavanc);
        listaencomendasListView = findViewById(R.id.listaencomendas);
        setardadoslistaencomendas();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            cidade = extras.getString("cidade");
            endereço = extras.getString("endereço");
            data = extras.getString("data");
            hora = extras.getString("hora");
            cidadedest = extras.getString("cidadedest");
            endereçodest = extras.getString("endereçodest");
            assentos = extras.getInt("assentos");
            encomendas = extras.getString("encomendas");
        }

        btnavancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                funcacanvar();
            }

        });



         btnVoltar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent voltar = new Intent(ListaEncomendas.this, Viagemconfig.class);
            startActivity(voltar);
            finish();
        }

    });

}

    private void setardadoslistaencomendas(){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Encomendas");
        lista = new ArrayList<>();
        //  adapter = new ArrayAdapter<String>(this,R.layout.infoviagens,R.id.textView8, lista);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    // Encomendas encomendas = ds.getValue(Encomendas.class);
                    lista.add(ds.getKey());
                    setardadoslistaencomendas2();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void setardadoslistaencomendas2() {

        for (String lista1 : lista) {

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Encomendas").child(lista1);
            lista = new ArrayList<>();
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Encomendas encomendas = ds.getValue(Encomendas.class);
                        String cidadeDest = String.valueOf(encomendas.getCidadeDestino());
                        String cidadeOri = String.valueOf(encomendas.getCidadeOrigem());
                        String nomeObjeto = String.valueOf(encomendas.getNomeObjeto());
                        String descrObjeto = String.valueOf(encomendas.getDescricao());
                        String idUsuario = String.valueOf(encomendas.getIdUsuario());
                        String idEncomenda = ds.getKey();

                        if (cidadeDest.equals(cidadedest)) {

                            Encomendas encomendas1 = new Encomendas();
                            encomendas1.setCidadeOrigem(cidadeOri);
                            encomendas1.setCidadeDestino(cidadeDest);
                            encomendas1.setNomeObjeto(nomeObjeto);
                            encomendas1.setDescricao(descrObjeto);
                            encomendas1.setIdUsuario(idUsuario);
                            encomendas1.setIdEncomenda(idEncomenda);


                            listaEncomendasSel.add(encomendas1);


                            adapterEncomendas = new AdapterEncomendas(listaEncomendasSel, ListaEncomendas.this);
                            listaencomendasListView.setAdapter(adapterEncomendas);


                            listaencomendasListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                @Override
                                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                                    Encomendas s = (Encomendas) parent.getAdapter().getItem(position);

                                    adicionarEncomendas(s);

                                    return true;

                                }
                            });

                        }

                    }

                }





                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }



    private void funcacanvar() {


        Intent i = new Intent(ListaEncomendas.this, Viagemrevisao.class);
        i.putExtra("cidade", cidade);
        i.putExtra("endereço", endereço);
        i.putExtra("data", data);
        i.putExtra("hora", hora);
        i.putExtra("cidadedest", cidadedest);
        i.putExtra("endereçodest", endereçodest);
        i.putExtra("assentos", assentos);
        i.putExtra("encomendas", encomendas);
        startActivity(i);
    }

    private void adicionarEncomendas(final Encomendas s) {
        // String emaildoamiginho = s.getEmail();
        // final String emaiamiguinhocodificado = Codificador.codificador(emaildoamiginho);


        final CharSequence[] escolha = {"Sim", "Não"};
        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaEncomendas.this);
        final PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(ListaEncomendas.this);
        alerta.setItems(escolha, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String opcao = (String) escolha[i];
                if (opcao.equals(("Sim"))) {
                    //ADICIONAR no firebase ANTES
                    EncomendasSelecionadas v = new EncomendasSelecionadas();
                    // v.setViagemUID(UUID.randomUUID().toString());
                    v.setCidadeDest(s.getCidadeDestino());
                    v.setCidadeOri(s.getCidadeOrigem());
                    v.setNomeObjeto(s.getNomeObjeto());
                    v.setDescricao(s.getDescricao());
                    v.setIdEncomenda(s.getIdEncomenda());
                    v.setIdUsuario(s.getIdUsuario());
                    v.setIdMotorista(preferenciasAndroid.getIdentificador());
                    v.setCidadeOrigemViagem(cidade);
                    v.setDataViagem(data);
                    v.setHoraViagem(hora);
                    String idAleatorio = Base64Custom.codificarBase64(UUID.randomUUID().toString());
                    DatabaseReference referenceFirebase = ConfiguracaoFirebase.getReferenceFirebase();
                    referenceFirebase.child("EncomendasSelecionadas").child(idAleatorio).setValue(v);


                    //PARA APAGAR USAR IDENCOMENDA
                     databaseReference = FirebaseDatabase.getInstance().getReference().child("Encomendas").child(v.getIdUsuario()).child(v.getIdEncomenda());
                     databaseReference.removeValue();
                    Toast.makeText(getApplicationContext(),"Encomenda aceita", Toast.LENGTH_SHORT).show();


                } else if (opcao.equals(("Não"))) {
                    finish();
                    Toast.makeText(getApplicationContext(),"Encomenda não aceita", Toast.LENGTH_SHORT).show();
                    // dialogInterface.cancel();
                }
            }
        });
        alerta.setTitle("Aceitar Encomenda ?");
        AlertDialog aviso = alerta.create();
        aviso.show();




    }



}
