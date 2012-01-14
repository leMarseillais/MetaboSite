create table MetaboDataBase(idFichier integer constraint idFichier primary key,
							fileLocation varchar(50) NOT NULL,
							keyword varchar(200) NOT NULL,
							organism varchar(30) NOT NULL,
							fileType varchar(30) NOT NULL);