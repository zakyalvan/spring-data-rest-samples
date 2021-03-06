-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jul 20, 2014 at 04:56 PM
-- Server version: 5.5.38-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `restservice`
--

--
-- Dumping data for table `innvz_department`
--

INSERT INTO `innvz_department` (`id`, `name`, `version`) VALUES
(1, 'Enterprise Java Department', NULL),
(2, 'Android Mobile Development', NULL),
(3, 'DotNET Product Development', NULL);

--
-- Dumping data for table `innvz_employee`
--

INSERT INTO `innvz_employee` (`id`, `email`, `name`) VALUES
(1, 'david@innovez-one.com', 'David Yeo'),
(2, 'zaky@innovez-one.com', 'Muhammad Zaky Alvan'),
(3, 'julianto.phang@innovez-one.com', 'Julianto Phang'),
(4, 'moko@innovez-one.com', 'Widiatmoko'),
(5, 'ferdinan@innovez-one.com', 'Ferdinan Tampubolon');

--
-- Dumping data for table `innvz_project`
--

INSERT INTO `innvz_project` (`id`, `budget_amount`, `budget_currency`, `description`, `end_date`, `name`, `start_date`, `version`, `manager_id`) VALUES
(1, 1000, 'IDR', 'Test project description', '2014-07-31', 'Test Project', '2014-07-01', NULL, 1);

--
-- Dumping data for table `innvz_project_member`
--

INSERT INTO `innvz_project_member` (`project_id`, `employee_id`) VALUES
(1, 2),
(1, 3),
(1, 4);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
