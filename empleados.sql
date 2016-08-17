/*
Navicat MySQL Data Transfer

Source Server         : Conexion local
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : empleados

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-08-10 15:58:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `desc_nomina`
-- ----------------------------
DROP TABLE IF EXISTS `desc_nomina`;
CREATE TABLE `desc_nomina` (
  `id_dn` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_empleado` bigint(20) DEFAULT NULL,
  `ht` int(11) DEFAULT NULL,
  `sxh` double DEFAULT NULL,
  `seguro` double DEFAULT NULL,
  `isr` double DEFAULT NULL,
  `id_nomina` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_dn`),
  KEY `de_nim` (`id_nomina`),
  CONSTRAINT `de_nim` FOREIGN KEY (`id_nomina`) REFERENCES `nomina` (`id_nom`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of desc_nomina
-- ----------------------------
INSERT INTO `desc_nomina` VALUES ('1', '11311194', '39', '200', '100', '120', '1');
INSERT INTO `desc_nomina` VALUES ('2', '11311195', '40', '200', '100', '20', '1');

-- ----------------------------
-- Table structure for `empleados`
-- ----------------------------
DROP TABLE IF EXISTS `empleados`;
CREATE TABLE `empleados` (
  `clave` int(50) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `ap_paterno` varchar(100) DEFAULT NULL,
  `ap_materno` varchar(100) DEFAULT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `puesto` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`clave`),
  KEY `em_pi` (`puesto`),
  CONSTRAINT `em_pi` FOREIGN KEY (`puesto`) REFERENCES `puestos` (`puesto`)
) ENGINE=InnoDB AUTO_INCREMENT=11311195 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of empleados
-- ----------------------------
INSERT INTO `empleados` VALUES ('11311194', 'MARTIN', 'CIBRIAN', 'OLIVARRIA', '6622982645', 'HORIZONTE_273A', 'PROGRAMADOR');

-- ----------------------------
-- Table structure for `nomina`
-- ----------------------------
DROP TABLE IF EXISTS `nomina`;
CREATE TABLE `nomina` (
  `id_nom` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`id_nom`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of nomina
-- ----------------------------
INSERT INTO `nomina` VALUES ('1', '2016-08-09');

-- ----------------------------
-- Table structure for `puestos`
-- ----------------------------
DROP TABLE IF EXISTS `puestos`;
CREATE TABLE `puestos` (
  `puesto` varchar(255) NOT NULL DEFAULT '',
  `sueldoXH` double DEFAULT NULL,
  PRIMARY KEY (`puesto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of puestos
-- ----------------------------
INSERT INTO `puestos` VALUES ('Operador', '100');
INSERT INTO `puestos` VALUES ('Programador', '200');
INSERT INTO `puestos` VALUES ('RH', '200');

-- ----------------------------
-- Table structure for `tiempos`
-- ----------------------------
DROP TABLE IF EXISTS `tiempos`;
CREATE TABLE `tiempos` (
  `id_tiempo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_usuarios` int(50) DEFAULT '0',
  `fecha` date DEFAULT NULL,
  `acceso1` time DEFAULT NULL,
  `acceso2` time DEFAULT '00:00:00',
  `acceso3` time DEFAULT NULL,
  `acceso4` time DEFAULT NULL,
  PRIMARY KEY (`id_tiempo`),
  UNIQUE KEY `Index 2` (`id_usuarios`,`fecha`,`acceso2`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tiempos
-- ----------------------------
INSERT INTO `tiempos` VALUES ('1', '11311194', '2016-08-01', '07:00:58', '09:00:00', '10:24:29', '15:11:37');
INSERT INTO `tiempos` VALUES ('2', '11311195', '2016-08-01', '07:00:58', '09:00:00', '10:24:29', '15:11:37');
INSERT INTO `tiempos` VALUES ('3', '11311194', '2016-08-02', '07:00:58', '09:00:00', '10:24:29', '15:11:37');
INSERT INTO `tiempos` VALUES ('4', '11311195', '2016-08-02', '07:00:58', '09:00:00', '10:24:29', '15:11:37');
INSERT INTO `tiempos` VALUES ('5', '11311194', '2016-08-03', '07:00:58', '09:00:00', '10:24:29', '15:11:37');
INSERT INTO `tiempos` VALUES ('6', '11311195', '2016-08-03', '07:00:58', '09:00:00', '10:24:29', '15:11:37');
INSERT INTO `tiempos` VALUES ('7', '11311194', '2016-08-15', '07:00:58', '09:00:00', '10:24:29', '15:11:37');
INSERT INTO `tiempos` VALUES ('8', '11311195', '2016-08-15', '07:00:58', '09:00:00', '10:24:29', '15:11:37');

-- ----------------------------
-- Table structure for `usuarios`
-- ----------------------------
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(255) DEFAULT NULL,
  `pdw` varchar(255) DEFAULT NULL,
  `nivel` int(11) NOT NULL,
  `id_empleado` int(11) NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of usuarios
-- ----------------------------
INSERT INTO `usuarios` VALUES ('9', 'Admin', '202cb962ac59075b964b07152d234b70', '1', '11311194');
INSERT INTO `usuarios` VALUES ('15', 'Empleados', '202cb962ac59075b964b07152d234b70', '3', '0');
INSERT INTO `usuarios` VALUES ('16', 'RH', '202cb962ac59075b964b07152d234b70', '2', '11311196');
INSERT INTO `usuarios` VALUES ('17', 'empleados2', '7a674153c63cff1ad7f0e261c369ab2c', '3', '0');
