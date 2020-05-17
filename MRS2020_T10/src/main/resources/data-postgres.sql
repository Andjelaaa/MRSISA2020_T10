insert into klinicki_centar (naziv) values ('Klinicki centar Srbije');
insert into klinika (naziv, adresa, opis, prosecna_ocena, email_klinike, kontakt_klinike, kc_id, broj_ocena) values ('Kardiologija', 'Bulevar Oslobodjenja 12, Novi Sad', 'kardiologija i kardiohirurgija', 7.6, 'kardio@klinika.com', '365/658',1, 5);
insert into klinika (naziv, adresa, opis, prosecna_ocena, email_klinike, kontakt_klinike, kc_id, broj_ocena) values ('Ortopedija', 'Bulevar Oslobodjenja 16, Novi Sad', 'ortopedija i fizikalna medicina', 8.2, 'orto@klinika.com', '365/656',1, 7);

insert into admin_klinike (adresa, drzava, email, grad, ime, kontakt, lozinka, prezime, klinika_id) 
values ('Cara Dusana 123', 'Srbija', 'nedicteodora1@gmail.com', 'Novi Sad', 'Aca', '065154513', '$2a$10$uf1G0CsocgVi9Uc8oYsQsuq9BBHxFZ5Vbirl4o8D87FnYirUnl5C2', 'Peric', 1);

insert into cenovnik (id) values (nextval('Cenovnik_id_SEQ'));

insert into tip_pregleda (naziv, opis, broj_aktvnih) values ('Opsti', 'opsti pregled', 0);
insert into tip_pregleda (naziv, opis, broj_aktvnih) values ('Ultrazvuk', 'opis pregleda', 0);

insert into stavka_cenovnika (cena, cenovnik_id, tip_pregleda_id) values (2000, 1, 1);
update tip_pregleda set stavka_id=1 where id=1;
insert into stavka_cenovnika (cena, cenovnik_id, tip_pregleda_id) values (3000, 1, 2);
update tip_pregleda set stavka_id=2 where id=2;

insert into lek (naziv,sifra) values ('Bromazepam', 'B1');
insert into lek (naziv, sifra) values ('Hloramfenikol', 'H1');
insert into lek (naziv, sifra) values ('Panklav', 'P1');

insert into dijagnoza (naziv,sifra) values ('Opis prve dijagnoze', 'D1');
insert into dijagnoza (naziv, sifra) values ('Opis druge dijagnoze', 'D2');

insert into sala (naziv, broj) values ('Sala A', '1');
insert into sala (naziv, broj) values ('Sala B', '2');

insert into lekar (adresa, drzava, email, grad, ime, kontakt, lozinka, prezime, broj_ocena, klinika_id, prosecna_ocena, rv_kraj, rv_pocetak, tip_pregleda_id) 
values ('Ulica 123', 'Srbija', 'lekar1@gmail.com', 'Novi Sad', 'Nenad', '065154923', '$2a$10$uf1G0CsocgVi9Uc8oYsQsuq9BBHxFZ5Vbirl4o8D87FnYirUnl5C2', 'Nenadovic', 0, 1, 0, '15:00', '07:00', 1);
insert into lekar (adresa, drzava, email, grad, ime, kontakt, lozinka, prezime, broj_ocena, klinika_id, prosecna_ocena, rv_kraj, rv_pocetak, tip_pregleda_id) 
values ('Ulicica 567', 'Srbija', 'lekar2@gmail.com', 'Novi Sad', 'Nikola', '060514848', '$2a$10$uf1G0CsocgVi9Uc8oYsQsuq9BBHxFZ5Vbirl4o8D87FnYirUnl5C2', 'Nikolic', 0, 1, 0, '21:00', '13:00', 2);

insert into zkarton(visina, tezina, datum_rodjenja, pol, dioptrija, krvna_grupa) values (180,80,null, 'm', 2.5,'AB');
insert into zkarton(visina, tezina, datum_rodjenja, pol, dioptrija, krvna_grupa) values (170,70,null, 'z', 0.5,'B');
insert into zkarton(visina, tezina, datum_rodjenja, pol, dioptrija, krvna_grupa) values (190,60,null, 'm', 1.5,'A');

insert into pacijent(ime, prezime, email, lozinka, adresa, grad, drzava, kontakt, lbo, enabled, z_karton_id) values ('Marko', 'Markovic', 'teosnedos@gmail.com', '$2a$10$uf1G0CsocgVi9Uc8oYsQsuq9BBHxFZ5Vbirl4o8D87FnYirUnl5C2', 'Adresa 18', 'Novi Sad', 'Srbija', '065123456', '123lbo22', true, 1);
--insert into adminkc (ime, prezime, email, lozinka, adresa, grad, drzava, kontakt) values ('Marko', 'Markovic', 'trtrt@gmail.com', 'adminkc', 'Topolska 18', 'Beograd', 'Srbija', '0652458615');
insert into pacijent(ime, prezime, email, lozinka, adresa, grad, drzava, kontakt, lbo, enabled, z_karton_id) values ('Ana', 'Markovic', 'anana@gmail.com', '$2a$10$uf1G0CsocgVi9Uc8oYsQsuq9BBHxFZ5Vbirl4o8D87FnYirUnl5C2', 'Adresa 20', 'Novi Sad', 'Srbija', '0651045155', '123123', true, 2);
insert into pacijent(ime, prezime, email, lozinka, adresa, grad, drzava, kontakt, lbo, enabled, z_karton_id) values ('Rajko', 'Jovanovic', 'rajkoj@gmail.com', '$2a$10$uf1G0CsocgVi9Uc8oYsQsuq9BBHxFZ5Vbirl4o8D87FnYirUnl5C2', 'Adresa 52', 'Novi Sad', 'Srbija', '0651256132', '123567', true, 3);


insert into pregled(datum_vreme, popust, status, trajanje, izvestaj_id, lekar_id, pacijent_id, sala_id, tip_pregleda_id, zkarton_id, klinika_id) values
('2020-05-16 15:30:00', 5, 3, 60, null, 1, 1, null, 1, 1, 1);
insert into pregled(datum_vreme, popust, status, trajanje, izvestaj_id, lekar_id, pacijent_id, sala_id, tip_pregleda_id, zkarton_id, klinika_id) values
('2020-05-16 10:00:00', 5, 1, 60, null, 1, 2, 2, 1, 1, 1);
insert into pregled(datum_vreme, popust, status, trajanje, izvestaj_id, lekar_id, pacijent_id, sala_id, tip_pregleda_id, zkarton_id, klinika_id) values
('2020-05-16 15:00:00', 5, 1, 60, null, 1, 1, 2, 1, 1, 1);
--
--insert into lekar_pregled(lekar_id,pregled_id) values(1,1);
--insert into lekar_pregled(lekar_id,pregled_id) values(1,2);
--insert into lekar_pregled(lekar_id,pregled_id) values(1,3);

insert into medicinska_sestra (adresa, drzava, email, grad, ime, kontakt, lozinka, prezime, klinika_id, radvr_kraj, radvr_pocetak) 
values ('Bul. Oslobodjenja 12', 'Srbija', 'meds1@gmail.com', 'Novi Sad', 'Manja', '064154123', '$2a$10$uf1G0CsocgVi9Uc8oYsQsuq9BBHxFZ5Vbirl4o8D87FnYirUnl5C2', 'Nekic', null, '15:00', '07:00');
insert into medicinska_sestra (adresa, drzava, email, grad, ime, kontakt, lozinka, prezime, klinika_id, radvr_kraj, radvr_pocetak) 
values ('Bul. 222', 'Srbija', 'ms2@gmail.com', 'Novi Sad', 'Marija', '060345822', '$2a$10$uf1G0CsocgVi9Uc8oYsQsuq9BBHxFZ5Vbirl4o8D87FnYirUnl5C2', 'Pekic', null, '21:00', '13:00');


insert into autoritet (name) values ('ROLE_PACIJENT');
insert into autoritet (name) values ('ROLE_LEKAR');
insert into autoritet (name) values ('ROLE_ADMIN_KLINIKE');
insert into autoritet (name) values ('ROLE_ADMIN_KLINICKOG_CENTRA');
insert into autoritet (name) values ('ROLE_MED_SESTRA');

insert into pacijent_autoriteti (pacijent_id, autoriteti_id) values (1, 1);
insert into admin_klinike_autoriteti (admin_klinike_id, autoriteti_id) values (1, 3);
insert into lekar_autoriteti (lekar_id, autoriteti_id) values (1, 2);
insert into lekar_autoriteti (lekar_id, autoriteti_id) values (2, 2);
insert into medicinska_sestra_autoriteti (med_sestra_id, autoriteti_id) values (1, 5);
insert into medicinska_sestra_autoriteti (med_sestra_id, autoriteti_id) values (2, 5);


insert into recept (id) values (nextval('Recept_id_SEQ'));
insert into recept (id) values (nextval('Recept_id_SEQ'));
insert into recept (id) values (nextval('Recept_id_SEQ'));

insert into recept_lek (recept_id, lek_id) values(1,1);

insert into recept_lek (recept_id, lek_id) values(1,2);

insert into recept_lek (recept_id, lek_id) values(2,3);
insert into recept_lek (recept_id, lek_id) values(3,2);
--
--insert into odsustvo (kraj, opis, pocetak, status, tip, lekar_id, med_sestra_id, admin_klinike_id)
--values('2020-05-13 12:00:00', 'nema opisa', '2020-05-10 11:00:00', 1, 'Odmor' , null, 1, null);
--insert into odsustvo (kraj, opis, pocetak, status, tip, lekar_id, med_sestra_id, admin_klinike_id)
--values('2020-05-25 12:00:00', 'neki opis tamo', '2020-05-23 11:00:00', 1, 'Odsustvo' , null, 1, null);
--
--insert into odsustvo (kraj, opis, pocetak, status, tip, lekar_id, med_sestra_id, admin_klinike_id)
--values('2020-06-5 12:00:00', 'nema opisa', '2020-05-27 11:00:00', 1, 'Odmor' , null, 1, null);


insert into odsustvo (kraj, opis, pocetak, status, tip, lekar_id, med_sestra_id, admin_klinike_id)
values('2020-05-25 12:00:00', 'neki opis tamo', '2020-05-23 11:00:00', 1, 'Odsustvo' , 1, null, null);

insert into odsustvo (kraj, opis, pocetak, status, tip, lekar_id, med_sestra_id, admin_klinike_id)
values('2020-06-5 12:00:00', 'nema opisa', '2020-05-27 11:00:00', 1, 'Odmor' , 1, null, null);


insert into operacija (datum_vreme,status, trajanje, sala_id, stavka_cenovnika_id, zkarton_id, klinika_id, pacijent_id) 
values('2020-05-5 12:00:00', 1, 30, 1, null, null, null,1 );
insert into operacija (datum_vreme,status, trajanje, sala_id, stavka_cenovnika_id, zkarton_id, klinika_id, pacijent_id) 
values('2020-05-16 13:23:00', 3, 43, 1, null, null, null,1 );
insert into operacija (datum_vreme,status, trajanje, sala_id, stavka_cenovnika_id, zkarton_id, klinika_id, pacijent_id) 
values('2020-05-17 12:00:00', 3, 30, 1, null, null, null,1 );

insert into operacija_lekar(operacija_id, lekar_id) values(1,1);
insert into operacija_lekar(operacija_id, lekar_id) values(2,1);
insert into operacija_lekar(operacija_id, lekar_id) values(3,1);
--insert into pregled(datum_vreme, popust,status, trajanje, izvestaj_id, lekar_id,pacijent_id, sala_id, tip_pregleda_id, zkarton_id, klinika_id) 
--values('2020-05-7 12:00:00', 5, 1, 45, null, 1, 1, 1,  1, null, null);
--
--insert into lekar_pregled(lekar_id,pregled_id) values(1,1);
insert into zahtev_registracije(ime, prezime, email, lozinka, adresa, grad, drzava, kontakt, lbo) values ('Ivan', 'Ivic', 'trajkovicka.9909@gmail.com', 'asdf', 'Adresa 18', 'Novi Sad', 'Srbija', '065123456', '3lbo133131');