DROP DATABASE  IF EXISTS `spring_security`;

CREATE DATABASE  IF NOT EXISTS `spring_security`;
USE `spring_security`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users`
--

INSERT INTO `users` 
VALUES 
('cpulover1','{bcrypt}$2y$12$xbDVkPOMK6yxs8ubQht2juiMGOV92NDXqJwZghQk5CD1XzItibOv2 
',1), -- password: 1 <=> {noop}1
('cpulover2','{bcrypt}$2y$12$1NlSUMmseoAyVngGmiYJnOqemsnciH6mGkuc/5DNRdGmaAXieA.Q.
',1), -- password: 2
('cpulover3','{bcrypt}$2y$12$Ajb/Ad/ttGgXaczrmJS7neMF9hIRhIRfuJWv7jVJMOgHnfKyT2opq
',1); -- password: 3


--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('cpulover1','ROLE_EMPLOYEE'),
('cpulover2','ROLE_EMPLOYEE'),
('cpulover2','ROLE_MANAGER'),
('cpulover3','ROLE_EMPLOYEE'),
('cpulover3','ROLE_ADMIN');


