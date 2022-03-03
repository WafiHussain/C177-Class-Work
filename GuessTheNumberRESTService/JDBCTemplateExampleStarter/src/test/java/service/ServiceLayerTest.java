package service;

import com.sg.guessthenumber.TestApplicationConfiguration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class LayeredServiceTest {

    // Service layer variable
    @Autowired
    LayeredService service;

    // Destructor
    public LayeredServiceTest() {
    }

    // Testing the determined results by adding numbers in
    @Test
    public void testDetermineResult() {
        String guess = "1234";
        String ans = "2159";
        String res = service.determineTheResult(guess, ans);

        assertEquals("e:0:p:2", res);
    }

    @Test
    public void testDetermineResult2() {
        String guess = "1234";
        String ans = "4321";
        String res = service.determineTheResult(guess, ans);

        assertEquals("e:0:p:4", res);
    }

    @Test
    public void testDetermineResult3() {
        String guess = "1234";
        String ans = "4321";
        String res = service.determineTheResult(guess, ans);

        assertEquals("e:0:p:4", res);
    }

    @Test
    public void testDetermineResult4() {
        String guess = "1234";
        String ans = "1324";
        String res = service.determineTheResult(guess, ans);

        assertEquals("e:0:p:0", res);
    }
}