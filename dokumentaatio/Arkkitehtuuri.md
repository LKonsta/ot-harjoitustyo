# Arkkitehtuuri

## Koodin rakennelma

Koodissa suurin ongelma on siinnä että kyseessä on peli ja se olisi pitänyt suunnitella aluksi paremmin ennen kun lähti rakentamaan sitä.
Projektissa kuitenkin kirjoitin koodia sen mukaan mitä siihen tarvitsin, enkä suunnitellut jakamaan sitä eri paketteihin.

Tämän takia logiikka osuudessa on hyvin paljon tavaraa jossa sekoitettu logikkaa ja palikoiden liikuttelua jotka ovat siis kuvia. 
Nämä olisi pitänyt paremmin jakaa eri osuuksiin.

Tällä hetkellä jos haluaisi jakaa projektin paremmin olisi se helpompi aloittaa uudestaan.
Jos jotain on opittu niin on se että on vielä paljon opittavaa ohjelmien rankenteista, ja niiden suunnittelemisesta.

Tämän hetkinen arkkitehtuuri.

<img src="https://github.com/LKonsta/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.png" width="700">

## Sovellus logiikka

Logiikkaa kutsuessa sovellus luo ensin kentän peliä varten, sitten nakymät joihin lisätään pisteytys ja seuraava palikan näkymä, ja lopuksi luo peli palikan.

Palikkaa luodessa kerrotaan sen muoto. Palikka taas on tieto eri muodoista ja käy tämän listan läpi josta saa tiedon siitä mihin sijainteihin kuutioita lisätään.
Palikka siis luo neljä yksittäistä kuutiota. Toimivuus on tämä koska ohjelmaa luodessa tein ensin mahdollisuuden lisätä yhden kuution kentälle. 

Palikan liikutuksessa sillä taas on tieto kentästä ja törmäys testeissä käydään kaikki yksittäiset palikan kuutiot läpi kun katsotaan törmääkö palikka.
Kentästä katsotaan onko mahdollisessa liikutuskohdassa 0 vai jokin muu.

kentässä voi siis olla 0, 1 tai 2. 0 jos siinnä ei ole mitään. 1 jos uusi palikka on tippunut siihen. 
2 kun olemme käyneet updateKentta() komennon jolla katsomme kentän läpi koordinaatiston, ja jos lydämme 1 niin lisäämme näytölle kuvan palikasta ja vaihdamme kenttä koordinaatistoon 2.

Uudet palikat ja muisti ovat jonoja. Uusi palikka jonossa on aina 3 palikkaa jotka eivät voi olla samoja, joka hieman parantaa palikoiden variaatiota. 
Muisti toimii siis tavallaan kuin uusi palikka jonona mutta siellä on tiedossa jokin tietty palikka joka sinne on laitettu. 

Kun peli luo uuden palikan ja jos löytää kohdasta johon se yrittää luoda sitä jo koordinaatiston arvon 2, loppuu peli.

<img src="https://github.com/LKonsta/ot-harjoitustyo/blob/master/dokumentaatio/logiikka.png" width="700">  

Sekvenssi kaavio saattaisi olla hieman hankala piirtää ohjelman monimutkaisuuden takia ja hieman turhaa olisi tehdä tätä esim yhden napin painamisen tapahtumaan. 

## Käyttöliittymä

Ohjelman avatessa ovat ohjelmassa nappulat pelin aloittamiseen ja highscore listaan.

## Tietokanta kaavio

Pelissä on siis vain yksi tietokanta joka pitää tiedossa pisteitä ja nimistä.

<img src="https://github.com/LKonsta/ot-harjoitustyo/blob/master/dokumentaatio/tietokanta.png" width="300">
