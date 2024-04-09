package api.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.Payload.User;
import api.endPoints.UserEndPoints;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User userPayLoad;
	
	@BeforeClass
	public void setup() {
		faker =new Faker();
		
		//creating the user payload
		userPayLoad=new User();
		userPayLoad.setId(faker.idNumber().hashCode());
		userPayLoad.setUsername(faker.name().username());
		userPayLoad.setFirstName(faker.name().firstName());
		userPayLoad.setLastName(faker.name().lastName());
		userPayLoad.setEmail(faker.internet().safeEmailAddress());
		userPayLoad.setPassword(faker.internet().password());
		userPayLoad.setPhone(faker.phoneNumber().cellPhone());
	}
	
	
	@Test(priority=1)
	public void postUsers() {
		Response response = UserEndPoints.createUser(userPayLoad);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("########################Post");
	}
	
	
	

	@Test(priority=2)
	public void getUsers() {
		Response response = UserEndPoints.getUser(this.userPayLoad.getUsername());
		response.then().log().all();
		//Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("########################Get");
	}
	
	
	@Test(priority=3)
	public void updateUsers() {
		
		userPayLoad.setFirstName(faker.name().firstName());
		userPayLoad.setLastName(faker.name().lastName());
		userPayLoad.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndPoints.updateUser(userPayLoad, this.userPayLoad.getUsername());
		response.then().log().body();
		//Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("########################Put");
	}
	
	@Test(priority=4)
	public void deleteUsers() {
		Response response = UserEndPoints.deleteUser(this.userPayLoad.getUsername());
		response.then().log().all();
	}
}
