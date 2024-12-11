package Resources;

import io.qameta.allure.Allure;

public class AllureReport {
    public static void logInfo(String info) {
        Allure.addAttachment("Info", info);
    }

    public static void logEnvironmentDetails() {
        Allure.addAttachment("Operating System", System.getProperty("os.name"));
        Allure.addAttachment("Java Version", System.getProperty("java.version"));
    }
}
