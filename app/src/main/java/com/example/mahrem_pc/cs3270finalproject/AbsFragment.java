package com.example.mahrem_pc.cs3270finalproject;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class AbsFragment extends Fragment {


    public AbsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_abs, container, false);
    }

    @Override
    public void onResume() {

        SharedPreferences sp = getActivity().getPreferences(Context.MODE_PRIVATE);


        super.onResume();
    }
}
