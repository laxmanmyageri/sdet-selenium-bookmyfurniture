package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ResponseUtil {

	Response response = null;

	public Response getResponse(ProductPOJO product, String uri) {
		response = RestAssured.given().contentType("application/json").body(product).when().log().all().post(uri);
		return response;
	}
}
