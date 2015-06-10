DROP TABLE account IF EXISTS;

CREATE TABLE account (
	account_number varchar(255),
	account_description varchar(255),
	account_balance bigint,
	max_balance_amount bigint,
	primary key (account_number)
);