package com.tushar.firebase;

import org.riversun.fcm.FcmClient;
import org.riversun.fcm.model.EntityMessage;
import org.riversun.fcm.model.FcmResponse;
import org.springframework.beans.factory.annotation.Value;


public class FCMNotificationSender {

    @Value("${fcm_server_key}")
    static String fcm_server_api_key;

    public  static void pushNotification(String registrationToken){

        FcmClient client = new FcmClient();
        // You can get from firebase console.
        // "select your project>project settings>cloud messaging"
        client.setAPIKey(fcm_server_api_key);

        // Data model for sending messages to specific entity(mobile devices,browser front-end apps)s
        EntityMessage msg = new EntityMessage();

        // Set registration token that can be retrieved
        // from Android entity(mobile devices,browser front-end apps) when calling
        // FirebaseInstanceId.getInstance().getToken();
        msg.addRegistrationToken(registrationToken);

        // Add key value pair into payload
        msg.putStringData("myKey1", "myValue1");
        msg.putStringData("myKey2", "myValue2");

        // push
        FcmResponse res = client.pushToEntities(msg);

        System.out.println(res);

    }
}
