PGDMP     2    '                {            TeamDB    15.2    15.2 
    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    17135    TeamDB    DATABASE     �   CREATE DATABASE "TeamDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE "TeamDB";
                postgres    false                        2615    17241    team_schema    SCHEMA        CREATE SCHEMA team_schema;
    DROP SCHEMA team_schema;
                postgres    false            �            1259    17242    teams    TABLE     �   CREATE TABLE team_schema.teams (
    team_id integer NOT NULL,
    team_name character varying(30),
    last_update timestamp with time zone DEFAULT now() NOT NULL
);
    DROP TABLE team_schema.teams;
       team_schema         heap    postgres    false    6            �            1259    17245    teams_team_id_seq    SEQUENCE     �   ALTER TABLE team_schema.teams ALTER COLUMN team_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME team_schema.teams_team_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            team_schema          postgres    false    215    6            �          0    17242    teams 
   TABLE DATA           E   COPY team_schema.teams (team_id, team_name, last_update) FROM stdin;
    team_schema          postgres    false    215   �	       �           0    0    teams_team_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('team_schema.teams_team_id_seq', 8, true);
          team_schema          postgres    false    216            h           2606    17253    teams teams_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY team_schema.teams
    ADD CONSTRAINT teams_pkey PRIMARY KEY (team_id);
 ?   ALTER TABLE ONLY team_schema.teams DROP CONSTRAINT teams_pkey;
       team_schema            postgres    false    215            �   �   x�}н
�@�z�ۋ�%]��FEL�%Y��y����������N��a<��R�7ˈ�q��m�`qV��&<1�=}Fg�q$��X��}�ʉ-;<���"m�kS�+�-}Hh�n
��1��^�����=>Idp�{Pw����1W�g�T���r�Yu;h�7�vA|BXb     