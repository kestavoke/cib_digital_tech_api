package za.co.absa.config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;

public class ReuseableSpecifications {

    public static RequestSpecBuilder rspec;
    public static RequestSpecification requestSpecification;

    public static ResponseSpecBuilder respec;
    public static ResponseSpecification responseSpecification;


    public static RequestSpecification getGenericRequestSpec() {

        //RestAssured.proxy("localhost", 8888); // comment this out if you aren't running REST Assured through a proxy

        rspec = new RequestSpecBuilder();
        rspec.setContentType(ContentType.JSON).
                setBaseUri("https://dog.ceo").
                setBasePath("/api/");
        requestSpecification = rspec.build();
        return requestSpecification;
    }

    public static ResponseSpecification getGenericResponseSpec() {
        respec = new ResponseSpecBuilder();
        respec.expectHeader("Content-Type", "application/json;charset=UTF-8");
        respec.expectHeader("Transfer-Encoding", "chunked");
        respec.expectStatusCode(200);
        respec.expectResponseTime(lessThan(60L), TimeUnit.SECONDS);
        responseSpecification = respec.build();
        return responseSpecification;

    }
}