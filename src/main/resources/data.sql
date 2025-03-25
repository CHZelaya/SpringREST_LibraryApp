-- Insert into Author table
INSERT INTO author (name, biography) VALUES
('J.K. Rowling', 'British author, best known for the Harry Potter series.'),
('George R.R. Martin', 'American novelist and short story writer, famous for "A Song of Ice and Fire" series.'),
('J.R.R. Tolkien', 'English writer, best known for "The Lord of the Rings" and "The Hobbit."'),
('Agatha Christie', 'English writer, known for her detective novels, particularly those featuring Hercule Poirot and Miss Marple.');


-- -- Insert into Book table
INSERT INTO book (title, isbn, publication_year) VALUES
('Harry Potter and the Sorcerers Stone', '978-0747532743', 1997),
('A Game of Thrones', '978-0553103540', 1996),
('The Hobbit', '978-0261103283', 1937),
('Murder on the Orient Express', '978-0062073501', 1934);
-- ON CONFLICT (title, isbn, publication_year) DO NOTHING;
--
-- Insert into membership_card table (generate card_number field and populate)
INSERT INTO membership_card (card_number, issue_date, expiry_date) VALUES
('g7h2k-9j8lq','2021-04-15', '2022-04-15'),
('a3b5c-1d2e3','2020-07-30', '2021-07-30'),
('x9y8z-4w5v6','2022-11-25', '2023-11-25'),
('m4n8p-2q1r3','2019-06-10', '2020-06-10');
-- ON CONFLICT (card_number, issue_date, expiry_date) DO NOTHING;

-- Insert into LibraryMember table
-- Now includes 'membership_card_id' to link to the MembershipCard table
INSERT INTO library_member (name, email, membership_date, membership_card_id) VALUES
('Alice Johnson', 'alice.johnson@example.com', '2021-04-15', 1),  -- Linking to MembershipCard with ID 1
('Bob Smith', 'bob.smith@example.com', '2020-07-30', 2),          -- Linking to MembershipCard with ID 2
('Charlie Brown', 'charlie.brown@example.com', '2022-11-25', 3),  -- Linking to MembershipCard with ID 3
('David Wilson', 'david.wilson@example.com', '2019-06-10', 4);  -- Linking to MembershipCard with ID 4
-- ON CONFLICT (name, email, membership_date, membership_card_id) DO NOTHING;
-- -- Insert into Book-Author relationship table (Many-to-Many)
INSERT INTO book_author (book_id, author_id) VALUES
(1, 1),  -- 'Harry Potter and the Sorcerer's Stone' linked with 'J.K. Rowling'
(2, 2),  -- 'A Game of Thrones' linked with 'George R.R. Martin'
(3, 3),  -- 'The Hobbit' linked with 'J.R.R. Tolkien'
(4, 4);  -- 'Murder on the Orient Express' linked with 'Agatha Christie'
-- -- ON CONFLICT (book_id, author_id) DO NOTHING;
-- Insert into BorrowRecord table (make sure to link books and library members)
INSERT INTO borrow_record (borrow_date, return_date, library_member_id, book_id) VALUES
('2022-01-01', '2022-01-10', 1, 1),  -- Alice Johnson borrows 'Harry Potter and the Sorcerer's Stone'
('2021-11-15', '2021-11-20', 2, 2),  -- Bob Smith borrows 'A Game of Thrones'
('2023-02-01', NULL, 3, 3),          -- Charlie Brown borrows 'The Hobbit' (not returned yet)
('2020-08-05', '2020-08-12', 4, 4);-- David Wilson borrows 'Murder on the Orient Express'
-- ON CONFLICT (borrow_date, return_date, library_member_id, book_id) DO NOTHING ;