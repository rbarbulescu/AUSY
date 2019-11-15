INSERT INTO admins(adminId, institution)
VALUES ("admin1", "UMFCV"),
	("admin2", "ACE"),
    ("admin3", "UCV");
    
INSERT INTO persons(email, username, password, typeofuser, adminsId)
VALUES ("bnb@gmail.com", "123321", "qwerty", "student", "admin1"),
		("sad@gmail.com", "190234", "qwerty", "student", "admin1"),
        ("happy@yahoo.com", "username", "password", "student", "admin1"),
        ("email@google.com", "uniqueUsername", "qwerty", "student", "admin2"),
        ("sali@gmail.com", "190234123", "qwerty", "student", "admin2"),
        ("rmarius@gmail.com", "147258369", "qwerty", "student", "admin3"),
        ("bbartu@gmail.com", "123456", "password", "student", "admin3"),
        ("c.Hartita@yahoo.com", "456789", "password", "student", "admin2");
        
INSERT INTO students(firstname, lastname, phone, address, CNP, birthday, trips, personsId)
VALUES ("Barbu", "Florin", "0730234567", "address 1", "123456789", 19900203, 1, "123321"),
	("Mincu", "Bogdan", "0725123456", "Craiova", "789456", 1890212, 1, "190234"),
    ("barbu", "Florin", "0725145632", "Bucuresti, Sector2", "1950523160025", 19921012, 1, "username"),
    ("Razvan", "Barbulescu", "0730217022", "Craiova", "1950524160025", 19950524, 1, "uniqueUsername"),
    ("Mihai", "Ionescu", "0730234567", "Bailesti, Dolj", "1234567890", 19980812, 1, "190234123");
    