INSERT INTO Persona (DNI,nombre,apellido,direccion,codigoPostal,email,facebook,telefonoFijo,telefonoCelular,fechaNacimiento) VALUES
('14559622','Mirta','Lopez','Sebastian Vera 2450','5800','mirtalopez@hotmail.com',NULL,'03584903659','0358156995364','1940-08-03'),
('27963163','Santiago','Alvarez','Constitucion 536','5800','salvarez@hotmail.com','Santiago Alvarez','03584906531','03581542963540','1985-06-13'),
('29948186','Estefania','Carasso','San Martin 1249','5800','esteficarasso@gmail.com','Estefania Carasso','03584901495','0358154257146','1990-11-21'),
('34654210','Marcos','Guerreiro','Buenos Aires 259','5805','marcosguerreiro@hotmail.com','Marcos Guerreiro',null,'0358154548953','1993-01-30'),
('39541203','Camila','Bacala','Uruguay 1448','5809','camibacala@hotmail.com','Cami Bacala','03584935641','03581548845996','1996-07-05'),
('20915239','Miguel','Alvarez','Cabrera 599','5800','miguelalvarez@yahoo.com',NULL,'03584901120','0358155423692','1979-02-28'),
('34152986','Micaela','Lucero','Maipu 1263','5800','micaluc@gmail.com','Mica Lucero',NULL,'0358155266398','1994-05-15');

INSERT INTO Contacto (DNI,fechaPrimerContacto,fechaAlta,fechaBaja,fechaRechazoAdhesion,estado,refdni,refRelacion,refComentario) VALUES
('27963163','2013-12-06','2013-12-20',NULL,NULL,'Adherido','14559622',NULL,NULL),
('29948186','2014-09-29','2014-10-30',NULL,NULL,'Adherido','14559622',NULL,NULL),
('34654210','2014-02-20','2014-02-20',NULL,NULL,'Adherido','14559622',NULL,NULL),
('39541203','2015-10-12','2015-10-15',NULL,NULL,'Voluntario','34654210','Amigo','-'),
('20915239','2015-06-21','2014-06-30',NULL,NULL,'Adherido','34654210','Hijo','-'),
('34152986','2016-02-16',NULL,NULL,NULL,'En Gestion','34654210','Primo','-');

INSERT INTO Donante (DNI,ocupacion,CUIL_CUIT) VALUES
('27963163','Licenciado en Cs de la Computacion','24-27963163-2'),
('29948186','Ama de Casa','27-29948186-6'),
('34654210','Estudiante','29-34654210-5'),
('20915239','Abogado','19-20915239-6');

INSERT INTO Programa (nombrePrograma,descripcion) VALUES
('Taller de Oficios','Talleres en los cuales se eseñan oficios como Carpinteria, Electricidad Domestica y Costura'),
('Curso de Ingles','Curso de Ingles hasta nivel 3'),
('Escuela Puente','Ayuda para que los chicos que no pudieron terminar el secundario lo hagan'),
('Programa de Deportes','Inclusion por medio de deportes');

INSERT INTO MedioDePago (nombreTitular,apellidoTitular) VALUES
('Santiago','Alvarez'),
('Estefania','Carasso'),
('Marcos','Guerreiro'),
('Miguel','Alvarez');

INSERT INTO Tarjeta (nombre) VALUES
('MasterCard'),
('Visa'),
('Nativa');

INSERT INTO TarjetaDeCredito (id,nroTarjeta,CVV,fechaVencimiento,nombreTarjeta) VALUES
(1,'102653','256','2017-08-01','MasterCard'),
(3,'226320','599','2018-01-01','Nativa');

INSERT INTO TarjetaDeDebitoOTransferencia (id,CBU,nroCuenta,tipoCuenta,sucursalBanco,nombreBanco) VALUES
(2,'2016663569','152369','Caja de Ahorros','Rio Cuarto Centro','Bancor'),
(4,'5693258756','326995','Cuenta Corriente','Carnerillo','Banco de la Nacion Argentina');

INSERT INTO Donacion (DNI,codigoPrograma,frecuencia,monto,identificador) VALUES
('27963163',1,'Mensual',200,1),
('29948186',1,'Semestral',1000,2),
('34654210',2,'Semestral',500,3),
('20915239',3,'Mensual',300,4);
