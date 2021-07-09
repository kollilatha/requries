package steps;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

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
import utils.FileManager;

public class getdatas {
	RequestSpecification requestSpecification;
	ResponseSpecification responseSpecification;
	Response response;
	@Given("^Getting single user$")
	public void getting_single_user() throws Throwable {
		Config conf = ConfigProvider.config().getConfig(Index.REQURIES_DATA);
		RestAssured.baseURI = conf.getString(Index.BASE_URI);
		this.requestSpecification = RestAssured.given();
		this.requestSpecification.headers(ConfigProvider.config().getObject(Index.HEADERS).unwrapped());
		this.requestSpecification.log().all();
		responseSpecification = requestSpecification.expect();
		responseSpecification.log().all();
	}

	@When("^Send a request with base parameter$")
	public void send_a_request_with_base_parameter() throws Throwable {
		Config conf = ConfigProvider.config().getConfig(Index.REQURIES_DATA);
	
		requestSpecification.pathParam("userId", conf.getString(Index.ID));
		response = requestSpecification.get(EndPoint.RETRIVE_SINGLE_DATA);
		
	}

	@Then("^we will be avaliable with user$")
	public void we_will_be_avaliable_with_user() throws Throwable {
		Config conf = ConfigProvider.config().getConfig(Index.REQURIES_DATA);
		Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
		response.then().assertThat().body(matchesJsonSchema(FileManager.loadJsonFile(Index.ADD_REQURIES_EXPECTED_SCHEMA)));
	}


}
