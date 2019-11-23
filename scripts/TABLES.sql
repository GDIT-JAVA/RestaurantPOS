CREATE TABLE "users" (
  "id" SERIAL PRIMARY KEY,
  "user_name" varchar,
  "password" varchar,
  "first_name" varchar,
  "last_name" varchar,
  "phone" varchar,
  "email" varchar,
  "created_at" timestamp,
  "is_active" boolean
);

CREATE TABLE "foodTypes" (
  "id" SERIAL PRIMARY KEY,
  "type_name" varchar,
  "description" varchar,
  "created_at" timestamp,
  "is_active" boolean
);

CREATE TABLE "foods" (
  "id" SERIAL PRIMARY KEY,
  "food_name" varchar,
  "description" varchar,
  "created_at" timestamp,
  "price" real,
  "is_active" boolean,
  "type_id" int
);

CREATE TABLE "customers" (
  "id" SERIAL PRIMARY KEY,
  "first_name" varchar,
  "last_name" varchar,
  "phone" varchar,
  "email" varchar,
  "description" varchar,
  "created_at" timestamp,
  "is_active" boolean
);

CREATE TABLE "orders" (
  "id" SERIAL PRIMARY KEY,
  "customer_id" int,
  "user_id" int,
  "is_takeAway" boolean,
  "created_at" timestamp,
  "is_active" boolean
);

CREATE TABLE "orderDetail" (
  "id" SERIAL PRIMARY KEY,
  "order_id" int,
  "food_id" int,
  "created_at" timestamp,
  "description" varchar,
  "is_active" boolean
);

CREATE TABLE "payments" (
  "id" SERIAL PRIMARY KEY,
  "order_id" int,
  "created_at" timestamp,
  "total" real,
  "description" varchar,
  "is_active" boolean
);

CREATE TABLE "coupons" (
  "id" SERIAL PRIMARY KEY,
  "customer_id" int,
  "user_id" int,
  "created_at" timestamp,
  "expire_date" timestamp,
  "is_active" boolean
);

ALTER TABLE "foods" ADD FOREIGN KEY ("type_id") REFERENCES "foodTypes" ("id");

ALTER TABLE "orders" ADD FOREIGN KEY ("customer_id") REFERENCES "customers" ("id");

ALTER TABLE "orders" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "orderDetail" ADD FOREIGN KEY ("order_id") REFERENCES "orders" ("id");

ALTER TABLE "orderDetail" ADD FOREIGN KEY ("food_id") REFERENCES "foods" ("id");

ALTER TABLE "payments" ADD FOREIGN KEY ("order_id") REFERENCES "orders" ("id");

ALTER TABLE "coupons" ADD FOREIGN KEY ("customer_id") REFERENCES "customers" ("id");

ALTER TABLE "coupons" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE users ADD CONSTRAINT users_username_unq UNIQUE (user_name);

