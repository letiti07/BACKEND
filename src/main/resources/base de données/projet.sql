PGDMP     #                    {            projet    14.1    14.1 B    _           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            `           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            a           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            b           1262    24576    projet    DATABASE     b   CREATE DATABASE projet WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'French_France.1252';
    DROP DATABASE projet;
                postgres    false            �            1259    123478    demande    TABLE     �  CREATE TABLE public.demande (
    id integer NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    last_modified_date timestamp without time zone,
    fichier character varying(255),
    objet character varying(255),
    iddemandeur integer,
    idutilisateur integer,
    create_by character varying(255),
    modified_by character varying(255),
    etat character varying(255),
    commentaire character varying(255)
);
    DROP TABLE public.demande;
       public         heap    postgres    false            �            1259    123485 	   demandeur    TABLE     �  CREATE TABLE public.demandeur (
    id integer NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    last_modified_date timestamp without time zone,
    boite_postale character varying(255),
    email character varying(255),
    ifu character varying(255),
    nom character varying(255),
    rccm character varying(255),
    compte character varying(255),
    create_by character varying(255),
    modified_by character varying(255),
    cnib_depositaire character varying(255),
    nom_depositaire character varying(255),
    prenom_depositaire character varying(255),
    tel character varying(255),
    tel_depositaire character varying(255)
);
    DROP TABLE public.demandeur;
       public         heap    postgres    false            �            1259    123492 
   facturefon    TABLE     �  CREATE TABLE public.facturefon (
    id integer NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    last_modified_date timestamp without time zone,
    idlocationfon integer,
    create_by character varying(255),
    modified_by character varying(255),
    etat character varying(255),
    idtva integer,
    code character varying(255),
    debut_periode timestamp without time zone,
    fin_periode timestamp without time zone,
    idtype_paiement integer,
    duree integer
);
    DROP TABLE public.facturefon;
       public         heap    postgres    false            �            1259    123477    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public          postgres    false            �            1259    123499 
   liaisonfon    TABLE     }  CREATE TABLE public.liaisonfon (
    id integer NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    last_modified_date timestamp without time zone,
    debut character varying(255),
    distance real,
    fin character varying(255),
    nbrefibre integer,
    idlocationfon integer,
    create_by character varying(255),
    modified_by character varying(255)
);
    DROP TABLE public.liaisonfon;
       public         heap    postgres    false            �            1259    147564    liaisonfon_pointconnexionfon    TABLE     �   CREATE TABLE public.liaisonfon_pointconnexionfon (
    liaisonfon_id integer NOT NULL,
    pointconnexion_id integer NOT NULL
);
 0   DROP TABLE public.liaisonfon_pointconnexionfon;
       public         heap    postgres    false            �            1259    123506    locationfon    TABLE     �  CREATE TABLE public.locationfon (
    id integer NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    last_modified_date timestamp without time zone,
    coutmetrelineaire double precision,
    duree integer,
    fraisconnexion double precision,
    periodedebut timestamp without time zone,
    periodedefin timestamp without time zone,
    id_demande integer,
    create_by character varying(255),
    modified_by character varying(255),
    etat character varying(255)
);
    DROP TABLE public.locationfon;
       public         heap    postgres    false            �            1259    123511 
   permission    TABLE       CREATE TABLE public.permission (
    id integer NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    last_modified_date timestamp without time zone,
    nom character varying(255),
    create_by character varying(255),
    modified_by character varying(255)
);
    DROP TABLE public.permission;
       public         heap    postgres    false            �            1259    123516    pointconnexion    TABLE     �  CREATE TABLE public.pointconnexion (
    id integer NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    last_modified_date timestamp without time zone,
    nom character varying(255),
    create_by character varying(255),
    modified_by character varying(255),
    fraishebergement double precision,
    typehebergement character varying(255),
    disponible boolean,
    idville integer
);
 "   DROP TABLE public.pointconnexion;
       public         heap    postgres    false            �            1259    123523    recu_paiementfon    TABLE       CREATE TABLE public.recu_paiementfon (
    id integer NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    last_modified_date timestamp without time zone,
    idfacturefon integer,
    create_by character varying(255),
    modified_by character varying(255)
);
 $   DROP TABLE public.recu_paiementfon;
       public         heap    postgres    false            �            1259    123528    role    TABLE       CREATE TABLE public.role (
    id integer NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    last_modified_date timestamp without time zone,
    nom character varying(255),
    create_by character varying(255),
    modified_by character varying(255)
);
    DROP TABLE public.role;
       public         heap    postgres    false            �            1259    123533    role_permission    TABLE     j   CREATE TABLE public.role_permission (
    role_id integer NOT NULL,
    permission_id integer NOT NULL
);
 #   DROP TABLE public.role_permission;
       public         heap    postgres    false            �            1259    155746    tva    TABLE       CREATE TABLE public.tva (
    id integer NOT NULL,
    create_by character varying(255),
    creation_date timestamp without time zone NOT NULL,
    last_modified_date timestamp without time zone,
    modified_by character varying(255),
    tva double precision,
    actif boolean
);
    DROP TABLE public.tva;
       public         heap    postgres    false            �            1259    163938    type_paiement    TABLE     D  CREATE TABLE public.type_paiement (
    id integer NOT NULL,
    create_by character varying(255),
    creation_date timestamp without time zone NOT NULL,
    last_modified_date timestamp without time zone,
    modified_by character varying(255),
    type_paiement character varying(255),
    code character varying(255)
);
 !   DROP TABLE public.type_paiement;
       public         heap    postgres    false            �            1259    123536    utilisateur    TABLE     U  CREATE TABLE public.utilisateur (
    id integer NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    last_modified_date timestamp without time zone,
    active boolean,
    adresse character varying(255),
    date_de_naissance timestamp without time zone,
    email character varying(255),
    matricule character varying(255),
    motdepasse character varying(255),
    nom character varying(255),
    prenom character varying(255),
    sexe character varying(255),
    tel character varying(255),
    create_by character varying(255),
    modified_by character varying(255)
);
    DROP TABLE public.utilisateur;
       public         heap    postgres    false            �            1259    123543    utilisateur_role    TABLE     l   CREATE TABLE public.utilisateur_role (
    utilisateur_id integer NOT NULL,
    role_id integer NOT NULL
);
 $   DROP TABLE public.utilisateur_role;
       public         heap    postgres    false            �            1259    172130    ville    TABLE       CREATE TABLE public.ville (
    id integer NOT NULL,
    create_by character varying(255),
    creation_date timestamp without time zone NOT NULL,
    last_modified_date timestamp without time zone,
    modified_by character varying(255),
    nom character varying(255)
);
    DROP TABLE public.ville;
       public         heap    postgres    false            M          0    123478    demande 
   TABLE DATA           �   COPY public.demande (id, creation_date, last_modified_date, fichier, objet, iddemandeur, idutilisateur, create_by, modified_by, etat, commentaire) FROM stdin;
    public          postgres    false    210   �_       N          0    123485 	   demandeur 
   TABLE DATA           �   COPY public.demandeur (id, creation_date, last_modified_date, boite_postale, email, ifu, nom, rccm, compte, create_by, modified_by, cnib_depositaire, nom_depositaire, prenom_depositaire, tel, tel_depositaire) FROM stdin;
    public          postgres    false    211   b       O          0    123492 
   facturefon 
   TABLE DATA           �   COPY public.facturefon (id, creation_date, last_modified_date, idlocationfon, create_by, modified_by, etat, idtva, code, debut_periode, fin_periode, idtype_paiement, duree) FROM stdin;
    public          postgres    false    212   !d       P          0    123499 
   liaisonfon 
   TABLE DATA           �   COPY public.liaisonfon (id, creation_date, last_modified_date, debut, distance, fin, nbrefibre, idlocationfon, create_by, modified_by) FROM stdin;
    public          postgres    false    213   e       Y          0    147564    liaisonfon_pointconnexionfon 
   TABLE DATA           X   COPY public.liaisonfon_pointconnexionfon (liaisonfon_id, pointconnexion_id) FROM stdin;
    public          postgres    false    222   �f       Q          0    123506    locationfon 
   TABLE DATA           �   COPY public.locationfon (id, creation_date, last_modified_date, coutmetrelineaire, duree, fraisconnexion, periodedebut, periodedefin, id_demande, create_by, modified_by, etat) FROM stdin;
    public          postgres    false    214   g       R          0    123511 
   permission 
   TABLE DATA           h   COPY public.permission (id, creation_date, last_modified_date, nom, create_by, modified_by) FROM stdin;
    public          postgres    false    215   zh       S          0    123516    pointconnexion 
   TABLE DATA           �   COPY public.pointconnexion (id, creation_date, last_modified_date, nom, create_by, modified_by, fraishebergement, typehebergement, disponible, idville) FROM stdin;
    public          postgres    false    216   �h       T          0    123523    recu_paiementfon 
   TABLE DATA           w   COPY public.recu_paiementfon (id, creation_date, last_modified_date, idfacturefon, create_by, modified_by) FROM stdin;
    public          postgres    false    217   �i       U          0    123528    role 
   TABLE DATA           b   COPY public.role (id, creation_date, last_modified_date, nom, create_by, modified_by) FROM stdin;
    public          postgres    false    218   j       V          0    123533    role_permission 
   TABLE DATA           A   COPY public.role_permission (role_id, permission_id) FROM stdin;
    public          postgres    false    219   ij       Z          0    155746    tva 
   TABLE DATA           h   COPY public.tva (id, create_by, creation_date, last_modified_date, modified_by, tva, actif) FROM stdin;
    public          postgres    false    223   �j       [          0    163938    type_paiement 
   TABLE DATA           {   COPY public.type_paiement (id, create_by, creation_date, last_modified_date, modified_by, type_paiement, code) FROM stdin;
    public          postgres    false    224   &k       W          0    123536    utilisateur 
   TABLE DATA           �   COPY public.utilisateur (id, creation_date, last_modified_date, active, adresse, date_de_naissance, email, matricule, motdepasse, nom, prenom, sexe, tel, create_by, modified_by) FROM stdin;
    public          postgres    false    220   Ck       X          0    123543    utilisateur_role 
   TABLE DATA           C   COPY public.utilisateur_role (utilisateur_id, role_id) FROM stdin;
    public          postgres    false    221   m       \          0    172130    ville 
   TABLE DATA           c   COPY public.ville (id, create_by, creation_date, last_modified_date, modified_by, nom) FROM stdin;
    public          postgres    false    225   7m       c           0    0    hibernate_sequence    SEQUENCE SET     B   SELECT pg_catalog.setval('public.hibernate_sequence', 487, true);
          public          postgres    false    209            �           2606    123484    demande demande_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.demande
    ADD CONSTRAINT demande_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.demande DROP CONSTRAINT demande_pkey;
       public            postgres    false    210            �           2606    123491    demandeur demandeur_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.demandeur
    ADD CONSTRAINT demandeur_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.demandeur DROP CONSTRAINT demandeur_pkey;
       public            postgres    false    211            �           2606    123498    facturefon facturefon_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.facturefon
    ADD CONSTRAINT facturefon_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.facturefon DROP CONSTRAINT facturefon_pkey;
       public            postgres    false    212            �           2606    123505    liaisonfon liaisonfon_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.liaisonfon
    ADD CONSTRAINT liaisonfon_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.liaisonfon DROP CONSTRAINT liaisonfon_pkey;
       public            postgres    false    213            �           2606    123510    locationfon locationfon_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.locationfon
    ADD CONSTRAINT locationfon_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.locationfon DROP CONSTRAINT locationfon_pkey;
       public            postgres    false    214            �           2606    123515    permission permission_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.permission
    ADD CONSTRAINT permission_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.permission DROP CONSTRAINT permission_pkey;
       public            postgres    false    215            �           2606    123522 "   pointconnexion pointconnexion_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.pointconnexion
    ADD CONSTRAINT pointconnexion_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.pointconnexion DROP CONSTRAINT pointconnexion_pkey;
       public            postgres    false    216            �           2606    123527 &   recu_paiementfon recu_paiementfon_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.recu_paiementfon
    ADD CONSTRAINT recu_paiementfon_pkey PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.recu_paiementfon DROP CONSTRAINT recu_paiementfon_pkey;
       public            postgres    false    217            �           2606    123532    role role_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.role DROP CONSTRAINT role_pkey;
       public            postgres    false    218            �           2606    155752    tva tva_pkey 
   CONSTRAINT     J   ALTER TABLE ONLY public.tva
    ADD CONSTRAINT tva_pkey PRIMARY KEY (id);
 6   ALTER TABLE ONLY public.tva DROP CONSTRAINT tva_pkey;
       public            postgres    false    223            �           2606    163944     type_paiement type_paiement_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.type_paiement
    ADD CONSTRAINT type_paiement_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.type_paiement DROP CONSTRAINT type_paiement_pkey;
       public            postgres    false    224            �           2606    123542    utilisateur utilisateur_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT utilisateur_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.utilisateur DROP CONSTRAINT utilisateur_pkey;
       public            postgres    false    220            �           2606    172136    ville ville_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.ville
    ADD CONSTRAINT ville_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.ville DROP CONSTRAINT ville_pkey;
       public            postgres    false    225            �           2606    123566 '   locationfon fk3g4pbifqmwn3bi0l106fvj8mn    FK CONSTRAINT     �   ALTER TABLE ONLY public.locationfon
    ADD CONSTRAINT fk3g4pbifqmwn3bi0l106fvj8mn FOREIGN KEY (id_demande) REFERENCES public.demande(id);
 Q   ALTER TABLE ONLY public.locationfon DROP CONSTRAINT fk3g4pbifqmwn3bi0l106fvj8mn;
       public          postgres    false    3225    214    210            �           2606    123596 ,   utilisateur_role fk6kifvrsfkpqn502r5ipjl5pvu    FK CONSTRAINT     �   ALTER TABLE ONLY public.utilisateur_role
    ADD CONSTRAINT fk6kifvrsfkpqn502r5ipjl5pvu FOREIGN KEY (utilisateur_id) REFERENCES public.utilisateur(id);
 V   ALTER TABLE ONLY public.utilisateur_role DROP CONSTRAINT fk6kifvrsfkpqn502r5ipjl5pvu;
       public          postgres    false    3243    220    221            �           2606    147567 8   liaisonfon_pointconnexionfon fka38hop5qf45tt0p19b9e0vbj2    FK CONSTRAINT     �   ALTER TABLE ONLY public.liaisonfon_pointconnexionfon
    ADD CONSTRAINT fka38hop5qf45tt0p19b9e0vbj2 FOREIGN KEY (pointconnexion_id) REFERENCES public.pointconnexion(id);
 b   ALTER TABLE ONLY public.liaisonfon_pointconnexionfon DROP CONSTRAINT fka38hop5qf45tt0p19b9e0vbj2;
       public          postgres    false    222    216    3237            �           2606    123586 +   role_permission fka6jx8n8xkesmjmv6jqug6bg68    FK CONSTRAINT     �   ALTER TABLE ONLY public.role_permission
    ADD CONSTRAINT fka6jx8n8xkesmjmv6jqug6bg68 FOREIGN KEY (role_id) REFERENCES public.role(id);
 U   ALTER TABLE ONLY public.role_permission DROP CONSTRAINT fka6jx8n8xkesmjmv6jqug6bg68;
       public          postgres    false    3241    218    219            �           2606    123591 ,   utilisateur_role fkad9wf1u7gjbx2p2y9hs8ow39x    FK CONSTRAINT     �   ALTER TABLE ONLY public.utilisateur_role
    ADD CONSTRAINT fkad9wf1u7gjbx2p2y9hs8ow39x FOREIGN KEY (role_id) REFERENCES public.role(id);
 V   ALTER TABLE ONLY public.utilisateur_role DROP CONSTRAINT fkad9wf1u7gjbx2p2y9hs8ow39x;
       public          postgres    false    3241    218    221            �           2606    123551 #   demande fkb547dmnvxk53xm00vpe24c4mh    FK CONSTRAINT     �   ALTER TABLE ONLY public.demande
    ADD CONSTRAINT fkb547dmnvxk53xm00vpe24c4mh FOREIGN KEY (idutilisateur) REFERENCES public.utilisateur(id);
 M   ALTER TABLE ONLY public.demande DROP CONSTRAINT fkb547dmnvxk53xm00vpe24c4mh;
       public          postgres    false    3243    220    210            �           2606    123576 ,   recu_paiementfon fkcdu9vnhnviyx31sfjv5jhq7vd    FK CONSTRAINT     �   ALTER TABLE ONLY public.recu_paiementfon
    ADD CONSTRAINT fkcdu9vnhnviyx31sfjv5jhq7vd FOREIGN KEY (idfacturefon) REFERENCES public.facturefon(id);
 V   ALTER TABLE ONLY public.recu_paiementfon DROP CONSTRAINT fkcdu9vnhnviyx31sfjv5jhq7vd;
       public          postgres    false    212    217    3229            �           2606    123546 #   demande fkerbvjya8cnyyskk7xpt4oe9t1    FK CONSTRAINT     �   ALTER TABLE ONLY public.demande
    ADD CONSTRAINT fkerbvjya8cnyyskk7xpt4oe9t1 FOREIGN KEY (iddemandeur) REFERENCES public.demandeur(id);
 M   ALTER TABLE ONLY public.demande DROP CONSTRAINT fkerbvjya8cnyyskk7xpt4oe9t1;
       public          postgres    false    210    3227    211            �           2606    123581 +   role_permission fkf8yllw1ecvwqy3ehyxawqa1qp    FK CONSTRAINT     �   ALTER TABLE ONLY public.role_permission
    ADD CONSTRAINT fkf8yllw1ecvwqy3ehyxawqa1qp FOREIGN KEY (permission_id) REFERENCES public.permission(id);
 U   ALTER TABLE ONLY public.role_permission DROP CONSTRAINT fkf8yllw1ecvwqy3ehyxawqa1qp;
       public          postgres    false    3235    219    215            �           2606    163945 &   facturefon fkgtjd66pgpgnk05issyu0pl45s    FK CONSTRAINT     �   ALTER TABLE ONLY public.facturefon
    ADD CONSTRAINT fkgtjd66pgpgnk05issyu0pl45s FOREIGN KEY (idtype_paiement) REFERENCES public.type_paiement(id);
 P   ALTER TABLE ONLY public.facturefon DROP CONSTRAINT fkgtjd66pgpgnk05issyu0pl45s;
       public          postgres    false    3247    224    212            �           2606    155753 &   facturefon fkhc9yeuf3xs4ldtt7lu6xjyf8v    FK CONSTRAINT     �   ALTER TABLE ONLY public.facturefon
    ADD CONSTRAINT fkhc9yeuf3xs4ldtt7lu6xjyf8v FOREIGN KEY (idtva) REFERENCES public.tva(id);
 P   ALTER TABLE ONLY public.facturefon DROP CONSTRAINT fkhc9yeuf3xs4ldtt7lu6xjyf8v;
       public          postgres    false    223    3245    212            �           2606    172137 *   pointconnexion fko97ttmq09mb0fk7co2w7t52yq    FK CONSTRAINT     �   ALTER TABLE ONLY public.pointconnexion
    ADD CONSTRAINT fko97ttmq09mb0fk7co2w7t52yq FOREIGN KEY (idville) REFERENCES public.ville(id);
 T   ALTER TABLE ONLY public.pointconnexion DROP CONSTRAINT fko97ttmq09mb0fk7co2w7t52yq;
       public          postgres    false    225    216    3249            �           2606    123556 &   facturefon fkorujclirhj83m56qllgsn5h0q    FK CONSTRAINT     �   ALTER TABLE ONLY public.facturefon
    ADD CONSTRAINT fkorujclirhj83m56qllgsn5h0q FOREIGN KEY (idlocationfon) REFERENCES public.locationfon(id);
 P   ALTER TABLE ONLY public.facturefon DROP CONSTRAINT fkorujclirhj83m56qllgsn5h0q;
       public          postgres    false    3233    212    214            �           2606    147572 8   liaisonfon_pointconnexionfon fkr2kbs6h320tufvifyuoxu6dpl    FK CONSTRAINT     �   ALTER TABLE ONLY public.liaisonfon_pointconnexionfon
    ADD CONSTRAINT fkr2kbs6h320tufvifyuoxu6dpl FOREIGN KEY (liaisonfon_id) REFERENCES public.liaisonfon(id);
 b   ALTER TABLE ONLY public.liaisonfon_pointconnexionfon DROP CONSTRAINT fkr2kbs6h320tufvifyuoxu6dpl;
       public          postgres    false    213    3231    222            �           2606    123561 &   liaisonfon fkshwl05a5pq8e3git31a5obq7w    FK CONSTRAINT     �   ALTER TABLE ONLY public.liaisonfon
    ADD CONSTRAINT fkshwl05a5pq8e3git31a5obq7w FOREIGN KEY (idlocationfon) REFERENCES public.locationfon(id);
 P   ALTER TABLE ONLY public.liaisonfon DROP CONSTRAINT fkshwl05a5pq8e3git31a5obq7w;
       public          postgres    false    214    3233    213            M   7  x���Kn�0���)�l&�C_��i�i� ](T��:�*K����uh��ڨ� \?(~��g�����B�FK��TW�xIJ/��}Y��~'�I|�~4I|���W����j���k�)�2�����K�(��+�+t� ѹ19�ĺWsx��:�x6��G�<Ny���-�N�M���-��b��F�E#�,]+��� >잺-���]�nZ���O�N���z��p��mҦ;����� ���ksS��;*Nډ�9�?��G97Z���u���˅C�9L��������>7Ds.�h&: �Ta�L�k��C~^ ���B��N�#N�Jr�������i��6�m����G=���`�	�
<��<���k�j���$֝@J�|��V��;L�Ft�`|�R�?���oiצ�b�,ꢬZf�m�h���r����ᅣ �Z�|7�?7����E���ޮc���7�Fk��ջ��Sf��9��hd�Yӡ�[�Ql9�e^��<5���}���G�$���y{	}�^�f��1o��>k
�m�\.� ŉ��      N     x����n�0���S��f�ǾZ��Z.JE�@�I��""e�H�O��J���rK�Μ3F�$H9F���#"��:8�̹�Z�}U��yQ�M����%�Vm�����o�N���c@��K�
�hP�a�C6��'k�!3� Z"^���&�h����K�����ѿ�C^ߏ���&r�J��Ѫ��9J�~�J�{�e���ɍ�M�g*�e��NUJ��ZQ�c0���)jL�'��~��	���7P}�R����"8CQ7�xU�8�嶸G�t_�U��$�`=8�#���5#Sס��M�D�h��RG��-��2�&�l�]�Ⱦ�:�����l���L��gd8�fs�L�Ӕ=��|Qm�b�'��1����.f,w�+��t/��MV�XUT�hc����NY^nn�J\�l���h���r�Z}���D�tq���)�[�5ښ�Ӓ*z�Fh;$��o�>�u���i�H�l��дٓ���e�:����U�gQ�y��C�b4�!�1      O   �   x�u�=N1�k�{�Ϳ��� TT(M�H��p�����,d�8 ���y�=5I�,3��mB
����[`���-���yz}~;$%J��u���l�iݨY}����{����3ل-�C�(����x|9|��Q�#ۯ�Ş����J�5(n��!
��?p>�GFvO�[v�� 굆9���P%i����u5 �貴w��A��x�c�      P   �  x�}��n�0�g�)�&�� �R�Y2fQ��u�@�y�^Ҧ,'b �Χ�{(qp��x�G��2r������fA�͏�0$�V����<�ҵc��!����3-et6�`���O���Cwl���W#i�=�.T��m^t�4^����\�����8�,V��Cy=����8��	������"�P��·�)rJnՅ�PG�uzh�?���R�����ߓ�$#ZT��{̐�<�s7D�%@G���&LD/�%�C�,=�f).,ȴm2�r�'�ܞ3�8�����E`�/��M��:.n����.�b�Xo�p&oS����]b\��n�,,U�]#^�k$�.�����RW]��9e����[ӞYgo��ǯ8�]�PZ�?�cy�5��dw�����      Y   H   x�M���0��)&"^������H�k�|�M(9E���~���^�:[�E�}sw��؆�T��"���      Q   _  x��SKn�0\�Sp"��C��M�n*U��w�9z�އP��,�@f<c[��8��X\Ѕ30��d��&��w|=V6L���������ן�o�W'��%��\�F�G@���I\<�A��bQ�c|�~|~Le��T`�:�#g�z�KT�scvK���{���]F
�⤙���aѬu��ay��	�A����ry�Dh�Z�VZ����LUC3H�Nƨ���%���l���5�]�d�s\�ޢI�v�������%�;"��@[����4;�KF��1XM�D���So�t��Z�{��#e���k�kz2�U�&ݹ��mY3��_���X2IX[�z"צm�3y��0�����      R   S   x�3�4202�54�52S04�25�25�377�)j`��D\�jL���̌q��A�ZbQc�gf��L�x��1To� ��#      S     x�m�KN�0�s�\ �y�5W@�t�@���JXp$���p��4I-�~˟��E�12wĝP�d*&�ҔK�ԡ��F�(���^�c�v��&u���G����Z�F�"�&��Ɩ�!+����~�x|덳Gۆ(�7 �`=���wc?�ɸ%Q�$��L��g���"�$���y��<4���3��V�(䆯�V�㩤^盯���	����gr�е$��á]�Ր*M+������J0�?G�=MeW���mv�4��r��      T      x������ � �      U   L   x�34�4202�54�52S04�25�21�345�)�������D\��(ʌ���L�L��q�;���@���qqq �kf      V       x�34�4�24� �\�� �!����� @�      Z   }   x�m���@��nn��� �p>����X�C�NNH#�	�Σ	��c���)5�yl�r.��&o�n���e�Q�-�[)�;�j��������u~�i�	k��#o���~R���'1�      [      x������ � �      W   �  x���ˎ�@�u�.܂u��bն⨠h�e$�)�Ku��8 #O?�3��`2�Y�gs���� C��D��l"����@�<��R2�.���B�)9q���<L|�9lgT�z����`?���.�7�����4�>n)��)[��ku=�c<�W���FD��%
"�H0Y�A��Y���!�A�l�Ì���.&&6L�TBIk��%
�5�Ҩ����E��*{V�x��퀔�$K�KT�#�(���W]���z/�.B�6��l��-��n�/Q�Dm�$;�tΩM��C�{��a-od�����_��z�B���Q�sg%|=�Cf�y�������
�쟜��6-�.4L��R!�Z�����YPe,S�j99i ��V.&�0臱�{���.1}�iT�C�}I5U<j��U�t: n��      X   "   x��4�44��\� ��!�mb �b���� eH;      \   �   x�u�A
�0E��^�`����ZdDf00҅���1�.��>x/�k�k�pDF=���H~t@�<+������#��L�g�΢Ou�#5�s��VIwd�F��y-^6HZ$�Jn�j�h�*�g!��%߲����@��G��A�=;J�~�$}�q��@)��Q}     