# Määrittelydokumentti

Projektin tarkoituksena on luoda algoritminen ratkaisija 15 -pelille. Pelissä on tasasivuinen pelilauta, yleensä 4 x 4 -kokoinen, jossa kaikki muut paikat ovat täynnä numeroituja tiiliä ja yksi on tyhjä. Tavoitteena pelissä on siirrellä tiiliä yksi kerrallaan vaihtamalla niiden paikkaa tyhjän paikan kanssa, kunnes tiilet on järjestetty nousevaan numerojärjestykseen ja tyhjä tiili sijaitsee alhaalla oikealla.

Ratkaisualgoritmiin käytän polunhakualgoritmia IDA* Manhattan-etäisyysheuristiikalla, eli pelin tilat mallinnetaan solmuina polulla, ja niiden joukosta etsitään lyhin reitti. IDA* lainaa heuristiikan idean A*-algoritmilta, ja käyttää mekanisminaan syvyyshakua. Heuristiikkaa käytetään laskemaan kulloinkin tutkittavalle polun haaralle maksimihinta. Jos maksimihinta ylittyy, kyseistä haaraa ei enää tutkita ja palataan "ylös". Manhattan-etäisyys tarkoittaa etäisyyttä sivusuunnassa ja pystysuunnassa yhteenlaskettuna. Etäisyys lasketaan jokaiselle tiilelle, ja se tarkoittaa tässä tapauksessa tiilen etäisyyttä oikeasta paikastaan. Nämä etäisyydet lasketaan yhteen ja saadaan tuloksesta tilan yhteisetäisyys nykytilasta, johon lisätään vielä varsinainen etäisyys alkusolmusta. Ohjelma ja sen ratkaisualgoritmi toteutetaan Javalla. 

Opiskelen tietojenkäsittelytieteen ohjelmassa ja dokumentaation toteutuskielenä on suomi.

## Tietorakenteet ja algoritmit

IDA* -algoritmi käyttää prioriteettijonoa valitakseen seuraavan solmun ja joukkoa jo käytyjen solmujen ylläpitämiseen. Solmuille on oma tietorakenteensa, joka sisältää 4x4 -taulukon, joka on nykyinen tila, sekä kyseisen tilan lapsisolmut.

## Syötteet

Syötettä ei ole. Ohjelma generoi satunnaisen aloitustilan ja sitten löytää lyhimmän polun ratkaisuun, mikäli mahdollista. Tulosteena on lopullinen tila sekä montako siirtoa tilan saavuttamiseen kului. Myös jokaisen siirron jälkeen voisi tulostaa pelin senhetkisen tilan.

## Lähteet

[Optimal 8/15-Puzzle Solver](http://www.brian-borowski.com/software/puzzle/)
[Introduction to Artificial Intelligence 2020](https://materiaalit.github.io/intro-to-ai/part1/)  
[1524 (github.com)](https://github.com/quantumelixir/1524)
