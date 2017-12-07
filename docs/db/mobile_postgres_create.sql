CREATE TABLE "user" (
	"id" serial NOT NULL,
	"last_name" varchar(15) NOT NULL,
	"first_name" varchar(15) NOT NULL,
	"password" integer(15) NOT NULL,
	"email" varchar(20) NOT NULL UNIQUE,
	CONSTRAINT user_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "tarif" (
	"id" serial NOT NULL,
	"title" varchar(10) NOT NULL UNIQUE,
	CONSTRAINT tarif_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "cost" (
	"id" serial NOT NULL,
	"call" integer(5) NOT NULL,
	"sms" integer(5) NOT NULL,
	"traffic" integer(5) NOT NULL,
	CONSTRAINT cost_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "operator" (
	"id" serial NOT NULL,
	"title" varchar(10) NOT NULL UNIQUE,
	CONSTRAINT operator_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "operator_2_tarif" (
	"operator_id" integer NOT NULL,
	"tarif_id" integer NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "services_received" (
	"id" serial NOT NULL,
	"call" integer NOT NULL,
	"sms" integer NOT NULL,
	"traffic" integer NOT NULL,
	"data_id" integer NOT NULL,
	CONSTRAINT services_received_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "account" (
	"id" serial NOT NULL,
	"login" varchar NOT NULL UNIQUE,
	CONSTRAINT account_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "login_2_tarif" (
	"login_id" integer NOT NULL,
	"tarif_id" integer NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "score" (
	"id" serial NOT NULL,
	"call" integer NOT NULL,
	"sms" integer NOT NULL,
	"traffic" integer NOT NULL,
	"all" integer NOT NULL,
	"data_id" integer NOT NULL,
	CONSTRAINT score_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "login_2_cost" (
	"score_id" integer NOT NULL,
	"login_id" integer NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "data" (
	"id" serial NOT NULL,
	"time" integer NOT NULL,
	CONSTRAINT data_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "account_2_services_received" (
	"services_received_id" integer NOT NULL,
	"account_id" integer NOT NULL
) WITH (
  OIDS=FALSE
);



ALTER TABLE "user" ADD CONSTRAINT "user_fk0" FOREIGN KEY ("id") REFERENCES "account"("id");


ALTER TABLE "cost" ADD CONSTRAINT "cost_fk0" FOREIGN KEY ("id") REFERENCES "tarif"("id");


ALTER TABLE "operator_2_tarif" ADD CONSTRAINT "operator_2_tarif_fk0" FOREIGN KEY ("operator_id") REFERENCES "operator"("id");
ALTER TABLE "operator_2_tarif" ADD CONSTRAINT "operator_2_tarif_fk1" FOREIGN KEY ("tarif_id") REFERENCES "tarif"("id");

ALTER TABLE "services_received" ADD CONSTRAINT "services_received_fk0" FOREIGN KEY ("data_id") REFERENCES "data"("id");


ALTER TABLE "login_2_tarif" ADD CONSTRAINT "login_2_tarif_fk0" FOREIGN KEY ("login_id") REFERENCES "account"("id");
ALTER TABLE "login_2_tarif" ADD CONSTRAINT "login_2_tarif_fk1" FOREIGN KEY ("tarif_id") REFERENCES "tarif"("id");

ALTER TABLE "score" ADD CONSTRAINT "score_fk0" FOREIGN KEY ("data_id") REFERENCES "data"("id");

ALTER TABLE "login_2_cost" ADD CONSTRAINT "login_2_cost_fk0" FOREIGN KEY ("score_id") REFERENCES "score"("id");
ALTER TABLE "login_2_cost" ADD CONSTRAINT "login_2_cost_fk1" FOREIGN KEY ("login_id") REFERENCES "account"("id");


ALTER TABLE "account_2_services_received" ADD CONSTRAINT "account_2_services_received_fk0" FOREIGN KEY ("services_received_id") REFERENCES "services_received"("id");
ALTER TABLE "account_2_services_received" ADD CONSTRAINT "account_2_services_received_fk1" FOREIGN KEY ("account_id") REFERENCES "account"("id");

