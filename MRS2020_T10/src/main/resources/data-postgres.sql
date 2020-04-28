insert into klinicki_centar (naziv) values ('Klinicki centar Srbije');

insert into tip_pregleda (naziv, opis, broj_aktvnih) values ('Opsti', 'opsti pregled', 0);
insert into tip_pregleda (naziv, opis, broj_aktvnih) values ('Ultrazvuk', 'opis pregleda', 0);

insert into lek (naziv,sifra) values ('Bromazepam', 'B1');
insert into lek (naziv, sifra) values ('Hloramfenikol', 'H1');

insert into dijagnoza (naziv,sifra) values ('Opis prve dijagnoze', 'D1');
insert into dijagnoza (naziv, sifra) values ('Opis druge dijagnoze', 'D2');

insert into sala (naziv, broj) values ('Sala A', '1');
insert into sala (naziv, broj) values ('Sala B', '2');

insert into lekar (adresa, drzava, email, grad, ime, kontakt, lozinka, prezime, broj_ocena, klinika_id, prosecna_ocena, rv_kraj, rv_pocetak, tip_pregleda_id) 
values ('Ulica 123', 'Srbija', 'lekar1@gmail.com', 'Novi Sad', 'Nenad', '065154923', 'lekar1', 'Nenadovic', 0, null, 0, '15:00', '07:00', 1);
insert into lekar (adresa, drzava, email, grad, ime, kontakt, lozinka, prezime, broj_ocena, klinika_id, prosecna_ocena, rv_kraj, rv_pocetak, tip_pregleda_id) 
values ('Ulicica 567', 'Srbija', 'lekar2@gmail.com', 'Novi Sad', 'Nikola', '060514848', 'lekar2', 'Nikolic', 0, null, 0, '21:00', '13:00', 2);

--insert into adminkc (ime, prezime, email, lozinka, adresa, grad, drzava, kontakt) values ('Marko', 'Markovic', 'teosnedos@gmail.com', 'adminkc', 'Topolska 18', 'Beograd', 'Srbija', '0652458615');