CREATE TABLE product
(
    id                   bigint    NOT NULL,
    category             varchar(255),
    discount_percentage  float(53),
    is_offer_applied     bit,
    name                 varchar(255),
    price                float(53) NOT NULL,
    price_after_discount float(53),
    PRIMARY KEY (id)
) engine=InnoDB;
