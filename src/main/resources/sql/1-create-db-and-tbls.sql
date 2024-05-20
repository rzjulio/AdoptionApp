drop table if exists pet;
drop table if exists adopter;


create table adopter(
                        id serial primary key,
                        name varchar(50) not null,
                        phone varchar(20),
                        date date
);

create table pet(
                    id serial primary key ,
                    name varchar(50) not null,
                    type varchar(20) not null,
                    breed varchar(40),
                    adopter_id integer,
                    CONSTRAINT fk_pet
                        FOREIGN KEY(adopter_id)
                            REFERENCES adopter(id)
);


GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO larku;
