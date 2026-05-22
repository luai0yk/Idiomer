package com.esh.eih.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.esh.eih.databinding.AnswersViewItemBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnswersAdapter extends BaseAdapter {
    Context context;
    List<String> answerArrayList;
    AnswersViewItemBinding binding;

    public AnswersAdapter(Context context, ArrayList<String> answerArrayList) {
        this.context = context;
        this.answerArrayList = answerArrayList;

        Collections.shuffle(answerArrayList);
    }

    @Override
    public int getCount() {
        return answerArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return answerArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        binding = AnswersViewItemBinding.inflate(LayoutInflater.from(parent.getContext()));

        binding.answerText.setText(answerArrayList.get(position));

        return binding.getRoot();
    }

}
