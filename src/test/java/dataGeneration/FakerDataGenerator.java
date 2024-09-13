package dataGeneration;

import org.testng.annotations.Test;
import com.github.javafaker.Faker;

public class FakerDataGenerator {
	@Test
	void testGenerateDummyData() {
		Faker faker = new Faker();
		String fullName = faker.name().fullName();
		String firstName = faker.name().firstName();
		String userName = faker.name().username();
		String password = faker.internet().password(8, 16);
		String mobileNumber = faker.phoneNumber().phoneNumber();
		String email = faker.internet().emailAddress();
	}

}
