package ift2905.net.ift2905_dev1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button but;

    boolean is_blinking;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        but = (Button)findViewById(R.id.button);
        but.setOnClickListener(buttonListener);

        but.setBackgroundColor(getResources().getColor(R.color.neutral));
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
                but.setText(R.string.desactiver);
                is_blinking = true;

                timer_listener.run();
            }
        }
    };
}
