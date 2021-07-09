package utils;

import org.apache.http.HttpStatus;

import com.typesafe.config.Config;

import config.ConfigProvider;
import constants.Index;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class commomUtils {
    static RequestSpecification requestSpecification;
    static ResponseSpecification responseSpecification;
    static Response response;
    
    public static JsonPath retrieveStationDetails(String id){

        Config conf  = ConfigProvider.config().getConfig(Index.REQURIES_DATA);
        RestAssured.baseURI = conf.getString(Index.BASE_URI);
        requestSpecification = RestAssured.given();
        requestSpecification.headers(ConfigProvider.config().getObject(Index.HEADERS).unwrapped());
        requestSpecification.pathParam(Index.ID,id);
        requestSpecification.log().all();
        responseSpecification = requestSpecification.expect();
        responseSpecification.log().all();
        responseSpecification.then().statusCode(HttpStatus.SC_OK);
        response =  requestSpecification.get("/stations");
        return response.jsonPath();
    }
}
