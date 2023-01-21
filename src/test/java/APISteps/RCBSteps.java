package APISteps;

import APITests.BaseTest;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.thucydides.core.annotations.Step;
import java.io.*;
import java.util.List;
import org.json.simple.JSONObject;

public class RCBSteps extends BaseTest {
    //the step class has the methods to implement tests
    List<String> foreignPlayers, wicketKeepers;//these lists will eventually have data read from the json file

    @Step//every method annotated with step gets mentioned in the report
    public void readsTheJsonFile(String filePath, String jayWayPath) throws IOException {
        JSONObject jsonContent = BaseTest.readJsonFile(filePath); //read the json file from the given path
        DocumentContext Json = JsonPath.parse(jsonContent); //parse the json content so that we can read jayWay paths
        if(jayWayPath.contains("country"))
            foreignPlayers = Json.read(jayWayPath);//read all the players whose country!=India
        else if(jayWayPath.contains("role"))
            wicketKeepers= Json.read(jayWayPath);//read all the players whose role=wicket-keeper
    }

    @Step
    public void compareExpectedForeignPlayerCount(int expectedForeignPlayerCount) {
        CustomAsserts.assertTrue("Is the number of foreign players less than or equal to 4?", foreignPlayers.size() <= expectedForeignPlayerCount);
    }

    @Step
    public void teamHasAtLeastOneWicketKeeper() {
        CustomAsserts.assertTrue("Is there at least one wicket keeper?", wicketKeepers.size() >= 1);
    }
}
