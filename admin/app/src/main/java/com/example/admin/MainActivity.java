package com.example.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {
private RecyclerView mReyclerView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mReyclerView = (RecyclerView) findViewById(R.id.rey) ;
        new FirebaseDatabaseHelper().readEmpl(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Empl> empls, List<String> keys) {
                new ReyclerView_Config().setConfig(mReyclerView,MainActivity.this,empls,keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
}