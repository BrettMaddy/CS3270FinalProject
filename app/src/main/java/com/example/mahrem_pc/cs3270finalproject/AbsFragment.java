package com.example.mahrem_pc.cs3270finalproject;


import android.support.v7.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import com.example.mahrem_pc.cs3270finalproject.db.Abs;
import com.example.mahrem_pc.cs3270finalproject.db.AppDatabase;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class AbsFragment extends Fragment {

    private View root;
    private TextView tvCrunchesProgress;
    private TextView tvLegRaisesProgress;
    private TextView tvBicyclesProgress;
    private EditText etCrunches;
    private EditText etLegRaises;
    private EditText etBicycles;
    private Button btnAbsProgressDone;
    private int differenceInCrunches;
    private int differenceInLegRaises;
    private int differenceInBicycles;
    private AlertDialog dialog;
    private View view;

    public AbsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_abs, container, false);

        Toolbar toolbar = (Toolbar)root.findViewById(R.id.absToolbar);
        toolbar.setTitle(R.string.abs_progress);
        setHasOptionsMenu(true);

        return root;
    }

    @Override
    public void onResume() {

        super.onResume();

        etCrunches = (EditText)root.findViewById(R.id.etCrunches);
        etLegRaises = (EditText)root.findViewById(R.id.etLegRaises);
        etBicycles = (EditText)root.findViewById(R.id.etBicycles);

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(root.getContext());
        view = getLayoutInflater().inflate(R.layout.dialog_abs, null);
        builder.setView(view);

        btnAbsProgressDone = (Button)view.findViewById(R.id.btnAbsProgress);
        tvCrunchesProgress = (TextView)view.findViewById(R.id.tvCrunchesProgress);
        tvLegRaisesProgress = (TextView)view.findViewById(R.id.tvLegRaisesProgress);
        tvBicyclesProgress = (TextView)view.findViewById(R.id.tvBicyclesProgress);

        dialog = builder.create();
        btnAbsProgressDone.setOnClickListener(new View.OnClickListener() {
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

                final int crunches = Integer.valueOf(etCrunches.getText().toString());
                final int legRaises = Integer.valueOf(etLegRaises.getText().toString());
                final int bicycles = Integer.valueOf(etBicycles.getText().toString());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Abs abs = new Abs(crunches, legRaises, bicycles);

                        AppDatabase.getInstance(getContext())
                                .absDAO()
                                .insertAbs(abs);

                        int mostRecentAbsId = AppDatabase.getInstance(getContext())
                                .absDAO()
                                .selectMostRecentAbsId();

                        int currentCrunches = AppDatabase.getInstance(getContext())
                                .absDAO()
                                .selectCrunches(mostRecentAbsId);

                        int yesterdaysCrunches = AppDatabase.getInstance(getContext())
                                .absDAO()
                                .selectCrunches(mostRecentAbsId - 1);

                        int currentLegRaises = AppDatabase.getInstance(getContext())
                                .absDAO()
                                .selectLegRaises(mostRecentAbsId);

                        int yesterdaysLegRaises = AppDatabase.getInstance(getContext())
                                .absDAO()
                                .selectLegRaises(mostRecentAbsId - 1);

                        int currentBicycles = AppDatabase.getInstance(getContext())
                                .absDAO()
                                .selectBicycles(mostRecentAbsId);

                        int yesterdaysBicycles = AppDatabase.getInstance(getContext())
                                .absDAO()
                                .selectBicycles(mostRecentAbsId - 1);

                        differenceInCrunches = currentCrunches - yesterdaysCrunches;
                        differenceInLegRaises = currentLegRaises - yesterdaysLegRaises;
                        differenceInBicycles = currentBicycles - yesterdaysBicycles;

                    }
                }).start();

                etCrunches.setText(R.string.empty);
                etLegRaises.setText(R.string.empty);
                etBicycles.setText(R.string.empty);

                break;

            case R.id.action_list:

                tvCrunchesProgress.setText("Difference in Crunches: " + " " + differenceInCrunches + " lbs");
                tvLegRaisesProgress.setText("Difference in Leg Raises: " + " " + differenceInLegRaises + " lbs");
                tvBicyclesProgress.setText("Difference in Bicycles: " + " " + differenceInBicycles + " lbs");

                dialog.show();

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
