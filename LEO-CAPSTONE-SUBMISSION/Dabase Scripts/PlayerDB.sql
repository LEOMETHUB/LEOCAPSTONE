PGDMP     &    '                {            PlayerDB    15.2    15.2 
    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    17261    PlayerDB    DATABASE     �   CREATE DATABASE "PlayerDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE "PlayerDB";
                postgres    false                        2615    17262    player_schema    SCHEMA        CREATE SCHEMA player_schema;
    DROP SCHEMA player_schema;
                postgres    false            �            1259    17264    players    TABLE       CREATE TABLE player_schema.players (
    player_id integer NOT NULL,
    first_name character varying,
    last_name character varying,
    country character varying,
    team_id integer NOT NULL,
    last_update timestamp with time zone DEFAULT now() NOT NULL
);
 "   DROP TABLE player_schema.players;
       player_schema         heap    postgres    false    6            �            1259    17263    players_player_id_seq    SEQUENCE     �   ALTER TABLE player_schema.players ALTER COLUMN player_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME player_schema.players_player_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            player_schema          postgres    false    6    216            �          0    17264    players 
   TABLE DATA           i   COPY player_schema.players (player_id, first_name, last_name, country, team_id, last_update) FROM stdin;
    player_schema          postgres    false    216   x
       �           0    0    players_player_id_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('player_schema.players_player_id_seq', 25, true);
          player_schema          postgres    false    215            h           2606    17270    players players_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY player_schema.players
    ADD CONSTRAINT players_pkey PRIMARY KEY (player_id);
 E   ALTER TABLE ONLY player_schema.players DROP CONSTRAINT players_pkey;
       player_schema            postgres    false    216            �   b  x���=o�0��ͯ�^Q哯� ��TB�R�.���Ʈ�C~}/�5n'gxr��7��;Rg����	�0Na:���(���4���qcE��BayrTJ���'��빱�M���\�KZ�u�>�ᅜ��7
s"/�h��������6���hI�cmj��8)��x���,5�F�4
y����eI��i/�PP�Gc+���[ꖰ#�x0~���G;	^���%d�4źmk4���7��Km��?0�F�}��F��{�EÙ�Crb��u1Y
��̴��n��G:p��^�k������e�-5
+�7�/c��R-r���~�k�m*�C1��o]�����܀�J���C?gn��     