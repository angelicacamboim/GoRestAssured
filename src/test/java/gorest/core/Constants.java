package gorest.core;

import io.restassured.http.ContentType;

public interface Constants {

    String APP_BASE_URL = "https://gorest.co.in/";
    //Integer APP_PORT = 443; //http - 80
    String APP_BASE_PATH = "public-api";

    ContentType APP_CONTENT_TYPE = ContentType.JSON;

    Long MAX_TIMEOUT = 12000L;

}
