--INSERT INTO "foodTypes" (type_name, description, created_at, is_active) 
--VALUES('DRINKS', '', '2019-11-23', true);

select * from "foodTypes"; 
select * from foods;

--INSERT INTO foods (food_name, description, created_at, price, is_active, type_id) 
--VALUES('GARLIC PRAWN', 'Prawns, baby spinach, tomato, oregano & mozzarella on a crème fraîche & garlic base',
--'2019-11-23', 12.5, true, 1);

--INSERT INTO foods (food_name, description, created_at, price, is_active, type_id) 
--VALUES('BBQ CHICKEN WINGS', '', '2019-11-23', 7.5, true, 2);

INSERT INTO foods (food_name, description, created_at, price, is_active, type_id) 
VALUES('V DRINK', '', '2019-11-23', 3.5, true, 3);
