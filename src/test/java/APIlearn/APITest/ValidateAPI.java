package APIlearn.APITest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.*;


import static io.restassured.RestAssured.*;

public class ValidateAPI {

	@BeforeClass
	public void setBaseURI() 
	{
		RestAssured.baseURI="https://reqres.in/";
	}
	
	@Test
	public void getAPI() 
	{
	   Response res = given().log().all().queryParam("page", "2").contentType(ContentType.JSON).
			   		  when().get("/api/users").
			   		  then().log().all().statusCode(200).extract().response();
	   
	   String response=res.asString();
	   
	   JsonPath js = new JsonPath(response);
	   int pages=js.getInt("total_pages");
	   Assert.assertEquals(pages, 2);
	}
	
	@Test
	public void postAPI() throws InterruptedException 
	{
		Thread.sleep(5000);
		
	 ValidatableResponse res=given().log().all().contentType(ContentType.JSON).
			   		  body("{\"name\":\"hely\",\"job\":\"leader\"}").
			   		  when().post("/api/users").
			   		  then().log().all().statusCode(200).body("job",equalTo("leader")).time(lessThan(3000L));
	
			 
	   
	   //long restime=res.time();
	
//	   JsonPath js = new JsonPath(response);
//	   int pages=js.getInt("total_pages");
	   //Assert.assertEquals(pages, 2);
	}
	
	
}
