package com.example.emano.sendwithme.PedidoPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.emano.sendwithme.R;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class ListarPedidosAdapter extends ArrayAdapter<Pedido> {

    private final ArrayList<Pedido> pedidos;
    private final Context context;

    public ListarPedidosAdapter(Context context, ArrayList<Pedido> pedidos){

        super(context, R.layout.linha_pedidos, pedidos);
        this.context = context;
        this.pedidos = pedidos;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha_pedidos, parent, false);
        TextView origem = (TextView) rowView.findViewById(R.id.txtOrigem);
        TextView destino = (TextView) rowView.findViewById(R.id.txtDestino);

        origem.setText(pedidos.get(position).getOrigem());
        destino.setText(pedidos.get(position).getDestino());

        return rowView;

    }

}