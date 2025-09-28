
-- Drop and recreate the database
DROP DATABASE IF EXISTS elibrary;
CREATE DATABASE elibrary;
USE elibrary;

-- Books table
CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(100) NOT NULL,
    genre VARCHAR(50) NOT NULL,
    copiesAvailable INT NOT NULL
);

-- Members table
CREATE TABLE members (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nic VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    password VARCHAR(100) NOT NULL
);

-- Librarians table
CREATE TABLE librarians (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userID VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    employeeID VARCHAR(50) NOT NULL
);

-- Borrowed books table
CREATE TABLE borrowed_books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    member_id INT NOT NULL,
    book_id INT NOT NULL,
    borrow_date DATE NOT NULL DEFAULT CURDATE(),
    returned BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (member_id) REFERENCES members(id) ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);

-- Sample data: books
INSERT INTO books (title, author, genre, copiesAvailable) VALUES
('Java Programming', 'James Gosling', 'Programming', 5),
('HTML & CSS Basics', 'Jennifer Robbins', 'Web Development', 3),
('Database Concepts', 'C.J. Date', 'Database', 2),
('Python for Beginners', 'Guido van Rossum', 'Programming', 4),
('Operating Systems', 'Andrew S. Tanenbaum', 'Computer Science', 1);

-- Sample member
INSERT INTO members (nic, name, phone, password) VALUES
('987654321V', 'Alice Perera', '0771234567', 'memberpass');

-- Sample librarian
INSERT INTO librarians (userID, name, password, phone, employeeID) VALUES
('lib001', 'Mr. Silva', 'librarianpass', '0712345678', 'EMP1001');
