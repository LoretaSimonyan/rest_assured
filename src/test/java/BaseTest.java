import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import utils.PropertiesReader;

abstract public class BaseTest {
    private final static PropertiesReader API_PROPERTIES = PropertiesReader.of("api.properties");

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI =API_PROPERTIES.getProperty("BASE_URI");
    }
}
