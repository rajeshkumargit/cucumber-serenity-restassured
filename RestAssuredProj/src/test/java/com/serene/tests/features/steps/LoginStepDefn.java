package com.serene.tests.features.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.runner.RunWith;



@RunWith(SerenityRunner.class)


public class LoginStepDefn {
	

	@Before
	public void setup()
	{
    	RestAssured.baseURI = "https://r8alxaspi1.execute-api.ap-southeast-2.amazonaws.com";

	}
	
	@After
	public void tearDown()
	{
        RestAssured.reset();
	}
	@Steps
	LoginAPISteps loginAPI;

	@Given("^I provide login credentials \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_provide_username_with_password(String username,String password) {
		Serenity.setSessionVariable("username").to(username);
		Serenity.setSessionVariable("password").to(password);
		loginAPI.givenUserDetails(username,password);
	}

	@When("^I send request to login$")
	public void i_send_request_to_login() throws Throwable {
		loginAPI.postLoginRequest();
	}

	@Then("^login is successful$")
	public void login_is_successful() {
		String username = Serenity.sessionVariableCalled("username").toString();
		String password = Serenity.sessionVariableCalled("password").toString();
		loginAPI.verifyLoginSuccess(username,password);
		}
	
	@Then("^login failed$")
	public void login_failed() {
		String username = Serenity.sessionVariableCalled("username").toString();
		String password = Serenity.sessionVariableCalled("password").toString();
		loginAPI.verifyLoginFailure(username,password);
		}
}
