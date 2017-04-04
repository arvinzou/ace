mysql -u root -p -P 3066 -h10.12.8.111
mysqldump -h 10.1.5.35 -u root -p msp>/Users/chenxiaoke/dumps/msp20161227.sql
clinic_hosp_audit_info
clinic_hosp_audit_num
clinic_hosp_rule_result
clinic_mid_audit_hosp
clinic_mid_audit_rule
hosp_mid_audit_rule
hosp_mid_audit_hosp
msp_audit_rule_attr




ALTER TABLE msp_audit_rule_activelog   DROP FOREIGN KEY  FK_Reference_14;
ALTER TABLE msp_audit_rule_area   DROP FOREIGN KEY  FK_Reference_3;
ALTER TABLE msp_audit_rule_attr   DROP FOREIGN KEY  FK_Reference_19;
ALTER TABLE msp_audit_rule_auditlog   DROP FOREIGN KEY  FK_Reference_15;
ALTER TABLE msp_audit_rule_condition_values   DROP FOREIGN KEY  FK_Reference_1;
ALTER TABLE msp_audit_rule_log   DROP FOREIGN KEY  FK_Reference_6;
ALTER TABLE msp_audit_rule_node   DROP FOREIGN KEY  FK_Reference_11;
ALTER TABLE msp_audit_rule_right   DROP FOREIGN KEY  FK_Reference_18;
ALTER TABLE msp_audit_rule_testlog   DROP FOREIGN KEY  FK_Reference_16;
ALTER TABLE msp_audit_rule_version   DROP FOREIGN KEY  FK_Reference_8;
ALTER TABLE msp_clinic_audit_detail   DROP FOREIGN KEY  FK_Reference_clinic2;
ALTER TABLE msp_clinic_autoaudit_detail   DROP FOREIGN KEY  FK_Reference_clinic4;
ALTER TABLE msp_clinic_firstaudit_detail   DROP FOREIGN KEY  FK_Reference_clinic8;
ALTER TABLE msp_clinic_reaudit_detail   DROP FOREIGN KEY  FK_Reference_clinic11;
ALTER TABLE msp_dict_drug_book   DROP FOREIGN KEY  FK_Reference_4;
ALTER TABLE msp_dict_drug_book   DROP FOREIGN KEY  FK_Reference_5;
ALTER TABLE msp_dict_knowledge_abstract   DROP FOREIGN KEY  FK_Reference_7;
ALTER TABLE msp_hosp_autoaudit_detail   DROP FOREIGN KEY  FK_Reference_hosp4;
ALTER TABLE msp_hosp_firstaudit_detail   DROP FOREIGN KEY  FK_Reference_hosp9;
ALTER TABLE msp_hosp_reaudit_detail   DROP FOREIGN KEY  FK_Reference_hosp13;
