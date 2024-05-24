drop table pet IF EXISTS;
drop table adopter IF EXISTS;


create memory table adopter(
                        id integer primary key auto_increment NOT NULL,
                        name varchar(50) not null,
                        phone varchar(20),
                        date date
);

create memory table pet(
                    id integer primary key auto_increment NOT NULL,
                    name varchar(50) not null,
                    type varchar(20) not null,
                    breed varchar(40),
                    adopter_id integer,
                    CONSTRAINT fk_pet
                        FOREIGN KEY(adopter_id)
                            REFERENCES adopter(id)
);


CREATE USER IF NOT EXISTS LARKU SALT 'f2d97d5e5c194fe4' HASH 'bf9ac7082b79123183a1a58f3f23b3822cbedc5c1161394f43bd4d0d03237c59' ADMIN;
