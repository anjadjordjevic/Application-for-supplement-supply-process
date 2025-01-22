ALTER TABLE ARTIKAL ADD nazivJM VARCHAR2(30);
ALTER TABLE ARTIKAL ADD CONSTRAINT CHECKJM CHECK(nazivJM in ('g','kg','mg'));
 
ALTER TABLE ARTIKAL
DROP CONSTRAINT CHECKJM;

DROP TABLE JedinicaMere;
 
CREATE OR REPLACE PROCEDURE DODAVANJE_NOVE_JEDINICE_MERE (
    JM IN VARCHAR2
) AS 
    l_search_condition VARCHAR2(500 CHAR);
BEGIN
    -- Dobijanje trenutnog search_condition za CHECKJM constraint
    SELECT search_condition 
    INTO l_search_condition
    FROM all_constraints
    WHERE constraint_name = 'CHECKJM';

    -- Brisanje postoje?eg CHECKJM constraint-a
    EXECUTE IMMEDIATE 'ALTER TABLE ARTIKAL DROP CONSTRAINT CHECKJM';

    -- Dodavanje novog CHECKJM constraint-a sa dodatkom novog JM
    EXECUTE IMMEDIATE 'ALTER TABLE ARTIKAL ADD CONSTRAINT CHECKJM CHECK (' || l_search_condition || ' OR (NAZIVJM = ''' || JM || '''))';
END DODAVANJE_NOVE_JEDINICE_MERE;
/

BEGIN DODAVANJE_NOVE_JEDINICE_MERE('mg');end;