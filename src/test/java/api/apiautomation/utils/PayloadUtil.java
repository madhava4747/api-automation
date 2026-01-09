package api.apiautomation.utils;

public class PayloadUtil {

    public static String createUserPayload(String name, String job) {
        return "{ \"name\":\"" + name + "\", \"job\":\"" + job + "\" }";
    }
}
