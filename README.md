CREATE TABLE Bus ( id INT AUTO_INCREMENT PRIMARY KEY, immatriculation VARCHAR(20) NOT NULL UNIQUE,
nombre_places INT NOT NULL CHECK (nombre_places > 0));

CREATE TABLE Etudiant (id INT AUTO_INCREMENT PRIMARY KEY,nom VARCHAR(50) NOT NULL,
prenom VARCHAR(50) NOT NULL,email VARCHAR(100) NOT NULL UNIQUE);

CREATE TABLE AbonnementTransport ( id INT AUTO_INCREMENT PRIMARY KEY,bus_id INT NOT NULL,
 etudiant_id INT NOT NULL,date_abonnement DATE NOT NULL,
 FOREIGN KEY (bus_id) REFERENCES Bus(id) ,
 FOREIGN KEY (etudiant_id) REFERENCES Etudiant(id) ,UNIQUE(bus_id, etudiant_id) );