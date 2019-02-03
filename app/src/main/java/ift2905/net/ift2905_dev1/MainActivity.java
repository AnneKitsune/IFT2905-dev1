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
    boolean is_bright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        but = (Button)findViewById(R.id.button);

        timer = new Handler();
        but.setOnClickListener(buttonListener);

        but.setBackgroundColor(getResources().getColor(R.color.neutral));
        is_blinking = false;
        is_bright = false;
    }

    View.OnClickListener buttonListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            if(is_blinking){
                // Le clignotement est activé: Le désactiver
                but.setText(R.string.activer);
                is_blinking = false;

                timer.removeCallbacks(timer_listener);
                but.setBackgroundColor(getResources().getColor(R.color.neutral));
            }else{
                // Le clignotement est désactivé: L'activer
                but.setText(R.string.message);
                is_blinking = true;

                timer_listener.run();
            }
        }
    };

    //code de demo1
    Runnable timer_listener = new Runnable() {
        @Override
        public void run() {
            if(is_bright){
                but.setBackgroundColor(getResources().getColor(R.color.neutral));
                is_bright = false;
            }else{
                but.setBackgroundColor(getResources().getColor(R.color.bright));
                is_bright = true;
            }

            timer.postDelayed(timer_listener, 1000);
        }
    };
}
