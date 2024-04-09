package api.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.Payload.User;
import api.Utils.ExcelUtils;
import api.endPoints.UserEndPoints;
import io.restassured.response.Response;

public class DataDrivenTest {

    @Test(priority = 1, dataProvider = "readExcelData", dataProviderClass = ExcelUtils.class)
    public void createUsersPost(String UserID, String UserName, String FirstName, String LastName, String Email,
                                 String Password, String Phone) {

        User userPayload = new User();
        userPayload.setId(Integer.parseInt(UserID));
        userPayload.setUsername(UserName);
        userPayload.setFirstName(FirstName);
        userPayload.setLastName(LastName);
        userPayload.setEmail(Email);
        userPayload.setPassword(Password);
        userPayload.setPhone(Phone);

        Response response = UserEndPoints.createUser(userPayload);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @DataProvider(name = "readExcelData")
    public Object[][] dataFromExcel() {
        String filePath = System.getProperty("user.dir") + "\\TestData\\testData.xlsx";
        String sheetName = "TestData";
        Object[][] data = ExcelUtils.getTestDataFromExcel(sheetName, filePath);
        return data;
    }
}
