package projetosendwithmemotorista.sendwithmemotorista.Activity.Viagens;

import java.util.ArrayList;

public class ViagemFB {

    private String viagemUID;

    private String cidadedest;
    private String endereçodest;
    private String cidade;
    private String endereço;
    private String data;
    private String hora;
    private String encomendas;
    private String usuarioid;
    private ArrayList listapass;


    public ViagemFB(){


    }
    public ArrayList getListapass() {
        return listapass;
    }

    public void setListapass(ArrayList listapass) {
        this.listapass = listapass;
    }

    public String getViagemUID() {
        return cidadedest;
    }

    public void setViagemUID(String cidadedest) {
        this.cidadedest = cidadedest;
    }

    public String getCidadedest() {
        return cidadedest;
    }

    public void setCidadedest(String cidadedest) {
        this.cidadedest = cidadedest;
    }

    public String getEndereçodest() {
        return endereçodest;
    }

    public void setEndereçodest(String endereçodest) {
        this.endereçodest = endereçodest;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEncomendas() {
        return encomendas;
    }

    public void setEncomendas(String encomendas) {
        this.encomendas = encomendas;
    }

    public String getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(String usuarioid) {
        this.usuarioid = usuarioid;
    }
}
