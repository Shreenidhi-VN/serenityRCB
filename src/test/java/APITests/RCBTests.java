package APITests;

import APISteps.RCBSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(SerenityRunner.class)
//Lets the serenity know that this class has tests that needs reporting
public class RCBTests extends BaseTest {
    @Steps
    public RCBSteps rcbSteps;
    //this is the class that has methods to validate things asked in tests

    @Test
    @Title("Write a test that validates that team has only 4 foreign players")
    public void ForeignPlayersCountTest() throws IOException {
        rcbSteps.readsTheJsonFile(BaseTest.filePathForJson, BaseTest.jaywayPathForRcbForeignPlayers);
        rcbSteps.compareExpectedForeignPlayerCount(4);//in this case the maximum foreign player count is 4.
    }

    @Test
    @Title("Write a test that validates that there is at least one wicket keeper")
    public void WicketKeeperTest() throws IOException {
        rcbSteps.readsTheJsonFile(BaseTest.filePathForJson, BaseTest.jaywayPathForWicketKeeperCount);
        rcbSteps.teamHasAtLeastOneWicketKeeper();
    }
}
