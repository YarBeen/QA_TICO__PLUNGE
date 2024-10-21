const request = require("supertest");
const mongoose = require("mongoose");
const express = require("express");
const feedbackRouter = require("./Feedback");
require("dotenv").config();
const app = express();
app.use(express.json());
app.use("/api/feedback", feedbackRouter);
beforeAll(async () => {
  // Connect to the database before running tests
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
    await mongoose.connection.close(); // Close the connection
    console.log("MongoDB connection closed");
  } catch (error) {
    console.error("Error closing MongoDB connection", error);
  }
});
describe("POST /api/feedback", () => {
  it("should add a new comment", async () => {
    const newComment = {
      comentario: "TEST COMMENT.",
      rating: 3,
    };

    const response = await request(app).post("/api/feedback").send(newComment);
    console.log(response.body);
    expect(response.status).toBe(201);
    let createdCommentId = response.body.comentario._id;
    if (createdCommentId) {
      await request(app).delete(`/api/feedback/${createdCommentId}`);
      console.log(`Deleted comment with ID: ${createdCommentId}`);
    }
    /* expect(response.body).toHaveProperty(
      "message",
      "Comentario agregado exitosamente."
    );
    expect(response.body).toHaveProperty("comentario");
    expect(response.body.comentario).toHaveProperty(
      "content",
      "This is a new test comment."
    );*/
  });
});
