package com.tushar.firebase;

import com.barclays.dto.User;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.internal.FirebaseAppStore;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class FireBaseDatabaseAdmin {

   public void initialize_firebase_db() throws IOException {
       ClassLoader classLoader = getClass().getClassLoader();
       String fileName= "marketplace-e1c39-firebase-adminsdk-ccppg-5c362afc7f.json";
       File file = new File(classLoader.getResource(fileName).getFile());
       FileInputStream serviceAccount = new FileInputStream(file);
       FirebaseOptions options = new FirebaseOptions.Builder()
               .setCredentials(GoogleCredentials.fromStream(serviceAccount))
               .setDatabaseUrl("https://marketplace-e1c39.firebaseio.com/")
               .build();
       FirebaseApp.initializeApp(options);

       final FirebaseDatabase database = FirebaseDatabase.getInstance();
       DatabaseReference ref = database.getReference("server/saving-data/fireblog");
       DatabaseReference usersRef = ref.child("users");
       Map<String, User> users = new HashMap<String, User>();
       users.put("alanisawesome", new User("June 23, 1912", "Alan Turing"));
       users.put("gracehop", new User("December 9, 1906", "Grace Hopper"));
       usersRef.setValue(users);

   }

}
