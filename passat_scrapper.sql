DROP DATABASE IF EXISTS passat_scraper;
CREATE DATABASE passat_scraper DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci; 
USE passat_scraper;
    
CREATE TABLE passat (
	passat_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`name` varchar(200) NOT NULL,
    price INT(11) NOT NULL,
    mileage INT(11) NOT NULL,
    publish_date DATETIME NOT NULL
    );