package utiltiy;



import com.fasterxml.jackson.databind.ObjectMapper;
import utiltiy.validateData.StudentPostRequestData;

public class Util {

    ObjectMapper mapper = new ObjectMapper();

    public StudentRequestData stDetailsData(String firstName, String middleName, String lastName, String dateOfBirth){

        StudentRequestData stData=new StudentRequestData();
        stData.setFirstName(firstName);
        stData.setMiddleName(middleName);
        stData.setLastName(lastName);
        stData.setDateOfBirth(dateOfBirth);
        return stData;
    }


    public StudentPostRequestData studentPostData(String firstName, String middleName, String lastName, String dateOfBirth){

        StudentPostRequestData postData=new StudentPostRequestData();
        postData.setFirstName(firstName);
        postData.setMiddleName(middleName);
        postData.setLastName(lastName);
        postData.setDateOfBirth(dateOfBirth);
        return postData;
    }


}
