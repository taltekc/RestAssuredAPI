package TestPackage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.poi.util.SystemOutLogger;
import org.testng.annotations.Test;

public class StudentDetailsGetCall {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getTest() throws JsonProcessingException {

        RestAssured.baseURI="http://thetestingworldapi.com";

        Response res=RestAssured.given()
                .header("Content_Type","application/json")
                .accept("ContentType.JSON")
                .get("api/studentsDetails/88936");


        String body = res.getBody().asString();
        JsonNode bd = mapper.readTree(body);
//        System.out.println(bd);
//        System.out.println(bd.path("data"));
//        System.out.println(bd.path("data").get("id"));

        Object id=bd.path("data").get("id").toString();
        System.out.println(id);
    }



    @org.junit.Test
    public void getCall2() throws JsonProcessingException {

        RestAssured.baseURI="https://reqres.in/";

        Response res=RestAssured.given()
                .header("Content_Type","application/json")
                .accept("ContentType.JSON")
                .get("\n" +
                        "/api/users?page=2");


        JsonNode js = mapper.readTree(res.getBody().asString());

//        System.out.println(js.path("data").get(1));
//        System.out.println(js.path("data").get(1).get("first_name"));
//        System.out.println(js.path("data").get("first_name"));

        System.out.println(js.size());

        for(int i=0;i<js.size();i++){

            System.out.println(js.path("data").get(i).get("first_name"));
        }
    }

}
