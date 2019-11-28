package slack.tests;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends BaseTest implements ITestListener {

	@Override
	public void onFinish(ITestContext Result) {

	}
	@Override
	public void onStart(ITestContext Result) {
		Result.getName();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {

	}

	@Override
	public void onTestFailure(ITestResult Result) {
		System.out.println("Test Failed: " + Result.getName());
//		report.testFail(Result.getName());
	}

	@Override
	public void onTestSkipped(ITestResult Result) {
		System.out.println("The following test has been Skipped: " + Result.getName());
	}

	@Override
	public void onTestStart(ITestResult Result) {
		System.out.println("The following test is being executed: " + Result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult Result) {
		System.out.println("Test Passed: " + Result.getName());
	}

}
