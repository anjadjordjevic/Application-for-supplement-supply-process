SELECT * FROM OTPREMNICA WHERE NAZIVDOBAVLJACA = 'METAL 56';

CREATE INDEX NazivDobavljacaIndex ON Otpremnica(NazivDobavljaca);

SELECT * FROM ARTIKAL WHERE JMID = 1;

CREATE INDEX ArtikalJedinicaMereIndex ON ARTIKAL (JMID);

