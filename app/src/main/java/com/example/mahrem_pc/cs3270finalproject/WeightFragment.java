package com.example.mahrem_pc.cs3270finalproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.mahrem_pc.cs3270finalproject.db.AppDatabase;
import com.example.mahrem_pc.cs3270finalproject.db.Weight;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeightFragment extends Fragment {

    private View root;
    private EditText etWeight;
    private double weightdifference = 0;

    public WeightFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_weight, container, false);

        etWeight = (EditText)root.findViewById(R.id.etWeight);

        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        menu.clear();

        getActivity().getMenuInflater().inflate(R.menu.menu_save_and_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.action_save:

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Weight weight = new Weight(Double.valueOf(etWeight.getText().toString()));

                        AppDatabase.getInstance(getContext())
                                .weightDAO()
                                .insertWeight(weight);
                    }
                }).start();


                etWeight.setText(R.string.empty);
                break;

            case R.id.action_list:

                Thread thread1 = new Thread();



            new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Weight weight;

                        int mostRecentWeightId = AppDatabase.getInstance(getContext())
                                .weightDAO()
                                .selectMostRecentWeightId();

                        double mostCurrentWeight = AppDatabase.getInstance(getContext())
                                .weightDAO()
                                .selectWeight(mostRecentWeightId);
                        double yesterdaysWeight = AppDatabase.getInstance(getContext())
                                .weightDAO()
                                .selectWeight(mostRecentWeightId - 1);

                        weightdifference = mostCurrentWeight - yesterdaysWeight;


                    }
                }).start();

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
