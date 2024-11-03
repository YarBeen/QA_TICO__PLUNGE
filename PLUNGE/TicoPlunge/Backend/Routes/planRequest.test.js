const request = require("supertest");
const mongoose = require("mongoose");
const express = require("express");
const planRequestRouter = require("./planRequest");
const { Plan } = require("../Models/PlanModel");
const { User } = require("../Models/User");
const { PlanRequest } = require("../Models/PlanRequestModel");
const { Service } = require("../Models/ServiceModel");
require("dotenv").config();
const app = express();
app.use(express.json());
app.use("/api/planRequest", planRequestRouter);

describe("Plan Request Routes Validation", () => {
  let server;
  let planId;
  let serviceId;
  let userId;
  let planRequestId;
  beforeAll(async () => {
    const connectionParams = {
      useNewUrlParser: true,
      useUnifiedTopology: true,
    };
    try {
      await mongoose.connect(process.env.DB, connectionParams);
      console.log("Connected to database successfully");
    } catch (error) {
      console.error("Could not connect to database!", error);
    }
  });

  afterAll(async () => {
    try {
      await mongoose.connection.close();
      console.log("MongoDB connection closed");
    } catch (error) {
      console.error("Error closing MongoDB connection", error);
    }
  });
  beforeEach(async () => {
    const user = new User({
      firstName: "Test!",
      lastName: "User!",
      email: "testuserC@example.com",
      password: "Password123!",
      role: "Client",
    });
    const resUser = await user.save();
    userId = resUser._id;
    const plan = new Plan({
      name: "Test Plan",
      services: [
        { service: "605c72adf2d9f2a48c39e42e", credits: 10 },
        { service: "605c72adf2d9f2a48c39e42f", credits: 20 },
      ],
      price: 59.99,
    });
    let res = await plan.save();

    planId = plan._id;
    const planRequest = new PlanRequest({
      user: userId,
      plan: planId,
    });
    await planRequest.save();
    planRequestId = planRequest._id;
  });

  afterEach(async () => {
    const resUserAfterEach = await User.deleteOne({
      email: "testuserC@example.com",
    });

    await Plan.deleteOne({ planId });
    await PlanRequest.deleteOne({ planRequestId });
  });

  it("Should get plan requests", async () => {
    const res = await request(app).get("/api/planRequest");
    console.log(res.body);
    expect(res.status).toBe(200);
    expect(res.body.length).toBeGreaterThan(0);
  });
  it("should create a new plan Request ", async () => {
    const user = new User({
      firstName: "Test23",
      lastName: "User23",
      email: "testuser2223@example.com",
      password: "Password123!",
      role: "Client",
    });
    await user.save();

    const plan = new Plan({
      name: "Test Plan Request",
      services: [
        { service: "605c72adf2d9f2a48c39e42e", credits: 10 },
        { service: "605c72adf2d9f2a48c39e42f", credits: 20 },
      ],
      price: 59.99,
    });
    await plan.save();

    const res = await request(app).post("/api/planRequest").send({
      user: user._id,
      plan: plan._id,
    });

    expect(res.status).toBe(201);
    expect(res.body).toHaveProperty("user", String(user._id));
    expect(res.body).toHaveProperty("plan", String(plan._id));

    if (res.status === 201) {
      await Plan.deleteOne({ name: "Test Plan Request" });
      await User.deleteOne({ email: "testuser2223@example.com" });

      await PlanRequest.deleteOne({ planRequestId: res.body._id });
    }
  });

  it("should return 400 if the user is missing", async () => {
    const res = await request(app).post("/api/planRequest").send({
      user: "",
      plan: planId,
    });

    expect(res.status).toBe(400);
  });
  it("should return 400 if the plan is missing", async () => {
    const res = await request(app).post("/api/planRequest").send({
      user: userId,
      plan: "",
    });

    expect(res.status).toBe(400);
  });
});
