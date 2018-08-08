package com.example.mahrem_pc.cs3270finalproject;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment {

    private View root;
    private OnOptionButtonClicked mCallback;
    private ImageButton btnFood;
    private ImageButton btnExercise;
    private ImageButton btnWeight;

    public interface OnOptionButtonClicked
    {
        void onFoodButtonClicked();
        void onExerciseButtonClicked();
        void onWeightButtonClicked();
    }

    public HomePageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_home_page, container, false);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        btnFood = (ImageButton)root.findViewById(R.id.btnFoodIntake);
        btnExercise = (ImageButton)root.findViewById(R.id.btnExercise);
        btnWeight = (ImageButton)root.findViewById(R.id.btnWeight);

        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onFoodButtonClicked();
            }
        });

        btnExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onExerciseButtonClicked();
            }
        });

        btnWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onWeightButtonClicked();
            }
        });

    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);

        try
        {
            mCallback = (OnOptionButtonClicked)activity;
        }
        catch(ClassCastException cce) {
            throw new ClassCastException(activity.toString() + " must implement OnOptionButtonClicked");
        }
    }

}
