package test;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import common.StaticConstants;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import utils.ProductPOJO;
import utils.ReadPropertiesFile;
import utils.Reporting;
import utils.ResponseUtil;

import org.testng.annotations.BeforeTest;

import java.io.PrintStream;
import java.io.StringWriter;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class WebServiceAutomation {

	Properties prop;
	Reporting extentReport;
	public ExtentTest logger;
	ResponseUtil responseUtil;
	Response response = null;
	String resBody;
	String reqBody; 
	public static StringWriter requestWriter;
	public static PrintStream requestCapture;

	@BeforeTest
	public void setUpExtentReport() {
		extentReport = new Reporting();
		extentReport.setExtentReport();
	}

	@Test(groups = { "regression" }, priority = 1, description = "Verify POST method Using POJO Class")
	public void verifyPostMethodUsingPOJO() {
		logger = Reporting.extent.createTest("RestAssured.TC001.Verify POST method Using POJO Class");
		prop = ReadPropertiesFile.readPropertiesFromConfigFile();
		String uri = prop.getProperty("postUri");
		responseUtil = new utils.ResponseUtil();

		ProductPOJO product = new ProductPOJO(true, 2, "Blue", "2020-02-02", "Chair to sit", 10, "Steel", "Chair", 5999,
				"2020-02-02", 1);
		
		reqBody = RestAssured.given()
				.body(product)
				.log()
				.body()
				.toString();
		System.out.println("POST - Request Body : " + reqBody);
		logger.log(Status.INFO, MarkupHelper.createLabel("Request Body : "+reqBody, ExtentColor.WHITE));

		response = responseUtil.getResponse(product, uri);
		resBody = response.getBody().asString();
		System.out.println("POJO --> POST Method - Response Body : " + resBody);
		logger.log(Status.INFO, MarkupHelper.createLabel("Response Body : "+resBody, ExtentColor.WHITE));

		Assert.assertEquals("Created", response.jsonPath().get("message"));
	}

	@Test(groups = { "regression" },priority = 2, description = "Verify POST method")
	public void verifyPostMethod() {
		logger = Reporting.extent.createTest("RestAssured.TC002.Verify POST method");
		prop = ReadPropertiesFile.readPropertiesFromConfigFile();
		RestAssured.baseURI = prop.getProperty("postUri");
		RequestSpecification httpRequest = RestAssured.given();

		// Request payload sending with post request
		JSONObject requestParam = new JSONObject();
		requestParam.put("availability", prop.getProperty("availability"));
		requestParam.put("categoryId", prop.getProperty("categoryId"));
		requestParam.put("color", prop.getProperty("color"));
		requestParam.put("createdOn", prop.getProperty("createdOn"));
		requestParam.put("description", prop.getProperty("description"));
		requestParam.put("discount", prop.getProperty("discount"));
		requestParam.put("materialDescription", prop.getProperty("materialDescription"));
		requestParam.put("name", prop.getProperty("name"));
		requestParam.put("price", prop.getProperty("price"));
		requestParam.put("updateOn", prop.getProperty("updateOn"));
		requestParam.put("warranty", prop.getProperty("warranty"));

		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParam.toJSONString());

		response = httpRequest.request(Method.POST);

		reqBody = httpRequest
				.log()
				.body().toString();
		System.out.println("POST - Request Body : " + reqBody);
		logger.log(Status.INFO, MarkupHelper.createLabel("Request Body : "+reqBody, ExtentColor.WHITE));

		resBody = response.getBody().asString();
		System.out.println("POST - Response Body : " + resBody);
		logger.log(Status.INFO, MarkupHelper.createLabel("Response Body : "+resBody, ExtentColor.WHITE));

		// Status code verification
		int statusCode = response.getStatusCode();
		Assert.assertEquals(201, statusCode);

		// Store Product Id for GET method
		StaticConstants.productId = response.jsonPath().get("body.productId");

		// Success message validation
		String message = response.jsonPath().get("message");
		Assert.assertEquals("Created", message);
	}

	@Test(groups = { "regression" }, priority = 3, description = "Verify GET method")
	public void verifyGetMethod() {
		logger = Reporting.extent.createTest("RestAssured.TC003.Verify GET method");
		prop = ReadPropertiesFile.readPropertiesFromConfigFile();
		RestAssured.baseURI = prop.getProperty("getUri");
		RequestSpecification httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, String.valueOf(StaticConstants.productId));

		resBody = response.getBody().asString();
		System.out.println("GET - Response Body : " + resBody);
		logger.log(Status.INFO, MarkupHelper.createLabel("Response Body : "+resBody, ExtentColor.WHITE));

		// Status code verification
		int statusCode = response.getStatusCode();
		Assert.assertEquals(200, statusCode);

		// Success message validation
		String message = response.jsonPath().get("message");
		Assert.assertEquals("Product found", message);
	}

	@AfterMethod
	public void logTestStatus(ITestResult result) {
		if (ITestResult.SUCCESS == result.getStatus()) {
			logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " ------ PASSED ", ExtentColor.GREEN));
		} else if (ITestResult.FAILURE == result.getStatus()) {
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " ------ FAILED ", ExtentColor.RED));
			logger.fail(result.getThrowable());
		} else if (ITestResult.SKIP == result.getStatus()) {
			logger.log(Status.SKIP,	MarkupHelper.createLabel(result.getName() + " ------ SKIPPED ", ExtentColor.ORANGE));
			logger.skip(result.getThrowable());
		}
	}

	@AfterTest
	public void endReport() {
		extentReport = new Reporting();
		extentReport.endExtentReport();
	}
}
