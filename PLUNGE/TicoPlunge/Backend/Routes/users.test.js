const request = require("supertest");
const mongoose = require("mongoose");
const express = require("express");
const userRouter = require("./users"); 
require("dotenv").config();
const { User } = require("../Models/User");

const app = express();
app.use(express.json());
app.use("/api/users", userRouter);

beforeAll(async () => {
    const connectionParams = {
        useNewUrlParser: true,
        useUnifiedTopology: true,
    };
    await mongoose.connect(process.env.DB, connectionParams);
});

afterAll(async () => {
    await mongoose.connection.close();
});

describe("POST /api/users", () => {
    it("should create a new user", async () => {
        const newUser = {
            firstName: "Testo12",
            lastName: "User213",
            email: "testuserrwqer13@example.com",
            password: "ASDF1234abc!", 
            role: "Client",
        };

        const response = await request(app).post("/api/users").send(newUser);
        expect(response.status).toBe(201);
        expect(response.body).toHaveProperty("message", "User created successfully");

        await User.deleteOne({ email: newUser.email });

    });

    it("should return 409 if user with email already exists", async () => {
        const existingUser = {
            firstName: "Test",
            lastName: "User",
            email: "testuser@example.com",
            password: "ASDF1234abc!",
            role: "Client",
        };

       await request(app).post("/api/users").send(existingUser);
        const response = await request(app).post("/api/users").send(existingUser);
        expect(response.status).toBe(409);
        expect(response.body).toHaveProperty("message", "User with given email already Exist!");
    });
});


describe("GET /api/users", () => {
    it("should return all users", async () => {
        const response = await request(app).get("/api/users");
        expect(response.status).toBe(200);
        expect(Array.isArray(response.body)).toBe(true);
    });

    it("should return only clients", async () => {
        const response = await request(app).get("/api/users/getClients");
        expect(response.status).toBe(200);
        expect(Array.isArray(response.body)).toBe(true);
        response.body.forEach(user => expect(user.role).toBe("Client"));
    });
});


describe("DELETE /api/users/id", () => {
  it("should delete an user ", async () => {
      const user = new User({
          firstName: "BORRATEEEEEE",
          lastName: "BORRATEEEEEE",
          email: "BORRATEEEEEE@BORRATEEEEEE.BORRATEEEEEE",
          password: "BORRATEEEEEE1223!",
          role: "Client",
      });
      const userId = (await user.save())._id;

      const response = await request(app).delete(`/api/users/${userId}`);
      expect(response.status).toBe(200);
      expect(response.body).toHaveProperty("message", "Usuario eliminado exitosamente.");

      const userInDb = await User.findById(userId);
      expect(userInDb).toBeNull();
  });
});

describe("PUT /api/users", () => {
  it("should update an user ", async () => {
      const user = new User({
          firstName: "Update",
          lastName: "User",
          email: "updatetest@example.com",
          password: "UpdatePassword123!",
          role: "Client",
      });
      const savedUser = await user.save();
      const userId = savedUser._id;

      const updatedUser = {
          _id: userId,
          firstName: "Update2",
          lastName: "User2",
          email: "updatetest@example.com",
          password: "UpdatPassword123!",
          role: "Client",
      };

      const response = await request(app).put("/api/users").send(updatedUser);
      expect(response.status).toBe(200);
      expect(response.body).toHaveProperty("message", "User updated successfully");

      const userInDb = await User.findById(userId);
      expect(userInDb).not.toBeNull();
      expect(userInDb.firstName).toBe(updatedUser.firstName);
      expect(userInDb.lastName).toBe(updatedUser.lastName);

      await User.deleteOne({ _id: userId });
  });
});
