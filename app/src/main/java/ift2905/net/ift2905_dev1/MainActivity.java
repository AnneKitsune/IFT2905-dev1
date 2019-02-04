package ift2905.net.ift2905_dev1;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    int counter = 1;
    double timerArray[] = new double[5];

    Handler timer;
    boolean is_blank;

    Button button;
    Chronometer chronometer;
    TextView textView;
    Runnable yRunnable;

    int random = 3 + (int)(Math.random() * 8);
    double average = 0;
    static int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        chronometer = findViewById(R.id.chronometer);
        chronometer.setVisibility(View.INVISIBLE);

        button = findViewById(R.id.button);
        button.setOnClickListener(button_listener);
        button.setText(R.string.msgRepos);
        button.setBackgroundColor(getResources().getColor(R.color.buttonGrey));

    }


    View.OnClickListener button_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            is_blank = true;

            textView.setText("Essai " + counter + " de 5");

            chronometer.setVisibility(View.VISIBLE);
            button.setText(R.string.msgGrey);
            System.out.println("onclick");

            color = ((ColorDrawable) button.getBackground()).getColor();
            System.out.println(color);
            while(is_blank) {


                switch (color) {

                    case -16729344:  // -16729344 = green
                        System.out.println("green");

                        Timer delay = new Timer();
                        TimerTask timerTask = new TimerTask() {
                            @Override
                            public void run() {
                                button.setBackgroundColor(getResources().getColor(R.color.buttonGrey));
                                button.setText(R.string.msgGrey);
                                if (counter < 6) {
                                    chronometer.setText("00:00");
                                    textView.setText("Essai " + counter + " de 5");
                                }

                                System.out.println("success1111");

                            }
                        };

                        delay.schedule(timerTask, 3500);
                        System.out.println("number i = " + counter);



                    case -5592406:   // -5592406 = grey
                        System.out.println("The color is Yellow!!");
                        yRunnable = new Runnable() {
                            @Override
                            public void run() {
                                button.setText(R.string.msgYellow);
                                button.setBackgroundColor(getResources().getColor(R.color.buttonYellow));

                                chronometer.start();
                                chronometer.setBase(SystemClock.elapsedRealtime());
                            }
                        };

                        timer = new Handler();
                        timer.postDelayed(yRunnable, random * 1000);
                        color = ((ColorDrawable) button.getBackground()).getColor();
                        System.out.println(color);
                        is_blank = false;
                        break;


                    case -256:  // -256 = yellow
                        button.setText(R.string.msgGreen);
                        button.setBackgroundColor(getResources().getColor(R.color.buttonGreen));
                        chronometer.stop();

                        long elapsed = SystemClock.elapsedRealtime() - chronometer.getBase();
                        timerArray[counter - 1] = (double) elapsed / 1000;

                        System.out.println("timeArray " + counter + " - 1 = " + timerArray[counter - 1]);

                        color = ((ColorDrawable) button.getBackground()).getColor();
                        System.out.println(color);
                        is_blank = true;

                        counter++;

                        if (counter > 5) {
                            System.out.println("no i = " + counter);
                            is_blank = false;
                            break;
                        }

                        //break;

                }
            }

            if (counter > 5) {
                    for(int i = 0; i < 5; i++) {
                        average += timerArray[i];
                    }

                    average = average / 5;

                    System.out.println("else!!!!!");
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle(R.string.popup_title)
                            .setMessage("Temps de réaction moyen : " + average)
                            .setPositiveButton(
                                    getString(android.R.string.ok),
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    }
                            )
                            .show();
            }


        }
    };
}

