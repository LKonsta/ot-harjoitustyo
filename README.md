# Ultimate 2D Tetris

## Ohjelman toimivuus

Talla hetkellä voi pelin aloittaa jolloin ohjelma luo ikkunan jossa pystyy liikuttamaan yhtä, neljän kuution muodostavaa, palikkaa kerrallaan. Palikka tippuu automaattisesti ja sillä on törmäys tarkistukset seiniin ja muihin palikoihin. Kun palikkaa ei voi enään liikuttaa tallentuu se taulukkoon joka vastaa 10-20 kokoista pelikenttää, pelikentässä on myös värien tunnistus.

<code> mvn package </code> jar paketin luomis koodi.

<code> mvn compile exec:java -Dexec.mainClass=ultimatetetris.ui.Ui </code> Koodi jolla voi mavenin avulla avata sovelluksen. 

<code> mvn test </code> Testien suoritus vaikkakin tällä hetkellä pitää manuaalisesti sulkea ohjelma.

<code> mvn test jacoco:report </code> jacoco raportin luominen vaikkakin pitää tällähetkellä sulkea ohjelma manuaalisesti.

<code> mvn jxr:jxr checkstyle:checkstyle </code> checkstylen tarkistus koodi.

[Ultimate2DTetris 1.2](https://github.com/LKonsta/ot-harjoitustyo/releases) Final Release.

<code> java -jar Ultimate_2D_Tetris.jar </code> koodi jolla käynnistetään Release 1.2 versio Ultimate 2D Tetriksestä.

## Käyttö ohjeet.

ASD - liikkuminen

Z - pyöritä palikkaa vasemmalle

X - pyöritä palikkaa oikealle

C - laita palikka muistiin

P - peli paussille

SPACE - tiputa palikka suoraan alas


## Dokumentaatio

[Tetris määrittely](https://github.com/LKonsta/ot-harjoitustyo/blob/master/dokumentaatio/maarittelydokumentti.md)

[Työaikakiranpito](https://github.com/LKonsta/ot-harjoitustyo/blob/master/dokumentaatio/TyoTunnit.md)

[Testidokumentaatio](https://github.com/LKonsta/ot-harjoitustyo/blob/master/dokumentaatio/testausdokumentti.md)

[Arkkitehtuuri](https://github.com/LKonsta/ot-harjoitustyo/blob/master/dokumentaatio/Arkkitehtuuri.md)

[Käyttö ohjeet](https://github.com/LKonsta/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohjeet.md)
