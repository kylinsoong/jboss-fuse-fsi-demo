DROP TABLE IF EXISTS funds;
CREATE TABLE IF NOT EXISTS funds (
    fundNumber INTEGER NOT NULL,
    balance INTEGER,
    denied BOOLEAN,
    deniedCause VARCHAR(255),
    transactionType VARCHAR(10),
    fundName VARCHAR (30),
    fundType VARCHAR (30),
    fundStatus VARCHAR (30)
  );
  ALTER TABLE funds ADD PRIMARY KEY(fundNumber);
