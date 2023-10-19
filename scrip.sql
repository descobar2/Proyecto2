
CREATE DATABASE FABRICA;
USE FABRICA;
CREATE TABLE Persona (
    PersonaID INT PRIMARY KEY AUTO_INCREMENT,
    Tipo VARCHAR(1), NOT NULL,
    Nombre VARCHAR(255) NOT NULL,
    Nit VARCHAR(10) NOT NULL
);
CREATE TABLE Producto(
    ProductoID INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(255), NOT NULL,
    Cantidad INT
    Precio DECIMAL(10, 2) NOT NULL,
    Disponible INT
);
CREATE TABLE Compra(
    CompraID INT PRIMARY KEY AUTO_INCREMENT,
    Material VARCHAR(255), NOT NULL,
    Cantidad INT
    Precio DECIMAL(10, 2) NOT NULL,
    Disponible INT
);
CREATE TABLE Cliente (
    ClienteID INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(255) NOT NULL,
    Direccion VARCHAR(255),
    Telefono VARCHAR(15)
);

CREATE TABLE Pedido (
    PedidoID INT PRIMARY KEY AUTO_INCREMENT,
    FechaPedido DATE,
    ClienteID INT,
    FOREIGN KEY (ClienteID) REFERENCES Cliente(ClienteID)
);

CREATE TABLE DetallePedido (
    PedidoID INT,
    MuebleID INT,
    Cantidad INT,
    PRIMARY KEY (PedidoID, MuebleID),
    FOREIGN KEY (PedidoID) REFERENCES Pedido(PedidoID),
    FOREIGN KEY (MuebleID) REFERENCES Mueble(MuebleID)
);

CREATE TABLE Compra (
    CompraID INT PRIMARY KEY AUTO_INCREMENT,
    FechaCompra DATE,
    ProveedorID INT,
    FOREIGN KEY (ProveedorID) REFERENCES Proveedor(ProveedorID)
);

CREATE TABLE DetalleCompra (
    CompraID INT,
    MuebleID INT,
    Cantidad INT,
    PrecioCompra DECIMAL(10, 2),
    PRIMARY KEY (CompraID, MuebleID),
    FOREIGN KEY (CompraID) REFERENCES Compra(CompraID),
    FOREIGN KEY (MuebleID) REFERENCES Mueble(MuebleID)
);
/*
INSERT INTO Proveedor (ProveedorID, Nombre, Direccion, Telefono)
VALUES (4, 'Roberto', 'Ciudad', '24280000');

UPDATE Proveedor SET Nombre = ? WHERE ProveedorID = ?
VALUES (4,'Guatemala');
*/