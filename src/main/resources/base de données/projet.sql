PGDMP     4    5                {            projet    14.1    14.1 	    &           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            '           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            (           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            )           1262    24576    projet    DATABASE     b   CREATE DATABASE projet WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'French_France.1252';
    DROP DATABASE projet;
                postgres    false            �            1259    196706    demande    TABLE     �  CREATE TABLE public.demande (
    id integer NOT NULL,
    create_by character varying(255),
    creation_date timestamp without time zone NOT NULL,
    last_modified_date timestamp without time zone,
    modified_by character varying(255),
    commentaire character varying(255),
    etat character varying(255),
    fichier character varying(255),
    objet character varying(255),
    iddemandeur integer,
    idutilisateur integer
);
    DROP TABLE public.demande;
       public         heap    postgres    false            #          0    196706    demande 
   TABLE DATA           �   COPY public.demande (id, create_by, creation_date, last_modified_date, modified_by, commentaire, etat, fichier, objet, iddemandeur, idutilisateur) FROM stdin;
    public          postgres    false    219   �
       �           2606    196712    demande demande_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.demande
    ADD CONSTRAINT demande_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.demande DROP CONSTRAINT demande_pkey;
       public            postgres    false    219            �           2606    196763 #   demande fkb547dmnvxk53xm00vpe24c4mh    FK CONSTRAINT     �   ALTER TABLE ONLY public.demande
    ADD CONSTRAINT fkb547dmnvxk53xm00vpe24c4mh FOREIGN KEY (idutilisateur) REFERENCES public.utilisateur(id);
 M   ALTER TABLE ONLY public.demande DROP CONSTRAINT fkb547dmnvxk53xm00vpe24c4mh;
       public          postgres    false    219            �           2606    196758 #   demande fkerbvjya8cnyyskk7xpt4oe9t1    FK CONSTRAINT     �   ALTER TABLE ONLY public.demande
    ADD CONSTRAINT fkerbvjya8cnyyskk7xpt4oe9t1 FOREIGN KEY (iddemandeur) REFERENCES public.demandeur(id);
 M   ALTER TABLE ONLY public.demande DROP CONSTRAINT fkerbvjya8cnyyskk7xpt4oe9t1;
       public          postgres    false    219            #   �  x����n�@���S��ڝ�gﭢ 8P��*���T�m�>�p'/Ƭ!�u��F�鳬o����Zk���Y q��% ��*iPf�TW�)���O�qY�n����>���hI��W�MKגe��H�5��6%�_ȵ/W��\��\�W��W���\W��=��4M�vk��Y��7~�PF��R��^�	����2>��rM���rs��ܯɅ���_[O>6]�%7u���՛�s��.&�!�L�~s8�\ˑ9���0�F:�hUJS���>�.���bǲ�7��$���~"�j�y�3~�CRڎ������q5�#&5!"�(�Q��|E�v��{�0�����a~��Z� f��y5�i22E������wGÿ�Qq`���_z�a8Z�ק!`���Q%!֕�� ��[�T���~ɴL&���4Qf����]I~�"W�u����q5�}4� �~՘UX��S�# u���g耆{г��r#�w�J��߼;�M�7ٓ	]�01���{g�,�<������ɡ�o����N�Y|K���y%���*�>�-��{����L`�3�c{�)c��߅�3�RK�
�?�Ct�a��$�n��$��}1��ߓ��j��r��W��w     