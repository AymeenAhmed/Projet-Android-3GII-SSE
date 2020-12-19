package com.example.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
Button btn ;
EditText t1 , t2 ;
String id = "azerty" ;
String m2 ,m ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button);
        t1 = (EditText) findViewById(R.id.Namein);
        t2 = (EditText) findViewById(R.id.Passin);
        //recap
       SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE) ;
      m = sharedPreferences.getString("name" , "") ;
       m2 = sharedPreferences.getString("password" , "");
       // m="" ;
      //  m2="" ;
        if ((m.isEmpty()) || (m2.isEmpty())) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  m = t1.getText().toString();
                  m2 = t2.getText().toString();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Employés").child(m) ;
                reference.child("id").setValue(id);
                 //id+=1;
                reference.child("password").setValue(m2);
                reference.child("state").setValue("0");
            //memoire
                SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE) ;
                SharedPreferences.Editor editor = sharedPreferences.edit() ;
               // editor.putString("name" , m) ;
              //  editor.putString("password" , m2) ;
                //editor.apply();

             //suppression des données memoirisé
                editor.remove("name") ;
                editor.apply();
                //intente 2
                Intent i = new Intent(MainActivity.this,MainActivity3.class) ;
                i.putExtra("cle",m  );
                i.putExtra("cle2",id) ;
                startActivity(i);
            }
        });
    }
        else {
            //intente 2
            Intent i = new Intent(MainActivity.this,MainActivity3.class) ;
            i.putExtra("cle",m );
            startActivity(i);
        }
    }




}