PGDMP         (                {            TicketDB    15.2    15.2 
    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    17134    TicketDB    DATABASE     �   CREATE DATABASE "TicketDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE "TicketDB";
                postgres    false                        2615    17203    ticket_schema    SCHEMA        CREATE SCHEMA ticket_schema;
    DROP SCHEMA ticket_schema;
                postgres    false            �            1259    17205    tickets    TABLE     �   CREATE TABLE ticket_schema.tickets (
    ticket_id integer NOT NULL,
    customer_name character varying,
    ticket_price double precision,
    match_id integer NOT NULL,
    last_update timestamp with time zone DEFAULT now(),
    field_id integer
);
 "   DROP TABLE ticket_schema.tickets;
       ticket_schema         heap    postgres    false    6            �            1259    17204    tickets_ticket_id_seq    SEQUENCE     �   ALTER TABLE ticket_schema.tickets ALTER COLUMN ticket_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME ticket_schema.tickets_ticket_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            ticket_schema          postgres    false    6    216            �          0    17205    tickets 
   TABLE DATA           q   COPY ticket_schema.tickets (ticket_id, customer_name, ticket_price, match_id, last_update, field_id) FROM stdin;
    ticket_schema          postgres    false    216   t
       �           0    0    tickets_ticket_id_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('ticket_schema.tickets_ticket_id_seq', 11, true);
          ticket_schema          postgres    false    215            h           2606    17211    tickets tickets_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY ticket_schema.tickets
    ADD CONSTRAINT tickets_pkey PRIMARY KEY (ticket_id);
 E   ALTER TABLE ONLY ticket_schema.tickets DROP CONSTRAINT tickets_pkey;
       ticket_schema            postgres    false    216            �   �   x��ѱj1�Y~
������lM���d���.Vj_�����d4�~~i8ᅒ|�P��R
4/�=+�#ո��V�+=*;�'5��f�Y��g���4e'r9����������	{8d��CY2:�A�����>�q�|�R�m�N�a�S����-򚦩�u�n�����R��4� G�]L��_ԇҊ^)�߱F���M��#��������?VB�_?��     