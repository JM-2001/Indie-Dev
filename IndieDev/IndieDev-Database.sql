-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 06, 2023 at 12:23 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `indiedev`
--

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `comment_id` bigint(20) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`comment_id`, `body`, `created_at`, `post_id`, `user_id`) VALUES
(1, 'ddd', '2023-12-04 14:50:19.000000', 3, 2),
(2, 'rewrqwe', '2023-12-04 14:50:31.000000', 2, 2),
(3, 'Ok', '2023-12-04 14:52:51.000000', 2, 16);

-- --------------------------------------------------------

--
-- Table structure for table `follows`
--

CREATE TABLE `follows` (
  `user_id_followed` int(11) NOT NULL,
  `user_id_following` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
  `message_id` bigint(20) NOT NULL,
  `user_id_author` bigint(20) DEFAULT NULL,
  `body` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `user_id_to` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `message`
--

INSERT INTO `message` (`message_id`, `user_id_author`, `body`, `created_at`, `user_id_to`) VALUES
(1, 2, 'test', NULL, 2),
(2, 2, 'poopy', NULL, 16);

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE `post` (
  `post_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `visual` varchar(255) DEFAULT NULL,
  `body` varchar(255) DEFAULT NULL,
  `like_count` int(11) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `locked_post` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`post_id`, `user_id`, `visual`, `body`, `like_count`, `created_at`, `locked_post`) VALUES
(2, 16, 'robot-507811_12801.jpg', 'This is my sick ai image bros', 110, '2023-12-01 15:05:54.000000', b'0'),
(3, 2, 'robot-507811_12801.jpg', 'This is my sick ai image bros', 106, '2023-12-01 15:05:54.000000', b'0'),
(6, 18, '432testing18.jpg', 'SHREK', 0, NULL, b'1'),
(7, 16, 'testPic16.png', 'I like coding in C++ & C.', 0, NULL, b'1');

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

CREATE TABLE `project` (
  `project_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `visual` varchar(255) DEFAULT NULL,
  `body` varchar(255) DEFAULT NULL,
  `classification` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `timespent` varchar(255) DEFAULT NULL,
  `visual2` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `report`
--

CREATE TABLE `report` (
  `report_id` bigint(20) NOT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `report_classification` varchar(255) DEFAULT NULL,
  `report_description` varchar(255) DEFAULT NULL,
  `reported_post_id` bigint(20) DEFAULT NULL,
  `reported_project_id` bigint(20) DEFAULT NULL,
  `reported_user_id` bigint(20) NOT NULL,
  `reporter_user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `report`
--

INSERT INTO `report` (`report_id`, `creation_date`, `report_classification`, `report_description`, `reported_post_id`, `reported_project_id`, `reported_user_id`, `reporter_user_id`) VALUES
(1, '2023-12-03 20:52:23.000000', 'USER', 'poopooo', NULL, NULL, 16, 2),
(2, '2023-12-04 00:09:16.000000', 'USER', 'not kewl', NULL, NULL, 3, 2),
(3, '2023-12-04 00:34:38.000000', 'POST', 'BAD CONTENT', 1, NULL, 2, 17),
(4, '2023-12-04 00:35:05.000000', 'POST', 'BADCONTENT4321', 2, NULL, 16, 17),
(5, '2023-12-05 17:53:43.000000', 'POST', 'Post is too reliable. It hurts!', 7, NULL, 16, 20);

-- --------------------------------------------------------

--
-- Table structure for table `tos`
--

CREATE TABLE `tos` (
  `tos_id` bigint(20) NOT NULL,
  `terms_of_service` longtext DEFAULT NULL,
  `tos_public` bit(1) DEFAULT NULL,
  `admin_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tos`
--

INSERT INTO `tos` (`tos_id`, `terms_of_service`, `tos_public`, `admin_id`) VALUES
(1, 'This is Terms of Service. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', b'1', 2),
(2, 'This is Terms of Service. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.asdasdf', b'1', 3),
(3, 'This is Terms of Service. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.asdasdferwqrqwe', b'0', 3),
(4, 'This is Terms of Service. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.asdasdferwqrqwe', b'0', 3),
(5, 'This is Terms of Service. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.asdasdferwqrqwe', b'0', 3),
(6, 'This is Terms of Service. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.asdasdferwqrqwesadfasdfasfd. ROBLOX.', b'1', 3),
(7, 'This is Terms of Service. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.asdasdferwqrqwe. ROBLOX ROBLOX ROBLOX', b'1', 3),
(8, 'This is Terms of Service. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.asdasdferwqrqwesagsfg', b'1', 3),
(9, 'This is Terms of Service. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.asdasdferwqrqwe. TURTLES', b'1', 3);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `profession` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `profile_picture` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `username`, `password`, `role`, `profession`, `language`, `profile_picture`) VALUES
(2, 'JOEMAMA-MOD1', 'testmod', 'test', 'MOD', 'Artist', 'English', '/images/2_profilepic.png'),
(3, 'Yomommasadmin', 'testadmin', 'test', 'ADMIN', 'Musician', 'English', '/images/pp4.jpg'),
(5, 'Yomomma', 'testuser', 'test', 'LOCKEDUSER', 'Developer', 'English', '/images/profile1.jpg'),
(16, 'John Doe', 'JohnDoe', 'test', 'USER', 'Developer', 'English', '/images/16_profilepic.png'),
(18, 'Ethan Allen', 'pisspants', 'test', 'USER', 'Developer', 'English', '/images/pp5.jpg'),
(20, 'Alex Joe', 'AlexJo', 'testing', 'USER', 'Designer', 'English', '/images/pp2.png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`comment_id`);

--
-- Indexes for table `follows`
--
ALTER TABLE `follows`
  ADD PRIMARY KEY (`user_id_followed`,`user_id_following`);

--
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`message_id`);

--
-- Indexes for table `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`post_id`,`user_id`);

--
-- Indexes for table `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`project_id`,`user_id`);

--
-- Indexes for table `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`report_id`);

--
-- Indexes for table `tos`
--
ALTER TABLE `tos`
  ADD PRIMARY KEY (`tos_id`),
  ADD KEY `FKkn59jwc5238kd19pkx2gsvto4` (`admin_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `comment_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `message`
--
ALTER TABLE `message`
  MODIFY `message_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
  MODIFY `post_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `project`
--
ALTER TABLE `project`
  MODIFY `project_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `report`
--
ALTER TABLE `report`
  MODIFY `report_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tos`
--
ALTER TABLE `tos`
  MODIFY `tos_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tos`
--
ALTER TABLE `tos`
  ADD CONSTRAINT `FKkn59jwc5238kd19pkx2gsvto4` FOREIGN KEY (`admin_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
