--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0
-- Dumped by pg_dump version 16.0

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
-- Name: contents; Type: TABLE; Schema: public; Owner: user_pdf_form_filling
--

CREATE TABLE public.contents (
    content_id integer NOT NULL,
    form_id integer,
    user_id integer,
    content jsonb NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.contents OWNER TO user_pdf_form_filling;

--
-- Name: contents_content_id_seq; Type: SEQUENCE; Schema: public; Owner: user_pdf_form_filling
--

CREATE SEQUENCE public.contents_content_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.contents_content_id_seq OWNER TO user_pdf_form_filling;

--
-- Name: contents_content_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: user_pdf_form_filling
--

ALTER SEQUENCE public.contents_content_id_seq OWNED BY public.contents.content_id;


--
-- Name: fields; Type: TABLE; Schema: public; Owner: user_pdf_form_filling
--

CREATE TABLE public.fields (
    field_id integer NOT NULL,
    form_id integer NOT NULL,
    field_name character varying(255) NOT NULL,
    field_alias character varying(255) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.fields OWNER TO user_pdf_form_filling;

--
-- Name: fields_field_id_seq; Type: SEQUENCE; Schema: public; Owner: user_pdf_form_filling
--

CREATE SEQUENCE public.fields_field_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.fields_field_id_seq OWNER TO user_pdf_form_filling;

--
-- Name: fields_field_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: user_pdf_form_filling
--

ALTER SEQUENCE public.fields_field_id_seq OWNED BY public.fields.field_id;


--
-- Name: forms; Type: TABLE; Schema: public; Owner: user_pdf_form_filling
--

CREATE TABLE public.forms (
    form_id integer NOT NULL,
    form_name character varying(255) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    form_path character varying(255)
);


ALTER TABLE public.forms OWNER TO user_pdf_form_filling;

--
-- Name: forms_form_id_seq; Type: SEQUENCE; Schema: public; Owner: user_pdf_form_filling
--

CREATE SEQUENCE public.forms_form_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.forms_form_id_seq OWNER TO user_pdf_form_filling;

--
-- Name: forms_form_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: user_pdf_form_filling
--

ALTER SEQUENCE public.forms_form_id_seq OWNED BY public.forms.form_id;


--
-- Name: roles; Type: TABLE; Schema: public; Owner: user_pdf_form_filling
--

CREATE TABLE public.roles (
    role_id integer NOT NULL,
    role_name character varying(255) NOT NULL,
    role_alias character varying(255) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.roles OWNER TO user_pdf_form_filling;

--
-- Name: roles_role_id_seq; Type: SEQUENCE; Schema: public; Owner: user_pdf_form_filling
--

CREATE SEQUENCE public.roles_role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.roles_role_id_seq OWNER TO user_pdf_form_filling;

--
-- Name: roles_role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: user_pdf_form_filling
--

ALTER SEQUENCE public.roles_role_id_seq OWNED BY public.roles.role_id;


--
-- Name: user_roles; Type: TABLE; Schema: public; Owner: user_pdf_form_filling
--

CREATE TABLE public.user_roles (
    user_id integer NOT NULL,
    role_id integer NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.user_roles OWNER TO user_pdf_form_filling;

--
-- Name: users; Type: TABLE; Schema: public; Owner: user_pdf_form_filling
--

CREATE TABLE public.users (
    user_id integer NOT NULL,
    email character varying(50) NOT NULL,
    password character varying(255) NOT NULL,
    first_name character varying(25) NOT NULL,
    patronymic character varying(25),
    second_name character varying(25) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.users OWNER TO user_pdf_form_filling;

--
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: user_pdf_form_filling
--

CREATE SEQUENCE public.users_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_user_id_seq OWNER TO user_pdf_form_filling;

--
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: user_pdf_form_filling
--

ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;


--
-- Name: contents content_id; Type: DEFAULT; Schema: public; Owner: user_pdf_form_filling
--

ALTER TABLE ONLY public.contents ALTER COLUMN content_id SET DEFAULT nextval('public.contents_content_id_seq'::regclass);


--
-- Name: fields field_id; Type: DEFAULT; Schema: public; Owner: user_pdf_form_filling
--

ALTER TABLE ONLY public.fields ALTER COLUMN field_id SET DEFAULT nextval('public.fields_field_id_seq'::regclass);


--
-- Name: forms form_id; Type: DEFAULT; Schema: public; Owner: user_pdf_form_filling
--

ALTER TABLE ONLY public.forms ALTER COLUMN form_id SET DEFAULT nextval('public.forms_form_id_seq'::regclass);


--
-- Name: roles role_id; Type: DEFAULT; Schema: public; Owner: user_pdf_form_filling
--

ALTER TABLE ONLY public.roles ALTER COLUMN role_id SET DEFAULT nextval('public.roles_role_id_seq'::regclass);


--
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: user_pdf_form_filling
--

ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);


--
-- Data for Name: contents; Type: TABLE DATA; Schema: public; Owner: user_pdf_form_filling
--

COPY public.contents (content_id, form_id, user_id, content, created_at) FROM stdin;
1	1	1	[{"value": "21432", "fieldId": 1, "fieldName": "001_editTextDataYavki", "fieldAlias": "Дата поездки"}, {"value": "", "fieldId": 2, "fieldName": "002_editTextVremyaYavki", "fieldAlias": "явка"}, {"value": "", "fieldId": 3, "fieldName": "003_editTextLokSeria", "fieldAlias": "Локомотив серия"}, {"value": "", "fieldId": 4, "fieldName": "004_editTextLokNomer", "fieldAlias": "№"}, {"value": "", "fieldId": 5, "fieldName": "005_editTextLokPripiska", "fieldAlias": "депо приписки_ТЧ"}, {"value": "", "fieldId": 6, "fieldName": "006_editTextElekKarta", "fieldAlias": "Номер версии ЭК"}, {"value": "", "fieldId": 7, "fieldName": "007_editTextDopPribor", "fieldAlias": "Доп.приборы без-ти"}, {"value": "", "fieldId": 8, "fieldName": "009_editTextMashinistTabl", "fieldAlias": "таб.№"}, {"value": "", "fieldId": 9, "fieldName": "008_editTextMashinist", "fieldAlias": "Машинист"}, {"value": "", "fieldId": 10, "fieldName": "010_editTextPomoshnik", "fieldAlias": "Пом.маш.-та"}, {"value": "", "fieldId": 11, "fieldName": "011_editTextPomoshnikTabl", "fieldAlias": "таб.№"}, {"value": "", "fieldId": 12, "fieldName": "012_editTextInstruktor", "fieldAlias": "ТЧЭМИ"}, {"value": "efw", "fieldId": 13, "fieldName": "013_editTextKolonna", "fieldAlias": "Колонна, №"}, {"value": "", "fieldId": 14, "fieldName": "014_editTextKassRegistraciiNomer", "fieldAlias": "Кассета регистрации"}, {"value": "", "fieldId": 15, "fieldName": "015_editTextPribBezopasnosti", "fieldAlias": "Прибор безопасности"}, {"value": "GDSA", "fieldId": 16, "fieldName": "016_editTextPribBezopasNomer", "fieldAlias": "Номер приб. безопасности"}, {"value": "", "fieldId": 17, "fieldName": "017_editTextKran", "fieldAlias": "Кран маш.-та"}, {"value": "", "fieldId": 18, "fieldName": "018_editTextKranNomer", "fieldAlias": "Номер крана"}, {"value": "", "fieldId": 19, "fieldName": "019_editTextPoezdNomer", "fieldAlias": "№ поезда"}, {"value": "", "fieldId": 20, "fieldName": "020_editTextPoezdVes", "fieldAlias": "Вес поезда"}, {"value": "", "fieldId": 21, "fieldName": "021_editTextPoezdOsi", "fieldAlias": "Кол.-во осей"}, {"value": "GS", "fieldId": 22, "fieldName": "022_editTextPoezdUslDlina", "fieldAlias": "Условная дл."}, {"value": "A", "fieldId": 23, "fieldName": "023_editTextStanciyaOtpravl", "fieldAlias": "Станция отпр."}, {"value": "", "fieldId": 24, "fieldName": "024_editTextVremyaOtpr", "fieldAlias": "Время отпр."}, {"value": "GS", "fieldId": 25, "fieldName": "025_editTextEmm", "fieldAlias": "Маршрут, №"}, {"value": "A", "fieldId": 26, "fieldName": "026_editTextStanciyaPrib", "fieldAlias": "Станция приб."}, {"value": "GA", "fieldId": 27, "fieldName": "027_editTextVremyaPrib", "fieldAlias": "Время приб."}, {"value": "G", "fieldId": 28, "fieldName": "028_textViewNormaTer", "fieldAlias": "Расход эл.энергии, норма"}, {"value": "SDA", "fieldId": 29, "fieldName": "029_textViewFaktTer", "fieldAlias": "Расход эл.энергии, факт"}, {"value": "G", "fieldId": 30, "fieldName": "030_textViewRekuperTer", "fieldAlias": "Расход эл.энергии, РТ"}, {"value": "DSA", "fieldId": 31, "fieldName": "031_editTextNePriminRT", "fieldAlias": "Причина неприменения РТ"}, {"value": "G", "fieldId": 32, "fieldName": "032_editTextDavlenieTM", "fieldAlias": "Давление в ТМ"}, {"value": "DFA", "fieldId": 33, "fieldName": "033_editTextVremyaSverki", "fieldAlias": "Время сверки"}, {"value": "G", "fieldId": 34, "fieldName": "034_editTextDspStanciiOtpr", "fieldAlias": "ДСП"}, {"value": "FAD", "fieldId": 35, "fieldName": "035_editTextDncUchastka", "fieldAlias": "ДНЦ"}, {"value": "G", "fieldId": 36, "fieldName": "036_editTextPotrebNazatie", "fieldAlias": "ВУ 45, потребное нажатие"}, {"value": "FDA", "fieldId": 37, "fieldName": "037_editTextFaktichNazatie", "fieldAlias": "ВУ 45, фактическое нажатие"}, {"value": "G", "fieldId": 38, "fieldName": "038_editTextKompoziciya", "fieldAlias": "Композиция"}, {"value": "ADF", "fieldId": 39, "fieldName": "039_editTextPlotnostTM", "fieldAlias": "Плотность ТМ"}, {"value": "GASDF", "fieldId": 40, "fieldName": "040_editTextVremyaVidachi", "fieldAlias": "Время выдачи"}, {"value": "GFAS", "fieldId": 41, "fieldName": "041_editTextNomerPoezdaSpravka", "fieldAlias": "Справку о торм. на п.№"}, {"value": "GFA", "fieldId": 42, "fieldName": "042_editTextSpravkuPeredal", "fieldAlias": "Получил от маш."}, {"value": "GFDSA", "fieldId": 43, "fieldName": "043_editTextSpravkuPoluchil", "fieldAlias": "Машинист, ТЧЭ"}]	2024-02-27 17:22:54.740175
9	11	1	[{"value": "Гильдебрант Алексей Викторович", "fieldId": 126, "fieldName": "001_name_user_resume_forma", "fieldAlias": "Фамилия Имя Отчество"}, {"value": "Java - Разработчик", "fieldId": 127, "fieldName": "002_position_resume_forma", "fieldAlias": "Должность"}, {"value": "89501212299", "fieldId": 128, "fieldName": "003_phone_resume_form", "fieldAlias": "Телефон"}, {"value": "mashinis@mail.ru", "fieldId": 129, "fieldName": "004_email_resume_forma", "fieldAlias": "Электронная почта"}, {"value": "Иркутск", "fieldId": 130, "fieldName": "005_city_resume_forma", "fieldAlias": "Город проживания"}, {"value": "80000", "fieldId": 131, "fieldName": "006_desired_salary_resume_forma", "fieldAlias": "Желаемая зарплата"}, {"value": "9:00 - 18:00", "fieldId": 132, "fieldName": "007_work_schedule_resume_forma", "fieldAlias": "График работы"}, {"value": "Полная, удаленный график", "fieldId": 133, "fieldName": "008_busyness_resume_forma", "fieldAlias": "Занятость"}, {"value": "24.06.1975", "fieldId": 134, "fieldName": "009_birthdate_salary_resume_forma", "fieldAlias": "Дата рождения"}, {"value": "Российская Федерация", "fieldId": 135, "fieldName": "010_citizenship_resume_forma", "fieldAlias": "Гражданство"}, {"value": "Муж.", "fieldId": 136, "fieldName": "011_gender_resume_forma", "fieldAlias": "Пол"}, {"value": "Женат", "fieldId": 137, "fieldName": "012_marital_status_resume_forma", "fieldAlias": "Семейное положение"}, {"value": "Высшее", "fieldId": 138, "fieldName": "013_education_resume_forma", "fieldAlias": "Образование"}, {"value": "22 года, РЖД; 6 месяцев, ОТУС (Стажер Java разработчика)", "fieldId": 139, "fieldName": "014_position_resume_forma", "fieldAlias": "Опыт работы (должность)"}, {"value": "22 года. 6 месяцев", "fieldId": 140, "fieldName": "015_work_experience_resume_forma", "fieldAlias": "Опыт работы (срок)"}, {"value": "РЖД, ОТУС", "fieldId": 141, "fieldName": "016_organization_resume_forma", "fieldAlias": "Название организации"}, {"value": "Разработка ПО на языке Java", "fieldId": 142, "fieldName": "017_function_resume_forma", "fieldAlias": "Должностные обязанности"}, {"value": "ИрНИТУ", "fieldId": 143, "fieldName": "018_educational_institution_resume_forma", "fieldAlias": "Учебное заведение"}, {"value": "Программист", "fieldId": 144, "fieldName": "019_specialization_resume_forma", "fieldAlias": "Специальность"}, {"value": "Кибернетики", "fieldId": 145, "fieldName": "020_faculty_resume_forma", "fieldAlias": "Факультет"}, {"value": "2018", "fieldId": 146, "fieldName": "021_year_graduation_resume_forma", "fieldAlias": "Год окончания обучения"}, {"value": "Разработчик на Spring Framework; Java Developer. Professional", "fieldId": 147, "fieldName": "022_course name_resume_forma", "fieldAlias": "Название курса"}, {"value": "ОТУС", "fieldId": 148, "fieldName": "023__educational_institution_resume_forma", "fieldAlias": "Учебное заведение"}, {"value": "2024", "fieldId": 149, "fieldName": "024_year_graduation_resume_forma", "fieldAlias": "Год окончания обучения"}, {"value": "2023 - 2025", "fieldId": 150, "fieldName": "025_period_study_resume_forma", "fieldAlias": "Период обучения"}, {"value": "Английский, начальный", "fieldId": 151, "fieldName": "026_foreign_languages_resume_forma", "fieldAlias": "Знание иностранных языков"}, {"value": "Профессиональные", "fieldId": 152, "fieldName": "027_computer skills_resume_forma", "fieldAlias": "Компьютерные навыки"}, {"value": "Служил", "fieldId": 153, "fieldName": "028_military_service_resume_forma", "fieldAlias": "Служба в армии"}, {"value": "Нет", "fieldId": 154, "fieldName": "029_medical_book_resume_forma", "fieldAlias": "Медицинская книжка"}, {"value": "Есть, категории A, B, C", "fieldId": 155, "fieldName": "030_driver_license_resume_forma", "fieldAlias": "Наличие водительских прав"}, {"value": "Тренажер, просмотр фильмов", "fieldId": 156, "fieldName": "031_free_time_resume_forma", "fieldAlias": "Занятия в свободное время"}, {"value": "Уверенный, стабильный, не конфликтный", "fieldId": 157, "fieldName": "032_personal_qualities_resume_forma", "fieldAlias": "Личные качества"}, {"value": "Java, Git, Maven, SQL, Postgres, HTML, CSS", "fieldId": 158, "fieldName": "033_professional qualities_resume_forma", "fieldAlias": "Профессиональные навыки"}, {"value": "Моя цель - получить должность Java разработчика. Я более 20 лет работал машинистом электровоза. Достиг предела развития в этой должности. Моя мотивация основана на стремлении постоянно развиваться в сфере Java Development. В настоящее время я активно обучаюсь в \\"Отус\\" в рамках программы Java Developer Professional с фокусом на Spring Framework.", "fieldId": 159, "fieldName": "034_about_me_resume_forma", "fieldAlias": "Раскажите о себе"}]	2024-02-29 23:09:36.577334
\.


--
-- Data for Name: fields; Type: TABLE DATA; Schema: public; Owner: user_pdf_form_filling
--

COPY public.fields (field_id, form_id, field_name, field_alias, created_at) FROM stdin;
1	1	001_editTextDataYavki	Дата поездки	2024-02-26 22:00:06.110391
2	1	002_editTextVremyaYavki	явка	2024-02-26 22:00:06.110391
3	1	003_editTextLokSeria	Локомотив серия	2024-02-26 22:00:06.110391
4	1	004_editTextLokNomer	№	2024-02-26 22:00:06.110391
5	1	005_editTextLokPripiska	депо приписки_ТЧ	2024-02-26 22:00:06.110391
6	1	006_editTextElekKarta	Номер версии ЭК	2024-02-26 22:00:06.110391
7	1	007_editTextDopPribor	Доп.приборы без-ти	2024-02-26 22:00:06.110391
10	1	010_editTextPomoshnik	Пом.маш.-та	2024-02-26 22:00:06.110391
11	1	011_editTextPomoshnikTabl	таб.№	2024-02-26 22:00:06.110391
12	1	012_editTextInstruktor	ТЧЭМИ	2024-02-26 22:00:06.110391
13	1	013_editTextKolonna	Колонна, №	2024-02-26 22:00:06.110391
14	1	014_editTextKassRegistraciiNomer	Кассета регистрации	2024-02-26 22:00:06.110391
15	1	015_editTextPribBezopasnosti	Прибор безопасности	2024-02-26 22:00:06.110391
16	1	016_editTextPribBezopasNomer	Номер приб. безопасности	2024-02-26 22:00:06.110391
17	1	017_editTextKran	Кран маш.-та	2024-02-26 22:00:06.110391
18	1	018_editTextKranNomer	Номер крана	2024-02-26 22:00:06.110391
19	1	019_editTextPoezdNomer	№ поезда	2024-02-26 22:00:06.110391
20	1	020_editTextPoezdVes	Вес поезда	2024-02-26 22:00:06.110391
21	1	021_editTextPoezdOsi	Кол.-во осей	2024-02-26 22:00:06.110391
22	1	022_editTextPoezdUslDlina	Условная дл.	2024-02-26 22:00:06.110391
23	1	023_editTextStanciyaOtpravl	Станция отпр.	2024-02-26 22:00:06.110391
24	1	024_editTextVremyaOtpr	Время отпр.	2024-02-26 22:00:06.110391
25	1	025_editTextEmm	Маршрут, №	2024-02-26 22:00:06.110391
26	1	026_editTextStanciyaPrib	Станция приб.	2024-02-26 22:00:06.110391
27	1	027_editTextVremyaPrib	Время приб.	2024-02-26 22:00:06.110391
28	1	028_textViewNormaTer	Расход эл.энергии, норма	2024-02-26 22:00:06.110391
29	1	029_textViewFaktTer	Расход эл.энергии, факт	2024-02-26 22:00:06.110391
30	1	030_textViewRekuperTer	Расход эл.энергии, РТ	2024-02-26 22:00:06.110391
31	1	031_editTextNePriminRT	Причина неприменения РТ	2024-02-26 22:00:06.110391
32	1	032_editTextDavlenieTM	Давление в ТМ	2024-02-26 22:00:06.110391
33	1	033_editTextVremyaSverki	Время сверки	2024-02-26 22:00:06.110391
34	1	034_editTextDspStanciiOtpr	ДСП	2024-02-26 22:00:06.110391
35	1	035_editTextDncUchastka	ДНЦ	2024-02-26 22:00:06.110391
36	1	036_editTextPotrebNazatie	ВУ 45, потребное нажатие	2024-02-26 22:00:06.110391
37	1	037_editTextFaktichNazatie	ВУ 45, фактическое нажатие	2024-02-26 22:00:06.110391
38	1	038_editTextKompoziciya	Композиция	2024-02-26 22:00:06.110391
39	1	039_editTextPlotnostTM	Плотность ТМ	2024-02-26 22:00:06.110391
40	1	040_editTextVremyaVidachi	Время выдачи	2024-02-26 22:00:06.110391
41	1	041_editTextNomerPoezdaSpravka	Справку о торм. на п.№	2024-02-26 22:00:06.110391
42	1	042_editTextSpravkuPeredal	Получил от маш.	2024-02-26 22:00:06.110391
43	1	043_editTextSpravkuPoluchil	Машинист, ТЧЭ	2024-02-26 22:00:06.110391
8	1	008_editTextMashinist	Машинист	2024-02-26 22:00:06.110391
9	1	009_editTextMashinistTabl	таб.№	2024-02-26 22:00:06.110391
45	7	002_UserProfile_FirstName	Ваше имя	2024-02-28 23:54:39.200593
46	7	003_UserProfile_Street	Улица	2024-02-28 23:54:39.200593
47	7	001_UserProfile_LastName	Ваша фамилия	2024-02-28 23:54:39.200593
48	7	004_UserProfile_House	Номер дома	2024-02-28 23:54:39.200593
49	7	007_UserProfile_Postcode	Почтовый индекс	2024-02-28 23:54:39.200593
50	7	006_UserProfile_Region	Область, край	2024-02-28 23:54:39.200593
51	7	008_UserProfile_City	Город проживания	2024-02-28 23:54:39.200593
52	7	009_UserProfile_Country	Страна проживания	2024-02-28 23:54:39.200593
53	7	010_UserProfile_Gender	Ваш пол	2024-02-28 23:54:39.200593
54	7	011_UserProfile_Height	Ваш рост	2024-02-28 23:54:39.200593
55	7	012_UserProfile_ShoeSize	Ваш размер обуви	2024-02-28 23:54:39.200593
56	7	005_UserProfile_Flat	Номер квартиры	2024-02-28 23:54:39.200593
126	11	001_name_user_resume_forma	Фамилия Имя Отчество	2024-02-29 22:54:46.573725
127	11	002_position_resume_forma	Должность	2024-02-29 22:54:46.573725
128	11	003_phone_resume_form	Телефон	2024-02-29 22:54:46.573725
129	11	004_email_resume_forma	Электронная почта	2024-02-29 22:54:46.573725
130	11	005_city_resume_forma	Город проживания	2024-02-29 22:54:46.573725
131	11	006_desired_salary_resume_forma	Желаемая зарплата	2024-02-29 22:54:46.573725
132	11	007_work_schedule_resume_forma	График работы	2024-02-29 22:54:46.573725
133	11	008_busyness_resume_forma	Занятость	2024-02-29 22:54:46.573725
134	11	009_birthdate_salary_resume_forma	Дата рождения	2024-02-29 22:54:46.573725
135	11	010_citizenship_resume_forma	Гражданство	2024-02-29 22:54:46.573725
136	11	011_gender_resume_forma	Пол	2024-02-29 22:54:46.573725
137	11	012_marital_status_resume_forma	Семейное положение	2024-02-29 22:54:46.573725
138	11	013_education_resume_forma	Образование	2024-02-29 22:54:46.573725
139	11	014_position_resume_forma	Опыт работы (должность)	2024-02-29 22:54:46.573725
140	11	015_work_experience_resume_forma	Опыт работы (срок)	2024-02-29 22:54:46.573725
141	11	016_organization_resume_forma	Название организации	2024-02-29 22:54:46.573725
142	11	017_function_resume_forma	Должностные обязанности	2024-02-29 22:54:46.573725
143	11	018_educational_institution_resume_forma	Учебное заведение	2024-02-29 22:54:46.573725
144	11	019_specialization_resume_forma	Специальность	2024-02-29 22:54:46.573725
145	11	020_faculty_resume_forma	Факультет	2024-02-29 22:54:46.573725
146	11	021_year_graduation_resume_forma	Год окончания обучения	2024-02-29 22:54:46.573725
147	11	022_course name_resume_forma	Название курса	2024-02-29 22:54:46.573725
148	11	023__educational_institution_resume_forma	Учебное заведение	2024-02-29 22:54:46.573725
149	11	024_year_graduation_resume_forma	Год окончания обучения	2024-02-29 22:54:46.573725
150	11	025_period_study_resume_forma	Период обучения	2024-02-29 22:54:46.573725
151	11	026_foreign_languages_resume_forma	Знание иностранных языков	2024-02-29 22:54:46.573725
152	11	027_computer skills_resume_forma	Компьютерные навыки	2024-02-29 22:54:46.573725
153	11	028_military_service_resume_forma	Служба в армии	2024-02-29 22:54:46.573725
154	11	029_medical_book_resume_forma	Медицинская книжка	2024-02-29 22:54:46.573725
155	11	030_driver_license_resume_forma	Наличие водительских прав	2024-02-29 22:54:46.573725
156	11	031_free_time_resume_forma	Занятия в свободное время	2024-02-29 22:54:46.573725
157	11	032_personal_qualities_resume_forma	Личные качества	2024-02-29 22:54:46.573725
158	11	033_professional qualities_resume_forma	Профессиональные навыки	2024-02-29 22:54:46.573725
159	11	034_about_me_resume_forma	Раскажите о себе	2024-02-29 22:54:46.573725
\.


--
-- Data for Name: forms; Type: TABLE DATA; Schema: public; Owner: user_pdf_form_filling
--

COPY public.forms (form_id, form_name, created_at, form_path) FROM stdin;
1	Пояснительная записка	2024-02-26 21:58:54.587	pdf/explanatory_note.pdf
7	Профиль пользователя	2024-02-28 23:54:39.083024	pdf/user_data_forma.pdf
11	Резюме	2024-02-29 22:54:46.417121	pdf/resume_form.pdf
\.


--
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: user_pdf_form_filling
--

COPY public.roles (role_id, role_name, role_alias, created_at) FROM stdin;
\.


--
-- Data for Name: user_roles; Type: TABLE DATA; Schema: public; Owner: user_pdf_form_filling
--

COPY public.user_roles (user_id, role_id, created_at) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: user_pdf_form_filling
--

COPY public.users (user_id, email, password, first_name, patronymic, second_name, created_at) FROM stdin;
2	isaliy@mail.ru	$2a$10$fO2lQ1WojPENyanDlpmYK.reIoLbOT2TLnNDeDfUR7YItMKtJ3lX.	Виктор	Абдулаевич	Исалиев	2024-02-27 00:36:42.112873
1	petrov@mail.ru	$2a$10$PkIGV2xrUZExN13HzsL0FOgG.LovbNJiLRZWUv17BqHKpDvlXOlnS	Иван	Иванович	Петров	2024-02-26 21:55:42.282466
\.


--
-- Name: contents_content_id_seq; Type: SEQUENCE SET; Schema: public; Owner: user_pdf_form_filling
--

SELECT pg_catalog.setval('public.contents_content_id_seq', 9, true);


--
-- Name: fields_field_id_seq; Type: SEQUENCE SET; Schema: public; Owner: user_pdf_form_filling
--

SELECT pg_catalog.setval('public.fields_field_id_seq', 159, true);


--
-- Name: forms_form_id_seq; Type: SEQUENCE SET; Schema: public; Owner: user_pdf_form_filling
--

SELECT pg_catalog.setval('public.forms_form_id_seq', 11, true);


--
-- Name: roles_role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: user_pdf_form_filling
--

SELECT pg_catalog.setval('public.roles_role_id_seq', 1, false);


--
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: user_pdf_form_filling
--

SELECT pg_catalog.setval('public.users_user_id_seq', 2, true);


--
-- Name: contents contents_pkey; Type: CONSTRAINT; Schema: public; Owner: user_pdf_form_filling
--

ALTER TABLE ONLY public.contents
    ADD CONSTRAINT contents_pkey PRIMARY KEY (content_id);


--
-- Name: fields fields_pkey; Type: CONSTRAINT; Schema: public; Owner: user_pdf_form_filling
--

ALTER TABLE ONLY public.fields
    ADD CONSTRAINT fields_pkey PRIMARY KEY (field_id);


--
-- Name: forms forms_form_name_key; Type: CONSTRAINT; Schema: public; Owner: user_pdf_form_filling
--

ALTER TABLE ONLY public.forms
    ADD CONSTRAINT forms_form_name_key UNIQUE (form_name);


--
-- Name: forms forms_pkey; Type: CONSTRAINT; Schema: public; Owner: user_pdf_form_filling
--

ALTER TABLE ONLY public.forms
    ADD CONSTRAINT forms_pkey PRIMARY KEY (form_id);


--
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: user_pdf_form_filling
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (role_id);


--
-- Name: forms unique_form_path; Type: CONSTRAINT; Schema: public; Owner: user_pdf_form_filling
--

ALTER TABLE ONLY public.forms
    ADD CONSTRAINT unique_form_path UNIQUE (form_path);


--
-- Name: user_roles user_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: user_pdf_form_filling
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id);


--
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: user_pdf_form_filling
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: user_pdf_form_filling
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- Name: contents contents_form_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: user_pdf_form_filling
--

ALTER TABLE ONLY public.contents
    ADD CONSTRAINT contents_form_id_fkey FOREIGN KEY (form_id) REFERENCES public.forms(form_id);


--
-- Name: contents contents_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: user_pdf_form_filling
--

ALTER TABLE ONLY public.contents
    ADD CONSTRAINT contents_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- Name: fields fields_form_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: user_pdf_form_filling
--

ALTER TABLE ONLY public.fields
    ADD CONSTRAINT fields_form_id_fkey FOREIGN KEY (form_id) REFERENCES public.forms(form_id);


--
-- Name: user_roles user_roles_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: user_pdf_form_filling
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.roles(role_id);


--
-- Name: user_roles user_roles_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: user_pdf_form_filling
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- PostgreSQL database dump complete
--

