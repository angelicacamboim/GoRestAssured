package gorest.tests;

import gorest.core.BaseTest;
import io.restassured.RestAssured;
import io.restassured.specification.FilterableRequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class AuthTest extends BaseTest {

    @Test
    public void naoDeveAcessarAPISemTokenUsers(){
        FilterableRequestSpecification req =  (FilterableRequestSpecification) RestAssured.requestSpecification;
        req.removeHeader("Authorization");

        given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("_meta.code", Matchers.is(401))
                .body("_meta.success", Matchers.is(false))
                .body("result.message", Matchers.is("Your request was made with invalid credentials."))

        ;
    }

    @Test
    public void naoDeveAcessarAPISemTokenPhotos(){
        FilterableRequestSpecification req =  (FilterableRequestSpecification) RestAssured.requestSpecification;
        req.removeHeader("Authorization");

        given()
                .when()
                .get("/photos")
                .then()
                .statusCode(200)
                .body("_meta.code", Matchers.is(401))
                .body("_meta.success", Matchers.is(false))
                .body("result.message", Matchers.is("Your request was made with invalid credentials."))

        ;
    }

    @Test
    public void naoDeveAcessarAPISemTokenPosts(){
        FilterableRequestSpecification req =  (FilterableRequestSpecification) RestAssured.requestSpecification;
        req.removeHeader("Authorization");

        given()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("_meta.code", Matchers.is(401))
                .body("_meta.success", Matchers.is(false))
                .body("result.message", Matchers.is("Your request was made with invalid credentials."))

        ;
    }

    @Test
    public void naoDeveAcessarAPISemTokenComments(){
        FilterableRequestSpecification req =  (FilterableRequestSpecification) RestAssured.requestSpecification;
        req.removeHeader("Authorization");

        given()
                .when()
                .get("/comments")
                .then()
                .statusCode(200)
                .body("_meta.code", Matchers.is(401))
                .body("_meta.success", Matchers.is(false))
                .body("result.message", Matchers.is("Your request was made with invalid credentials."))

        ;
    }
}
