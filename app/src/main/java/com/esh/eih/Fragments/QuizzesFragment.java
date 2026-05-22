package com.esh.eih.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.esh.eih.Adapters.CategoriesAdapter;
import com.esh.eih.Databases.IdiomsDatabase;
import com.esh.eih.IdiomsModel;
import com.esh.eih.Interfaces.OnFragmentNavigationListener;
import com.esh.eih.Interfaces.OnItemClickListener;
import com.esh.eih.LYK;
import com.esh.eih.R;
import com.esh.eih.databinding.FragmentQuizzesBinding;

public class QuizzesFragment extends Fragment  implements OnItemClickListener {

    OnFragmentNavigationListener listener;

    FragmentQuizzesBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentQuizzesBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listener.onNavigationFragmentListener(LYK.QUIZZES_FRAGMENT);

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("a", Activity.MODE_PRIVATE);

        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(this);
        binding.recycleView.setAdapter(categoriesAdapter);

        binding.toolBar.titleText.setText(getResources().getString(R.string.quiz_page));
        binding.toolBar.diamondsText.setVisibility(View.VISIBLE);
        binding.toolBar.diamondsText.setText(String.valueOf(sharedPreferences.getInt("diamond_id", 30)));
        binding.toolBar.backImage.setOnClickListener(v -> requireActivity().onBackPressed());

    }

    @Override
    public void onItemClickListener(IdiomsModel idiomsModel, int position) {

        IdiomsDatabase idiomsDatabase = new IdiomsDatabase(getActivity());
        int size = idiomsDatabase.getData(LYK.FIRST_LETTER, LYK.lettersArray.get(position)).size();
        if (size>5){
            Bundle bundle = new Bundle();
            bundle.putString("first_letter", LYK.lettersArray.get(position));
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_quizzesFragment_to_quizzesViewFragment, bundle);
        }else {
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
            alertDialog.setMessage("No enough idioms to take a quiz");
            alertDialog.setOnCancelListener(dialog -> {
                alertDialog.cancel();
            });
            alertDialog.show();
        }
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