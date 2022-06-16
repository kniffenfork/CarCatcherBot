create table users(
                      id bigint primary key,
                      first_name varchar(100),
                      last_name varchar(100),
                      username varchar(100) not null,
                      state_code varchar(100) not null
);

create table car_searches(
    id bigserial primary key,
    mark varchar(100),
    model varchar(100),
    generation varchar(100),
    user_id bigint references users(id)
)
