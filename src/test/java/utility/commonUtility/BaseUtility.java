package utility.commonUtility;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import utility.signUpData.Dob;
import utility.signUpData.SignUpRequestData;
import utility.signUpData.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.specification.RequestSpecification;

public class BaseUtility {

    static ObjectMapper mapper = new ObjectMapper();
    static RequestSpecBuilder builder = new RequestSpecBuilder();
    static ResponseSpecBuilder response = new ResponseSpecBuilder();
    static RequestSpecification spec;



    public static void encodeConfig(){

        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);
    }


    public static RequestSpecification postCommonSpec(String baseurl,String endpoint){
        builder.setBaseUri(baseurl);
        builder.setBasePath(endpoint);
        builder.setContentType("application/json");
         RequestSpecification postCommon = builder.build();
        return postCommon;
    }

    public static RequestSpecification postCommonSpecTTech(String baseurl,String endpoint){

        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(baseurl);
        builder.setBasePath(endpoint);
        builder.addHeader("Content-Type","application/json");
        RequestSpecification postCommon = builder.build();
        return postCommon;
    }


    public static RequestSpecification getCommonSpecTTech(String baseurl,String endpoint) {

        builder.setBaseUri(baseurl);
        builder.setBasePath(endpoint);
        builder.addHeader("Content_Type", "application/json");
        builder.setAccept("ContentType.JSON");
        RequestSpecification requestSpec = builder.build();
        return requestSpec;
    }



    public static SignUpRequestData signupReData(UserInfo user, String email, String password ){

        SignUpRequestData sData=new SignUpRequestData();
        Dob dob=new Dob();
        sData.setDob(dob);
        dob.setYear(user.getYear());
        dob.setMonth(user.getMonth());
        dob.setDay(user.getDay());

        sData.setFirstName(user.getFirstName());
        sData.setLastName(user.getLastName());
        sData.setEmail(email);
        sData.setPassword(password);
        sData.setConfirmPassword(password);
        sData.setGender(user.getGender());
        sData.setAgree(user.isAgree());
        return sData;

    }

    public static Response callPostEndpoint(Method httpMethod, Object body, String baseurl, String endpoint) {

        if (httpMethod != Method.POST){
            throw new IllegalArgumentException("Wrong http method requested ");
        }

        try {
            spec = RestAssured.given().spec(BaseTest.getRequestSpecification());
            Response post = spec.header("Content-Type", "application/json").accept(ContentType.JSON)
                    .baseUri(baseurl).body(body).post(endpoint);
            return post;
        }catch(Exception exception){
            throw new RuntimeException("Could not call endpoint successfully");
        }
    }


    public static Response callGetEndPoint(Method httpMethod,String baseurl,String id, String endpoint) {

        if (httpMethod!=Method.GET) {
            throw new IllegalArgumentException("Wrong http method requested ");
        }
        try {
            spec = RestAssured.given().spec(BaseTest.getRequestSpecification());
            Response get = spec.header("Content-Type", "application/json").accept(ContentType.JSON)
                    .baseUri(baseurl).formParam("id", id).get(endpoint);
            return get;
        } catch (Exception exception) {
            throw new RuntimeException("could not call endpoint successfully");
        }
    }


    public static Response callGetEndPoint1(Method httpMethod,String baseurl, String endpoint) {

        if (httpMethod!=Method.GET) {
            throw new IllegalArgumentException("Wrong http method requested ");
        }
        try {
            spec = RestAssured.given().spec(BaseTest.getRequestSpecification());
            Response get = spec.header("Content-Type", "application/json").accept(ContentType.JSON)
                    .baseUri(baseurl).get(endpoint);
            return get;
        } catch (Exception exception) {
            throw new RuntimeException("could not call endpoint successfully");
        }
    }
}
