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
import utils.FileManager;

public class postdatas {
	
	RequestSpecification requestSpecification;
	ResponseSpecification responseSpecification;
	Response response;
	@Given("^Adding new user$")
	public void adding_new_user() throws Throwable {
		Config conf = ConfigProvider.config().getConfig(Index.REQURIES_DATA);
		RestAssured.baseURI = conf.getString(Index.BASE_URI);
		this.requestSpecification = RestAssured.given();
		this.requestSpecification.headers(ConfigProvider.config().getObject(Index.HEADERS).unwrapped());
		this.requestSpecification.body(FileManager.loadJsonFile(Index.ADD_REQURIES_REQUESTPAYLOAD));
		this.requestSpecification.log().all();
		responseSpecification = requestSpecification.expect();
		responseSpecification.log().all();
	}

	@When("^we give request  with invalid detials$")
	public void we_give_request_with_invalid_detials() throws Throwable {
		Config conf = ConfigProvider.config().getConfig(Index.REQURIES_DATA);
		
		response = requestSpecification.post(EndPoint.RETRIVE_ENTRIE_DATA);
		Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_CREATED);
	}

	@When("^we give request  with detials$")
	public void we_give_request_with_detials() throws Throwable {
	   
	}

	@Then("^the user shoukd be created$")
	public void the_user_shoukd_be_created() throws Throwable {
	   
	}

}
