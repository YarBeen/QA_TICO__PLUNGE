const PurchaseHistory = require("../Models/PurchaseHistoryModel"); 
const mongoose = require("mongoose");

describe("PurchaseHistory Model Validation", () => {
  
    it("should validate successfully with correct data", () => {
        const validPurchaseHistoryData = {
            buyerName: "Paulo Londra",
            totalAmount: 100.50,
            detail: "Detail",
            purchaseDate: new Date(),
        };

        const purchaseHistory = new PurchaseHistory(validPurchaseHistoryData);
        const error = purchaseHistory.validateSync(); 

        expect(error).toBeUndefined(); 
    });

    it("should return an error for invalid data", () => {
        const invalidPurchaseHistoryData = {
            totalAmount: 100.50,
            detail: "Detail",
            purchaseDate: new Date(),
        };

        const purchaseHistory = new PurchaseHistory(invalidPurchaseHistoryData);
        const error = purchaseHistory.validateSync(); 

        expect(error).toBeDefined(); 
    });

});
