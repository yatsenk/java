import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Testing extends Assert {
    static Database d;

    @Before
    public void prepare(){
        try {
            d = new Database();
            d.statement.execute("DROP TABLE users");
            d.connection.close();
            d = new Database();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void test(){
        //Trying adding and double user
        assertTrue(d.addUser("me","verysecure","fullme"));
        assertFalse(d.addUser("me","verysecure","fullme"));

        //Checking existing and not existing user
        assertTrue(d.checkUser("me","verysecure"));
        assertFalse(d.checkUser("somebody","verysecure"));

        //Renaming existing and not existing user
        assertTrue(d.renameUser("me","verysecure","somebody","fullme"));
        assertFalse(d.renameUser("me","verysecure","somebody","fullme"));

        //Adding user again
        assertTrue(d.addUser("me","topsecure","fullme"));

        //Checking database condition
        try {
            ResultSet rs;
            rs = d.statement.executeQuery("SELECT id FROM users WHERE username = 'somebody' AND password = '"+d.myHash("verysecure")+"'");
            rs.next();
            assertEquals(1, rs.getInt("id"));

            rs = d.statement.executeQuery("SELECT id FROM users WHERE username = 'me' AND password = '"+d.myHash("topsecure")+"'");
            rs.next();
            assertEquals(2, rs.getInt("id"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
