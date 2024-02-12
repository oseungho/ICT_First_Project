
--CREATE TABLE SHADMIN(
--    ID VARCHAR(15) PRIMARY KEY, -- ���̵�
--    USERNAME VARCHAR(20) NOT NULL, -- ���� �̸�
--    PASSWORD VARCHAR(15) NOT NULL -- ��й�ȣ
--);
--INSERT INTO SHADMIN(ID, USERNAME, PASSWORD) VALUES('OSH', '����ȣ', 'OSH');

//���� DB ����
CREATE SEQUENCE Subject_sequence
  START WITH 1001
  INCREMENT BY 1
  NOCACHE;

CREATE TABLE SubjectDB(
    SubjectID NUMBER default Subject_sequence.NEXTVAL PRIMARY KEY, -- ������ȣ
    SubjectName VARCHAR(20) not null, -- ������
    Subject_STLimit number(2) default 10,-- �ѿ�
    ID REFERENCES SHADMIN(ID) not null,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- ��������
);

//���� DB ����
CREATE SEQUENCE Teacher_sequence
  START WITH 10001
  INCREMENT BY 1
  NOCACHE;

CREATE TABLE TeacherDB(
    TeacherID NUMBER default Teacher_sequence.NEXTVAL PRIMARY KEY, -- ����ID
    TName Varchar(20) not null,
    Tage number(3),
    TPhone_number VARCHAR(15) default null,
    Temail VARCHAR(25) default null,
    SubjectID REFERENCES SubjectDB(SubjectID), -- 
    ID REFERENCES SHADMIN(ID) not null, -- �ۼ���ID
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- ��������
);
ALTER TABLE TeacherDB ADD CONSTRAINT UC_TeacherDB UNIQUE (TeacherID, SubjectID);

//�л� DB ����

CREATE SEQUENCE Student_sequence
  START WITH 20001
  INCREMENT BY 1
  NOCACHE;

CREATE TABLE StudentDB(
--    StudentID NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, -- �л�ID
    StudentID NUMBER default Student_sequence.NEXTVAL PRIMARY KEY,
    SName Varchar(20) not null, -- �л��̸�
    Sage number(3), -- �л�����
    SPhone_number VARCHAR(15) default null, -- �л���ȭ��ȣ
    Semail VARCHAR(25) default null, -- �л� ����
    STNumber VARCHAR(20), -- �й�
    ID REFERENCES SHADMIN(ID) not null, -- �ۼ���ID
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- ��������
);

//�л� �������� DB ����
CREATE TABLE StudentSubject (
    StudentSubjectID NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, -- �л��� ���� ���� ID
    StudentID NUMBER REFERENCES StudentDB(StudentID) ON DELETE CASCADE not null , -- �л�ID
    SubjectID NUMBER REFERENCES SubjectDB(SubjectID) not null, -- ����ID
    score number(3)
);
-- StudentSubject ���̺����� StudentID�� SubjectID ���տ� ����ũ ���� �߰�
ALTER TABLE StudentSubject
ADD CONSTRAINT UC_StudentSubject_StudentID_SubjectID UNIQUE (StudentID, SubjectID);


--drop table studentSubject;
--drop table studentDB;
--drop table TeacherDB;
--drop table subjectDB;
--drop SEQUENCE subject_sequence;
--drop SEQUENCE Teacher_sequence;
--drop SEQUENCE Student_sequence;