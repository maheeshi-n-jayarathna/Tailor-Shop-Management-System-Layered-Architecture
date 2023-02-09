create database tsms;
use tsms;

create table customer
(
    customerId varchar(4)   not null
        primary key,
    name       varchar(100) null,
    nic        varchar(15)  null,
    telNumber  varchar(15)  null,
    email      varchar(45)  null,
    address    text         null
);
  
create table employee
(
    employeeId varchar(4)   not null
        primary key,
    name       varchar(100) null,
    `rank`     varchar(25)  null,
    nic        varchar(15)  null,
    telNumber  varchar(15)  null,
    email      varchar(45)  null,
    address    text         null
);
   
create table fabric
(
    fabricId    varchar(4)    not null
        primary key,
    description text          null,
    qtyOnStock  int           null,
    unitPrice   decimal(8, 2) null
);
    
create table measurement
(
    measurementId      varchar(4) not null
        primary key,
    details            text       null,
    length             double     null,
    neck               double     null,
    bust               double     null,
    downAShoulder      double     null,
    underBust          double     null,
    sleeve             double     null,
    west               double     null,
    chest              double     null,
    crotch             double     null,
    knee               double     null,
    calf               double     null,
    hipAndWestDistance double     null,
    inseam             double     null,
    thigh              double     null,
    hip                double     null,
    skirtLength        double     null,
    frockLength        double     null
);

   
create table orders
(
    orderId     varchar(4)    not null,
    customerId  varchar(4)    not null,
    description text          null,
    Advance     decimal(8, 2) null,
    totalPrice  decimal(8, 2) null,
    primary key (orderId, customerId),
    constraint order_customer_customerId_fk
        foreign key (customerId) references customer (customerId)
            on update cascade on delete cascade
);
  
create table orderdetails
(
    orderId        varchar(4)    not null,
    fabricId       varchar(4)    not null,
    measurementId  varchar(4)    not null,
    receiveDate    date          null,
    status          varchar(20)   null,
    returnDate     date          null,
    cost           decimal(8, 2) null,
    trueReturnDate date          null,
    qty            int           null ,
    primary key (orderId, fabricId, measurementId),
    constraint orderDetails_fabric_fabricId_fk
        foreign key (fabricId) references fabric (fabricId)
            on update cascade on delete cascade,
    constraint orderDetails_measurement_measurementId_fk
        foreign key (measurementId) references measurement (measurementId)
            on update cascade on delete cascade,
    constraint orderDetails_order_orderId_fk
        foreign key (orderId) references orders (orderId)
            on update cascade on delete cascade
);

   
create table supplier
(
    supplierId  varchar(4)   not null
        primary key,
    name        varchar(100) null,
    description text         null,
    telNumber   varchar(15)  null,
    address     text         null
);
   
create table supplies
(
    suppliesId varchar(4) not null
        primary key,
    date       date       null,
    supplierId varchar(4) not null,
    constraint supplies_supplier_supplierId_fk
        foreign key (supplierId) references supplier (supplierId)
);
   
create table suppliesdetails
(
    suppliesId varchar(4)    not null
        primary key,
    fabricId   varchar(4)    not null,
    qty        int           null,
    unitPrice  decimal(8, 2) null,
    constraint suppliesDetails_fabric_fabricId_fk
        foreign key (fabricId) references fabric (fabricId),
    constraint suppliesDetails_supplies_suppliesId_fk
        foreign key (suppliesId) references supplies (suppliesId)
);
    
create table user
(
    userId    varchar(4)   not null
        primary key,
    userRank  varchar(25)  null,
    userName  varchar(100) null,
    password  varchar(45)  null,
    nic       varchar(15)  null,
    telNumber varchar(15)  null,
    email     varchar(45)  null,
    address   text         null
);
   

