package steps;

import org.apache.http.HttpStatus;
import org.testng.Assert;

import com.typesafe.config.Config;

import config.ConfigProvider;
import constants.EndPoint;
import constants.Index;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class deldata {
	RequestSpecification requestSpecification;
	ResponseSpecification responseSpecification;
	Response response;
	
	@Given("^User is with endpoint and id$")
	public void user_is_with_endpoint_and_id() throws Throwable {
		Config conf = ConfigProvider.config().getConfig(Index.REQURIES_DATA);
		RestAssured.baseURI = conf.getString(Index.BASE_URI);
		this.requestSpecification = RestAssured.given();
		this.requestSpecification.headers(ConfigProvider.config().getObject(Index.HEADERS).unwrapped());
		this.requestSpecification.log().all();
		responseSpecification = requestSpecification.expect();
		responseSpecification.log().all();
	}

	@When("^User sends the request$")
	public void user_sends_the_request() throws Throwable {
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NO_CONTENT);
	}

	@Then("^Delete that data$")
	public void delete_that_data() throws Throwable {
		Config conf = ConfigProvider.config().getConfig(Index.REQURIES_DATA);
		requestSpecification.pathParam("userId",conf.getString(Index.ID) );
		response = requestSpecification.delete(EndPoint.RETRIVE_SINGLE_DATA);
	}

}
