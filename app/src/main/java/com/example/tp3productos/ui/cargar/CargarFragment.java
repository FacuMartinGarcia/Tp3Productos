package com.example.tp3productos.ui.cargar;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.tp3productos.databinding.FragmentCargarBinding;

import com.example.tp3productos.R;

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

        // Observers
        viewModel.getMensaje().observe(getViewLifecycleOwner(), mensaje -> {
            if (mensaje != null) {
                Toast.makeText(requireContext(), mensaje, Toast.LENGTH_LONG).show();
                viewModel.limpiarMensajes();
            }
        });

        viewModel.getOperacionExitosa().observe(getViewLifecycleOwner(), exitosa -> {
            if (Boolean.TRUE.equals(exitosa)) {
                // Limpiar formulario después de guardar correctamente
                limpiarFormulario();
            }
        });

        // Listener del botón Guardar
        binding.btnGuardar.setOnClickListener(v -> {
            try {
                int codigo = Integer.parseInt(binding.etCodigo.getText().toString().trim());
                String descripcion = binding.etDescripcion.getText().toString().trim();
                double precio = Double.parseDouble(binding.etPrecio.getText().toString().trim());

                viewModel.agregarProducto(codigo, descripcion, precio);

            } catch (NumberFormatException e) {
                Toast.makeText(requireContext(), "Ingrese valores numéricos válidos", Toast.LENGTH_SHORT).show();
            }
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


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CargarViewModel.class);
        // TODO: Use the ViewModel
    }

}