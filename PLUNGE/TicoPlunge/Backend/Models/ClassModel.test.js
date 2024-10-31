const { validateClass, Class } = require("./ClassModel");

describe("Class Model Validation", () => {
  let classData, error, value;

  beforeEach(() => {
    classData = null;
    error = undefined;
    value = undefined;
  });

  const validate = (data) => {
    ({ error, value } = validateClass(data));
  };

  //Prueba de validación de clase con todos los datos correctos
  it("should pass validation with correct data", () => {
    classData = {
      date: new Date(),
      user: "123c72adf2d9f2a48c39e42e",
      service: "605c72adf2d9f2a48c39e42E",
      capacity: 10,
      students: [
        "123c72adf2d9f2a48c39e42e",
        "123c72adf2d9f2a48c39e42f",
        "123c72adf2d9f2a48c39e42a",
      ],
    };

    validate(classData);

    expect(error).toBeUndefined();
    expect(value).toEqual(classData);
  });

  //Prueba de validación de clase con campo Service vacío (obligatorio)
  it("should fail when  required fields are missing", () => {
    classData = {
      date: new Date(),
      user: "123c72adf2d9f2a48c39e42e",
      capacity: 30,
      service: '',
      students: [
        "123c72adf2d9f2a48c39e42e",
        "123c72adf2d9f2a48c39e42f",
        "123c72adf2d9f2a48c39e42a",
      ],
    };

    validate(classData);

    expect(error).toBeDefined();
    expect(error.details[0].message).toMatch(/"Service" is not allowed to be empty/);
  });

  // Prueba de validación de clase con capacidad no permitida (menor que el mínimo)
  it("should fail validation when capacity is below minimum", () => {
    classData = {
      date: new Date(),
      user: "123c72adf2d9f2a48c39e42e",
      service: "123c72adf2d9f2a48c39e42f",
      capacity: 0,
      students: ["123c72adf2d9f2a48c39e42f"],
    };

    validate(classData);

    expect(error).toBeDefined();
    expect(error.details[0].message).toMatch(/"Capacity" must be greater than or equal to 1/);
  });

  // Prueba de validación de clase con fecha inválida
  it("should fail validation when date is invalid", () => {
    classData = {
      date: new Date().toDateString,
      user: "123c72adf2d9f2a48c39e42e",
      service: "123c72adf2d9f2a48c39e42f",
      capacity: 10,
      students: ["1235c72adf2d9f2a48c39e42e"],
    };

    validate(classData);

    expect(error).toBeDefined();
    expect(error.details[0].message).toMatch(/"Date" must be a valid date/);
  });

  // Prueba de validación de clase sin estudiantes
  it("should pass validation when students list is empty", () => {
    classData = {
      date: new Date(),
      user: "123c72adf2d9f2a48c39e42e",
      service: "123c72adf2d9f2a48c39e42f",
      capacity: 10,
      students: [],
    };

    validate(classData);

    expect(error).toBeUndefined();
    expect(value).toEqual(classData);
  });

  // Prueba de validación de clase con lista de estudiantes que contiene un estudiante con Id inválido
  it("should fail validation when students list contains student with invalid Id", () => {
    classData = {
      date: new Date(),
      user: "123c72adf2d9f2a48c39e42e",
      service: "123c72adf2d9f2a48c39e42f",
      capacity: 10,
      students: ["123c72adf2d9f2a48c39e42e", "TEST"],
    };

    validate(classData);

    expect(error).toBeDefined();
    expect(error.details[0].message).toMatch(/fails to match the required pattern/);
  });

  // Prueba de validación de clase sin lista de estudiantes
  it("should pass validation when students field is omitted", () => {
    classData = {
      date: new Date(),
      user: "123c72adf2d9f2a48c39e42e",
      service: "123c72adf2d9f2a48c39e42f",
      capacity: 10,
    };

    validate(classData);

    expect(error).toBeUndefined();
    expect(value).toEqual(classData);
  });

});

