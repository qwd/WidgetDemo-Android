package com.heweather.widgetdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btH = findViewById(R.id.bt_horizon);
        Button btLL = findViewById(R.id.bt_left);
        Button btRL = findViewById(R.id.bt_right);
        Button btV = findViewById(R.id.bt_vertical);
        btH.setOnClickListener(this);
        btLL.setOnClickListener(this);
        btRL.setOnClickListener(this);
        btV.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(this, ViewActivity.class);
        switch (view.getId()) {
            case R.id.bt_horizon:
                intent.putExtra("type", "h");
                break;

            case R.id.bt_left:
                intent.putExtra("type", "ll");
                break;

            case R.id.bt_right:
                intent.putExtra("type", "rl");
                break;

            case R.id.bt_vertical:
                intent.putExtra("type", "v");
                break;

        }
        startActivity(intent);

    }
}
