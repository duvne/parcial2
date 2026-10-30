package com.example.menunavegacion;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import adaptadores.homeadaptador;
import clases.productos;

public class MainActivity extends AppCompatActivity {

    EditText edtUsuario, edtContraseña;
    Button btnIngresar;
    String usuarioLogueado = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsuario = findViewById(R.id.edt_usuario);
        edtContraseña = findViewById(R.id.edt_contraseña);
        btnIngresar = findViewById(R.id.btn_ingresar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = edtUsuario.getText().toString().trim();
                String contraseña = edtContraseña.getText().toString().trim();

                if (usuario.isEmpty() || contraseña.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (usuario.equals("duvan") && contraseña.equals("12345678")) {
                    usuarioLogueado = usuario;
                    Toast.makeText(MainActivity.this, "¡Inicio de sesión exitoso!", Toast.LENGTH_SHORT).show();
                    mostrarMenuPrincipal();
                } else {
                    Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void mostrarMenuPrincipal() {
        setContentView(R.layout.fragment_home);

        BottomNavigationView btn_menu = findViewById(R.id.btn_menu);

        cargarHomeProductos();

        btn_menu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();

                if (itemId == R.id.nav_home) {
                    setContentView(R.layout.fragment_home);
                    BottomNavigationView menu = findViewById(R.id.btn_menu);
                    menu.setSelectedItemId(R.id.nav_home);
                    menu.setOnItemSelectedListener(this);
                    cargarHomeProductos();
                    return true;
                } else if (itemId == R.id.nav_setting) {
                    SettingFragment settingFragment = SettingFragment.newInstance(usuarioLogueado, "");
                    loadFragment(settingFragment);
                    return true;
                }
                return false;
            }
        });
    }

    private void cargarHomeProductos() {
        RecyclerView rcv_productos = findViewById(R.id.rcv_productos);
        if (rcv_productos != null) {
            rcv_productos.setLayoutManager(new LinearLayoutManager(this));

            List<productos> listaproductos = new ArrayList<>();
            listaproductos.add(new productos(
                    "https://cdn.kia.com.co/Foto_espacio_Picanto2_4338b35cae.JPG",
                    "kia picanto",
                    "El Kia Picanto fue diseñado pensando en maximizar el espacio para toda la familia. Cuenta con un amplio y meticuloso espacio que proporcionará no solo comodidad y relajación sino también una óptima usabilidad y practicidad para sus ocupantes."
            ));
            listaproductos.add(new productos(
                    "https://www.elcarrocolombiano.com/wp-content/webp-express/webp-images/uploads/2025/01/20250110-TESLA-MODEL-Y-JUNIPER-2025-PORTADA.jpg.webp",
                    "Tesla Model Y",
                    "El Tesla Model Y es un SUV compacto 100% eléctrico que se ha convertido en uno de los vehículos más vendidos en Colombia y a nivel mundial"
            ));
            listaproductos.add(new productos(
                    "https://www.elcarrocolombiano.com/wp-content/webp-express/webp-images/uploads/2017/06/20170606-CHEVROLET-BEAT-2018-MEXICO-SPARK-GT-01.jpg.webp",
                    "Chevrolet Spark GT / Beat",
                    "El Chevrolet Spark GT y su derivado sedán, el Chevrolet Beat, es un vehículo compacto muy popular en Colombia por su economía y bajo costo de mantenimiento"
            ));
            listaproductos.add(new productos(
                    "https://www.elcarrocolombiano.com/wp-content/webp-express/webp-images/uploads/2025/12/20250112-TOYOTA-HILUX-ELECTRICA-2026-PORTADA.jpg.webp",
                    "toyota hilux",
                    "La Toyota Hilux es una de las camionetas pick-up medianas más populares y resistentes del mundo, con amplia presencia en Colombia para trabajo y aventura"
            ));
            listaproductos.add(new productos(
                    "https://http2.mlstatic.com/D_NQ_NP_2X_685105-MCO116502454773_082026-F.webp",
                    "Toyota Land Cruiser Prado",
                    "La Toyota Land Cruiser Prado (Serie 250) es un SUV 4x4 de tamaño completo que destaca por su robustez, chasis de escalera (plataforma TNGA-F) y gran desempeño todoterreno."
            ));
            listaproductos.add(new productos(
                    "https://http2.mlstatic.com/D_NQ_NP_2X_821819-MCO114184195667_072026-F.webp",
                    "Ford Ranger",
                    "La Ford Ranger es una camioneta pick-up mediana disponible en Colombia con diversas versiones, motorizaciones y precios para trabajo o uso particular"
            ));

            homeadaptador adaptador = new homeadaptador(listaproductos);
            rcv_productos.setAdapter(adaptador);
        }
    }

    private void loadFragment(Fragment selectedFragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frm_container, selectedFragment)
                .commit();
    }
}