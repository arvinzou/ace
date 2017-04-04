create database audit0000         DEFAULT CHARACTER SET utf8 ;
create database audit6201         DEFAULT CHARACTER SET utf8 ;
create database audit6202         DEFAULT CHARACTER SET utf8 ;
create database audit6203         DEFAULT CHARACTER SET utf8 ;
create database audit6204         DEFAULT CHARACTER SET utf8 ;
create database audit6205         DEFAULT CHARACTER SET utf8 ;
create database audit6206         DEFAULT CHARACTER SET utf8 ;
create database audit6207         DEFAULT CHARACTER SET utf8 ;
create database audit6208         DEFAULT CHARACTER SET utf8 ;
create database audit6209         DEFAULT CHARACTER SET utf8 ;
create database audit6210         DEFAULT CHARACTER SET utf8 ;
create database audit6211         DEFAULT CHARACTER SET utf8 ;
create database audit6212         DEFAULT CHARACTER SET utf8 ;
create database audit6213         DEFAULT CHARACTER SET utf8 ;
create database audit6214         DEFAULT CHARACTER SET utf8 ;
create database audit6215         DEFAULT CHARACTER SET utf8 ;
create database audit6216         DEFAULT CHARACTER SET utf8 ;
create database audit6217         DEFAULT CHARACTER SET utf8 ;
create database audit6218         DEFAULT CHARACTER SET utf8 ;
create database audit6219         DEFAULT CHARACTER SET utf8 ;
create database audit6220         DEFAULT CHARACTER SET utf8 ;
create database audit6221         DEFAULT CHARACTER SET utf8 ;
create database audit6222         DEFAULT CHARACTER SET utf8 ;
create database audit6223         DEFAULT CHARACTER SET utf8 ;
create database audit6224         DEFAULT CHARACTER SET utf8 ;
create database audit6225         DEFAULT CHARACTER SET utf8 ;
create database audit6226         DEFAULT CHARACTER SET utf8 ;
create database audit6227         DEFAULT CHARACTER SET utf8 ;
create database audit6228         DEFAULT CHARACTER SET utf8 ;
create database audit6229         DEFAULT CHARACTER SET utf8 ;
create database audit6230         DEFAULT CHARACTER SET utf8 ;
create database auditrule         DEFAULT CHARACTER SET utf8 ;
create database dict              DEFAULT CHARACTER SET utf8 ;
create database portal            DEFAULT CHARACTER SET utf8 ;
create database seqdb             DEFAULT CHARACTER SET utf8 ;
use seqdb;
/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `mycat_sequence` */

DROP TABLE IF EXISTS `mycat_sequence`;

CREATE TABLE `mycat_sequence` (
  `NAME` varchar(50) NOT NULL,
  `current_value` int(11) NOT NULL,
  `increment` int(11) NOT NULL DEFAULT '100',
  PRIMARY KEY (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `mycat_sequence` */



/* Function  structure for function  `mycat_seq_currval` */

/*!50003 DROP FUNCTION IF EXISTS `mycat_seq_currval` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`msp`@`%` FUNCTION `mycat_seq_currval`(seq_name VARCHAR(50)) RETURNS varchar(64) CHARSET latin1
    DETERMINISTIC
BEGIN

    DECLARE retval VARCHAR(64);

    SET retval="-1,0";

    SELECT concat(CAST(current_value AS CHAR),",",CAST(increment AS CHAR) ) INTO retval 

        FROM MYCAT_SEQUENCE  WHERE name = seq_name;

    RETURN retval ;

END */$$
DELIMITER ;

/* Function  structure for function  `mycat_seq_nextval` */

/*!50003 DROP FUNCTION IF EXISTS `mycat_seq_nextval` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`msp`@`%` FUNCTION `mycat_seq_nextval`(seq_name VARCHAR(50)) RETURNS varchar(64) CHARSET latin1
    DETERMINISTIC
BEGIN

    DECLARE retval VARCHAR(64);

    DECLARE val BIGINT;

    DECLARE inc INT;

    DECLARE seq_lock INT;

    set val = -1;

    set inc = 0;

    SET seq_lock = -1;

    SELECT GET_LOCK(seq_name, 15) into seq_lock;

   

    if seq_lock = 1 then 

      SELECT current_value + increment, increment INTO val, inc 

          FROM MYCAT_SEQUENCE WHERE name = seq_name for update;

      if val != -1 then

          UPDATE MYCAT_SEQUENCE SET current_value = val WHERE name = seq_name;

      end if;

      SELECT RELEASE_LOCK(seq_name) into seq_lock;

    end if;

    SELECT concat(CAST((val - inc + 1) as CHAR),",",CAST(inc as CHAR)) INTO retval;

    RETURN retval;

END */$$
DELIMITER ;

/* Function  structure for function  `mycat_seq_setval` */

/*!50003 DROP FUNCTION IF EXISTS `mycat_seq_setval` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`msp`@`%` FUNCTION `mycat_seq_setval`(seq_name VARCHAR(50), value INTEGER) RETURNS varchar(64) CHARSET latin1
    DETERMINISTIC
BEGIN

    DECLARE retval VARCHAR(64);

    DECLARE inc INT;

    SET inc = 0;

    SELECT increment INTO inc FROM MYCAT_SEQUENCE WHERE name = seq_name;

    UPDATE MYCAT_SEQUENCE SET current_value = value WHERE name = seq_name;

    SELECT concat(CAST(value as CHAR),",",CAST(inc as CHAR)) INTO retval;

    RETURN retval;

END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

INSERT INTO mycat_sequence(name, current_value, increment)
    VALUES ('location', 10000, 50),
           ('logs', 10000, 50),
           ('dict', 10000, 50),
           ('role_resources', 10000, 50),
           ('attach', 10000, 50),
           ('users_role', 10000, 50),
           ('department', 10000, 50),
           ('msp_audit_item', 10000, 50),
           ('msp_audit_item_param', 10000, 50),
           ('msp_audit_level', 10000, 50),
           ('msp_audit_rule', 10000, 50),
           ('msp_audit_rule_activelog', 10000, 50),
           ('msp_audit_rule_area', 10000, 50),
           ('msp_audit_rule_attr', 10000, 50),
           ('msp_audit_rule_auditlog', 10000, 50),
           ('msp_audit_rule_branch', 10000, 50),
           ('msp_audit_rule_condition', 10000, 50),
           ('msp_audit_rule_condition_values', 10000, 50),
           ('msp_audit_rule_log', 10000, 50),
           ('msp_audit_rule_node', 10000, 50),
           ('msp_audit_rule_right', 10000, 50),
           ('msp_audit_rule_testlog', 10000, 50),
           ('msp_audit_rule_version', 10000, 50),
           ('msp_dict_drug_book', 10000, 50),
           ('msp_dict_drug_detail', 10000, 50),
           ('msp_dict_icd_area', 10000, 50),
           ('msp_dict_knowledge_abstract', 10000, 50),
           ('msp_dict_catalog_area', 10000, 50),
           ('msp_clinic_audit', 10000, 50),
           ('msp_clinic_audit_detail', 10000, 50),
           ('msp_clinic_autoaudit', 10000, 50),
           ('msp_clinic_autoaudit_detail', 10000, 50),
           ('msp_clinic_clarify', 10000, 50),
           ('msp_clinic_firstaudit', 10000, 50),
           ('msp_clinic_firstaudit_detail', 10000, 50),
           ('msp_clinic_info', 10000, 50),
           ('msp_clinic_info_detail', 10000, 200),
           ('msp_clinic_reaudit', 10000, 50),
           ('msp_clinic_reaudit_detail', 10000, 50),
           ('msp_hosp_audit', 10000, 50),
           ('msp_hosp_audit_detail', 10000, 50),
           ('msp_hosp_autoaudit', 10000, 50),
           ('msp_hosp_autoaudit_detail', 10000, 50),
           ('msp_hosp_clarify', 10000, 50),
           ('msp_hosp_diagnose', 10000, 50),
           ('msp_hosp_firstaudit', 10000, 50),
           ('msp_hosp_firstaudit_detail', 10000, 50),
           ('msp_hosp_info', 10000, 50),
           ('msp_hosp_info_detail', 10000, 500),
           ('msp_hosp_operation', 10000, 50),
           ('msp_hosp_reaudit', 10000, 50),
           ('msp_hosp_reaudit_detail', 10000, 50);