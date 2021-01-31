package TestPackage;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;


public class PutPatch {

    @Test
    public void putTest(){

        RestAssured.baseURI="https://reqres.in/";

        Response res=RestAssured.given()
                .contentType("application/json")
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"TTech golden boys \"\n" +
                        "}")
                .put("/api/users/2");

        System.out.println(res.getStatusCode());
        System.out.println(res.getBody().asString());
    }

    @Test
    public void patchTest(){


        RestAssured.baseURI="https://reqres.in/";

        Response res=RestAssured.given()
                .contentType("application/json")
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"TTech golden boys \"\n" +
                        "}")
                .put("/api/users/2");

        System.out.println(res.getStatusCode());
        System.out.println(res.getBody().asString());

    }





}
