package com.esh.eih.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esh.eih.Adapters.IdiomsAdapter;
import com.esh.eih.Databases.IdiomsDatabase;
import com.esh.eih.IdiomsModel;
import com.esh.eih.Interfaces.OnFragmentNavigationListener;
import com.esh.eih.Interfaces.OnItemClickListener;
import com.esh.eih.LYK;
import com.esh.eih.R;
import com.esh.eih.databinding.FragmentIdiomsBinding;

import java.util.ArrayList;


public class IdiomsFragment extends Fragment implements OnItemClickListener{

    FragmentIdiomsBinding binding;
    int arraySize;
    OnFragmentNavigationListener listener;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentIdiomsBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listener.onNavigationFragmentListener(LYK.IDIOMS_FRAGMENT);
        getAdapter("main");

        binding.editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getAdapter("search", s.toString());

                if (arraySize == 0){
                    binding.emptyList.getRoot().setVisibility(View.VISIBLE);
                }else {
                    binding.emptyList.getRoot().setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @SuppressLint("NotifyDataSetChanged")
    public void getAdapter(String... type){
        IdiomsDatabase idiomsDatabase = new IdiomsDatabase(getActivity());
        ArrayList<IdiomsModel> arrayList = idiomsDatabase.getData(type);
        arraySize = arrayList.size();
        IdiomsAdapter idiomsAdapter = new IdiomsAdapter(getActivity(), this, arrayList);
        idiomsAdapter.notifyDataSetChanged();
        binding.recycleView.setAdapter(idiomsAdapter);
    }

    public Bundle sendBundle(IdiomsModel model){
        Bundle bundle = new Bundle();
        bundle.putString("idiom", model.getIdiom());
        setArguments(bundle);
        return  bundle;
    }

    @Override
    public void onItemClickListener(IdiomsModel idiomsModel, int position) {
        binding.editSearch.setEnabled(false);
        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_idiomsFragment_to_viewIdiomFragment, sendBundle(idiomsModel));

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentNavigationListener){
            listener = (OnFragmentNavigationListener) context;
        } else {
            throw new RuntimeException(context + "Errors");
        }

    }
}