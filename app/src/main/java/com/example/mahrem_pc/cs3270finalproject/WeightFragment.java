package com.example.mahrem_pc.cs3270finalproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mahrem_pc.cs3270finalproject.db.AppDatabase;
import com.example.mahrem_pc.cs3270finalproject.db.Weight;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeightFragment extends Fragment {

    private View root;
    private EditText etWeight;
    private TextView tvWeightProgress;
    private double weightdifference = 0;
    private Button btnWeightProgressDone;
    private ActionBar actionBar;

    public WeightFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_weight, container, false);

        Toolbar toolbar = (Toolbar)root.findViewById(R.id.weightToolbar);
        toolbar.setTitle(R.string.weight_title);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();

        setHasOptionsMenu(true);

        etWeight = (EditText)root.findViewById(R.id.etWeight);
        tvWeightProgress = (TextView)root.findViewById(R.id.tvWeightProgress);
        btnWeightProgressDone = (Button)root.findViewById(R.id.btnWeightProgress);

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

                        AlertDialog.Builder builder = new AlertDialog.Builder(root.getContext());
                        View view = getLayoutInflater().inflate(R.layout.dialog_weight, null);

                        etWeight.setText(etWeight.getText().toString() + " " + weightdifference);

                        builder.setView(view);

                        final AlertDialog dialog = builder.create();
                        dialog.show();

                        btnWeightProgressDone.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                }).start();

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
