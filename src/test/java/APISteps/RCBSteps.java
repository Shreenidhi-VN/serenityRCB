package APISteps;

import APITests.BaseTest;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.thucydides.core.annotations.Step;

import java.io.File;
import java.util.List;

public class RCBSteps extends BaseTest {
    DocumentContext Json;
    List<String> foreignPlayers,wicketKeepers;
    public void readsTheJsonFile(String filePath) {
        File jsonContent=new File(filePath);
        Json= JsonPath.parse(jsonContent.toString());
    }
    @Step
    public void compareExpectedForeignPlayerCount(int expectedForeignPlayerCount) {
        CustomAsserts.assertTrue("Is the number of foreign players less than or equal to 4?",foreignPlayers.size()<=4);
    }
    @Step
    public void teamHasAtLeastOneWicketKeeper() {

    }

    public void readParticularData(String jayWayPath) {
        foreignPlayers=Json.read(jayWayPath);

    }
}
