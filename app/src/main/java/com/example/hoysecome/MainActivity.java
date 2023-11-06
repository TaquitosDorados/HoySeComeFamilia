package com.example.hoysecome;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hoysecome.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.hoysecome.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    public  static  Bundle myBundle = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        View frag = binding.container.getViewById(R.id.nav_host_fragment_activity_main);
        EditText textBox = frag.findViewById(R.id.txbFindIngredient);

        frag.findViewById(R.id.btnSearch).setOnClickListener(v -> {
            String searchName = textBox.getText().toString();

            ingredient_list list = ingredient_list.newInstance(searchName);

            FragmentContainerView container = frag.findViewById(R.id.fragmentContainerView);
            FragmentManager fm = container.getFragment().getParentFragmentManager();
            fm.beginTransaction().replace(fm.findFragmentById(R.id.fragmentContainerView).getId(),list).commit();

            /*AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage(searchName)
                .setTitle("Webo");

            AlertDialog dialog = builder.create();
            dialog.show();*/


        });
    }

}