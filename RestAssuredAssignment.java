package recap;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class RestAssuredAssignment {
	@Test (priority = 1)
	public void getEmployees() {
		System.out.println("getting employees...");
		RestAssured.baseURI = "https://reqres.in/api/users?page=2";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("");
		ResponseBody body = response.getBody();
		
		String name = body.jsonPath().getString("data.first_name");
		name = name.substring(1, name.length() - 1);
		System.out.println("employeeName: " + name);
		
		System.out.println(body.asString());
		
		if(response.getStatusCode() == 200) {
			System.out.println();
			System.out.println("okay: " + response.getStatusCode());
		}else {
			System.out.println("error" + response.getStatusCode());
		}
		
	}

	@Test(priority = 2)
	public void addEmployees() {
		System.out.println("Adding employee...");
        
        RestAssured.baseURI = "https://reqres.in/api/users";
        RequestSpecification request = RestAssured.given();
        
        JSONObject requestParams = new JSONObject(); 
        requestParams.put("name", "Adrian"); 
        requestParams.put("job", "tester"); 
        
        request.header("Content-Type", "application/json");
        request.body(requestParams.toJSONString());
        
        Response response = request.post("/employees");
        System.out.println("The status received: " + response.statusLine());
        
        System.out.println(response.getBody().asString());
        
        if (response.getStatusCode() == 201) {
            System.out.println("employee added successfully");
            System.out.println("OK: " + response.getStatusCode());
        } else {
            System.out.println("An error occurred");
            System.out.println("Error: " + response.getStatusCode());
        }
    }
	
	@Test(priority = 3)
	public void delEmployees() {
		System.out.println("deleting employee...");
		RestAssured.baseURI = "https://reqres.in/api/users/2";
		RequestSpecification request = RestAssured.given();
		
		Response response = request.delete("");
        ResponseBody body = response.getBody();
        
        System.out.println(body.asString());
        if(response.getStatusCode() == 204) {
			System.out.println("employee has been deleted successfully");
			System.out.println("okay: " + response.getStatusCode());
		}else {
			System.out.println("an error occurs");
			System.out.println("error: " + response.getStatusCode());
		}
		
	}
	@Test(priority = 4)
	public void updateBook() {
		System.out.println("updating employee...");
		RestAssured.baseURI = "https://reqres.in/api/users/2";
		RequestSpecification request = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("first_name", "adrian");
        requestParams.put("last_name", "pascual");
        requestParams.put("email", "drianpascual@sample.com");
        
        request.body(requestParams.toJSONString());
        Response response = request.put("");
        ResponseBody body = response.getBody();
        
        System.out.println(body.asString());
        
        int status = response.getStatusCode();
		
        
        if(status == 200) {     	
			System.out.println("employee has been updated successfully");
			System.out.println("okay: " + response.getStatusCode());
		}else {
			System.out.println("an error occurs");
			System.out.println("error: " + response.getStatusCode());
		}
		
	}
}
