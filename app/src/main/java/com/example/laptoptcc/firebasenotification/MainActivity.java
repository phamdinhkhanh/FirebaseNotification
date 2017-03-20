package com.example.laptoptcc.firebasenotification;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;


public class MainActivity extends AppCompatActivity {
    private static String TAG = MainActivity.class.toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token  = FirebaseInstanceId.getInstance().getToken();
                Log.d(TAG," Token:" + token);
                Toast.makeText(MainActivity.this,token, Toast.LENGTH_SHORT);
            }
        });
    }
}


