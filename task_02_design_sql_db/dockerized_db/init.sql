CREATE TYPE "note_status" AS ENUM (
  'neutral',
  'open',
  'closed'
);

CREATE TYPE "placement_status" AS ENUM (
  'applied',
  'visited',
  'interview',
  'selection',
  'accepted',
  'declined'
);

CREATE TYPE "offer_status" AS ENUM (
  'draft',
  'published',
  'private',
  'over',
  'archived'
);

CREATE TABLE "recruiters" (
  "id" integer PRIMARY KEY,
  "name" varchar
);

CREATE TABLE "hiring_managers" (
  "id" integer PRIMARY KEY,
  "name" varchar
);

CREATE TABLE "offers" (
  "id" integer PRIMARY KEY,
  "status" offer_status,
  "description" text
);

CREATE TABLE "candidates" (
  "id" integer PRIMARY KEY,
  "name" varchar
);

CREATE TABLE "placements" (
  "id" integer PRIMARY KEY,
  "status" placement_status,
  "score" integer
);

CREATE TABLE "notes" (
  "id" integer PRIMARY KEY,
  "content" text,
  "author_id" hiring_managers,
  "status" note_status
);

CREATE TABLE "interviews" (
  "id" integer PRIMARY KEY,
  "start" timestamp,
  "end" timestamp,
  "additional_participants" varchar[]
);

ALTER TABLE "notes" ADD FOREIGN KEY ("id") REFERENCES "placements" ("id");

ALTER TABLE "interviews" ADD FOREIGN KEY ("id") REFERENCES "placements" ("id");

ALTER TABLE "offers" ADD FOREIGN KEY ("id") REFERENCES "recruiters" ("id");

ALTER TABLE "offers" ADD FOREIGN KEY ("id") REFERENCES "hiring_managers" ("id");

ALTER TABLE "placements" ADD FOREIGN KEY ("id") REFERENCES "candidates" ("id");

ALTER TABLE "placements" ADD FOREIGN KEY ("id") REFERENCES "offers" ("id");
