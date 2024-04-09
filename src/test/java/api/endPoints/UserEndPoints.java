package api.endPoints;

import api.Payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserEndPoints {

    static String token = "Bearer 9f378887517c46948b9c0e768423abb96c0dcf03b61c7b3bd40af81f74d8ff6d";

    // CRUD Create, Read(Get), Update, Delete methods
    public static Response createUser(User payload) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_URL);
        return response;
    }

    
    public static Response getUser(String userName) {
        Response response = given()
        		.header("Authorization", token)
                .pathParam("userName", userName)
                .get(Routes.get_URL);
        return response;
    }
    

    public static Response updateUser(User payload, String userName) {
        Response response = given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .pathParam("userName", userName)
                .body(payload)
                .when()
                .put(Routes.update_URL);
        return response;
    }

    public static Response deleteUser(String userName) {
        Response response = given()
                .header("Authorization", token)
                .pathParam("userName", userName)
                .delete(Routes.delete_URL);
        return response;
    }
}
