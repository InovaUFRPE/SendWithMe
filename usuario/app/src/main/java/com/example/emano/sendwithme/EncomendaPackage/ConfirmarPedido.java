package com.example.emano.sendwithme.EncomendaPackage;

import android.support.v7.app.AppCompatActivity;

public class ConfirmarPedido extends AppCompatActivity { //apaga
}

    /*Button confirma;
    EditText titulo;
    EditText nome;
    String titulo1;
    String nome1;
    private DatabaseReference databaseReference;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    Pedido pedido = new Pedido();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_pedido);

    }

    public void ConfirmarPedido(View view) {

        setView();

        //Intent novaIntent = getIntent();
        Bundle novaIntent = getIntent().getExtras();

        String origem = novaIntent.getString("origem");
        String destino = novaIntent.getString("destino");

        pedido.setOrigem(origem);
        pedido.setDestino(destino);

        if (titulo1.isEmpty()) {
            titulo.setError("Campo em branco");
        } else if (nome1.isEmpty()) {
            nome.setError("Campo em branco");
        } else {

            pedido.setTitulo(titulo.getText().toString());
            pedido.setObjeto(nome.getText().toString());
            pedido.setIdUsuario(user.getUid());

            databaseReference = FirebaseDatabase.getInstance().getReference("Pedidos").child(user.getUid());

            databaseReference.push().setValue(pedido);

            Intent intent1 = new Intent(getApplicationContext(), HomeDrawer.class);
            startActivity(intent1);

            Toast.makeText(getApplicationContext(), "Pedido Salvo", Toast.LENGTH_LONG).show();


        }


    }

    private void setView() {

       confirma = (Button) findViewById(R.id.btnConfirmarPedido);
       titulo = (EditText) findViewById(R.id.editTituloPedido);
       nome = (EditText) findViewById(R.id.editNomeObjeto);
       titulo1 = titulo.getText().toString();
       nome1 = nome.getText().toString();
    }

}*/
