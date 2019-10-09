package gorest.tests;

import gorest.Utils.DataUtils;
import gorest.core.BaseTest;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AlbumsTest extends BaseTest {

    @Test
    public void criarNovoAlbum(){

        Map<String, String> album = new HashMap<String, String>();
        album.put("user_id", "1859");
        album.put("title", "Album de fotos de viagens");

        given()
                .body(album)
                .when()
                .post("/albums")
                .then()
                .statusCode(302)
                .body("result.id", Matchers.notNullValue())
                .body("result.user_id", Matchers.is("1859"))
                .body("result.title", Matchers.is("Album de fotos de viagens"))
                .body("_meta.code", Matchers.is(201))
                .body("_meta.success", Matchers.is(true))

        ;

    }
    @Test
    public void editarAlbum() {

        Map<String, String> album = new HashMap<String, String>();
        album.put("title", "Album de fotos de viagens - updating");

        given()
                .body(album)
                .when()
                .put("/albums/12")
                .then()
                .statusCode(200)
                .body("result.title", Matchers.is("Album de fotos de viagens - updating"))
                .body("_meta.code", Matchers.is(200))
                .body("_meta.success", Matchers.is(true))

        ;
    }
    @Test
    public void deletarAlbum(){
        given()
                .when()
                .delete("/albums/2")
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
                .post("/albums")
                .then()
                .statusCode(200)
                .body("result", Matchers.hasSize(2))//array Raiz $
                .body("result.message", Matchers.hasItems(
                        "User ID cannot be blank.",
                        "Title cannot be blank."
                ))
                .body("_meta.code", Matchers.is(422))
                .body("_meta.success", Matchers.is(false))

        ;
    }

    @Test
    public void albumNaoEncontrado(){

        given()
                .when()
                .get("/albums/100000")
                .then()
                .statusCode(200)
                .body("_meta.code", Matchers.is(404))
                .body("_meta.success", Matchers.is(false))
                .body("result.name", Matchers.is("Not Found"))
        ;
    }
}
