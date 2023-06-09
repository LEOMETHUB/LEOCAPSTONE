PGDMP         (                {            TournamentDB    15.2    15.2                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    17133    TournamentDB    DATABASE     �   CREATE DATABASE "TournamentDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE "TournamentDB";
                postgres    false                        2615    17219    tournament_schema    SCHEMA     !   CREATE SCHEMA tournament_schema;
    DROP SCHEMA tournament_schema;
                postgres    false            �            1259    17362    teams    TABLE     �   CREATE TABLE tournament_schema.teams (
    team_id integer NOT NULL,
    team_name character varying NOT NULL,
    last_update date DEFAULT now() NOT NULL
);
 $   DROP TABLE tournament_schema.teams;
       tournament_schema         heap    postgres    false    6            �            1259    17278    tournament_teams    TABLE     �   CREATE TABLE tournament_schema.tournament_teams (
    tournament_id integer NOT NULL,
    team_id integer NOT NULL,
    last_update timestamp with time zone DEFAULT now() NOT NULL
);
 /   DROP TABLE tournament_schema.tournament_teams;
       tournament_schema         heap    postgres    false    6            �            1259    17220    tournaments    TABLE     m  CREATE TABLE tournament_schema.tournaments (
    tournament_id integer NOT NULL,
    tournament_name character varying,
    sport_category character varying,
    tournament_style character varying,
    start_date timestamp with time zone,
    end_date timestamp with time zone,
    last_update timestamp with time zone DEFAULT now(),
    teams character varying
);
 *   DROP TABLE tournament_schema.tournaments;
       tournament_schema         heap    postgres    false    6            �            1259    17228    tournaments_tournament_id_seq    SEQUENCE     �   ALTER TABLE tournament_schema.tournaments ALTER COLUMN tournament_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME tournament_schema.tournaments_tournament_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            tournament_schema          postgres    false    6    215                      0    17362    teams 
   TABLE DATA           K   COPY tournament_schema.teams (team_id, team_name, last_update) FROM stdin;
    tournament_schema          postgres    false    218   e                 0    17278    tournament_teams 
   TABLE DATA           Z   COPY tournament_schema.tournament_teams (tournament_id, team_id, last_update) FROM stdin;
    tournament_schema          postgres    false    217                    0    17220    tournaments 
   TABLE DATA           �   COPY tournament_schema.tournaments (tournament_id, tournament_name, sport_category, tournament_style, start_date, end_date, last_update, teams) FROM stdin;
    tournament_schema          postgres    false    215   �                  0    0    tournaments_tournament_id_seq    SEQUENCE SET     V   SELECT pg_catalog.setval('tournament_schema.tournaments_tournament_id_seq', 7, true);
          tournament_schema          postgres    false    216            t           2606    17371    teams teams_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY tournament_schema.teams
    ADD CONSTRAINT teams_pkey PRIMARY KEY (team_id);
 E   ALTER TABLE ONLY tournament_schema.teams DROP CONSTRAINT teams_pkey;
       tournament_schema            postgres    false    218            r           2606    17227    tournaments tournaments_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY tournament_schema.tournaments
    ADD CONSTRAINT tournaments_pkey PRIMARY KEY (tournament_id);
 Q   ALTER TABLE ONLY tournament_schema.tournaments DROP CONSTRAINT tournaments_pkey;
       tournament_schema            postgres    false    215            u           2606    17372 &   tournament_teams fk_tournament_id_team    FK CONSTRAINT     �   ALTER TABLE ONLY tournament_schema.tournament_teams
    ADD CONSTRAINT fk_tournament_id_team FOREIGN KEY (team_id) REFERENCES tournament_schema.teams(team_id) NOT VALID;
 [   ALTER TABLE ONLY tournament_schema.tournament_teams DROP CONSTRAINT fk_tournament_id_team;
       tournament_schema          postgres    false    218    3188    217            v           2606    17357 ,   tournament_teams fk_tournament_id_tournament    FK CONSTRAINT     �   ALTER TABLE ONLY tournament_schema.tournament_teams
    ADD CONSTRAINT fk_tournament_id_tournament FOREIGN KEY (tournament_id) REFERENCES tournament_schema.tournaments(tournament_id) NOT VALID;
 a   ALTER TABLE ONLY tournament_schema.tournament_teams DROP CONSTRAINT fk_tournament_id_tournament;
       tournament_schema          postgres    false    3186    217    215               �   x�]���@D뽯��(��B5F���l`s�w�����3��M&o�pa��d-�U��R�,B����.Y�"H���$�3X���~K1�#;��ى1'c9�(r��$4$���l���& G�9���7�t^x�Ģ3��
�I���tY-��l�F�>[��d�E�         a   x����� k<E�(��c̒��\$w�����V�C�羣�ʹ|��S��$�čD"ёx�@}}}}}}$vIL��#�]wy��}�e}\         F  x���Kk�0��Χ�}��3q|�;���]vI����Ƿ��t!�v[@� !駿L��q����s%�Z�3E��|z��p��k��~ok��D��k�j��"�RvC�2K��"�BJ��(Ldʙ<e)b�#�$�Q�T���h`�ۢ�Eez�-:_�n���h��l]x۬�%(fq�z���9˶ ���Q̧7ui�U ��
�T$?��Y򡪂O�j��Y���fد�
�Th.��<Vb�J$FE�Lw͇�@��3�ƭ� �H���01��|��3WS��ݔ�?�Ӭ�	�Ē,v�Q2���\��wN�z��(����     