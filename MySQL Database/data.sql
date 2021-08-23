CREATE TABLE userAccounts (
Firstname varchar(50) NOT NULL,
Lastname varchar(50) NOT NULL, 
Email varchar(50) NOT NULL,
Phone varchar(50) NOT NULL,
Username varchar(50) NOT NULL,
Pwd varchar(200) NOT NULL,
Role varchar(50) DEFAULT "PUBLIC",
PRIMARY KEY (Username, Pwd)
);

INSERT INTO userAccounts VALUES
('Sara','Fletcher','sara.fletcher@myemail.co.uk','44-0191-5678901','sarafletcher','sFletch123');

INSERT INTO userAccounts VALUES
('admin','admin','admin@admin','44-0191-5678901','admin','42f749ade7f9e195bf475f37a44cafcb','ADMIN');

CREATE TABLE adminNumbers (
Id INT AUTO_INCREMENT,
Numbers varchar(50) NOT NULL, 
PRIMARY KEY (Id)
);


