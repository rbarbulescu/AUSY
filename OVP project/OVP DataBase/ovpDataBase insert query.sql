USE ovpDataBase;

INSERT INTO user(userName, password)
VALUES ("username1", "password1"),
	("username2", "password2"),
    ("username3", "password3"),
    ("username4", "password4");

INSERT INTO user_type (userId, userType)
VALUES (1, "admin"),
	(2, "student"),
	(3, "student"),
    (4, "admin");