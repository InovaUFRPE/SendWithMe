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

    private final ArrayList<EncomendaSelecionada> encomendas;
    private final Context context;

    public ListarEncomendasSelecionadasAdapter(Context context, ArrayList<EncomendaSelecionada> encomendas){

        super(context, R.layout.linha_encomenda2, encomendas);
        this.context = context;
        this.encomendas = encomendas;

    }

    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha_encomenda2, parent, false);
        TextView nome = (TextView) rowView.findViewById(R.id.txtNomeEncomendaLista);
        TextView origem = (TextView) rowView.findViewById(R.id.txtOrigemEncomendaLista);
        TextView destino = (TextView) rowView.findViewById(R.id.txtDestinoEncomendaLista);

        nome.setText(encomendas.get(position).getNomeObjeto());
        origem.setText(encomendas.get(position).getCidadeOri());
        destino.setText(encomendas.get(position).getCidadeDest());

        return rowView;

    }

}
