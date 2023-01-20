package APITests;

import APISteps.RCBSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class RCBTests {

    @Steps
    public RCBSteps rcbSteps;

    @Test
    @Title("Make sure there are only four foreign players in the team sheet")
    public void ForeignPlayersCountTest() {
        rcbSteps.readsTheJsonFile();
        rcbSteps.compareExpectedForeignPlayerCount(4);
    }

    @Test
    @Title("Make sure there is atleast one wicket keeper in the team sheet")
    public void WicketKeeperTest() {
        rcbSteps.readsTheJsonFile();
        rcbSteps.teamHasAtleastOneWicketKeeper();
    }
}
