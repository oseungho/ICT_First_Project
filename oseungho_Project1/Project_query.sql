--select *--st.sname, st.sage, st.stnumber, st.sphone_number, st.semail, sj.subjectname 
--from studentSubject stsj 
--join studentDB st on stsj.StudentID = st.StudentID
--join subjectdb sj on stsj.subjectID = sj.subjectID;

--//학생이 듣는 수강과목 묶어서 출력(평균 포함)
--SELECT s.SName sname, s.Sage sage, s.STNumber stnumber,
--        LISTAGG(sj.SubjectName, ', ') WITHIN GROUP (ORDER BY ss.SubjectID) subjectname,
--        TRUNC(AVG(score), 2) average FROM StudentDB s
--    JOIN StudentSubject ss ON s.StudentID = ss.StudentID
--    JOIN SubjectDB sj ON ss.SubjectID = sj.SubjectID
--    GROUP BY s.SName, s.Sage, s.STNumber;


--// 학생이 듣는 수강과목 묶어서 출력(이메일, 전화번호 미포함)
--SELECT s.SName 이름, s.Sage 나이, s.STNumber 학번, 
--    LISTAGG(sj.SubjectName, ', ') WITHIN GROUP (ORDER BY ss.SubjectID) 수강과목
--    FROM StudentDB s
--    JOIN StudentSubject ss ON s.StudentID = ss.StudentID
--    JOIN SubjectDB sj ON ss.SubjectID = sj.SubjectID
--    GROUP BY s.SName, s.Sage, s.STNumber;
--
--// 학생이 듣는 수강과목 묶어서 출력(이메일, 전화번호 포함)
--SELECT s.SName 이름, s.Sage 나이, s.STNumber 학번, s.SPhone_number 전화번호, s.Semail 이메일,
--    LISTAGG(sj.SubjectName, ', ') WITHIN GROUP (ORDER BY ss.SubjectID) 수강과목
--FROM StudentDB s
--JOIN StudentSubject ss ON s.StudentID = ss.StudentID
--JOIN SubjectDB sj ON ss.SubjectID = sj.SubjectID
--GROUP BY s.SName, s.Sage, s.STNumber, s.SPhone_number, s.Semail;
--
--
--// 특정 과목의 수업을 듣는 학생 뽑기
--WITH JavaStudents AS (
--    SELECT s.StudentID, s.SName AS sname, s.Sage AS sage, s.STNumber AS stnumber
--    FROM StudentDB s
--    JOIN StudentSubject ss ON s.StudentID = ss.StudentID
--    WHERE ss.SubjectID = 1004
--)
--SELECT j.sname, j.sage, j.stnumber, (
--    SELECT LISTAGG(sj.SubjectName, ', ') WITHIN GROUP (ORDER BY ss2.SubjectID)
--    FROM StudentSubject ss2
--    JOIN SubjectDB sj ON ss2.SubjectID = sj.SubjectID
--    WHERE ss2.StudentID = j.StudentID
--) AS subjectname
--FROM JavaStudents j;

//교사 Data 조회
--SELECT * FROM (
--SELECT TNAME 이름, TAGE 나이, TPHONE_NUMBER 전화번호, TEMAIL 이메일, sbdb.SubjectName 담당과목 FROM TeacherDB tdb
--LEFT join subjectdb sbdb on tdb.SubjectID = sbdb.SubjectID);

// 학생 Data 조회
--SELECT s.SName 이름, s.Sage 나이, s.STNumber 학번, s.SPhone_number 전화번호, s.Semail 이메일,
--LISTAGG(sj.SubjectName, ', ') WITHIN GROUP (ORDER BY ss.SubjectID) 수강과목 
--FROM StudentDB s Left JOIN StudentSubject ss ON s.StudentID = ss.StudentID 
--Left JOIN SubjectDB sj ON ss.SubjectID = sj.SubjectID 
--GROUP BY s.SName, s.Sage, s.STNumber, s.SPhone_number, s.Semail;



// StudentDB와 TeacherDB를 전부 보여주는 Data
with ALL_records as (
    select ROWNUM as 코드번호, 구분, 이름, 나이, 전화번호, 이메일, 학번, 과목 from
    (SELECT '학생' AS 구분, SName AS 이름, Sage AS 나이, SPhone_number AS 전화번호, SEmail AS 이메일, stnumber as 학번,
    LISTAGG(sj.SubjectName, ', ') WITHIN GROUP (ORDER BY ss.SubjectID) 과목 
    FROM StudentDB s Left JOIN StudentSubject ss ON s.StudentID = ss.StudentID 
    Left JOIN SubjectDB sj ON ss.SubjectID = sj.SubjectID 
    GROUP BY s.SName, s.Sage, s.STNumber, s.SPhone_number, s.Semail
    UNION
    SELECT '교사' AS 구분, TName AS 이름, Tage AS 나이, TPhone_number AS 전화번호, TEmail AS 이메일, '' as 학번, subjectName 과목
    FROM TeacherDB t join subjectDB sbdb on t.subjectID = sbdb.subjectID)
) select * from ALL_records where 과목 = 'JAVA' or 과목 like '%JAVA,%';

// StudentDB와 TeacherDB를 전부 보여주는 Data


select * from
    (SELECT '학생' AS 구분, s.StudentID AS 코드번호, SName AS 이름, Sage AS 나이, SPhone_number AS 전화번호, SEmail AS 이메일, stnumber as 학번,
    LISTAGG(sj.SubjectName, ', ') WITHIN GROUP (ORDER BY ss.SubjectID) 과목 
    FROM StudentDB s Left JOIN StudentSubject ss ON s.StudentID = ss.StudentID 
    Left JOIN SubjectDB sj ON ss.SubjectID = sj.SubjectID 
    GROUP BY s.StudentID, s.SName, s.Sage, s.STNumber, s.SPhone_number, s.Semail
    UNION
    SELECT '교사' AS 구분, TeacherID AS 코드번호, TName AS 이름, Tage AS 나이, TPhone_number AS 전화번호, TEmail AS 이메일, '' as 학번, subjectName 과목
    FROM TeacherDB t join subjectDB sbdb on t.subjectID = sbdb.subjectID) where 이름 = '오승호';

 SELECT '교사' AS 구분, TeacherID AS 코드번호, TName AS 이름, Tage AS 나이, TPhone_number AS 전화번호, TEmail AS 이메일, '' as 학번, subjectName 과목
    FROM TeacherDB t Left join subjectDB sbdb on t.subjectID = sbdb.subjectID) where 이름 = '오승호';

select * from
    (SELECT '학생' AS 구분, s.StudentID AS 코드번호, SName AS 이름, Sage AS 나이, SPhone_number AS 전화번호, SEmail AS 이메일, stnumber as 학번,
    LISTAGG(sj.SubjectName, ', ') WITHIN GROUP (ORDER BY ss.SubjectID) 과목 
    FROM StudentDB s Left JOIN StudentSubject ss ON s.StudentID = ss.StudentID 
    Left JOIN SubjectDB sj ON ss.SubjectID = sj.SubjectID 
    GROUP BY s.StudentID, s.SName, s.Sage, s.STNumber, s.SPhone_number, s.Semail
    UNION
    SELECT '교사' AS 구분, TeacherID AS 코드번호, TName AS 이름, Tage AS 나이, TPhone_number AS 전화번호, TEmail AS 이메일, '' as 학번, subjectName 과목
    FROM TeacherDB t join subjectDB sbdb on t.subjectID = sbdb.subjectID) where 과목 = 'JAVA' or 과목 like '%JAVA,%';
    
    
    
--// 사용자로부터 학생코드 & 교과명을 입력받아서 db에 넣기
--INSERT INTO StudentSubject(StudentID, SubjectID) 
--select 20002, SubjectID
--FROM SubjectDB 
--where SubjectName = 'PYTHON';
--
--// 사용자로부터 학생코드 & 교과명을 입력받아서 db에서 삭제
--DELETE FROM StudentSubject
--WHERE SubjectID IN (
--    SELECT SubjectID
--    FROM SubjectDB
--    WHERE SubjectName = 'JAVA'
--) AND StudentID = 20001;
