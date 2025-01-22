CREATE OR REPLACE TRIGGER TR_ADRESA_INSERT_NAZIV_GRADA
	BEFORE INSERT
	ON Adresa
	FOR EACH ROW
DECLARE
	v_nazivGrada varchar2 (50);
BEGIN
	SELECT g.nazivGrada
	INTO v_nazivGrada
	FROM Grad g
	WHERE g.gradID = :NEW.gradID;

	:NEW.nazivGrada := v_nazivGrada;
END;

INSERT INTO ADRESA (ADRESAID, DRZAVAID, GRADID, ULICA, BROJ) VALUES (7,1,2,'Bulevar 2',7);

----------------------------------


CREATE OR REPLACE TRIGGER TR_ADRESA_UPDATE_GRADID
BEFORE UPDATE OF gradID ON Adresa
FOR EACH ROW 
DECLARE 
v_nazivGrada VARCHAR2(40);
--PRAGMA AUTONOMOUS_TRANSACTION;
BEGIN 
 --EXECUTE IMMEDIATE 'ALTER TRIGGER TR_ADRESA_FORBID_UPDATE_NAZIV DISABLE';

SELECT g.nazivGrada INTO v_nazivGrada 
FROM Grad g 
WHERE g.gradID = :NEW.gradID; 

:NEW.nazivGrada := v_nazivGrada; 
 --EXECUTE IMMEDIATE 'ALTER TRIGGER TR_ADRESA_FORBID_UPDATE_NAZIV enable';
--commit;

END;

UPDATE ADRESA SET gradid = 2 WHERE ADRESAID = 1;

----------------------------------

CREATE OR REPLACE TRIGGER TR_ADRESA_FORBID_UPDATE_NAZIV
	BEFORE UPDATE OF nazivGrada ON Adresa
FOR EACH ROW
BEGIN
RAISE_APPLICATION_ERROR (-20005,  'Zabranjeno azuriranje kolone nazivGrada');
END;

UPDATE ADRESA SET NAZIVGRADA = 'Beograd' WHERE ADRESAID = 1;

-----------------------------------

/*CREATE OR REPLACE TRIGGER TR_ADRESA_FORB_UPDATE_DRZAVAID
	BEFORE UPDATE OF drzavaID ON Adresa
FOR EACH ROW
BEGIN
	IF  :NEW.drzavaID != :OLD.drzavaID THEN
	     RAISE_APPLICATION_ERROR (-20005,  'Zabranjeno azuriranje kolone drzavaID');
	END IF;
END;*/

CREATE OR REPLACE TRIGGER TR_AKIJEV
AFTER UPDATE OF gradID ON Adresa
FOR EACH ROW
BEGIN
    UPDATE DOBAVLJAC
    SET gradID = :NEW.gradID
    WHERE adresaID = :NEW.adresaID;
END;


---------------------------

CREATE OR REPLACE TRIGGER TR_GRAD_UPDATE_NAZIV
AFTER UPDATE OF nazivGrada ON Grad
FOR EACH ROW
DECLARE
  v_noviNaziv VARCHAR2(50);
  v_gradID NUMBER;

  PRAGMA AUTONOMOUS_TRANSACTION;
BEGIN
  v_noviNaziv := :NEW.nazivGrada;
  v_gradID := :NEW.gradID;

  EXECUTE IMMEDIATE 'ALTER TRIGGER TR_ADRESA_FORBID_UPDATE_NAZIV DISABLE';

  UPDATE Adresa
  SET nazivGrada = v_noviNaziv
  WHERE gradID = v_gradID;

  EXECUTE IMMEDIATE 'ALTER TRIGGER TR_ADRESA_FORBID_UPDATE_NAZIV ENABLE';
  COMMIT;
END;

UPDATE GRAD SET NAZIVGRADA = 'Cacak' WHERE GRADID = 1;

--------------------------------

/*CREATE OR REPLACE TRIGGER TR_GRAD_UPDATE_DRZAVAID
AFTER UPDATE OF drzavaID ON Grad
FOR EACH ROW
DECLARE
  v_noviID VARCHAR2(50);
  v_gradID NUMBER;

  PRAGMA AUTONOMOUS_TRANSACTION;
BEGIN
  v_noviID := :NEW.drzavaID;
  v_gradID := :NEW.gradID;

  EXECUTE IMMEDIATE 'ALTER TRIGGER TR_ADRESA_FORB_UPDATE_DRZAVAID DISABLE';

  UPDATE Adresa
  SET drzavaID = v_noviID
  WHERE gradID = v_gradID;

  EXECUTE IMMEDIATE 'ALTER TRIGGER TR_ADRESA_FORB_UPDATE_DRZAVAID ENABLE';
  COMMIT;
END;*/

--------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE TRIGGER TR_SKLADISTE_UPDATE_GRAD
AFTER UPDATE OF gradID ON Skladiste
FOR EACH ROW
DECLARE 
PRAGMA AUTONOMOUS_TRANSACTION;
BEGIN
    IF :NEW.gradID != :OLD.gradID THEN
        EXECUTE IMMEDIATE 'ALTER TRIGGER TR_RACUN_FORBID_UPDATE_GRAD DISABLE';

        UPDATE Racun
        SET Racun.gradID = :NEW.gradID
        WHERE Racun.skladisteID = :NEW.skladisteID;

        EXECUTE IMMEDIATE 'ALTER TRIGGER TR_RACUN_FORBID_UPDATE_GRAD ENABLE';
    END IF;
    COMMIT;
END;

--triger za drzavaID
/*CREATE OR REPLACE TRIGGER TR_POVEZI_DRZAVU_SKLADISTE
BEFORE INSERT OR UPDATE ON skladiste
FOR EACH ROW
DECLARE
  v_drzavaID NUMBER;
BEGIN
  -- Povuci vrednost za drzavaID na osnovu gradID iz tabele grad
  SELECT drzavaID INTO v_drzavaID
  FROM grad
  WHERE gradID = :NEW.gradID;

  -- Postavi vrednost za drzavaID u tabeli skladiste
  :NEW.drzavaID := v_drzavaID;
END;*/

UPDATE SKLADISTE SET GRADID = 1 WHERE SKLADISTEID  = 1;

--------------------------------

CREATE OR REPLACE TRIGGER TR_RACUN_INSERT
BEFORE INSERT ON Racun
FOR EACH ROW
DECLARE
	v_gradID NUMBER;
    v_drzavaID NUMBER;
BEGIN
	SELECT gradID INTO v_gradID
	FROM Skladiste
	WHERE skladisteID = :NEW.skladisteID;

	:NEW.gradID := v_gradID;
    
    SELECT drzavaID INTO v_drzavaID
	FROM Skladiste
	WHERE skladisteID = :NEW.skladisteID;

	:NEW.drzavaID := v_drzavaID;
END;

INSERT INTO RACUN (BROJRACUNA, DATUMRACUNA, DATUMPLACANJA, DATUMPROMETA, DOBAVLJACID, SKLADISTEID, BROJOTPREMNICE, RADNIKID)
VALUES (4, TO_DATE ('2023/11/27', 'yyyy/mm/dd'), TO_DATE ('2023/11/27', 'yyyy/mm/dd'), TO_DATE ('2023/11/27', 'yyyy/mm/dd'),
1, 1, 1, 1);

----------------------------

CREATE OR REPLACE TRIGGER TR_RACUN_UPDATE_SKLADISTE
BEFORE UPDATE OF skladisteID ON Racun
FOR EACH ROW
DECLARE
	v_gradID NUMBER;
BEGIN
	IF :NEW.skladisteID != :OLD.skladisteID THEN
	     SELECT gradID INTO v_gradID
	     FROM Skladiste
	     WHERE skladisteID = :NEW.skladisteID;

	    :NEW.gradID := v_gradID;
	END IF;
END;

UPDATE RACUN SET SKLADISTEID = 2 WHERE SKLADISTEID = 1;

------------------------------------------

CREATE OR REPLACE TRIGGER TR_RACUN_FORBID_UPDATE_GRAD
BEFORE UPDATE OF gradID ON Racun
FOR EACH ROW
BEGIN
		RAISE_APPLICATION_ERROR (-20009, 'Zabranjeno azuriranje kolone gradID');
        ROLLBACK;
END;

UPDATE RACUN SET GRADID = 1 WHERE GRADID = 2;

create or replace TRIGGER TR_RACUN_FORBID_UPDATE_DRZAVA
BEFORE UPDATE OF DRZAVAID ON Racun
FOR EACH ROW
BEGIN
		RAISE_APPLICATION_ERROR (-20009, 'Zabranjeno azuriranje kolone drzavaID');
        ROLLBACK;
END;

-------------------------------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE TRIGGER TR_OTPR_FORBID_UPDATE_NAZIV
BEFORE UPDATE OF nazivDobavljaca ON Otpremnica
FOR EACH ROW 
BEGIN
--IF :OLD.nazivDobavljaca != :NEW.nazivDobavljaca THEN
RAISE_APPLICATION_ERROR (-20000, 'Nije dozvoljena direktna izmena naziva dobavljaca u okviru tabele Otpremnica.'); 
--END IF; 
END; 

UPDATE OTPREMNICA SET NAZIVDOBAVLJACA = 'XY' WHERE DOBAVLJACID = 1;

--------------------------

CREATE OR REPLACE TRIGGER TR_DOBAVLJAC_UPDATE_NAZIV
AFTER UPDATE OF NazivDobavljaca ON Dobavljac 
FOR EACH ROW 
DECLARE 
PRAGMA AUTONOMOUS_TRANSACTION; 
BEGIN 
EXECUTE IMMEDIATE 'ALTER TRIGGER TR_OTPR_FORBID_UPDATE_NAZIV DISABLE'; 

UPDATE Otpremnica
SET nazivDobavljaca = :NEW.nazivDobavljaca 
WHERE dobavljacID = :OLD.dobavljacID; 

EXECUTE IMMEDIATE 'ALTER TRIGGER TR_OTPR_FORBID_UPDATE_NAZIV ENABLE'; 

COMMIT;
END; 

UPDATE DOBAVLJAC SET NAZIVDOBAVLJACA = 'METAL 56' WHERE DOBAVLJACID = 1;

-------------------------------

CREATE OR REPLACE TRIGGER TR_OTPREMNICA_INSERT 
BEFORE INSERT ON Otpremnica
FOR EACH ROW 
DECLARE 
v_nazivDobavljaca VARCHAR2(50); 
BEGIN 
SELECT nazivDobavljaca INTO v_nazivDobavljaca 
FROM Dobavljac 
WHERE dobavljacID = :NEW.dobavljacID; 

:NEW.nazivDobavljaca:=v_nazivDobavljaca; 
END; 

INSERT INTO OTPREMNICA (BROJOTPREMNICE, DATUMDOKUMENTA, DATUMVALUTE, SIFRAKOMITENTA, DOBAVLJACID, PORUDZBENICAID, RADNIKID)
VALUES (2,TO_DATE ('2023/11/27', 'yyyy/mm/dd'),TO_DATE ('2023/11/27', 'yyyy/mm/dd'),15, 555, 1, 1);

----------------------------

CREATE OR REPLACE TRIGGER TR_OTPR_UPDATE_ID
BEFORE UPDATE OF dobavljacID ON Otpremnica
FOR EACH ROW 
DECLARE 
v_nazivDobavljaca VARCHAR2(40); 
BEGIN 
SELECT d.nazivDobavljaca INTO v_nazivDobavljaca 
FROM Dobavljac D 
WHERE d.dobavljacID = :NEW.dobavljacID; 

:NEW.nazivDobavljaca := v_nazivDobavljaca; 
END;

UPDATE OTPREMNICA SET DOBAVLJACID = 1 WHERE DOBAVLJACID = 555;

-------------------------------------
