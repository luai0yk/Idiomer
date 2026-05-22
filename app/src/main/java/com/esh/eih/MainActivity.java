package com.esh.eih;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.esh.eih.Interfaces.OnFragmentNavigationListener;
import com.esh.eih.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements OnFragmentNavigationListener {

    ActivityMainBinding binding;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavController navController = Navigation.findNavController(this, R.id.host_fragments);
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

        i = 1;

        final Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.WHITE);
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.WHITE);
        }
    }

    public void onNavigationFragmentListener(int fragmentId) {

            switch (fragmentId) {
                case LYK.VIEW_IDIOM_FRAGMENT:
                case LYK.QUIZZES_VIEW_FRAGMENT:
                    if (i != 0)
                    binding.bottomNavigationView.setVisibility(View.GONE);
                    break;
                case LYK.IDIOMS_FRAGMENT:
                case LYK.QUIZZES_FRAGMENT:
                case LYK.FAVORITE_FRAGMENT:
                    if (i != 0)
                        binding.bottomNavigationView.setVisibility(View.VISIBLE);
                    break;
            }
    }

}