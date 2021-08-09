import dataprovider.CreateUser;
import io.restassured.response.ValidatableResponse;
import model.Address;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RequestUtils;
import utils.ResponseUtils;

import java.util.List;

public class UserTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(UserTest.class);

    @Test
    public void getFirstUser() {
        RequestUtils.get("/users/1");
        ResponseUtils.validateStatusCode(200);
    }

    @Test
    public void postUserWithFreemarkerTest() {
        CreateUser createUser = new CreateUser();
        createUser.generateUserBodyWithFreemarker(
                "jdna",
                "jfsl",
                "dnjkavko",
                "hkwcs",
                "inci");
        String user = createUser.getCreatePostRequestBody();
        RequestUtils.post("/users", user);

        ResponseUtils.validateResponseUsingJsonSchema("schemas/user.json");
        ResponseUtils.validateStatusCode(201);
   //     ResponseUtils.validateJsonValue("id", 11);
        Assert.assertEquals(ResponseUtils.getStringFromResponseInJsonPath("id"), "11");
    }

    @Test
    public void postUserWithObject() {
        User user = CreateUser.generateUserBodyWithUserClass();
        RequestUtils.post("/users", user);
        ResponseUtils.validateStatusCode(201);
        User newCreatedUser = ResponseUtils.getResponseAsObject(User.class);
        Assert.assertEquals(user.getName(), newCreatedUser.getName());
        Address newUserAddress = ResponseUtils.getObjectFromResponseInJsonPath(Address.class, "address");
        Assert.assertEquals(user.getAddress().getCity(), newUserAddress.getCity());
    }

    @Test
    public void getUserTest() {
         RequestUtils.get("/users");
         ResponseUtils.validateStatusCode(200);

    }

}
