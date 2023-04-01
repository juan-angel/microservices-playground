CREATE TABLE IF NOT EXISTS public.member (
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL
)

TABLESPACE pg_default;

ALTER TABLE public.member
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.role (
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(20) NOT NULL
)

TABLESPACE pg_default;

ALTER TABLE public.role
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.member_role (
	id_member BIGSERIAL NOT NULL,
	id_role BIGSERIAL NOT NULL,
	CONSTRAINT FK_MEMBER_MEMBER_ROLE FOREIGN KEY(id_member) REFERENCES member(id),
	CONSTRAINT FK_ROLE_MEMBER_ROLE FOREIGN KEY(id_role) REFERENCES role(id)
)

TABLESPACE pg_default;

ALTER TABLE public.member_role
    OWNER to postgres;
