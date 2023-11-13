-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Nov 13, 2023 alle 18:22
-- Versione del server: 10.4.24-MariaDB
-- Versione PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `wine_shop`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `dettagli_ordini`
--

CREATE TABLE `dettagli_ordini` (
  `ID` int(11) NOT NULL,
  `Quantita` int(11) DEFAULT NULL,
  `CODOrdine` int(11) DEFAULT NULL,
  `CODVino` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `offerte`
--

CREATE TABLE `offerte` (
  `ID` int(11) NOT NULL,
  `Descrizione` varchar(255) DEFAULT NULL,
  `Sconto` int(11) DEFAULT NULL,
  `CODVino` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `ordini`
--

CREATE TABLE `ordini` (
  `ID` int(11) NOT NULL,
  `Totale` double DEFAULT NULL,
  `Metodo_Pagamento` varchar(20) NOT NULL,
  `CODUtente` varchar(17) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `preferiti`
--

CREATE TABLE `preferiti` (
  `ID` int(11) NOT NULL,
  `CODVino` int(11) DEFAULT NULL,
  `CODUtente` varchar(17) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `produttori`
--

CREATE TABLE `produttori` (
  `ID` int(11) NOT NULL,
  `CF` varchar(17) NOT NULL,
  `Nome` varchar(10) DEFAULT NULL,
  `Cognome` varchar(10) DEFAULT NULL,
  `Email` varchar(20) DEFAULT NULL,
  `Telefono` varchar(10) DEFAULT NULL,
  `IndAzienda` varchar(30) DEFAULT NULL,
  `Tipo` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `recensioni`
--

CREATE TABLE `recensioni` (
  `ID` int(11) NOT NULL,
  `Voto` varchar(1) NOT NULL,
  `Commento` varchar(100) DEFAULT NULL,
  `CODVino` int(11) DEFAULT NULL,
  `CODUtente` varchar(17) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `utenti`
--

CREATE TABLE `utenti` (
  `ID` int(11) NOT NULL,
  `CF` varchar(17) NOT NULL,
  `Nome` varchar(10) DEFAULT NULL,
  `Cognome` varchar(10) DEFAULT NULL,
  `Username` varchar(10) DEFAULT NULL,
  `Password` varchar(10) DEFAULT NULL,
  `Email` varchar(20) DEFAULT NULL,
  `Telefono` varchar(10) DEFAULT NULL,
  `Indirizzo` varchar(30) DEFAULT NULL,
  `Tipo` varchar(20) DEFAULT NULL,
  `Online` tinyint(1) DEFAULT 0,
  `LastLogin` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `utenti`
--

INSERT INTO `utenti` (`ID`, `CF`, `Nome`, `Cognome`, `Username`, `Password`, `Email`, `Telefono`, `Indirizzo`, `Tipo`, `Online`, `LastLogin`) VALUES
(1, 'BRTNDR01E08G337B', 'Andrea', 'Abretti', 'andre', '1111', 'ciao@gmail.com', '1520154789', 'casa', 'cliente', 1, '2023-11-13 09:48:17');

-- --------------------------------------------------------

--
-- Struttura della tabella `vini`
--

CREATE TABLE `vini` (
  `ID` int(11) NOT NULL,
  `Nome` varchar(20) DEFAULT NULL,
  `Provenienza` varchar(20) DEFAULT NULL,
  `Anno` int(11) DEFAULT NULL,
  `Descrizione` varchar(255) DEFAULT NULL,
  `Vitigno` varchar(20) DEFAULT NULL,
  `Prezzo` double DEFAULT NULL,
  `Soglia` int(11) DEFAULT NULL,
  `Quantita` int(11) DEFAULT NULL,
  `Immagine` varchar(100) DEFAULT NULL,
  `CODProduttore` varchar(17) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `dettagli_ordini`
--
ALTER TABLE `dettagli_ordini`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `CODOrdine` (`CODOrdine`),
  ADD KEY `CODVino` (`CODVino`);

--
-- Indici per le tabelle `offerte`
--
ALTER TABLE `offerte`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `CODVino` (`CODVino`);

--
-- Indici per le tabelle `ordini`
--
ALTER TABLE `ordini`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `CODUtente` (`CODUtente`);

--
-- Indici per le tabelle `preferiti`
--
ALTER TABLE `preferiti`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `CODVino` (`CODVino`),
  ADD KEY `CODUtente` (`CODUtente`);

--
-- Indici per le tabelle `produttori`
--
ALTER TABLE `produttori`
  ADD PRIMARY KEY (`ID`);

--
-- Indici per le tabelle `recensioni`
--
ALTER TABLE `recensioni`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `CODVino` (`CODVino`),
  ADD KEY `CODUtente` (`CODUtente`);

--
-- Indici per le tabelle `utenti`
--
ALTER TABLE `utenti`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Username` (`Username`);

--
-- Indici per le tabelle `vini`
--
ALTER TABLE `vini`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `CODProduttore` (`CODProduttore`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `dettagli_ordini`
--
ALTER TABLE `dettagli_ordini`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `offerte`
--
ALTER TABLE `offerte`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `ordini`
--
ALTER TABLE `ordini`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `preferiti`
--
ALTER TABLE `preferiti`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `produttori`
--
ALTER TABLE `produttori`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `recensioni`
--
ALTER TABLE `recensioni`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `utenti`
--
ALTER TABLE `utenti`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT per la tabella `vini`
--
ALTER TABLE `vini`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `dettagli_ordini`
--
ALTER TABLE `dettagli_ordini`
  ADD CONSTRAINT `dettagli_ordini_ibfk_1` FOREIGN KEY (`CODOrdine`) REFERENCES `ordini` (`ID`),
  ADD CONSTRAINT `dettagli_ordini_ibfk_2` FOREIGN KEY (`CODVino`) REFERENCES `vini` (`ID`);

--
-- Limiti per la tabella `offerte`
--
ALTER TABLE `offerte`
  ADD CONSTRAINT `offerte_ibfk_1` FOREIGN KEY (`CODVino`) REFERENCES `vini` (`ID`);

--
-- Limiti per la tabella `ordini`
--
ALTER TABLE `ordini`
  ADD CONSTRAINT `ordini_ibfk_1` FOREIGN KEY (`CODUtente`) REFERENCES `utenti` (`CF`);

--
-- Limiti per la tabella `preferiti`
--
ALTER TABLE `preferiti`
  ADD CONSTRAINT `preferiti_ibfk_1` FOREIGN KEY (`CODVino`) REFERENCES `vini` (`ID`),
  ADD CONSTRAINT `preferiti_ibfk_2` FOREIGN KEY (`CODUtente`) REFERENCES `utenti` (`CF`);

--
-- Limiti per la tabella `recensioni`
--
ALTER TABLE `recensioni`
  ADD CONSTRAINT `recensioni_ibfk_1` FOREIGN KEY (`CODVino`) REFERENCES `vini` (`ID`),
  ADD CONSTRAINT `recensioni_ibfk_2` FOREIGN KEY (`CODUtente`) REFERENCES `utenti` (`CF`);

--
-- Limiti per la tabella `vini`
--
ALTER TABLE `vini`
  ADD CONSTRAINT `vini_ibfk_1` FOREIGN KEY (`CODProduttore`) REFERENCES `produttori` (`CF`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
