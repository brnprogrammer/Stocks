create database stockmanager;


create table Stock (
		id_stock int not null auto_increment primary key,
		cd_stock varchar(10)
)


create table Quotes(
	id_quotes int not null auto_increment primary key,
    dt_stock date,
    id_stock int,
    valor_stock decimal,
    CONSTRAINT fk_StockQuotes foreign key(id_stock) references Stock(id_stock)
)



