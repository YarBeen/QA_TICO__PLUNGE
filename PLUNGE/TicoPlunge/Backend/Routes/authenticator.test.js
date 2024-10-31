const request = require("supertest");
const mongoose = require("mongoose");
const express = require("express");
const authRouter = require("./authenticator");
const { User } = require("../Models/User");
const jwt = require("jsonwebtoken");
require("dotenv").config();

const app = express();
app.use(express.json());
app.use("/api/auth", authRouter);

beforeAll(async () => {
  // Conexión a la base de datos
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
  // Cerrar la conexión después de las pruebas
  try {
    await mongoose.connection.close();
    console.log("MongoDB connection closed");
  } catch (error) {
    console.error("Error closing MongoDB connection", error);
  }
});

describe("Authenticator Routes Validation", () => {
  // Prueba para error de credenciales inválidas
  it("should return 401 for invalid email or password", async () => {
    const response = await request(app)
      .post("/api/auth")
      .send({ email: "invalidUser@test.com", password: "invalidPassword" });

    expect(response.status).toBe(401);
    expect(response.body).toHaveProperty("message", "Invalid Email or Password");
  });

  // Prueba para error de validación con campos faltantes
  it("should return 400 for missing fields", async () => {
    const response = await request(app).post("/api/auth").send({ email: "test@email.com" }); //Campo faltante (contraseña)
    expect(response.status).toBe(400);
    expect(response.body).toHaveProperty("message");
  });
  // Prueba para un token inválido
  it("should return 500 for invalid token", async () => {
    const response = await request(app).get("/api/auth?token=invalidtoken");
    expect(response.status).toBe(500);
    expect(response.body).toHaveProperty("message", "Internal Server Error");
  });
});
