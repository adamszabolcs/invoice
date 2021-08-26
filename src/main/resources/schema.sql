CREATE TABLE "Invoice_item" (
	"id" VARCHAR2(255) NOT NULL,
	"invoice_id" VARCHAR2(255) NOT NULL,
	"product_id" VARCHAR2(255) NOT NULL,
	"quantity" INT NOT NULL,
	"total_item_price" FLOAT NOT NULL,
	constraint INVOICE_ITEM_PK PRIMARY KEY ("id"));


/
CREATE TABLE "Invoice" (
	"id" VARCHAR2(255) NOT NULL,
	"customer_name" VARCHAR2(255) NOT NULL,
	"issue_date" DATE NOT NULL,
	"due_date" DATE NOT NULL,
	"comment" VARCHAR2(255),
	"total" FLOAT NOT NULL,
	constraint INVOICE_PK PRIMARY KEY ("id"));


/
CREATE TABLE "Product" (
	"id" VARCHAR2(255) NOT NULL,
	"name" VARCHAR2(255) NOT NULL,
	"unit_price" FLOAT NOT NULL,
	constraint PRODUCT_PK PRIMARY KEY ("id"));


/
ALTER TABLE "Invoice_item" ADD CONSTRAINT "Invoice_item_fk0" FOREIGN KEY ("invoice_id") REFERENCES "Invoice"("id");
ALTER TABLE "Invoice_item" ADD CONSTRAINT "Invoice_item_fk1" FOREIGN KEY ("product_id") REFERENCES "Product"("id");


