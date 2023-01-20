package APITests;

import net.serenitybdd.core.Serenity;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;


public class BaseTest {
    public static Properties confProp;
    public static String rcbForeignPlayers;

    static {
        try {
        confProp=getProperties("src/main/resources/Config/jawaypaths.properties");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Properties getProperties(String sPropertiesFile) throws IOException {
        InputStream input=new FileInputStream(sPropertiesFile);
        Properties prop=new Properties();
        prop.load(input);
        return prop;
    }
    public static void readJayWayPaths(){
        rcbForeignPlayers=confProp.getProperty("rcb.foreign.players");
    }

    public static void logger(String sLogHeader,String sLog){
        serenityReports.reportDataAsContent(sLogHeader,sLog.toString());
    }

    public static class serenityReports{
        public static void reportFile(String sTitle,String outputReportFile,boolean isDownloadable) throws IOException {
            Path generatedReport= Paths.get(outputReportFile);
            if(isDownloadable){
                Serenity.recordReportData().withTitle(sTitle).downloadable().fromFile(generatedReport);
            }else{
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
            assertThat(true).withFailMessage(msg,"expected [true] and was :[%s]",expected).isEqualTo(expected);
        }
        public static void assertEquals(String msg, int actual,int expected) {
            logger(msg, msg + String.format("-Comparing [%s] and [%s]", expected,actual));
            assertThat(actual).withFailMessage(msg,"expected [%s] is equal to [%s]",expected,actual).isEqualTo(expected);
        }
    }
}
