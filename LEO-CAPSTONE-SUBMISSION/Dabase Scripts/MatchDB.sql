PGDMP         '                {            MatchDB    15.2    15.2 
    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    17132    MatchDB    DATABASE     �   CREATE DATABASE "MatchDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE "MatchDB";
                postgres    false                        2615    17377    match_schema    SCHEMA        CREATE SCHEMA match_schema;
    DROP SCHEMA match_schema;
                postgres    false            �            1259    17379    matches    TABLE     U  CREATE TABLE match_schema.matches (
    match_id integer NOT NULL,
    field_id integer NOT NULL,
    tournament_id integer NOT NULL,
    players character varying,
    teams character varying,
    start_date timestamp with time zone,
    end_date timestamp with time zone,
    last_update timestamp with time zone DEFAULT now() NOT NULL
);
 !   DROP TABLE match_schema.matches;
       match_schema         heap    postgres    false    6            �            1259    17378    matches_match_id_seq    SEQUENCE     �   ALTER TABLE match_schema.matches ALTER COLUMN match_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME match_schema.matches_match_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            match_schema          postgres    false    6    216            �          0    17379    matches 
   TABLE DATA           }   COPY match_schema.matches (match_id, field_id, tournament_id, players, teams, start_date, end_date, last_update) FROM stdin;
    match_schema          postgres    false    216   �
       �           0    0    matches_match_id_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('match_schema.matches_match_id_seq', 12, true);
          match_schema          postgres    false    215            h           2606    17401    matches matches_pkey 
   CONSTRAINT     k   ALTER TABLE ONLY match_schema.matches
    ADD CONSTRAINT matches_pkey PRIMARY KEY (match_id, last_update);
 D   ALTER TABLE ONLY match_schema.matches DROP CONSTRAINT matches_pkey;
       match_schema            postgres    false    216    216            �   �   x����� �3T��u��H-鿎�S�
�e$$�<���@p$d\}2�a���az�>�3W%/_+��F�FA$��腤ψ&0�x�%����4�
�v*ȡԣ/Ts�u^��Z�=#n����ܹ��;Ы�ۜ�6�Ϳ�=c�?�L�F     