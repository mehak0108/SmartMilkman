/*
Navicat MySQL Data Transfer

Source Server         : LOL
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : Creamery

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-07-17 19:34:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `billgen`
-- ----------------------------
DROP TABLE IF EXISTS `billgen`;
CREATE TABLE `billgen` (
  `StartDate` date DEFAULT NULL,
  `EndDate` date DEFAULT NULL,
  `cname` varchar(20) DEFAULT NULL,
  `mobile` varchar(10) DEFAULT NULL,
  `cQtyTot` float DEFAULT NULL,
  `bQtyTot` float DEFAULT NULL,
  `Camt` float DEFAULT NULL,
  `Bamt` float DEFAULT NULL,
  `Total` float DEFAULT NULL,
  `cstatus` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of billgen
-- ----------------------------
INSERT INTO `billgen` VALUES ('2017-07-03', '2017-07-08', 'Akshu', '9858495005', '110', '110', '3808', '6552', '10360', '1');
INSERT INTO `billgen` VALUES ('2017-07-03', '2017-07-13', 'Prashant', '7654333344', '12', '23', '2096', '559', '2655', '0');
INSERT INTO `billgen` VALUES ('2017-07-03', '2017-07-14', 'Sahil', '8674930202', '4', '10', '132', '198', '330', '0');
INSERT INTO `billgen` VALUES ('2017-07-03', '2017-07-20', 'Mehak', '8437145781', '1', '3', '12', '96', '108', '1');
INSERT INTO `billgen` VALUES ('2017-07-02', '2017-07-26', 'Shivam', '7890330053', '8', '6', '209', '110', '319', '1');
INSERT INTO `billgen` VALUES ('2017-07-03', '2017-07-26', 'Sahil', '8674930202', '4', '10', '132', '198', '330', '0');
INSERT INTO `billgen` VALUES ('2017-07-11', '2017-07-21', 'Meghna', '8793039706', '0', '0', '192', '495', '687', '1');
INSERT INTO `billgen` VALUES ('2017-07-06', '2017-07-14', 'Amit', '1234567891', '6', '2', '312', '24', '336', '0');
INSERT INTO `billgen` VALUES ('2017-07-05', '2017-07-13', 'Akshu', '9858495005', '119', '117', '3808', '6552', '10360', '0');
INSERT INTO `billgen` VALUES ('2017-07-03', '2017-07-20', 'Meghna', '8793039706', '6', '11', '192', '495', '687', '0');
INSERT INTO `billgen` VALUES ('2017-07-01', '2017-07-31', 'Pratham', '8796504324', '0', '20', '0', '640', '640', '0');
INSERT INTO `billgen` VALUES ('2017-07-01', '2017-07-31', 'Prashant', '7654333344', '14', '33', '2096', '559', '2655', '0');
INSERT INTO `billgen` VALUES ('2017-07-04', '2017-07-27', 'Saumia', '3456721678', '30', '4', '360', '48', '408', '0');
INSERT INTO `billgen` VALUES ('2017-07-01', '2017-07-16', 'Sahil', '8674930202', '12', '18', '132', '198', '330', '0');
INSERT INTO `billgen` VALUES ('2017-07-01', '2017-07-16', 'shikha', '5432567541', '8', '1', '168', '12', '180', '0');
INSERT INTO `billgen` VALUES ('2017-07-01', '2017-07-16', 'Shivam', '7890330053', '19', '10', '209', '110', '319', '0');
INSERT INTO `billgen` VALUES ('2017-07-01', '2017-07-16', 'Zenia', '1234567891', '20', '0', '460', '0', '460', '1');
INSERT INTO `billgen` VALUES ('2017-07-01', '2017-07-16', 'Prashant', '7654333344', '16', '43', '2096', '559', '2655', '0');
INSERT INTO `billgen` VALUES ('2017-07-01', '2017-07-17', 'Amit', '1234567891', '26', '2', '312', '24', '336', '0');
INSERT INTO `billgen` VALUES ('2017-07-14', '2017-07-19', 'Muskan', '5432235678', '54', '6', '648', '72', '720', '0');

-- ----------------------------
-- Table structure for `customer`
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `cname` varchar(20) NOT NULL,
  `mobile` varchar(11) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `cow` float DEFAULT NULL,
  `buffalo` float DEFAULT NULL,
  `timings` varchar(200) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`cname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('Akshu', '9858495005', 'h.no.543', 'up', '6.5', '3', 'Evening', '2017-07-05');
INSERT INTO `customer` VALUES ('Ali', '1234567891', 'vbnm', 'up', '10', '0', 'Morning,', '2017-07-23');
INSERT INTO `customer` VALUES ('Amit', '1234567891', 'ertty', 'bti', '10', '0', ',Evening', '2017-07-12');
INSERT INTO `customer` VALUES ('Jack', '9873456222', 'h.no.123', 'chd', '10.2', '0', 'Morning,Evening', '2017-07-16');
INSERT INTO `customer` VALUES ('Jamy', '6433455666', 'bcfvbbn', 'bti', '4.5', '6', 'Morning,Evening', '2017-07-05');
INSERT INTO `customer` VALUES ('Jim', '', 'cvbnc', 'rkr', '10', '0', 'Morning,', '2017-07-16');
INSERT INTO `customer` VALUES ('Maddy', '1324234543', 'ewqr', 'up', '30', '0', 'Morning,', '2017-07-14');
INSERT INTO `customer` VALUES ('Meghna', '8793039706', 'h.no.786', 'KKP', '4.5', '3', 'Morning', '2017-07-05');
INSERT INTO `customer` VALUES ('Mehak', '8437145781', 'H.No.21165\nSt.No.7	', 'Ajit Road', '1.2', '3', 'Morning', '2017-07-04');
INSERT INTO `customer` VALUES ('Muskan', '5432235678', 'nbvcxxzxc', 'bti', '45.6', '2.3', 'Morning', '2017-07-05');
INSERT INTO `customer` VALUES ('Nandini', '9234156743', 'h.no.856', 'chd', '8.9', '3.5', 'Morning,', '2017-07-17');
INSERT INTO `customer` VALUES ('Prashant', '7654333344', 'vcxzzcv', 'up', '2.3', '10', 'Morning', '2017-07-07');
INSERT INTO `customer` VALUES ('Pratham', '8796504324', 'h.no.678', 'up', '0', '10', 'Morning,', '2017-07-07');
INSERT INTO `customer` VALUES ('Rohan', '5666455555', 'hggvv', 'civil lines', '4.5', '5', 'Morning', '2017-07-05');
INSERT INTO `customer` VALUES ('Sahil', '8674930202', 'h.no.7874', 'Guniana', '2.4', '5', 'MorningEvening', '2017-07-02');
INSERT INTO `customer` VALUES ('Saumia', '3456721678', 'h.no.546', 'up', '30', '4.5', 'Morning,Evening', '2017-07-12');
INSERT INTO `customer` VALUES ('shikha', '5432567541', 'h.no.789', 'chd', '30', '40', 'Morning,', '2017-07-16');
INSERT INTO `customer` VALUES ('Shivam', '7890330053', 'h.no.654', 'kkp', '4.5', '3', 'MorningEvening', '2017-07-03');
INSERT INTO `customer` VALUES ('Zenia', '1234567891', 'h.no.897', 'roorkee', '20', '0', ',Evening', '2017-07-15');

-- ----------------------------
-- Table structure for `dailyRecord`
-- ----------------------------
DROP TABLE IF EXISTS `dailyRecord`;
CREATE TABLE `dailyRecord` (
  `cname` varchar(30) DEFAULT NULL,
  `cmilkQty` float(30,0) DEFAULT NULL,
  `bmilkQty` float(30,0) DEFAULT NULL,
  `DOD` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of dailyRecord
-- ----------------------------
INSERT INTO `dailyRecord` VALUES ('Akshu', '101', '101', '2017-07-07');
INSERT INTO `dailyRecord` VALUES ('Muskan', '46', '2', '2017-07-07');
INSERT INTO `dailyRecord` VALUES ('Pratham', '0', '10', '2017-07-07');
INSERT INTO `dailyRecord` VALUES ('Sahil', '2', '5', '2017-07-07');
INSERT INTO `dailyRecord` VALUES ('Shivam', '4', '3', '2017-07-07');
INSERT INTO `dailyRecord` VALUES ('Meghna', '0', '0', '2017-07-07');
INSERT INTO `dailyRecord` VALUES ('Mehak', '0', '0', '2017-07-07');
INSERT INTO `dailyRecord` VALUES ('Akshu', '9', '9', '2017-07-07');
INSERT INTO `dailyRecord` VALUES ('Ali', '10', '0', '2017-07-09');
INSERT INTO `dailyRecord` VALUES ('Akshu', '6', '3', '2017-07-09');
INSERT INTO `dailyRecord` VALUES ('Meghna', '4', '3', '2017-07-09');
INSERT INTO `dailyRecord` VALUES ('Mehak', '1', '3', '2017-07-09');
INSERT INTO `dailyRecord` VALUES ('Muskan', '46', '2', '2017-07-09');
INSERT INTO `dailyRecord` VALUES ('Sahil', '2', '5', '2017-07-09');
INSERT INTO `dailyRecord` VALUES ('Shivam', '4', '3', '2017-07-09');
INSERT INTO `dailyRecord` VALUES ('Pratham', '0', '0', '2017-07-09');
INSERT INTO `dailyRecord` VALUES ('Prashant', '12', '23', '2017-07-09');
INSERT INTO `dailyRecord` VALUES ('Mehak', '1', '3', '2017-07-12');
INSERT INTO `dailyRecord` VALUES ('Muskan', '46', '2', '2017-07-12');
INSERT INTO `dailyRecord` VALUES ('Prashant', '2', '10', '2017-07-12');
INSERT INTO `dailyRecord` VALUES ('Pratham', '0', '10', '2017-07-12');
INSERT INTO `dailyRecord` VALUES ('Sahil', '2', '5', '2017-07-12');
INSERT INTO `dailyRecord` VALUES ('Saumia', '30', '4', '2017-07-12');
INSERT INTO `dailyRecord` VALUES ('Shivam', '4', '3', '2017-07-12');
INSERT INTO `dailyRecord` VALUES ('shubham', '0', '10', '2017-07-12');
INSERT INTO `dailyRecord` VALUES ('Ali', '12', '11', '2017-07-12');
INSERT INTO `dailyRecord` VALUES ('Akshu', '3', '4', '2017-07-12');
INSERT INTO `dailyRecord` VALUES ('Amit', '6', '2', '2017-07-12');
INSERT INTO `dailyRecord` VALUES ('Meghna', '2', '8', '2017-07-12');
INSERT INTO `dailyRecord` VALUES ('Ali', '10', '0', '2017-07-16');
INSERT INTO `dailyRecord` VALUES ('Akshu', '6', '3', '2017-07-16');
INSERT INTO `dailyRecord` VALUES ('Amit', '10', '0', '2017-07-16');
INSERT INTO `dailyRecord` VALUES ('Jack', '10', '0', '2017-07-16');
INSERT INTO `dailyRecord` VALUES ('Jim', '10', '0', '2017-07-16');
INSERT INTO `dailyRecord` VALUES ('Maddy', '30', '0', '2017-07-16');
INSERT INTO `dailyRecord` VALUES ('Meghna', '4', '3', '2017-07-16');
INSERT INTO `dailyRecord` VALUES ('Mehak', '1', '3', '2017-07-16');
INSERT INTO `dailyRecord` VALUES ('Muskan', '46', '2', '2017-07-16');
INSERT INTO `dailyRecord` VALUES ('Prashant', '2', '10', '2017-07-16');
INSERT INTO `dailyRecord` VALUES ('Zenia', '20', '0', '2017-07-16');
INSERT INTO `dailyRecord` VALUES ('Pratham', '4', '7', '2017-07-16');
INSERT INTO `dailyRecord` VALUES ('Rohan', '5', '6', '2017-07-16');
INSERT INTO `dailyRecord` VALUES ('Sahil', '6', '3', '2017-07-16');
INSERT INTO `dailyRecord` VALUES ('Saumia', '10', '2', '2017-07-16');
INSERT INTO `dailyRecord` VALUES ('shikha', '8', '1', '2017-07-16');
INSERT INTO `dailyRecord` VALUES ('Shivam', '7', '1', '2017-07-16');
INSERT INTO `dailyRecord` VALUES ('Ali', '10', '0', '2017-07-17');
INSERT INTO `dailyRecord` VALUES ('Akshu', '6', '3', '2017-07-17');
INSERT INTO `dailyRecord` VALUES ('Amit', '10', '0', '2017-07-17');
INSERT INTO `dailyRecord` VALUES ('Jack', '10', '0', '2017-07-17');
INSERT INTO `dailyRecord` VALUES ('Rohan', '4', '5', '2017-07-17');
INSERT INTO `dailyRecord` VALUES ('Sahil', '2', '5', '2017-07-17');
INSERT INTO `dailyRecord` VALUES ('Saumia', '30', '4', '2017-07-17');
INSERT INTO `dailyRecord` VALUES ('shikha', '30', '40', '2017-07-17');
INSERT INTO `dailyRecord` VALUES ('Shivam', '4', '3', '2017-07-17');
INSERT INTO `dailyRecord` VALUES ('Zenia', '20', '0', '2017-07-17');
INSERT INTO `dailyRecord` VALUES ('Jim', '4', '4', '2017-07-17');
INSERT INTO `dailyRecord` VALUES ('Maddy', '3', '4', '2017-07-17');
INSERT INTO `dailyRecord` VALUES ('Meghna', '2', '4', '2017-07-17');
INSERT INTO `dailyRecord` VALUES ('Mehak', '3', '4', '2017-07-17');
INSERT INTO `dailyRecord` VALUES ('Muskan', '8', '4', '2017-07-17');
INSERT INTO `dailyRecord` VALUES ('Prashant', '8', '4', '2017-07-17');
INSERT INTO `dailyRecord` VALUES ('Pratham', '8', '4', '2017-07-17');

-- ----------------------------
-- Table structure for `loginup`
-- ----------------------------
DROP TABLE IF EXISTS `loginup`;
CREATE TABLE `loginup` (
  `sname` varchar(20) DEFAULT NULL,
  `pwd` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of loginup
-- ----------------------------
INSERT INTO `loginup` VALUES ('Mehak Mittal', 'qwerty123');

-- ----------------------------
-- Table structure for `milkBowl`
-- ----------------------------
DROP TABLE IF EXISTS `milkBowl`;
CREATE TABLE `milkBowl` (
  `sDate` date DEFAULT NULL,
  `cMilkQty` float DEFAULT NULL,
  `bMilkQty` float DEFAULT NULL,
  `totalQty` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of milkBowl
-- ----------------------------
INSERT INTO `milkBowl` VALUES ('2017-07-07', '162', '130', '292');
INSERT INTO `milkBowl` VALUES ('2017-07-09', '85', '42', '127');
INSERT INTO `milkBowl` VALUES ('2017-07-12', '108', '72', '180');
INSERT INTO `milkBowl` VALUES ('2017-07-16', '189', '41', '230');
INSERT INTO `milkBowl` VALUES ('2017-07-17', '162', '88', '250');
