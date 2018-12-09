package com.example.emano.sendwithme.motoristaPackage;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.emano.sendwithme.R;
import com.example.emano.sendwithme.ViagemPackage.Viagem;

import java.util.ArrayList;

public class ListarMotoristasAdapter2 extends ArrayAdapter<Motorista> {

    private final ArrayList<Motorista> motoristas;
    private final ArrayList<Viagem> viagem;
    private final Context context;


    public ListarMotoristasAdapter2(Context context, ArrayList<Motorista> motoristas, ArrayList<Viagem> viagem){

        super(context, R.layout.linha_pedidos, motoristas);
        this.context = context;
        this.motoristas = motoristas;
        this.viagem = viagem;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha_lista_motorista_nova, parent, false);
        TextView nome = (TextView) rowView.findViewById(R.id.nomeMotorista);
        TextView origem = (TextView) rowView.findViewById(R.id.origemSet);
        TextView destino = (TextView) rowView.findViewById(R.id.destinoSet);

        nome.setText(motoristas.get(position).getNome()+ " "+ motoristas.get(position).getSobrenome());
        origem.setText(viagem.get(position).getCidade());
        destino.setText(viagem.get(position).getCidadedest());


        return rowView;

    }

}
