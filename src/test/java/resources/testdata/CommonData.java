package resources.testdata;

import com.github.javafaker.Faker;
import resources.testdata.signUpData.UserInfo;

import java.time.LocalDateTime;

public class CommonData {

    static Faker faker = new Faker();

    public static String baseURLTTech="http://qa.taltektc.com/";
    public static String endPointSignUp="api/signup";
    public static String endPointGetStudent="api/student/";
    public static String endPointStudentLogin="api/login";
    public static String endPointStudentUpdatePassword="api/updatePassword/";
    public static String endPointAllStudents="api/students";


    public int rand=faker.number().numberBetween(100,999);
    public String email="Test1"+rand+"@gmail.com";
    public String password="Test99"+rand;
    public String updatePassword="Update11"+rand;

    public static UserInfo getUserInfo(){
        UserInfo info=new UserInfo();
        info.setFirstName(faker.name().firstName());
        info.setLastName(faker.name().lastName());
        info.setYear(LocalDateTime.now().minusMonths(120).getYear());
        info.setMonth(LocalDateTime.now().getMonthValue());
        info.setDay(LocalDateTime.now().getDayOfMonth());
        info.setGender("male");
        info.setAgree(true);
        return info;
    }

}
