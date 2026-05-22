package com.esh.eih.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esh.eih.Interfaces.OnFragmentNavigationListener;
import com.esh.eih.LYK;
import com.esh.eih.R;
import com.esh.eih.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {

    FragmentSettingsBinding binding;
    OnFragmentNavigationListener listener;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listener.onNavigationFragmentListener(LYK.SETTINGS_FRAGMENT);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentNavigationListener){
            listener = (OnFragmentNavigationListener) context;
        } else {
            throw new RuntimeException(context.toString() + "Errors");
        }

    }
}