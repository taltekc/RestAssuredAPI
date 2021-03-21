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

public class TTechPatchGetTest extends BaseTest {

    ObjectMapper mapper = new ObjectMapper();


    @Test
    public void patchTTechTest() throws JsonProcessingException {

        CommonData cdata=new CommonData();
        BaseUtility.encodeConfig();
        Response response = BaseUtility.callPatchEndpoint(Method.PATCH,BaseTest.getTestCache().get("accessToken").toString(),BaseUtility.studentPatchData(cdata.updatePassword),cdata.baseURLTTech,cdata.endPointStudentUpdatePassword+BaseTest.getTestCache().get("studentId").toString());

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        BaseTest.getTestCache().put("updatedPassword",cdata.updatePassword);
        JsonNode obj = mapper.readTree(response.getBody().asString());
        Assert.assertEquals(obj.get("message").toString().replaceAll("\"",""),"Password update success");


    }


    @Test(dependsOnMethods = "patchTTechTest")
    public void studentLoginWithUpdatedPassword() throws JsonProcessingException {

        CommonData cdata=new CommonData();
        BaseUtility.encodeConfig();
        Response response =BaseUtility.callPostEndpoint(Method.POST,BaseUtility.studentLogData(BaseTest.getTestCache().get("email").toString(),BaseTest.getTestCache().get("updatedPassword").toString()),cdata.baseURLTTech,cdata.endPointStudentLogin);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        JsonNode obj = mapper.readTree(response.getBody().asString());
        Assert.assertEquals(obj.get("message").toString().replaceAll("\"",""),"Login Success");
        BaseTest.getTestCache().put("accessToken",obj.get("token").toString().replaceAll("\"",""));

    }


}
