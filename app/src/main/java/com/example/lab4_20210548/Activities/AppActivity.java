package com.example.lab4_20210548.Activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.lab4_20210548.Fragments.FragmentLigas;
import com.example.lab4_20210548.Fragments.FragmentPositions;
import com.example.lab4_20210548.Fragments.FragmentResults;
import com.example.lab4_20210548.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AppActivity extends AppCompatActivity {
    private NavController navController;
    private BottomNavigationView bottomNav;
    private FragmentLigas fragmentLigas = new FragmentLigas();
    private FragmentPositions fragmentPositions = new FragmentPositions();
    private FragmentResults fragmentResults = new FragmentResults();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_app);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Cargar el fragmento inicial
        loadFragment(fragmentLigas);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // Cargar el fragmento correspondiente según la selección usando if-else
            if (item.getItemId() == R.id.ligas) {
                loadFragment(fragmentLigas);
                return true;
            } else if (item.getItemId() == R.id.position) {
                loadFragment(fragmentPositions);
                return true;
            } else if (item.getItemId() == R.id.results) {
                loadFragment(fragmentResults);
                return true;
            } else {
                return false;
            }
        }
    };

    // Método para cargar el fragmento
    public void loadFragment(Fragment fragmento) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerView, fragmento);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}