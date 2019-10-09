package gorest.tests;

import gorest.Utils.DataUtils;
import gorest.core.BaseTest;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CommentsTest extends BaseTest {

    @Test
    public void criarNovoComentario(){

        Map<String, String> comment = new HashMap<String, String>();
        comment.put("post_id", "4");
        comment.put("name", "Luke");
        comment.put("email", "test@teste.com");
        comment.put("body", "Testing");


        given()
                .body(comment)
                .when()
                .post("/comments")
                .then()
                .statusCode(302)
                .body("result.id", Matchers.notNullValue())
                .body("result.post_id", Matchers.is("4"))
                .body("result.name", Matchers.is("Luke"))
                .body("result.email", Matchers.is("test@teste.com"))
                .body("result.body", Matchers.is("Testing"))
                .body("_meta.code", Matchers.is(201))
                .body("_meta.success", Matchers.is(true))

        ;
    }

    @Test
    public void editarComentario(){

        Map<String, String> comment = new HashMap<String, String>();
        comment.put("name", "Luke");
        comment.put("email", "dcronin@yahoo.com");
        comment.put("body", "Testing updating");


        given()
                .body(comment)
                .when()
                .put("/comments/3")
                .then()
                .statusCode(200)
                .body("result.name", Matchers.is("Luke"))
                .body("result.email", Matchers.is("dcronin@yahoo.com"))
                .body("result.body", Matchers.is("Testing updating"))
                .body("_meta.code", Matchers.is(200))
                .body("_meta.success", Matchers.is(true))

        ;
    }

    @Test
    public void deletarComentario(){

        given()
                .when()
                .delete("/comments/7")
                .then()
                .statusCode(200)
                .body("_meta.code", Matchers.is(204))
                .body("_meta.success", Matchers.is(true))
        ;
    }

    @Test
    public void validarCamposObrigatorios(){

        given()
                .when()
                .post("/comments")
                .then()
                .statusCode(200)
                .body("result", Matchers.hasSize(4))//array Raiz $
                .body("result.message", Matchers.hasItems(
                        "Post ID cannot be blank.",
                        "Name cannot be blank.",
                        "Email cannot be blank.",
                        "Body cannot be blank."
                ))
                .body("_meta.code", Matchers.is(422))
                .body("_meta.success", Matchers.is(false))

        ;
    }

    @Test
    public void comentarioNaoEncontrado(){

        given()
                .when()
                .get("/comments/10000")
                .then()
                .statusCode(200)
                .body("_meta.code", Matchers.is(404))
                .body("_meta.success", Matchers.is(false))
                .body("result.name", Matchers.is("Not Found"))
        ;
    }
}
