package TestPackage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import utiltiy.Util;

public class FinalWay {

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

    public RequestSpecification postCommonSpec(String endPoint){

        builder.setBaseUri("http://thetestingworldapi.com");
        builder.setBasePath(endPoint);
        builder.setContentType("application/json");
        RequestSpecification reqCommon = builder.build();
        return reqCommon;
    }

    public ResponseSpecification commonResponse(String contentType, int status){

        response.expectContentType(contentType);
        response.expectStatusCode(status);
        ResponseSpecification responseSpec = response.build();
        return responseSpec;
    }



    @Test
    public void postTest() throws JsonProcessingException {
        Util utl=new Util();

        Response response = RestAssured
                .given()
                .spec(postCommonSpec("api/studentsDetails"))
                .body(utl.studentPostData("hello","api","testing","5645654"))
                .post();

                response
                        .then()
                        .spec(commonResponse("application/json; charset=utf-8",201));

        JsonNode obj = mapper.readTree(response.getBody().asString());
        System.out.println(obj.get("last_name"));



    }


}
