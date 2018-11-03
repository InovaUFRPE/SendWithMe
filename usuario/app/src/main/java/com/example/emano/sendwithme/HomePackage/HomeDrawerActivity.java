package com.example.emano.sendwithme.HomePackage;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.Button;
import android.widget.TextView;

import com.example.emano.sendwithme.PedidoPackage.CadastrarPedido;
import com.example.emano.sendwithme.PerfilPackage.PerfilActivity;
import com.example.emano.sendwithme.R;
import com.example.emano.sendwithme.UsuarioPackage.Usuario;
import com.example.emano.sendwithme.ViagemPackage.CadastroViagem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

import java.util.ArrayList;

import util.DesenhaRotaTask;

public class HomeDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView textoEntrada;
    private TextView nomeUsuario;
    private TextView emailUsuario;
    private FloatingActionButton fabTracarRota;
    private FirebaseAuth mAuth;
    private DatabaseReference dataBaseReferencia = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference usuarioReferencia = dataBaseReferencia.child("Usuarios");
    private DatabaseReference usuarioReferencia2;
    private View hView;
    private MapView mapa;
    private GeoPoint pontoInicial;
    private GeoPoint pontoFinal;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.fabTracarRota = (FloatingActionButton) findViewById(R.id.fbRotaID);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        hView = navigationView.getHeaderView(0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED ||

                    ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                String[] permissoes = {Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE};

                requestPermissions(permissoes, 1);

            }

        }

        //Pega o mapa adicionada no arquivo activity_main.xml
        this.mapa = (MapView) findViewById(R.id.mapaId);
//Fonte de imagens
        this.mapa.setTileSource(TileSourceFactory.MAPNIK);

//Cria um ponto de referência com base na latitude e longitude
        this.pontoInicial = new GeoPoint(-7.8031351,-35.2372257);

        IMapController mapController = this.mapa.getController();
//Faz zoom no mapa
        mapController.setZoom(15);
//Centraliza o mapa no ponto de referência
        mapController.setCenter(this.pontoInicial);

//Cria um marcador no mapa
        Marker startMarker = new Marker(this.mapa);
        startMarker.setPosition(this.pontoInicial);
        startMarker.setTitle("Ponto Inicial");
//Posição do ícone
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        mapa.getOverlays().add(startMarker);

        this.pontoFinal = new GeoPoint(-7.8450178,-35.243718);
        Marker endMarker = new Marker(mapa);
        endMarker.setPosition(this.pontoFinal);
        endMarker.setTitle("Ponto Final");
//Posição do ícone
        endMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        mapa.getOverlays().add(endMarker);


        /// classe para nome
        /*
        * FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }
        * */
        //textoEntrada = findViewById(R.id.textoEntradaId2);
        nomeUsuario = hView.findViewById(R.id.nomeUsuarioDrawerId);
        //nomePerfil = hView.findViewById(R.id.nomePerfilHomeId);
        emailUsuario = hView.findViewById(R.id.emailUsuarioDrawerId);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        usuarioReferencia2 = usuarioReferencia.child(uid);
        usuarioReferencia2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                //textoEntrada.setText(usuario.getNome());
                nomeUsuario.setText(usuario.getNome());
                emailUsuario.setText(usuario.getEmail());
                //textoEntrada.setText("TELA MAPA HOME");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ///

        this.fabTracarRota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tracarRota();
            }
        });

    }


    public void tracarRota(){
        ArrayList<GeoPoint> waypoints = new ArrayList<GeoPoint>();
        waypoints.add(this.pontoInicial);
        waypoints.add(this.pontoFinal);

//Cria o objeto gerenciador de rotas
        RoadManager roadManager = new OSRMRoadManager(this);
        Road road = null;
//        Road road = roadManager.getRoad(waypoints);
        try {
            //Chama a classe(DesenhaRotaTask) que executa tarefas assincronas, passa os pontos de referências
            //para a classe DesenhaRotaTask traçar a rota
            road = new DesenhaRotaTask(waypoints, roadManager).execute(roadManager).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

//Desenha a rota
        Polyline roadOverlay = RoadManager.buildRoadOverlay(road);
//Adiciona a rota no mapa
        this.mapa.getOverlays().add(roadOverlay);
//atualiza o mapa
        this.mapa.invalidate();
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
            startActivity(new Intent(HomeDrawerActivity.this, PerfilActivity.class));

        } else if (id == R.id.nav_sair) {
            logout();

        } else if (id == R.id.nav_pedido) {

            startActivity(new Intent(HomeDrawerActivity.this, CadastrarPedido.class));

        } else if (id == R.id.nav_viagem) {

            startActivity(new Intent(HomeDrawerActivity.this, CadastroViagem.class));

        } else if (id == R.id.nav_home_default) {
            startActivity(new Intent(HomeDrawerActivity.this, BlankActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout() {
        //Sessao.instance.reset();
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(HomeDrawerActivity.this, LoginActivity.class));
        HomeDrawerActivity.this.finish();
    }

}
