-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Jeu 06 Mars 2014 à 15:23
-- Version du serveur: 5.6.12-log
-- Version de PHP: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `idl`
--
CREATE DATABASE IF NOT EXISTS `idl` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `idl`;

-- --------------------------------------------------------

--
-- Structure de la table `t_actions`
--

DROP TABLE IF EXISTS `t_actions`;
CREATE TABLE IF NOT EXISTS `t_actions` (
  `id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `t_actions`
--

INSERT INTO `t_actions` (`id`, `name`) VALUES
(1, 'AddRessource'),
(2, 'RemoveRessource');

-- --------------------------------------------------------

--
-- Structure de la table `t_medias`
--

DROP TABLE IF EXISTS `t_medias`;
CREATE TABLE IF NOT EXISTS `t_medias` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `binaryBlob` longblob NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `t_medias`
--

INSERT INTO `t_medias` (`id`, `name`, `binaryBlob`) VALUES
(1, 'test.txt', 0x436563692065737420756e206669636869657220746573740d0a);

-- --------------------------------------------------------

--
-- Structure de la table `t_rights`
--

DROP TABLE IF EXISTS `t_rights`;
CREATE TABLE IF NOT EXISTS `t_rights` (
  `id_user` int(5) unsigned NOT NULL,
  `id_action` int(5) unsigned NOT NULL,
  KEY `id_user` (`id_user`),
  KEY `id_action` (`id_action`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `t_rights`
--

INSERT INTO `t_rights` (`id_user`, `id_action`) VALUES
(1, 1),
(1, 2);

-- --------------------------------------------------------

--
-- Structure de la table `t_users`
--

DROP TABLE IF EXISTS `t_users`;
CREATE TABLE IF NOT EXISTS `t_users` (
  `id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(12) NOT NULL,
  `password` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `t_users`
--

INSERT INTO `t_users` (`id`, `name`, `password`) VALUES
(1, 'toto', 'toto');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `t_rights`
--
ALTER TABLE `t_rights`
  ADD CONSTRAINT `t_rights_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `t_users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `t_rights_ibfk_2` FOREIGN KEY (`id_action`) REFERENCES `t_actions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
