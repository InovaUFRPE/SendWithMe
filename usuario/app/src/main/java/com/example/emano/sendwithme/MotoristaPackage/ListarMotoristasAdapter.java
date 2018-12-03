package com.example.emano.sendwithme.MotoristaPackage;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.emano.sendwithme.R;

import java.util.ArrayList;

public class ListarMotoristasAdapter extends ArrayAdapter<Motorista> {

    private final ArrayList<Motorista> motoristas;
    private final Context context;


    public ListarMotoristasAdapter(Context context, ArrayList<Motorista> motoristas){

        super(context, R.layout.linha_pedidos, motoristas);
        this.context = context;
        this.motoristas = motoristas;

    }

    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha_motorista_nome_simples_nova, parent, false);
        TextView nome = (TextView) rowView.findViewById(R.id.nomeMotorista);

        nome.setText(motoristas.get(position).getNome()+ " "+ motoristas.get(position).getSobrenome());

        return rowView;

    }

}
