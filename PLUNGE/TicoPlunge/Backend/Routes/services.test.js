const request = require("supertest");
const mongoose = require("mongoose");
const express = require("express");
const serviceRouter = require("./service"); 
require("dotenv").config();

const app = express();
app.use(express.json());
app.use("/api/service", serviceRouter);

beforeAll(async () => {
    const connectionParams = {
        useNewUrlParser: true,
        useUnifiedTopology: true,
    };
    try {
        await mongoose.connect(process.env.DB, connectionParams);
    } catch (error) {
        console.error("Error connecting to the database", error);
    }
});

afterAll(async () => {
    try {
        await mongoose.connection.close();
    } catch (error) {
        console.error("Error closing the database connection", error);
    }
});

describe("GET /api/service", () => {
    it("should return all services", async () => {
        const response = await request(app).get("/api/service");
        expect(response.status).toBe(200);
        expect(Array.isArray(response.body)).toBe(true);
    });
});

describe("POST /api/service", () => {
    it("should create a new service", async () => {
        const newService = {
            name: "Service Test2",
            encargados: [] 
        };

        const response = await request(app).post("/api/service").send(newService);
        expect(response.status).toBe(201);
        expect(response.body).toHaveProperty("message", "Servicio agregado exitosamente.");
        expect(response.body.servicio).toHaveProperty("_id");
        expect(response.body.servicio).toHaveProperty("name", newService.name);
        createdServiceId = response.body.servicio._id;
        if (createdServiceId) {
          await request(app).delete(`/api/service/${createdServiceId}`);
          console.log(`Deleted comment with ID: ${createdServiceId}`);
        }

    });

    it("should return a 400 error if the service data is invalid", async () => {
        const invalidService = {
            encargados: [] 
        };

        const response = await request(app).post("/api/service").send(invalidService);
        expect(response.status).toBe(400);
        expect(response.text).toContain("Name"); 
    });
});


describe("DELETE /api/service/id", () => {
  it("should delete a service by ID", async () => {
      const newService = {
          name: "Service Deletion Testo D:",
          encargados: []
      };
      
      const postResponse = await request(app).post("/api/service").send(newService);
      expect(postResponse.status).toBe(201);
      const serviceIdToDelete = postResponse.body.servicio._id;

      const deleteResponse = await request(app).delete(`/api/service/${serviceIdToDelete}`);
      expect(deleteResponse.status).toBe(200);
      expect(deleteResponse.body).toHaveProperty("message", "Servicio eliminado exitosamente.");
  });
});

describe("PUT /api/service/id", () => {
  it("should update an existing service", async () => {
      const newService = {
          name: "Service to Update Testo",
          encargados: []
      };

      const postResponse = await request(app).post("/api/service").send(newService);
      expect(postResponse.status).toBe(201); 

      const serviceIdToUpdate = postResponse.body.servicio._id;

      const updatedData = {
          name: "Updated Service Names",
          encargados: []
      };

      const putResponse = await request(app).put(`/api/service/${serviceIdToUpdate}`).send(updatedData);
      expect(putResponse.status).toBe(200); 

      const getUpdatedService = await request(app).get(`/api/service/${serviceIdToUpdate}`);
      expect(getUpdatedService.status).toBe(200);
      expect(getUpdatedService.body).toHaveProperty("name", updatedData.name);
        if (serviceIdToUpdate) {
          await request(app).delete(`/api/service/${serviceIdToUpdate}`);
          console.log(`Deleted comment with ID: ${serviceIdToUpdate}`);
        }
  });
});

