CREATE OR REPLACE PACKAGE CenaPaket AS
Sifra number;
Pom number := 0;
END CenaPaket;


--Triger - zabrana izmene aktuelne cene
CREATE OR REPLACE TRIGGER TR_FORBID_UPDATE_AKT_CENA
BEFORE UPDATE OF AktuelnaCena ON Artikal
FOR EACH ROW
BEGIN
IF (CenaPaket.Pom = 0 AND :NEW.AktuelnaCena != :OLD.AktuelnaCena) THEN
RAISE_APPLICATION_ERROR(-20000,'Nije dozvoljena direktna izmena aktuelne cene.');
END IF;
END;

UPDATE ARTIKAL SET AKTUELNACENA = 150 WHERE ARTIKALID = 1;

--Triger koji cuva sifru artikla
CREATE OR REPLACE TRIGGER TR_SACUVAJ_SIFRU_ARTIKLA
BEFORE INSERT OR UPDATE OR DELETE ON CenaParitet
FOR EACH ROW
BEGIN
IF(INSERTING OR UPDATING) THEN
BEGIN
CenaPaket.Sifra := :new.ArtikalID;
END;
ELSE
BEGIN
CenaPaket.Sifra := :old.ArtikalID;
END;
END IF;
END;

--Kreiranje procedure za odredjivanje aktuelne cene artikla
CREATE OR REPLACE PROCEDURE OdrediAktuelnuCenu(Sifra IN number) AS 
Iznos decimal := 0;  
BEGIN 
SELECT cp.cena.getIznosCene() INTO Iznos 
FROM CenaParitet cp
WHERE cp.ArtikalID = Sifra AND cp.cena.getDatumCene()= 
   (SELECT MAX(cp.cena.getDatumCene())  
    FROM CenaParitet cp
    WHERE cp.ArtikalID = Sifra); 
UPDATE Artikal 
SET AktuelnaCena = Iznos 
WHERE ArtikalID = Sifra; 
END;

--Triger koji poziva proceduru
CREATE OR REPLACE TRIGGER TR_AKTUELNA_CENA_ARTIKLA
AFTER INSERT OR UPDATE OR DELETE ON CenaParitet
FOR EACH ROW
DECLARE Sifra number := CenaPaket.Sifra;
BEGIN
CenaPaket.Pom := 1;
OdrediAktuelnuCenu(Sifra);
CenaPaket.Pom := 0;
END;


UPDATE CENAPARITET SET CENA = CenaParitetTip (234.22, TO_DATE ('2023/12/19', 'yyyy/mm/dd')) WHERE ARTIKALID = 1;
INSERT INTO CENAPARITET VALUES (6, 6, 'tip 6', new CenaParitetTip (1000.22, TO_DATE ('2024/12/26', 'yyyy/mm/dd')));
DELETE FROM CENAPARITET WHERE CENAPARITETID = 5;

--------------------------------------

SELECT INSTANCE_NAME FROM V$INSTANCE;