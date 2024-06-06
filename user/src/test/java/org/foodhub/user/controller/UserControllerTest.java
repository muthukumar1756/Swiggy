package org.foodhub.user.controller;

import org.foodhub.common.json.JsonElement;
import org.foodhub.common.json.JsonFactory;
import org.foodhub.user.model.user.User;
import org.foodhub.user.service.UserService;
import org.foodhub.user.service.internal.impl.UserServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import org.mockito.Mockito;

/**
 * <p>
 * The UserTest class is designed to perform unit tests on the User controller class.It ensures that the methods and
 * functionalities of the User controller class are working correctly by providing a series of test cases.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
final class UserControllerTest {

    private static final String STATUS = "status";
    private final UserController userController;
    private final UserService userService;
    private final JsonFactory jsonFactory;

    public UserControllerTest() {
        jsonFactory = JsonFactory.getInstance();
        userController = UserController.getInstance();
        userService = Mockito.mock(UserServiceImpl.class);

        userController.setUserService(userService);
    }

    /**
     * <p>
     *  Verifies that the getUserById method correctly retrieves user details for given user ids.
     * </p>
     */
    @ParameterizedTest
    @ValueSource(longs = {1, 50, 47})
    void shouldReturnCorrectUserDetailsForGivenUserIds(final long userId) {
        final User user = new User.UserBuilder().setId(userId).build();

        Mockito.when(userService.getUserById(userId)).thenReturn(jsonFactory.createObjectNode().build(user).asBytes());
        final byte[] result = userController.getUserById(userId);
        final JsonElement jsonElement = jsonFactory.asJsonArray(result);

        if (jsonElement.hasElement("id")) {
            final String value = jsonElement.getValue("id");

            Assertions.assertEquals(String.valueOf(userId), value);
        }
    }

    /**
     * <p>
     * Checks the functionalities of the user profile creation is working correctly by providing a series of test cases.
     * </p>
     */
    @ParameterizedTest
    @CsvSource({
            "Muthu kumar,muthukumar@gmail.com,9832178952,Kumar@1234,User profile was created",
            "Mk,muthukumar@gmail.com,9832178952,Kumar@1234,Enter a valid name",
            "Muthu kumar,kumar@g,9832178952,Kumar@1234,Enter a valid email id",
            "Muthu kumar,muthukumar@gmail.com,74105,Kumar@1234,Enter a valid phone number",
            "Muthu kumar,muthukumar@gmail.com,9832178952,kumar,Enter a valid password"
    })
    void shouldValidateAndCreateUserProfileBasedOnInputData(final String name, final String emailId, final String phoneNumber,
                                  final String password, final String expectedStatus) {
        final User user = new User.UserBuilder().setName(name).setEmailId(emailId).setPhoneNumber(phoneNumber)
                .setPassword(password).build();

        Mockito.when(userService.createUserProfile(user)).thenReturn(jsonFactory.createObjectNode()
                .put(STATUS, expectedStatus).asBytes());
        final byte[] result = userController.createUserProfile(user);
        final JsonElement jsonElement = jsonFactory.asJsonArray(result);

        if (jsonElement.hasElement(STATUS)) {
            final String value = jsonElement.getValue(STATUS);

            Assertions.assertEquals(expectedStatus, value);
        }
    }

    /**
     * <p>
     * Checks the functionalities of the user profile creation is working correctly by providing a series of test cases.
     * </p>
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/user_data.csv")
    void shouldValidateAndCreateUserProfileBasedOnCsvData(final String name, final String emailId, final String phoneNumber,
                                  final String password, final String expectedStatus) {
        final User user = new User.UserBuilder().setName(name).setEmailId(emailId).setPhoneNumber(phoneNumber)
                .setPassword(password).build();

        Mockito.when(userService.createUserProfile(user)).thenReturn(jsonFactory.createObjectNode()
                .put(STATUS, expectedStatus).asBytes());
        final byte[] result = userController.createUserProfile(user);
        final JsonElement jsonElement = jsonFactory.asJsonArray(result);

        if (jsonElement.hasElement(STATUS)) {
            final String value = jsonElement.getValue(STATUS);

            Assertions.assertEquals(expectedStatus, value);
        }
    }
}