--INSERT INTO users (user_name, password, first_name, last_name, phone, email, created_at, is_active) 
--VALUES('ppuarat', '123456', 'Pangseen', 'Puarat', '0874737908', 'student@aspire2.ac.nz', '2019-11-21', true);


SELECT id, user_name, password, first_name, last_name, phone, email, created_at, is_active 
FROM users
where is_active = true and user_name = 'ppuarat' and password = '123456';


UPDATE users SET user_name='', "password"='', first_name='', last_name='', 
phone='', email='', created_at='', is_active=true 
WHERE id=nextval('users_id_seq'::regclass);
