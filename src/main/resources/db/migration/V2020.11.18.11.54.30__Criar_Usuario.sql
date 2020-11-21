CREATE TABLE `usuario` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `nome`  varchar(200) not null,
  `email` varchar(255) NOT NULL unique,
  `senha_hash` varchar(255) NOT NULL,
  `cep` int NOT NULL,
  `role` varchar(20)  not null,
  PRIMARY KEY (`id`)
);