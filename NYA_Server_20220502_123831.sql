--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1
-- Dumped by pg_dump version 14.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: env_modulators; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.env_modulators (
    index integer NOT NULL,
    module_id integer NOT NULL,
    room_temperature double precision NOT NULL,
    module_status boolean NOT NULL,
    target_temperature double precision DEFAULT 0 NOT NULL,
    cooling_status boolean DEFAULT false NOT NULL,
    heating_status boolean DEFAULT false NOT NULL,
    override boolean DEFAULT false NOT NULL
);


ALTER TABLE public.env_modulators OWNER TO postgres;

--
-- Name: env_modulators_index_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.env_modulators_index_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.env_modulators_index_seq OWNER TO postgres;

--
-- Name: env_modulators_index_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.env_modulators_index_seq OWNED BY public.env_modulators.index;


--
-- Name: env_modulators_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.env_modulators_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.env_modulators_user_id_seq OWNER TO postgres;

--
-- Name: env_modulators_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.env_modulators_user_id_seq OWNED BY public.env_modulators.module_id;


--
-- Name: user_data; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_data (
    user_id text NOT NULL,
    password text NOT NULL,
    user_no integer NOT NULL
);


ALTER TABLE public.user_data OWNER TO postgres;

--
-- Name: table1_userID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."table1_userID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."table1_userID_seq" OWNER TO postgres;

--
-- Name: table1_userID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."table1_userID_seq" OWNED BY public.user_data.user_id;


--
-- Name: user_data_userNo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."user_data_userNo_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."user_data_userNo_seq" OWNER TO postgres;

--
-- Name: user_data_userNo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."user_data_userNo_seq" OWNED BY public.user_data.user_no;


--
-- Name: user_module_id; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_module_id (
    user_id integer NOT NULL,
    module_id integer NOT NULL
);


ALTER TABLE public.user_module_id OWNER TO postgres;

--
-- Name: user_module_id_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_module_id_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_module_id_user_id_seq OWNER TO postgres;

--
-- Name: user_module_id_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_module_id_user_id_seq OWNED BY public.user_module_id.user_id;


--
-- Name: env_modulators index; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.env_modulators ALTER COLUMN index SET DEFAULT nextval('public.env_modulators_index_seq'::regclass);


--
-- Name: env_modulators module_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.env_modulators ALTER COLUMN module_id SET DEFAULT nextval('public.env_modulators_user_id_seq'::regclass);


--
-- Name: user_data user_no; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_data ALTER COLUMN user_no SET DEFAULT nextval('public."user_data_userNo_seq"'::regclass);


--
-- Name: user_module_id user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_module_id ALTER COLUMN user_id SET DEFAULT nextval('public.user_module_id_user_id_seq'::regclass);


--
-- Data for Name: env_modulators; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.env_modulators VALUES
	(1, 1, 21.95, true, 22, false, false, false),
	(2, 2, 30.06, false, 30, false, false, false);


--
-- Data for Name: user_data; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_data VALUES
	('test', 'test', 1),
	('test2', 'test2', 5),
	('test4', 'test5', 7),
	('test4', 'test5', 8),
	('test4', 'test5', 10),
	('test34', 'test33', 11),
	('asdasd', 'asdasd', 12);


--
-- Data for Name: user_module_id; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_module_id VALUES
	(1, 1),
	(5, 2);


--
-- Name: env_modulators_index_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.env_modulators_index_seq', 3, true);


--
-- Name: env_modulators_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.env_modulators_user_id_seq', 1, true);


--
-- Name: table1_userID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."table1_userID_seq"', 1, false);


--
-- Name: user_data_userNo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."user_data_userNo_seq"', 12, true);


--
-- Name: user_module_id_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_module_id_user_id_seq', 1, false);


--
-- Name: env_modulators env_modulators_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.env_modulators
    ADD CONSTRAINT env_modulators_pkey PRIMARY KEY (index);


--
-- Name: env_modulators unique_env_modulators_index; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.env_modulators
    ADD CONSTRAINT unique_env_modulators_index UNIQUE (index);


--
-- Name: user_data unique_table1_userID; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_data
    ADD CONSTRAINT "unique_table1_userID" PRIMARY KEY (user_no);


--
-- Name: user_data unique_user_data_userNo; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_data
    ADD CONSTRAINT "unique_user_data_userNo" UNIQUE (user_no);


--
-- Name: user_module_id module; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_module_id
    ADD CONSTRAINT module FOREIGN KEY (module_id) REFERENCES public.env_modulators(index) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: user_module_id user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_module_id
    ADD CONSTRAINT "user" FOREIGN KEY (user_id) REFERENCES public.user_data(user_no) MATCH FULL ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

