package ift2905.net.ift2905_dev1;

import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    int i = 1;

    double timerArray[] = new double[5];

    Handler timer;
    boolean trueOrFalse;
    boolean is_yellow;
    boolean is_green;
    boolean is_grey;
    boolean is_red;

    Button button;
    Chronometer chronometer;
    TextView textView;
    Runnable yRunnable;

    int random = 3 + (int)(Math.random() * 8);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        chronometer.setVisibility(View.INVISIBLE);

        button.setOnClickListener(button_listener);

        button.setText(R.string.msgRepos);
        button.setBackgroundColor(getResources().getColor(R.color.buttonGrey));

    }


    View.OnClickListener button_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            trueOrFalse = true;

            textView.setText("Essai " + i + " de 5");

            chronometer.setVisibility(View.VISIBLE);
            button.setText(R.string.msgGrey);
            System.out.println("The color is grey!!");

            int color = ((ColorDrawable) button.getBackground()).getColor();
            System.out.println(color);

            while(trueOrFalse) {

                switch (color) {
                    case -5592406:   // -5592406 = grey

                        yRunnable = new Runnable() {
                            @Override
                            public void run() {
                                button.setText(R.string.msgYellow);
                                button.setBackgroundColor(getResources().getColor(R.color.buttonYellow));

                                //여기에서 노란색으로 바뀌자마자 타이머가 작동하고
                                chronometer.start();
                                chronometer.setBase(SystemClock.elapsedRealtime());

                                /*
                                chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                                    @Override
                                    public void onChronometerTick(Chronometer chronometer) {
                                        long elapsed = SystemClock.elapsedRealtime() - chronometer.getBase();
                                        long sec = elapsed / 1000;
                                        System.out.println(elapsed);
                                        String strElapsed = Long.toString(elapsed);
                                        String millis = strElapsed.substring(strElapsed.length()-3);
                                        chronometer.setText(sec + ":" + millis);
                                    }
                                });

                                */
                                //chronometer.setBase(System.currentTimeMillis());
                                // 다시 한번 클릭을 했을 때 타이머가 멈추면서 그 시간을 저장.
                                // 그리고 화면은 녹색으로 메세지도 녹색메세지로 바뀌어야 함.
                            }
                        };
                        timer = new Handler();
                        timer.postDelayed(yRunnable, 2 * 1000);

                        System.out.println("The color is Yellow!!");
                        trueOrFalse = false;
                        break;

                    case -256:  // -256 = yellow
                        button.setText(R.string.msgGreen);
                        button.setBackgroundColor(getResources().getColor(R.color.buttonGreen));
                        chronometer.stop();

                        long elapsed = SystemClock.elapsedRealtime() - chronometer.getBase();
                        timerArray[i - 1] = elapsed;

                        System.out.println(timerArray[i - 1]);

                        color = ((ColorDrawable) button.getBackground()).getColor();
                        System.out.println(color);
                        trueOrFalse = false;



                    case -16729344:  // -16729344 = green
                        System.out.println("green");
                        if(i < 6) {
                            Timer timer = new Timer();
                            TimerTask timerTask = new TimerTask() {
                                @Override
                                public void run() {
                                    button.setBackgroundColor(getResources().getColor(R.color.buttonGrey));
                                }
                            };

                            timer.schedule(timerTask, 3500);
                            i++;
                            System.out.println("success");
                            color = -5592406;

                        } else {
                            break;
                        }


                }
            }
        }
    };
}

