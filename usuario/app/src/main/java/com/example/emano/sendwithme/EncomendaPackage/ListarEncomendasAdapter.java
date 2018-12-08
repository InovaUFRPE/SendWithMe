package com.example.emano.sendwithme.EncomendaPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.emano.sendwithme.R;

import java.util.ArrayList;

public class ListarEncomendasAdapter extends ArrayAdapter<Encomenda> {

    private final ArrayList<Encomenda> encomendas;
    private final Context context;

    public ListarEncomendasAdapter(Context context, ArrayList<Encomenda> encomendas){

        super(context, R.layout.linha_encomenda2, encomendas);
        this.context = context;
        this.encomendas = encomendas;

    }

    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha_encomenda2, parent, false);
        TextView nome = (TextView) rowView.findViewById(R.id.txtNomeEncomendaLista);
        TextView origem = (TextView) rowView.findViewById(R.id.txtOrigemEncomendaListaInfo);
        TextView destino = (TextView) rowView.findViewById(R.id.txtDestinoEncomendaListaInfo);

        nome.setText(encomendas.get(position).getNomeObjeto());
        origem.setText(encomendas.get(position).getEnderecoOrigem());
        destino.setText(encomendas.get(position).getEndrerecoDestino());

        return rowView;

    }

}
