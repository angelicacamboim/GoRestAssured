package gorest.tests;

import gorest.Utils.DataUtils;
import gorest.core.BaseTest;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PhotosTest extends BaseTest {

    @Test
    public void criarNovaFoto(){

        Map<String, String> photo = new HashMap<String, String>();
        photo.put("album_id", "1349");
        photo.put("title", "United States");
        photo.put("url", "http://lorempixel.com/output/city-q-c-640-480-3.jpg");
        photo.put("thumbnail", "http://lorempixel.com/output/city-h-c-216-256-3.jpg");


        given()
                .body(photo)
                .when()
                .post("/photos")
                .then()
                .statusCode(302)
                .body("result.id", Matchers.notNullValue())
                .body("result.album_id", Matchers.is("1349"))
                .body("result.title", Matchers.is("United States"))
                .body("result.url", Matchers.is("http://lorempixel.com/output/city-q-c-640-480-3.jpg"))
                .body("result.thumbnail", Matchers.is("http://lorempixel.com/output/city-h-c-216-256-3.jpg"))
                .body("_meta.code", Matchers.is(201))
                .body("_meta.success", Matchers.is(true))

        ;

    }
    @Test
    public void editarFoto(){

        Map<String, String> photo = new HashMap<String, String>();
        photo.put("title", "United States update");
        photo.put("url", "http://lorempixel.com/g/400/200/");
        photo.put("thumbnail", "http://lorempixel.com/output/city-h-c-216-256-1.jpg");


        given()
                .body(photo)
                .when()
                .put("/photos/15")
                .then()
                .statusCode(200)
                .body("result.title", Matchers.is("United States update"))
                .body("result.url", Matchers.is("http://lorempixel.com/g/400/200/"))
                .body("result.thumbnail", Matchers.is("http://lorempixel.com/output/city-h-c-216-256-1.jpg"))
                .body("_meta.code", Matchers.is(200))
                .body("_meta.success", Matchers.is(true))

        ;

    }

    @Test
    public void validarCamposObrigatorios(){

        given()
                .when()
                .post("/photos")
                .then()
                .statusCode(200)
                .body("result", Matchers.hasSize(4))//array Raiz $
                .body("result.message", Matchers.hasItems(
                        "Album ID cannot be blank.",
                        "Title cannot be blank.",
                        "Photo Url cannot be blank.",
                        "Photo Thumbnail Url cannot be blank."
                        ))
                .body("_meta.code", Matchers.is(422))
                .body("_meta.success", Matchers.is(false))

        ;
    }

    @Test
    public void deletarPhoto(){

        given()
                .when()
                .delete("/photos/21")
                .then()
                .statusCode(200)
                .body("_meta.code", Matchers.is(204))
                .body("_meta.success", Matchers.is(true))
        ;
    }
    @Test
    public void photoNaoEncontrada(){

        given()
                .when()
                .get("/photos/100000")
                .then()
                .statusCode(200)
                .body("_meta.code", Matchers.is(404))
                .body("_meta.success", Matchers.is(false))
                .body("result.name", Matchers.is("Not Found"))
        ;
    }

}
