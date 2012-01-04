drop table file cascade;
drop table Project cascade;
drop table SiteUser cascade;

create table SiteUser(Login varchar(8) constraint Login primary key,
					Password varchar(8) NOT NULL,
					email varchar(30) NOT NULL,
					connected boolean,
					Corporation varchar(30),
					Laboratory varchar(30));

create table Project(Project_Id integer constraint Project_Id primary key,
					Project_name varchar(20) NOT NULL,
					Project_Description varchar(200),
					Login varchar(8) references SiteUser);

create table file(File_Location varchar(60) constraint File_Location primary key,
					File_Description varchar(200),
					Project_Id integer references Project);