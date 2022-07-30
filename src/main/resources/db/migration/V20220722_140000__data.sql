INSERT INTO item (id)
VALUES ('079f10d9-78e7-41ca-88cc-d675b4a0eeaa');

insert into item_attribute (id, item_id, attribute_key, attribute_type, string_value, number_value)
values  ('bd2e0a67-ebc7-41f8-9218-03580b01b4f3', '079f10d9-78e7-41ca-88cc-d675b4a0eeaa', 'values', 'DICTIONARY', null, null),
        ('c498179f-6ee7-4912-b563-218796539f43', '079f10d9-78e7-41ca-88cc-d675b4a0eeaa', 'name', 'STRING', 'Единицы измерения', null),
        ('0b9738bc-5e8c-40ba-b3e5-5bcc2fbeac42', '079f10d9-78e7-41ca-88cc-d675b4a0eeaa', 'description', 'STRING', 'Название единицы измерения', null);

insert into attribute_multiple_value (id, attribute_id, value)
values
       ('14ec79ad-4260-4a95-9a8f-209776a6fe03', 'bd2e0a67-ebc7-41f8-9218-03580b01b4f3', 'трлн.руб.'),
       ('4335b910-47b6-4890-a11d-aa6fffc4cf22', 'bd2e0a67-ebc7-41f8-9218-03580b01b4f3', 'млрд.руб.'),
       ('cf185b2c-4a89-4832-afe7-1ad33a1992a1', 'bd2e0a67-ebc7-41f8-9218-03580b01b4f3', 'млн.руб.'),
       ('45731e12-a4a0-4a7f-99b4-f65ca7cd049c', 'bd2e0a67-ebc7-41f8-9218-03580b01b4f3', 'тыс. руб.'),
       ('737d832c-1643-4823-9da6-6bdab329371e', 'bd2e0a67-ebc7-41f8-9218-03580b01b4f3', 'руб.'),
       ('0396bb3b-45d9-44cc-99ec-05a0e84de289', 'bd2e0a67-ebc7-41f8-9218-03580b01b4f3', '%'),
       ('566150a3-d1e5-46ce-bbd4-2edfdd78eba6', 'bd2e0a67-ebc7-41f8-9218-03580b01b4f3', 'Тб'),
       ('f1b6d785-b73d-44b2-8848-6fa914b6808e', 'bd2e0a67-ebc7-41f8-9218-03580b01b4f3', 'г.'),
       ('d8b0a3ce-eac9-4ba3-bf4a-a8693ef1e13e', 'bd2e0a67-ebc7-41f8-9218-03580b01b4f3', 'мес.'),
       ('0bf2b7b1-0956-4f4b-9da4-50edc6c746f9', 'bd2e0a67-ebc7-41f8-9218-03580b01b4f3', 'нед.'),
       ('93dc6f96-e64e-4968-b7b7-c9deb8857f3b', 'bd2e0a67-ebc7-41f8-9218-03580b01b4f3', 'дн.'),
       ('208f3756-e2d8-49ef-96ca-f95341ef02e5', 'bd2e0a67-ebc7-41f8-9218-03580b01b4f3', 'час'),
       ('68529f83-fdf5-485b-8ee8-551e67a067a9', 'bd2e0a67-ebc7-41f8-9218-03580b01b4f3', 'мин.'),
       ('979b819d-d564-4389-b0d3-60c240e36ea6', 'bd2e0a67-ebc7-41f8-9218-03580b01b4f3', 'сек.'),
       ('d16df7a5-0abc-4a5d-9a66-bb198a16d1ee', 'bd2e0a67-ebc7-41f8-9218-03580b01b4f3', 'шт.'),
       ('fa8c3623-014c-4a4e-84a8-bc3fd59ba9f3', 'bd2e0a67-ebc7-41f8-9218-03580b01b4f3', 'ШЕ'),
       ('939a6a91-1055-4fe8-8919-b23d27351e83', 'bd2e0a67-ebc7-41f8-9218-03580b01b4f3', 'FTE'),
       ('6c54fba4-ddf4-4743-b64d-e4a4a8001bbe', 'bd2e0a67-ebc7-41f8-9218-03580b01b4f3', 'ТОП-х'),
       ('f3655909-71fa-4722-87ab-c43d96611a92', 'bd2e0a67-ebc7-41f8-9218-03580b01b4f3', 'балл'),
       ('be95c739-e37d-4587-a52e-6c514bf6ba91', 'bd2e0a67-ebc7-41f8-9218-03580b01b4f3', 'млн. /год'),
       ('f445de7c-d20b-4af2-b3fb-9bf647698cc6', 'bd2e0a67-ebc7-41f8-9218-03580b01b4f3', 'млрд. руб./год'),
       ('f86b0f1a-7e01-48cb-928d-e2e5e3af39a9', 'bd2e0a67-ebc7-41f8-9218-03580b01b4f3', 'клиентов на сотрудника/год'),
       ('5cff99b2-a310-4688-82aa-9797356c86f3', 'bd2e0a67-ebc7-41f8-9218-03580b01b4f3', 'минут/год'),
       ('0ef4332a-791a-4068-807c-8b00d2193184', 'bd2e0a67-ebc7-41f8-9218-03580b01b4f3', 'млн. руб./месяц'),
       ('c2f87546-6b6f-42d7-bb2d-2a188dbaaece', 'bd2e0a67-ebc7-41f8-9218-03580b01b4f3', 'чел.часов/месяц'),
       ('73e14280-20dd-470c-bb93-80fcce73cc61', 'bd2e0a67-ebc7-41f8-9218-03580b01b4f3', 'часов/месяц'),
       ('721a256c-868c-4e1a-9423-a3548a238111', 'bd2e0a67-ebc7-41f8-9218-03580b01b4f3', 'штук/сек');

