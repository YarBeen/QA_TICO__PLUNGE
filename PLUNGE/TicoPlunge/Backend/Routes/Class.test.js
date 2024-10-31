const request = require("supertest");
const mongoose = require("mongoose");
const express = require("express");
const classRouter = require("./Class");
require("dotenv").config();
const app = express();
app.use(express.json());
app.use("/api/class", classRouter);

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

describe("Class Routes Validation", () => {
  // Prueba para agregar una nueva clase
  it("should create a new class", async () => {
    const claseData = {
      date: new Date().toISOString(),
      capacity: 10,
      user: "60c72b2f9b1d8b3b50d47f4a",
      service: "60c72b2f9b1d8b3b50d47f4b",
      students: ["60c72b2f9b1d8b3b50d47f4a"],
    };
    const response = await request(app).post("/api/class").send(claseData);
    createdClassId = response.body.clase._id

    expect(response.status).toBe(201);
    expect(response.body).toHaveProperty("message", "Clase agregada exitosamente.");
    expect(response.body).toHaveProperty("clase");

    //Verificar datos
    expect(response.body.clase).toHaveProperty("date");
    expect(response.body.clase).toHaveProperty("capacity", claseData.capacity);
    expect(response.body.clase).toHaveProperty("user", claseData.user);
    expect(response.body.clase).toHaveProperty("service", claseData.service);

    //Borrar clase creada
    await request(app).delete(`/api/class/${createdClassId}`);
  });
  
  // Prueba para verificar la obtención de clases
  it("should retrieve all classes", async () => {
    const response = await request(app).get("/api/class");
    expect(response.status).toBe(200);
    expect(Array.isArray(response.body)).toBe(true);
  });

  // Prueba para eliminar una clase creada y clase que no exista
  it("should delete a class by ID", async () => {
    const classTest = {
      date: new Date().toISOString(),
      capacity: 10,
      user: "12c72b2f9b1d8b3b50d47f4a",
      service: "12c72b2f9b1d8b3b50d47f4b",
      students: ["12c72b2f9b1d8b3b50d47f4a"],
    };

    const createResponse = await request(app).post("/api/class").send(classTest);
    expect(createResponse.status).toBe(201);
    
    const claseId = createResponse.body.clase._id;

    //Eliminar clase
    const deleteResponse = await request(app).delete(`/api/class/${claseId}`);
    expect(deleteResponse.status).toBe(200);
    expect(deleteResponse.body).toHaveProperty("message", "Clase eliminada exitosamente.");

    //Eliminar la misma clase
    const deleteNonExistentResponse = await request(app).delete(`/api/class/${claseId}`);
    expect(deleteNonExistentResponse.status).toBe(404);
    expect(deleteNonExistentResponse.body).toHaveProperty("error", "Clase no encontrada.");
  });

  // Prueba para actualizar una clase con estudiantes inscritos
  it("should not update a class when it has students", async () => {
    const classTest = {
      date: new Date().toISOString(),
      capacity: 10,
      user: "60c72b2f9b1d8b3b50d47f4a",
      service: "60c72b2f9b1d8b3b50d47f4b",
      students: ["60c72b2f9b1d8b3b50d47f4a"],
    };
    const createResponse = await request(app).post("/api/class").send(classTest);
    const claseId = createResponse.body.clase._id;

    const updateData = {
      date: new Date().toISOString(),
      capacity: 20,
      user: "60c72b2f9b1d8b3b50d47f4a",
      service: "60c72b2f9b1d8b3b50d47f4b",
      students: ["60c72b2f9b1d8b3b50d47f4a"],
    };

    const updateResponse = await request(app).put(`/api/class/${claseId}`).send(updateData);
    expect(updateResponse.status).toBe(400);
    expect(updateResponse.body).toHaveProperty("error", "No se puede cambiar la clase cuando hay clientes inscritos.");

    //Borrar la clase creada
    await request(app).delete(`/api/class/${claseId}`);
  });

});
