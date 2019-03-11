package JavaАlg1Lesson5.tests;

import JavaАlg1Lesson5.Exponent;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExponentTest {

    @Test
    public void calc() {
        Exponent e = new Exponent();

        int num,exp;
        num = 5;
        exp = 7;

        Assert.assertEquals((int)Math.pow(num,exp),e.calc(num,exp));

    }
}