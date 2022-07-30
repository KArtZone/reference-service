create table item
(
    id          uuid,

    constraint item_pk primary key (id)
);

create table item_attribute
(
    id             uuid,
    item_id        uuid        not null,
    attribute_key  varchar(50) not null,
    attribute_type varchar(50) not null,
    string_value   varchar(255),
    number_value   numeric(12, 2),

    constraint item_attribute_pk primary key (id),
    constraint item_attribute_ref_item foreign key (item_id) references item (id),
    constraint unique_item_attribute unique (item_id, attribute_key)
);

create table attribute_text_value
(
    attribute_id uuid not null,
    value        text not null,

    constraint attribute_text_value_pk primary key (attribute_id),
    constraint attribute_text_value_ref_attribute foreign key (attribute_id) references item_attribute (id)
);

create table attribute_multiple_value
(
    id           uuid,
    attribute_id uuid not null,
    value        varchar(50),

    constraint attribute_multiple_value_pk primary key (id),
    constraint attribute_multiple_value_ref_attribute foreign key (attribute_id) references item_attribute (id),
    constraint unique_attribute_multiple_value unique (attribute_id, value)
);
