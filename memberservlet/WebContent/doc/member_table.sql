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

/* 회원기본 */
DROP TABLE member 
   CASCADE CONSTRAINTS;

/* 회원상세 */
DROP TABLE member_detail 
   CASCADE CONSTRAINTS;

/* 회원기본 */
CREATE TABLE member (
   id VARCHAR2(16) NOT NULL, /* 아이디 */
   name VARCHAR2(30) NOT NULL, /* 이름 */
   pass VARCHAR2(16) NOT NULL, /* 비밀번호 */
   emailid VARCHAR2(16), /* 이메일아이디 */
   emaildomain VARCHAR2(30), /* 이메일도메인 */
   joindate DATE DEFAULT sysdate /* 가입일 */
);

COMMENT ON TABLE member IS '회원기본';

COMMENT ON COLUMN member.id IS '아이디';

COMMENT ON COLUMN member.name IS '이름';

COMMENT ON COLUMN member.pass IS '비밀번호';

COMMENT ON COLUMN member.emailid IS '이메일아이디';

COMMENT ON COLUMN member.emaildomain IS '이메일도메인';

COMMENT ON COLUMN member.joindate IS '가입일';

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

/* 회원상세 */
CREATE TABLE member_detail (
   id VARCHAR2(16), /* 아이디 */
   zipcode VARCHAR2(5), /* 우편번호 */
   address VARCHAR2(100), /* 일반주소 */
   address_detail VARCHAR(100), /* 상세주소 */
   tel1 VARCHAR2(3), /* 전화번호1 */
   tel2 VARCHAR2(4), /* 전화번호2 */
   tel3 VARCHAR2(4) /* 전화번호3 */
);

COMMENT ON TABLE member_detail IS '회원상세';

COMMENT ON COLUMN member_detail.id IS '아이디';

COMMENT ON COLUMN member_detail.zipcode IS '우편번호';

COMMENT ON COLUMN member_detail.address IS '일반주소';

COMMENT ON COLUMN member_detail.address_detail IS '상세주소';

COMMENT ON COLUMN member_detail.tel1 IS '전화번호1';

COMMENT ON COLUMN member_detail.tel2 IS '전화번호2';

COMMENT ON COLUMN member_detail.tel3 IS '전화번호3';

ALTER TABLE member_detail
   ADD
      CONSTRAINT FK_member_TO_member_detail
      FOREIGN KEY (
         id
      )
      REFERENCES member (
         id
      );