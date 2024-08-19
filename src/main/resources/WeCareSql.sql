create database WeCare;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(50) NOT NULL UNIQUE,
    username VARCHAR(25) NOT NULL,
    password VARCHAR(20) NOT NULL
);

CREATE TABLE appointments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    life_coach_id INT,
    appointment_time DATETIME,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (life_coach_id) REFERENCES life_coaches(id) ON DELETE CASCADE
);

CREATE TABLE life_coaches (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(25) NOT NULL,
    specialty VARCHAR(50) NOT NULL,
    email VARCHAR(25) NOT NULL UNIQUE,
    password VARCHAR(20) NOT NULL
);

