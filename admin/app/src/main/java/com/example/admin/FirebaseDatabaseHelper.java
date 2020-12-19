package com.example.admin;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase ;
    private DatabaseReference mReferenceemp ;
    private List<Empl> empls = new ArrayList<>() ;

    public interface DataStatus {
        void DataIsLoaded (List<Empl> empls , List<String> keys) ;
        void DataIsInserted() ;
        void DataIsUpdated() ;
        void DataIsDeleted() ;
    }

    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance() ;
        mReferenceemp = mDatabase.getReference("Employés") ;
    }
    public void readEmpl(final  DataStatus dataStatus) {
        mReferenceemp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapShot) {
                empls.clear();
                List<String> keys = new ArrayList<>() ;
                for(DataSnapshot keyNode : dataSnapShot.getChildren()) {
                    keys.add(keyNode.getKey()) ;
                    Empl empl = keyNode.getValue(Empl.class) ;
                    empls.add(empl) ;
                }
                dataStatus.DataIsLoaded(empls,keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
