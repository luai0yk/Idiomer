package com.esh.eih.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.esh.eih.Adapters.IdiomsAdapter;
import com.esh.eih.Databases.DatabaseManager;
import com.esh.eih.IdiomsModel;
import com.esh.eih.Interfaces.OnFragmentNavigationListener;
import com.esh.eih.Interfaces.OnItemClickListener;
import com.esh.eih.LYK;
import com.esh.eih.R;
import com.esh.eih.databinding.FragmentFavoritesBinding;

import java.util.ArrayList;

public class  FavoritesFragment extends Fragment implements OnItemClickListener {

    FragmentFavoritesBinding binding;
    IdiomsAdapter idiomsAdapter;
    ArrayList<IdiomsModel> arrayList;
    OnFragmentNavigationListener listener;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        listener.onNavigationFragmentListener(LYK.FAVORITE_FRAGMENT);

        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         arrayList = new ArrayList<>();
        DatabaseManager databaseManager = new DatabaseManager(getActivity());
        arrayList = databaseManager.getFavorites();
        idiomsAdapter = new IdiomsAdapter(getActivity(), this, arrayList);
        binding.recycleView.setAdapter(idiomsAdapter);

        binding.emptyList.imageView.setImageResource(R.drawable.ic_favorite);
        binding.emptyList.imageView.setColorFilter(getResources().getColor(R.color.green6));
        binding.emptyList.textView.setText(getResources().getString(R.string.no_favorites));

        binding.toolBar.titleText.setText(getResources().getString(R.string.favorites_page));
        binding.toolBar.backImage.setOnClickListener(v -> requireActivity().onBackPressed());

        if (arrayList.size() == 0)
            binding.emptyList.getRoot().setVisibility(View.VISIBLE);
        else
            binding.emptyList.getRoot().setVisibility(View.GONE);

    }

    @Override
    public void onItemClickListener(IdiomsModel idiomsModel, int position) {
        Bundle bundle = new Bundle();
        bundle.putString("idiom", idiomsModel.getIdiom());
        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_favoriteFragment_to_viewIdiomFragment, bundle);
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