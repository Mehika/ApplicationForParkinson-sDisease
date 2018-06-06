package com.example.mehikamanocha.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.mobile.auth.core.IdentityManager;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FeedbackActivity extends AppCompatActivity {

    TextView score;
    TextView result1;
    StoreResults myDb;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor = null;

    private final DynamoDBMapper mapper;

    public FeedbackActivity() {
        AmazonDynamoDBClient dynamoDBClient =
                new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        mapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        int A = getIntent().getIntExtra("noTapsA", 0);
        int B = getIntent().getIntExtra("noTapsB", 0);

        score = findViewById(R.id.score);
        score.setText(Integer.toString(A + B));

        myDb = new StoreResults(getApplicationContext());
        sqLiteDatabase = myDb.getReadableDatabase();
        cursor = myDb.Get_Note(sqLiteDatabase);

        String todayAsString = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        String time = String.valueOf(DateFormat.getTimeInstance(DateFormat.SHORT).format(new Date()));

        boolean isInserted = myDb.Insert_Data(" Title", todayAsString + " " + time, " ");

        saveResults();
    }


    public void saveResults() {
        final ResultsDO results = new ResultsDO();
        SharedPreferences sharedPreferences = FeedbackActivity.this.getSharedPreferences("App_Pref_File", MODE_PRIVATE);

        Random RANDOM = new Random();
        final String DATA = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        String generatedString;
        StringBuilder sb = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {
            sb.append(DATA.charAt(RANDOM.nextInt(DATA.length())));
        }

        generatedString = sb.toString();

        results.setUserId(generatedString);

        results.setAge((double) sharedPreferences.getInt("age", 0));

        if (sharedPreferences.getBoolean("yesFemale", false)) {
            results.setGender("Female");
        } else if (sharedPreferences.getBoolean("yesMale", false)) {
            results.setGender("Male");
        } else if (sharedPreferences.getBoolean("yesOther" , false)) {
            results.setGender("Other");
        }

        if (sharedPreferences.getBoolean("yesRight", false)) {
            results.setWhichHand("Right");
        } else if (sharedPreferences.getBoolean("yesLeft", false)) {
            results.setWhichHand("Left");
        } else if (sharedPreferences.getBoolean("yesAmbi", false)) {
            results.setWhichHand("Ambi");
        }

        int A = getIntent().getIntExtra("noTapsA", 0);
        int B = getIntent().getIntExtra("noTapsB", 0);
        int outside = getIntent().getIntExtra("outside", 0);

        results.setInsideTapsA((double) (A));
        results.setInsideTapsB((double) (B));
        results.setOutsideTaps((double) outside);

        if (sharedPreferences.getBoolean("yesPD", false)) {
            results.setHavePD(Boolean.TRUE);
        } else if (sharedPreferences.getBoolean("noPD", false)){
            results.setHavePD(Boolean.FALSE);

        }

        results.setDateofDiagnosis(sharedPreferences.getString("diagnosis", ""));
        if (sharedPreferences.getBoolean("EBSyes", false)) {
            results.setTakeEBS(Boolean.TRUE);
        } else {
            results.setTakeEBS(Boolean.FALSE);
        }

        results.setMedication(sharedPreferences.getString("medication", ""));
        results.setTimeofMedication(sharedPreferences.getString("timeformedication", ""));

        new Thread(new Runnable() {
            @Override
            public void run() {
                mapper.save(results);
            }
        }).start();

    }

    public void gotoDashboard(View view) {
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
    }

    public void goToProfile(View view) {
        Intent intent = new Intent(this, Demographics.class);
        startActivity(intent);
    }

}
