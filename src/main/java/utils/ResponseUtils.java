package utils;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.core.report.ListReportProvider;
import com.github.fge.jsonschema.core.report.LogLevel;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.ResponseSpecification;
import lombok.experimental.UtilityClass;

import java.util.List;

import static org.hamcrest.Matchers.*;

@UtilityClass
public class ResponseUtils {
    public ValidatableResponse getResponse() {
        return RequestUtils.getResponse();
    }

    public String getStringFromResponseInJsonPath(String jsonPath) {
        return getResponse()
                .extract()
                .jsonPath()
                .getString(jsonPath);
    }

    public <T> T getObjectFromResponseInJsonPath( Class<T> type, String jsonPath) {
        return getResponse()
                .extract()
                .jsonPath()
                .getObject(jsonPath, type);
    }

    public List<Object> getListOfObjectsFromResponse(String jsonPath) {
        return getResponse()
                .extract()
                .jsonPath()
                .getList(jsonPath);
    }

    public void validateJsonValue(String jsonPath, Object value) {
        getResponse()
                .assertThat()
                .body(jsonPath, equalTo(value));
    }

    public void validateStatusCode(int statusCode) {
        getResponse()
                .assertThat()
                .statusCode(statusCode);
    }

    public void validateResponseUsingJsonSchema(String schemaPath) {
        final JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory
                .newBuilder()
                .setValidationConfiguration(ValidationConfiguration
                        .newBuilder()
                        .setDefaultVersion(SchemaVersion.DRAFTV4)
                        .freeze()
                )
                .setReportProvider(new ListReportProvider(LogLevel.ERROR, LogLevel.ERROR))
                .freeze();
        getResponse()
                .spec(getResponseSpecification())
                .assertThat()
                .body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath(schemaPath)
                        .using(jsonSchemaFactory)
                );
    }

    public int getResponseArraySize(String arrayName) {
        return
                getResponse()
                        .extract()
                        .jsonPath()
                        .getList(arrayName)
                        .size();
    }

    public void printBody() {
        getResponse().extract().response().prettyPrint();

    }

    public void verifyKeyAvailability(String objectName, String key){
        getResponse()
                .assertThat()
                .body(objectName,hasItem(key));

    }

    private ResponseSpecification getResponseSpecification() {
        ResponseSpecBuilder specBuilder = new ResponseSpecBuilder();
        return specBuilder
                .expectHeader("Content-Type","application/json; charset=utf-8")
                .build();

    }

    public <T> T getResponseAsObject(Class<T> aClass){
        return getResponse().extract().as(aClass);
    }
}
