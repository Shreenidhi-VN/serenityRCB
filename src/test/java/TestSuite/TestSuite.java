package TestSuite;

import APITests.RCBTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( {
        //Here we list all the tests that need to be executed as a part of this suite
    RCBTests.class,
})

public class TestSuite{

}
