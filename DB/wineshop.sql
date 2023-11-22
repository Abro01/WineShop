CREATE TABLE utenti(
    ID int AUTO_INCREMENT NOT null PRIMARY KEY,
    CF varchar(17),
    Nome varchar(10),
    Cognome varchar(10),
    Username varchar(10) UNIQUE,
    Password varchar(10),
    Email varchar(20),
    Telefono varchar(10),
    Indirizzo varchar(30),
    Tipo varchar(20),
    Online tinyint(1),
    LastLogin datetime
);

CREATE TABLE vini(
    ID int AUTO_INCREMENT NOT null PRIMARY KEY,
    Nome varchar(20),
    Provenienza varchar(10),
    Anno int,
    Descrizione varchar(255),
    Vitigno varchar(20),
    Prezzo double,
    Soglia int,
    Quantita int,
    Immagine varchar(100),
    CODProduttore int,
    FOREIGN KEY(CODProduttore) REFERENCES utenti(ID)
);

CREATE TABLE report(
    ID int AUTO_INCREMENT NOT null PRIMARY KEY,
    Descrizione varchar(255),
    Data date,
    CODAmministratore int,
    FOREIGN KEY(CODAmministratore) REFERENCES utenti(ID)
);

CREATE TABLE recensioni(
    ID int AUTO_INCREMENT NOT null PRIMARY KEY,
    Voto varchar(1),
    Commento varchar(100),
    CODVino int,
    CODCliente int,
    FOREIGN KEY(CODVino) REFERENCES vini(ID),
    FOREIGN KEY(CODCliente) REFERENCES utenti(ID)
);

CREATE TABLE preferiti(
    ID int AUTO_INCREMENT NOT null PRIMARY KEY,
    CODVino int,
    CODCliente int,
    FOREIGN KEY(CODVino) REFERENCES vini(ID),
    FOREIGN KEY(CODCliente) REFERENCES utenti(ID)
);

CREATE TABLE ordini(
    ID int AUTO_INCREMENT NOT null PRIMARY KEY,
    Totale double,
    Indirizzo varchar(30),
    Metodo_Pagamento varchar(20),
    CODCliente int,
    FOREIGN KEY(CODCliente) REFERENCES utenti(ID)
);

CREATE TABLE offerte(
    ID int AUTO_INCREMENT NOT null PRIMARY KEY,
    Descrizione varchar(255),
    Sconto int,
    CODVino int,
    FOREIGN KEY(CODVino) REFERENCES vini(ID)
);

CREATE TABLE dettagli_ordini(
    ID int AUTO_INCREMENT NOT null PRIMARY KEY,
    Quantita int,
    CODVino int,
    CODOrdine int,
    FOREIGN KEY(CODOrdine) REFERENCES ordini(ID),
    FOREIGN KEY(CODVino) REFERENCES vini(ID)
);