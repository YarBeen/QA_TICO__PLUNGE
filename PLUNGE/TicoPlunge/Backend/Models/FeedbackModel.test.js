const { validateFeedback, Feedback } = require("./FeedbackModel");

describe("Feedback Model Validation", () => {
  let feedbackData, error, value;

  beforeEach(() => {
    feedbackData = null;
    error = undefined;
    value = undefined;
  });

  const validate = (data) => {
    ({ error, value } = validateFeedback(data));
  };

  // Prueba de validación de feedback con todos los datos correctos
  it("should pass validation with correct data", () => {
    feedbackData = {
      comentario: "Excelente servicio",
      rating: 5,
      user: "123c72adf2d9f2a48c39e42e",
    };

    validate(feedbackData);

    expect(error).toBeUndefined();
    expect(value).toEqual(feedbackData);
  });

  // Prueba de validación de feedback con campo obligatorio faltante (Comentario)
  it("should fail validation when missing required fields", () => {
    feedbackData = {
      rating: 4,
      user: "123c72adf2d9f2a48c39e42e",
    };

    validate(feedbackData);

    expect(error).toBeDefined();
    expect(error.details[0].message).toMatch(/"Comentario" is required/);
  });

  // Prueba de validación de feedback con rating fuera del rango permitido (menos que el mínimo)
  it("should fail validation when rating is below minimum", () => {
    feedbackData = {
      comentario: "Mal servicio",
      rating: 0,
      user: "123c72adf2d9f2a48c39e42e",
    };

    validate(feedbackData);

    expect(error).toBeDefined();
    expect(error.details[0].message).toMatch(/"Rating" must be greater than or equal to 1/);
  });

  // Prueba de validación de feedback con rating más que lo permitido
  it("should fail validation when rating is more than maximum", () => {
    feedbackData = {
      comentario: "Excelente",
      rating: 6,
      user: "123c72adf2d9f2a48c39e42e",
    };

    validate(feedbackData);

    expect(error).toBeDefined();
    expect(error.details[0].message).toMatch(/"Rating" must be less than or equal to 5/);
  });

  //Prueba de validación de feedback con comentario vacío
  it("should fail validation when comentario is empty", () => {
    feedbackData = {
      comentario: "",
      rating: 3,
      user: "123c72adf2d9f2a48c39e42e",
    };
  
    validate(feedbackData);
  
    expect(error).toBeDefined();
    expect(error.details[0].message).toMatch(/"Comentario" is not allowed to be empty/);
  });
  
  // Prueba de validación de feedback con rating no entero
  it("should fail validation when rating is not an integer", () => {
    feedbackData = {
      comentario: "Servicio",
      rating: 2.5, 
      user: "123c72adf2d9f2a48c39e42e",
    };
  
    validate(feedbackData);
  
    expect(error).toBeDefined();
    expect(error.details[0].message).toMatch(/"Rating" must be an integer/);
  });
  
});
