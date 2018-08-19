package com.serene.tests.features.steps;

import org.junit.Assert;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import net.thucydides.core.annotations.Step;


public class LoginAPISteps {
	
    private Response res = null; //Response
    private JsonPath jp = null; //JsonPath
    private RequestSpecification requestSpec;
	
        @Step
        public void givenUserDetails (String username,String password){
        	RequestSpecBuilder builder = new RequestSpecBuilder();
        	builder.setBasePath("api/ezypay/login");
        	builder.setContentType("application/json");
        	System.out.println("{\"username\":\""+username+"\",\"password\": \""+password+"\"}");
        	builder.setBody("{\"username\":\""+username+"\",\"password\": \""+password+"\"}");
        	requestSpec = builder.build();
        	requestSpec = RestAssured.given().spec(requestSpec);
        	requestSpec.log().all();

        }
        
        @Step     
        public void postLoginRequest()  {
        	res = requestSpec.when().post();
          }

        @Step     
        public void verifyLoginSuccess(String username,String password) {
        	jp = res.jsonPath();
            Assert.assertEquals("Status Check Failed!", 200, res.getStatusCode());
            Assert.assertEquals("Username is wrong in response", username, jp.get("username"));
            Assert.assertEquals("Password is wrong in response", password, jp.get("password"));
        }
        
        @Step     
        public void verifyLoginFailure(String username,String password) {
        	jp = res.jsonPath();
            Assert.assertEquals("Invalid user credentials allowed", 401, res.getStatusCode());
            Assert.assertEquals("Message Code incorrect", "UnauthorizedError", jp.get("Code"));
            Assert.assertEquals("Message Incorrect", "UnauthorizedError: invalid username or password", jp.get("Message"));

        }

    	
   }


