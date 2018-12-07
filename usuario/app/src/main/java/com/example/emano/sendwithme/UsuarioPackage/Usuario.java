package com.example.emano.sendwithme.UsuarioPackage;

import java.util.ArrayList;

public class Usuario {

    private String id;
    private String nome;
    private String email;
    private String cpf;
    private String senha;
    private ArrayList<String> viagens;

    public Usuario() {
    }

    public Usuario(String nome, String sobrenome, String email, String cpf, String senha) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
    }

    public ArrayList<String> getViagens() {
        return viagens;
    }

    public void setViagens(ArrayList<String> viagens) {
        this.viagens = viagens;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
