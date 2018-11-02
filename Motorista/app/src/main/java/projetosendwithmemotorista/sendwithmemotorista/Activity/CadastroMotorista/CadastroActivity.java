package projetosendwithmemotorista.sendwithmemotorista.Activity.CadastroMotorista;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import projetosendwithmemotorista.sendwithmemotorista.Activity.LoginMotorista.LoginActivity;
import projetosendwithmemotorista.sendwithmemotorista.DAO.ConfiguracaoFirebase;
import projetosendwithmemotorista.sendwithmemotorista.Entidades.Usuarios;
import projetosendwithmemotorista.sendwithmemotorista.Helper.Base64Custom;
import projetosendwithmemotorista.sendwithmemotorista.Helper.PreferenciasAndroid;
import projetosendwithmemotorista.sendwithmemotorista.R;

public class CadastroActivity extends AppCompatActivity {

    private EditText edtCadEmail;
    private EditText edtCadNome;
    private EditText edtCadSobrenome;
    private EditText edtCadSenha;
    private EditText edtCadConfirmarSenha;
    private EditText edtCadDataNascimento;
    private EditText edtCadCpf;
    private RadioButton rbMasculino;
    private RadioButton rbFeminino;
    private Button btnSalvar;
    private Usuarios usuarios;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtCadEmail = (EditText)findViewById(R.id.edtCadEmail);
        edtCadCpf = (EditText)findViewById(R.id.edtCadCpf);
        edtCadNome = (EditText)findViewById(R.id.edtCadNome);
        edtCadSobrenome = (EditText)findViewById(R.id.edtCadSobrenome);
        edtCadSenha = (EditText)findViewById(R.id.edtCadSenha);
        edtCadConfirmarSenha = (EditText)findViewById(R.id.edtCadConfirmarSenha);
        edtCadDataNascimento = (EditText)findViewById(R.id.edtCadDataNascimento);
        rbFeminino = (RadioButton) findViewById(R.id.rbFeminino);
        rbMasculino = (RadioButton) findViewById(R.id.rbMasculino);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);



        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtCadSenha.getText().toString().equals(edtCadConfirmarSenha.getText().toString()) && validarCampos()){
                    usuarios = new Usuarios();
                    usuarios.setNome(edtCadNome.getText().toString());
                    usuarios.setEmail(edtCadEmail.getText().toString());
                    usuarios.setCpf(edtCadCpf.getText().toString());
                    usuarios.setSenha(edtCadSenha.getText().toString());
                    usuarios.setNascimento(edtCadDataNascimento.getText().toString());
                    usuarios.setSobrenome(edtCadSobrenome.getText().toString());


                    if (rbFeminino.isChecked()) {
                        usuarios.setSexo("Feminino");
                    } else {
                        usuarios.setSexo("Masculino");
                    }

                    cadastrarUsuario();

                }else {
                    Toast.makeText(CadastroActivity.this, "verifique se os campos foram preenchidos corretamente!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void cadastrarUsuario(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuarios.getEmail(),
                usuarios.getSenha()
        ).addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CadastroActivity.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();

                    String identificadorUsuario = Base64Custom.codificarBase64(usuarios.getEmail());
                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    usuarios.setId(identificadorUsuario);
                    usuarios.salvar();

                    PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(CadastroActivity.this);
                    preferenciasAndroid.salvarUsuarioPreferencias(identificadorUsuario, usuarios.getNome());

                    abrirLoginUsuario();
                }else {
                    String erroExcecao = "";

                    try{
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e){
                        erroExcecao = "Digite uma senha mais forte! Dica: No minimo 6 caracteres!";
                    } catch (FirebaseAuthInvalidCredentialsException e){
                        erroExcecao = "E-mail digitado inválido! Digite um novo e-mail!";
                    } catch (FirebaseAuthUserCollisionException e) {
                        erroExcecao = "E=mail já cadastrado no sistema!";
                    } catch (Exception e){
                        erroExcecao = "Erro ao efetuar o cadastro!";
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroActivity.this, "Erro: " + erroExcecao, Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    public void abrirLoginUsuario(){
        Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    public boolean validarCampos() {
        boolean erro = true;
        if (validarCpf()){
            erro = false;
        }
        if (validarNome()){
            erro = false;
        }
        if (validarEmail()){
            erro = false;
        }
        if (validarSobrenome()){
            erro = false;
        }
        if (validarDatadeNascimento()){
            erro = false;
        }
        if (validarSenha()){
            erro = false;
        }
        if (validarConfirmarSenha()){
            erro = false;
        }
        return erro;
    }
    private boolean validarCpf(){
        boolean erro = false;
        String cpfString = edtCadCpf.getText().toString().trim();
        if (cpfString.isEmpty()) {
            erro = true;
            edtCadCpf.setError("Campo Vazio!");
        } else if(cpfString.length() != 11){
            erro = true;
            edtCadCpf.setError("Cpf não contém 11 digitos!");
        }else if (!cpfString.matches("[0-9]+")){
            erro = true;
            edtCadCpf.setError("Cpf não contém apenas números!");
        }
        return erro;
    }
    private boolean validarNome(){
        boolean erro = false;
        String nomeString = edtCadNome.getText().toString().trim();
        if (nomeString.isEmpty()) {
            erro = true;
            edtCadNome.setError("Campo Vazio!");
        }
        return erro;
    }
    private boolean validarEmail(){
        boolean erro = false;
        String emailString = edtCadEmail.getText().toString().trim();
        if (emailString.isEmpty()){
            erro = true;
            edtCadEmail.setError("Campo Vazio!");
        }else{
            String excercao = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
            Pattern pattern = Pattern.compile(excercao);
            Matcher matcher = pattern.matcher(emailString);

            if (!matcher.matches()){
                erro = true;
                edtCadEmail.setError("Email Inválido!");
            }
        }
        return erro;
    }
    private boolean validarSobrenome(){
        boolean erro = false;
        String sobrenomeString = edtCadSobrenome.getText().toString().trim();
        if (sobrenomeString.isEmpty()) {
            erro = true;
            edtCadSobrenome.setError("Campo Vazio!");
        }
        return erro;
    }
    private boolean validarDatadeNascimento(){
        boolean erro = false;
        String datanascimentoString = edtCadDataNascimento.getText().toString().trim();
        if (datanascimentoString.isEmpty()) {
            erro = true;
            edtCadDataNascimento.setError("Campo Vazio!");
        }
        return erro;
    }
    private boolean validarSenha(){
        boolean erro = false;
        String senhaString = edtCadSenha.getText().toString().trim();
        if (senhaString.isEmpty()) {
            erro = true;
            edtCadSenha.setError("Campo Vazio!");
        }
        return erro;
    }
    private boolean validarConfirmarSenha(){
        boolean erro = false;
        String confirmasenhaString = edtCadConfirmarSenha.getText().toString().trim();
        if (confirmasenhaString.isEmpty()) {
            erro = true;
            edtCadConfirmarSenha.setError("Campo Vazio!");
        }
        return erro;
    }

}
