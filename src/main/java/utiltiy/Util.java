package utiltiy;

public class Util {

    public StudentRequestData stDetailsData(String firstName, String middleName, String lastName, String dateOfBirth){

        StudentRequestData stData=new StudentRequestData();
        stData.setFirstName(firstName);
        stData.setMiddleName(middleName);
        stData.setLastName(lastName);
        stData.setDateOfBirth(dateOfBirth);
        return stData;
    }

}
