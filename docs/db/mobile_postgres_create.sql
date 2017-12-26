CREATE TABLE "service_history" (
	"id" serial NOT NULL,
	"date" time with time zone NOT NULL,
	"tariff_item_id" bigint NOT NULL,
	"quantity" integer NOT NULL,
	"sum" integer NOT NULL,
	"phone_number_id" bigint NOT NULL,
	CONSTRAINT service_history_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "account_details" (
	"id" serial NOT NULL,
	"last_name" character varying(30) NOT NULL,
	"first_name" character varying(30) NOT NULL,
	"created " TIMESTAMP NOT NULL,
	"modified" TIMESTAMP NOT NULL,
	CONSTRAINT account_details_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "operator" (
	"id" serial NOT NULL,
	"title" character varying(30) NOT NULL,
	"deleted" BOOLEAN NOT NULL DEFAULT 'false',
	"created " TIMESTAMP NOT NULL,
	"modified" TIMESTAMP NOT NULL,
	CONSTRAINT operator_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "account" (
	"id" serial NOT NULL,
	"email" character varying(50) NOT NULL UNIQUE,
	"password" character varying(50) NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"modified" TIMESTAMP NOT NULL,
	CONSTRAINT account_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "account_2_role" (
	"account_id" bigint NOT NULL,
	"role_id" bigint NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "invoice" (
	"id" serial NOT NULL,
	"type" character varying NOT NULL,
	"quantity" integer NOT NULL,
	"sum" integer NOT NULL,
	"month" time with time zone NOT NULL,
	"year" time with time zone NOT NULL,
	CONSTRAINT invoice_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "phone_number_2_invoice" (
	"invoice_id" bigint NOT NULL,
	"phone_number_id" bigint NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "service" (
	"id" serial NOT NULL,
	"type" character varying(50) NOT NULL,
	"unit" character varying(50) NOT NULL,
	"deleted" BOOLEAN NOT NULL DEFAULT 'false',
	"created" TIMESTAMP NOT NULL,
	"modified" TIMESTAMP NOT NULL,
	CONSTRAINT service_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "phone_number" (
	"id" serial NOT NULL,
	"account_id" bigint NOT NULL,
	"number" character varying NOT NULL UNIQUE,
	"tariff_id" bigint NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"modified" TIMESTAMP NOT NULL,
	CONSTRAINT phone_number_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "tariff" (
	"id" serial NOT NULL,
	"operator_id" bigint NOT NULL,
	"name" character varying NOT NULL,
	"deleted" BOOLEAN NOT NULL DEFAULT 'false',
	"created " TIMESTAMP NOT NULL,
	"modified" TIMESTAMP NOT NULL,
	CONSTRAINT tariff_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "tariff_item" (
	"id" serial NOT NULL,
	"tariif_id" bigint NOT NULL,
	"service_id" bigint NOT NULL,
	"cost" integer NOT NULL,
	"deleted" BOOLEAN NOT NULL DEFAULT 'false',
	"created " TIMESTAMP NOT NULL,
	"modified" TIMESTAMP NOT NULL,
	CONSTRAINT tariff_item_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "service_history" ADD CONSTRAINT "service_history_fk0" FOREIGN KEY ("tariff_item_id") REFERENCES "tariff_item"("id");
ALTER TABLE "service_history" ADD CONSTRAINT "service_history_fk1" FOREIGN KEY ("phone_number_id") REFERENCES "phone_number"("id");



ALTER TABLE "account" ADD CONSTRAINT "account_fk0" FOREIGN KEY ("id") REFERENCES "account_details"("id");

ALTER TABLE "account_2_role" ADD CONSTRAINT "account_2_role_fk0" FOREIGN KEY ("account_id") REFERENCES "account"("id");


ALTER TABLE "phone_number_2_invoice" ADD CONSTRAINT "phone_number_2_invoice_fk0" FOREIGN KEY ("invoice_id") REFERENCES "invoice"("id");
ALTER TABLE "phone_number_2_invoice" ADD CONSTRAINT "phone_number_2_invoice_fk1" FOREIGN KEY ("phone_number_id") REFERENCES "phone_number"("id");


ALTER TABLE "phone_number" ADD CONSTRAINT "phone_number_fk0" FOREIGN KEY ("account_id") REFERENCES "account"("id");
ALTER TABLE "phone_number" ADD CONSTRAINT "phone_number_fk1" FOREIGN KEY ("tariff_id") REFERENCES "tariff"("id");

ALTER TABLE "tariff" ADD CONSTRAINT "tariff_fk0" FOREIGN KEY ("operator_id") REFERENCES "operator"("id");
ALTER TABLE "tariff" ADD CONSTRAINT "tariff_fk1" FOREIGN KEY ("name") REFERENCES ""("");

ALTER TABLE "tariff_item" ADD CONSTRAINT "tariff_item_fk0" FOREIGN KEY ("tariif_id") REFERENCES "tariff"("id");
ALTER TABLE "tariff_item" ADD CONSTRAINT "tariff_item_fk1" FOREIGN KEY ("service_id") REFERENCES "service"("id");

