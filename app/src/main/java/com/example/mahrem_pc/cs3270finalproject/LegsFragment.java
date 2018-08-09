package com.example.mahrem_pc.cs3270finalproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import com.example.mahrem_pc.cs3270finalproject.db.BicepsAndBack;
import com.example.mahrem_pc.cs3270finalproject.db.Legs;


/**
 * A simple {@link Fragment} subclass.
 */
public class LegsFragment extends Fragment {

    private View root;
    private TextView tvLungesProgress;
    private TextView tvCalfRaisesProgress;
    private TextView tvSquatThrustsProgress;
    private EditText etLunges;
    private EditText etCalfRaises;
    private EditText etSquatThrusts;
    private Button btnLegsProgressDone;
    private int differenceInLunges;
    private int differenceInCalfRaises;
    private int differenceInSquatThrusts;
    private AlertDialog dialog;
    private View view;


    public LegsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_legs, container, false);

        Toolbar toolbar = (Toolbar)root.findViewById(R.id.legsToolbar);
        toolbar.setTitle(R.string.legs_progress);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        etLunges = (EditText)root.findViewById(R.id.etLunges);
        etCalfRaises = (EditText)root.findViewById(R.id.etCalfRaises);
        etSquatThrusts = (EditText)root.findViewById(R.id.etSquatThrusts);

        AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(root.getContext());
        view = getLayoutInflater().inflate(R.layout.dialog_legs, null);
        builder.setView(view);

        btnLegsProgressDone = (Button)view.findViewById(R.id.btnLegsProgress);
        tvLungesProgress = (TextView)view.findViewById(R.id.tvLungesProgress);
        tvCalfRaisesProgress = (TextView)view.findViewById(R.id.tvCalfRaisesProgress);
        tvSquatThrustsProgress = (TextView)view.findViewById(R.id.tvSquatThrustsProgress);

        dialog = builder.create();
        btnLegsProgressDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
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

                final int lunges = Integer.valueOf(etLunges.getText().toString());
                final int calfRaises = Integer.valueOf(etCalfRaises.getText().toString());
                final int squatThrusts = Integer.valueOf(etSquatThrusts.getText().toString());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Legs legs = new Legs(lunges, calfRaises, squatThrusts);

                        AppDatabase.getInstance(getContext())
                                .legsDAO()
                                .insertLegs(legs);

                        int mostRecentLegsId = AppDatabase.getInstance(getContext())
                                .legsDAO()
                                .selectMostRecentLegsId();

                        int currentLunges = AppDatabase.getInstance(getContext())
                                .legsDAO()
                                .selectLunges(mostRecentLegsId);

                        int yesterdaysLunges = AppDatabase.getInstance(getContext())
                                .legsDAO()
                                .selectLunges(mostRecentLegsId - 1);

                        int currentCalfRaises = AppDatabase.getInstance(getContext())
                                .legsDAO()
                                .selectCalfRaises(mostRecentLegsId);

                        int yesterdaysCalfRaises = AppDatabase.getInstance(getContext())
                                .legsDAO()
                                .selectCalfRaises(mostRecentLegsId - 1);

                        int currentSquatThrusts = AppDatabase.getInstance(getContext())
                                .legsDAO()
                                .selectSquatThrusts(mostRecentLegsId);

                        int yesterdaysSquatThrusts = AppDatabase.getInstance(getContext())
                                .legsDAO()
                                .selectSquatThrusts(mostRecentLegsId - 1);

                        differenceInLunges = currentLunges - yesterdaysLunges;
                        differenceInCalfRaises = currentCalfRaises - yesterdaysCalfRaises;
                        differenceInSquatThrusts = currentSquatThrusts - yesterdaysSquatThrusts;

                    }
                }).start();

                etLunges.setText(R.string.empty);
                etCalfRaises.setText(R.string.empty);
                etSquatThrusts.setText(R.string.empty);

                break;

            case R.id.action_list:

                tvLungesProgress.setText("Difference in Lunges: " + " " + differenceInLunges);
                tvCalfRaisesProgress.setText("Difference in Calf Raises: " + " " + differenceInCalfRaises);
                tvSquatThrustsProgress.setText("Difference in Squat Thrusts: " + " " + differenceInSquatThrusts);

                dialog.show();

                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
