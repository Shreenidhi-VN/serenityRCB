package APITests;

import APISteps.RCBSteps;
import net.serenitybdd.core.annotations.events.BeforeScenario;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class RCBTests extends BaseTest {
    @Steps
    public RCBSteps rcbSteps;

    @BeforeScenario
    public void readJsonFile() {
        rcbSteps.readsTheJsonFile("src/main/resources/JsonFiles/RCBResponse.json");
    }

    @Test
    @Title("Make sure there are only four foreign players in the team sheet")
    public void ForeignPlayersCountTest() {
        rcbSteps.readParticularData(rcbForeignPlayers);
        rcbSteps.compareExpectedForeignPlayerCount(4);
    }

    @Test
    @Title("Make sure there is at least one wicket keeper in the team sheet")
    public void WicketKeeperTest() {
        rcbSteps.readParticularData(rcbForeignPlayers);
        rcbSteps.teamHasAtLeastOneWicketKeeper();
    }


}
