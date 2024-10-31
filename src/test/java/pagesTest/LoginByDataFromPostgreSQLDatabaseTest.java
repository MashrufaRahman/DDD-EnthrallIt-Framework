package pagesTest;

import java.util.Iterator;
import java.util.List;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import baseUtil.BaseClass;
import dataTestObject.User;
import queries.UserQueries;

public class LoginByDataFromPostgreSQLDatabaseTest extends BaseClass {
	@DataProvider(name = "users_obj")
	// why we use object?
	// when it is not denined like String, Integer or mixed data present
	public Object[][] getUserObjects() {
		List<User> list = UserQueries.getUsers();
		Object[][] objects = new Object[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			objects[i] = new Object[] { list.get(i) };
		}
		return objects;
	}

	// alternative of object from above is iterator type
	@DataProvider(name = "users_iterator")
	public Iterator<User> getUserIterator() {
		List<User> list = UserQueries.getUsers();
		return list.iterator();

	}

	@Test(dataProvider = "users_obj")

	public void loginTest(User user) {
		login.validationLogoAndTitle();
		login.clickOnLogIn();
		login.ValidationEmailId(user.getEmailId());
		login.ValidationPassword(user.getPassword());
		login.ValideLogInButton();
		login.landingOnDashboard();
	}
//next class we will retrieve data from excel sheet
}
