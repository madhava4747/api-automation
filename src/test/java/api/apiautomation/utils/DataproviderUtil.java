package api.apiautomation.utils;

import org.testng.annotations.DataProvider;

public class DataproviderUtil {

    @DataProvider
    public Object[][] userData() {
        return new Object[][] {
            {"Maddy", "QA"},
            {"John", "Automation"}
        };
    }
}
