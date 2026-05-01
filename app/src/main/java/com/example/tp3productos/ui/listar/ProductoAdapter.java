package com.example.tp3productos.ui.listar;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp3productos.databinding.ItemProductoBinding;
import com.example.tp3productos.modelo.Producto;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolderProducto> {

    private List<Producto> productos;

    public ProductoAdapter(List<Producto> productos){
        this.productos = productos;
    }

    public void setProductos(List<Producto> productos){
        this.productos = productos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderProducto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemProductoBinding binding = ItemProductoBinding.inflate(inflater, parent, false);

        return new ViewHolderProducto(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProducto holder, int position) {

        Producto p = productos.get(position);

        holder.binding.TvCodigo.setText(String.valueOf(p.getCodigo()));
        holder.binding.TvDescripcion.setText(p.getDescripcion());
        holder.binding.TvPrecio.setText("$ " + p.getPrecio());

    }

    @Override
    public int getItemCount() {
        return productos != null ? productos.size() : 0;
    }


    public class ViewHolderProducto extends RecyclerView.ViewHolder{

        ItemProductoBinding binding;

        public ViewHolderProducto(ItemProductoBinding binding) {
            super (binding.getRoot());

            this.binding = binding;
        }
    }
}
