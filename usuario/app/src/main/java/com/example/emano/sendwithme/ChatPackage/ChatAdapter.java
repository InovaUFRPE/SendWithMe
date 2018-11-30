package com.example.emano.sendwithme.ChatPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.example.emano.sendwithme.R;

import com.github.library.bubbleview.BubbleTextView;

import java.util.ArrayList;

public class ChatAdapter extends ArrayAdapter<Mensagem> {
    private final ArrayList<Mensagem> mensagemList;
    private final Context context;
    private LayoutInflater layoutInflater;
    private String idUser;


    public ChatAdapter(Context context, ArrayList<Mensagem> usuarioList, String idUserSed){

        super(context, R.layout.list_item_message_send, usuarioList);
        this.context = context;
        this.mensagemList = usuarioList;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.idUser = idUserSed;

    }

    public View getView(int position, View convertView, ViewGroup parent){
        //Auxiliar auxiliar = new Auxiliar(context);

        View view = convertView;
        if(mensagemList.get(position).getIdUsuario().equals(idUser))
            view = layoutInflater.inflate(R.layout.list_item_message_send, null);
        else
            view = layoutInflater.inflate(R.layout.list_item_message_recv,null);
        BubbleTextView textMessage = (BubbleTextView)view.findViewById(R.id.text_message);
        textMessage.setText(mensagemList.get(position).getMensagem());
        return view;

    }
}