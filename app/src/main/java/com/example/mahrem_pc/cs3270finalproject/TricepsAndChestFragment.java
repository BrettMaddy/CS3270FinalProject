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
import com.example.mahrem_pc.cs3270finalproject.db.Legs;
import com.example.mahrem_pc.cs3270finalproject.db.TricepsAndChest;


/**
 * A simple {@link Fragment} subclass.
 */
public class TricepsAndChestFragment extends Fragment {

    private View root;
    private TextView tvPushupsProgress;
    private TextView tvDipsProgress;
    private TextView tvShoulderPressProgress;
    private EditText etPushups;
    private EditText etDips;
    private EditText etShoulderPress;
    private Button btnTricepsAndChestProgressDone;
    private int differenceInPushups;
    private int differenceInDips;
    private int differenceInShoulderPress;
    private AlertDialog dialog;
    private View view;


    public TricepsAndChestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_triceps_and_chest, container, false);

        Toolbar toolbar = (Toolbar)root.findViewById(R.id.legsToolbar);
        toolbar.setTitle(R.string.legs_progress);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        etPushups = (EditText)root.findViewById(R.id.etPushups);
        etDips = (EditText)root.findViewById(R.id.etDips);
        etShoulderPress = (EditText)root.findViewById(R.id.etShoulderPress);

        AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(root.getContext());
        view = getLayoutInflater().inflate(R.layout.dialog_triceps_and_chest, null);
        builder.setView(view);

        btnTricepsAndChestProgressDone = (Button)view.findViewById(R.id.btnLegsProgress);
        tvPushupsProgress = (TextView)view.findViewById(R.id.tvPushupsProgress);
        tvDipsProgress = (TextView)view.findViewById(R.id.tvDipsProgress);
        tvShoulderPressProgress = (TextView)view.findViewById(R.id.tvShoulderPressProgress);

        dialog = builder.create();
        btnTricepsAndChestProgressDone.setOnClickListener(new View.OnClickListener() {
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

                final int pushups = Integer.valueOf(etPushups.getText().toString());
                final int dips = Integer.valueOf(etDips.getText().toString());
                final int shoulderPress = Integer.valueOf(etShoulderPress.getText().toString());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        TricepsAndChest tricepsAndChest = new TricepsAndChest(pushups, dips, shoulderPress);

                        AppDatabase.getInstance(getContext())
                                .tricepsAndChestDAO()
                                .insertTricepsAndChest(tricepsAndChest);

                        int mostRecentTricepsAndChestId = AppDatabase.getInstance(getContext())
                                .tricepsAndChestDAO()
                                .selectMostRecentTricepsAndChestId();

                        int currentPushups = AppDatabase.getInstance(getContext())
                                .tricepsAndChestDAO()
                                .selectPushups(mostRecentTricepsAndChestId);

                        int yesterdaysPushups = AppDatabase.getInstance(getContext())
                                .tricepsAndChestDAO()
                                .selectPushups(mostRecentTricepsAndChestId - 1);

                        int currentDips = AppDatabase.getInstance(getContext())
                                .tricepsAndChestDAO()
                                .selectDips(mostRecentTricepsAndChestId);

                        int yesterdaysDips = AppDatabase.getInstance(getContext())
                                .tricepsAndChestDAO()
                                .selectDips(mostRecentTricepsAndChestId - 1);

                        int currentShoulderPress = AppDatabase.getInstance(getContext())
                                .tricepsAndChestDAO()
                                .selectShoulderPress(mostRecentTricepsAndChestId);

                        int yesterdaysShoulderPress = AppDatabase.getInstance(getContext())
                                .tricepsAndChestDAO()
                                .selectShoulderPress(mostRecentTricepsAndChestId - 1);

                        differenceInPushups = currentPushups - yesterdaysPushups;
                        differenceInDips = currentDips - yesterdaysDips;
                        differenceInShoulderPress = currentShoulderPress - yesterdaysShoulderPress;

                    }
                }).start();

                etPushups.setText(R.string.empty);
                etDips.setText(R.string.empty);
                etShoulderPress.setText(R.string.empty);

                break;

            case R.id.action_list:

                tvPushupsProgress.setText("Difference in Pushups: " + " " + differenceInPushups);
                tvDipsProgress.setText("Difference in Dips: " + " " + differenceInDips);
                tvShoulderPressProgress.setText("Difference in Shoulder Press: " + " " + differenceInShoulderPress);

                dialog.show();

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
