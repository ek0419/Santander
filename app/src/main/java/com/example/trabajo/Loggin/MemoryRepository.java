package com.example.trabajo.Loggin;

public class MemoryRepository implements LogginFragmentInterface.LogginRepository {

    private ModelUser user;

    @Override
    public void saveUserRepository(ModelUser user) {

        FirebaseLogginUser firebaseLogginUser = FirebaseLogginUser.getInstance();

        if (user != null) {
            firebaseLogginUser.saveUserIntoFirebase(user);
        }

        if (user == null) {
            //      user = getUserRepository();
        }

        //seguarda en 2QQQ
        this.user = user;

    }

    @Override
    public ModelUser getUserRepository() {
        FirebaseLogginUser firebaseLogginUser = FirebaseLogginUser.getInstance();
        return firebaseLogginUser.getUserFirebase();
    }
}
