Drop database if exists Batibank2;
create database Batibank2;
set sql_safe_updates = 0;
use Batibank2;

drop table if exists usuarios;
create table usuarios(
	id int not null primary key auto_increment,
    nombre varchar(20) not null,
    primer_apellido varchar(20) not null,
    segundo_apellido varchar(20) not null,
    email varchar(20) not null,
	keyword varchar(20)
    
)Engine=innodb;

drop table if exists cuentas;
create table cuentas(
	
    id int not null primary key auto_increment,
    cantidad double not null,
    cliente int not null,
    tipo varchar(30),
    foreign key (cliente)
    references usuarios(id)
    on delete cascade
    on update cascade
    
)Engine = innodb;

drop table if exists movimientos;
create table movimientos(

	id int not null primary key auto_increment,
    id_envio int not null,
    id_recibe int not null,
    cantidad double not null,
    fecha date not null,
    descripcion varchar(30),
    cliente int
    
)Engine=innodb;

drop procedure if exists sp_insertar_usuario;
DELIMITER $$
	-- CREATE PROCEDIMIENTO ALMACENADO
    create procedure sp_insertar_usuario(IN nombre varchar(30),IN primer_apellido varchar(30),
									IN segundo_apellido varchar(30),IN email varchar(30), IN keyword varchar(30))
    begin
		insert into usuarios (nombre,primer_apellido,segundo_apellido,email,keyword) values
        (nombre,primer_apellido,segundo_apellido,email,keyword);
    end$$
DELIMITER ;

drop procedure if exists sp_insertar_cuenta;
DELIMITER $$
	-- CREATE PROCEDIMIENTO ALMACENADO
    create procedure sp_insertar_cuenta(IN cantidad varchar(30), IN cliente varchar(30), IN tipo varchar(30))
    begin
		insert into cuentas(cantidad,cliente,tipo) values (cantidad,cliente,tipo);
    end$$
DELIMITER ;

drop procedure if exists sp_update_cuenta;
DELIMITER $$
	-- CREATE PROCEDIMIENTO ALMACENADO
    create procedure sp_update_cuenta(IN _cantidad double, IN _id varchar(30))
    begin
		UPDATE cuentas SET cantidad = _cantidad where id = _id;
    end$$
DELIMITER ;


drop procedure if exists sp_insertar_historial;
DELIMITER $$
	-- CREATE PROCEDIMIENTO ALMACENADO
    create procedure sp_insertar_historial(IN id_envio varchar(30), IN id_recibe varchar(30), IN cantidad double, IN fecha date, IN descripcion varchar(30),IN cliente int)
    begin
		insert into movimientos(id_envio,id_recibe,cantidad,fecha,descripcion,cliente) values (id_envio,id_recibe,cantidad,fecha,descripcion,cliente);
    end$$
DELIMITER ;


UPDATE cuentas SET cantidad = 15 where id = 3;
call sp_update_cuenta(11,3);

call sp_insertar_historial('1','2',2,'2021-11-12',"holaa");

select * from movimientos

