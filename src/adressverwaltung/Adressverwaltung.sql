create database Adressverwaltung;

use Adressverwaltung;

Create table Places (
    `oid` int(8),
    `name` varchar(255),
    `plz` int(8),
    Primary Key (`oid`)
);

create table Adresses (
    `id` int(8),
    `name` varchar(255),
    `vorname` varchar(255),
    `strasse`  varchar(255),
    `oid` int(8),
    `telefon` varchar(255),
    `handy` varchar(255),
    `email` varchar(255),
    Primary Key(`id`),
    CONSTRAINT FK_Ort FOREIGN KEY (`oid`) REFERENCES Places(`oid`)
);


