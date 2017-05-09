package com.example.mbennett.myapplication;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.mbennett.myapplication.R.id.introText;

public class MainActivity extends Activity implements SensorEventListener {

    private SensorManager senSensorManager;
    private Sensor senAeccelerometer;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z = 0;
    private double speed;
    private float speedX, speedY, changeX, changeY = 0;

    private static final int SHAKE_THRESHOLD = 600;
    TextView IntroText;
    TextView star;
    private float last_posX, last_posY, posX, posY;

    float xmax = 100;
    float ymax = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAeccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAeccelerometer , SensorManager.SENSOR_DELAY_NORMAL);
        //IntroText = (TextView) findViewById(R.id.introText);
        IntroText = (TextView) findViewById(R.id.introText);
        IntroText.setText("testing");
        star = (TextView) findViewById(R.id.star);
        last_posX = star.getX();
        last_posY = star.getY();


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent){
        Sensor mySensor = sensorEvent.sensor;

        if(mySensor.getType() == Sensor.TYPE_ACCELEROMETER){ //Check to make sure this is the correct sensor type
            float x = sensorEvent.values[0]; //acceleration (minus gravity) on each respective axis)
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime = System.currentTimeMillis(); //storing the systems current time in milliseconds

           // if ((curTime - lastUpdate) > 100){ //test to make sure that it has been more than 100 milliseconds since this was last run
                long diffTime = curTime - lastUpdate;
                lastUpdate = curTime;

                speedX = speedX + x*diffTime;
                posX = 1/2*x*diffTime*diffTime + speedX*diffTime + last_posX;

                speedY = speedY + y*diffTime;
                posY = 1/2*y*diffTime*diffTime + speedY*diffTime + last_posY;

            if (posX > xmax)
                  posX = xmax;
            else if (posX < 0)
                posX = 0;
            if(posY > ymax)
                posY = ymax;
            else if(posY < 0)
                posY = 0;


            star.setX(posX);
            star.setY(posY);

            last_posX = posX;
            last_posY = posY;

               /* if(speed > SHAKE_THRESHOLD){
                    Toast.makeText(getApplicationContext(), "Speed by Cheating:"+speed, Toast.LENGTH_LONG);
                    IntroText.setText("Shake Shake, Shake Shake, ah Shake YA!");
                }*/


          //  }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){
    }

    protected void onPause(){
        super.onPause();;
        senSensorManager.unregisterListener(this);
    }
    //unregister sensor when application hibernates

    protected void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senAeccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    //reregister sensor when application is back in use
}

//https://code.tutsplus.com/tutorials/using-the-accelerometer-on-android--mobile-22125