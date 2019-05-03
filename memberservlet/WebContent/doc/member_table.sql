ALTER TABLE member_detail
   DROP
      CONSTRAINT FK_member_TO_member_detail
      CASCADE;

ALTER TABLE member
   DROP
      PRIMARY KEY
      CASCADE
      KEEP INDEX;

DROP INDEX member_id_pk;

/* ȸ���⺻ */
DROP TABLE member 
   CASCADE CONSTRAINTS;

/* ȸ���� */
DROP TABLE member_detail 
   CASCADE CONSTRAINTS;

/* ȸ���⺻ */
CREATE TABLE member (
   id VARCHAR2(16) NOT NULL, /* ���̵� */
   name VARCHAR2(30) NOT NULL, /* �̸� */
   pass VARCHAR2(16) NOT NULL, /* ��й�ȣ */
   emailid VARCHAR2(16), /* �̸��Ͼ��̵� */
   emaildomain VARCHAR2(30), /* �̸��ϵ����� */
   joindate DATE DEFAULT sysdate /* ������ */
);

COMMENT ON TABLE member IS 'ȸ���⺻';

COMMENT ON COLUMN member.id IS '���̵�';

COMMENT ON COLUMN member.name IS '�̸�';

COMMENT ON COLUMN member.pass IS '��й�ȣ';

COMMENT ON COLUMN member.emailid IS '�̸��Ͼ��̵�';

COMMENT ON COLUMN member.emaildomain IS '�̸��ϵ�����';

COMMENT ON COLUMN member.joindate IS '������';

CREATE UNIQUE INDEX member_id_pk
   ON member (
      id ASC
   );

ALTER TABLE member
   ADD
      CONSTRAINT member_id_pk
      PRIMARY KEY (
         id
      );

/* ȸ���� */
CREATE TABLE member_detail (
   id VARCHAR2(16), /* ���̵� */
   zipcode VARCHAR2(5), /* �����ȣ */
   address VARCHAR2(100), /* �Ϲ��ּ� */
   address_detail VARCHAR(100), /* ���ּ� */
   tel1 VARCHAR2(3), /* ��ȭ��ȣ1 */
   tel2 VARCHAR2(4), /* ��ȭ��ȣ2 */
   tel3 VARCHAR2(4) /* ��ȭ��ȣ3 */
);

COMMENT ON TABLE member_detail IS 'ȸ����';

COMMENT ON COLUMN member_detail.id IS '���̵�';

COMMENT ON COLUMN member_detail.zipcode IS '�����ȣ';

COMMENT ON COLUMN member_detail.address IS '�Ϲ��ּ�';

COMMENT ON COLUMN member_detail.address_detail IS '���ּ�';

COMMENT ON COLUMN member_detail.tel1 IS '��ȭ��ȣ1';

COMMENT ON COLUMN member_detail.tel2 IS '��ȭ��ȣ2';

COMMENT ON COLUMN member_detail.tel3 IS '��ȭ��ȣ3';

ALTER TABLE member_detail
   ADD
      CONSTRAINT FK_member_TO_member_detail
      FOREIGN KEY (
         id
      )
      REFERENCES member (
         id
      );