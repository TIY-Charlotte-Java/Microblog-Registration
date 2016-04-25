package com.theironyard.clt;

/**
 * Created by Ultramar on 4/19/16.
 */
public class Message {
    public int ID;
    public String userName;
    public String text;

    public Message(int ID,String userName,String text) {
        this.ID = ID;
        this.userName = userName;
        this.text = text;
    }

    @Override
    public String toString() {
        return String.format("%s. %s by %s",ID, text, userName);
    }
}