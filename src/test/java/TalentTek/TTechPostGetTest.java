package TalentTek;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Method;
import org.testng.Assert;
import resources.commonUtility.BaseTest;
import resources.commonUtility.BaseUtility;
import resources.testdata.CommonData;
import io.restassured.response.Response;
import org.testng.annotations.Test;
public class TTechPostGetTest extends BaseTest {

    ObjectMapper mapper = new ObjectMapper();


    @Test
    public void signUpTTech() throws JsonProcessingException {

        CommonData cdata=new CommonData();
        BaseUtility.encodeConfig();
        Response response = BaseUtility.callPostEndpoint(Method.POST,BaseUtility.signupReData(cdata.getUserInfo(),cdata.email,cdata.password),cdata.baseURLTTech,cdata.endPointSignUp);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(),201);
        JsonNode obj = mapper.readTree(response.getBody().asString());
        System.out.println(obj.get("id"));
        BaseTest.getTestCache().put("studentId",obj.get("id").toString().replaceAll("\"",""));
        BaseTest.getTestCache().put("email",cdata.email);
        BaseTest.getTestCache().put("password",cdata.password);
    }


    @Test(dependsOnMethods = "signUpTTech")
    public void getStudentInfo() throws JsonProcessingException {

        CommonData cdata=new CommonData();
        BaseUtility.encodeConfig();
        Response response = BaseUtility.callGetEndPointTTech(Method.GET,cdata.baseURLTTech,cdata.endPointGetStudent+BaseTest.getTestCache().get("studentId").toString());

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(),200);
        JsonNode obj = mapper.readTree(response.getBody().asString());
        System.out.println(obj.get("data").get("studentId"));
        Assert.assertEquals(obj.get("data").get("studentId").toString().replaceAll("\"",""),BaseTest.getTestCache().get("studentId").toString());
        Assert.assertEquals(obj.get("data").get("email").toString().replaceAll("\"",""),BaseTest.getTestCache().get("email"));

    }






}
