package in.fssa.productprice;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.productprice.exception.ServiceException;
import in.fssa.productprice.exception.ValidationException;
import in.fssa.productprice.model.UserEntity;
import in.fssa.productprice.service.UserService;

public class TestCaseForUser {

		
		  
		////  GENERATE AUTOMATIC STRING FOR EMAIL
		
		 private String generateRandomString(int length) {
		        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		        StringBuilder randomString = new StringBuilder();
		        java.util.Random random = new java.util.Random();

		        for (int i = 0; i < length; i++) {
		            int index = random.nextInt(characters.length());
		            randomString.append(characters.charAt(index));
		        } 

		        return randomString.toString();
		    }
		 
	
		 
		 private long generateRandomPhoneNumber(int length) {
		        java.util.Random random = new java.util.Random();

		       
		        long minPhoneNumber = 600_000_0000L; 
		        long maxPhoneNumber = 999_999_9999L; 

		       
		        long phoneNumber = minPhoneNumber + (long) (random.nextDouble() * (maxPhoneNumber - minPhoneNumber + 1));

		        return phoneNumber;
		    }
		   
		 @Test
		    void testCreateUserWithValidInput() {
		        UserService userService = new UserService();

		        UserEntity newUser = new UserEntity();

		        newUser.setName("santhosh");

		        String randomString = generateRandomString(8);
		        newUser.setEmail(randomString + "@gmail.com");

		        long randomPhoneNumber = generateRandomPhoneNumber(10);
		        newUser.setPhoneNumber(randomPhoneNumber);

		        newUser.setPassword("Santhosh@12");
		       

		        assertDoesNotThrow(() -> {
		            userService.create(newUser);
		        });
		    }
		 
		//// TEST FOR INVALID INPUT
		  
		@Test
		void testCreateUserWithInvalidInput() {
			
			UserService userService = new UserService();
			Exception exception = assertThrows(ValidationException.class, () -> {
				userService.create(null);
			});
			String exceptedMessage = "Invalid user input";
			String actualMessage = exception.getMessage();
			
			assertEquals(exceptedMessage,actualMessage);
		}
		
		//// TEST FOR EMAIL WITH NULL
		
		@Test 
		void testCreateUserWithEmailNull() {
			
			UserService userService = new UserService();
			
			UserEntity newUser = new UserEntity();
			newUser.setName("Sound");
			newUser.setEmail(null);
			newUser.setPhoneNumber(7908946112l);
			newUser.setPassword("Sound@12");
			
			
			Exception exception = assertThrows(ValidationException.class, () -> {
				userService.create(newUser);
			});
			String exceptedMessage = "Email cannot be null or empty";
			String actualMessage = exception.getMessage();
			
			assertEquals(exceptedMessage,actualMessage);
		}
		
		//// TEST FOR EMAIL WITH EMPTY STRING
		
		@Test 
		void testCreateUserWithEmailEmpty() {
			
			UserService userService = new UserService();
			
			UserEntity newUser = new UserEntity();
			newUser.setName("Uthra");
			newUser.setEmail("");
			newUser.setPhoneNumber(7908946112l);
			newUser.setPassword("Uthra@12");
			
			
			Exception exception = assertThrows(ValidationException.class, () -> {
				userService.create(newUser);
			});
			String exceptedMessage = "Email cannot be null or empty";
			String actualMessage = exception.getMessage();
			
			assertEquals(exceptedMessage,actualMessage);
		}
		
		////TEST FOR EMAIL WITH PATTERN	
		@Test 
		void testCreateUserWithEmailPattern() {
				
			UserService userService = new UserService();
				
			UserEntity newUser = new UserEntity();
			newUser.setName("Sounarya");
			newUser.setEmail("uthragmailcom");
			newUser.setPhoneNumber(7908946112l);
			newUser.setPassword("Uthra@12");
			
				
			Exception exception = assertThrows(ValidationException.class, () -> {
				userService.create(newUser);
			});
			String exceptedMessage = "Email doesn't match the pattern";
			String actualMessage = exception.getMessage();
				
			assertEquals(exceptedMessage,actualMessage);
		}
		
		////TEST FOR EMAIL WITH ALREADY EXISTS	
		
		@Test 
		void testCreateUserWithEmailExists() {
				
			UserService userService = new UserService();
				
			UserEntity newUser = new UserEntity();
			newUser.setName("SoundaryaReddy");
			newUser.setEmail("u1223@gmail.com");
			newUser.setPhoneNumber(7908946112l);
			newUser.setPassword("Ut@12");
			
				
			Exception exception = assertThrows(ValidationException.class, () -> {
				userService.create(newUser);
			});
			String exceptedMessage = "This user is already exist";
			String actualMessage = exception.getMessage();
				
			assertEquals(exceptedMessage,actualMessage);
		}
		
		
		/// TEST FOR PASSWORD WITH NULL
		
		@Test
		void testCreateUserWithPasswordNull() {
			
			UserService userService = new UserService();
			
			UserEntity newUser = new UserEntity();
			
			newUser.setName("Soundarya");
			newUser.setEmail("sound@gmail.com");
			newUser.setPhoneNumber(7908946112l);
			newUser.setPassword(null);
			

			Exception exception = assertThrows(ValidationException.class, () -> {
				userService.create(newUser);
			});
			String expectedMessage = "Password cannot be null or empty";
			String actualMessage = exception.getMessage();

			assertEquals(expectedMessage,actualMessage);
		}
		
		//// TEST FOR PASSWORD WITH EMPTY STRING

		@Test
		void testCreateUserWithPasswordEmpty() {
			
			UserService userService = new UserService();
		
			UserEntity newUser = new UserEntity();
			
			newUser.setName("Soundarya");
			newUser.setEmail("Sound@gmail.com");
			newUser.setPhoneNumber(7908946112l);
			newUser.setPassword("");
			

			Exception exception = assertThrows(Exception.class, () -> {
				userService.create(newUser);
			});
			String expectedMessage = "Password cannot be null or empty";
			String actualMessage = exception.getMessage();

			assertEquals(expectedMessage,actualMessage);
		}
		
		////TEST FOR PASSWORD WITH PASSWORD LENGTH 

		@Test
		void testCreateUserWithPasswordLength() {
			
			UserService userService = new UserService();
		
			UserEntity newUser = new UserEntity();
			
			newUser.setName("Sound");
			newUser.setEmail("Sound@gmail.com");
			newUser.setPhoneNumber(7908946112l);
			newUser.setPassword("th!29");
			

			Exception exception = assertThrows(Exception.class, () -> {
				userService.create(newUser);
			});
			String expectedMessage = "Password must contain atleast 8 characters";
			String actualMessage = exception.getMessage();

			assertEquals(expectedMessage,actualMessage);
		}

		
		////TEST FOR PASSWORD WITH PATTERN 

		@Test
		public void testCreateUserWithPasswordPattern() {
			
			UserService userService = new UserService();
		
			UserEntity newUser = new UserEntity();
			
			newUser.setName("Sindhu");
			newUser.setEmail("utrgh@gmail.com");
			newUser.setPhoneNumber(7908946112l);
			newUser.setPassword("Uthrakannan");
			
			Exception exception = assertThrows(Exception.class, () -> {
				userService.create(newUser);
			});
			String expectedMessage = "Password doesn't match the pattern";
			String actualMessage = exception.getMessage();

			assertEquals(expectedMessage,actualMessage);
		}
		
		//// TEST FOR NAME WITH NULL

		@Test
		void testCreateUserWithNameNull() {
			
			UserService userService = new UserService();
			
			UserEntity newUser = new UserEntity();

			newUser.setName(null);
			newUser.setEmail("Soun@gmail.com");
			newUser.setPhoneNumber(7908946112l);
			newUser.setPassword("Uthra@12");
			
			

			Exception exception = assertThrows(ValidationException.class, () -> {
				userService.create(newUser);
			});
			String expectedMessage = "Name cannot be null or empty";
			String actualMessage = exception.getMessage();

			assertEquals(expectedMessage,actualMessage);
		}
		
		//// TEST FOR NAME WITH EMPTY STRING

		@Test
		void testCreateUserWithNameEmpty() {
			UserService userService = new UserService();
			UserEntity newUser = new UserEntity();

			newUser.setName("");
			newUser.setEmail("uthra@gmail.com");
			newUser.setPhoneNumber(7908946112l);
			newUser.setPassword("Uthra@12");
			

			Exception exception = assertThrows(ValidationException.class, () -> {
				userService.create(newUser);
			});
			String expectedMessage = "Name cannot be null or empty";
			String actualMessage = exception.getMessage();

			assertEquals(expectedMessage,actualMessage);
		}
		
		////TEST FOR NAME WITH PATTERN

		@Test
		void testCreateUserWithNamePattern() {
			UserService userService = new UserService();
			UserEntity newUser = new UserEntity();

			newUser.setName("1245");
			newUser.setEmail("uthra@gmail.com");
			newUser.setPhoneNumber(7908946112l);
			newUser.setPassword("Uthra@12");
			

			Exception exception = assertThrows(ValidationException.class, () -> {
				userService.create(newUser);
			});
			String expectedMessage = "Name doesn't match the pattern";
			String actualMessage = exception.getMessage();

			assertEquals(expectedMessage,actualMessage);
		}
		
		//// TEST FOR PHONE NUMBER WITH 0
		
		@Test
		void testCreateUserWithPhoneNumber () {
			
			UserService userService = new UserService();
			
			UserEntity newUser = new UserEntity();
			
			newUser.setName("Uthra");
			newUser.setEmail("uthra@gmail.com");
			newUser.setPhoneNumber(0l);
			newUser.setPassword("Uthra@12");
			
			
			Exception exception = assertThrows(ValidationException.class, () -> {
				userService.create(newUser);
			});
			String expectedMessage = "Invalid phone number";
			String actualMessage = exception.getMessage();

			assertEquals(expectedMessage,actualMessage);
			
		}
		
		//// TEST FOR PHONE NUMBER WITH LENGHT
		
		@Test
		void testCreateUserWithPhoneNumberLength () {
			
			UserService userService = new UserService();
			
			UserEntity newUser = new UserEntity();
			
			newUser.setName("Uthra");
			newUser.setEmail("uthra@gmail.com");
			newUser.setPhoneNumber(9781246678324l);
			newUser.setPassword("Uthra@12");
			
			
			Exception exception = assertThrows(ValidationException.class, () -> {
				userService.create(newUser);
			});
			String expectedMessage = "Invalid phone number";
			String actualMessage = exception.getMessage();

			assertEquals(expectedMessage,actualMessage);
			
		}
		
		//// TEST FOR PHONE NUMBER WITH PATTERN
		
		@Test
		public void testCreateUserWithInvalidPhoneNumber () {
			
			UserService userService = new UserService();
			
			UserEntity newUser = new UserEntity();
			
			newUser.setName("Uthra");
			newUser.setEmail("uthra@gmail.com");
			newUser.setPhoneNumber(2348588980l);
			newUser.setPassword("Uthra@12");
			
			
			Exception exception = assertThrows(ValidationException.class, () -> {
				userService.create(newUser);
			});
			String expectedMessage = "Invalid phone number";
			String actualMessage = exception.getMessage();

			assertEquals(expectedMessage,actualMessage);
			
		}
		
		////TEST FOR PHONE NUMBER WITH ALREADY EXISTS	
		
		@Test 
		public void testCreateUserWithPhoneNumberExists() {
				
			UserService userService = new UserService();
				
			 UserEntity newUser = new UserEntity();
		        newUser.setName("soundarya");
		        String randomString = generateRandomString(8);
		        newUser.setEmail("cbuhs43@gmail.com");
		        newUser.setPhoneNumber(9340697100l); // Same phone number as existing user
		        newUser.setPassword("soun@12");
		       

		        Exception exception = assertThrows(ValidationException.class, () -> {
		            userService.create(newUser);
		        });

		        String expectedMessage = "This user is already exist";
		        String actualMessage = exception.getMessage();

		        assertEquals(expectedMessage, actualMessage);
		}
		
		
		
		
		
		//// TEST FOR GET ALL USERS
		
		@Test
		void getAllUsers() throws ServiceException {
			
			UserService userService = new UserService();
			assertDoesNotThrow (() -> {
				Set<UserEntity> userList = UserService.findAll();
				System.out.println(userList);
			});
			
		}
		
		//// TEST FOR GET USER BY ID
		
		@Test
		void getById() throws ServiceException, ValidationException {
			
			UserService userService = new UserService();
			
			assertDoesNotThrow (() -> {
				UserEntity userList = userService.findById(2);
				System.out.println(userList);
			});
			
		}
		
		//// TEST FOR UPDATE USER
		
		@Test
		void testUpdateUser() {

			UserService userService = new UserService();
			
			UserEntity updateUser = new UserEntity();
			
			updateUser.setName("UthraKannan");
			updateUser.setPassword("Ramya@12");
			
			
			assertDoesNotThrow(() -> {
				userService.update(1, updateUser);
			});
			
		}
		
		//// TEST FOR DELETE USER
		
		@Test
		void deleteUser() throws ServiceException, ValidationException {
			
			UserService userService = new UserService();
			assertDoesNotThrow ( () -> {
				userService.delete(2);
			});
			
		}
		
	}

