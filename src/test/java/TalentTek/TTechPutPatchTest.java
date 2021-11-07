package TalentTek;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.commonUtility.BaseTest;
import resources.commonUtility.Utility;
import resources.testdata.CommonData;

public class TTechPutPatchTest extends BaseTest{

    ObjectMapper mapper = new ObjectMapper();

    @Test(dependsOnMethods = "studentLogin")
    public void studentPutTTech() throws JsonProcessingException {

        CommonData cdata=new CommonData();
        Utility.encodeConfig();
        Response response = Utility.callPutEndpoint(Method.PUT,BaseTest.getTestCache().get("accessToken").toString(),cdata.studentPutBody(BaseTest.getTestCache().get("email").toString()),cdata.baseURLTTech,cdata.endPointGetStudent+BaseTest.getTestCache().get("studentId").toString());
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(),200);

    }


    @Test
    public void studentLogin() throws JsonProcessingException {

        CommonData cdata=new CommonData();
        Utility.encodeConfig();
        Response response =Utility.callPostEndpoint(Method.POST,cdata.loginBody(BaseTest.getTestCache().get("email").toString(),BaseTest.getTestCache().get("password").toString()),cdata.baseURLTTech,cdata.endPointStudentLogin);

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        JsonNode obj = mapper.readTree(response.getBody().asString());
        System.out.println(obj.get("token"));
        BaseTest.getTestCache().put("accessToken",obj.get("token").toString().replaceAll("\"",""));

    }



}
