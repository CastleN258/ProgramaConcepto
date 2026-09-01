
-- Base de Datos 1 - Prueba de Concepto
-- Script de creación: tabla, datos y procedimientos almacenados


-- 1. Crear la tabla Empleado
CREATE TABLE dbo.Empleado
(
    id INT IDENTITY (1, 1) PRIMARY KEY
    , Nombre VARCHAR(128) NOT NULL
    , Salario MONEY NOT NULL
);
GO

-- 2. Cargar las 40 filas de datos de prueba del trabajo 
INSERT INTO dbo.Empleado (Nombre, Salario) VALUES
('Juan Perez', 200000.00),
('Ana Rojas', 250000.00),
('Luis Chaves', 180000.00),
('Maria Gonzalez', 300000.00),
('Carlos Mora', 220000.50),
('Sofia Vargas', 275000.00),
('Diego Solano', 195000.00),
('Laura Jimenez', 310000.00),
('Pedro Castro', 205000.00),
('Andrea Fonseca', 240000.00),
('Jose Ramirez', 190000.00),
('Gabriela Salas', 265000.00),
('Miguel Alvarado', 210000.00),
('Valeria Brenes', 285000.00),
('Roberto Quiros', 230000.00),
('Camila Herrera', 255000.00),
('Fernando Vega', 200000.00),
('Natalia Cordero', 245000.00),
('Alejandro Rojas', 215000.00),
('Paola Mendez', 260000.00),
('Ricardo Duarte', 225000.00),
('Daniela Araya', 270000.00),
('Esteban Sequeira', 185000.00),
('Monica Villalobos', 295000.00),
('Kevin Barrantes', 200000.00),
('Karla Zuniga', 250000.00),
('Marco Chinchilla', 235000.00),
('Silvia Navarro', 280000.00),
('Oscar Elizondo', 195000.00),
('Adriana Campos', 305000.00),
('Manuel Fallas', 210000.00),
('Priscilla Mora', 245000.00),
('Rodrigo Ureña', 220000.00),
('Wendy Chacon', 265000.00),
('Victor Rodriguez', 200000.00),
('Ivannia Solis', 275000.00),
('Gustavo Aguilar', 190000.00),
('Tatiana Segura', 260000.00),
('Anthony Blanco', 215000.00),
('Melissa Ortiz', 290000.00);
GO

-- 3. Procedimiento almacenado: listar empleados ordenados por nombre
CREATE PROCEDURE dbo.sp_ListarEmpleados
AS
BEGIN
    SET NOCOUNT ON;
    SELECT id, Nombre, Salario
    FROM dbo.Empleado
    ORDER BY Nombre ASC;
END;
GO

-- 4. Procedimiento almacenado: insertar empleado con validación de nombre duplicado
CREATE PROCEDURE dbo.sp_InsertarEmpleado
    @Nombre VARCHAR(128),
    @Salario MONEY,
    @CodigoResultado INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;

    IF EXISTS (SELECT 1 FROM dbo.Empleado WHERE Nombre = @Nombre)
    BEGIN
        SET @CodigoResultado = -1; -- Error porque el nombre ya existe
        RETURN;
    END

    INSERT INTO dbo.Empleado (Nombre, Salario)
    VALUES (@Nombre, @Salario);

    SET @CodigoResultado = 0; -- nuevo empleado insertado 
END;
GO
