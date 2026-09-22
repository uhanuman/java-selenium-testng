package com.demo.prep.retry;

import com.demo.prep.singleton.ConfigManager;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount;

    @Override
    public boolean retry(ITestResult result) {
        int maxRetries = ConfigManager.getInstance().getRetryCount();
        if (!result.isSuccess() && retryCount < maxRetries) {
            retryCount++;
            System.out.printf("[RETRY] Retrying %s.%s attempt %d of %d%n",
                    result.getTestClass().getName(),
                    result.getMethod().getMethodName(),
                    retryCount,
                    maxRetries);
            return true;
        }
        return false;
    }
}