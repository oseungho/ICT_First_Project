//���� Data
INSERT INTO SubjectDB(SubjectName, Subject_STLimit, ID) values('JAVA',default,'OSH');
INSERT INTO SubjectDB(SubjectName, Subject_STLimit, ID) values('JAVASCRIPT',default,'OSH');
INSERT INTO SubjectDB(SubjectName, Subject_STLimit, ID) values('HTML',default,'OSH');
INSERT INTO SubjectDB(SubjectName, Subject_STLimit, ID) values('CSS',default,'OSH');
INSERT INTO SubjectDB(SubjectName, Subject_STLimit, ID) values('PYTHON',default,'OSH');
INSERT INTO SubjectDB(SubjectName, Subject_STLimit, ID) values('R',default,'OSH');

//���� DATA
INSERT INTO TeacherDB(TName, Tage, SubjectID, TPhone_number, Temail, ID) values ('���ڹ�', 40, 1001, '010-1111-2222', 'gajava@academy.osh', 'OSH');
INSERT INTO TeacherDB(TName, Tage, SubjectID, TPhone_number, Temail, ID) values ('���ڽ�', 42, 1002, '010-2222-2222', 'najavascript@academy.osh', 'OSH');
INSERT INTO TeacherDB(TName, Tage, SubjectID, TPhone_number, Temail, ID) values ('����Ʈ', 44, 1003, '010-3333-2222', 'dahtml@academy.osh', 'OSH');
INSERT INTO TeacherDB(TName, Tage, SubjectID, TPhone_number, Temail, ID) values ('�󾾽�', 46, 1004, '010-4444-2222', 'racass@academy.osh', 'OSH');
INSERT INTO TeacherDB(TName, Tage, SubjectID, TPhone_number, Temail, ID) values ('���̽�', 48, 1005, '010-5555-2222', 'mapython@academy.osh', 'OSH');
INSERT INTO TeacherDB(TName, Tage, SubjectID, TPhone_number, Temail, ID) values ('�پ˽�', 50, 1006, '010-6666-2222', 'barstudio@academy.osh', 'OSH');
INSERT INTO TeacherDB(TName, Tage, SubjectID, TPhone_number, Temail, ID) values ('���ڹ�', 52, 1001, '010-7777-2222', 'sajava@academy.osh', 'OSH');

//�л� Data
INSERT INTO StudentDB(SName, Sage, STNumber, SPhone_number, Semail, ID) VALUES('���л�', 26, '2017�й�','010-1111-111', 'gastudent@naver.com', 'OSH');
INSERT INTO StudentDB(SName, Sage, STNumber, SPhone_number, Semail, ID) VALUES('���л�', 24, '2019�й�','010-1111-1111', 'nastudent@naver.com', 'OSH');
INSERT INTO StudentDB(SName, Sage, STNumber, SPhone_number, Semail, ID) VALUES('���л�', 26, '2017�й�','010-1111-1111', 'dastudent@naver.com', 'OSH');
INSERT INTO StudentDB(SName, Sage, STNumber, SPhone_number, Semail, ID) VALUES('���л�', 22, '2021�й�','010-1234-1111', 'rastudent@naver.com', 'OSH');
INSERT INTO StudentDB(SName, Sage, STNumber, SPhone_number, Semail, ID) VALUES('���л�', 28, '2015�й�','010-1234-1111', 'mastudent@naver.com', 'OSH');
INSERT INTO StudentDB(SName, Sage, STNumber, SPhone_number, Semail, ID) VALUES('���л�', 21, '2022�й�','010-1234-1111', 'bastudent@naver.com', 'OSH');
INSERT INTO StudentDB(SName, Sage, STNumber, SPhone_number, Semail, ID) VALUES('���л�', 20, '2023�й�','010-1234-1111', 'sastudent@naver.com', 'OSH');
INSERT INTO StudentDB(SName, Sage, STNumber, SPhone_number, Semail, ID) VALUES('���л�', 24, '2019�й�','010-1234-1111', 'oastudent@naver.com', 'OSH');
INSERT INTO StudentDB(SName, Sage, STNumber, SPhone_number, Semail, ID) VALUES('���л�', 26, '2017�й�','010-1234-1111', 'jastudent@naver.com', 'OSH');
INSERT INTO StudentDB(SName, Sage, STNumber, SPhone_number, Semail, ID) VALUES('���л�', 29, '2014�й�','010-1234-1111', 'chastudent@naver.com', 'OSH');
INSERT INTO StudentDB(SName, Sage, STNumber, SPhone_number, Semail, ID) VALUES('ī�л�', 29, '2014�й�','010-1234-1111', 'castudent@naver.com', 'OSH');
INSERT INTO StudentDB(SName, Sage, STNumber, SPhone_number, Semail, ID) VALUES('Ÿ�л�', 26, '2017�й�','010-1234-1111', 'tastudent@naver.com', 'OSH');
INSERT INTO StudentDB(SName, Sage, STNumber, SPhone_number, Semail, ID) VALUES('���л�', 24, '2019�й�','010-1234-1111', 'pastudent@naver.com', 'OSH');
INSERT INTO StudentDB(SName, Sage, STNumber, SPhone_number, Semail, ID) VALUES('���л�', 26, '2017�й�','010-1234-1111', 'hastudent@naver.com', 'OSH');

//���� Data
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20001, 1001, '87');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20001, 1002, '77');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20001, 1005, '100');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20002, 1001, '87');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20002, 1002, '77');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20002, 1006, '100');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20003, 1002, '87');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20003, 1003, '77');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20003, 1004, '100');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20004, 1004, '87');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20004, 1001, '77');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20004, 1003, '100');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20005, 1004, '87');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20005, 1001, '77');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20005, 1005, '100');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20006, 1001, '87');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20006, 1002, '77');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20006, 1005, '100');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20007, 1003, '100');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20007, 1004, '60');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20007, 1005, '75');

INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20008, 1001, '87');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20008, 1002, '77');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20008, 1005, '100');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20009, 1001, '87');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20009, 1002, '77');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20009, 1006, '100');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20010, 1002, '87');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20010, 1003, '77');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20010, 1004, '100');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20011, 1001, '87');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20011, 1003, '77');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20011, 1006, '100');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20012, 1002, '87');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20012, 1003, '70');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20012, 1004, '90');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20013, 1001, '81');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20013, 1005, '72');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20013, 1002, '61');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20014, 1002, '77');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20014, 1003, '97');
INSERT INTO StudentSubject(StudentID, SubjectID, score) VALUES(20014, 1004, '60');
COMMIT;