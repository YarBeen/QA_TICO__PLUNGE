const request = require("supertest");
const mongoose = require("mongoose");
const express = require("express");
const purchaseHistoryRouter = require("./purchaseHistory"); 
require("dotenv").config();

const app = express();
app.use(express.json());
app.use("/api/purchaseHistory", purchaseHistoryRouter);

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

describe("GET /api/purchaseHistory", () => {
    it("should return all purchase history entries", async () => {
        const response = await request(app).get("/api/purchaseHistory");
        expect(response.status).toBe(200);
        expect(Array.isArray(response.body)).toBe(true);
    });
});

describe("POST /api/purchaseHistory", () => {
    it("should create a new purchase history entry", async () => {
        const newEntry = {
            buyerName: "Ozuna",
            totalAmount: 100,
            detail: "EpicOzuna"
        };

        const response = await request(app).post("/api/purchaseHistory").send(newEntry);
        expect(response.status).toBe(201);
        expect(response.body).toHaveProperty("_id"); 
        expect(response.body).toHaveProperty("buyerName", newEntry.buyerName);
    });

    it("should return a 400 error if the entry is invalid", async () => {
        const invalidEntry = { totalAmount: 50 };
        const response = await request(app).post("/api/purchaseHistory").send(invalidEntry);
        expect(response.status).toBe(400);
        expect(response.body).toHaveProperty("message"); 
    });
});

describe("DELETE /api/purchaseHistory/id", () => {
    it("should delete a purchase history entry", async () => {
        const newEntry = {
            buyerName: "jorge el buyer",
            totalAmount: 100,
            detail: "Purchased an item"
        };

        const createResponse = await request(app).post("/api/purchaseHistory").send(newEntry);
        const createdId = createResponse.body._id; 

        const deleteResponse = await request(app).delete(`/api/purchaseHistory/${createdId}`);
        expect(deleteResponse.status).toBe(200);
        expect(deleteResponse.body).toHaveProperty("message", "Purchase deleted");
    });

   
});
