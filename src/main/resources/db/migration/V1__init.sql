DROP TABLE IF EXISTS `consent`;

CREATE TABLE `consent` (
  `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `profile` varchar(100),
  `client_id` varchar(100),
  `user_id` varchar(100),
  `accounts` varchar(500),
  `creation_datetime` datetime NOT NULL,
  `status` varchar(30) NOT NULL,
  `status_update_datetime` datetime NOT NULL,
  `permissions` varchar(1000) NOT NULL,
  `expiration_datetime` datetime,
  `transaction_from_datetime` datetime,
  `transaction_to_datetime` datetime,
  PRIMARY KEY (`id`)
);