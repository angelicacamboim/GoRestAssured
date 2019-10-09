package gorest.Utils;

import io.restassured.RestAssured;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DataUtils {

    public static String getUserId(String firstName){
        return RestAssured.get("/users?first_name=" + firstName).then().extract().path("result.id[0]");
    }
    public static String getPostId(String title){
        return RestAssured.get("/posts?title=" + title).then().extract().path("result.id[0]");
    }

    public static String getCommentsIdByName(String name){
        return RestAssured.get("/comments?name=" + name).then().extract().path("result.id[0]");
    }

    public static String getalbumsIdByTitle(String title){
        return RestAssured.get("/albums?title=" + title).then().extract().path("result.id[0]");
    }

    public static String getPhotoIdByTitle(String title){
        return RestAssured.get("/photos?title=" + title).then().extract().path("result.id[0]");
    }


    public static String getNameGenerator(){

        String[] Beginning = { "Kr", "Ca", "Ra", "Mrok", "Cru",
                "Ray", "Bre", "Zed", "Drak", "Mor", "Jag", "Mer", "Jar", "Mjol",
                "Zork", "Mad", "Cry", "Zur", "Creo", "Azak", "Azur", "Rei", "Cro",
                "Mar", "Luk" };
       String[] Middle = { "air", "ir", "mi", "sor", "mee", "clo",
                "red", "cra", "ark", "arc", "miri", "lori", "cres", "mur", "zer",
                "marac", "zoir", "slamar", "salmar", "urak" };
        String[] End = { "d", "ed", "ark", "arc", "es", "er", "der",
                "tron", "med", "ure", "zur", "cred", "mur" };

        Random rand = new Random();

        return Beginning[rand.nextInt(Beginning.length)] +
                    Middle[rand.nextInt(Middle.length)]+
                    End[rand.nextInt(End.length)];

        }
}
