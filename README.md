# PMA
FindACar predstavlja android aplikaciju koja omogućava korisnicima da rezervišu vozilo iz određenog rent-a-car servisa, na određeni vremenski period. “FindACar” pruža mogućnost  rezervacije vozila samo registrovanim korisnicima. Korisnik prilikom pretrage unosi datum preuzimanja i vraćanja vozila kao i lokaciju odnosno grad iz kog želi da rezerviše vozilo. Omogućen mu je brz i jednostavan pregled svih registrovanih rent-a-car servisa za određeni grad kao i detaljan pregled vozila koja su na raspolaganju u datom rent-a-car servisu za uneti termin. Takođe, postoji mogućnost pretrage rent-a-car servisa na osnovu trenutne lokacije korisnika pri čemu je moguće odabrati opseg pretrage u kilometrima. Korisnik ima opciju prikaza rent-a-car servisa koji zadovoljavaju kriterijum pretrage na mapi. Korisnik ima opciju „lajkovanja“ vozila tj. dodavanja vozila u listu omiljenih vozila kao i uklanjanja vozila iz liste omiljenih. Lista omiljenih vozila, zajedno sa recenzijama vozila, se čuva u SQLite bazi podataka pa korisnik može da pristupi omiljenim vozilima i u ofline režimu. Nakon prijave korisnika u aplikaciju započinje proces sinhronizacije podatka (vozila i njihovih recenzija) koji se periodično ponavlja na svaka 4 minuta. Funkcionalnosti podržane aplikacijom: 

1. Registracija – ukoliko korisnik prethodno nije registoravan, nudi mu se opcija registracije unosom neophodnih podataka

2. Prijava na sistem – korisnik unosi email i lozinku kako bi pristupio svom nalogu na aplikaciji

3. Prikaz na mapi – na osnovu unetog grada korisniku se nudi prikaz rent-a-car servisa na mapi

4. Pretraga – na osnovu zadatih parametara vrsi se filtriranje vozila

5. Pretraga servisa u blizini – vrsi se pretraga servisa u odnosu na lokaciju korisnika i zeljenog opsega (npr.  u krugu od 3 km). Ukoliko nije zadat opseg, uzima se podrazumevani.

6. Rezervacija – korisnik unosi datum i vremenski period za koji hoce da rezerviše dato vozilo.

7. Otkazivanje rezervacije – korisnik moze da otkaze rezervaciju vozila u skladu se odredjenim vremenskim rokom

8. Pregled rezervacija – korisnik može da pregleda sve svoje rezervacije, aktivne i rezervacije koje su prošle

9. Ostavljanje recenzije  – korisniku se nudi opcija ocenjivanja i komentarisanja  usluge rent-a-car servisa nakon sto vrati vozilo.

10. Lokalizacija – podržani srpski i engleski jezik

11.	Notifikacije  - korisnik dobija notifikaciju kada se približi vreme rezervisanog termina za preuzimanje vozila, kao i vreme kada treba da vrati vozilo

12. Lista omiljenih vozila – korisnik ima mogucnost dodavanje vozila u listu omiljenih vozila i uklanjanja vozila iz liste. Korisnik moze da vidi recenzije vozila za svako vozilo u listi.

