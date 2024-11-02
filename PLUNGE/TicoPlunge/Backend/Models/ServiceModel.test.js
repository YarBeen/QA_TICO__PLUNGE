const { validateService } = require("../Models/ServiceModel");

describe("Service Model Validation", () => {
  
    it("should validate successfully with correct data", () => {
        const validServiceData = {
            name: "Test Service",
            encargados: [], 
        };

        const { error } = validateService(validServiceData);
        expect(error).toBeUndefined(); 
    });

    it("should return an error for invalid data", () => {
        const invalidServiceData = {
            encargados: []
        };

        const { error } = validateService(invalidServiceData);
        expect(error).toBeDefined(); 
    });

});
