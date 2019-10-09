package gorest.tests;

import gorest.Utils.DataUtils;
import gorest.core.BaseTest;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostsTest extends BaseTest {


    @Test
    public void criarPost(){

        Map<String, String> post = new HashMap<String, String>();
        post.put("user_id", "1659");
        post.put("title", "TÍTULO DO POST");
        post.put("body", "Autem voluptas sed sed quisquam sit. " +
                "Rerum qui veritatis cum architecto qui. Sit sapiente rem et fuga.");

        given()
                    .body(post)
                .when()
                    .post("/posts")
                .then()
                    .statusCode(302)
                    .body("result.user_id", Matchers.is("1659"))
                    .body("result.title", Matchers.is("TÍTULO DO POST"))
                    .body("result.body", Matchers.is("Autem voluptas sed sed quisquam sit. " +
                            "Rerum qui veritatis cum architecto qui. Sit sapiente rem et fuga."))
                    .body("_meta.code", Matchers.is(201))
                    .body("_meta.success", Matchers.is(true))

        ;
    }
    @Test
    public void editarPost(){

        Map<String, String> post = new HashMap<String, String>();
        post.put("user_id", "1659");
        post.put("title", "Testing");
        post.put("body", "Et minus dolores voluptas. Rerum possimus ipsum sint. " +
                "Ut voluptate temporibus laboriosam molestiae nobis.");

        given()
                    .body(post)
                .when()
                    .put("/posts/3")
                .then()
                    .statusCode(200)
                    .body("result.title", Matchers.is("Testing"))
                    .body("result.body", Matchers.is("Et minus dolores voluptas. Rerum possimus ipsum sint." +
                            " Ut voluptate temporibus laboriosam molestiae nobis."))
                    .body("_meta.code", Matchers.is(200))
                    .body("_meta.success", Matchers.is(true))
        ;
    }
    @Test
    public void deletarPost(){

        given()
                .when()
                    .delete("/posts/10")
                .then()
                    .statusCode(200)
                .body("_meta.success", Matchers.is(true))
                .body("_meta.code", Matchers.is(204))

        ;
    }
    @Test
    public void validarCamposObrigatorios(){

        given()
                .when()
                    .post("/posts")
                .then()
                    .statusCode(200)
                    .body("result", Matchers.hasSize(3))//array Raiz $
                    .body("result.message", Matchers.hasItems(
                        "User ID cannot be blank.",
                        "Title cannot be blank.",
                        "Body cannot be blank."
                    ))
                    .body("_meta.code", Matchers.is(422))
                    .body("_meta.success", Matchers.is(false))

        ;
    }
    @Test
    public void postNaoEncontrado(){

        given()
                .when()
                    .get("/posts/22321321321")
                .then()
                    .statusCode(200)
                    .body("_meta.code", Matchers.is(404))
                    .body("_meta.success", Matchers.is(false))
                    .body("result.name", Matchers.is("Not Found"))
        ;
    }
}
