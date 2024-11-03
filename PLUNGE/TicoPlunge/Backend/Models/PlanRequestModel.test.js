const { validatePlanRequest, PlanRequest } = require("./PlanRequestModel");
const { User } = require("./User");
const { Plan } = require("./PlanModel");
const mongoose = require("mongoose");
describe("PlanRequest Model Validation", () => {
  let user;
  let plan;

  it("should create a PlanRequest if ID and Plan ID are provided", async () => {
    const planRequestData = {
      user: new mongoose.Types.ObjectId().toString(),
      plan: new mongoose.Types.ObjectId().toString(),
    };

    const { error, value } = validatePlanRequest(planRequestData);

    expect(error).toBeUndefined();
    expect(value).toEqual(planRequestData);
  });

  it("should create a PlanRequest if user ID and Plan ID are provided", async () => {
    const planRequestData = {
      user: new mongoose.Types.ObjectId().toString(),
      plan: new mongoose.Types.ObjectId().toString(),
    };

    const { error, value } = validatePlanRequest(planRequestData);

    expect(error).toBeUndefined();
    expect(value).toEqual(planRequestData);
  });

  it("should fail if userID is missing", async () => {
    const planRequestData = {
      user: "",
      plan: new mongoose.Types.ObjectId().toString(),
    };

    const { error, value } = validatePlanRequest(planRequestData);

    expect(error).toBeDefined();
    expect(value).toEqual(planRequestData);
  });

  it("should fail if planID is missing", async () => {
    const planRequestData = {
      user: new mongoose.Types.ObjectId().toString(),
      plan: "",
    };

    const { error, value } = validatePlanRequest(planRequestData);

    expect(error).toBeDefined();
    expect(value).toEqual(planRequestData);
  });
});
