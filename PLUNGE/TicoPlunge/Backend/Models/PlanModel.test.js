const { validatePlan, Plan } = require("./PlanModel");
describe("Plan Model Validation", () => {
  it("should pass validation with correct data", () => {
    const validPlanData = {
      name: "Basic Plan",
      services: [
        { service: "605c72adf2d9f2a48c39e42e", credits: 10 },
        { service: "605c72adf2d9f2a48c39e42f", credits: 20 },
      ],
      price: 49.99,
    };

    const { error, value } = validatePlan(validPlanData);

    expect(error).toBeUndefined();
    expect(value).toEqual(validPlanData);
    /*if (error) {
        // Imprime o accede al mensaje del primer error
        console.log(error.details[0].message); // "Name is required"
        
        // O usa el detalle del error en la expectativa de la prueba
        expect(error.details[0].message).toMatch(/Name is required/);
      }*/
  });

  it("should pass validation with correct data", () => {
    const validPlanData = {
      name: "Basic Plan",
      services: [
        { service: "ABC", credits: -10 },
        { service: "605c72adf2d9f2a48c39e42f", credits: -20 },
      ],
      price: 49.99,
    };

    const { error, value } = validatePlan(validPlanData);

    expect(error).toBeDefined();
    expect(value).toEqual(validPlanData);
  });
});
