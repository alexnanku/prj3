create database if not exists esqui;

use esqui;

create table monitor(
	dni_monitor varchar(9) not null,
    nom varchar(20) not null,
    cognom varchar(50) not null,
    sexe varchar(50),
    primary key (dni_monitor)
);

create table curs(
	id_curs int(4) not null,
	dni_monitor varchar(9) not null,
	nom varchar(20),
	descripcio varchar(100),
	data_inici datetime,
    preu_hora float,
    primary key (id_curs),
    foreign key (dni_monitor) references monitor(dni_monitor)
);
   
create table curs_individual(
	id_curs int(4) not null,
    primary key (id_curs),
    foreign key (id_curs) references curs(id_curs)
);

create table curs_colectiu(
	id_curs int(4) not null,
    aforament int(9),
    preu float, 
    primary key (id_curs),
    foreign key (id_curs) references curs(id_curs)
);

create table curs_competitiu(
	id_curs int(4) not null,
    preu_comp float,
    nivell varchar(50),
    primary key (id_curs),
    foreign key (id_curs) references curs(id_curs)
);

create table client(
	dni_usuari varchar(9) not null,
	nom varchar(20) not null,
    cognom varchar(50) not null,
    cognom2 varchar(50),
    data_naix DATE,
    usuari varchar (16) not null,
    contrasenya varchar(50) not null,
    sexe varchar(4),
    email varchar(50),
    primary key (dni_usuari)
);
    
create table client_individual(
	dni_usuari varchar(9) not null,
	primary key (dni_usuari),
	foreign key (dni_usuari) references client(dni_usuari)
);

create table client_colectiu(
	dni_usuari varchar(9) not null,
	num_familiar int(12) unique AUTO_INCREMENT,
	primary key (dni_usuari),
	foreign key (dni_usuari) references client(dni_usuari)
);

create table client_federat(
	dni_usuari varchar(9) not null,
	num_federacio int(12),
	primary key (dni_usuari),
	foreign key (dni_usuari) references client(dni_usuari)
);

create table lloguerCurs_individual(
	id_curs int(4) not null,
	dni_usuari varchar(9) not null,
	data_lloguer datetime,
	preu float,
	primary key (id_curs),
    foreign key (id_curs) references curs_individual(id_curs),
    foreign key (dni_usuari) references client_individual(dni_usuari)
);

create table lloguerCurs_colectiu(
	id_curs int(4) not null,
	dni_usuari varchar(9) not null,
	data_lloguer datetime,
	preu float,
	max_clients int(2),
	primary key (id_curs),
    foreign key (id_curs) references curs_colectiu(id_curs),
    foreign key (dni_usuari) references client_colectiu(dni_usuari)
);

create table lloguerCurs_competitiu(
	id_curs int(4) not null,
	dni_usuari varchar(9) not null,
	data_lloguer datetime,
	preu float,
	nivell varchar(12),
	primary key (id_curs),
    foreign key (id_curs) references curs_competitiu(id_curs),
    foreign key (dni_usuari) references client_federat(dni_usuari)
);

create table optar_inv(
	id_curs int,
    dni_usuari varchar(9),
    foreign key (dni_usuari) references client_colectiu(dni_usuari),
    foreign key (id_curs) references curs_individual(id_curs),
    primary key (id_curs)
);

create table optar_col(
	id_curs int,
    dni_usuari varchar(9),
    foreign key (dni_usuari) references client_individual(dni_usuari),
    foreign key (id_curs) references curs_colectiu(id_curs),
    primary key (id_curs)
);

create table producte(
	id_producte int(3) AUTO_INCREMENT not null,
	model varchar(50),
	marca varchar(50),
	preu float,
    img varchar(50),
    num_usos int,
	primary key (id_producte)
);

create table lloguer_producte(
	id_producte int(3) not null,
	dni_usuari varchar(9) not null,
    foreign key (dni_usuari) references client(dni_usuari),
    primary key (id_producte),
    foreign key (id_producte) references producte(id_producte)
);

create table kit(
	id_kit int (3) not null,
	preu float,
	data_lloguer date,
	primary key (id_kit)
);

create table lloguer_kit(
	id_kit int(3) not null,
	dni_usuari varchar(9) not null,
    foreign key (dni_usuari) references client(dni_usuari),
    primary key (id_kit),
    foreign key (id_kit) references kit(id_kit)
);

create table forma(
	id_kit int(3) not null,
	id_producte int(3) not null,
	data_lloguer date,
	primary key (id_kit),
    foreign key (id_kit) references kit(id_kit),
    foreign key (id_producte) references producte(id_producte)
);

create table esqui(
	id_producte int(3) not null,
	mida int (4),
	primary key (id_producte),
    foreign key (id_producte) references producte(id_producte)
);

create table botes(
	id_producte int(3) not null,
	talla int (4),
	primary key (id_producte),
    foreign key (id_producte) references producte(id_producte)
);

create table pals(
	id_producte int(3) not null,
	altura int (4),
	primary key (id_producte),
    foreign key (id_producte) references producte(id_producte)
);

create table prod_elim(
	id_producte int,
    dni_usuari varchar(9),
     foreign key (dni_usuari) references client(dni_usuari),
    primary key (id_producte),
    foreign key (id_producte) references producte(id_producte)
);

insert into producte (model, marca, preu, img, num_usos) VALUES 
('Atris','BLACK CROWS','669','img/1.png', '0'),
('Duos Freebird','BLACK CROWS','120','img/2.png', '0'),
('Pro 100 BLACK BELLUGA RED','SALOMON','275','img/3.png', '0');

insert into esqui (id_producte, mida) VALUES 
('1','184');

insert into pals (id_producte, altura) VALUES 
('2','140');

insert into botes (id_producte, talla) VALUES 
('3','41');

insert into client (dni_usuari, nom, cognom, cognom2, data_naix, usuari, contrasenya, sexe, email) VALUES 
('X5522891F', 'Alex', 'Rubtsov', '', '1996-03-02', 'arubtsov', MD5('Fat/3232'), 'home', 'arubtsov@gmail.com'),
('55522891E', 'Eric', 'Estivill', 'Pont', '2003-05-24', 'eestivill', MD5('Fat/3232'), 'home', 'eestivill@gmail.com'),
('85522891Y', 'Oleg', 'Blyznyuk', '', '1994-10-23', 'oblyznyuk', MD5('Fat/3232'), 'home', 'mbelivan@gmail.com');

insert into client_individual (dni_usuari) values 
('X5522891F'), ('85522891Y');

insert into client_colectiu (dni_usuari) values 
('55522891E'),
('X5522891F');

insert into client_federat (dni_usuari, num_federacio) values 
('85522891Y', '10000000');

insert into monitor (dni_monitor, nom, cognom, sexe) values ('27644446K', 'David', 'Salsablanca', 'home');

insert into curs (id_curs, dni_monitor, nom, descripcio, data_inici, preu_hora) values ('1','27644446K','Iniciació individual','Curs orientat a esquiar individualment.','2022-05-24', '50'),
('2','27644446K','Iniciació colectiva','Curs orientat a esquiar en grup.','2022-05-25', '150'),
('3','27644446K','Torneig de campions','Torneig de obstacles en baixada.','2022-05-30', '25');

insert into curs_individual (id_curs) values ('1');

insert into curs_colectiu (id_curs, aforament, preu) values ('2', '25', '10');

insert into curs_competitiu (id_curs, preu_comp, nivell) values ('3', '8', 'Avançat');

DELIMITER //
create procedure comprobarNumUsos(_id_prod int)
begin
	declare num_lloguer int;
    
    select num_usos into num_lloguer from producte p, lloguer_producte lp 
    where lp.id_producte=p.id_producte and lp.id_producte=_id_prod;
    
    if num_lloguer >= 10 then
		SET foreign_key_checks = 0;
		delete from lloguer_producte where id_producte=_id_prod;
        delete from producte where id_producte=_id_prod;
	end if;
end
//

DELIMITER //
create trigger update_productes
	before update on lloguer_producte
	for each row
	begin
	insert into prod_elim values (id_producte, dni_usuari);
	end;
//

DELIMITER //
create procedure augmentarNumUsos(_id_producte int)
begin
	declare _num_usos int;
    
    select count (id_producte) into _num_usos from producte p, lloguer_producte lp 
    where lp.id_producte=p.id_producte and p.id_producte=_id_producte;
    
    update producte set num_usos = _num_usos where id_producte=_id_producte;
end
//

set foreign_key_checks=0;