package com.esh.eih.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.esh.eih.Interfaces.OnItemClickListener;
import com.esh.eih.LYK;
import com.esh.eih.databinding.CategoriesItemBinding;

import java.util.Arrays;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    static OnItemClickListener listener;

    public CategoriesAdapter(OnItemClickListener listener){
        CategoriesAdapter.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CategoriesItemBinding binding = CategoriesItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int length = LYK.lettersArray.get(position).length();
        holder.binding.textView.setText(LYK.lettersArray.get(position));
    }

    @Override
    public int getItemCount() {
        return LYK.lettersArray.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        CategoriesItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CategoriesItemBinding.bind(itemView);

            itemView.setOnClickListener(v -> listener.onItemClickListener(null,getAdapterPosition()));
        }
    }
}
