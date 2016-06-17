package lk.meterialdesign.color.view;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import lk.meterialdesign.R;

public class NumberCountActivity extends AppCompatActivity {
    NumberCountView numberCountView;
    Handler mHandler;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_count);
        numberCountView = (NumberCountView) findViewById(R.id.numbercount);

        mHandler = new Handler();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                if (i < 999) {
                    numberCountView.update(i);
                    i++;
                    mHandler.postDelayed(this, 20);
                }
            }
        };
        mHandler.post(task);
    }
}
