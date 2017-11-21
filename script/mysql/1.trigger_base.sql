DELIMITER |
CREATE DEFINER=CURRENT_USER TRIGGER `bf_department_BEFORE_INSERT` BEFORE INSERT ON `bf_department` FOR EACH ROW
BEGIN
  IF new.id_bf_department IS NULL OR new.id_bf_department = ''
  THEN
    SET new.id_bf_department = uuid();
  END IF;
END
|

CREATE DEFINER=CURRENT_USER TRIGGER `bf_people_BEFORE_INSERT` BEFORE INSERT ON `bf_people` FOR EACH ROW
BEGIN
  IF new.id_bf_people IS NULL OR new.id_bf_people = ''
  THEN
    SET new.id_bf_people = uuid();
  END IF;
END
|

CREATE DEFINER=CURRENT_USER TRIGGER `bf_department_rel_BEFORE_INSERT` BEFORE INSERT ON `bf_department_rel` FOR EACH ROW
BEGIN
  IF new.id_bf_department_rel IS NULL OR new.id_bf_department_rel = ''
  THEN
    SET new.id_bf_department_rel = uuid();
  END IF;
END
|

CREATE DEFINER=CURRENT_USER TRIGGER `bf_role_BEFORE_INSERT` BEFORE INSERT ON `bf_role` FOR EACH ROW
BEGIN
  IF new.id_bf_role IS NULL OR new.id_bf_role = ''
  THEN
    SET new.id_bf_role = uuid();
  END IF;
END
|

CREATE DEFINER=CURRENT_USER TRIGGER `bf_people_role_rel_BEFORE_INSERT` BEFORE INSERT ON `bf_people_role_rel` FOR EACH ROW
BEGIN
  IF new.id_bf_people_role_rel IS NULL OR new.id_bf_people_role_rel = ''
  THEN
    SET new.id_bf_people_role_rel = uuid();
  END IF;
END
|

CREATE DEFINER=CURRENT_USER TRIGGER `bf_document_BEFORE_INSERT` BEFORE INSERT ON `bf_document` FOR EACH ROW
BEGIN
  IF new.id_bf_document IS NULL OR new.id_bf_document = ''
  THEN
    SET new.id_bf_document = uuid();
  END IF;
END
|

CREATE DEFINER=CURRENT_USER TRIGGER `bf_doc_group_BEFORE_INSERT` BEFORE INSERT ON `bf_doc_group` FOR EACH ROW
BEGIN
  IF new.id_bf_doc_group IS NULL OR new.id_bf_doc_group = ''
  THEN
    SET new.id_bf_doc_group = uuid();
  END IF;
END
|

CREATE DEFINER=CURRENT_USER TRIGGER `bf_folder_BEFORE_INSERT` BEFORE INSERT ON `bf_folder` FOR EACH ROW
BEGIN
  IF new.id_bf_folder IS NULL OR new.id_bf_folder = ''
  THEN
    SET new.id_bf_folder = uuid();
  END IF;
END
|

CREATE DEFINER=CURRENT_USER TRIGGER `bf_folder_doc_rel_BEFORE_INSERT` BEFORE INSERT ON `bf_folder_doc_rel` FOR EACH ROW
BEGIN
  IF new.id_bf_folder_doc_rel IS NULL OR new.id_bf_folder_doc_rel = ''
  THEN
    SET new.id_bf_folder_doc_rel = uuid();
  END IF;
END
|

CREATE DEFINER=CURRENT_USER TRIGGER `bf_role_folder_auth_BEFORE_INSERT` BEFORE INSERT ON `bf_role_folder_auth` FOR EACH ROW
BEGIN
  IF new.id_bf_role_folder_auth IS NULL OR new.id_bf_role_folder_auth = ''
  THEN
    SET new.id_bf_role_folder_auth = uuid();
  END IF;
END
|
COMMIT