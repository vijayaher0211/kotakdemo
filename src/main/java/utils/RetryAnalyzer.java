package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


/**
 * RetryAnalyzer
 *
 * Implements TestNG's IRetryAnalyzer to automatically re-run failed tests.
 * - Reads the maximum retry count from the   file.
 * - Retries a failed test until the maximum retry count is reached.
 *
 * Useful for handling temporary test failures caused by network issues,
 * environment instability, or intermittent UI behavior.
 */


public class RetryAnalyzer implements IRetryAnalyzer {
	
    private int retryCount = 0;
    private static final int maxRetryCount =  ReadConfigFile.getRetryCount();

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;  // retry this test
        }
        return false;  // do not retry
    }
}
