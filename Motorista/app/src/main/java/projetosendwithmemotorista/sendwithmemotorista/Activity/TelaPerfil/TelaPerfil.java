package projetosendwithmemotorista.sendwithmemotorista.Activity.TelaPerfil;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import projetosendwithmemotorista.sendwithmemotorista.Activity.LoginMotorista.LoginActivity;
import projetosendwithmemotorista.sendwithmemotorista.Entidades.Usuarios;
import projetosendwithmemotorista.sendwithmemotorista.Helper.PreferenciasAndroid;
import projetosendwithmemotorista.sendwithmemotorista.R;

public class TelaPerfil extends AppCompatActivity {

    private Button btnExcluirConta;
    private TextView nomePerfil;
    private TextView sobrenomePerfil;
    private TextView emailPerfil;
    private TextView cpfPerfil;
    private TextView dataPerfil;
    private TextView senhaPerfil;
    private Button voltaMapa;
    private DatabaseReference mDatebaseRef;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil);
        setarDadosPerfil();


        btnExcluirConta = (Button) findViewById(R.id.EditarPerfil);
        btnExcluirConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                user.delete();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(TelaPerfil.this, LoginActivity.class));
                TelaPerfil.this.finish();
            }
        });
        voltaMapa = (Button) findViewById(R.id.voltaMapa);
    }



    private void setarDadosPerfil() {

        PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(TelaPerfil.this);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("usuario").child(preferenciasAndroid.getIdentificador());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              Usuarios usuarios = dataSnapshot.getValue(Usuarios.class);
              setView();
              EditarNome();
              EditarSobrenome();
              EditarEmail();
              EditarCpf();
              EditarData();
              EditarSenha();
              nomePerfil.setText(usuarios.getNome());
              sobrenomePerfil.setText(usuarios.getSobrenome());
              emailPerfil.setText(usuarios.getEmail());
              cpfPerfil.setText(usuarios.getCpf());
              dataPerfil.setText(usuarios.getNascimento());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void setView() {
        nomePerfil = findViewById(R.id.nomePerfilId);
        sobrenomePerfil = findViewById(R.id.sobrenomePerfilId);
        emailPerfil = findViewById(R.id.emailPerfilId);
        cpfPerfil = findViewById(R.id.cpfPerfilId);
        dataPerfil = findViewById(R.id.dataPerfilId);
        senhaPerfil = findViewById(R.id.senhaPerfilId);

    }

    private void EditarNome(){
        nomePerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(TelaPerfil.this);
                View alertview = getLayoutInflater().inflate(R.layout.dialogo_editar_perfil, null);
                TextView alertTitulo = alertview.findViewById(R.id.textViewEditar);
                final EditText editTextAlterar = alertview.findViewById(R.id.editTextAlertConf);
                Button buttonConfirmar = alertview.findViewById(R.id.bttEdicao);
                Button buttonCancelar = alertview.findViewById(R.id.bttCancelarEdicao);

                alertTitulo.setText("Digite um novo Nome: ");
                buttonConfirmar.setText("Editar");
                buttonCancelar.setText("Cancelar");
                alertDialog.setView(alertview);
                final AlertDialog dialog = alertDialog.create();
                dialog.show();

                buttonCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });







                buttonConfirmar.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                        if (!editTextAlterar.toString().isEmpty()) {

                            PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(TelaPerfil.this);
                            mDatebaseRef = FirebaseDatabase.getInstance().getReference("usuario").child(preferenciasAndroid.getIdentificador()).child("nome"); //mudar o child para cada informacao correspondente
                            mDatebaseRef.setValue(editTextAlterar.getText().toString());
                            setarDadosPerfil();
                            Toast.makeText(TelaPerfil.this, "Nome alterado", Toast.LENGTH_LONG).show();
                            dialog.dismiss();

                        }


                    }
                });


            }
        });

    }
    private void EditarSobrenome(){
        sobrenomePerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(TelaPerfil.this);
                View alertview = getLayoutInflater().inflate(R.layout.dialogo_editar_perfil, null);
                TextView alertTitulo = alertview.findViewById(R.id.textViewEditar);
                final EditText editTextAlterar = alertview.findViewById(R.id.editTextAlertConf);
                Button buttonConfirmar = alertview.findViewById(R.id.bttEdicao);
                Button buttonCancelar = alertview.findViewById(R.id.bttCancelarEdicao);

                alertTitulo.setText("Digite um novo Sobrenome: ");
                buttonConfirmar.setText("Editar");
                buttonCancelar.setText("Cancelar");
                alertDialog.setView(alertview);
                final AlertDialog dialog = alertDialog.create();
                dialog.show();

                buttonCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });
                buttonConfirmar.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                        if (!editTextAlterar.toString().isEmpty()) {

                            PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(TelaPerfil.this);
                            mDatebaseRef = FirebaseDatabase.getInstance().getReference("usuario").child(preferenciasAndroid.getIdentificador()).child("sobrenome"); //mudar o child para cada informacao correspondente
                            mDatebaseRef.setValue(editTextAlterar.getText().toString());
                            setarDadosPerfil();
                            Toast.makeText(TelaPerfil.this, "SobreNome alterado", Toast.LENGTH_LONG).show();
                            dialog.dismiss();

                        }


                    }
                });
            }
        });
    }


    private void EditarEmail(){
        emailPerfil.setOnClickListener(new View.OnClickListener() { //Alterar
            @Override
            public void onClick(View v) {
                Toast.makeText(TelaPerfil.this, "Você não pode alterar o Email", Toast.LENGTH_LONG).show(); //Alterar


            }
        });

    }

    private void EditarCpf(){ //chamarMetodo no oncreate
        cpfPerfil.setOnClickListener(new View.OnClickListener() { //Alterar
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(TelaPerfil.this);
                View alertview = getLayoutInflater().inflate(R.layout.dialogo_editar_perfil, null);
                TextView alertTitulo = alertview.findViewById(R.id.textViewEditar);
                final EditText editTextAlterar = alertview.findViewById(R.id.editTextAlertConf);
                Button buttonConfirmar = alertview.findViewById(R.id.bttEdicao);
                Button buttonCancelar = alertview.findViewById(R.id.bttCancelarEdicao);

                alertTitulo.setText("Digite um novo CPF: "); //Alterar
                buttonConfirmar.setText("Editar");
                buttonCancelar.setText("Cancelar");
                alertDialog.setView(alertview);
                final AlertDialog dialog = alertDialog.create();
                dialog.show();

                buttonCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });







                buttonConfirmar.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                        if (!editTextAlterar.toString().isEmpty()) {

                            PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(TelaPerfil.this);
                            mDatebaseRef = FirebaseDatabase.getInstance().getReference("usuario").child(preferenciasAndroid.getIdentificador()).child("cpf"); //mudar o child para cada informacao correspondente
                            mDatebaseRef.setValue(editTextAlterar.getText().toString());
                            setarDadosPerfil();
                            Toast.makeText(TelaPerfil.this, "CPF alterado", Toast.LENGTH_LONG).show(); //Alterar
                            dialog.dismiss();

                        }


                    }
                });


            }
        });

    }

    private void EditarData(){
        dataPerfil.setOnClickListener(new View.OnClickListener() { //Alterar
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(TelaPerfil.this);
                View alertview = getLayoutInflater().inflate(R.layout.dialogo_editar_perfil, null);
                TextView alertTitulo = alertview.findViewById(R.id.textViewEditar);
                final EditText editTextAlterar = alertview.findViewById(R.id.editTextAlertConf);
                Button buttonConfirmar = alertview.findViewById(R.id.bttEdicao);
                Button buttonCancelar = alertview.findViewById(R.id.bttCancelarEdicao);

                alertTitulo.setText("Digite uma nova data de nascimento: "); //Alterar
                buttonConfirmar.setText("Editar");
                buttonCancelar.setText("Cancelar");
                alertDialog.setView(alertview);
                final AlertDialog dialog = alertDialog.create();
                dialog.show();

                buttonCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });







                buttonConfirmar.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                        if (!editTextAlterar.toString().isEmpty()) {

                            PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(TelaPerfil.this);
                            mDatebaseRef = FirebaseDatabase.getInstance().getReference("usuario").child(preferenciasAndroid.getIdentificador()).child("nascimento"); //mudar o child para cada informacao correspondente
                            mDatebaseRef.setValue(editTextAlterar.getText().toString());
                            setarDadosPerfil();
                            Toast.makeText(TelaPerfil.this, "Data de nascimento alterada", Toast.LENGTH_LONG).show(); //Alterar
                            dialog.dismiss();

                        }


                    }
                });


            }
        });

    }


    private void EditarSenha(){//chmar no oncreate
        senhaPerfil.setOnClickListener(new View.OnClickListener() { //Alterar
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(TelaPerfil.this);
                View alertview = getLayoutInflater().inflate(R.layout.dialogo_editar_perfil, null);
                TextView alertTitulo = alertview.findViewById(R.id.textViewEditar);
                final EditText editTextAlterar = alertview.findViewById(R.id.editTextAlertConf);
                Button buttonConfirmar = alertview.findViewById(R.id.bttEdicao);
                Button buttonCancelar = alertview.findViewById(R.id.bttCancelarEdicao);

                alertTitulo.setText("Digite uma nova senha: "); //Alterar
                buttonConfirmar.setText("Editar");
                buttonCancelar.setText("Cancelar");
                alertDialog.setView(alertview);
                final AlertDialog dialog = alertDialog.create();
                dialog.show();

                buttonCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });







                buttonConfirmar.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                        if (!editTextAlterar.toString().isEmpty()) {

                            PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(TelaPerfil.this);
                            mDatebaseRef = FirebaseDatabase.getInstance().getReference("usuario").child(preferenciasAndroid.getIdentificador()).child("senha"); //mudar o child para cada informacao correspondente
                            mDatebaseRef.setValue(editTextAlterar.getText().toString());
                            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            user.updatePassword(editTextAlterar.getText().toString());
                            setarDadosPerfil();
                            Toast.makeText(TelaPerfil.this, "Senha alterada", Toast.LENGTH_LONG).show();
                            dialog.dismiss();

                        }


                    }
                });


            }
        });

    }

}
