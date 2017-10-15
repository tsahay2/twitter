package com.barclays.dto;

public class UserSample {

    public String username;
    public long id;
    public String registrationId;

    public UserSample(String username, long id, String registrationId) {
        this.username = username;
        this.id = id;
        this.registrationId = registrationId;
    }

    public UserSample() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public UserSample(String username, long id) {
        this.username = username;
        this.id = id;
    }
}
