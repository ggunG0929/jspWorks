-- user: jweb인 계정 생성
create user jweb@localhost identified by 'jweb';

-- db 생성
create database javaweb;

-- db 권한 주기
grant all privileges on javaweb.* to jweb@localhost with grant option;


use javaweb;

-- product 테이블 생성
CREATE TABLE product(
	p_id 	VARCHAR(10) PRIMARY KEY,
    p_name	VARCHAR(30),
    p_unitPrice		INTEGER,
    p_description 	TEXT,
    p_category		VARCHAR(20),
    p_manufacturer	VARCHAR(20),
    p_unitsInStock	LONG,
    p_condition		VARCHAR(20),
    p_productImage	VARCHAR(20)
);

-- 상품 추가
INSERT INTO product VALUES ('P1234', 'iPhone 6s', 1200000, '1334X750 Renia HD Display', 'Smart Phone', 'Apple', 1000, 'New', 'P1234.png');

select * from product;

select * from product where p_id = 'P1234';

-- member 테이블 생성
create table member(
	mid			varchar(20),
    passwd		varchar(20) not null,
    mname		varchar(30) not null,
    gender		varchar(10),
    birth		varchar(20),
    email		varchar(30),
    phone		varchar(20),
    address	varchar(50),
    regDate		timestamp default now(),
    primary key(mid)
);

insert into member(mid, passwd, mname, gender, birth, email, phone, address)
 values('cloud', 'm123456#', '구름이', '여', '1995/12/1', 'cloud@sky.com', '010/1234/5678', '서울시 강남구 청담동');

 select * from member;