package com.example.mahrem_pc.cs3270finalproject;

import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements HomePageFragment.OnOptionButtonClicked,
                                                                ExerciseFragment.OnExerciseButtonClicked{

    private FragmentManager fm;
    private HomePageFragment homePageFragment;
    private FoodIntakeFragment foodIntakeFragment;
    private ExerciseFragment exerciseFragment;
    private WeightFragment weightFragment;
    private BicepsAndBackFragment bicepsAndBackFragment;
    private TricepsAndChestFragment tricepsAndChestFragment;
    private AbsFragment absFragment;
    private LegsFragment legsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar)findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);

        homePageFragment = new HomePageFragment();
        foodIntakeFragment = new FoodIntakeFragment();
        exerciseFragment = new ExerciseFragment();
        weightFragment = new WeightFragment();
        bicepsAndBackFragment = new BicepsAndBackFragment();
        tricepsAndChestFragment = new TricepsAndChestFragment();
        absFragment = new AbsFragment();
        legsFragment = new LegsFragment();

        fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.fragment_home_page, homePageFragment).addToBackStack(null).commit();



    }


    @Override
    public void onFoodButtonClicked() {
        fm.beginTransaction().replace(R.id.fragment_home_page, foodIntakeFragment).addToBackStack(null).commit();
    }

    @Override
    public void onExerciseButtonClicked() {
        fm.beginTransaction().replace(R.id.fragment_home_page, exerciseFragment).addToBackStack(null).commit();
    }

    @Override
    public void onWeightButtonClicked() {
        fm.beginTransaction().replace(R.id.fragment_home_page, weightFragment).addToBackStack(null).commit();
    }

    @Override
    public void onBicepsAndBackButtonClicked() {
        fm.beginTransaction().replace(exerciseFragment.getId(), bicepsAndBackFragment).addToBackStack(null).commit();
    }

    @Override
    public void onTricepsAndChestButtonClicked() {
        fm.beginTransaction().replace(exerciseFragment.getId(), tricepsAndChestFragment).addToBackStack(null).commit();
    }

    @Override
    public void onAbsButtonClicked() {
        fm.beginTransaction().replace(exerciseFragment.getId(), absFragment).addToBackStack(null).commit();
    }

    @Override
    public void onLegsButtonClicked() {
        fm.beginTransaction().replace(exerciseFragment.getId(), legsFragment).addToBackStack(null).commit();
    }
}
