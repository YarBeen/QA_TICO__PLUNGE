const request = require("supertest");
const mongoose = require("mongoose");
const express = require("express");
const feedbackRouter = require("./PrivateFeedback"); 
require("dotenv").config();
const { PrivateFeedback } = require("../Models/PrivateFeedback");
const app = express();
app.use(express.json());
app.use("/api/PrivateFeedback", feedbackRouter);
beforeAll(async () => {
    const connectionParams = {
      useNewUrlParser: true,
      useUnifiedTopology: true,
    };
    try {
      await mongoose.connect(process.env.DB, connectionParams);
    } catch (error) {
    }
  });
  afterAll(async () => {
    try {
      await mongoose.connection.close(); 
    } catch (error) {
    }
  });

  

  

  describe("POST /api/privatefeedback", () => { 
    it("should add a new comment", async () => {
      const newComment = { comentario: "Test373 " };
  
      const response = await request(app).post("/api/privatefeedback").send(newComment);
      console.log(response.body);  // Log the response to see validation errors, if any
      expect(response.status).toBe(201);
      expect(response.body).toHaveProperty("message", "Comentario agregado exitosamente.");
      expect(response.body).toHaveProperty("comentario");
      expect(response.body.comentario).toHaveProperty("comentario", newComment.comentario);
      let createdCommentId = response.body.comentario._id;
      if (createdCommentId) {
      await request(app).delete(`/api/privatefeedback/${createdCommentId}`);
      console.log(`Deleted comment with ID: ${createdCommentId}`);
    }
    });
  
    it("should return a 400 error if the comment is invalid", async () => {
      const invalidComment = { rating: 6 }; 
      
      const response = await request(app).post("/api/privatefeedback").send(invalidComment);
      expect(response.status).toBe(400);
      expect(response.body).toHaveProperty("error");
    });
    
    it("should return a 400 error if the comment already exists", async () => {
      const newComment = { comentario: "This is a test comment" };
      await request(app).post("/api/privatefeedback").send(newComment);
      const response = await request(app).post("/api/privatefeedback").send(newComment);

      expect(response.status).toBe(400);
      expect(response.body).toHaveProperty("error", "El comentario ya existe");
  });


  });
  
  describe("DELETE /api/privateFeedback/id", () => {
    it("should delete an existing feedback comment", async () => {
        const feedback = new PrivateFeedback({ comentario: "This is a test comment" });
        await feedback.save();
        const feedbackId = feedback._id;

        const response = await request(app).delete(`/api/privateFeedback/${feedbackId}`);
        expect(response.status).toBe(200);
        expect(response.body).toHaveProperty("message", "Comentario eliminado exitosamente.");

        const feedbackInDb = await PrivateFeedback.findById(feedbackId);
        expect(feedbackInDb).toBeNull();
    });});