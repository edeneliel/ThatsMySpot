package eden.eliel;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Eden on 8/12/2016.
 */
public class FireBaseManager {
    private FirebaseOptions _options;
    private FirebaseDatabase _database;

    public FireBaseManager(){
        _options = new FirebaseOptions.Builder()
                .setDatabaseUrl("https://thatsmyspot-16f2c.firebaseio.com/")
                .setServiceAccount(getClass().getResourceAsStream("/ThatsMySpot-ec2e69ba63ea.json"))
                .build();

        FirebaseApp.initializeApp(_options);
        _database = FirebaseDatabase.getInstance();
    }

    public void saveNextFreeTime(String movieName, String time){
        DatabaseReference ref = _database.getReference("Movies");
        ref.child(movieName).setValue(time);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void setStopFlag(boolean stopFlag) {
        DatabaseReference ref = _database.getReference();
        ref.child("stopFlag").setValue(stopFlag);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
