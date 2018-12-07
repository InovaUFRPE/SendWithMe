package projetosendwithmemotorista.sendwithmemotorista.Entidades;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

import projetosendwithmemotorista.sendwithmemotorista.DAO.ConfiguracaoFirebase;

public class Usuarios {

    private String id;
    private String email;
    private String senha;
    private String nome;
    private String sobrenome;
    private String datanascimento;
    private String cpf;
    private String sexo;

    public Usuarios() {
    }

    public void salvar(){
        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getReferenceFirebase();
        referenciaFirebase.child("usuario").child(String.valueOf(getId())).setValue(this);
    }

    @Exclude

    public Map<String, Object> toMap() {
        HashMap<String, Object> hashMapUsuario = new HashMap<>();

        hashMapUsuario.put("id", getId());
        hashMapUsuario.put("email", getEmail());
        hashMapUsuario.put("senha", getSenha());
        hashMapUsuario.put("nome", getNome());
        hashMapUsuario.put("sobrenome", getSobrenome());
        hashMapUsuario.put("datanascimento", getNascimento());
        hashMapUsuario.put("cpf", getCpf());
        hashMapUsuario.put("sexo", getSexo());

        return hashMapUsuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getNascimento() {
        return datanascimento;
    }

    public void setNascimento(String nascimento) {
        this.datanascimento = nascimento;
    }

    public String getCpf() { return  cpf;}

    public void setCpf(String cpf) {this.cpf = cpf;}

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
