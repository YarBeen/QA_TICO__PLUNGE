const request = require("supertest");
const mongoose = require("mongoose");
const express = require("express");
const metaDataRouter = require("./metaData");
const { Metadata } = require("../Models/MetaDataModel");
require("dotenv").config();
const app = express();
app.use(express.json());
app.use("/api/metaData", metaDataRouter);

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
  userId = new mongoose.Types.ObjectId();

  const metadata = new Metadata({
    user: userId,
    phone: "1234567890",
    height: 180,
    weight: 75,
    birthday: "1990-01-01",
  });
  await metadata.save();
  metadataId = metadata._id;
});

afterEach(async () => {
  await Metadata.deleteMany({});
});

describe("meta data Routes Validation", () => {
  it("should create metadata when valid data is provided", async () => {
    const res = await request(app).post("/api/metadata").send({
      user: new mongoose.Types.ObjectId(),
      phone: "9876543210",
      height: 170,
      weight: 70,
      birthday: "1992-02-02",
    });
    expect(res.status).toBe(201);
    expect(res.body).toHaveProperty("message", "Metadata saved successfully");
    expect(res.body.metadata).toHaveProperty("user");
    expect(res.body.metadata).toHaveProperty("phone", "9876543210");
    expect(res.body.metadata).toHaveProperty("height", 170);
    expect(res.body.metadata).toHaveProperty("weight", 70);
    expect(res.body.metadata).toHaveProperty(
      "birthday",
      "1992-02-02T00:00:00.000Z"
    );
    if (res.body.metadata) {
      await Metadata.findByIdAndDelete(res.body.metadata._id);
    }
  });

  it("should return code 500 if meta data is invalidad", async () => {
    const res = await request(app).post("/api/metadata").send({
      phone: "9876543210",
      height: 170,
    });
    expect(res.status).toBe(500);
    expect(res.body).toHaveProperty("message", "Failed to save metadata");
  });

  it("should return 404 if user doesnt exist", async () => {
    const nonExistentUserId = new mongoose.Types.ObjectId();
    const res = await request(app).get(`/metadata/${nonExistentUserId}`);

    expect(res.status).toBe(404);
  });
});
