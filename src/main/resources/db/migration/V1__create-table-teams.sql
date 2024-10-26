CREATE TABLE teams (
  id SERIAL NOT NULL,
   name VARCHAR(255) NOT NULL UNIQUE,
   nickname VARCHAR(255) NOT NULL UNIQUE,
   foundation_date DATE,
   stadium VARCHAR(255) NOT NULL,
   state VARCHAR(255) NOT NULL,
   url_badge VARCHAR(255) NOT NULL,
   CONSTRAINT pk_teams PRIMARY KEY (id)

);
