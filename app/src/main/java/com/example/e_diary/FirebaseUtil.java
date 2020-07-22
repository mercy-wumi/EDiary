package com.example.e_diary;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class FirebaseUtil {
    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDatabaseReference;
    private static FirebaseUtil firebaseUtil;
    public static FirebaseStorage mStorage;
    public static StorageReference mStorageRef;
    public static ArrayList<EventInput> events;

    private FirebaseUtil(){}

    public static void openFbReference (String ref){
        if (firebaseUtil == null) {
            firebaseUtil = new FirebaseUtil();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            connectStorage();
        }
        events = new ArrayList<EventInput>();
        mDatabaseReference = mFirebaseDatabase.getReference().child(ref);
    }
    public static void connectStorage(){
        mStorage= FirebaseStorage.getInstance();
        mStorageRef = mStorage.getReference().child("events_pix");
    }
}
