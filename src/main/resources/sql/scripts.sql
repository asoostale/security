CREATE TABLE `users` (
                         `id` INT NOT NULL AUTO_INCREMENT,
                         `username` VARCHAR(45) NOT NULL,
                         `password` VARCHAR(45) NOT NULL,
                         `enabled` INT NOT NULL,
                         PRIMARY KEY (`id`)
);



CREATE TABLE `authorities`(
                              `id` int not null AUTO_INCREMENT,
                              `username` varchar(45) not null ,
                              `authority` varchar(45) not null ,
                              primary key (`id`)
);

insert ignore into `users` values (null, 'happy', '12345', '1');
insert ignore into `authorities` values (null, 'happy', 'write');