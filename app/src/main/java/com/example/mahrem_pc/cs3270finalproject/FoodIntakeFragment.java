package com.example.mahrem_pc.cs3270finalproject;



import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

import com.example.mahrem_pc.cs3270finalproject.db.AppDatabase;
import com.example.mahrem_pc.cs3270finalproject.db.FoodIntake;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoodIntakeFragment extends Fragment {

    private View root;
    private TextView tvCaloriesProgress;
    private TextView tvProteinProgress;
    private TextView tvCarbsProgress;
    private TextView tvFatProgress;
    private EditText etCalories;
    private EditText etProtein;
    private EditText etCarbs;
    private EditText etFat;
    private Button btnFoodProgressDone;
    private int differenceInCalories;
    private int differenceInCarbs;
    private int differenceInProtein;
    private int differenceInFat;
    private AlertDialog dialog;
    private View view;

    public FoodIntakeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_food_intake, container, false);

        Toolbar toolbar = (Toolbar)root.findViewById(R.id.foodIntakeToolbar);
        toolbar.setTitle(R.string.food_intake_progress);
        setHasOptionsMenu(true);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        etCalories = (EditText)root.findViewById(R.id.etCalories);
        etCarbs = (EditText)root.findViewById(R.id.etCarbs);
        etProtein = (EditText)root.findViewById(R.id.etProtein);
        etFat = (EditText)root.findViewById(R.id.etFat);


        AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(root.getContext());
        view = getLayoutInflater().inflate(R.layout.dialog_food, null);
        builder.setView(view);

        btnFoodProgressDone = (Button)view.findViewById(R.id.btnFoodProgress);
        tvCaloriesProgress = (TextView)view.findViewById(R.id.tvCaloriesProgress);
        tvProteinProgress = (TextView)view.findViewById(R.id.tvProteinProgress);
        tvCarbsProgress = (TextView)view.findViewById(R.id.tvCarbsProgress);
        tvFatProgress = (TextView)view.findViewById(R.id.tvFatProgress);

        dialog = builder.create();
        btnFoodProgressDone.setOnClickListener(new View.OnClickListener() {
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

                final int cals = Integer.valueOf(etCalories.getText().toString());//Integer.parseInt(etCalories.getText().toString());
                final int carbs = Integer.valueOf(etCarbs.getText().toString());//Integer.parseInt(etCarbs.getText().toString());
                final int prot = Integer.valueOf(etProtein.getText().toString());//Integer.parseInt(etProtein.getText().toString());
                final int fat = Integer.valueOf(etFat.getText().toString());//Integer.parseInt(etFat.getText().toString());

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        FoodIntake foodIntake = new FoodIntake(cals, carbs, prot, fat);
                        AppDatabase.getInstance(getContext())
                                .foodIntakeDAO()
                                .insertFoodIntake(foodIntake);

                        int mostRecentFoodId = AppDatabase.getInstance(getContext())
                                .foodIntakeDAO()
                                .selectMostRecentFoodIntakeId();

                        int currentCalories = AppDatabase.getInstance(getContext())
                                .foodIntakeDAO()
                                .selectCalories(mostRecentFoodId);

                        int yesterdayCalories = AppDatabase.getInstance(getContext())
                                .foodIntakeDAO()
                                .selectCalories(mostRecentFoodId - 1);

                        int currentCarbs = AppDatabase.getInstance(getContext())
                                .foodIntakeDAO()
                                .selectCarbs(mostRecentFoodId);

                        int yesterdaysCarbs = AppDatabase.getInstance(getContext())
                                .foodIntakeDAO()
                                .selectCarbs(mostRecentFoodId);

                        int currentProtein = AppDatabase.getInstance(getContext())
                                .foodIntakeDAO()
                                .selectProtein(mostRecentFoodId);

                        int yesterdaysProtein = AppDatabase.getInstance(getContext())
                                .foodIntakeDAO()
                                .selectProtein(mostRecentFoodId - 1);

                        int currentFat = AppDatabase.getInstance(getContext())
                                .foodIntakeDAO()
                                .selectFat(mostRecentFoodId);

                        int yesterdaysFat = AppDatabase.getInstance(getContext())
                                .foodIntakeDAO()
                                .selectFat(mostRecentFoodId - 1);

                        differenceInCalories = currentCalories - yesterdayCalories;
                        differenceInCarbs = currentCarbs - yesterdaysCarbs;
                        differenceInProtein = currentProtein - yesterdaysProtein;
                        differenceInFat = currentFat - yesterdaysFat;
                    }
                }).start();

                etCalories.setText(R.string.empty);
                etCarbs.setText(R.string.empty);
                etProtein.setText(R.string.empty);
                etFat.setText(R.string.empty);
                break;

            case R.id.action_list:

                tvCaloriesProgress.setText("Difference in Calories: " + " " + differenceInCalories + " g");
                tvCarbsProgress.setText("Difference in Carbs: " + " " + differenceInCarbs + " g");
                tvProteinProgress.setText("Difference in Protein: " + " " + differenceInProtein + " g");
                tvFatProgress.setText("Difference in Fat: " + " " + differenceInFat + " g");

                dialog.show();

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
