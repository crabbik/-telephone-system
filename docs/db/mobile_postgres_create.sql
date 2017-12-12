CREATE TABLE "spent" (
	"id" serial NOT NULL,
	"data" time with time zone NOT NULL,
	"type" varchar NOT NULL,
	"quantity" float4 NOT NULL,
	"sum" float4 NOT NULL,
	CONSTRAINT spent_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "client" (
	"id" serial NOT NULL,
	"last_name" varchar(30) NOT NULL,
	"first_name" varchar(30) NOT NULL,
	"tariff_id" bigint NOT NULL,
	CONSTRAINT client_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "tariff" (
	"id" serial NOT NULL,
	"title" varchar(30) NOT NULL,
	"operator_id" bigint NOT NULL,
	"service_id" varchar NOT NULL,
	"cost" float4 NOT NULL,
	"deleted" BOOLEAN NOT NULL DEFAULT 'false',
	CONSTRAINT tariff_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "operator" (
	"id" serial NOT NULL,
	"title" varchar(30) NOT NULL,
	"deleted" BOOLEAN NOT NULL DEFAULT 'false',
	CONSTRAINT operator_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "account" (
	"id" serial NOT NULL,
	"email" varchar(50) NOT NULL,
	"password" varchar(50) NOT NULL,
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
	"type" varchar NOT NULL,
	"quantity" float4 NOT NULL,
	"sum" float4 NOT NULL,
	"day" time with time zone NOT NULL,
	"month" time with time zone NOT NULL,
	"year" time with time zone NOT NULL,
	CONSTRAINT invoice_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "client_2_ivoice" (
	"invoice_id" bigint NOT NULL,
	"client_id" bigint NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "service" (
	"id" serial NOT NULL,
	"type" varchar(50) NOT NULL,
	"unit" varchar(50) NOT NULL,
	CONSTRAINT service_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "spent" ADD CONSTRAINT "spent_fk0" FOREIGN KEY ("id") REFERENCES "client"("id");

ALTER TABLE "client" ADD CONSTRAINT "client_fk0" FOREIGN KEY ("tariff_id") REFERENCES "tariff"("id");

ALTER TABLE "tariff" ADD CONSTRAINT "tariff_fk0" FOREIGN KEY ("operator_id") REFERENCES "operator"("id");
ALTER TABLE "tariff" ADD CONSTRAINT "tariff_fk1" FOREIGN KEY ("service_id") REFERENCES "service"("id");


ALTER TABLE "account" ADD CONSTRAINT "account_fk0" FOREIGN KEY ("id") REFERENCES "client"("id");

ALTER TABLE "account_2_role" ADD CONSTRAINT "account_2_role_fk0" FOREIGN KEY ("account_id") REFERENCES "account"("id");


ALTER TABLE "client_2_ivoice" ADD CONSTRAINT "client_2_ivoice_fk0" FOREIGN KEY ("invoice_id") REFERENCES "invoice"("id");
ALTER TABLE "client_2_ivoice" ADD CONSTRAINT "client_2_ivoice_fk1" FOREIGN KEY ("client_id") REFERENCES "client"("id");


