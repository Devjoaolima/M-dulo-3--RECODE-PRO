CREATE DATABASE AgenciaViagem;

USE AgenciaViagem;

CREATE TABLE Cliente (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Cpf VARCHAR(20) UNIQUE,
    Idade INT,
    Email NVARCHAR(255),
    Nome NVARCHAR(255),
    Telefone INT
);

CREATE TABLE Viagem (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Preco FLOAT,
    Taxas FLOAT,
    Destino NVARCHAR(255)
);

CREATE TABLE Passagem (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Viagemid INT,
    Usuarioid INT,
    FOREIGN KEY (Usuarioid) REFERENCES Cliente(Id),
    FOREIGN KEY (Viagemid) REFERENCES Viagem(Id)
);

-- INSERIR EM DESTINO
INSERT INTO Viagem (Destino, Preco, Taxas) VALUES ('RECIFE', 500.00,  250);
INSERT INTO Viagem (Destino, Preco, Taxas) VALUES ('PORTO DE GALINHAS', 650.00, 300);
INSERT INTO Viagem (Destino, Preco, Taxas) VALUES ('SALVADOR', 700.00, 320);
INSERT INTO Viagem (Destino, Preco, Taxas) VALUES ('RIO DE JANEIRO', 889.00, 155);
INSERT INTO Viagem (Destino, Preco, Taxas) VALUES ('SÃO PAULO', 729.00, 189);
INSERT INTO Viagem (Destino, Preco, Taxas) VALUES ('LONDRES', 1250.00, 50);
