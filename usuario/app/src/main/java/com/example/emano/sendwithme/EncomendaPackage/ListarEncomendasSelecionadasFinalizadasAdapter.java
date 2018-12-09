package com.example.emano.sendwithme.EncomendaPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.emano.sendwithme.R;

import java.util.ArrayList;

public class ListarEncomendasSelecionadasFinalizadasAdapter extends ArrayAdapter<EncomendaSelecionada> {

    private final ArrayList<EncomendaSelecionada> encomendaSelecionadasFinalizadas;
    private final Context context;

    public ListarEncomendasSelecionadasFinalizadasAdapter(Context context, ArrayList<EncomendaSelecionada> encomendaSelecionadasFinalizadas){

        super(context, R.layout.linha_encomendas_selecionadas_finalizadas, encomendaSelecionadasFinalizadas);
        this.context = context;
        this.encomendaSelecionadasFinalizadas = encomendaSelecionadasFinalizadas;

    }

    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha_encomendas_selecionadas_finalizadas, parent, false);
        TextView nome = (TextView) rowView.findViewById(R.id.txtNomeEncomendaListaSelecionadaFinalizada);
        TextView origem = (TextView) rowView.findViewById(R.id.txtOrigemEncomendaListaSelecionadaInfoFinalizada);
        TextView destino = (TextView) rowView.findViewById(R.id.txtDestinoEncomendaListaSelecionadaInfoFinalizada);

        nome.setText(encomendaSelecionadasFinalizadas.get(position).getNomeObjeto());
        origem.setText(encomendaSelecionadasFinalizadas.get(position).getCidadeOri());
        destino.setText(encomendaSelecionadasFinalizadas.get(position).getCidadeDest());

        return rowView;

    }

}
