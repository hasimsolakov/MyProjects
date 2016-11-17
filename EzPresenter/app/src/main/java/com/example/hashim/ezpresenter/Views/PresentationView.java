package com.example.hashim.ezpresenter.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.hashim.ezpresenter.Interfaces.IPresentationPresenter;
import com.example.hashim.ezpresenter.Interfaces.IPresentationView;
import com.example.hashim.ezpresenter.Presenters.PresentationPresenter;
import com.example.hashim.ezpresenter.R;

public class PresentationView extends AppCompatActivity implements IPresentationView {

    private static IPresentationPresenter presentationPresenter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presentationPresenter.onDestroy();
    }

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
            presentationPresenter = new PresentationPresenter(this);
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
            View layoutInflater = inflater.inflate(R.layout.navigation_fragment, container, false);
            ImageButton nextButton = (ImageButton) layoutInflater.findViewById(R.id.nextButton);
            ImageButton previousButton = (ImageButton) layoutInflater.findViewById(R.id.previousButton);
            Button slideShowButton = (Button) layoutInflater.findViewById(R.id.slideShow_button);
            nextButton.setOnClickListener(onClickListener);
            previousButton.setOnClickListener(onClickListener);
            slideShowButton.setOnClickListener(onClickListener);
            return layoutInflater;
        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.nextButton:
                        presentationPresenter.gotoNextSlide();
                        break;
                    case R.id.previousButton:
                        presentationPresenter.gotoPreviousSlide();
                        break;
                    case R.id.slideShow_button:
                        presentationPresenter.manipulateSlideShow(v);
                        break;
                }
            }
        };
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
