const request = require("supertest");
const mongoose = require("mongoose");
const express = require("express");
const planRouter = require("./plan");
const { Plan } = require("../Models/PlanModel");
const { Service } = require("../Models/ServiceModel");
require("dotenv").config();
const app = express();
app.use(express.json());
app.use("/api/plan", planRouter);

describe("Plan Routes Validation", () => {
  let server;
  let planId;
  let serviceId;
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
    const plan = new Plan({
      name: "Basic Plan",
      services: [
        { service: "605c72adf2d9f2a48c39e42e", credits: 10 },
        { service: "605c72adf2d9f2a48c39e42f", credits: 20 },
      ],
      price: 49.99,
    });
    let res = await plan.save();

    planId = plan._id;
  });

  afterEach(async () => {
    await Plan.deleteOne({ planId });
  });

  it("Should get plans", async () => {
    const res = await request(app).get("/api/plan");
    console.log(res.body);
    expect(res.status).toBe(200);
    expect(res.body.length).toBeGreaterThan(0);
  });
  it("should create a new plan ", async () => {
    const res = await request(app)
      .post("/api/plan")
      .send({
        name: "Plan Ultra Premium",
        services: [{ service: "605c72adf2d9f2a48c39e42e", credits: 20 }],
        price: 189.99,
      });
    console.log(res.body);
    expect(res.status).toBe(201);
    expect(res.body).toHaveProperty("message", "Plan creado exitosamente.");
    expect(res.body.plan).toHaveProperty("name", "Plan Ultra Premium");
    if (res.status === 201) {
      await Plan.deleteOne({ name: "Plan Ultra Premium" });
    }
  });

  it("should return 500 if plan is created with the same name ", async () => {
    const res = await request(app)
      .post("/api/plan")
      .send({
        name: "Basic Plan",
        services: [{ service: "605c72adf2d9f2a48c39e42e", credits: 20 }],
        price: 19.99,
      });
    console.log(res.body);
    expect(res.status).toBe(400);
    expect(res.body).toHaveProperty(
      "error",
      "Ya existe un plan con ese nombre."
    );
  });

  it("should return 400 if the services are missing", async () => {
    const res = await request(app).post("/api/plan").send({
      name: "No service Plan",
      services: "",
      price: 109.99,
    });

    expect(res.status).toBe(400);
  });
  it("should return 400 if the price is negative", async () => {
    const res = await request(app)
      .post("/api/plan")
      .send({
        name: "Negative Price Plan",
        services: [{ service: "605c72adf2d9f2a48c39e42e", credits: 20 }],
        price: -1,
      });

    expect(res.status).toBe(400);
  });
});
