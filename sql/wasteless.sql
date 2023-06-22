create database wasteless;
use wasteless;
create table utenti
(
	id int primary key auto_increment,
    nome varchar(100),
    cognome varchar(100),
    email varchar(100),
    password varchar(100),
    ruolo varchar(100)
);

create table prodotti (
	id int primary key auto_increment, 
    nome varchar(100), 
    dataScadenza date, 
    quantita int, 
    descrizione varchar (500), 
    prezzo float,
    alimento boolean,
    stato varchar(100),
    giorni int,
    prezzoVendita float,
    idUtente int,
    foreign key ( idUtente)
	references utenti(id)
    on delete cascade
);