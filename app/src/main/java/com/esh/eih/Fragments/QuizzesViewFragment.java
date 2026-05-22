package com.esh.eih.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.esh.eih.Adapters.AnswersAdapter;
import com.esh.eih.Databases.IdiomsDatabase;
import com.esh.eih.IdiomsModel;
import com.esh.eih.Interfaces.OnFragmentNavigationListener;
import com.esh.eih.LYK;
import com.esh.eih.R;
import com.esh.eih.databinding.CustomDialogBinding;
import com.esh.eih.databinding.FragmentQuizzesViewBinding;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class QuizzesViewFragment extends Fragment {

    FragmentQuizzesViewBinding binding;
    OnFragmentNavigationListener listener;
    int uniqueRandomNumber;
    Random random = new Random();
    ArrayList<Integer> integerArrayList = new ArrayList<>();
    ArrayList<IdiomsModel> idiomsModelArrayList = new ArrayList<>();
    ArrayList<String> answerArrayList = new ArrayList<>();

    int currentPosition = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQuizzesViewBinding.inflate(inflater, container, false);

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("a", Activity.MODE_PRIVATE);
        IdiomsDatabase idiomsDatabase = new IdiomsDatabase(getActivity());

        assert getArguments() != null;
        String first_letter = getArguments().getString("first_letter");
        idiomsModelArrayList = idiomsDatabase.getData(LYK.FIRST_LETTER, first_letter);

        listener.onNavigationFragmentListener(LYK.QUIZZES_VIEW_FRAGMENT);
        binding.toolBar.titleText.setText(getResources().getString(R.string.quiz_page));
        binding.toolBar.diamondsText.setVisibility(View.VISIBLE);
        binding.toolBar.diamondsText.setText(String.valueOf(sharedPreferences.getInt("diamond_id", 30)));
        binding.toolBar.backImage.setOnClickListener(v -> requireActivity().onBackPressed());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.questionText.setText(idiomsModelArrayList.get(currentPosition).getDefinition());

        answerArrayList.add(idiomsModelArrayList.get(currentPosition).getIdiom());
        answerArrayList.add(idiomsModelArrayList.get(currentPosition+1).getIdiom());
        answerArrayList.add(idiomsModelArrayList.get(currentPosition+2).getIdiom());
        answerArrayList.add(idiomsModelArrayList.get(currentPosition+3).getIdiom());

        AnswersAdapter answersAdapter = new AnswersAdapter(getActivity(), answerArrayList);
        binding.answersListView.setAdapter(answersAdapter);

        binding.answersListView.setOnItemClickListener((parent, view1, position, id) -> {
            if (idiomsModelArrayList.get(currentPosition).getIdiom().equals(answerArrayList.get(position))){
                Toast.makeText(getActivity(), "true", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getActivity(), "false", Toast.LENGTH_SHORT).show();
            }
        });

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

    public int getUniqueRandomNumber(int currentPosition) {
        do {
            uniqueRandomNumber = random.nextInt(idiomsModelArrayList.size()-1);
        }while (integerArrayList.contains(uniqueRandomNumber) || uniqueRandomNumber == currentPosition);
        integerArrayList.add(uniqueRandomNumber);
        return  uniqueRandomNumber;
    }
}