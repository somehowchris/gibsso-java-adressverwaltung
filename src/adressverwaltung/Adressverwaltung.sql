drop database if exists Adressverwaltung;

create database Adressverwaltung;

use Adressverwaltung;

Create table Places (
    `oid` int(8) AUTO_INCREMENT,
    `name` varchar(255),
    `plz` int(8),
    Primary Key (`oid`)
);

create table Adresses (
    `pid` int(8) AUTO_INCREMENT,
    `name` varchar(255),
    `vorname` varchar(255),
    `strasse`  varchar(255),
    `oid` int(8),
    `telefon` varchar(255),
    `handy` varchar(255),
    `email` varchar(255),
    Primary Key(`pid`),
    CONSTRAINT FK_Ort FOREIGN KEY (`oid`) REFERENCES Places(`oid`)
);

insert into Adresses ( name, vorname, strasse, telefon, handy, email) Values ('Nicola','Temporal', 'Rankenweg 5', '', '0786698389' ,'nicolatemporal@gawnet.ch');


