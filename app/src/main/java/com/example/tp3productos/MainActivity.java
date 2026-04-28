package com.example.tp3productos;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;

import com.example.tp3productos.modelo.Producto;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tp3productos.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    public static List<Producto> productos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.appBarMain.toolbar);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_main);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_cargar,
                R.id.nav_listar
        )
                .setOpenableLayout(binding.drawerLayout)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        if (binding.navView != null) {
            NavigationUI.setupWithNavController(binding.navView, navController);
        }
        if (binding.appBarMain.contentMain.bottomNavView != null) {
            NavigationUI.setupWithNavController(binding.appBarMain.contentMain.bottomNavView, navController);
        }



        // NAVEGACION DEL BOTON EN FRAGMENT LISTAR

        binding.appBarMain.fab.setOnClickListener(v -> {
            binding.appBarMain.contentMain.bottomNavView.setSelectedItemId(R.id.nav_cargar);
        });


        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.nav_listar) {
                binding.appBarMain.fab.show();
            } else {
                binding.appBarMain.fab.hide();
            }
        });

        // DIALOG SALIR APLICACION
        binding.navView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_salir) {
                mostrarDialogSalir();
                binding.drawerLayout.closeDrawers();
                return true;
            }

            boolean handled = NavigationUI.onNavDestinationSelected(item, navController);
            if (handled) {
                binding.drawerLayout.closeDrawers();
            }
            return handled;
        });

    }
    private void mostrarDialogSalir() {
        new AlertDialog.Builder(this)
                .setTitle("Salir")
                .setMessage("¿Seguro que querés salir de la aplicación?")
                .setPositiveButton("Sí", (dialog, which) -> finishAffinity())
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean result = super.onCreateOptionsMenu(menu);
        // Using findViewById because NavigationView exists in different layout files
        // between w600dp and w1240dp
        NavigationView navView = findViewById(R.id.nav_view);
        if (navView == null) {
            // The navigation drawer already has the items including the items in the overflow menu
            // We only inflate the overflow menu if the navigation drawer isn't visible
            getMenuInflater().inflate(R.menu.overflow, menu);
        }
        return result;
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}