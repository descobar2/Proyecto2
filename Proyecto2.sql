CREATE DATABASE FABRICA;
USE FABRICA;

CREATE TABLE Tipo (
    TipoID INT AUTO_INCREMENT PRIMARY KEY,
    Descripcion VARCHAR(255)
);

CREATE TABLE Persona (
    PersonaID INT AUTO_INCREMENT PRIMARY KEY,
    TipoID INT NOT NULL,
    Nombre VARCHAR(255) NOT NULL,
    Nit VARCHAR(20) NOT NULL,
    FOREIGN KEY (TipoID) REFERENCES Tipo(TipoID)
);

CREATE TABLE Material (
    MaterialID INT AUTO_INCREMENT PRIMARY KEY,
    NombreMat VARCHAR(255) NOT NULL,
    Medida VARCHAR(50) NOT NULL,
    PrecioMat DECIMAL(10, 2) NOT NULL,
    CantDisp INT NOT NULL
);

CREATE TABLE ListMaterial (
    ListMaterialID INT AUTO_INCREMENT PRIMARY KEY,
    MaterialID INT NOT NULL,
    Cantidad INT NOT NULL,
    Precio DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (MaterialID) REFERENCES Material(MaterialID)
);

CREATE TABLE Documento (
    DocumentoID INT AUTO_INCREMENT PRIMARY KEY,
    ProveedorID INT,
    ClienteID INT,
    ProductoID INT,
    CantPro INT NOT NULL,
    TipoID INT NOT NULL,
    Estado VARCHAR(50) NOT NULL,
    FOREIGN KEY (ProductoID) REFERENCES Producto(ProductoID),
    FOREIGN KEY (TipoID) REFERENCES Tipo(TipoID),
    FOREIGN KEY (ProveedorID) REFERENCES Persona(PersonaID),
    FOREIGN KEY (ClienteID) REFERENCES Persona(PersonaID)
);

CREATE TABLE DetalleDocumento (
    DetalleDocumentoID INT AUTO_INCREMENT PRIMARY KEY,
    DocumentoID INT NOT NULL,
    MaterialID INT NOT NULL,
    Cantidad INT NOT NULL,
    Precio DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (DocumentoID) REFERENCES Documento(DocumentoID),
    FOREIGN KEY (MaterialID) REFERENCES Material(MaterialID)
);

CREATE TABLE Producto (
    ProductoID INT AUTO_INCREMENT PRIMARY KEY,
    NombrePro VARCHAR(255) NOT NULL,
    PrecioProd DECIMAL(10, 2) NOT NULL
);

CREATE TABLE ProductoMaterial (
    ProductoMaterialID INT AUTO_INCREMENT PRIMARY KEY,
    ProductoID INT NOT NULL,
    MaterialID INT NOT NULL,
    Cantidad INT NOT NULL);
);
DROP TABLE Documento;
