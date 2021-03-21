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

public class TTechDeleteGetTest extends BaseTest {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void studentDeleteTest() throws JsonProcessingException {

        CommonData cdata=new CommonData();
        BaseUtility.encodeConfig();
        Response response = BaseUtility.callDeleteEndPointTTech(Method.DELETE,BaseTest.getTestCache().get("accessToken").toString(),cdata.baseURLTTech,cdata.endPointGetStudent+BaseTest.getTestCache().get("studentId").toString());
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        JsonNode obj = mapper.readTree(response.getBody().asString());
        Assert.assertEquals(obj.get("messages").toString().replaceAll("\"",""),"Data Deleted");
    }

    @Test(dependsOnMethods = "studentDeleteTest")
    public void studentDeleteVerify() throws JsonProcessingException {

        CommonData cdata=new CommonData();
        BaseUtility.encodeConfig();
        Response response = BaseUtility.callGetEndPointTTech(Method.GET,cdata.baseURLTTech,cdata.endPointGetStudent+BaseTest.getTestCache().get("studentId").toString());

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(),404);

    }
}
