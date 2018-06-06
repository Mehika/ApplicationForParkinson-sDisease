package com.example.mehikamanocha.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dashboard extends AppCompatActivity {

    ListView listView;
    ListAddAdapter listAddAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        listView = findViewById(R.id.listview);
        registerForContextMenu(listView);

        listAddAdapter = new ListAddAdapter(getApplicationContext(), R.layout.tasks);
        listView.setAdapter(listAddAdapter);
        String title = "Task 1";
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String date_task = dateFormat.format(date);
        DataProvider  dataList = new DataProvider(title, date_task, 12, "4343");
        listAddAdapter.add(dataList);
    }

    public void share(View view) {

        String filename="tappingresults.csv";
        File filelocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), filename);
        Uri path = Uri.fromFile(filelocation);
        Intent intent = new Intent(Intent.ACTION_SENDTO);

        String uriText = "mailto:" + Uri.encode("u5607484@anu.edu.au") +
                "?subject=" + Uri.encode("Finger Tapping Task Results") ;
        Uri uri = Uri.parse(uriText);

        intent .putExtra(Intent.EXTRA_STREAM, path);

        intent.setData(uri);

        startActivity(Intent.createChooser(intent, "Send Email..."));
    }

    public void gotoActivity(View view) {
        Intent intent = new Intent(this, FingerTapping.class);
        startActivity(intent);
    }
}
