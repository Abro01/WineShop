CREATE DATABASE wine_shop;
CREATE TABLE utenti(
    ID int AUTO_INCREMENT NOT null PRIMARY KEY,
    CF varchar(17),
    Nome varchar(10),
    Cognome varchar(10),
    Username varchar(10) UNIQUE,
    Password varchar(50),
    Email varchar(30),
    Telefono varchar(10),
    Indirizzo varchar(50),
    Tipo varchar(20),
    Online tinyint(1),
    LastLogin datetime
);

CREATE TABLE vini(
    ID int AUTO_INCREMENT NOT null PRIMARY KEY,
    Nome varchar(100),
    Provenienza varchar(50),
    Anno int,
    Descrizione varchar(1000),
    Vitigno varchar(50),
    Prezzo double,
    Soglia int,
    Quantita int,
    Immagine varchar(1000),
    CODProduttore int,
    FOREIGN KEY(CODProduttore) REFERENCES utenti(ID)
);

CREATE TABLE report(
    ID int AUTO_INCREMENT NOT null PRIMARY KEY,
    Descrizione varchar(500),
    Data varchar(15),
    CODAmministratore int,
    FOREIGN KEY(CODAmministratore) REFERENCES utenti(ID)
);

CREATE TABLE recensioni(
    ID int AUTO_INCREMENT NOT null PRIMARY KEY,
    Voto varchar(1),
    Commento varchar(1000),
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
    Indirizzo varchar(50),
    Metodo_Pagamento varchar(20),
	Stato varchar(20),
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
	CODCliente int,
    FOREIGN KEY(CODOrdine) REFERENCES ordini(ID),
    FOREIGN KEY(CODVino) REFERENCES vini(ID), 
	FOREIGN KEY(CODCliente) REFERENCES utenti(ID)
);

CREATE TABLE assistenze(
	ID int AUTO_INCREMENT NOT null PRIMARY KEY,
	Proposta_Acquisto varchar(400),
	CODImpiegato int,
	CODCliente int,
	FOREIGN KEY(CODImpiegato) REFERENCES utenti(ID),
	FOREIGN KEY(CODCliente) REFERENCES utenti(ID)
);

INSERT INTO vini(Nome, Provenienza, Anno, Descrizione, Vitigno, Prezzo, Soglia, Quantita, Immagine, CODProduttore)
VALUES('Barolo', 'Alpi Sud-Occidentali, Langhe', 1996, 'Il Barolo si presenta di colore rosso granato con riflessi aranciati, mentre al naso è complesso, persistente ed intenso. A note fruttate e floreali (viola e vaniglia) si accompagnano note più speziate e di goudron. In bocca è potente, equilibrato ed elegante, un vino di grande personalità.', 'Nebbiolo', 50, 10, 500, 'C:/Users/andre/OneDrive/Documenti/GitHub/WineShop/WineShop/src/main/resources/Design/Loghi/Vini/Barolo.png', 2);

INSERT INTO vini(Nome, Provenienza, Anno, Descrizione, Vitigno, Prezzo, Soglia, Quantita, Immagine, CODProduttore)
VALUES('Chianti', 'Siena, Firenze', 2016, 'Di colore rosso rubino intenso con riflessi granati, il Chianti Classico DOCG in bocca si presenta di buona tannicità, asciutto, sapido e vellutato. Speziato anche al naso, floreale, balsamico quando molto invecchiato, non nasconde profumi di frutti di bosco.', 'Sangiovese', 30, 10, 50, 'C:/Users/andre/OneDrive/Documenti/GitHub/WineShop/WineShop/src/main/resources/Design/Loghi/Vini/Chianti.png', 2);

INSERT INTO vini(Nome, Provenienza, Anno, Descrizione, Vitigno, Prezzo, Soglia, Quantita, Immagine, CODProduttore)
VALUES('Champagne Brut', 'Francia', 2002, 'Nel calice si presenta di un colore giallo dorato con una bollicina fine e persistente. Al naso note di pesca, limone, crosta di pane e fiori bianchi, a cui subentrano nuances più minerali. In bocca è vivace, fresco, snello, con un sorso fine ed equilibrato.', 'Chardonnay', 2500, 10, 100, 'C:/Users/andre/OneDrive/Documenti/GitHub/WineShop/WineShop/src/main/resources/Design/Loghi/Vini/champagne-brut-millesime-ancestrale.jpg', 2);

INSERT INTO vini(Nome, Provenienza, Anno, Descrizione, Vitigno, Prezzo, Soglia, Quantita, Immagine, CODProduttore)
VALUES('Malvasia forte', 'Romagna', 2012, 'La Malvasia bianca di Candia è un vitigno a frutto bianco, con un leggero aroma del moscato dà origine a un vino frizzante secco, morbido e fresco al palato, di buona struttura generale. colore: paglierino o paglierino scarico. odore: aroma caratteristico anche intenso.', 'Malvasia', 10, 5, 100, 'C:/Users/andre/OneDrive/Documenti/GitHub/WineShop/WineShop/src/main/resources/Design/Loghi/Vini/malvasia-forte.jpg', 2);

INSERT INTO vini(Nome, Provenienza, Anno, Descrizione, Vitigno, Prezzo, Soglia, Quantita, Immagine, CODProduttore)
VALUES('Dom Perignon', 'Sainte-Menehould (regione francese)', 2022, 'Le uve che si assemblano per produrre il Dom Pérignon, esclusivamente Pinot nero e Chardonnay, provengono prevalentemente da soli vigneti Gran Cru. Queste uve, come abbiamo già sottolineato, sono accuratamente selezionate e sono il frutto delle vendemmie migliori.', '55% da Chardonnay e al 45% da Pinot Noir', 500, 10, 500, 'C:/Users/andre/OneDrive/Documenti/GitHub/WineShop/WineShop/src/main/resources/Design/Loghi/Vini/Dom_Perignon.jpg', 2);

INSERT INTO vini(Nome, Provenienza, Anno, Descrizione, Vitigno, Prezzo, Soglia, Quantita, Immagine, CODProduttore)
VALUES('Brunello di Montalcino', 'Montalcino, Toscana', 1992, 'Il Brunello di Montalcino è un vino di colore rosso rubino intenso. Visivamente è limpido, brillante, di colore granato vivace. Ha profumo intenso, persistente, ampio ed etereo.', 'Sangiovese', 75, 10, 100, 'C:/Users/andre/OneDrive/Documenti/GitHub/WineShop/WineShop/src/main/resources/Design/Loghi/Vini/Brunello_Montalcino.png', 3);

INSERT INTO vini(Nome, Provenienza, Anno, Descrizione, Vitigno, Prezzo, Soglia, Quantita, Immagine, CODProduttore)
VALUES('Pinot Grigio', 'Principalmente coltivato in varie zone del Friuli', 2002, 'Vino bianco leggero e fresco con aromi di agrumi e frutta a polpa bianca. È noto per la sua acidità vivace.', 'Pinot Grigio', 20, 5, 100, 'C:/Users/andre/OneDrive/Documenti/GitHub/WineShop/WineShop/src/main/resources/Design/Loghi/Vini/pinot_grigio.png', 3); 

INSERT INTO vini(Nome, Provenienza, Anno, Descrizione, Vitigno, Prezzo, Soglia, Quantita, Immagine, CODProduttore)
VALUES('Vermentino', 'Sardegna', 2005, 'Colore: dal bianco carta al giallo paglierino tenue, con riflessi verdolini, brillante. odore: profumo caratteristico delicato e gradevole. sapore: secco, amabile, sapido, fresco, acidulo con leggero retrogusto amarognolo.', 'Vermentino di Gallura', 25, 10, 100, 'C:/Users/andre/OneDrive/Documenti/GitHub/WineShop/WineShop/src/main/resources/Design/Loghi/Vini/vermentino-di-sardegna.jpg', 3);

INSERT INTO vini(Nome, Provenienza, Anno, Descrizione, Vitigno, Prezzo, Soglia, Quantita, Immagine, CODProduttore)
VALUES('Etna Rosso', 'Castiglione, Sicilia', 1980, 'Al naso è fruttato, di rosolio, spezie eleganti e minerale. Al palato è succoso, equilibrato e morbido.', 'Contrada Pietrrizzo', 30, 10, 100, 'C:/Users/andre/OneDrive/Documenti/GitHub/WineShop/WineShop/src/main/resources/Design/Loghi/Vini/EtnaRosso.png', 3);

INSERT INTO vini(Nome, Provenienza, Anno, Descrizione, Vitigno, Prezzo, Soglia, Quantita, Immagine, CODProduttore)
VALUES('Massetino', 'Bolgheri, Toscana', 2020, 'Complessa e generosa la gamma olfattiva nei suoi profumi di frutta rossa matura e spezie dolci, arricchiti da ricordi di liquirizia e tabacco e da spunti leggermente vegetali.', 'Masseto', 700, 10, 100, 'C:/Users/andre/OneDrive/Documenti/GitHub/WineShop/WineShop/src/main/resources/Design/Loghi/Vini/massetino.jpg', 3);

INSERT INTO vini(Nome, Provenienza, Anno, Descrizione, Vitigno, Prezzo, Soglia, Quantita, Immagine, CODProduttore)
VALUES('Pinot Noir', 'Francia', 1950, 'Il vitigno Pinot nero, vinificato in rosso, origina vini poco ricchi in colore, trasparenti, poco tannici e di spiccata acidità, caratterizzati da sfumature fruttate e floreali', 'Borgogna', 3000, 10, 100, 'C:/Users/andre/OneDrive/Documenti/GitHub/WineShop/WineShop/src/main/resources/Design/Loghi/Vini/pinot_noir.jpg', 4);

INSERT INTO vini(Nome, Provenienza, Anno, Descrizione, Vitigno, Prezzo, Soglia, Quantita, Immagine, CODProduttore)
VALUES('Taurasi DOCG', 'Irpinia, Avellino', 2008, 'Il Taurasi DOCG è uno dei più rinomati vini rossi italiani, ed è caratterizzato da una struttura molto importante grazie a cui riesce a invecchiare bene anche per molti anni, guadagnando col tempo complessità ed eleganza.', 'Aglianico', 300, 10, 100, 'C:/Users/andre/OneDrive/Documenti/GitHub/WineShop/WineShop/src/main/resources/Design/Loghi/Vini/taurasi-riserva-radici.jpg', 4);

INSERT INTO vini(Nome, Provenienza, Anno, Descrizione, Vitigno, Prezzo, Soglia, Quantita, Immagine, CODProduttore)
VALUES('Villamagna', 'Montepulciano', 2023, 'Il colore è rosso rubino intenso. Il profumo è persistente con sentori di frutta matura, confettura, note di cannella e vaniglia. Al palato è equilibrato, armonico e piacevolmente tannico.', 'Cascina del Colle', 15, 10, 100, 'C:/Users/andre/OneDrive/Documenti/GitHub/WineShop/WineShop/src/main/resources/Design/Loghi/Vini/villamagna-riserva.jpg', 4);

INSERT INTO vini(Nome, Provenienza, Anno, Descrizione, Vitigno, Prezzo, Soglia, Quantita, Immagine, CODProduttore)
VALUES('Valpolicella', 'Provincia di Verona', 2006, 'Caratteristico, ciliegia, delicato, gradevole, mandorla amara, rosa, sottile, vinoso. Amarognolo, armonico, di corpo, secco, fresco, sapido, piacevolmente tannico, vellutato, vivace.', 'Corvina', 180, 10, 100, 'C:/Users/andre/OneDrive/Documenti/GitHub/WineShop/WineShop/src/main/resources/Design/Loghi/Vini/valpolicella-superiore-mythas-doc-bio.jpg', 4);

INSERT INTO vini(Nome, Provenienza, Anno, Descrizione, Vitigno, Prezzo, Soglia, Quantita, Immagine, CODProduttore)
VALUES('Nero d Avola', 'Eloro, Siracusa', 2020, 'Il vino nero d Avola si distingue per precise caratteristiche: innanzitutto per il colore, che è rosso rubino con riflessi granati. Al palato, invece, se ne percepisce un aroma fruttato (sa di amarena, prugne) e speziato.', 'Nero d Avola', 25, 10, 100, 'C:/Users/andre/OneDrive/Documenti/GitHub/WineShop/WineShop/src/main/resources/Design/Loghi/Vini/nero_davola.jpg', 4);





