package gorest.suites;

import gorest.core.BaseTest;
import gorest.tests.*;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@RunWith(org.junit.runners.Suite.class)
@org.junit.runners.Suite.SuiteClasses({
       UsersTest.class,
        PostsTest.class,
        CommentsTest.class,
        AlbumsTest.class,
        PhotosTest.class,
        AuthTest.class

})
public class Suite extends BaseTest {

    @BeforeClass
    public static void token(){
        String TOKEN = "nmaxiMWKOaz3cinLrJ6FQeKRo6yYWN6CaQFX";
        RestAssured.requestSpecification.header("Authorization", "Bearer " + TOKEN);

        System.out.println("PASSOU AQUI TOKEN");

    }

}
