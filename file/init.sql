create database url_shortcut with encoding 'utf8';

\c url_shortcut;

DROP TABLE if exists "public"."t_shortcut";
CREATE TABLE "public"."t_shortcut"
(
    "id"          varchar(32)  NOT NULL,
    "create_time" timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "url"         text  NOT NULL,
    CONSTRAINT "t_short_cut_pkey" PRIMARY KEY ("id")
);

truncate t_shortcut;

ALTER USER postgres WITH PASSWORD 'xxx';
