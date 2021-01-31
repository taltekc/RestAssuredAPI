package TestPackage;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import modal.listuser.Datum;
import modal.listuser.RegresGetResponse;
import org.testng.annotations.Test;
import utiltiy.StudentResponseData;
import utiltiy.Util;

import java.util.List;

public class GetCallNewWay {

    ObjectMapper mapper = new ObjectMapper();
    RequestSpecBuilder builder = new RequestSpecBuilder();
    ResponseSpecBuilder response = new ResponseSpecBuilder();


    public RequestSpecification getCommonSpec() {


        builder.setBaseUri("https://reqres.in/");
        builder.setBasePath("/api/users?page=2");
        builder.addHeader("Content_Type", "application/json");
        builder.setAccept("ContentType.JSON");
        RequestSpecification requestSpec = builder.build();
        return requestSpec;
    }

    public ResponseSpecification commonResponse(String contentType, int status){

        response.expectContentType(contentType);
        response.expectStatusCode(status);
        ResponseSpecification responseSpec = response.build();
        return responseSpec;
    }


    @Test
    public void getCall() throws JsonProcessingException {

        RestAssured.baseURI = "https://reqres.in/";

        Response res = RestAssured.given()
                .header("Content_Type", "application/json")
                .accept("ContentType.JSON")
                .get("/api/users?page=2");

        String tree = res.getBody().asString();
        JsonNode bd = mapper.readTree(res.getBody().asString());//tree
        System.out.println(bd.get("data").get(0));
        System.out.println(bd.get("data").get(0).get("last_name"));
        System.out.println(bd.size());
        for (int i = 0; i < bd.size(); i++) {
            System.out.println(bd.get("data").get(i).get("last_name"));
        }
    }



    @Test
    public void getCallSpec() throws JsonProcessingException {

        RestAssured.baseURI = "https://reqres.in/";

        Response res = RestAssured
                .given()
                .spec(getCommonSpec())
                .get();

                 res.then()
                .spec(commonResponse("application/json; charset=utf-8",200));
                 System.out.println(res.getBody().asString());

        RegresGetResponse     regRes=res.getBody().as(RegresGetResponse.class);

        List<Datum> data = regRes.getData();
        System.out.println(data.get(0).getEmail());

        String st = res.getBody().asString();
        JsonNode bd = mapper.readTree(st);
        System.out.println(bd.get("data").get(1).get("id"));


    }


    public RequestSpecification reqCommonPost(){

        builder.setBaseUri("http://thetestingworldapi.com");
        builder.setBasePath("api/studentsDetails");
        builder.setContentType("application/json");
        RequestSpecification reqCommon = builder.build();
        return reqCommon;

    }

    @Test
    public void postTest() throws JsonProcessingException {
        Util ut=new Util();

        Response response = RestAssured.given()
                .spec(reqCommonPost())
                .body(ut.stDetailsData("test","middle","last name","DOB"))
                .post();

                response.then()
                        .spec(commonResponse("application/json; charset=utf-8",201));


        StudentResponseData stResponse=response.getBody().as(StudentResponseData.class);

        System.out.println(stResponse.getFirstName());

        //System.out.println(response.getBody().asString());
        String st = response.getBody().asString();
        JsonNode bd = mapper.readTree(st);
        //System.out.println(bd.get("last_name"));
       System.out.println( bd.path("last_name"));

    }

}
