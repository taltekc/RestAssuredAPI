package TalentTek;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.commonUtility.BaseTest;
import resources.commonUtility.BaseUtility;
import resources.testdata.CommonData;

public class TTechPutPatchTest extends BaseTest{

    ObjectMapper mapper = new ObjectMapper();

    @Test(dependsOnMethods = "studentLogin")
    public void studentPutTTech() throws JsonProcessingException {

        CommonData cdata=new CommonData();
        BaseUtility.encodeConfig();
        Response response = BaseUtility.callPutEndpoint(Method.PUT,BaseTest.getTestCache().get("accessToken").toString(),BaseUtility.studentPutData(cdata.getUserInfo(),BaseTest.getTestCache().get("email").toString()),cdata.baseURLTTech,cdata.endPointGetStudent+BaseTest.getTestCache().get("studentId").toString());
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(),200);

    }


    @Test
    public void studentLogin() throws JsonProcessingException {

        CommonData cdata=new CommonData();
        BaseUtility.encodeConfig();
        Response response =BaseUtility.callPostEndpoint(Method.POST,BaseUtility.studentLogData(BaseTest.getTestCache().get("email").toString(),BaseTest.getTestCache().get("password").toString()),cdata.baseURLTTech,cdata.endPointStudentLogin);

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        JsonNode obj = mapper.readTree(response.getBody().asString());
        System.out.println(obj.get("token"));
        BaseTest.getTestCache().put("accessToken",obj.get("token").toString().replaceAll("\"",""));

    }



}
