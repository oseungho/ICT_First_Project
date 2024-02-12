--select *--st.sname, st.sage, st.stnumber, st.sphone_number, st.semail, sj.subjectname 
--from studentSubject stsj 
--join studentDB st on stsj.StudentID = st.StudentID
--join subjectdb sj on stsj.subjectID = sj.subjectID;

--//�л��� ��� �������� ��� ���(��� ����)
--SELECT s.SName sname, s.Sage sage, s.STNumber stnumber,
--        LISTAGG(sj.SubjectName, ', ') WITHIN GROUP (ORDER BY ss.SubjectID) subjectname,
--        TRUNC(AVG(score), 2) average FROM StudentDB s
--    JOIN StudentSubject ss ON s.StudentID = ss.StudentID
--    JOIN SubjectDB sj ON ss.SubjectID = sj.SubjectID
--    GROUP BY s.SName, s.Sage, s.STNumber;


--// �л��� ��� �������� ��� ���(�̸���, ��ȭ��ȣ ������)
--SELECT s.SName �̸�, s.Sage ����, s.STNumber �й�, 
--    LISTAGG(sj.SubjectName, ', ') WITHIN GROUP (ORDER BY ss.SubjectID) ��������
--    FROM StudentDB s
--    JOIN StudentSubject ss ON s.StudentID = ss.StudentID
--    JOIN SubjectDB sj ON ss.SubjectID = sj.SubjectID
--    GROUP BY s.SName, s.Sage, s.STNumber;
--
--// �л��� ��� �������� ��� ���(�̸���, ��ȭ��ȣ ����)
--SELECT s.SName �̸�, s.Sage ����, s.STNumber �й�, s.SPhone_number ��ȭ��ȣ, s.Semail �̸���,
--    LISTAGG(sj.SubjectName, ', ') WITHIN GROUP (ORDER BY ss.SubjectID) ��������
--FROM StudentDB s
--JOIN StudentSubject ss ON s.StudentID = ss.StudentID
--JOIN SubjectDB sj ON ss.SubjectID = sj.SubjectID
--GROUP BY s.SName, s.Sage, s.STNumber, s.SPhone_number, s.Semail;
--
--
--// Ư�� ������ ������ ��� �л� �̱�
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

//���� Data ��ȸ
--SELECT * FROM (
--SELECT TNAME �̸�, TAGE ����, TPHONE_NUMBER ��ȭ��ȣ, TEMAIL �̸���, sbdb.SubjectName ������ FROM TeacherDB tdb
--LEFT join subjectdb sbdb on tdb.SubjectID = sbdb.SubjectID);

// �л� Data ��ȸ
--SELECT s.SName �̸�, s.Sage ����, s.STNumber �й�, s.SPhone_number ��ȭ��ȣ, s.Semail �̸���,
--LISTAGG(sj.SubjectName, ', ') WITHIN GROUP (ORDER BY ss.SubjectID) �������� 
--FROM StudentDB s Left JOIN StudentSubject ss ON s.StudentID = ss.StudentID 
--Left JOIN SubjectDB sj ON ss.SubjectID = sj.SubjectID 
--GROUP BY s.SName, s.Sage, s.STNumber, s.SPhone_number, s.Semail;



// StudentDB�� TeacherDB�� ���� �����ִ� Data
with ALL_records as (
    select ROWNUM as �ڵ��ȣ, ����, �̸�, ����, ��ȭ��ȣ, �̸���, �й�, ���� from
    (SELECT '�л�' AS ����, SName AS �̸�, Sage AS ����, SPhone_number AS ��ȭ��ȣ, SEmail AS �̸���, stnumber as �й�,
    LISTAGG(sj.SubjectName, ', ') WITHIN GROUP (ORDER BY ss.SubjectID) ���� 
    FROM StudentDB s Left JOIN StudentSubject ss ON s.StudentID = ss.StudentID 
    Left JOIN SubjectDB sj ON ss.SubjectID = sj.SubjectID 
    GROUP BY s.SName, s.Sage, s.STNumber, s.SPhone_number, s.Semail
    UNION
    SELECT '����' AS ����, TName AS �̸�, Tage AS ����, TPhone_number AS ��ȭ��ȣ, TEmail AS �̸���, '' as �й�, subjectName ����
    FROM TeacherDB t join subjectDB sbdb on t.subjectID = sbdb.subjectID)
) select * from ALL_records where ���� = 'JAVA' or ���� like '%JAVA,%';

// StudentDB�� TeacherDB�� ���� �����ִ� Data


select * from
    (SELECT '�л�' AS ����, s.StudentID AS �ڵ��ȣ, SName AS �̸�, Sage AS ����, SPhone_number AS ��ȭ��ȣ, SEmail AS �̸���, stnumber as �й�,
    LISTAGG(sj.SubjectName, ', ') WITHIN GROUP (ORDER BY ss.SubjectID) ���� 
    FROM StudentDB s Left JOIN StudentSubject ss ON s.StudentID = ss.StudentID 
    Left JOIN SubjectDB sj ON ss.SubjectID = sj.SubjectID 
    GROUP BY s.StudentID, s.SName, s.Sage, s.STNumber, s.SPhone_number, s.Semail
    UNION
    SELECT '����' AS ����, TeacherID AS �ڵ��ȣ, TName AS �̸�, Tage AS ����, TPhone_number AS ��ȭ��ȣ, TEmail AS �̸���, '' as �й�, subjectName ����
    FROM TeacherDB t join subjectDB sbdb on t.subjectID = sbdb.subjectID) where �̸� = '����ȣ';

 SELECT '����' AS ����, TeacherID AS �ڵ��ȣ, TName AS �̸�, Tage AS ����, TPhone_number AS ��ȭ��ȣ, TEmail AS �̸���, '' as �й�, subjectName ����
    FROM TeacherDB t Left join subjectDB sbdb on t.subjectID = sbdb.subjectID) where �̸� = '����ȣ';

select * from
    (SELECT '�л�' AS ����, s.StudentID AS �ڵ��ȣ, SName AS �̸�, Sage AS ����, SPhone_number AS ��ȭ��ȣ, SEmail AS �̸���, stnumber as �й�,
    LISTAGG(sj.SubjectName, ', ') WITHIN GROUP (ORDER BY ss.SubjectID) ���� 
    FROM StudentDB s Left JOIN StudentSubject ss ON s.StudentID = ss.StudentID 
    Left JOIN SubjectDB sj ON ss.SubjectID = sj.SubjectID 
    GROUP BY s.StudentID, s.SName, s.Sage, s.STNumber, s.SPhone_number, s.Semail
    UNION
    SELECT '����' AS ����, TeacherID AS �ڵ��ȣ, TName AS �̸�, Tage AS ����, TPhone_number AS ��ȭ��ȣ, TEmail AS �̸���, '' as �й�, subjectName ����
    FROM TeacherDB t join subjectDB sbdb on t.subjectID = sbdb.subjectID) where ���� = 'JAVA' or ���� like '%JAVA,%';
    
    
    
--// ����ڷκ��� �л��ڵ� & �������� �Է¹޾Ƽ� db�� �ֱ�
--INSERT INTO StudentSubject(StudentID, SubjectID) 
--select 20002, SubjectID
--FROM SubjectDB 
--where SubjectName = 'PYTHON';
--
--// ����ڷκ��� �л��ڵ� & �������� �Է¹޾Ƽ� db���� ����
--DELETE FROM StudentSubject
--WHERE SubjectID IN (
--    SELECT SubjectID
--    FROM SubjectDB
--    WHERE SubjectName = 'JAVA'
--) AND StudentID = 20001;
