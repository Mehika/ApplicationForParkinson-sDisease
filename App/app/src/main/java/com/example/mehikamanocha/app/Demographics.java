package com.example.mehikamanocha.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.AmazonClientException;
import com.amazonaws.mobile.auth.core.IdentityManager;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

public class Demographics extends AppCompatActivity {

    TextView EnterAge;
    RadioButton male;
    RadioButton female;
    RadioButton other;
    boolean yesFemale;
    boolean yesMale;
    boolean yesOther;
    boolean yesLeft;
    boolean yesRight;
    boolean yesAmbi;
    boolean yesPD;
    boolean noPD;
    RadioButton left;
    RadioButton right;
    RadioButton ambi;
    RadioButton yes;
    RadioButton no;
    double age;

    final ResultsDO resultsDO = new ResultsDO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demographics);

        EnterAge = findViewById(R.id.EnterAge);
        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        other = findViewById(R.id.other);
        left = findViewById(R.id.left);
        right = findViewById(R.id.right);
        ambi = findViewById(R.id.ambi);

        SharedPreferences sharedPreferences = Demographics.this.getSharedPreferences("App_Pref_File", MODE_PRIVATE);

        if (!(sharedPreferences.getInt("age", 0) == 0)) {
            EnterAge.setText(String.valueOf(sharedPreferences.getInt("age", 0)));
        }

        male.setChecked(sharedPreferences.getBoolean("yesMale", false));
        female.setChecked(sharedPreferences.getBoolean("yesFemale", false));
        other.setChecked(sharedPreferences.getBoolean("yesOther", false));

        left.setChecked(sharedPreferences.getBoolean("yesLeft", false));
        right.setChecked(sharedPreferences.getBoolean("yesRight", false));
        ambi.setChecked(sharedPreferences.getBoolean("yesAmbi", false));

        yes.setChecked(sharedPreferences.getBoolean("yesPD", false));
        no.setChecked(sharedPreferences.getBoolean("noPD", false));
    }


    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        SharedPreferences sharedPreferences = Demographics.this.getSharedPreferences("App_Pref_File", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        resultsDO.setHavePD(Boolean.TRUE);

        try {
            if(!EnterAge.getText().toString().equals("")){
                age = Double.parseDouble(EnterAge.getText().toString());
                editor.putInt("age", (int) age);
                resultsDO.setAge(age);
            }
        } catch (IllegalArgumentException ex) {
            Toast.makeText(this, "Please enter a valid age", Toast.LENGTH_SHORT).show();
        }

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.yes:
                if (checked) {
                    editor.putBoolean("yes", true);
                    yesPD = true;
                    resultsDO.setHavePD(true);
                    Intent intent = new Intent(this, OnlyPDquestions.class);
                    startActivity(intent);
                    break;
                }
            case R.id.no:
                if (checked) {
                    editor.putBoolean("no", true);
                    noPD = true;
                    resultsDO.setHavePD(false);
                    Intent intent = new Intent(this, Instructions.class);
                    startActivity(intent);
                    break;
                }
        }

        editor.putBoolean("yesFemale", yesFemale);
        editor.putBoolean("yesMale", yesMale);
        editor.putBoolean("yesOther", yesOther);

        editor.putBoolean("yesRight", yesRight);
        editor.putBoolean("yesLeft", yesLeft);
        editor.putBoolean("yesAmbi", yesAmbi);

        editor.putBoolean("yesPD", yesPD);
        editor.putBoolean("noPD", noPD);

        editor.commit();
    }

    public void EnterGender(View view) {

        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.female:
                if (checked) {
                    resultsDO.setGender("Female");
                    yesFemale = true;
                    break;
                }
            case R.id.male:
                if (checked) {
                    resultsDO.setGender("Male");
                    yesMale = true;
                    break;
                }
            case R.id.other:
                if (checked) {
                    resultsDO.setGender("Other");
                    yesOther = true;
                    break;
                }
        }
    }

    public void EnterHand(View view) {

        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.right:
                if (checked) {
                    resultsDO.setWhichHand("Right");
                    yesRight = true;
                    break;
                }
            case R.id.left:
                if (checked) {
                    resultsDO.setWhichHand("Left");
                    yesLeft = true;
                    break;
                }
            case R.id.ambi:
                if (checked) {
                    resultsDO.setWhichHand("Ambi");
                    yesAmbi = true;
                    break;
                }
        }
    }

    public void goToNext(View view) {

        SharedPreferences sharedPreferences = Demographics.this.getSharedPreferences("App_Pref_File", MODE_PRIVATE);

        if (sharedPreferences.getBoolean("yesPD", false)) {
            Intent intent = new Intent(this, OnlyPDquestions.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, Instructions.class);
            startActivity(intent);
        }
    }
}
