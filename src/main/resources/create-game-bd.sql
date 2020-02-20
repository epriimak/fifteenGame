create table game_field_entity ( id int not null auto_increment,
                     field char(100),
                     primary key (id));

create table game_field_solution_step_entity (id int not null auto_increment,
                     field varchar(100),
                     direction varchar(20),
                     primary key (id));

insert into game_field_entity (field) values('[1 2 3 4]
[5 6 7 8]
[9 10 11 12]
[13 14 0 15]');