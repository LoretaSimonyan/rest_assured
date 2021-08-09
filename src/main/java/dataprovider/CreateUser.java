package dataprovider;

import com.google.common.collect.ImmutableMap;
import model.Address;
import model.Company;
import model.Geo;
import model.User;
import utils.RandomData;
import utils.RequestUtils;

import java.util.Map;


public class CreateUser {

    private final String USER_BODY_TEMPLATE = "templates/user.ftl";
    private String createPostRequestBody;

    public String getCreatePostRequestBody() {
        return createPostRequestBody;
    }

    public void generateUserBodyWithFreemarker(String name, String username, String email, String street, String companyName) {
        Map<String, Object> createPostData = ImmutableMap.of(
                "name", name,
                "username", username,
                "email", email,
                "addressStreet", street,
                "companyName", companyName);
        this.createPostRequestBody = RequestUtils.editJson(USER_BODY_TEMPLATE, createPostData);
    }

    public static User generateUserBodyWithUserClass() {
        return new User.UserBuilder()
                .setName(RandomData.randomStringGenerator(4))
                .setUsername(RandomData.randomStringGenerator(4))
                .setEmail(RandomData.randomStringGenerator(4) + System.currentTimeMillis() + "@iof.com")
                .setWebsite(RandomData.randomStringGenerator(4))
                .setPhone(RandomData.randomStringGenerator(10))
                .setCompany(new Company.CompanyBuilder()
                        .setName("jgsdnl")
                        .setBs("jgrnksdl")
                        .setCatchPhrase("jgnsdlv")
                        .build())
                .setAddress(new Address.AddressBuilder()
                        .setStreet(RandomData.randomStringGenerator(4))
                        .setSuite(RandomData.randomStringGenerator(4))
                        .setCity(RandomData.randomStringGenerator(4))
                        .setZipcode(RandomData.randomStringGenerator(4))
                        .setGeo(new Geo.GeoBuilder()
                                .setLang(RandomData.randomStringGenerator(4))
                                .setLat(RandomData.randomStringGenerator(4))
                                .build()).build()).build();
    }

}
