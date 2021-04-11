-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: am-test-mysql
-- Generation Time: Apr 11, 2021 at 02:03 PM
-- Server version: 5.7.30
-- PHP Version: 7.4.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Database: `anteairlines`
--

-- --------------------------------------------------------

--
-- Table structure for table `Airport`
--

CREATE TABLE `Airport` (
  `airportId` int(11) NOT NULL,
  `airportName` varchar(45) NOT NULL,
  `airportLocation` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- --------------------------------------------------------

--
-- Table structure for table `Booking`
--

CREATE TABLE `Booking` (
  `bookingId` bigint(20) NOT NULL,
  `bookingDate` varchar(45) NOT NULL,
  `numOfPassengers` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- --------------------------------------------------------

--
-- Table structure for table `Flight`
--

CREATE TABLE `Flight` (
  `flightId` bigint(20) NOT NULL,
  `seatCapacity` int(11) NOT NULL,
  `carrierName` varchar(45) NOT NULL,
  `flightModel` varchar(45) NOT NULL,
  `departureDate` date NOT NULL,
  `arrivalDate` date NOT NULL,
  `departureTime` varchar(45) NOT NULL,
  `arrivalTime` varchar(45) NOT NULL,
  `flightCharge` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- --------------------------------------------------------

--
-- Table structure for table `Passenger`
--

CREATE TABLE `Passenger` (
  `passengerId` int(11) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `LastName` varchar(45) NOT NULL,
  `phoneNumber` varchar(45) NOT NULL,
  `passportNumber` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `luggage` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- --------------------------------------------------------

--
-- Table structure for table `Schedulde`
--

CREATE TABLE `Schedulde` (
  `scheduleId` bigint(20) NOT NULL,
  `departDateTime` varchar(45) NOT NULL,
  `arrivalDateTime` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- --------------------------------------------------------

--
-- Table structure for table `ScheduledFlight`
--

CREATE TABLE `ScheduledFlight` (
  `scheduleFlightId` bigint(20) NOT NULL,
  `availableSeats` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE `Users` (
  `userId` bigint(20) NOT NULL,
  `username` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `phoneNumber` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Airport`
--
ALTER TABLE `Airport`
  ADD PRIMARY KEY (`airportId`);

--
-- Indexes for table `Booking`
--
ALTER TABLE `Booking`
  ADD PRIMARY KEY (`bookingId`);

--
-- Indexes for table `Flight`
--
ALTER TABLE `Flight`
  ADD PRIMARY KEY (`flightId`);

--
-- Indexes for table `Passenger`
--
ALTER TABLE `Passenger`
  ADD PRIMARY KEY (`passengerId`);

--
-- Indexes for table `Schedulde`
--
ALTER TABLE `Schedulde`
  ADD PRIMARY KEY (`scheduleId`);

--
-- Indexes for table `ScheduledFlight`
--
ALTER TABLE `ScheduledFlight`
  ADD PRIMARY KEY (`scheduleFlightId`);

--
-- Indexes for table `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`userId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Airport`
--
ALTER TABLE `Airport`
  MODIFY `airportId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Booking`
--
ALTER TABLE `Booking`
  MODIFY `bookingId` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Flight`
--
ALTER TABLE `Flight`
  MODIFY `flightId` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Passenger`
--
ALTER TABLE `Passenger`
  MODIFY `passengerId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Schedulde`
--
ALTER TABLE `Schedulde`
  MODIFY `scheduleId` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ScheduledFlight`
--
ALTER TABLE `ScheduledFlight`
  MODIFY `scheduleFlightId` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Users`
--
ALTER TABLE `Users`
  MODIFY `userId` bigint(20) NOT NULL AUTO_INCREMENT;
COMMIT;