DROP TABLE IF EXISTS funds;
CREATE TABLE IF NOT EXISTS funds (
    fundNumber INTEGER NOT NULL,
    balance INTEGER,
    denied BOOLEAN,
    deniedCause VARCHAR(255),
    transactionType VARCHAR(10),
    fundName VARCHAR (30)
  );
