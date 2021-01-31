package TestPackage;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.poi.util.SystemOutLogger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetTestClass {

    @Test
    public void get_test() throws AssertionError {

        SoftAssert soft=new SoftAssert();

        RestAssured.baseURI="https://reqres.in/";

       Response res=RestAssured.given()
                .header("Content_Type","application/json")
                .accept("ContentType.JSON")
                .get("/api/users?page=2");

//        System.out.println(res.statusCode());
//        System.out.println(res.statusLine());
//        System.out.println(res.time());
       System.out.println(res.getBody().asString());
        //System.out.println(res.getHeaders());
//        System.out.println(res.getHeader("Date"));
//        System.out.println(res.getHeader("Content-Type"));
//        System.out.println(res.getHeader("Connection"));
//
//        Assert.assertEquals(res.statusCode(),200);
//        Assert.assertEquals(res.header("Content-Type"),"application/json; charset=utf-8");
//
//        try{
//            Assert.assertEquals(res.statusCode(),201);
//
//            }catch(AssertionError error) {
//            System.err.println(error);
//        } catch (Exception e){
//            System.out.println(e);
//        }
//
//        try {
//            Assert.assertEquals(res.header("Content-Type"),"application/json1; charset=utf-8");
//        }catch(AssertionError er){
//            System.err.println(er);
//        }

//        soft.assertEquals(res.statusCode(),201);
//        soft.assertEquals(res.header("Content-Type"),"application/json1");
//        soft.assertAll();

        System.out.println("Test Continue after failing asserts ");


        JsonPath jpath = res.getBody().jsonPath();

        System.out.println(jpath.get("data.id[0]"));
        System.out.println(jpath.get("page"));

        int aa=jpath.getList("data").size();

        for(int i=0;i<aa;i++){

            System.out.println(jpath.get("data.email["+i+"]"));
        }




    }

    @Test
    public void test1(){


        Response res=RestAssured.given()
                .header("Content_Type","application/json")
                .accept("ContentType.JSON")
                .get("/api/users/45");

        System.out.println(res.getBody().asString());
    }


}
