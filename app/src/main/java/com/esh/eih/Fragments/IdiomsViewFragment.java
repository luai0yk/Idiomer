package com.esh.eih.Fragments;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esh.eih.Databases.DatabaseManager;
import com.esh.eih.Databases.IdiomsDatabase;
import com.esh.eih.IdiomsModel;
import com.esh.eih.Interfaces.OnFragmentNavigationListener;
import com.esh.eih.LYK;
import com.esh.eih.R;
import com.esh.eih.databinding.FragmentIdiomsViewBinding;
import java.util.ArrayList;
import java.util.Locale;

public class IdiomsViewFragment extends Fragment{

    FragmentIdiomsViewBinding binding;
    OnFragmentNavigationListener listener;

    TextToSpeech textToSpeech;

    IdiomsModel idiomsModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentIdiomsViewBinding.inflate(inflater, container, false);

        listener.onNavigationFragmentListener(LYK.VIEW_IDIOM_FRAGMENT);

        assert getArguments() != null;
        String idiom = getArguments().getString("idiom");
        IdiomsDatabase idiomsDatabase = new IdiomsDatabase(getActivity());
        ArrayList<IdiomsModel> arrayList = idiomsDatabase.getData("search", idiom);
        idiomsModel = arrayList.get(0);

        DatabaseManager databaseManager = new DatabaseManager(getActivity());

        if (databaseManager.getCheckFavorite(idiomsModel.getId()))
            binding.toolBar.imageFavorite.setImageResource(R.drawable.ic_favorite);
        else
            binding.toolBar.imageFavorite.setImageResource(R.drawable.ic_favorite_border);

        binding.toolBar.imageFavorite.setVisibility(View.VISIBLE);
        binding.toolBar.titleText.setText(getResources().getString(R.string.view_idiom));
        binding.toolBar.backImage.setOnClickListener(v -> requireActivity().onBackPressed());

        binding.toolBar.imageShare.setVisibility(View.VISIBLE);
        binding.toolBar.imageShare.setOnClickListener(v -> {
            startActivity(Intent.createChooser(new Intent()
                    .setAction(Intent.ACTION_SEND)
                    .setType("text/plain")
                    .putExtra(Intent.EXTRA_TEXT, getShareDate()), getResources().getString(R.string.share_using)));
        });

        binding.idiom.titleText.setText(requireActivity().getString(R.string.idiom));
        binding.idiom.englishText.setText(idiomsModel.getIdiom());
        binding.idiom.arabicText.setText(idiomsModel.getArIdiom());

        binding.definition.titleText.setText(requireActivity().getString(R.string.definition));
        binding.definition.englishText.setText(idiomsModel.getDefinition());
        binding.definition.arabicText.setText(idiomsModel.getArDefinition());

        binding.example.titleText.setText(requireActivity().getString(R.string.example));
        binding.example.englishText.setText(idiomsModel.getExample());
        binding.example.arabicText.setText(idiomsModel.getArExample());

        binding.toolBar.imageFavorite.setOnClickListener(v -> {
            if (databaseManager.getCheckFavorite(idiomsModel.getId())){
                binding.toolBar.imageFavorite.setImageResource(R.drawable.ic_favorite_border);
                databaseManager.deleteFromFavorite(idiomsModel.getId());
            }else {
                binding.toolBar.imageFavorite.setImageResource(R.drawable.ic_favorite);
                databaseManager.addToFavorite(idiomsModel);
            }
            listener.onNavigationFragmentListener(LYK.EMPTY_LIST);
        });

        textToSpeech = new TextToSpeech(getActivity(), status -> textToSpeech.setLanguage(Locale.ENGLISH));
        binding.idiom.imageSpeach.setOnClickListener(v -> textToSpeech.speak(idiomsModel.getIdiom(), TextToSpeech.QUEUE_FLUSH, null, null));
        binding.definition.imageSpeach.setOnClickListener(v -> textToSpeech.speak(idiomsModel.getDefinition(), TextToSpeech.QUEUE_FLUSH, null, null));
        binding.example.imageSpeach.setOnClickListener(v -> textToSpeech.speak(idiomsModel.getExample(), TextToSpeech.QUEUE_FLUSH, null, null));

        return binding.getRoot();
    }

    private String getShareDate(){
      return idiomsModel.getIdiom().concat("\n").concat(idiomsModel.getArIdiom())
              .concat("\n\n").concat(idiomsModel.getDefinition()).concat("\n").concat(idiomsModel.getArDefinition())
              .concat("\n\n").concat(idiomsModel.getExample()).concat("\n").concat(idiomsModel.getArExample())
              .concat("\n\n").concat(getResources().getString(R.string.shared_by_english_idioms_helper));
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