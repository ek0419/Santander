package com.example.trabajo.Loggin;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

class FirebaseLogginUser {

    private static final String PATH_LOGING = "LOGIN";
    private static final String PATH_USER = "USER";
    private static FirebaseLogginUser instancia;
    private ModelUser user;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    private FirebaseLogginUser() {

        database = FirebaseDatabase.getInstance();
        reference = database.getReference(PATH_LOGING).child(PATH_USER);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot chil : dataSnapshot.getChildren()) {
                    user = chil.getValue(ModelUser.class);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    static FirebaseLogginUser getInstance() {
        if (instancia == null) {
            instancia = new FirebaseLogginUser();
            return instancia;
        } else {
            return instancia;
        }
    }


    void saveUserIntoFirebase(ModelUser user) {
        reference.push().setValue(user);
    }

    ModelUser getUserFirebase() {
        return user;
    }

}
