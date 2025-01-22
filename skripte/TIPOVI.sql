CREATE OR REPLACE TYPE CenaParitetTip AS OBJECT ( 
iznosCene DECIMAL (10,2), 
datumCene DATE,  
MEMBER FUNCTION getIznosCene RETURN DECIMAL, 
MEMBER FUNCTION getDatumCene RETURN DATE,
CONSTRUCTOR FUNCTION CenaParitetTip(iznosCene DECIMAL, datumCene DATE) 
RETURN SELF AS RESULT)
INSTANTIABLE NOT FINAL; 


CREATE OR REPLACE TYPE BODY CenaParitetTip AS 
MEMBER FUNCTION getIznosCene RETURN DECIMAL IS 
BEGIN 
RETURN SELF.iznosCene; 
END; 

MEMBER FUNCTION getDatumCene RETURN DATE IS 
BEGIN 
RETURN SELF.datumCene; 
END;

CONSTRUCTOR FUNCTION CenaParitetTip(iznosCene DECIMAL, datumCene DATE) 
RETURN SELF AS RESULT IS
 	BEGIN
  		  IF iznosCene <= 0 THEN
    		  	RAISE_APPLICATION_ERROR(-20001, 'Iznos cene mora biti veci od 0.');
   		  END IF;
  	 SELF.iznosCene := iznosCene;
   	 SELF.datumCene := datumCene;
 	 RETURN;
END;
END; 

CREATE TABLE CenaParitet ( 
artikalID NUMBER, 
cenaParitetID NUMBER,   
naziv VARCHAR2(50), 
cena CenaParitetTip,
CONSTRAINT PK_CENAPARITET2 PRIMARY KEY (artikalID, cenaParitetID),
CONSTRAINT FK_CENAPARITET2 FOREIGN KEY (artikalID) REFERENCES ARTIKAL (artikalID)
);


INSERT INTO CenaParitet (artikalID, cenaParitetID, naziv, cena) VALUES (1, 1, 'tip 1', NEW CenaParitetTip (126, TO_DATE ('2023/11/27', 'yyyy/mm/dd')));

UPDATE CenaParitet SET cena = CenaParitetTip (130.22, TO_DATE ('2023/12/19', 'yyyy/mm/dd')) WHERE CenaParitetID = 1 and artikalID = 1;
SELECT c.artikalID, c.cenaParitetID, c.naziv, c.cena.getIznosCene(), c.cena.getDatumCene() 
FROM CenaParitet c;


-------------------------------


CREATE OR REPLACE TYPE PIBTip AS OBJECT ( 
oznakaPIB VARCHAR2(9), 
MEMBER FUNCTION getPIBTip RETURN VARCHAR2) 
INSTANTIABLE FINAL; 
 
CREATE OR REPLACE TYPE BODY PIBTip AS 
MEMBER FUNCTION getPIBTip return VARCHAR2 IS 
BEGIN 
RETURN SELF.oznakaPIB; 
END; 
END; 

CREATE TABLE Dobavljac ( 
dobavljacID NUMBER, 
nazivDobavljaca VARCHAR2 (50), 
telefon VARCHAR2 (15), 
maticniBroj VARCHAR2 (30), 
PIB PIBTip, 
adresaID NUMBER,
drzavaID NUMBER,
gradID NUMBER,
constraint PK_Dobavljac2 PRIMARY KEY (dobavljacID), 
constraint FK_Dobavljac2 FOREIGN KEY (adresaID, drzavaID, gradID) REFERENCES Adresa (adresaID, drzavaID, gradID),
check (REGEXP_LIKE (PIB.oznakaPIB,'[0-9]{9}'))  
); 

INSERT INTO Dobavljac VALUES (555, 'Metal Export DOO', '011-777-89', '22334478', PIBTip('012345678'), 1, 1, 1); 
INSERT INTO Dobavljac VALUES (2, 'Y group', '011-596-89', '12334478', PIBTip('992345678'), 1, 1, 1); 
INSERT INTO Dobavljac VALUES (1, 'MKP', '011-777-89', '22334478', PIBTip('512345678'), 1, 1, 1); 
UPDATE Dobavljac SET PIB = PIBTip ('987650431') WHERE dobavljacID = 555; 
SELECT d.dobavljacID, d.nazivDobavljaca, d.PIB.getPIBTip() FROM Dobavljac2 d;