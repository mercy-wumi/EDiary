package com.example.e_diary;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
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
    public static FirebaseAuth mfirebaseAuth;
    public static FirebaseAuth.AuthStateListener mAuthListener;
    public static StorageReference mStorageRef;
    public static ArrayList<EventInput> events;

    private FirebaseUtil(){}

    public static void openFbReference (String ref){
        if (firebaseUtil == null) {
            firebaseUtil = new FirebaseUtil();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mfirebaseAuth = FirebaseAuth.getInstance();
            mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                }
            };
            connectStorage();
        }
        events = new ArrayList<EventInput>();
        mDatabaseReference = mFirebaseDatabase.getReference().child(ref);
    }

    public static void attachListener(){
        mfirebaseAuth.addAuthStateListener(mAuthListener);
    }
    public static void detachListener(){
        mfirebaseAuth.removeAuthStateListener(mAuthListener);
    }

    public static void connectStorage(){
        mStorage= FirebaseStorage.getInstance();
        mStorageRef = mStorage.getReference().child("events_pix");
    }
}
