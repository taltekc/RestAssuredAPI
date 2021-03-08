package TalentTek;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Method;
import utility.commonUtility.BaseTest;
import utility.commonUtility.BaseUtility;
import utility.testdata.CommonData;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
public class ApiTest extends BaseTest {

    ObjectMapper mapper = new ObjectMapper();


    @Test
    public void signUpTTech() throws JsonProcessingException {

        CommonData cdata=new CommonData();
        BaseUtility.encodeConfig();
        Response response = BaseUtility.callPostEndpoint(Method.POST,BaseUtility.signupReData(cdata.getUserInfo(),cdata.email,cdata.password),cdata.baseURLTTech,cdata.endPointSignUp);

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        JsonNode obj = mapper.readTree(response.getBody().asString());
        System.out.println(obj.get("id"));
        BaseTest.getTestCache().put("studentId",obj.get("id").toString().replaceAll("\"",""));

    }


    @Test(dependsOnMethods = "signUpTTech")
    public void getStudentInfo(){

        CommonData cdata=new CommonData();
        BaseUtility.encodeConfig();
        Response response = BaseUtility.callGetEndPoint1(Method.GET,cdata.baseURLTTech,cdata.endPointGetStudent+BaseTest.getTestCache().get("studentId").toString());

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
    }





}
