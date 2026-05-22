package com.esh.eih.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.esh.eih.IdiomsModel;
import com.esh.eih.Interfaces.OnItemClickListener;
import com.esh.eih.R;
import com.esh.eih.databinding.IdiomsItemBinding;

import java.util.ArrayList;

public class IdiomsAdapter extends RecyclerView.Adapter<IdiomsAdapter.ViewHolder> {

    Context context;
    OnItemClickListener onItemClickListener;
    ArrayList<IdiomsModel> idiomModels;

    public IdiomsAdapter(Context context, OnItemClickListener onItemClickListener, ArrayList<IdiomsModel> idiomModels) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
        this.idiomModels = idiomModels;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        IdiomsItemBinding binding = IdiomsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        IdiomsModel model = idiomModels.get(position);
        holder.binding.letterText.setText(model.getIdiom().substring(0, 1));
        holder.binding.idiomText.setText(model.getIdiom());
        holder.binding.definitionText.setText(model.getDefinition());
        holder.binding.exampleText.setText(model.getExample());
    }

    @Override
    public int getItemCount() {
        return idiomModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        IdiomsItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = IdiomsItemBinding.bind(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClickListener(idiomModels.get(getAdapterPosition()), getAdapterPosition());
                }
            });

        }
    }
}
