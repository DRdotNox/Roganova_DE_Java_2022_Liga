CREATE TABLE IF NOT EXISTS comment (
    id SERIAL PRIMARY KEY,
    test varchar(200) not null,
    task_id int8 not null
);

CREATE TABLE IF NOT EXISTS project (
    id SERIAL PRIMARY KEY,
    header varchar(200),
    description varchar(200)
);

CREATE TABLE IF NOT EXISTS users
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(200),
    password VARCHAR(200),
    email VARCHAR(200),
    role VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS tasks
(
    id    SERIAL PRIMARY KEY ,
    name  VARCHAR(200),
    header  VARCHAR(200) ,
    description  VARCHAR(200),
    date DATE,
    status varchar(255)
);