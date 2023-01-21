package APITests;

import net.serenitybdd.core.Serenity;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;


public class BaseTest {
    public static Properties confProp;
    public static String jaywayPathForRcbForeignPlayers,jaywayPathForWicketKeeperCount;
    public static String filePathForJson="src\\main\\resources\\JsonFiles\\RCBResponse.json";

    static {
        try {
            confProp = getProperties("src/main/resources/Config/jawaypaths.properties");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @BeforeClass
    public static void init(){
        readJayWayPaths();
    }
    public static void readJayWayPaths() {
        jaywayPathForRcbForeignPlayers = confProp.getProperty("rcb.foreign.players");
        jaywayPathForWicketKeeperCount = confProp.getProperty("rcb.wicket.keepers");
    }
    public static Properties getProperties(String sPropertiesFile) throws IOException {
        InputStream input = new FileInputStream(sPropertiesFile);
        Properties prop = new Properties();
        prop.load(input);
        return prop;
    }


    public static JSONObject readJsonFile(String filePath) throws IOException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonContent = new JSONObject();
        try (FileReader reader = new FileReader(filePath))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            jsonContent = (JSONObject) obj;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        serenityReports.reportFile("The JSON file read for testing ",filePath,false);
        return jsonContent;
    }

    public static void logger(String sLogHeader, String sLog) {
        serenityReports.reportDataAsContent(sLogHeader, sLog.toString());
    }

    public static class serenityReports {
        public static void reportFile(String sTitle, String outputReportFile, boolean isDownloadable) throws IOException {
            Path generatedReport = Paths.get(outputReportFile);
            if (isDownloadable) {
                Serenity.recordReportData().withTitle(sTitle).downloadable().fromFile(generatedReport);
            } else {
                Serenity.recordReportData().withTitle(sTitle).fromFile(generatedReport);
            }
        }

        public static void reportDataAsContent(String sTitle, String contentToBeReported) {
            Serenity.recordReportData().withTitle(sTitle).andContents(contentToBeReported);
        }
    }

    public static class CustomAsserts {
        public static void assertTrue(String msg, boolean expected) {
            logger(msg, msg + String.format("-expected:[true] and was: [%s]", expected));
            assertThat(true).withFailMessage(msg, "expected [true] and was :[%s]", expected).isEqualTo(expected);
        }

        public static void assertEquals(String msg, int actual, int expected) {
            logger(msg, msg + String.format("-Comparing [%s] and [%s]", expected, actual));
            assertThat(actual).withFailMessage(msg, "expected [%s] is equal to [%s]", expected, actual).isEqualTo(expected);
        }
    }
}
