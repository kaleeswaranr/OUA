USE TODO_MGR;

DROP TABLE IF EXISTS Activity;

CREATE TABLE Activity(
	id int(11) unsigned NOT NULL AUTO_INCREMENT,
	description VARCHAR(100) NOT NULL,
	status VARCHAR(30) NULL,
	delFlag CHAR(1) NULL,
	modifiedBy VARCHAR(30) NULL, 
	modifiedDate TIMESTAMP NULL,
	createdBy VARCHAR(30) NULL, 
	createdDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
