const {
  validatePrivateFeedback,
  PrivateFeedback,
} = require("./PrivateFeedBack");
const mongoose = require("mongoose");
describe("Private Feedback Model Validation", () => {
  it("should pass validation with correct data", () => {
    const validFeedbackData = {
      comentario: "This is a private feedback comment.",
      user: new mongoose.Types.ObjectId().toString(),
    };

    const { error, value } = validatePrivateFeedback(validFeedbackData);

    expect(error).toBeUndefined();
    expect(value).toEqual(validFeedbackData);
  });

  it("should fail without an id", () => {
    const validFeedbackData = {
      comentario: "This is a private feedback comment.",
      user: "",
    };

    const { error, value } = validatePrivateFeedback(validFeedbackData);

    expect(error).toBeDefined();
    expect(value).toEqual(validFeedbackData);
  });

  it("should fail if comment is empty", () => {
    const validFeedbackData = {
      comentario: "",
      user: new mongoose.Types.ObjectId().toString(),
    };

    const { error, value } = validatePrivateFeedback(validFeedbackData);

    expect(error).toBeDefined();
    expect(value).toEqual(validFeedbackData);
  });
  it("should fail if comment is of incorrect type", () => {
    const validFeedbackData = {
      comentario: 12,
      user: new mongoose.Types.ObjectId().toString(),
    };

    const { error, value } = validatePrivateFeedback(validFeedbackData);

    expect(error).toBeDefined();
    expect(value).toEqual(validFeedbackData);
  });
});
