package ift2905.net.ift2905_dev1;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.os.Handler;
import android.widget.Chronometer;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button but;
    Handler timer;
    TextView tv2;
    boolean playing;
    int compter;
    double somme;
    boolean canClick = false;
    long testStart = 0;
    Chronometer chrono;
    // Tracks if the check has been cancelled by pressing too early.
    boolean cancelled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        but = (Button)findViewById(R.id.button);
        tv2 = (TextView)findViewById(R.id.textView2);
        chrono = (Chronometer)findViewById(R.id.chrono);
        chrono.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer c) {
                // Adapté de https://stackoverflow.com/questions/4152569/how-to-change-format-of-chronometer
                // (juste les multiplications et divisions de temps pour h,m et sec).

                // Le chronomètre est trop lent pour afficher le temps correctement.
                long time = System.currentTimeMillis() - c.getBase();
                int h = (int)(time / 3600000);
                int m = (int)(time - h * 3600000)/60000;
                int sec = (int)(time - h * 3600000 - m * 60000)/1000;
                int milli = (int)(time - h * 3600000 - m * 60000 - sec * 1000);

                String sec_text = sec < 10 ? "0"+sec : ""+sec;
                String milli_text =  null;
                if(milli < 10){
                    milli_text = "00"+milli;
                } else if (milli < 100) {
                    milli_text = "0"+milli;
                } else {
                    milli_text = ""+milli;
                }
                c.setText(sec_text+":"+milli_text);
            }
        });

        timer = new Handler();

        but.setOnClickListener(buttonListener);
        but.setBackgroundColor(getResources().getColor(R.color.neutral));

        tv2.setVisibility(View.GONE);
        chrono.setVisibility(View.GONE);

        playing = false;
        compter = 0;
        somme = 0;
    }

    View.OnClickListener buttonListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            if(!playing){
                but.setText(R.string.activer2);
                playing = true;
                compter++;
                tv2.setText("Essai " + compter + " de 5");
                but.setBackgroundColor(getResources().getColor(R.color.neutral));
                chrono.setBase(System.currentTimeMillis());
                chrono.getOnChronometerTickListener().onChronometerTick(chrono);
                chrono.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.VISIBLE);

                //chrono.isTheFinalCountDown() its the final countdown dun dun dun duuuuuuuuuun

                Random rand = new Random();
                int wait = rand.nextInt(3000) + 1000;
                timer.postDelayed(timer_listener, wait);

                //timer_listener.run();
            }else{
                if(canClick){
                    canClick = false;
                    playing = false;
                    chrono.stop();
                    chrono.getOnChronometerTickListener().onChronometerTick(chrono);

                    but.setText(R.string.activer);

                    somme += (System.currentTimeMillis() - testStart);

                    if(compter >= 5){
                        double avg = somme/5f;

                        compter = 0;
                        somme = 0;
                        but.setText(R.string.activer);
                        chrono.setVisibility(View.GONE);

                        new AlertDialog.Builder(MainActivity.this)
                            .setTitle(R.string.titre)
                            .setMessage("Temps de réaction moyen : "+avg+"ms")
                            .setPositiveButton(
                                    getString(android.R.string.ok),
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //compter = 0;
                                            //somme = 0;
                                        }
                                    }
                            )
                            .show();
                    }
                }else if(!cancelled){
                    chrono.stop();
                    chrono.setVisibility(View.GONE);
                    compter--;
                    but.setBackgroundColor(getResources().getColor(R.color.red));
                    but.setText(R.string.activer5);
                    cancelled = true;
                    timer.removeCallbacks(timer_listener);
                    timer.postDelayed(unlockButton, 1500);
                }
            }
        }
    };

    //code de demo1
    Runnable timer_listener = new Runnable() {
        @Override
        public void run() {
            if(!cancelled){
                canClick = true;
                but.setText(R.string.activer3);
                but.setBackgroundColor(getResources().getColor(R.color.yellow));
                testStart = System.currentTimeMillis();
                chrono.setBase(testStart);
                chrono.start();
            }

        }
    };

    Runnable unlockButton = new Runnable() {
        @Override
        public void run() {
            playing = false;
            but.setBackgroundColor(getResources().getColor(R.color.neutral));
            but.setText(R.string.activer);
            cancelled = false;

        }
    };
}
