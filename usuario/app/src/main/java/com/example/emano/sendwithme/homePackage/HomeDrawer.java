package com.example.emano.sendwithme.homePackage;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.emano.sendwithme.PedidoPackage.ListarPedidos;
import com.example.emano.sendwithme.HomePackage.HomeFragmentMap;
import com.example.emano.sendwithme.perfilPackage.Perfil;
import com.example.emano.sendwithme.R;
import com.example.emano.sendwithme.usuarioPackage.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private TextView nomeUsuario;
    private TextView emailUsuario;
    private FirebaseAuth mAuth;
    private DatabaseReference dataBaseReferencia = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference usuarioReferencia = dataBaseReferencia.child("Usuarios");
    private DatabaseReference usuarioReferencia2;
    private View hView;
    private FragmentManager fragmentManager;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        hView = navigationView.getHeaderView(0);

        setInfoUsuario();
        setFragmentoPadrao();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED ||

                    ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                String[] permissoes = {Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE};

                requestPermissions(permissoes, 1);

            }

        }



    }

    private void setFragmentoPadrao() {
        fragmentManager = getSupportFragmentManager(); //setou

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.add(R.id.containerIdCentral, new HomeFragmentMap(), "MapsFragment");

        transaction.commitAllowingStateLoss();
    }

    private void setInfoUsuario() {
        nomeUsuario = hView.findViewById(R.id.nomeUsuarioDrawerId);
        emailUsuario = hView.findViewById(R.id.emailUsuarioDrawerId);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        usuarioReferencia2 = usuarioReferencia.child(uid);
        usuarioReferencia2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                nomeUsuario.setText(usuario.getNome());
                emailUsuario.setText(usuario.getEmail());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the PerfilActivity/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                // Se a solicitação de permissão foi cancelada o array vem vazio.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permissão cedida, recria a activity para carregar o mapa, só será executado uma vez
                    this.recreate();

                }

            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {


        } else if (id == R.id.nav_perfil) {
            startActivity(new Intent(HomeDrawer.this, Perfil.class));

        } else if (id == R.id.nav_sair) {
            logout();

        }else if (id == R.id.nav_lista_pedidos){
            startActivity(new Intent(HomeDrawer.this, ListarPedidos.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout() {
        //Sessao.instance.reset();
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(HomeDrawer.this, Login.class));
        HomeDrawer.this.finish();
    }

}
