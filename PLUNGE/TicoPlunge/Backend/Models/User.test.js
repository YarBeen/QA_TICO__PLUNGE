const { validate } = require("../Models/User");

describe("User Model Validation", () => {
  
    it("should pass validation with correct data", () => {
        const validUserData = {
            firstName: "Test",
            lastName: "User",
            email: "testuser@example.com",
            password: "Password123!",
            role: "Client",
        };

        const { error } = validate(validUserData);
        expect(error).toBeUndefined(); 
    });

    it("should return an error for invalid data", () => {
        const invalidUserData = {
            firstName: "Test",
            lastName: "User",
            email: "invalid-email-format",
            password: "Password123!",
            role: "Client", 
        };

        const { error } = validate(invalidUserData);
        expect(error).toBeDefined(); 
    });

});
