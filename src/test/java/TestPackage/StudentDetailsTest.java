package TestPackage;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.config.EncoderConfig.encoderConfig;


public class StudentDetailsTest {

    int id;

    @Test
    public void postTest(){

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
        id=js.get("id");
        System.out.println(id);
        response.prettyPrint();


    }

    @Test(dependsOnMethods = {"postTest"})
    public void getTest(){

        RestAssured.baseURI="http://thetestingworldapi.com";

        Response res=RestAssured.given()
                .header("Content_Type","application/json")
                .accept("ContentType.JSON")
                .get("api/studentsDetails/"+id);


        System.out.println(res.getBody().asString());
    }

    @Test
    public void putTest(){

        RestAssured.baseURI="http://thetestingworldapi.com";
        Response response = RestAssured.given()
                .config(RestAssured
                        .config().encoderConfig(encoderConfig()
                                .encodeContentTypeAs("ContentType.JSON", ContentType.JSON)))
                .contentType("application/json")
                .body("{\n" +
                        "  \"first_name\": \"sample string 7872\",\n" +
                        "  \"middle_name\": \"sample string 3\",\n" +
                        "  \"last_name\": \"sample string 4\",\n" +
                        "  \"date_of_birth\": \"sample string 5\"\n" +
                        "}")
                .put("api/studentsDetails/88837");

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());

//        JsonPath js = response.jsonPath();
//        System.out.println(js.get("id"));
//        System.out.println(js.get("first_name"));
//        response.prettyPrint();
    }


    @Test(dependsOnMethods = {"getTest"})
    public void deleteTest(){

        RestAssured.baseURI="http://thetestingworldapi.com";

        Response res=RestAssured.given()
                .header("Content_Type","application/json")
                .accept("ContentType.JSON")
                .delete("api/studentsDetails/"+id);
        System.out.println(res.getStatusCode());
        System.out.println(res.getBody().asString());

    }



}
