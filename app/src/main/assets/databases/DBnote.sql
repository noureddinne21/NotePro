
CREATE TABLE "T" (
	"id"	INTEGER NOT NULL UNIQUE,
	"title"	TEXT,
	"note"	TEXT,
	"date"	TEXT,
	"favorite"	TEXT,
	PRIMARY KEY("id" AUTOINCREMENT)
);

INSERT INTO T (title, note, date, favorite) VALUES ('Fierst Note', 'HI.', '00/00/0000', '');

