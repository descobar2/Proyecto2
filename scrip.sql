CREATE DATABASE FabricaMuebles;
USE FabricaMuebles;

CREATE TABLE Proveedor (
    ProveedorID INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(255) NOT NULL,
    Direccion VARCHAR(255),
    Telefono VARCHAR(15)
);

CREATE TABLE Mueble (
    MuebleID INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(255) NOT NULL,
    PrecioVenta DECIMAL(10, 2) NOT NULL,
    CantidadDisponible INT
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
