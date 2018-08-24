# cucumber-serenity-restassured
This is a Rest Assured BDD framework for API test automation. Took the approach of BDD along with Rest Assured in order to ensure that the specifications are testable. When the features/scenarios are written in Gherkin and glued with step defnitions, it provideds an executable one source of truth.

Chosen Cucumber for its rich implementation of the Behavior Driven Development model. Integration with Serenity provides both out of the box Rest-Assured methods and an Aggregate report to make sure that the framework is scalable.
Rest-Assured as an API testing library provides wide array of choices for test developers with both groovy and java libraries. Choesn Java to build the framework.

Currently framework lacks reusable custom base library and helper methods for better reusability. A couple utility/helper classes will serve the purpose by moving all the RestAssured setbaseURI,basePath,Headers, etc and the Response/JSON parsing by encapsulating in a helper class. Besides logger needs to be added. Currently only the request is logged in Console.
Also the Serentiy report doesn't print the 'Rest Query' which needs to be investigated.

# Run Instructions:

Run it as 'mvn clean verify' from the project dir

# Report Dir:
/target/site/reports

# Automation Strategy 
This framework needs to be scaled to cater to the entire API testing of your application
# Approach
Currently the framework adheres to a BDD model:
------------------------------------------------
Features->Scennarios->Step Defnitions->RestTestLib
This approach brings a collaborative model to the API testing where the Business Analysts, Developers and testers could work in tandem in Agile to conceive the acceptance criteria leveraging Gherkin. Test automation engineer could build step defintions and rest assured methods and assertions by consulting the developers. Testsuite could be executed as Maven Verify (mvn verify).

However at present, framework needs to add the following classes/components in order to be more scalable:
1. An utility class with a few rest assured methods such as request spec builder, response spec builder, common assertions like validating status code, parsing the response for headers, response objects, etc
2. Helper class (Support Code) specific to a project which returns parsed response. For example JSON path of a particular object or construcing an arraylist object for validation, etc.
3. Environment variables need to be moved to properties (serenity.properties)
4. Report doesn't print Rest Query which needs to be investigated
5. Logging needs to be expanded further

Future model:
------------
Features->Scennarios->Step Defnitions->RestTestLib->Custom Helper Classes


#Test Coverage
--------------
Test scenarios
Though every microservice is an independent entity, still it needs to be tested for integration. I'll identify the tests with the following approach:
1. End-to-End test testing of the data flow between various services and its different request/responses. This could achieved by creating the Features/Scenarios from Cucumber and writing step defnitions and further steps to call the API layer.
2. Services that impact funds/monetary values (For ex., transfer between accounts, updating bank balance)
3. Services that are called frequently
4. APIs that could impact the business profusely if failed or unavailable

Also I'll segregate the tests based on 'pre-integration' and 'integration-tests' which would give us the ability to run the new featuers during the 'pre-integration' but the 'integration-tests' for regression, etc.

Continuous Testing with the aid of integration to CI/CD will provide the team a visibility. So it's mandatory to integrate the tests in pipeline.

#Factors to consider
----------------------
1. Backgrounds and Sceanrio Outlines - In the feature files in Cucumber Gherkin, this brings the reusability to the fore
2. Step Defintions Reusability- Reusing the step defintions this trickles down from the Backgrounds and Sceanrio Outlines in Feature files
3. Building reusable utility/helper classes which could be API Specific, Project related and common methods for entire REST architecture
4. Providing examples (Dataset) in Features for multiple test iterations
5. Mocking responses where third-party APIs are required
6. Meeting the Agile sprint testing goals through Automation
7. Working in tandem with GUI automation where required (For example., Creating Synthetic data and Deleting it as part of the Tear down exercises using API tests)
8. Providing feedback by leverging email plugins in CI/CD platform to keep the stakeholders posted on the test results
