-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 12, 2021 at 10:21 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ablecarehome`
--

-- --------------------------------------------------------

--
-- Table structure for table `administrations`
--

CREATE TABLE `administrations` (
  `id` int(10) UNSIGNED NOT NULL,
  `prescription_id` int(10) UNSIGNED DEFAULT NULL,
  `nurse_id` int(10) UNSIGNED NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `administrations`
--

INSERT INTO `administrations` (`id`, `prescription_id`, `nurse_id`, `created_at`) VALUES
(1, 3, 3, '2021-05-05 12:17:06'),
(2, 4, 3, '2021-05-06 11:08:34'),
(3, 1, 3, '2021-05-06 11:12:57'),
(4, 2, 3, '2021-05-06 11:12:57'),
(5, 5, 4, '2021-05-06 19:41:19'),
(6, 6, 4, '2021-05-06 19:41:21'),
(7, 1, 3, '2021-05-07 00:15:50'),
(8, 1, 15, '2021-05-07 18:59:30'),
(9, 3, 15, '2021-05-07 18:59:45'),
(10, 4, 15, '2021-05-07 18:59:53'),
(11, 8, 4, '2021-05-12 16:32:58'),
(12, 1, 4, '2021-05-12 16:33:00'),
(13, 3, 4, '2021-05-12 16:33:03');

-- --------------------------------------------------------

--
-- Table structure for table `beds`
--

CREATE TABLE `beds` (
  `bed_ID` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `room_id` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `beds`
--

INSERT INTO `beds` (`bed_ID`, `name`, `room_id`) VALUES
(1, 'bed 1', 1),
(2, 'bed 2', 2),
(3, 'bed 3', 2),
(4, 'bed 4', 3),
(5, 'bed 5', 3),
(6, 'bed 6', 3),
(7, 'bed 7', 3),
(8, 'bed 8', 4),
(9, 'bed 9', 4),
(10, 'bed 10', 4),
(11, 'bed 11', 4),
(12, 'bed 12', 5),
(13, 'bed 13', 5),
(14, 'bed 14', 5),
(15, 'bed 15', 5),
(16, 'bed 16', 6),
(17, 'bed 17', 6),
(18, 'bed 18', 6),
(19, 'bed 19', 6),
(20, 'bed 20', 7),
(21, 'bed 21', 8),
(22, 'bed 22', 8),
(23, 'bed 23', 9),
(24, 'bed 24', 9),
(25, 'bed 25', 9),
(26, 'bed 26', 9),
(27, 'bed 27', 10),
(28, 'bed 28', 10),
(29, 'bed 29', 10),
(30, 'bed 30', 10),
(31, 'bed 31', 11),
(32, 'bed 32', 11),
(33, 'bed 33', 11),
(34, 'bed 34', 11),
(35, 'bed 35', 12),
(36, 'bed 36', 12),
(37, 'bed 37', 12),
(38, 'bed 38', 12);

-- --------------------------------------------------------

--
-- Table structure for table `changes_beds`
--

CREATE TABLE `changes_beds` (
  `id` int(10) UNSIGNED NOT NULL,
  `patient_id` int(10) UNSIGNED DEFAULT NULL,
  `fromBed_id` int(10) UNSIGNED DEFAULT NULL,
  `toBed_id` int(10) UNSIGNED DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `changes_beds`
--

INSERT INTO `changes_beds` (`id`, `patient_id`, `fromBed_id`, `toBed_id`, `created_at`) VALUES
(1, 10, 12, 38, '2021-05-04 23:32:53'),
(2, 4, 10, 8, '2021-05-06 20:42:48'),
(3, 8, 21, 10, '2021-05-06 22:47:55'),
(4, 1, 4, 7, '2021-05-07 00:19:14'),
(5, 1, 7, 15, '2021-05-07 19:01:31'),
(6, 24, 38, 1, '2021-05-12 19:17:03');

-- --------------------------------------------------------

--
-- Table structure for table `logs`
--

CREATE TABLE `logs` (
  `id` int(10) UNSIGNED NOT NULL,
  `staff_id` int(10) UNSIGNED DEFAULT NULL,
  `startSessionDate` timestamp NOT NULL DEFAULT current_timestamp(),
  `endSessionDate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `logs`
--

INSERT INTO `logs` (`id`, `staff_id`, `startSessionDate`, `endSessionDate`) VALUES
(1, 1, '2021-05-06 11:31:40', '2021-05-06 13:33:07'),
(2, 2, '2021-05-06 11:33:45', '2021-05-06 13:35:53'),
(3, 3, '2021-05-06 11:36:07', '2021-05-06 13:37:01'),
(4, 1, '2021-05-06 11:44:18', '2021-05-06 13:45:13'),
(5, 1, '2021-05-06 12:04:16', '2021-05-06 14:04:25'),
(6, 2, '2021-05-06 12:04:31', '2021-05-06 14:04:37'),
(7, 3, '2021-05-06 12:04:45', '2021-05-06 14:04:51'),
(8, 1, '2021-05-06 12:17:56', '2021-05-06 15:38:42'),
(9, 1, '2021-05-06 12:19:58', '2021-05-06 15:38:42'),
(10, 1, '2021-05-06 12:28:48', '2021-05-06 15:38:42'),
(11, 1, '2021-05-06 12:32:32', '2021-05-06 15:38:42'),
(12, 1, '2021-05-06 12:38:37', '2021-05-06 15:38:42'),
(13, 1, '2021-05-06 12:59:10', '2021-05-06 15:38:42'),
(14, 1, '2021-05-06 13:08:23', '2021-05-06 15:38:42'),
(15, 1, '2021-05-06 13:11:11', '2021-05-06 15:38:42'),
(16, 1, '2021-05-06 13:38:39', '2021-05-06 15:38:42'),
(17, 3, '2021-05-06 13:38:56', '2021-05-06 15:39:16'),
(18, 1, '2021-05-06 13:43:46', '2021-05-06 15:43:49'),
(19, 3, '2021-05-06 13:43:53', '2021-05-06 15:44:03'),
(20, 2, '2021-05-06 14:03:57', '2021-05-06 16:04:38'),
(21, 2, '2021-05-06 14:20:32', '2021-05-06 16:22:56'),
(22, 2, '2021-05-06 14:46:04', '2021-05-06 16:47:17'),
(23, 4, '2021-05-06 19:28:03', '2021-05-06 21:39:54'),
(24, 4, '2021-05-06 19:38:56', '2021-05-06 21:39:54'),
(25, 4, '2021-05-06 19:40:12', '2021-05-06 21:42:30'),
(26, 4, '2021-05-06 19:46:11', '2021-05-06 21:50:33'),
(27, 4, '2021-05-06 19:50:16', '2021-05-06 21:50:33'),
(28, 4, '2021-05-06 19:52:19', '2021-05-06 21:52:34'),
(29, 3, '2021-05-06 20:41:35', '2021-05-06 22:43:24'),
(30, 1, '2021-05-06 22:05:29', '2021-05-07 00:10:02'),
(31, 1, '2021-05-06 22:11:22', '2021-05-07 00:20:16'),
(32, 1, '2021-05-06 22:18:37', '2021-05-07 00:20:16'),
(33, 3, '2021-05-06 22:26:12', '2021-05-07 00:28:06'),
(34, 3, '2021-05-06 22:28:28', '2021-05-07 00:31:33'),
(35, 3, '2021-05-06 22:32:01', '2021-05-07 00:32:11'),
(36, 1, '2021-05-06 22:34:21', '2021-05-07 00:35:30'),
(37, 1, '2021-05-06 22:37:12', '2021-05-07 00:37:25'),
(38, 3, '2021-05-06 22:37:39', '2021-05-07 00:37:59'),
(39, 3, '2021-05-06 22:42:17', '2021-05-07 00:44:33'),
(40, 3, '2021-05-06 22:43:28', '2021-05-07 00:44:33'),
(41, 3, '2021-05-06 22:47:43', '2021-05-07 00:48:34'),
(42, 1, '2021-05-06 23:09:17', '2021-05-07 01:09:25'),
(43, 1, '2021-05-06 23:10:44', '2021-05-07 01:11:05'),
(44, 1, '2021-05-06 23:14:25', '2021-05-07 01:15:12'),
(45, 1, '2021-05-06 23:21:57', '2021-05-07 01:22:05'),
(46, 1, '2021-05-06 23:30:37', '2021-05-07 01:32:25'),
(47, 1, '2021-05-06 23:57:41', '2021-05-07 01:57:42'),
(48, 2, '2021-05-06 23:57:48', '2021-05-07 01:57:49'),
(49, 3, '2021-05-06 23:58:04', '2021-05-07 01:58:07'),
(50, 3, '2021-05-07 00:10:28', '2021-05-07 02:14:22'),
(51, 3, '2021-05-07 00:15:43', '2021-05-07 02:20:12'),
(52, 1, '2021-05-07 00:23:20', '2021-05-07 02:23:54'),
(53, 1, '2021-05-07 00:25:35', '2021-05-07 02:26:25'),
(54, 1, '2021-05-07 00:28:33', '2021-05-07 02:28:40'),
(55, 1, '2021-05-07 00:28:50', '2021-05-07 02:33:07'),
(56, 1, '2021-05-07 11:45:55', '2021-05-07 13:45:59'),
(57, 1, '2021-05-07 12:07:09', '2021-05-07 14:07:14'),
(58, 1, '2021-05-07 12:07:49', '2021-05-07 14:07:50'),
(59, 1, '2021-05-07 13:17:47', '2021-05-07 15:22:37'),
(60, 4, '2021-05-07 13:24:26', '2021-05-07 15:24:29'),
(61, 1, '2021-05-07 18:52:25', '2021-05-07 20:58:30'),
(62, 15, '2021-05-07 18:58:44', '2021-05-07 21:01:51'),
(63, 14, '2021-05-07 19:02:19', '2021-05-07 21:09:18'),
(64, 14, '2021-05-07 19:07:22', '2021-05-07 21:09:18'),
(65, 1, '2021-05-11 16:10:26', '2021-05-11 18:10:30'),
(67, 1, '2021-05-11 18:10:47', '2021-05-11 20:12:38'),
(68, 4, '2021-05-11 18:21:14', '2021-05-11 20:22:27'),
(69, 4, '2021-05-11 18:23:15', '2021-05-11 20:24:38'),
(70, 1, '2021-05-11 18:33:40', '2021-05-11 20:33:42'),
(71, 4, '2021-05-11 18:34:09', '2021-05-11 20:34:11'),
(72, 2, '2021-05-11 18:34:18', '2021-05-11 20:34:20'),
(73, 1, '2021-05-11 18:36:50', '2021-05-11 20:36:51'),
(74, 2, '2021-05-11 18:36:56', '2021-05-11 20:36:58'),
(75, 1, '2021-05-11 18:40:31', '2021-05-11 20:41:44'),
(76, 1, '2021-05-11 18:41:56', '2021-05-11 20:42:01'),
(77, 2, '2021-05-11 18:42:05', '2021-05-11 20:42:11'),
(78, 4, '2021-05-11 18:42:17', '2021-05-11 20:42:27'),
(79, 1, '2021-05-11 18:49:59', '2021-05-11 20:50:13'),
(80, 1, '2021-05-12 12:20:37', '2021-05-12 14:20:58'),
(81, 1, '2021-05-12 12:22:06', '2021-05-12 14:22:35'),
(82, 1, '2021-05-12 13:14:30', '2021-05-12 15:15:50'),
(83, 1, '2021-05-12 13:22:58', '2021-05-12 15:27:58'),
(84, 1, '2021-05-12 13:35:31', '2021-05-12 15:36:20'),
(85, 1, '2021-05-12 13:37:07', '2021-05-12 15:37:37'),
(86, 1, '2021-05-12 13:41:01', '2021-05-12 15:46:37'),
(87, 1, '2021-05-12 13:42:59', '2021-05-12 15:46:37'),
(88, 1, '2021-05-12 13:46:24', '2021-05-12 15:46:37'),
(89, 1, '2021-05-12 13:48:32', '2021-05-12 15:58:39'),
(90, 1, '2021-05-12 13:57:15', '2021-05-12 15:58:39'),
(91, 1, '2021-05-12 14:01:03', '2021-05-12 16:17:14'),
(92, 1, '2021-05-12 14:16:45', '2021-05-12 16:17:14'),
(93, 1, '2021-05-12 14:30:32', '2021-05-12 16:31:02'),
(94, 1, '2021-05-12 14:31:48', '2021-05-12 16:33:24'),
(95, 1, '2021-05-12 14:33:52', '2021-05-12 16:37:34'),
(96, 1, '2021-05-12 14:35:19', '2021-05-12 16:37:34'),
(97, 1, '2021-05-12 14:38:50', '2021-05-12 16:39:19'),
(98, 1, '2021-05-12 14:51:44', '2021-05-12 16:52:22'),
(99, 1, '2021-05-12 15:02:36', '2021-05-12 17:02:47'),
(100, 1, '2021-05-12 15:11:05', '2021-05-12 17:11:25'),
(101, 1, '2021-05-12 15:13:10', '2021-05-12 17:13:18'),
(102, 1, '2021-05-12 15:15:47', '2021-05-12 17:16:51'),
(103, 1, '2021-05-12 15:17:42', '2021-05-12 17:19:12'),
(104, 1, '2021-05-12 15:19:31', '2021-05-12 17:24:08'),
(105, 1, '2021-05-12 15:22:14', '2021-05-12 17:24:08'),
(106, 1, '2021-05-12 15:23:42', '2021-05-12 17:24:08'),
(107, 1, '2021-05-12 15:26:25', '2021-05-12 17:27:27'),
(108, 1, '2021-05-12 15:30:48', '2021-05-12 17:31:44'),
(109, 1, '2021-05-12 15:35:59', '2021-05-12 17:36:19'),
(110, 1, '2021-05-12 15:38:46', '2021-05-12 17:38:57'),
(111, 1, '2021-05-12 15:43:26', '2021-05-12 17:43:27'),
(112, 2, '2021-05-12 15:43:32', '2021-05-12 17:43:50'),
(113, 2, '2021-05-12 15:48:28', '2021-05-12 17:48:46'),
(114, 4, '2021-05-12 15:49:03', '2021-05-12 17:49:12'),
(115, 2, '2021-05-12 15:53:48', '2021-05-12 17:54:00'),
(116, 2, '2021-05-12 16:08:56', '2021-05-12 18:09:53'),
(117, 1, '2021-05-12 16:11:40', '2021-05-12 18:11:43'),
(118, 2, '2021-05-12 16:11:47', '2021-05-12 18:12:01'),
(119, 4, '2021-05-12 16:31:39', '2021-05-12 18:33:09'),
(120, 4, '2021-05-12 16:32:50', '2021-05-12 18:33:09'),
(121, 4, '2021-05-12 16:39:09', '2021-05-12 18:39:18'),
(122, 4, '2021-05-12 19:16:15', '2021-05-12 21:17:26'),
(123, 1, '2021-05-12 19:28:37', '2021-05-12 21:28:54'),
(124, 2, '2021-05-12 19:28:59', '2021-05-12 21:29:15'),
(125, 2, '2021-05-12 19:36:30', '2021-05-12 21:36:53'),
(126, 4, '2021-05-12 19:36:57', '2021-05-12 21:37:14'),
(127, 1, '2021-05-12 20:00:06', '2021-05-12 22:00:13'),
(128, 1, '2021-05-12 20:08:05', '2021-05-12 22:08:11'),
(129, 2, '2021-05-12 20:08:17', '2021-05-12 22:08:25'),
(130, 1, '2021-05-12 20:19:25', '2021-05-12 22:19:35');

-- --------------------------------------------------------

--
-- Table structure for table `medicines`
--

CREATE TABLE `medicines` (
  `medicine_ID` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Unit` enum('pills','gram') COLLATE utf8mb4_unicode_ci NOT NULL,
  `Amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `medicines`
--

INSERT INTO `medicines` (`medicine_ID`, `name`, `Unit`, `Amount`) VALUES
(1, 'Ampicillin', 'gram', 250),
(2, 'Allopurinol', 'gram', 300),
(3, 'Baclofen', 'gram', 10),
(4, 'Bosentan', 'gram', 62),
(5, 'Calcium', 'gram', 600),
(6, 'Paracetamol', 'pills', 24),
(7, 'Panadol', 'pills', 12),
(8, 'Excedrin ', 'pills', 50);

-- --------------------------------------------------------

--
-- Table structure for table `patients`
--

CREATE TABLE `patients` (
  `patient_ID` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Phone` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `gender` enum('male','female') COLLATE utf8mb4_unicode_ci NOT NULL,
  `isIsolted` int(11) NOT NULL DEFAULT 0,
  `admitted` datetime NOT NULL,
  `discharged` datetime DEFAULT NULL,
  `bed_id` int(10) UNSIGNED NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `patients`
--

INSERT INTO `patients` (`patient_ID`, `name`, `Phone`, `gender`, `isIsolted`, `admitted`, `discharged`, `bed_id`, `created_at`, `updated_at`) VALUES
(1, 'moustafa', '9660978521673', 'male', 0, '2012-01-01 00:00:00', NULL, 15, '2021-05-03 19:54:06', '2021-05-07 19:01:31'),
(2, 'bassem', '9660568525415', 'male', 0, '2021-04-11 00:00:00', NULL, 33, '2021-05-03 21:34:47', '2021-05-03 21:42:14'),
(3, 'gehan', '846512178', 'female', 0, '2021-04-25 00:00:00', NULL, 9, '2021-05-04 00:09:29', '2021-05-04 00:33:50'),
(4, 'amira', '985461213', 'female', 0, '2021-04-21 00:00:00', NULL, 8, '2021-05-04 00:13:14', '2021-05-06 20:42:48'),
(5, 'dasd', '84564123', 'male', 0, '2021-04-25 00:00:00', '2021-05-04 02:18:19', 30, '2021-05-04 00:14:33', '2021-05-04 00:34:44'),
(6, 'tret', '954561', 'female', 0, '2021-04-19 00:00:00', '2021-05-04 02:15:56', 37, '2021-05-04 00:14:50', '2021-05-04 00:15:56'),
(7, 'hana', '65979465', 'female', 1, '2021-04-09 00:00:00', NULL, 16, '2021-05-04 10:58:37', '2021-05-04 10:58:37'),
(8, 'olaa', '954646', 'female', 0, '2021-04-19 00:00:00', NULL, 10, '2021-05-04 11:02:56', '2021-05-06 22:47:55'),
(9, 'yasser', '659848545', 'male', 0, '2021-04-19 00:00:00', NULL, 6, '2021-05-04 14:38:10', '2021-05-04 14:38:10'),
(10, 'poo', '964565', 'female', 0, '2021-04-11 00:00:00', '2021-05-07 15:21:47', 38, '2021-05-04 15:57:20', '2021-05-07 13:21:47'),
(11, 'msdsd', '96554156', 'male', 1, '2021-03-23 00:00:00', '2021-05-05 12:01:24', 1, '2021-05-05 10:01:06', '2021-05-05 10:01:24'),
(12, 'zena', '97546513', 'female', 0, '2021-05-24 00:00:00', '2021-05-07 00:19:14', 20, '2021-05-06 12:20:24', '2021-05-06 22:19:14'),
(13, 'hany', '974626423', 'male', 1, '2021-04-12 00:00:00', '2021-05-06 15:11:17', 29, '2021-05-06 12:39:06', '2021-05-06 13:11:17'),
(14, 'mahmoud', '97564561', 'male', 0, '2021-02-22 00:00:00', NULL, 23, '2021-05-06 13:01:39', '2021-05-06 13:01:39'),
(15, 'Medo', '34234', 'male', 1, '2021-05-04 00:00:00', NULL, 30, '2021-05-07 00:30:36', '2021-05-07 00:30:36'),
(19, 'Mody', '123123', 'male', 1, '2021-05-07 00:00:00', '2021-05-12 17:27:11', 38, '2021-05-07 18:58:23', '2021-05-12 15:27:11'),
(23, 'Hassennnn', '5641561', 'male', 0, '2021-02-17 00:00:00', NULL, 14, '2021-05-12 15:23:57', '2021-05-12 15:36:16'),
(24, 'boobo', '415456', 'male', 1, '2020-07-01 00:00:00', NULL, 1, '2021-05-12 15:31:12', '2021-05-12 19:17:03');

-- --------------------------------------------------------

--
-- Table structure for table `patient_medicines`
--

CREATE TABLE `patient_medicines` (
  `prescription_ID` int(10) UNSIGNED NOT NULL,
  `patient_id` int(10) UNSIGNED NOT NULL,
  `doctor_id` int(10) UNSIGNED NOT NULL,
  `medicine_id` int(10) UNSIGNED NOT NULL,
  `reason` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `hourTime` int(11) NOT NULL,
  `active` int(10) NOT NULL DEFAULT 1,
  `doseAmount` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `patient_medicines`
--

INSERT INTO `patient_medicines` (`prescription_ID`, `patient_id`, `doctor_id`, `medicine_id`, `reason`, `hourTime`, `active`, `doseAmount`, `created_at`, `updated_at`) VALUES
(1, 1, 2, 1, 'sasasas', 10, 1, 200, '2021-05-04 22:12:10', '2021-05-05 10:47:47'),
(2, 7, 2, 2, 'arered', 12, 1, 95, '2021-05-04 22:31:11', '2021-05-05 10:08:25'),
(3, 1, 2, 3, 'ojhdwqq', 24, 1, 100, '2021-05-04 22:32:35', '2021-05-04 22:32:35'),
(4, 1, 2, 6, 'pkfghds', 6, 1, 2, '2021-05-04 22:33:19', '2021-05-04 22:33:19'),
(5, 2, 2, 1, 'poggg', 3, 1, 50, '2021-05-06 14:46:34', '2021-05-06 14:46:42'),
(6, 2, 2, 7, 'ffbb', 6, 1, 2, '2021-05-06 14:46:57', '2021-05-06 14:46:57'),
(7, 2, 2, 8, 'puiui', 12, 0, 1, '2021-05-06 14:47:10', '2021-05-06 14:47:13'),
(8, 1, 14, 7, 'Habd', 11, 1, 23, '2021-05-07 19:08:31', '2021-05-07 19:08:31'),
(9, 1, 2, 8, 'gdfgdfg', 7, 0, 1, '2021-05-12 16:09:34', '2021-05-12 16:09:49');

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

CREATE TABLE `rooms` (
  `room_ID` int(10) UNSIGNED NOT NULL,
  `name` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type_id` enum('male','female','free') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'free',
  `ward_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`room_ID`, `name`, `type_id`, `ward_id`) VALUES
(1, 'Room 1', 'male', 1),
(2, 'Room 2', 'free', 1),
(3, 'Room 3', 'male', 1),
(4, 'Room 4', 'female', 1),
(5, 'Room 5', 'male', 1),
(6, 'Room 6', 'female', 1),
(7, 'Room 7', 'free', 2),
(8, 'Room 8', 'free', 2),
(9, 'Room 9', 'male', 2),
(10, 'Room 10', 'male', 2),
(11, 'Room 11', 'male', 2),
(12, 'Room 12', 'free', 2);

-- --------------------------------------------------------

--
-- Table structure for table `shifts`
--

CREATE TABLE `shifts` (
  `shift_ID` int(10) UNSIGNED NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `shifts`
--

INSERT INTO `shifts` (`shift_ID`, `start_time`, `end_time`) VALUES
(1, '08:00:00', '16:00:00'),
(2, '14:00:00', '22:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `staff_ID` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type_id` enum('Manager','Doctor','Nurse') COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `endeffectiveDate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`staff_ID`, `name`, `phone`, `username`, `password`, `type_id`, `created_at`, `updated_at`, `endeffectiveDate`) VALUES
(1, 'Abdallah', '9660548557624', 'abd', '1234', 'Manager', '2021-05-01 14:33:46', '2021-05-12 13:14:35', NULL),
(2, 'Mohamed', '9660978521673', 'moh', '123', 'Doctor', '2021-05-02 09:53:22', '2021-05-02 09:58:43', NULL),
(3, 'ali', '9660543021649', 'ali', 'aaa', 'Nurse', '2021-05-02 09:56:33', '2021-05-06 20:41:25', NULL),
(4, 'moustafa', '9660557427619', 'mou', '111', 'Nurse', '2021-05-03 11:35:24', '2021-05-05 10:00:01', NULL),
(5, 'boo', '9660548736521', 'boo', 'oo', 'Nurse', '2021-05-03 11:58:28', '2021-05-06 23:14:47', NULL),
(6, 'ahmed', '9660567927611', 'ahmed', 'ddd', 'Nurse', '2021-05-03 13:12:52', '2021-05-06 23:14:51', NULL),
(7, 'mahmoud', '9654781234', 'mmmm', '777', 'Doctor', '2021-05-03 23:28:20', '2021-05-06 23:11:02', NULL),
(8, 'dasd', '635265', 'sdad', '515', 'Nurse', '2021-05-03 23:42:33', '2021-05-06 23:15:05', '2021-05-07 01:15:05'),
(9, 'fdgdfg', '56456', 'fsd', '65416', 'Doctor', '2021-05-03 23:44:09', '2021-05-04 00:05:13', '2021-05-04 02:05:13'),
(10, 'dasd', '564156', 'sdsd', '2525', 'Nurse', '2021-05-03 23:58:36', '2021-05-04 00:05:05', '2021-05-04 02:05:05'),
(11, 'mksds', '563215', 'dasd', '5647', 'Nurse', '2021-05-04 00:02:30', '2021-05-04 00:05:02', '2021-05-04 02:05:02'),
(12, 'gdfh', '456123', 'sdawe', '2416', 'Nurse', '2021-05-04 00:04:25', '2021-05-04 00:04:59', '2021-05-04 02:04:59'),
(13, 'ahmed', '987321951753264', 'ahm', '111', 'Nurse', '2021-05-06 23:31:20', '2021-05-06 23:32:20', '2021-05-07 01:32:20'),
(14, 'Omar', '012023982486', 'Omar10', '10', 'Doctor', '2021-05-07 18:54:05', '2021-05-07 18:54:05', NULL),
(15, 'Magdy', '324234', 'Magdy10', '10', 'Nurse', '2021-05-07 18:56:23', '2021-05-07 18:56:23', NULL),
(20, 'mazen', '542164', 'mazz', '222', 'Doctor', '2021-05-12 14:17:05', '2021-05-12 14:51:58', NULL),
(21, 'hniaaa', '987564', 'hniaaa', '546', 'Nurse', '2021-05-12 14:32:12', '2021-05-12 14:37:29', '2021-05-12 16:37:29');

-- --------------------------------------------------------

--
-- Table structure for table `staffshift`
--

CREATE TABLE `staffshift` (
  `id` int(10) UNSIGNED NOT NULL,
  `staff_id` int(10) UNSIGNED NOT NULL,
  `shift_id` int(10) UNSIGNED NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `staffshift`
--

INSERT INTO `staffshift` (`id`, `staff_id`, `shift_id`, `created_at`) VALUES
(12, 3, 1, '2021-05-11 17:34:00'),
(13, 4, 2, '2021-05-11 17:34:00'),
(14, 5, 1, '2021-05-11 17:34:00'),
(15, 6, 2, '2021-05-11 17:34:00'),
(16, 15, 2, '2021-05-11 17:34:00'),
(17, 21, 2, '2021-05-12 14:32:12');

-- --------------------------------------------------------

--
-- Table structure for table `wards`
--

CREATE TABLE `wards` (
  `ward_ID` int(10) UNSIGNED NOT NULL,
  `name` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `wards`
--

INSERT INTO `wards` (`ward_ID`, `name`) VALUES
(1, 'ward 1'),
(2, 'ward 2');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `administrations`
--
ALTER TABLE `administrations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `administrations_prescription_id_foreign` (`prescription_id`),
  ADD KEY `administrations_nurse_id_foreign` (`nurse_id`);

--
-- Indexes for table `beds`
--
ALTER TABLE `beds`
  ADD PRIMARY KEY (`bed_ID`),
  ADD UNIQUE KEY `bed_name_unique` (`name`),
  ADD KEY `beds_room_id_foreign` (`room_id`);

--
-- Indexes for table `changes_beds`
--
ALTER TABLE `changes_beds`
  ADD PRIMARY KEY (`id`),
  ADD KEY `changes_beds_patient_id_foreign` (`patient_id`),
  ADD KEY `changes_beds_tobed_id_foreign` (`toBed_id`),
  ADD KEY `changes_beds_frombed_id_foreign` (`fromBed_id`);

--
-- Indexes for table `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `staff_id` (`staff_id`);

--
-- Indexes for table `medicines`
--
ALTER TABLE `medicines`
  ADD PRIMARY KEY (`medicine_ID`),
  ADD UNIQUE KEY `medicine_name_unique` (`name`);

--
-- Indexes for table `patients`
--
ALTER TABLE `patients`
  ADD PRIMARY KEY (`patient_ID`),
  ADD UNIQUE KEY `Phone_name_unique` (`Phone`),
  ADD KEY `patients_bed_id_foreign` (`bed_id`);

--
-- Indexes for table `patient_medicines`
--
ALTER TABLE `patient_medicines`
  ADD PRIMARY KEY (`prescription_ID`),
  ADD UNIQUE KEY `prescription_unique` (`patient_id`,`doctor_id`,`medicine_id`),
  ADD KEY `patient_medicines_patient_id_foreign` (`patient_id`),
  ADD KEY `patient_medicines_doctor_id_foreign` (`doctor_id`),
  ADD KEY `patient_medicines_medicine_id_foreign` (`medicine_id`);

--
-- Indexes for table `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`room_ID`),
  ADD UNIQUE KEY `room_name_unique` (`name`),
  ADD KEY `rooms_ward_id_foreign` (`ward_id`);

--
-- Indexes for table `shifts`
--
ALTER TABLE `shifts`
  ADD PRIMARY KEY (`shift_ID`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`staff_ID`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `staffshift`
--
ALTER TABLE `staffshift`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shift_id` (`shift_id`),
  ADD KEY `staff_id` (`staff_id`);

--
-- Indexes for table `wards`
--
ALTER TABLE `wards`
  ADD PRIMARY KEY (`ward_ID`),
  ADD UNIQUE KEY `ward_name_unique` (`name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `administrations`
--
ALTER TABLE `administrations`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `beds`
--
ALTER TABLE `beds`
  MODIFY `bed_ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `changes_beds`
--
ALTER TABLE `changes_beds`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `logs`
--
ALTER TABLE `logs`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=131;

--
-- AUTO_INCREMENT for table `medicines`
--
ALTER TABLE `medicines`
  MODIFY `medicine_ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `patients`
--
ALTER TABLE `patients`
  MODIFY `patient_ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `patient_medicines`
--
ALTER TABLE `patient_medicines`
  MODIFY `prescription_ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `rooms`
--
ALTER TABLE `rooms`
  MODIFY `room_ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `shifts`
--
ALTER TABLE `shifts`
  MODIFY `shift_ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
  MODIFY `staff_ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `staffshift`
--
ALTER TABLE `staffshift`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `wards`
--
ALTER TABLE `wards`
  MODIFY `ward_ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `administrations`
--
ALTER TABLE `administrations`
  ADD CONSTRAINT `administrations_nurse_id_foreign` FOREIGN KEY (`nurse_id`) REFERENCES `staff` (`staff_ID`),
  ADD CONSTRAINT `administrations_prescription_id_foreign` FOREIGN KEY (`prescription_id`) REFERENCES `patient_medicines` (`prescription_ID`);

--
-- Constraints for table `beds`
--
ALTER TABLE `beds`
  ADD CONSTRAINT `beds_room_id_foreign` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_ID`);

--
-- Constraints for table `changes_beds`
--
ALTER TABLE `changes_beds`
  ADD CONSTRAINT `changes_beds_frombed_id_foreign` FOREIGN KEY (`fromBed_id`) REFERENCES `beds` (`bed_ID`),
  ADD CONSTRAINT `changes_beds_patient_id_foreign` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`patient_ID`),
  ADD CONSTRAINT `changes_beds_tobed_id_foreign` FOREIGN KEY (`toBed_id`) REFERENCES `beds` (`bed_ID`);

--
-- Constraints for table `logs`
--
ALTER TABLE `logs`
  ADD CONSTRAINT `logs_ibfk_1` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`staff_ID`),
  ADD CONSTRAINT `logs_staff_id_foreign` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`staff_ID`);

--
-- Constraints for table `patients`
--
ALTER TABLE `patients`
  ADD CONSTRAINT `patients_bed_id_foreign` FOREIGN KEY (`bed_id`) REFERENCES `beds` (`bed_ID`);

--
-- Constraints for table `patient_medicines`
--
ALTER TABLE `patient_medicines`
  ADD CONSTRAINT `patient_medicines_doctor_id_foreign` FOREIGN KEY (`doctor_id`) REFERENCES `staff` (`staff_ID`),
  ADD CONSTRAINT `patient_medicines_medicine_id_foreign` FOREIGN KEY (`medicine_id`) REFERENCES `medicines` (`medicine_ID`),
  ADD CONSTRAINT `patient_medicines_patient_id_foreign` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`patient_ID`);

--
-- Constraints for table `rooms`
--
ALTER TABLE `rooms`
  ADD CONSTRAINT `rooms_ward_id_foreign` FOREIGN KEY (`ward_id`) REFERENCES `wards` (`ward_ID`);

--
-- Constraints for table `staffshift`
--
ALTER TABLE `staffshift`
  ADD CONSTRAINT `staffshift_ibfk_1` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`staff_ID`),
  ADD CONSTRAINT `staffshift_ibfk_2` FOREIGN KEY (`shift_id`) REFERENCES `shifts` (`shift_ID`),
  ADD CONSTRAINT `staffshift_ibfk_3` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`staff_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
