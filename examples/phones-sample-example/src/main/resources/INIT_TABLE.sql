CREATE TABLE IF NOT EXISTS T_Phone
(
    Id      INT NOT NULL primary key auto_increment,
    sdk      varchar(10),
    price   decimal(8,2),
    Brand   varchar(10),
    model   varchar(10),
    memory  INT,
    color   VARCHAR(10),
    weight  INT,
    pattern VARCHAR(5),
    origin  VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS T_Discount
(
    Id       INT NOT NULL primary key auto_increment,
    sdk      varchar(10),
    discount decimal(5,2),
    startDate date,
    endDate date
);