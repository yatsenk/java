import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Base64;

public class Database {
    private static final String serverurl = "jdbc:mysql://localhost:3306/"; //brew install mysql && mysql.service start
    private static final String database = "chatDB";
    private static final String timeZone = "?serverTimezone=Europe/Moscow";
    private static final String user = "root";
    private static final String password = "";
    Connection connection;
    Statement statement;

    public Database() {
        try {
            //Creating DB because paranoia
            connection = DriverManager.getConnection(serverurl+timeZone,user,password);
            statement = connection.createStatement();
            statement.execute("CREATE DATABASE IF NOT EXISTS " + database);
            connection.close();

            //Connecting DB and creating tables
            connection = DriverManager.getConnection(serverurl+database+timeZone,user,password);
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "id INT UNSIGNED NOT NULL AUTO_INCREMENT, " +
                    "username CHAR(20) NOT NULL," +
                    "password CHAR(60) NOT NULL," +
                    "fullname CHAR(40)," +
                    "PRIMARY KEY (ID)," +
                    "UNIQUE (username))");
        } catch (SQLException throwables) {
            throw new RuntimeException("Connection fault.");
        }
    }

    public boolean addUser(String username, String password, String fullname){
        try {
            //Check that user does not exists, or mysql will fail.
            if (statement.executeQuery("SELECT id FROM users WHERE username = '" + username+"'").next()) return false;
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users VALUES(NULL,?,?,?)");
            preparedStatement.setString(1,username);
            preparedStatement.setString(2, myHash(password));
            preparedStatement.setString(3,fullname);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("Adding user get wrong");
        }
    }

    public boolean checkUser(String username, String password){
        try {
            return statement.executeQuery("SELECT id FROM users WHERE username = '"+username+"' AND password = '"+ myHash(password)+"'").next();
        } catch (SQLException throwables) {
            throw new RuntimeException("Checking user get wrong");
        }
    }

    public boolean renameUser(String username, String password, String newUsername, String newFullname){
        try {
            //Check if new name not in use.
            if (statement.executeQuery("SELECT id FROM users WHERE username = '" + newUsername+"'").next()) return false;
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET username = ?, fullname = ? WHERE username = ? AND password = ?");
            preparedStatement.setString(1,newUsername);
            preparedStatement.setString(2,newFullname);
            preparedStatement.setString(3,username);
            preparedStatement.setString(4, myHash(password));
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new RuntimeException("Checking user get wrong");
        }
    }

    //Not shure about SHA-256, but much better than nothing.
    public String myHash(String password){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return Base64.getEncoder().encodeToString((digest.digest(password.getBytes())));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Sha-generator was failed.");
        }
    }
}
