package com.bloodapp.blood;

public class Reciever {

    String userID;
    String userName;
    String userBlood;
    String userAge;
    String userNumber;
    String userVerify;

    public Reciever()
    {

    }

    public Reciever(String userID, String userName, String userBlood, String userAge, String userNumber, String userVerify) {
        this.userID = userID;
        this.userName = userName;
        this.userBlood = userBlood;
        this.userAge = userAge;
        this.userNumber = userNumber;
        this.userVerify = userVerify;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserBlood() {
        return userBlood;
    }

    public String getUserAge() {
        return userAge;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public String getUserVerify()
    {
        return userVerify;
    }
}
