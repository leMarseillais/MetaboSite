create table Modules(idModule varchar(50) constraint idModule primary key,
							deploymentLocation varchar(50) NOT NULL,
							description varchar(200) NOT NULL,
							subjet varchar(50) NOT NULL);