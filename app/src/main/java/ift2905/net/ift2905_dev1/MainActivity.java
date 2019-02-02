package ift2905.net.ift2905_dev1;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int i = 1;

    Handler timer;
    boolean is_yellow;
    boolean is_green;
    boolean is_grey;

    Button button;
    Chronometer chronometer;
    TextView textView;

    int random = 3 + (int)(Math.random() * 7 + 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        chronometer.setVisibility(View.INVISIBLE);

        timer = new Handler();
        button.setOnClickListener(button_listener);

        button.setText(R.string.msgRepos);
        button.setBackgroundColor(getResources().getColor(R.color.buttonGrey));
    }

    View.OnClickListener button_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            textView.setText("Essai " + i + " de 5");
            chronometer.setVisibility(View.VISIBLE);
            button.setText(R.string.msgGrey);

        }
    };

}

