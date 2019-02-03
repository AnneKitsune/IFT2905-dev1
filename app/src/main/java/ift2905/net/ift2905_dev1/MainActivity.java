package ift2905.net.ift2905_dev1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {
    Button but;
    Handler timer;

    boolean is_blinking;
    int compter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        but = (Button)findViewById(R.id.button);

        timer = new Handler();
        but.setOnClickListener(buttonListener);

        but.setBackgroundColor(getResources().getColor(R.color.neutral));
        is_blinking = false;
        compter = 0;
    }

    View.OnClickListener buttonListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            if(is_blinking){
                // Le clignotement est activé: Le désactiver
                but.setText(R.string.activer2);
                is_blinking = false;

                timer.removeCallbacks(timer_listener);
                but.setBackgroundColor(getResources().getColor(R.color.neutral));
            }else{
                // Le clignotement est désactivé: L'activer
                but.setText(R.string.activer3);
                is_blinking = true;

                timer_listener.run();
            }
        }
    };

    //code de demo1
    Runnable timer_listener = new Runnable() {
        @Override
        public void run() {
            if(compter<5){
                but.setBackgroundColor(getResources().getColor(R.color.neutral));
                compter ++;
            }else{
                but.setBackgroundColor(getResources().getColor(R.color.bright));
                but.setText(R.string.message);
            }

            timer.postDelayed(timer_listener, 1000);
        }
    };
}
