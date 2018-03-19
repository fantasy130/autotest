import net.winfield.demo.util.PageAction;
import net.winfield.demo.util.Parameter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.internal.PropertiesFile;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class TestBankIndex {

    private WebDriver driver;

    @Test
    public void testEachCase() throws Exception {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
        //System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");

        PropertiesFile propertiesFile = new PropertiesFile("src/test/resources/testCase.properties");
        Properties properties = propertiesFile.getProperties();
        String[] testCaseNameList = properties.getProperty("testCaseNameList").split("\\|");
        for (String testCase : testCaseNameList) {
            driver = new FirefoxDriver();
            List keyList = properties.keySet().stream().filter(k -> k.toString().startsWith(testCase)).sorted().collect(Collectors.toList());
            Class<?> pageActionClass = Class.forName("net.winfield.demo.util.PageAction");
            PageAction pageAction = (PageAction) pageActionClass.newInstance();
            for (Object action : keyList) {
                String value = properties.getProperty((String) action);
                String[] actionNameAndParam = value.split("\\|");
                List<String> paramList = Arrays.asList(actionNameAndParam).subList(1, actionNameAndParam.length);

                Class<?> paramterClass = Class.forName("net.winfield.demo.util.Parameter");
                Parameter parameter = (Parameter) paramterClass.newInstance();
                parameter.setDriver(driver);
                for (String param : paramList) {
                    String[] keyValue = param.split(":");
                    if (keyValue.length > 0) {
                        Method setter = paramterClass.getMethod("set" + keyValue[0], String.class);
                        setter.invoke(parameter, keyValue[1]);
                    }
                }
                System.out.println("Start********************" + actionNameAndParam[0] + "************************");
                Method ob = pageActionClass.getMethod(actionNameAndParam[0], Parameter.class);
                ob.invoke(pageAction, parameter);
                System.out.println("End********************" + actionNameAndParam[0] + "************************");
            }
        }

    }

    @AfterMethod
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }
}
