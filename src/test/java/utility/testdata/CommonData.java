package utility.testdata;

import com.github.javafaker.Faker;
import utility.signUpData.UserInfo;

import java.time.LocalDateTime;

public class CommonData {

    static Faker faker = new Faker();

    public static String baseURLTTech="http://qa.taltektc.com/";
    public static String endPointSignUp="api/signup";
    public static String endPointGetStudent="api/student/";


    public int rand=faker.number().numberBetween(100,999);
    public String email="Test"+rand+"@gmail.com";
    public String password="Test99"+rand;

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
