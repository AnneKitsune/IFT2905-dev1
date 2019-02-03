package ift2905.net.ift2905_dev1;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.os.Handler;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button but;
    Handler timer;
    TextView tv2;
    boolean is_blinking;
    int compter;
    double tempsMoyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        but = (Button)findViewById(R.id.button);
        tv2 = (TextView)findViewById(R.id.textView2);

        timer = new Handler();
        but.setOnClickListener(buttonListener);

        but.setBackgroundColor(getResources().getColor(R.color.neutral));
        is_blinking = false;
        compter = 1;
        tempsMoyen = 0;
    }

    View.OnClickListener buttonListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            if(is_blinking){
                // Le clignotement est activé: Le désactiver
                but.setText(R.string.activer2);
                is_blinking = false;
                tv2.setText("Essai "+compter+" de 5");
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
                but.setBackgroundColor(getResources().getColor(R.color.yellow));

                compter ++;
            }else{

                //code de demo2
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(R.string.titre)
                        .setMessage("Temps de réaction moyen : "+tempsMoyen)
                        .setPositiveButton(
                                getString(android.R.string.ok),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Uri uri = Uri.parse("http://umontreal.ca");
                                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                        startActivity(intent);
                                    }
                                }
                        )
                        .show();

            }

            timer.postDelayed(timer_listener, 3000);
        }
    };
}
