package com.theironyard.clt;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Ultramar on 4/25/16.
 */
public class DataLog {

    public static ArrayList<Message> getMessages(String name) throws SQLException {
        ensureMessagesExists();
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");

        PreparedStatement stmnt2 = conn.prepareStatement("select messages.message from messages inner join users on messages.userId = users.id where username = ?;");
        stmnt2.setString(1, name);


        ResultSet results = stmnt2.executeQuery();
        ArrayList<Message> messages = new ArrayList<>();
        while (results.next()) {
            String text = results.getString("text");
            String username = results.getString("username");
            int id = results.getInt("id");
            Message message = new Message(id,username,text);
            messages.add(message);

        }

        return messages;
    }

    public static void addMessage(String userName, String text) throws SQLException{
        ensureMessagesExists();
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");
        //my current problem area\/
        PreparedStatement stmt = conn.prepareStatement("insert into messages values(NULL, select id from users where username = ?, ?);");

        stmt.setString(1, userName);
        stmt.setString(2, text);
        stmt.execute();

        //make a statement of add
        //execute statement

    }

    public static void deleteMessage(int messageId) throws SQLException{
        ensureMessagesExists();
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");

        PreparedStatement stmt = conn.prepareStatement("DELETE FROM MESSAGES WHERE id=?");
        stmt.setInt(1, messageId);
        stmt.execute();

    }

    public static void editMessage(String text, int messageId) throws SQLException{
        ensureMessagesExists();
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");

        PreparedStatement stmt = conn.prepareStatement("UPDATE MESSAGES SET text=? WHERE id=?");
        stmt.setString(1, text);
        stmt.setInt(2, messageId);
        //make statement of edit
        //execute statement
        stmt.execute();
    }

    public static void addUser(String userName, String firstName, String lastName)throws SQLException {
        ensureMessagesExists();
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");

        PreparedStatement stmt = conn.prepareStatement("INSERT INTO USERS VALUES(NULL, ?, ?, ?)");
        stmt.setString(1, userName);
        stmt.setString(2, firstName);
        stmt.setString(3, lastName);
        stmt.execute();
    }

    private static void ensureMessagesExists() throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");

        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS MESSAGES(id IDENTITY , userID INT, text VARCHAR);");
        Statement stmt2 = conn.createStatement();
        stmt2.execute("CREATE TABLE IF NOT EXISTS USERS(id IDENTITY , userName VARCHAR , firstName VARCHAR , lastName VARCHAR);");
        Statement stmt3 = conn.createStatement();
        stmt3.execute("ALTER TABLE USERS ADD UNIQUE(userName);");
        Statement stmt4 = conn.createStatement();
        stmt4.execute("ALTER TABLE MESSAGES ADD FOREIGN KEY(userId) REFERENCES USERS(id);");
    }

}
