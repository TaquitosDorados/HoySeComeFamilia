package com.example.hoysecome.ui.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.hoysecome.MainActivity;
import com.example.hoysecome.R;
import com.example.hoysecome.databinding.FragmentHomeBinding;
import com.example.hoysecome.ingredient_list;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*binding.btnSearch.setOnClickListener(v -> {
            String searchName = binding.txbFindIngredient.getText().toString();

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setMessage(searchName)
                    .setTitle("Webo");

            AlertDialog dialog = builder.create();
            dialog.show();

            MainActivity.myBundle.putString("arg_name", searchName);
        });*/

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public Fragment updateFragment(Fragment frag, String name){
        frag = new ingredient_list();
        Bundle args = new Bundle();
        args.putString("arg_name", name);
        frag.setArguments(args);
        return frag;
    }

}