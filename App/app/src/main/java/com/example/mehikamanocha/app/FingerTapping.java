package com.example.mehikamanocha.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;
import android.hardware.SensorEventListener;

public class FingerTapping extends AppCompatActivity implements SensorEventListener {

    Toolbar toolbar;
    TextView time;
    CountDownTimer countDownTimer;
    Button start;
    Button refresh;
    RelativeLayout layout;

    int countA = 0;
    int countB = 0;
    int countOutside = 0;
    float timeleft = 0;

    private SensorManager mSensorManager;
    private Sensor mSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_tapping);

        layout = findViewById(R.id.layout);
        layout.addView(new Circle(FingerTapping.this));
        refresh = findViewById(R.id.refresh);

        refresh.setVisibility(View.INVISIBLE);

        time = findViewById(R.id.time);
        start = findViewById(R.id.start);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        assert mSensorManager != null;
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    public void goToProfile(View view) {
        mSensorManager.unregisterListener(this, mSensor);
        cancelTimer();
        Intent intent = new Intent(this, Demographics.class);
        startActivity(intent);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.values[0] > 15 || sensorEvent.values[0] < -4 || sensorEvent.values[1] > 15 || sensorEvent.values[1] < -4
                || sensorEvent.values[2] > 15 || sensorEvent.values[2] < -4) {
            Toast toast = Toast.makeText(getBaseContext(), "Phone is moving. Keep it stationary", Toast.LENGTH_SHORT);
            toast.show();
            toast.setGravity(Gravity.TOP | Gravity.START, 185, 550);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public class Circle extends View {

        Paint paint;

        public Circle(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(20);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawCircle(350, 1175, 250, paint);
            canvas.drawCircle(1075, 1175, 250, paint);
        }

        boolean checkA = true;
        boolean checkB = true;

        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    if (timeleft <= 30 && timeleft > 0) {
                        if (inCircle(event.getX(), event.getY(), 350, 1175, 250)) {
                            countA++;
                            layout.setBackgroundColor(Color.LTGRAY);
                            checkA = true;
                            if (!checkB) {
                                Toast toast = Toast.makeText(getBaseContext(), "Please tap the circles alternately", Toast.LENGTH_SHORT);
                                toast.show();
                                toast.setGravity(Gravity.TOP | Gravity.START, 185, 550);
                            }
                            checkB = false;
                        } else if (inCircle(event.getX(), event.getY(), 1075, 1175, 250)) {
                            countB++;
                            layout.setBackgroundColor(Color.LTGRAY);
                            checkB = true;
                            if (!checkA) {
                                //  Toast.makeText(getBaseContext(), "Please tap the circles alternately", Toast.LENGTH_SHORT).show();
                                Toast toast = Toast.makeText(getBaseContext(), "Please tap the circles alternately", Toast.LENGTH_SHORT);
                                toast.show();
                                toast.setGravity(Gravity.TOP | Gravity.START, 185, 550);
                            }
                            checkA = false;
                        } else {
                            countOutside++;
                        }

                        return true;
                    }
                }
            }
            return false;
        }

        private boolean inCircle(float x, float y, float circleCenterX, float circleCenterY, float circleRadius) {
            double dx = Math.pow(x - circleCenterX, 2);
            double dy = Math.pow(y - circleCenterY, 2);
            return (dx + dy) < Math.pow(circleRadius, 2);
        }
    }

    public void getFeedback(View view) {
        mSensorManager.unregisterListener(this, mSensor);
        Intent myintent = new Intent(this, FeedbackActivity.class);
        myintent.putExtra("noTapsA", countA);
        myintent.putExtra("noTapsB", countB);
        myintent.putExtra("outside", countOutside);
        startActivity(myintent);
    }

    CountDownTimer Timer = null;

    public void startTimer(final View view) {
        mSensorManager.registerListener(this, mSensor, mSensorManager.SENSOR_DELAY_NORMAL);

        refresh.setVisibility(View.VISIBLE);

        Timer = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                time.setText(String.format("%d", millisUntilFinished / 1000));
                timeleft = millisUntilFinished / 1000;
                layout.setBackgroundColor(Color.WHITE);
            }

            public void onFinish() {
                time.setText(R.string.zero);
                getFeedback(view);
            }

        }.start();

        start.setVisibility(View.INVISIBLE);
    }

    void cancelTimer() {
        if (Timer != null) {
            Timer.cancel();
        }
    }

    public void gotoInstructions(View view) {
        mSensorManager.unregisterListener(this, mSensor);
        cancelTimer();
        Intent intent = new Intent(this, InstructionsNoButton.class);
        startActivity(intent);
    }

    public void refreshTimer(final View view) {
        cancelTimer();
        timeleft = 0;
        Timer = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                time.setText(String.format("%d", millisUntilFinished / 1000));
                timeleft = millisUntilFinished / 1000;
                layout.setBackgroundColor(Color.WHITE);
            }

            public void onFinish() {
                time.setText(R.string.zero);
                getFeedback(view);
            }

        }.start();
    }
}