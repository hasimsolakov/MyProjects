package com.example.hashim.ezpresenter.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hashim.ezpresenter.Interfaces.IPresentationView;
import com.example.hashim.ezpresenter.R;

/**
 * Created by Hashim on 9.7.2016 г..
 */
public class PresentationView extends AppCompatActivity implements IPresentationView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presentation_view);
        if (savedInstanceState == null) {
            NavigationFragment navigationFragment = new NavigationFragment();
            SlideImageFragment slideImageFragment = new SlideImageFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.presentation_view, navigationFragment)
                    .add(R.id.presentation_view, slideImageFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
        }
    }

    public static class NavigationFragment extends Fragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.navigation_fragment, container, false);
        }
    }

    public static class SlideImageFragment extends Fragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.presenation_fragment, container, false);
        }
    }
}
