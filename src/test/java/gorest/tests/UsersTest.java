package gorest.tests;

import gorest.Utils.DataUtils;
import gorest.core.BaseTest;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import javafx.scene.chart.PieChart;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class UsersTest extends BaseTest {

    @Test
    public void criarNovoUsuario(){
        Map<String, String> user = new HashMap<String, String>();
        user.put("first_name", "Chuck");
        user.put("last_name", "Noris");
        user.put("gender", "male");
        user.put("email", "chuck_noris@test.com" );
        user.put("status", "active");

        given()
                    .body(user)
                .when()
                    .post("/users")
                .then()
                    .statusCode(302)
                    .body("result.id", Matchers.notNullValue())
                    .body("result.first_name", Matchers.is("Chuck"))
                    .body("result.last_name", Matchers.is("Noris"))
                    .body("result.gender", Matchers.is("male"))
                    .body("result.email", Matchers.is("chuck_noris@test.com" ))
                    .body("result.status", Matchers.is("active"))
                    .body("_meta.code", Matchers.is(201))
                    .body("_meta.success", Matchers.is(true))

        ;
    }

    @Test
    public void validarCamposObrigatorios(){

        given()
                .when()
                .post("/users")
                .then()
                .statusCode(200)
                .body("result", Matchers.hasSize(4))//array Raiz $
                .body("result.message", Matchers.hasItems(
                        "Email cannot be blank.",
                        "First Name cannot be blank.",
                        "Last Name cannot be blank.",
                        "Gender cannot be blank."
                ))
                .body("_meta.code", Matchers.is(422))
                .body("_meta.success", Matchers.is(false))

        ;
    }

    @Test
    public void ValidarEmailDuplicado(){
        String firstName = DataUtils.getNameGenerator();
        String lastName = DataUtils.getNameGenerator();

        Map<String, String> user = new HashMap<String, String>();
        user.put("first_name", firstName);
        user.put("last_name", lastName);
        user.put("gender", "male");
        user.put("email", "zsmitham@example.net");
        user.put("status", "active");

        given()
                    .body(user)
                .when()
                    .post("/users")
                .then()
                    .statusCode(200)
                    .body("_meta.code", Matchers.is(422))
                    .body("_meta.success", Matchers.is(false))
                    .body("result.message", Matchers.contains("Email \"zsmitham@example.net\" has already been taken."))
        ;
    }
    @Test
    public void editarUsuario(){

        Map<String, String> user = new HashMap<String, String>();
        user.put("first_name", "John");
        user.put("last_name", "Snow");
        user.put("gender", "female");
        user.put("email", "myriam21@example.org");
        user.put("phone", "931-528-2470");
        user.put("status", "active");

        given()
                    .body(user)
                .when()
                    .put("/users/26")
                .then()
                    .statusCode(200)
                    .body("result.first_name", Matchers.is("John"))
                    .body("result.last_name", Matchers.is("Snow"))
                    .body("result.gender", Matchers.is("female"))
                    .body("result.email", Matchers.is("myriam21@example.org"))
                    .body("result.phone", Matchers.is("931-528-2470"))
                    .body("result.status", Matchers.is("active"))
                    .body("_meta.code", Matchers.is(200))
                    .body("_meta.success", Matchers.is(true))

        ;
    }
    @Test
    public void deletarUsuario(){

       given()
                .when()
                    .delete("/users/6")
                .then()
                .statusCode(200)
                .body("_meta.code", Matchers.is(204))
                .body("_meta.success", Matchers.is(true))
        ;
    }
    @Test
    public void UsuarioNaoEncontrado(){

        given()
                .when()
                    .get("/users/29089889")
                .then()
                    .statusCode(200)
                    .body("_meta.code", Matchers.is(404))
                    .body("_meta.success", Matchers.is(false))
                    .body("result.name", Matchers.is("Not Found"))
        ;
    }
}