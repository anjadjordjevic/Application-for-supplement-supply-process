CREATE TABLE ARTIKAL2 (
artikalID NUMBER,
naziv varchar2(50),
pakovanje varchar2(20),
kvalitet varchar2(100),
jmID NUMBER,
stopaID NUMBER,
CONSTRAINT PK_ARTIKAL PRIMARY KEY (artikalID),
CONSTRAINT FK_ARTIKAL FOREIGN KEY (jmID) REFERENCES JEDINICAMERE (jmID),
FOREIGN KEY (stopaID) REFERENCES PORESKASTOPA (stopaID)
)
PARTITION BY RANGE(KVALITET)
(
PARTITION LosKvalitet VALUES LESS THAN (50),
PARTITION DobarKvalitet VALUES LESS THAN (100)
);


----------------------------------


CREATE TABLE PorudzbenicaOsnovno ( 
PorudzbenicaID number,
Datum date, 
constraint PK_PorudzbenicaOsnovno PRIMARY KEY (PorudzbenicaID) 
);

CREATE TABLE PorudzbenicaDetalji ( 
PorudzbenicaID number, 
DobavljacID number,
RadnikID number, 
constraint PK_PorudzbenicaDetalji PRIMARY KEY (PorudzbenicaID), 
constraint FK_PorudzbenicaDetalji FOREIGN KEY (RadnikID) REFERENCES Radnik(RadnikID),
FOREIGN KEY (dobavljacID) REFERENCES DOBAVLJAC (dobavljacID)
);

CREATE OR REPLACE view PorudzbenicaPogled AS ( 
SELECT pos.PorudzbenicaID, pos.Datum, pd.DobavljacID, pd.RadnikID
FROM PorudzbenicaOsnovno pos, PorudzbenicaDetalji pd 
WHERE pos.PorudzbenicaID = pd.PorudzbenicaID 
);


--TRIGGER ZA ZABRANU DIREKTNOG UNOSA ILI IZMENE VREDNOSTI U OKVIRU TABELE PORUDZBENICAOSNOVNO
CREATE OR REPLACE TRIGGER TR_POR_OS_PREVENT_MODIFICATION
BEFORE INSERT OR UPDATE OR DELETE ON PorudzbenicaOsnovno
FOR EACH ROW
BEGIN
    -- Provera za zabranu unosa novog rekorda
    IF INSERTING THEN
        RAISE_APPLICATION_ERROR(-20001, 'Zabranjen unos novog rekorda u tabelu PorudzbenicaOsnovno.');
    END IF;
    
    -- Provera za zabranu brisanja rekorda
     IF DELETING THEN
        RAISE_APPLICATION_ERROR(-20001, 'Zabranjeno brisanje rekorda iz tabele PorudzbenicaOsnovno.');
    END IF;

    -- Provera za zabranu izmene atributa
    IF UPDATING('PorudzbenicaID') OR UPDATING('Datum') THEN
        RAISE_APPLICATION_ERROR(-20002, 'Zabranjena izmena atributa PorudzbenicaID ili Datum u tabeli PorudzbenicaOsnovno.');
    END IF;
END;

--TRIGGER ZA ZABRANU DIREKTNOG UNOSA ILI IZMENE VREDNOSTI U OKVIRU TABELE PORUDZBENICADETALJI
CREATE OR REPLACE TRIGGER TR_POR_DET_PREVENT_MODIFICATON
BEFORE INSERT OR UPDATE OR DELETE ON PorudzbenicaDetalji
FOR EACH ROW
BEGIN
    -- Provera za zabranu unosa novog rekorda
    IF INSERTING THEN
        RAISE_APPLICATION_ERROR(-20001, 'Zabranjen unos novog rekorda u tabelu PorudzbenicaDetalji.');
    END IF;
    
    -- Provera za zabranu brisanja rekorda
     IF DELETING THEN
        RAISE_APPLICATION_ERROR(-20001, 'Zabranjeno brisanje rekorda iz tabele PorudzbenicaDetalji.');
    END IF;

    -- Provera za zabranu izmene atributa
    IF UPDATING('PorudzbenicaID') OR UPDATING('DobavljacID') OR UPDATING('RadnikID') THEN
        RAISE_APPLICATION_ERROR(-20002, 'Zabranjena izmena atributa PorudzbenicaID, DobavljacID i RadnikID u tabeli PorudzbenicaDetalji.');
    END IF;
END;


UPDATE PorudzbenicaOsnovno SET PORUDZBENICAID = 3 WHERE PORUDZBENICAID = 1;
UPDATE PorudzbenicaOsnovno SET DATUM = TO_DATE('21/11/2023','DD/MM/YYYY') WHERE PORUDZBENICAID = 1;
UPDATE PorudzbenicaDetalji SET PORUDZBENICAID = 2 WHERE PORUDZBENICAID = 1;
UPDATE PorudzbenicaDetalji SET DOBAVLJACID = 2 WHERE PORUDZBENICAID = 1;
UPDATE PorudzbenicaDetalji SET RADNIKID = 2 WHERE PORUDZBENICAID = 1;
INSERT INTO PorudzbenicaOsnovno VALUES (2, TO_DATE('21/11/2023','DD/MM/YYYY'));
INSERT INTO PorudzbenicaDetalji VALUES (2, 1, 1);


--TRIGGER ZA INSERT 
CREATE OR REPLACE trigger TR_PorudzbenicaPogled_INSERT
INSTEAD OF INSERT ON PorudzbenicaPogled 
FOR EACH ROW 
DECLARE
    PRAGMA AUTONOMOUS_TRANSACTION;
BEGIN 
    EXECUTE IMMEDIATE 'ALTER TRIGGER TR_POR_DET_PREVENT_MODIFICATON DISABLE';
    EXECUTE IMMEDIATE 'ALTER TRIGGER TR_POR_OS_PREVENT_MODIFICATION DISABLE';
    
    INSERT INTO PorudzbenicaOsnovno(PorudzbenicaID, Datum) 
    VALUES (:new.PorudzbenicaID, :new.Datum); 
    INSERT INTO PorudzbenicaDetalji(PorudzbenicaID, DobavljacID, RadnikID) 
    VALUES (:new.PorudzbenicaID, :new.DobavljacID, :new.RadnikID); 
    
    EXECUTE IMMEDIATE 'ALTER TRIGGER TR_POR_DET_PREVENT_MODIFICATON ENABLE';
    EXECUTE IMMEDIATE 'ALTER TRIGGER TR_POR_OS_PREVENT_MODIFICATION ENABLE';

    COMMIT;
END;

INSERT INTO PorudzbenicaPogled VALUES (1, TO_DATE('15/12/2023','DD/MM/YYYY'), 1, 1);

--TRIGGER ZA UPDATE
CREATE OR REPLACE trigger TR_PorudzbenicaPogled_UPDATE
INSTEAD OF UPDATE ON PorudzbenicaPogled 
FOR EACH ROW 
DECLARE
    PRAGMA AUTONOMOUS_TRANSACTION;
BEGIN 
    EXECUTE IMMEDIATE 'ALTER TRIGGER TR_POR_DET_PREVENT_MODIFICATON DISABLE';
    EXECUTE IMMEDIATE 'ALTER TRIGGER TR_POR_OS_PREVENT_MODIFICATION DISABLE';
    
    IF :OLD.PorudzbenicaID != :NEW.PorudzbenicaID THEN
        RAISE_APPLICATION_ERROR(-20007, 'Zabranjena izmena vrednosti kolone PorudzbenicaID u pogledu PorudzbenicaPogled.');
    END IF;
    
    UPDATE PorudzbenicaOsnovno SET Datum = :new.Datum WHERE PORUDZBENICAID = :new.porudzbenicaid; 
    UPDATE PorudzbenicaDetalji SET RadnikID = :new.RadnikID WHERE PORUDZBENICAID = :new.porudzbenicaid;
    UPDATE PorudzbenicaDetalji SET DobavljacID = :new.DobavljacID WHERE PORUDZBENICAID = :new.porudzbenicaid; 
    
    EXECUTE IMMEDIATE 'ALTER TRIGGER TR_POR_DET_PREVENT_MODIFICATON ENABLE';
    EXECUTE IMMEDIATE 'ALTER TRIGGER TR_POR_OS_PREVENT_MODIFICATION ENABLE';

    COMMIT;
END;

UPDATE PorudzbenicaPogled SET DOBAVLJACID = 555 WHERE PORUDZBENICAID = 2;

UPDATE PorudzbenicaPogled 
SET
Datum = TO_DATE('22/12/2023', 'DD/MM/YYYY'),
DobavljacID = 555,
PorudzbenicaID = 5
WHERE PORUDZBENICAID = 1;

--TRIGGER ZA DELETE
CREATE OR REPLACE trigger TR_PorudzbenicaPogled_DELETE
INSTEAD OF DELETE ON PorudzbenicaPogled 
FOR EACH ROW 
DECLARE
    PRAGMA AUTONOMOUS_TRANSACTION;
BEGIN 
    EXECUTE IMMEDIATE 'ALTER TRIGGER TR_POR_DET_PREVENT_MODIFICATON DISABLE';
    EXECUTE IMMEDIATE 'ALTER TRIGGER TR_POR_OS_PREVENT_MODIFICATION DISABLE';
    
    DELETE FROM PorudzbenicaOsnovno WHERE PorudzbenicaID = :old.PorudzbenicaID; 
    DELETE FROM PorudzbenicaDetalji WHERE PorudzbenicaID = :old.PorudzbenicaID; 
    
    EXECUTE IMMEDIATE 'ALTER TRIGGER TR_POR_DET_PREVENT_MODIFICATON ENABLE';
    EXECUTE IMMEDIATE 'ALTER TRIGGER TR_POR_OS_PREVENT_MODIFICATION ENABLE';

    COMMIT;
END;

DELETE FROM porudzbenicapogled WHERE PORUDZBENICAID = 1;
DELETE FROM PORUDZBENICADETALJI WHERE PORUDZBENICAID = 1;
