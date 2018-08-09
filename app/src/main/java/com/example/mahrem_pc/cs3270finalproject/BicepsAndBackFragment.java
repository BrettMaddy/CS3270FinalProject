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

import com.example.mahrem_pc.cs3270finalproject.db.BicepsAndBack;
import com.example.mahrem_pc.cs3270finalproject.db.AppDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class BicepsAndBackFragment extends Fragment {

    private View root;
    private TextView tvCurlsProgress;
    private TextView tvPullupsProgress;
    private TextView tvChinupsProgress;
    private EditText etCurls;
    private EditText etPullups;
    private EditText etChinups;
    private Button btnBicepsAndBackProgressDone;
    private int differenceInCurls;
    private int differenceInPullups;
    private int differenceInChinups;
    private AlertDialog dialog;
    private View view;

    public BicepsAndBackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_biceps_and_back, container, false);

        Toolbar toolbar = (Toolbar)root.findViewById(R.id.bicepsAndBackToolbar);
        toolbar.setTitle(R.string.biceps_and_back_progress);
        setHasOptionsMenu(true);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        etCurls = (EditText)root.findViewById(R.id.etCurls);
        etPullups = (EditText)root.findViewById(R.id.etPullups);
        etChinups = (EditText)root.findViewById(R.id.etChinups);

        AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(root.getContext());
        view = getLayoutInflater().inflate(R.layout.dialog_biceps_and_back, null);
        builder.setView(view);

        btnBicepsAndBackProgressDone = (Button)view.findViewById(R.id.btnBicepsAndBackProgress);
        tvCurlsProgress = (TextView)view.findViewById(R.id.tvCurlsProgress);
        tvPullupsProgress = (TextView)view.findViewById(R.id.tvPullupsProgress);
        tvChinupsProgress = (TextView)view.findViewById(R.id.tvChinupsProgress);

        dialog = builder.create();
        btnBicepsAndBackProgressDone.setOnClickListener(new View.OnClickListener() {
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

                final int curls = Integer.valueOf(etCurls.getText().toString());
                final int pullups = Integer.valueOf(etPullups.getText().toString());
                final int chinups = Integer.valueOf(etChinups.getText().toString());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        BicepsAndBack bicepsAndBack = new BicepsAndBack(curls, pullups, chinups);

                        AppDatabase.getInstance(getContext())
                                .bicepsAndBackDAO()
                                .insertBicepsAndBack(bicepsAndBack);

                        int mostRecentBicepsAndBackId = AppDatabase.getInstance(getContext())
                                .bicepsAndBackDAO()
                                .selectMostRecentBicepsAndBackId();

                        int currentCurls = AppDatabase.getInstance(getContext())
                                .bicepsAndBackDAO()
                                .selectCurls(mostRecentBicepsAndBackId);

                        int yesterdaysCurls = AppDatabase.getInstance(getContext())
                                .bicepsAndBackDAO()
                                .selectCurls(mostRecentBicepsAndBackId - 1);

                        int currentPullups = AppDatabase.getInstance(getContext())
                                .bicepsAndBackDAO()
                                .selectPullups(mostRecentBicepsAndBackId);

                        int yesterdaysPullups = AppDatabase.getInstance(getContext())
                                .bicepsAndBackDAO()
                                .selectPullups(mostRecentBicepsAndBackId - 1);

                        int currentChinups = AppDatabase.getInstance(getContext())
                                .bicepsAndBackDAO()
                                .selectChinups(mostRecentBicepsAndBackId);

                        int yesterdaysChinups = AppDatabase.getInstance(getContext())
                                .bicepsAndBackDAO()
                                .selectChinups(mostRecentBicepsAndBackId - 1);

                        differenceInCurls = currentCurls - yesterdaysCurls;
                        differenceInPullups = currentPullups - yesterdaysPullups;
                        differenceInChinups = currentChinups - yesterdaysChinups;

                    }
                }).start();

                etCurls.setText(R.string.empty);
                etPullups.setText(R.string.empty);
                etChinups.setText(R.string.empty);

                break;

            case R.id.action_list:

                tvCurlsProgress.setText("Difference in Curls: " + " " + differenceInCurls + " lbs");
                tvPullupsProgress.setText("Difference in Leg Raises: " + " " + differenceInPullups + " lbs");
                tvChinupsProgress.setText("Difference in Chinups: " + " " + differenceInChinups + " lbs");

                dialog.show();

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
