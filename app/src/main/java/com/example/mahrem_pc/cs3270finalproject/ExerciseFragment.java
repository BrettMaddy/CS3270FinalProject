package com.example.mahrem_pc.cs3270finalproject;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExerciseFragment extends Fragment {

    private View root;
    private Button btnBicepsAndBack;
    private Button btnTricepsAndChest;
    private Button btnAbs;
    private Button btnLegs;
    private OnExerciseButtonClicked mCallback;

    public interface OnExerciseButtonClicked
    {
        void onBicepsAndBackButtonClicked();
        void onTricepsAndChestButtonClicked();
        void onAbsButtonClicked();
        void onLegsButtonClicked();
    }

    public ExerciseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_exercise, container, false);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        btnBicepsAndBack = (Button)root.findViewById(R.id.btnBicepsAndBack);
        btnTricepsAndChest = (Button)root.findViewById(R.id.btnTricepsAndChest);
        btnAbs = (Button)root.findViewById(R.id.btnAbs);
        btnLegs = (Button)root.findViewById(R.id.btnLegs);

        btnBicepsAndBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onBicepsAndBackButtonClicked();

            }
        });

        btnTricepsAndChest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onTricepsAndChestButtonClicked();
            }
        });

        btnAbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onAbsButtonClicked();
            }
        });

        btnLegs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onLegsButtonClicked();
            }
        });

    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);

        try
        {
            mCallback = (OnExerciseButtonClicked)activity;
        }
        catch(ClassCastException cce)
        {
            throw new ClassCastException(activity.toString() + " must implement OnExerciseButtonClicked");
        }
    }
}
