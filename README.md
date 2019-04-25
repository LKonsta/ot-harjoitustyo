# Ultimate 2D Tetris

Tänne tulee selitys siitä mitä tämä **projekti** pitää *sisällään*.

## Ohjelman toimivuus

Talla hetkellä voi pelin aloittaa jolloin ohjelma luo ikkunan jossa pystyy liikuttamaan yhtä, neljän kuution muodostavaa, palikkaa kerrallaan. Palikka tippuu automaattisesti ja sillä on törmäys tarkistukset seiniin ja muihin palikoihin. Kun palikkaa ei voi enään liikuttaa tallentuu se taulukkoon joka vastaa 10-20 kokoista pelikenttää, pelikentässä on myös värien tunnistus.

<code> mvn package </code> jar paketin luomis koodi.

<code> mvn compile exec:java -Dexec.mainClass=ultimatetetris.Peli </code> Koodi jolla voi mavenin avulla avata sovelluksen. 

<code> mvn test </code> Testien suoritus vaikkakin tällä hetkellä pitää manuaalisesti sulkea ohjelma.

<code> mvn test jacoco:report </code> jacoco raportin luominen vaikkakin pitää tällähetkellä sulkea ohjelma manuaalisesti.

<code> mvn jxr:jxr checkstyle:checkstyle </code> checkstylen tarkistus koodi.

[Ultimate2DTetris 1.0](https://github.com/LKonsta/ot-harjoitustyo/releases)

## Dokumentaatio

[Tetris määrittely](https://github.com/LKonsta/ot-harjoitustyo/blob/master/dokumentaatio/maarittelydokumentti.md)

[Työaikakiranpito](https://github.com/LKonsta/ot-harjoitustyo/blob/master/dokumentaatio/TyoTunnit.md)

[Testidokumentaatio](https://github.com/LKonsta/ot-harjoitustyo/blob/master/dokumentaatio/testausdokumentti.md)

[Arkkitehtuuri](https://github.com/LKonsta/ot-harjoitustyo/blob/master/dokumentaatio/Arkkitehtuuri.md)

[Käyttö ohjeet](https://github.com/LKonsta/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohjeet.md)
