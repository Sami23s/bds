-- MySQL Workbench Forward Engineering


-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

CREATE SCHEMA IF NOT EXISTS bds;

CREATE TABLE IF NOT EXISTS login
(
    email text,
    pwd text
);

CREATE TABLE IF NOT EXISTS bds.dummy
(
    person_id integer,
    first_name character varying(20),
    last_name character varying(20),
    nickname character varying(20),
    email character varying(20)
);


-- -----------------------------------------------------
-- Table bds.type_of_membership
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bds.type_of_membership (
   id_type_of_membership	SERIAL 	NOT NULL 	PRIMARY KEY,
   membership_name 		VARCHAR(45) NOT NULL,
   price 				INT 		NOT NULL
   );


-- -----------------------------------------------------
-- Table bds.membership_card
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bds.membership_card (
   id_membership_card 	SERIAL 	NOT NULL 		PRIMARY KEY,
   start_of_membership 	DATE 		NOT NULL,
   end_of_membership  	DATE 		NOT NULL,
   last_visited  		TIMESTAMP,
   type_of_membership  	SERIAL	NOT NULL,
   hours_spend			DECIMAL,
   FOREIGN KEY (type_of_membership) REFERENCES type_of_membership (id_type_of_membership)
	);


-- -----------------------------------------------------
-- Table bds.address
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bds.address (
   id_address 	SERIAL 	NOT NULL 	PRIMARY KEY,
   city  		VARCHAR(20) NOT NULL,
   street 		VARCHAR(20) NOT NULL,
   number 		INT 		NOT NULL,
   postal_code 	INT 		NOT NULL
 	);


-- -----------------------------------------------------
-- Table bds.contact
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bds.contact (
   id_contact 	SERIAL 	NOT NULL 	PRIMARY KEY,
   telephone 	VARCHAR(20) NOT NULL,
   email VARCHAR(45) NOT NULL,
   instagram VARCHAR(45)
 	);


-- -----------------------------------------------------
-- Table bds.member
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bds.member (
   id_member  		SERIAL 	NOT NULL 	PRIMARY KEY,
   first_name 		VARCHAR(20) NOT NULL,
   last_name 		VARCHAR(20) NOT NULL,
   birthday 		DATE 		NOT NULL,
   weight         SERIAL   NOT NULL,
   address 			SERIAL 	NOT NULL,
   membership_card_id 	SERIAL 	NOT NULL,
   id_contact 		SERIAL 	NOT NULL,
  FOREIGN KEY (address) REFERENCES address (id_address),
  FOREIGN KEY (membership_card_id) REFERENCES membership_card (id_membership_card),
  FOREIGN KEY (id_contact) REFERENCES contact (id_contact)
	);


-- -----------------------------------------------------
-- Table bds.owner
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bds.owner (
   id_owner  	SERIAL 	NOT NULL 	PRIMARY KEY,
   first_name 	VARCHAR(20) NOT NULL,
   last_name 	VARCHAR(20) NOT NULL
  	);


-- -----------------------------------------------------
-- Table bds.gym
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bds.gym (
   id_gym 		SERIAL 	NOT NULL 	PRIMARY KEY,
   address 		SERIAL 	NOT NULL,
   gym_name 	VARCHAR(20) NOT NULL,
   contact 		SERIAL	NOT NULL,
   owner 		SERIAL 	NOT NULL,
  FOREIGN KEY (address) REFERENCES address (id_address),
  FOREIGN KEY (contact) REFERENCES contact (id_contact),
  FOREIGN KEY (owner)   REFERENCES owner   (id_owner)
	);
-- -----------------------------------------------------
-- Table bds.salary
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bds.salary (
   id_salary  	SERIAL 	NOT NULL 	PRIMARY KEY,
   amount 		INT		 NOT NULL,
   currency 	VARCHAR(20) NOT NULL
  	);

-- -----------------------------------------------------
-- Table bds.working_hour
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bds.working_hour (
  id_working_hour 		SERIAL 		NOT NULL 	PRIMARY KEY,
  start_working_hour 	TIME 		NOT NULL,
  end_working_hour 		TIME 		NOT NULL
);

-- -----------------------------------------------------
-- Table bds.position
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bds.position (
	id_position  		SERIAL 	NOT NULL 	PRIMARY KEY,
	position_name  	VARCHAR(45) NOT NULL,
	salary 			SERIAL 		NOT NULL,
	type_of_employment 	VARCHAR(45) NOT NULL,
	working_hour 		SERIAL 		NOT NULL,
	description 		VARCHAR(45),
	FOREIGN KEY (salary) REFERENCES salary (id_salary),
	FOREIGN KEY (working_hour) REFERENCES working_hour (id_working_hour)
  	);


-- -----------------------------------------------------
-- Table bds.employee
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bds.employee (
   id_employee  		SERIAL 	NOT NULL 	PRIMARY KEY,
   first_name 		VARCHAR(20) NOT NULL,
   last_name 		VARCHAR(20) NOT NULL,
   id_position 		SERIAL 	NOT NULL,
  FOREIGN KEY (id_position) REFERENCES position (id_position)
	);


-- -----------------------------------------------------
-- Table bds.supplier
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bds.supplier (
   id_supplier 	SERIAL	NOT NULL 		PRIMARY KEY,
   supp_name 	VARCHAR(30) NOT NULL,
   deliv_time 	DATE 	NOT NULL,
   address 		SERIAL 	NOT NULL,
   supp_type 	VARCHAR(30) NOT NULL,
 FOREIGN KEY (address) REFERENCES address (id_address)
  	);


-- -----------------------------------------------------
-- Table bds.member_access_gym
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bds.member_access_gym (
   has_access  	BOOLEAN 	NOT NULL,
   id_member	SERIAL	NOT NULL,
   id_gym 		SERIAL 	NOT NULL,
  FOREIGN KEY (id_member) REFERENCES member (id_member),
  FOREIGN KEY (id_gym)	  REFERENCES gym	  (id_gym)
	);


-- -----------------------------------------------------
-- Table bds.order
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bds.order (
   id_order 		SERIAL 	NOT NULL PRIMARY KEY,
   id_member 		SERIAL	NOT NULL,
   date_created 		DATE 		NOT NULL,
   expected_delivery 	DATE 		NOT NULL,
   status 			VARCHAR(45) NOT NULL,
   discount 		DECIMAL 	NOT NULL,
   FOREIGN KEY (id_member) REFERENCES member (id_member)
	);


-- -----------------------------------------------------
-- Table bds.quantity
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bds.quantity (
   id_quantity  		SERIAL 	NOT NULL 	PRIMARY KEY,
   amount 				DECIMAL NOT NULL,
   unit	 				VARCHAR(10) 	NOT NULL
  	);
    
    
    -- -----------------------------------------------------
-- Table bds.price
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bds.price (
   id_price  			SERIAL 	NOT NULL 	PRIMARY KEY,
   amount 				INT NOT NULL,
   currency	 				VARCHAR(10) 	NOT NULL
  	);
    
 

-- -----------------------------------------------------
-- Table bds.product
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bds.product (
   id_product  		SERIAL 	NOT NULL 	PRIMARY KEY,
   product_name 		VARCHAR(45) NOT NULL,
   quantity 		SERIAL 		NOT NULL,
   product_type 		VARCHAR(45) NOT NULL,
   is_in_stock 		BOOLEAN 	NOT NULL,
   price			SERIAL		NOT NULL,
   FOREIGN KEY (quantity) REFERENCES quantity (id_quantity),
   FOREIGN KEY (price) REFERENCES price (id_price)
  	);


-- -----------------------------------------------------
-- Table bds.member_login
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bds.member_login (
   id_member 	SERIAL 	NOT NULL 	PRIMARY KEY,
   password 	VARCHAR(25) NOT NULL,
  FOREIGN KEY (id_member) REFERENCES member (id_member)
	);


-- -----------------------------------------------------
-- Table bds.product_has_order
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bds.product_has_order (
   id_product 	SERIAL 	NOT NULL,
   id_order 	SERIAL 	NOT NULL,
  FOREIGN KEY (id_product) REFERENCES product (id_product),
  FOREIGN KEY (id_order)   REFERENCES public.order   (id_order)
	);


-- -----------------------------------------------------
-- Table bds.gym_has_supplier
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bds.gym_has_supplier (
   id_gym	 	SERIAL 	NOT NULL,
   id_supplier 	SERIAL 	NOT NULL,
  FOREIGN KEY (id_gym) 		REFERENCES gym 		(id_gym),
  FOREIGN KEY (id_supplier) 	REFERENCES supplier	(id_supplier)
	);


-- -----------------------------------------------------
-- Table bds.member_has_instructor
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bds.member_has_instructor (
   id_member  		SERIAL 	NOT NULL,
   id_employee  		SERIAL 	NOT NULL,
   instructor_exists 	BOOLEAN 	NOT NULL,
  FOREIGN KEY (id_member) 	REFERENCES member 	(id_member),
  FOREIGN KEY (id_employee) 	REFERENCES employee	(id_employee)
	);


-- -----------------------------------------------------
-- Table bds.gym_has_employee
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bds.gym_has_employee (
   id_gym  		SERIAL 	NOT NULL, 
   id_employee 	SERIAL 	NOT NULL,
  FOREIGN KEY (id_gym) 		REFERENCES gym 		(id_gym),
  FOREIGN KEY (id_employee)	REFERENCES employee	(id_employee)
	)



