package utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;

import java.io.StringWriter;
import java.util.Map;
import static io.restassured.RestAssured.given;
@UtilityClass

public class RequestUtils {
    private ValidatableResponse response;

    public ValidatableResponse getResponse() {
        return response;
    }

    public String editJson(String pathOfFileToManipulate, Map<String, Object> valuesToReplaceWith) {
        String editedJson = null;
        Configuration freeMarkerConfig = new Configuration(new Version("2.3.23"));
        freeMarkerConfig.setClassForTemplateLoading(ResponseUtils.class,"/");
        try (StringWriter out = new StringWriter()) {
            Template freeMarkerTemplate = freeMarkerConfig.getTemplate(pathOfFileToManipulate);
            freeMarkerTemplate.process(valuesToReplaceWith, out);
            editedJson = out.getBuffer().toString();
        } catch (Exception ex) {
//             Logger.log(TemplateException.getMessage());
//            ex.getMessageWithoutStackTop()
        }
        return editedJson;
    }


    public void get(String endpoint) {
        response = given()
                .spec(getRequestSpecification())
                .when()
                .get(endpoint)
                .then()
                .log()
                .ifError();

    }

    public void post(String endpoint, Object body) {
        response = given()
                .spec(getRequestSpecification())
                .body(body)
                .post(endpoint)
                .then()
                .log()
                .ifError();

    }



    public void delete(String endpoint) {
        response = given()
                .spec(getRequestSpecification())
                .when()
                .delete(endpoint)
                .then()
                .log()
                .ifError();
    }

    public void put(String endpoint, Object body) {
        response = given()
                .spec(getRequestSpecification())
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .log()
                .ifError();
    }


    private RequestSpecification getRequestSpecification() {
        RequestSpecBuilder specBuilder = new RequestSpecBuilder();
        return specBuilder
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }
}
