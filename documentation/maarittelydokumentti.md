# Määrittelydokumentti

Projektin tarkoituksena on luoda algoritminen ratkaisija 15 -pelille. Pelissä on tasasivuinen pelilauta, yleensä 4 x 4 -kokoinen, jossa kaikki muut paikat ovat täynnä numeroituja tiiliä ja yksi on tyhjä. Tavoitteena pelissä on siirrellä tiiliä yksi kerrallaan vaihtamalla niiden paikkaa tyhjän paikan kanssa, kunnes tiilet on järjestetty nousevaan numerojärjestykseen ja tyhjä tiili sijaitsee alhaalla oikealla.

Ratkaisualgoritmiin käytän polunhakualgoritmia A* Manhattan-etäisyysheuristiikalla, eli pelin tilat mallinnetaan solmuina polulla, ja niiden joukosta etsitään lyhin reitti valitsemalla seuraava lähin solmu aina heuristiikan mukaan. Manhattan-etäisyys tarkoittaa etäisyyttä sivusuunnassa ja pystysuunnassa yhteenlaskettuna. Etäisyys lasketaan jokaiselle tiilelle, ja se tarkoittaa tässä tapauksessa tiilen etäisyyttä oikeasta paikastaan. Nämä etäisyydet lasketaan yhteen ja saadaan tuloksesta tilan yhteisetäisyys nykytilasta, johon lisätään vielä varsinainen etäisyys alkusolmusta. Ohjelma ja sen ratkaisualgoritmi toteutetaan Javalla.

Opiskelen tietojenkäsittelytieteen ohjelmassa ja dokumentaation toteutuskielenä on suomi.

## Tietorakenteet ja algoritmit

A* -haku käyttää prioriteettijonoa valitakseen seuraavan solmun ja joukkoa jo käytyjen solmujen ylläpitämiseen. Solmuille on oma tietorakenteensa, joka sisältää 4x4 -taulukon, joka on nykyinen tila, sekä kyseisen tilan lapsisolmut.

## Syötteet

Syötettä ei ole. Ohjelma generoi satunnaisen aloitustilan ja sitten löytää lyhimmän polun ratkaisuun, mikäli mahdollista. Tulosteena on lopullinen tila sekä montako siirtoa tilan saavuttamiseen kului. Myös jokaisen siirron jälkeen voisi tulostaa pelin senhetkisen tilan.

## Lähteet

[Solving the 15-Puzzle](https://medium.com/@prestonbjensen/solving-the-15-puzzle-e7e60a3d9782)  
[Introduction to Artificial Intelligence 2020](https://materiaalit.github.io/intro-to-ai/part1/)  
[15_puzzle.pdf (ucsd.edu)](https://cseweb.ucsd.edu/~ccalabro/essays/15_puzzle.pdf)
