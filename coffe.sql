-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th1 02, 2023 lúc 09:09 AM
-- Phiên bản máy phục vụ: 10.4.22-MariaDB
-- Phiên bản PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `coffe`
--
CREATE DATABASE IF NOT EXISTS `coffe` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `coffe`;
-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customer`
--

CREATE TABLE `customer` (
  `IDCus` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CusName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DateAdd` date DEFAULT NULL,
  `Phone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `points` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `customer`
--

INSERT INTO `customer` (`IDCus`, `CusName`, `DateAdd`, `Phone`, `Email`, `points`) VALUES
('KH01', 'Lê Ngọc Long', '2022-12-17', '01228838383', 'lgprovip@gmail.com', 18100),
('KH02', 'Phan Hoàng Tuấn', '2022-12-18', '03212332', 'SKKK@mg', 6350),
('KH03', 'Nguyễn Minh Nam', '2022-12-20', '023031123', 'namMinhg@gmail.com', 14850),
('KH04', 'Nguyền Đình Tuân', '2023-01-02', '0312312', 'dinhtuan@gmail.com', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `employee`
--

CREATE TABLE `employee` (
  `Username` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `NameEmp` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `IdCart` varchar(12) COLLATE utf8_unicode_ci NOT NULL,
  `Gender` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `Birthday` date NOT NULL,
  `Phone` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `Email` varchar(26) COLLATE utf8_unicode_ci NOT NULL,
  `Address` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `passwd` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `role` varchar(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'nv'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `employee`
--

INSERT INTO `employee` (`Username`, `NameEmp`, `IdCart`, `Gender`, `Birthday`, `Phone`, `Email`, `Address`, `passwd`, `role`) VALUES
('a', 'Ngọc Long', '3122121', 'Nam', '2022-12-08', '0358492111', 'Long122@gmail.com', '22 Dống Đa', '123', 'nv'),
('abcde', 'Bích Ngọc a', '1828282818', 'Nam', '2022-12-19', '0312', 'boOOO@gmail.com', '82 Đống đa', '123', 'nv'),
('admin', 'Admin', '031212112', 'Nam', '2013-11-13', '012222222', 'Long@gmail.com', '12 Tây sơn', '123', 'admin'),
('user', 'Ludochi', '', 'Nữ', '2022-12-14', '02112123', 'Em@gmail', '112aa', '1', 'nv');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orderdetails`
--

CREATE TABLE `orderdetails` (
  `IdOrder` int(20) NOT NULL,
  `IdProduct` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `Amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `orderdetails`
--

INSERT INTO `orderdetails` (`IdOrder`, `IdProduct`, `Amount`) VALUES
(1, 'CF05', 1),
(1, 'SR02', 1),
(1, 'T02', 1),
(1, 'T03', 1),
(2, 'T01', 1),
(3, 'T02', 1),
(3, 'T03', 1),
(4, 'SR02', 1),
(4, 'SR1', 1),
(5, 'SR02', 1),
(5, 'SR1', 1),
(5, 'T03', 1),
(5, 'T04', 1),
(6, 'SR02', 2),
(6, 'T01', 1),
(6, 'T02', 1),
(7, 'T01', 1),
(7, 'T02', 1),
(7, 'T03', 1),
(8, 'CF05', 1),
(8, 'CF1', 1),
(8, 'CF3', 2),
(8, 'T01', 1),
(8, 'T02', 1),
(8, 'T03', 1),
(9, 'SR02', 1),
(9, 'SR1', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orderr`
--

CREATE TABLE `orderr` (
  `IDOrder` int(20) NOT NULL,
  `TableID` int(11) DEFAULT NULL,
  `DateOrder` date DEFAULT NULL,
  `Status` int(1) NOT NULL DEFAULT 0,
  `EmpID` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CusId` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Points` int(11) NOT NULL DEFAULT 0,
  `Total` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `orderr`
--

INSERT INTO `orderr` (`IDOrder`, `TableID`, `DateOrder`, `Status`, `EmpID`, `CusId`, `Points`, `Total`) VALUES
(1, 1, '2023-01-02', 1, 'a', 'KH02', 10000, 97000),
(2, NULL, NULL, 0, NULL, NULL, 0, 0),
(3, 1, '2023-01-02', 1, 'a', NULL, 0, 52000),
(4, 1, '2023-01-02', 1, 'a', 'KH01', 1000, 40000),
(5, 1, '2023-01-02', 1, 'a', 'KH03', 10000, 84000),
(6, 1, '2023-01-02', 1, 'a', NULL, 0, 90000),
(7, 1, '2023-01-02', 1, 'a', 'KH03', 0, 77000),
(8, 1, '2023-01-02', 1, 'a', NULL, 0, 202000),
(9, 1, NULL, 0, NULL, NULL, 0, 0);

--
-- Bẫy `orderr`
--
DELIMITER $$
CREATE TRIGGER `updatepoint` AFTER UPDATE ON `orderr` FOR EACH ROW Update customer Set Points = Points - new.Points + old.Points + (new.Total * 5 / 100) - (old.Total * 5 / 100) WHERE customer.IDCus = new.CusId
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `IdProduct` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `ProductName` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `IdType` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `Price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`IdProduct`, `ProductName`, `IdType`, `Price`) VALUES
('CF05', 'Cappuccino', 'SP01', 25000),
('CF1', 'Cafe Đen', 'SP01', 30000),
('CF2', 'Cafe Sữa', 'SP01', 35000),
('CF3', 'Bạc Xỉu', 'SP01', 35000),
('CF4', 'Cafe Lốc', 'SP01', 25000),
('SR02', 'Siro đào', 'SP93', 20000),
('SR1', 'Siro Cam', 'SP93', 20000),
('T01', 'Trà Đào', 'SP02', 25000),
('T02', 'Trà Xanh', 'SP02', 25000),
('T03', 'Trà Vải', 'SP02', 27000),
('T04', 'Trà Gừng', 'SP01', 17000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `producttype`
--

CREATE TABLE `producttype` (
  `IDType` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `TypeName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `producttype`
--

INSERT INTO `producttype` (`IDType`, `TypeName`) VALUES
('SP01', 'Cafe'),
('SP02', 'Trà'),
('SP93', 'Siro đá bào');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tables`
--

CREATE TABLE `tables` (
  `TableID` int(11) NOT NULL,
  `Name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `Status` varchar(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Trống'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tables`
--

INSERT INTO `tables` (`TableID`, `Name`, `Status`) VALUES
(1, 'Bàn 1', 'Có người'),
(2, 'Bàn 2', 'Trống'),
(3, 'Bàn 3', 'Trống'),
(4, 'Bàn 4', 'Trống'),
(5, 'Bàn 5', 'Trống'),
(6, 'Bàn 9', 'Trống'),
(7, 'Bàn 7', 'Trống'),
(8, 'Bàn 8', 'Trống'),
(9, 'Bàn 9', 'Trống'),
(10, 'Bàn 10', 'Trống'),
(11, 'Bàn 11', 'Trống'),
(12, 'Bàn 12', 'Trống'),
(13, 'Bàn 13', 'Trống'),
(14, 'Bàn 14', 'Trống'),
(15, 'Bàn 15', 'Trống');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`IDCus`);

--
-- Chỉ mục cho bảng `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`Username`);

--
-- Chỉ mục cho bảng `orderdetails`
--
ALTER TABLE `orderdetails`
  ADD PRIMARY KEY (`IdOrder`,`IdProduct`),
  ADD KEY `orderdetails_ibfk_2` (`IdProduct`);

--
-- Chỉ mục cho bảng `orderr`
--
ALTER TABLE `orderr`
  ADD PRIMARY KEY (`IDOrder`),
  ADD KEY `orderr_ibfk_1` (`CusId`),
  ADD KEY `EmpID` (`EmpID`),
  ADD KEY `orderr_ibfk_3` (`TableID`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`IdProduct`),
  ADD KEY `product_ibfk_1` (`IdType`);

--
-- Chỉ mục cho bảng `producttype`
--
ALTER TABLE `producttype`
  ADD PRIMARY KEY (`IDType`);

--
-- Chỉ mục cho bảng `tables`
--
ALTER TABLE `tables`
  ADD PRIMARY KEY (`TableID`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `orderr`
--
ALTER TABLE `orderr`
  MODIFY `IDOrder` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `orderdetails`
--
ALTER TABLE `orderdetails`
  ADD CONSTRAINT `orderdetails_ibfk_2` FOREIGN KEY (`IdProduct`) REFERENCES `product` (`IdProduct`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `orderdetails_ibfk_3` FOREIGN KEY (`IdOrder`) REFERENCES `orderr` (`IDOrder`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `orderr`
--
ALTER TABLE `orderr`
  ADD CONSTRAINT `orderr_ibfk_1` FOREIGN KEY (`CusId`) REFERENCES `customer` (`IDCus`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `orderr_ibfk_2` FOREIGN KEY (`EmpID`) REFERENCES `employee` (`Username`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `orderr_ibfk_3` FOREIGN KEY (`TableID`) REFERENCES `tables` (`TableID`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`IdType`) REFERENCES `producttype` (`IDType`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
