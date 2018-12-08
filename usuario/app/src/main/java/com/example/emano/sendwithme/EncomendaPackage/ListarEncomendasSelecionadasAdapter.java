package com.example.emano.sendwithme.EncomendaPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.emano.sendwithme.R;

import java.util.ArrayList;

public class ListarEncomendasSelecionadasAdapter extends ArrayAdapter<EncomendaSelecionada> {

    private final ArrayList<EncomendaSelecionada> encomendasSelecionadas;
    private final Context context;

    public ListarEncomendasSelecionadasAdapter(Context context, ArrayList<EncomendaSelecionada> encomendasSelecionadas){

        super(context, R.layout.linha_encomenda_selecionada, encomendasSelecionadas);
        this.context = context;
        this.encomendasSelecionadas = encomendasSelecionadas;

    }

    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha_encomenda_selecionada, parent, false);
        TextView nome = (TextView) rowView.findViewById(R.id.txtNomeEncomendaListaSelecionada);
        TextView origem = (TextView) rowView.findViewById(R.id.txtOrigemEncomendaListaSelecionadaInfo);
        TextView destino = (TextView) rowView.findViewById(R.id.txtDestinoEncomendaListaSelecionadaInfo);

        nome.setText(encomendasSelecionadas.get(position).getNomeObjeto());
        origem.setText(encomendasSelecionadas.get(position).getCidadeOri());
        destino.setText(encomendasSelecionadas.get(position).getCidadeDest());

        return rowView;

    }



}
