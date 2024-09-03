INSERT INTO admin (id, first_name, last_name, gender, dob, email, phone, secondary_phone, nid, occupation, organization, profile_picurl, password, last_reset_at, last_updated, created_at, date_created, status, updated_at, updated_by) 
VALUES (1, 'John', 'Doe', 'male', '1990-01-15', 'john.doe@example.com', '123-456-7890', '098-765-4321', '1268457689', 'Engineer', 'Tech Corp', 'http://example.com/profiles/876135.jpg', 'password123', '2024-08-29T10:00:00.000Z', '2024-08-29T10:00:00.000Z', '2024-08-30T11:56:22.677Z', '2024-08-30T11:56:22.677Z', true, '2024-08-30T11:56:22.677Z', 'admin');

INSERT INTO customer (id, first_name, last_name, gender, dob, email, phone, secondary_phone, nid, occupation, organization, profile_picurl, password, last_reset_at, last_updated, created_at, date_created, status, updated_at, updated_by) 
VALUES (1, 'Mitchell', 'Johnson', 'male', '1990-01-15', 'mitchell.johnson@example.com', '123-456-7890', '098-765-4321', '1469872594', 'Engineer', 'Tech Corp', 'http://example.com/profiles/136963.jpg', 'password123', '2024-08-29T10:00:00.000Z', '2024-08-29T10:00:00.000Z', '2024-08-30T11:56:22.677Z', '2024-08-30T11:56:22.677Z', true, '2024-08-30T11:56:22.677Z', 'admin');

INSERT INTO merchant (id, first_name, last_name, gender, dob, email, phone, secondary_phone, nid, occupation, organization, profile_picurl, password, last_reset_at, last_updated, created_at, date_created, status, updated_at, updated_by) 
VALUES (1, 'Hashim', 'Amla', 'male', '1990-01-15', 'hashim.amla@example.com', '123-456-7890', '098-765-4321', '1597468536', 'Engineer', 'Tech Corp', 'http://example.com/profiles/146456.jpg', 'password123', '2024-08-29T10:00:00.000Z', '2024-08-29T10:00:00.000Z', '2024-08-30T11:56:22.677Z', '2024-08-30T11:56:22.677Z', true, '2024-08-30T11:56:22.677Z', 'admin');

INSERT INTO sys_admin (id, first_name, last_name, gender, dob, email, phone, secondary_phone, nid, occupation, organization, profile_picurl, password, last_reset_at, last_updated, created_at, date_created, status, updated_at, updated_by) 
VALUES (1, 'Ricky', 'Ponting', 'male', '1990-01-15', 'ricky.ponting@example.com', '123-456-7890', '098-765-4321', '1685492365', 'Engineer', 'Tech Corp', 'http://example.com/profiles/189357.jpg', 'password123', '2024-08-29T10:00:00.000Z', '2024-08-29T10:00:00.000Z', '2024-08-30T11:56:22.677Z', '2024-08-30T11:56:22.677Z', true, '2024-08-30T11:56:22.677Z', 'admin');

INSERT INTO address (id, label, division, district, upazila, local_govt, address_line1, address_line2, longitude, latitude, user_id, is_primary, is_deleted, date_created, last_updated) 
VALUES (1, 'Home', 'Dhaka', 'Dhaka', 'Savar', 'Ward-15', 'House-12', 'Road-5', '90.3773', '23.7465', 1, true, false, '2024-08-30T11:56:22.677Z', '2024-08-30T11:56:22.677Z');
