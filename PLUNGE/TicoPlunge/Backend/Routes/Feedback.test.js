const request = require("supertest");
const mongoose = require("mongoose");
const express = require("express");
const feedbackRouter = require("./Feedback");
require("dotenv").config();
const app = express();
app.use(express.json());
app.use("/api/feedback", feedbackRouter)

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

describe("Feedback Routes Validation", () => {
  //Prueba para verificar agregar nuevo comentario
  it("should add a new comment", async () => {
    const testComment = {
      comentario: "TEST COMMENT.",
      rating: 3,
    };
    const response = await request(app).post("/api/feedback").send(testComment);
    console.log(response.body);
    expect(response.status).toBe(201);
    let deleteCommentId = response.body.comentario._id;
    //Elimnar comentario creado
    if (deleteCommentId) {
      await request(app).delete(`/api/feedback/${deleteCommentId}`);
      //console.log(`Deleted comment with ID: ${createdCommentId}`);
    }
  });

  // Prueba para verificar eliminar un comentario agregado
  it("should delete the added comment", async () => {
    const newComment = {
      comentario: "Prueba de eliminar",
      rating: 3,
    };
    //Se crea comentario para eliminarlo
    const response = await request(app).post("/api/feedback").send(newComment);
    const createdCommentId = response.body.comentario._id;
    const deleteResponse = await request(app).delete(`/api/feedback/${createdCommentId}`);
    expect(deleteResponse.status).toBe(200);
    expect(deleteResponse.body).toHaveProperty("message", "Comentario eliminado exitosamente.");
  });

  // Prueba para verificar error al agregar un comentario sin campos requeridos
  it("should return 400 when required fields are missing", async () => {
    const invalidComment = {
      rating: 3,
    };
    const response = await request(app).post("/api/feedback").send(invalidComment);
    // console.log("Response Body:", response.body);
    expect(response.status).toBe(400);
    expect(response.body).toHaveProperty("error");
    expect(response.body.error).toMatch(/"Comentario" is required/);
  });

  // Prueba para verificar actualizar un comentario
  it("should update an existing comment", async () => {
    const newComment = {
      comentario: "Comentario original",
      rating: 2,
    };
  
    // Crear el comentario
    const response = await request(app).post("/api/feedback").send(newComment);
    const commentId = response.body.comentario._id;
  
    // Actualización del comentario
    const updatedComment = {
      comentario: "Comentario actualizado",
      rating: 5,
    };
  
    const updateResponse = await request(app).put(`/api/feedback/${commentId}`).send(updatedComment);
  
    expect(updateResponse.status).toBe(200);
    expect(updateResponse.body.comentario).toHaveProperty("comentario","Comentario actualizado");
    expect(updateResponse.body.comentario).toHaveProperty("rating", 5);
  
    // Eliminar comentario
    await request(app).delete(`/api/feedback/${commentId}`);
  });

  //Prueba para verificar actualizar un comentario con un Id no permitido
  it("should return 400 for invalid Id in update request", async () => {
    const invalidId = "12345";
    const updatedComment = {
      comentario: "Comentario inválido",
      rating: 4,
    };
  
    const response = await request(app).put(`/api/feedback/${invalidId}`).send(updatedComment);
  
    expect(response.status).toBe(400);
    expect(response.body).toHaveProperty("error", "ID de comentario no válido");
  });
});
