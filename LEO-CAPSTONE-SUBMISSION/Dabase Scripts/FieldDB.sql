PGDMP     0    &                {            FieldDB    15.2    15.2 
    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    17131    FieldDB    DATABASE     �   CREATE DATABASE "FieldDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE "FieldDB";
                postgres    false                        2615    17178    field_schema    SCHEMA        CREATE SCHEMA field_schema;
    DROP SCHEMA field_schema;
                postgres    false            �            1259    17180    fields    TABLE     �   CREATE TABLE field_schema.fields (
    field_id integer NOT NULL,
    field_name character varying,
    field_address character varying,
    field_capacity integer,
    last_update timestamp with time zone DEFAULT now()
);
     DROP TABLE field_schema.fields;
       field_schema         heap    postgres    false    6            �            1259    17179    FieldDB_field_id_seq    SEQUENCE     �   ALTER TABLE field_schema.fields ALTER COLUMN field_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME field_schema."FieldDB_field_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            field_schema          postgres    false    6    216            �          0    17180    fields 
   TABLE DATA           h   COPY field_schema.fields (field_id, field_name, field_address, field_capacity, last_update) FROM stdin;
    field_schema          postgres    false    216   6
       �           0    0    FieldDB_field_id_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('field_schema."FieldDB_field_id_seq"', 4, true);
          field_schema          postgres    false    215            h           2606    17186    fields FieldDB_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY field_schema.fields
    ADD CONSTRAINT "FieldDB_pkey" PRIMARY KEY (field_id);
 E   ALTER TABLE ONLY field_schema.fields DROP CONSTRAINT "FieldDB_pkey";
       field_schema            postgres    false    216            �   �   x����J�@�ϛ���Ɛ����FP�К�^�����ݲ�)�o�3�b�Zz�0���o&ͺBeY�hH)�TNj3�B<F����+��Cl����G��SL5ͮ�bJ��2͖I�e/ʫ�6H���P˱�=�En���Bt�=��S�mI����f���	M���c%���g͖Zs �j�!�G�2��s�(fٹ��}<ͣ��j֞�Hn�m��t?!;o�}�����/�l�l���(�_c�k�     