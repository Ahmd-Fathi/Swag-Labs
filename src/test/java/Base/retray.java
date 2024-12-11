package Base;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class retray  implements IRetryAnalyzer {

int count=0;
int maxTray=1;

    @Override
    public boolean retry(ITestResult iTestResult) {

        if (count<maxTray){
            count++;
            return true;

        }

        return false;
    }
}
