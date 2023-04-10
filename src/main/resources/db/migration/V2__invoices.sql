CREATE TABLE Invoices
(
    increment_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    id NVARCHAR(6) NOT NULL,
    user_id INTEGER,
    created_at DATE,
    payment_due DATE,
    description NVARCHAR(256),
    payment_terms INTEGER,
    client_name NVARCHAR(256),
    client_email NVARCHAR(256),
    status NVARCHAR(30),
    sender_id INTEGER,
    client_id INTEGER,
    total DOUBLE,
    foreign key (sender_id) references addresses(id),
    foreign key (client_id) references addresses(id)
);