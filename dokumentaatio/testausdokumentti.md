# Testaus Dokumentti

## Testaus

logiikka osuus työstä on ainoa jossa on testejä. 

Tässäkin testaus on jäänyt vain 50 % sillä kirjoittamani koodi ei ole mitenkään hyvä laatuista, jonka takia testien tekeminen on hyvin vaikeaa.
Hankaluus on siinnä että logiikka koodiin, jossa on suurin osa toiminnallisuudesta, on sekoitettu paljon javafx toimintoja, joita taas on vaikea testata.

Testauksessa on onglema ikkunan sulkemisessa.

## jacoc report

<img src="https://github.com/LKonsta/ot-harjoitustyo/blob/master/dokumentaatio/jacoco report.png" width="900">

## Checkstyle

Checkstyle raportissa on ongelmia vain logiikka osion kanssa sillä sen pituus ylitää 500 rivin ja siellä on muutama liian pitkä metodi. 

Checkstyle raportin errorien määrä ää kuitenkin vain 4, jos ui:ta ei oteta huomioon.

Testauksessa on onglema ikkunan sulkemisessa.
