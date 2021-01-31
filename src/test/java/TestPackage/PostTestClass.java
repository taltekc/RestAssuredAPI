package TestPackage;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

public class PostTestClass {

    @Test
    public void getPost(){

        RestAssured.baseURI="http://thetestingworldapi.com";
        Response response = RestAssured.given()
                .contentType("application/json")
                .body("{\n" +
                        "  \"first_name\": \"sample string 2\",\n" +
                        "  \"middle_name\": \"sample string 3\",\n" +
                        "  \"last_name\": \"sample string 4\",\n" +
                        "  \"date_of_birth\": \"sample string 5\"\n" +
                        "}")
                .post("api/studentsDetails");

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());

        JsonPath js = response.jsonPath();
        System.out.println(js.get("id"));
        System.out.println(js.get("first_name"));

    }


    @Test
    public void get_st_details(){

        RestAssured.baseURI="http://thetestingworldapi.com";

        Response res=RestAssured.given()
                .header("Content_Type","application/json")
                .accept("ContentType.JSON")
                .get("api/studentsDetails/88392");


        System.out.println(res.getBody().asString());
    }







    @Test
    public void getPost2(){

        RestAssured.baseURI="https://reqres.in/";

        Response res=RestAssured.given()
                .contentType("application/json")
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .post("/api/users");

        System.out.println(res.getStatusCode());
        System.out.println(res.getBody().asString());

    }


    @Test
    public void getCall2(){

        RestAssured.baseURI="https://reqres.in/";

        Response res=RestAssured.given()
                .header("Content_Type","application/json")
                .accept("ContentType.JSON")
                .get("\n" +
                        "/api/users/2");


        System.out.println(res.getBody().asString());
    }




}
