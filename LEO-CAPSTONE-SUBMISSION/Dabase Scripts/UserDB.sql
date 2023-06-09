PGDMP     &    (                {            UserDB    15.2    15.2                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    17136    UserDB    DATABASE     �   CREATE DATABASE "UserDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE "UserDB";
                postgres    false                        2615    17137    user_schema    SCHEMA        CREATE SCHEMA user_schema;
    DROP SCHEMA user_schema;
                postgres    false            �            1259    17139    user    TABLE     �   CREATE TABLE user_schema."user" (
    user_id integer NOT NULL,
    username character varying(50),
    first_name character varying(50),
    last_name character varying(50),
    password character varying,
    role character varying
);
    DROP TABLE user_schema."user";
       user_schema         heap    postgres    false    6            �            1259    17138    UserDetails_user_id_seq    SEQUENCE     �   ALTER TABLE user_schema."user" ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME user_schema."UserDetails_user_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            user_schema          postgres    false    6    216            �            1259    17144    role    TABLE     \   CREATE TABLE user_schema.role (
    role_id integer NOT NULL,
    name character varying
);
    DROP TABLE user_schema.role;
       user_schema         heap    postgres    false    6            �            1259    17164    token    TABLE     C  CREATE TABLE user_schema.token (
    token_id integer NOT NULL,
    token_value character varying NOT NULL,
    role character varying,
    expired boolean DEFAULT false NOT NULL,
    revoked boolean DEFAULT false NOT NULL,
    username character varying,
    last_update timestamp with time zone DEFAULT now() NOT NULL
);
    DROP TABLE user_schema.token;
       user_schema         heap    postgres    false    6            �            1259    17511    token_token_id_seq    SEQUENCE     �   ALTER TABLE user_schema.token ALTER COLUMN token_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME user_schema.token_token_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            user_schema          postgres    false    219    6            �            1259    17151 	   user_role    TABLE     c   CREATE TABLE user_schema.user_role (
    user_id integer NOT NULL,
    role_id integer NOT NULL
);
 "   DROP TABLE user_schema.user_role;
       user_schema         heap    postgres    false    6                      0    17144    role 
   TABLE DATA           2   COPY user_schema.role (role_id, name) FROM stdin;
    user_schema          postgres    false    217                    0    17164    token 
   TABLE DATA           j   COPY user_schema.token (token_id, token_value, role, expired, revoked, username, last_update) FROM stdin;
    user_schema          postgres    false    219   8                 0    17139    user 
   TABLE DATA           _   COPY user_schema."user" (user_id, username, first_name, last_name, password, role) FROM stdin;
    user_schema          postgres    false    216   �                 0    17151 	   user_role 
   TABLE DATA           :   COPY user_schema.user_role (user_id, role_id) FROM stdin;
    user_schema          postgres    false    218   2                   0    0    UserDetails_user_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('user_schema."UserDetails_user_id_seq"', 11, true);
          user_schema          postgres    false    215                       0    0    token_token_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('user_schema.token_token_id_seq', 28, true);
          user_schema          postgres    false    220            y           2606    17150    role Role_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY user_schema.role
    ADD CONSTRAINT "Role_pkey" PRIMARY KEY (role_id);
 ?   ALTER TABLE ONLY user_schema.role DROP CONSTRAINT "Role_pkey";
       user_schema            postgres    false    217            w           2606    17143    user UserDetails_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY user_schema."user"
    ADD CONSTRAINT "UserDetails_pkey" PRIMARY KEY (user_id);
 H   ALTER TABLE ONLY user_schema."user" DROP CONSTRAINT "UserDetails_pkey";
       user_schema            postgres    false    216            {           2606    17170    token token_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY user_schema.token
    ADD CONSTRAINT token_pkey PRIMARY KEY (token_id);
 ?   ALTER TABLE ONLY user_schema.token DROP CONSTRAINT token_pkey;
       user_schema            postgres    false    219            |           2606    17159    user_role fk_role_id_role    FK CONSTRAINT     �   ALTER TABLE ONLY user_schema.user_role
    ADD CONSTRAINT fk_role_id_role FOREIGN KEY (role_id) REFERENCES user_schema.role(role_id) NOT VALID;
 H   ALTER TABLE ONLY user_schema.user_role DROP CONSTRAINT fk_role_id_role;
       user_schema          postgres    false    217    3193    218                  x������ � �         G  x���I��\������W<��3z7�A:S	QP:�N@��xS�R��pB��y�Y�Z�]|�Z9<J^�E2�;������;����-h�\/�ρ9�B�!-��&�����C�v^c�9��5R�Ikz��@��{���Ʃ b	�r�hTe� _��u�"M���`�!g(�/��MR���(Y���Ә{6b`T���S��?ב���ږ�Q�08��܁TEώ�ΰ���t�j�Ɍ������Me�.��ܬα�fj��QQ�w	el���Q����O��V�>0w 뙱
��H��9�X����� �g�����%���Kp�י0����LB^@U�9yh����bu$U�KU'2x�ԗx�g���
�����R�C/����OB�		��!L����t�a�^���7�Z)�@@8A���A&�N���M� �cZؾCk<�z�}�7m����2�gz�B��g�&���2h��]4����B�7�~S�$���X�>���5�Ș�����܈���̖�����nh� �Ss[����6���?0�L��>��h9�I��3���dAv��Uz�̠C����,�@�i����&͕1H���D)�a��Y��!O�u���eķ��W.(K�g��jV�e�t*k�7OT2����%w�}�JΩ��΃���v��0�='$Ӧ�.؆
�Tܘ������0f�U˖�s�䐁��Q���-�;� at��T/�Y"�k\F�|��c�dr�Z����5,���2�eW�b� ir��|�:�B�t���P�B(Y�\��]���u/!8@w����eK'�TTX^CD�lvP%�r.�y���nnX\+��htbCzBw}�x�f��@��=4|Ԁ�����Wie*<�]���}��S�ˉ��K�=�"��N�wI���A�9#�[猐����*Zpr^�g����l�Qo ���uh�BO�H=4�k�{�vzM�zqf�&�;.��se�}�s�+�,����e�8V�_B��o����<�p��V��h���#VrT��z�#C�hլCeH��	��2�!A�&��sOBHh��)��=�;5X�ꦐ�2��pGs�z��|@o��J�E�8�Ǣ�Go)�-b}�h�[���3���îN�@�rnf����|l��QT��ٟ��ǽ%��s�sL��O=[��p�:��]Y9��P0�&�r�&�V���.ϥ��v���ތ��X��б�jo������D�/�'�(q"���y����lY]���8As��ptr��;�p�(���j�r&v�RO��%���\E[[{G�G.��3},���Z��_�XƠ�1�5����Z���跩Șq�ʌpD��Y.�a�ׇ�.kw��p�;�����Ӭ�M�}Cfp��t.f�/��Q�x+]f���0(F]�f�����4@?턐���9a�#|\^'9��Ux2E�KhnQ���s"a,N�����`���M�����pH�6�É(����L�8�*�6h���i��Wsv�ƂMv���>-��s������bH�#�`�VQ+�YK��|�C�lZ����K��������?=� �         �  x�m�Is�@ �3����i��#� �Md15�5,�@���'1N*35U��ի���xd(��cv��&/xqá]~_4p��Yu��#� W0��ڔЍy�G�.�ʍ�dߎ3%*��^���>f�H������,����D����L�2�O��T���3�P�1��5\n�'�>`����w�7�^	s�Y��}M{B��i�t�� ;[���b�)tW��㛭�q�WxEY�)���i��&*�qzG��rN��Vc�]V0����G��,6��Z�9��Tૻ�p������e�9^��pEgR��t�=����݃6����r#�3�/����me��O�IS}���:i��'�Mp� �ʨtO�O���<�sa�v��}֊��|���"�5Ô�4S��ﺻq8�����+�ƅ,w4��p̛2u�`��Xha��}�"7��b�TSO��/�3O�]d-Z̞��`�	�.�@���BO��"ns�+:m]�b�!�??~���2�Y��|W�U����b��:�W���щ�ޒ{Q���8e�&ڧˀ0��5A�&����q�b�J��vU@l�8y/-ͽ���{������>���ߎu�{�̯M���y[d����r&iz�{�(m��LV�ۯ�ы��7+U,�            x������ � �     