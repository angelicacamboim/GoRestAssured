package gorest.core;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class BaseTest implements Constants {

    @BeforeClass
    public static void setup(){
        RestAssured.baseURI = APP_BASE_URL;
       // RestAssured.port = APP_PORT;
        RestAssured.basePath = APP_BASE_PATH;

        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        reqBuilder.setContentType(APP_CONTENT_TYPE);
        RestAssured.requestSpecification = reqBuilder.build(); //JSON

        ResponseSpecBuilder resBuilder =  new ResponseSpecBuilder();
        resBuilder.expectResponseTime(Matchers.lessThan(MAX_TIMEOUT));
        RestAssured.responseSpecification = resBuilder.build();

        System.out.println("PASSOU AQUI - BASE TEST");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
