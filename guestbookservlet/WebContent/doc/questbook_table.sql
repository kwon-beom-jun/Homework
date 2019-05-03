ALTER TABLE guestbook
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

DROP INDEX PK_guestbook;

/* 방명록 */
DROP TABLE guestbook 
	CASCADE CONSTRAINTS;

DROP SEQUENCE guestbook_seq;

CREATE SEQUENCE guestbook_seq
START WITH 1 INCREMENT BY 1;

/* 방명록 */
CREATE TABLE guestbook (
	seq NUMBER NOT NULL, /* 글번호 */
	name VARCHAR2(16), /* 작성자 */
	subject VARCHAR2(100), /* 제목 */
	content CLOB, /* 내용 */
	logtime DATE DEFAULT sysdate /* 작성일 */
);

COMMENT ON TABLE guestbook IS '방명록';

COMMENT ON COLUMN guestbook.seq IS '글번호';

COMMENT ON COLUMN guestbook.name IS '작성자';

COMMENT ON COLUMN guestbook.subject IS '제목';

COMMENT ON COLUMN guestbook.content IS '내용';

COMMENT ON COLUMN guestbook.logtime IS '작성일';

CREATE UNIQUE INDEX PK_guestbook
	ON guestbook (
		seq ASC
	);

ALTER TABLE guestbook
	ADD
		CONSTRAINT PK_guestbook
		PRIMARY KEY (
			seq
		);