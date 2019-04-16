# Ultimate 2D Tetris

Tänne tulee selitys siitä mitä tämä **projekti** pitää *sisällään*.

## Ohjelman toimivuus

Talla hetkellä voi pelin aloittaa jolloin ohjelma luo ikkunan jossa pystyy liikuttamaan yhtä, neljän kuution muodostavaa, palikkaa kerrallaan. Palikka tippuu automaattisesti ja sillä on törmäys tarkistukset seiniin ja muihin palikoihin. Kun palikkaa ei voi enään liikuttaa tallentuu se taulukkoon joka vastaa 10-20 kokoista pelikenttää, pelikentässä on myös värien tunnistus.

palikan liikutus tapahtuu nuoli näppäimillä. palikan pyörittäminen tapahtuu Z ja X nappuloilla.

<code> mvn compile exec:java -Dexec.mainClass=ultimatetetris.Main </code> 

Koodi jolla voi mavenin avulla avata sovelluksen. 

## Dokumentaatio

[Tetris määrittely](https://github.com/LKonsta/ot-harjoitustyo/blob/master/dokumentaatio/maarittelydokumentti.md)

[Työaikakiranpito](https://github.com/LKonsta/ot-harjoitustyo/blob/master/dokumentaatio/TyoTunnit.md)

[Testidokumentaatio](https://github.com/LKonsta/ot-harjoitustyo/blob/master/dokumentaatio/testausdokumentti.md)

[Arkkitehtuuri](https://github.com/LKonsta/ot-harjoitustyo/blob/master/dokumentaatio/Ultimate2DTetris.md)
