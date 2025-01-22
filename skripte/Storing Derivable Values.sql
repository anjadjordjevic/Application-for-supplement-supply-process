CREATE OR REPLACE PACKAGE UkupnoPaket AS
Sifra number;
Pom number := 0;
END UkupnoPaket;

--Triger koji sprecava izmenu kolone Ukupno u Racunu
CREATE OR REPLACE TRIGGER TR_FORBID_UPDATE_UKUPNO
BEFORE UPDATE OF Ukupno ON Racun
FOR EACH ROW
BEGIN
IF (UkupnoPaket.Pom = 0 AND :NEW.UKUPNO != :OLD.UKUPNO) THEN
RAISE_APPLICATION_ERROR(-20000,'Nije dozvoljena direktna izmena kolone Ukupno.');
END IF;
END;

UPDATE RACUN SET UKUPNO = 1000 WHERE BROJRACUNA = 1;

--Triger pomocu kog se odredjuje šifra i postavlja u globalnu promenljivu
CREATE OR REPLACE TRIGGER TR_Sacuvaj_Sifre_Stavki
BEFORE INSERT OR UPDATE OR DELETE ON StavkaRacuna
FOR EACH ROW 
BEGIN
IF (INSERTING OR UPDATING) THEN 
BEGIN 
UkupnoPaket.Sifra := :NEW.brojracuna;
END;
ELSE 
BEGIN 
UkupnoPaket.Sifra := :OLD.brojracuna;
END; 
END IF; 
END;

--Triger koji poziva proceduru
CREATE OR REPLACE TRIGGER TR_Odredi_Sumu_Kolicine
AFTER INSERT OR UPDATE OR DELETE ON StavkaRacuna
DECLARE dok NUMBER := UkupnoPaket.Sifra; 
BEGIN
UkupnoPaket.Pom := 1;
SumaKolicineStavkiRacuna(dok); 
UkupnoPaket.Pom := 0;
END;

INSERT INTO STAVKARACUNA VALUES (1,1,100,5,1);
INSERT INTO STAVKARACUNA VALUES (3,2,200,1,1);
UPDATE STAVKARACUNA SET CENAARTIKLA = 150 WHERE BROJRACUNA = 1;
DELETE FROM STAVKARACUNA WHERE BROJRACUNA = 3 AND STAVKARACUNAID = 2;

--Procedura za odredjivanje ukupne kolicine stavki racuna
CREATE OR REPLACE PROCEDURE SumaKolicineStavkiRacuna
(sifraRacuna IN NUMBER) AS 
suma NUMBER := 0; 
BEGIN 
SELECT SUM(sr.kolicina * sr.CENAARTIKLA) INTO suma 
FROM StavkaRacuna sr
WHERE sr.brojRacuna = sifraRacuna; 
UPDATE Racun
SET Ukupno = suma 
WHERE brojRacuna = sifraRacuna; 
END;

--Triger koji brise stavke racuna, kada se obrise racun kome pripadaju
CREATE OR REPLACE TRIGGER TR_DELETE_STAVKA
BEFORE DELETE ON Racun
FOR EACH ROW
BEGIN
    DELETE FROM StavkaRacuna
    WHERE BROJRACUNA = :OLD.BROJRACUNA;
END;

ALTER TABLE STAVKARACUNA
ADD CONSTRAINT FK_STAVKA_RACUNA
FOREIGN KEY (BROJRACUNA)
REFERENCES RACUN(BROJRACUNA)
ON DELETE CASCADE;


--TRIGER KOJI VUCE CENU NA OSNOVU ARTIKALID
CREATE OR REPLACE TRIGGER TR_SET_CENA_IN_STAVKARACUNA
BEFORE INSERT ON STAVKARACUNA
FOR EACH ROW
DECLARE
    v_aktuelna_cena NUMBER;
BEGIN
    -- Dobijanje vrednosti AKTUELNACENA za zadati ARTIKALID
    SELECT AKTUELNACENA
    INTO v_aktuelna_cena
    FROM ARTIKAL
    WHERE ARTIKALID = :NEW.ARTIKALID;

    -- Postavljanje vrednosti CENAARTIKLA u STAVKARACUNA
    :NEW.CENAARTIKLA := v_aktuelna_cena;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        -- Dodajte postupanje u skladu sa zahtevima vašeg sistema
        -- Na primer, možete postaviti vrednost na nešto podrazumevano
        :NEW.CENAARTIKLA := 0; 
END;

INSERT INTO STAVKARACUNA (BROJRACUNA, STAVKARACUNAID, ARTIKALID, KOLICINA) VALUES (2, 1, 2, 3);
INSERT INTO STAVKARACUNA (BROJRACUNA, STAVKARACUNAID, ARTIKALID, KOLICINA) VALUES (2, 2, 1, 1);