

-- This is the main table for users, when creating a new user, they must enter in all fields except for name (not required)
-- Role is what will store the different authority levels, ex. USER, MOD, ADMIN, LOCKEDUSER
CREATE TABLE user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL,
    profession VARCHAR(355) NOT NULL,
    language VARCHAR(355) NOT NULL,
    profile_picture VARCHAR(355) NOT NULL
);


CREATE TABLE post (
    post_id INT AUTO_INCREMENT,
    user_id INT REFERENCES user(id),
    visual VARCHAR(335),
    body VARCHAR(355),
    like_count INT,
    created_at TIMESTAMP,  
    PRIMARY KEY (post_id, user_id)
);

CREATE TABLE comment (
 	post_id INT REFERENCES post(post_id),
    user_id INT REFERENCES user(id),
    body VARCHAR(355) NOT NULL,
    created_at TIMESTAMP,
    PRIMARY KEY (post_id, user_id)
);

CREATE TABLE project (
    project_id INT AUTO_INCREMENT,
    user_id INT REFERENCES user(id),
    name VARCHAR(50),
    classification VARCHAR(355),
    body VARCHAR(355),
    timespent VARCHAR(355),
    status VARCHAR(355),
    visual VARCHAR(355),
    visual2 VARCHAR(355),
	PRIMARY KEY (project_id, user_id)
);   

CREATE TABLE follows (
 	user_id_followed INT REFERENCES user(id),
    user_id_following INT REFERENCES user(id),
    PRIMARY KEY (user_id_followed, user_id_following)
);

CREATE TABLE report (
    user_id_reported INT REFERENCES user(id),
    user_id_author INT REFERENCES user(id),
    created_at TIMESTAMP,
    body VARCHAR(355),
    PRIMARY KEY (user_id_reported, user_id_author, created_at)
);

-- This will insert 4 users into the user table manually, later we will implement thymeleaf functionality to create users from the register page
INSERT INTO user (name, username, password, role, profession, language, profile_picture)
VALUES ('Yomomma', 'testuser', 'test', 'USER', 'Developer', 'English', '/images/profile1.jpg');

INSERT INTO user (name, username, password, role, profession, language, profile_picture)
VALUES ('Yomommasmod', 'testmod', 'test', 'MOD', 'Artist', 'English', '/images/profile2.jpg');

INSERT INTO user (name, username, password, role, profession, language, profile_picture)
VALUES ('Yomommasadmin', 'testadmin', 'test', 'ADMIN', 'Musician', 'English', '/images/profile1.jpg');

INSERT INTO user (name, username, password, role, profession, language, profile_picture)
VALUES ('Yomommaslocked', 'testlocked', 'test', 'LOCKEDUSER', 'Musician', 'English', '/images/profile2.jpg');
