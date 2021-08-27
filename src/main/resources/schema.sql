CREATE TABLE "invoice_item" (
	"id" VARCHAR2(255) NOT NULL,
	"invoice_id" VARCHAR2(255) NOT NULL,
	"product_id" VARCHAR2(255) NOT NULL,
	"quantity" INT NOT NULL,
	"total_item_price" FLOAT NOT NULL,
	constraint INVOICE_ITEM_PK PRIMARY KEY ("id"));


/
CREATE TABLE "invoice" (
	"id" VARCHAR2(255) NOT NULL,
	"customer_name" VARCHAR2(255) NOT NULL,
	"issue_date" DATE NOT NULL,
	"due_date" DATE NOT NULL,
	"user_comment" VARCHAR2(255),
	"total" FLOAT NOT NULL,
	constraint INVOICE_PK PRIMARY KEY ("id"));


/
CREATE TABLE "product" (
	"id" VARCHAR2(255) NOT NULL,
	"name" VARCHAR2(255) NOT NULL,
	"unit_price" FLOAT NOT NULL,
	constraint PRODUCT_PK PRIMARY KEY ("id"));


/
ALTER TABLE "invoice_item" ADD CONSTRAINT "invoice_item_fk0" FOREIGN KEY ("invoice_id") REFERENCES "invoice"("id");
ALTER TABLE "invoice_item" ADD CONSTRAINT "invoice_item_fk1" FOREIGN KEY ("product_id") REFERENCES "product"("id");


