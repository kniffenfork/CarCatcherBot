create table users(
                      id bigint primary key,
                      first_name varchar(100),
                      last_name varchar(100),
                      username varchar(100) not null,
                      state_code varchar(100) not null,
                      last_message_id bigint not null default 0
);

create table car_searches(
                             id bigserial primary key,
                             city varchar(100),
                             region varchar(100),
                             mark varchar(100),
                             model varchar(100),
                             generation varchar(100),
                             user_id bigint references users(id)
);
