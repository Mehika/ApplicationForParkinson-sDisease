package com.example.mehikamanocha.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.amazonaws.AmazonClientException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.mobile.auth.core.IdentityHandler;
import com.amazonaws.mobile.auth.core.IdentityManager;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.AWSStartupHandler;
import com.amazonaws.mobile.client.AWSStartupResult;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

public class OnlyPDquestions extends AppCompatActivity {

    Toolbar toolbar;
    ResultsDO resultsDO = new ResultsDO();
    TextView datePD;
    TextView EnterMedication;
    TextView EnterTimeForMedication;
    boolean yesEBS;
    boolean noEBS;

    RadioButton EBS_YES;
    RadioButton EBS_NO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only_pdquestions);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // this is required to have a back button on the page
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        datePD = findViewById(R.id.datePD);
        EnterMedication = findViewById(R.id.EnterMedication);
        EnterTimeForMedication = findViewById(R.id.EnterTimeForMedication);
        EBS_YES = findViewById(R.id.EBS_YES);
        EBS_NO = findViewById(R.id.EBS_NO);

        SharedPreferences sharedPreferences = OnlyPDquestions.this.getSharedPreferences("App_Pref_File", MODE_PRIVATE);

        datePD.setText(sharedPreferences.getString("diagnosis", " "));
        EnterMedication.setText(sharedPreferences.getString("medication", " "));
        EnterTimeForMedication.setText(sharedPreferences.getString("timeformedication", " "));

        EBS_YES.setChecked(sharedPreferences.getBoolean("EBSyes", false));
        EBS_NO.setChecked(sharedPreferences.getBoolean("EBSno", false));
    }

    private DynamoDBMapper mapper;

    /*
    This method is used to navigate to the FingerTapping page from this page
     */
    public void goToFingerTapping(View view) {
        String diagnosis = datePD.getText().toString();
        resultsDO.setDateofDiagnosis(diagnosis);
        String medication = EnterMedication.getText().toString();
        resultsDO.setMedication(medication);
        String time = EnterTimeForMedication.getText().toString();
        resultsDO.setTimeofMedication(time);

        resultsDO.setUserId(IdentityManager.getDefaultIdentityManager().getCachedUserID());

        SharedPreferences sharedPreferences = OnlyPDquestions.this.getSharedPreferences("App_Pref_File", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("diagnosis", diagnosis);
        editor.putString("medication", medication);
        editor.putString("timeformedication", time);

        editor.commit();

        Intent intent = new Intent(this, Instructions.class);
        startActivity(intent);
    }

    public void EBS(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        SharedPreferences sharedPreferences = OnlyPDquestions.this.getSharedPreferences("App_Pref_File", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.EBS_YES:
                if (checked) {
                    yesEBS = true;
                    editor.putBoolean("EBSyes", yesEBS);
                    editor.commit();
                    resultsDO.setTakeEBS(Boolean.TRUE);
                    break;
                }
            case R.id.EBS_NO:
                if (checked) {
                    noEBS = true;
                    editor.putBoolean("EBSno", noEBS);
                    editor.commit();
                    resultsDO.setTakeEBS(Boolean.FALSE);
                    break;
                }
        }
    }
}
