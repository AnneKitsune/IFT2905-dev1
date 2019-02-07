package ift2905.net.ift2905_dev1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public int counter = 1;
    public TextView tv1;
    public TextView tv2;
    public Button btn;

    private Context mContext;
    private Chronometer mChronometer;
    private Thread mThreadChrono;

    boolean is_blank;
    int random;
    int color;
    double average;
    Handler timer;
    double timerArray[] = new double[5];
    static long since;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        tv1 = findViewById(R.id.textView1);

        tv2 = findViewById(R.id.textView2);
        tv2.setVisibility(View.INVISIBLE);

        btn = findViewById(R.id.button);
        btn.setText(R.string.msgRepos);
        btn.setOnClickListener(btn_listener);
        btn.setBackgroundColor(getResources().getColor(R.color.buttonGrey));
    }

    View.OnClickListener btn_listener = new View.OnClickListener() {


        @Override
        public void onClick(View v) {

            is_blank = true;

            tv1.setText("Essai " + counter + " de 5");
            tv2.setVisibility(View.VISIBLE);
            btn.setText(R.string.msgGrey);

            random = 3 + (int)(Math.random() * 8);
            //System.out.println("random value is = " + random); on peut calculer du temps qu'on doit attendre

            color = ((ColorDrawable) btn.getBackground()).getColor();
            //System.out.println(color); par cette couleur, on sait ou on doit aller.

            while(is_blank) {
                switch (color) {

                    case -16729344:  // avant de cliquer, l'ecran est vert // -16729344 = green
                        System.out.println("green");

                        Timer delay = new Timer();
                        TimerTask timerTask = new TimerTask() {
                            @Override
                            public void run() {
                                btn.setBackgroundColor(getResources().getColor(R.color.buttonGrey));
                                btn.setText(R.string.msgGrey);
                                if (counter < 6) {
                                    tv2.setText("00:000");
                                    tv1.setText("Essai " + counter + " de 5");
                                }
                            }
                        };

                        delay.schedule(timerTask, 1500);
                        //System.out.println("number counter = " + counter);  // pour savoir le numero de counter.



                    case -5592406:   //avant de cliquer, le boutton est gris. // -5592406 = grey

                        timer = new Handler();
                        timer.postDelayed(timer_listener, random * 1000);
                        color = ((ColorDrawable) btn.getBackground()).getColor();
                        //System.out.println(color);
                        is_blank = false;
                        break;



                    case -256:  // -256 = yellow
                        btn.setText(R.string.msgGreen);
                        btn.setBackgroundColor(getResources().getColor(R.color.buttonGreen));
                        mChronometer.stop();

                        timerArray[counter - 1] = (double) since / 1000;

                        //System.out.println("timeArray " + counter + " - 1 = " + timerArray[counter - 1]);
                        // pour savoir le temp qu'on clique

                        color = ((ColorDrawable) btn.getBackground()).getColor();
                        //System.out.println(color);
                        is_blank = true;

                        counter++;

                        if (counter > 5) {
                            is_blank = false;
                            break;
                        }

                }
            }

            if (counter > 5) {
                for(int i = 0; i < 5; i++) {
                    average += timerArray[i];
                }
                average = average / 5;

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(R.string.popup_title)
                        .setMessage("Temps de réaction moyen : " + average)
                        .setPositiveButton(
                                getString(android.R.string.ok),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        Intent intent = getIntent();
                                        finish();
                                        startActivity(intent);
                                    }
                                }
                        )
                        .show();
            }

        }
    };




    public void updateTimerText(final String time) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv2.setText(time);
            }
        });
    }


    public class Chronometer implements Runnable {

        private Context mContext;
        private long mStartTime;

        private boolean mIsRunning;

        public Chronometer(Context context) {
            mContext = context;
        }

        public void start() {
            mStartTime = System.currentTimeMillis();
            mIsRunning = true;

        }

        public void stop() {
            mIsRunning = false;
            //mChronometer.stop();
            mThreadChrono.interrupt();
            mThreadChrono = null;
            mChronometer = null;
        }

        @Override
        public void run() {
            while(mIsRunning) {
                since = System.currentTimeMillis() - mStartTime;

                int seconds = (int) ((since / 1000)) % 60;
                int millis = (int) (since % 1000);

                ((MainActivity)mContext).updateTimerText(String.format(
                        "%02d:%03d", seconds, millis
                ));
            }
        }
    }

    Runnable timer_listener = new Runnable() {
        @Override
        public void run() {
            btn.setText(R.string.msgYellow);
            btn.setBackgroundColor(getResources().getColor(R.color.buttonYellow));

            if(mChronometer == null) {
                mChronometer = new Chronometer(mContext);
                mThreadChrono = new Thread(mChronometer);
                mThreadChrono.start();
                mChronometer.start();
            }
        }
    };

}



