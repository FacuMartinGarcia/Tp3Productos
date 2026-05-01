package com.example.tp3productos.ui.cargar;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.tp3productos.R;
import com.example.tp3productos.databinding.FragmentCargarBinding;

public class CargarFragment extends Fragment {

    private FragmentCargarBinding binding;
    private CargarViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCargarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        viewModel = new ViewModelProvider(this).get(CargarViewModel.class);

        viewModel.getMensaje().observe(getViewLifecycleOwner(), mensaje -> {
            if (mensaje != null) {
                Toast.makeText(requireContext(), mensaje, Toast.LENGTH_LONG).show();
                viewModel.limpiarMensajes();
            }
        });

        viewModel.getOperacionExitosa().observe(getViewLifecycleOwner(), exitosa -> {
            if (Boolean.TRUE.equals(exitosa)) {
                limpiarFormulario();
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    if (isAdded() && getView() != null) {
                        Navigation.findNavController(getView())
                                .navigate(R.id.action_nav_cargar_to_nav_listar);
                    }
                }, 2000);
            }
        });

        binding.btnGuardar.setOnClickListener(v -> {
            String sCodigo = binding.etCodigo.getText().toString().trim();
            String descripcion = binding.etDescripcion.getText().toString().trim();
            String sPrecio = binding.etPrecio.getText().toString().trim();
            viewModel.agregarProducto(sCodigo, descripcion, sPrecio);
        });
    }

    private void limpiarFormulario() {
        binding.etCodigo.setText("");
        binding.etDescripcion.setText("");
        binding.etPrecio.setText("");
        binding.etCodigo.requestFocus();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}