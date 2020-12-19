package com.example.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {
    TextView text ;
    Button stateBtn ;
    //code yessine

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        text = (TextView) findViewById(R.id.textView) ;
        stateBtn= (Button) findViewById(R.id.btnstat) ;
        Bundle extras= getIntent().getExtras();
        final String n= extras.getString("cle");
        final String n2= extras.getString("cle2");
        text.setText("Hello  "+n);

        stateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });





    }
}