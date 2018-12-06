package projetosendwithmemotorista.sendwithmemotorista.Activity.Viagens;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import projetosendwithmemotorista.sendwithmemotorista.R;

public class AdapterEncomendas extends BaseAdapter {


    private ArrayList<Encomendas> listaEncomendas = new ArrayList<>();
    private final Activity act;

    public AdapterEncomendas(ArrayList<Encomendas> encomendas, Activity act) {
        this.listaEncomendas = encomendas;
        this.act = act;
    }

    @Override
    public int getCount() {
        return listaEncomendas.size();
    }

    @Override
    public Object getItem(int position) {
        return listaEncomendas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = act.getLayoutInflater()
                .inflate(R.layout.adapter_encomendas, parent, false);


        Encomendas u = listaEncomendas.get(position);

        TextView cidadeDestino = (TextView)
                view.findViewById(R.id.adapterEncomendasCidadeDest);

        TextView cidadeOrigem = (TextView)
                view.findViewById(R.id.adapterEncomendasCidadeOrigem);


        TextView nomeObjeto = (TextView)
                view.findViewById(R.id.adapterEncomendasNomeObjeto);

        TextView descObjeto = (TextView)
                view.findViewById(R.id.adapterEncomendasDescObjeto);




        cidadeDestino.setText(u.getCidadeDestino());
        cidadeOrigem.setText(u.getCidadeOrigem());
        nomeObjeto.setText(u.getNomeObjeto());
        descObjeto.setText(u.getDescricao());



        return view;
    }




}

