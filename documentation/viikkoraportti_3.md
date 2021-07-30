# Viikkoraportti 3

Tällä viikolla olen muuttanut ohjelman käyttämän pääasiallisen algoritmin A*-algoritmista IDA* -algoritmiin viikkopalautteen mukaisesti. IDA* ei ollut entuudestaan tuttu, joten sen opiskeluun meni aika paljon aikaa. Itse koodin pariin pääsin vasta tässä loppuviikosta.

Päämetodit iterate ja search ovat vielä jonkin verran kesken, enkä vielä ole päättänyt, missä muodossa ohjelman pitäisi palauttaa tieto löydetystä maalista. Tein uuden luokan Path kuvaamaan valmista polkua sekä sen transitioiden määrää, mutta en ole vielä varma tarvitaanko tätä lopullisessa toteutuksessa ollenkaan.

Ideoin ja toteutin internetin lähteiden avustuksella heuristiikka-algoritmin koko pelilaudan Manhattan-etäisyyden laskemaan. Se on nyt testattu ja pitäisi toimia oikein.
Testaus on edistynyt muuten hitaanpuoleisesti, kun main-luokan tärkeimmät metodit ovat vielä pahasti kesken. Edelleen toteuttamatta on myös vielä ratkaisijan se osuus, joka laskee aloitustilasta, että onko se ollenkaan ratkaistavissa.

Seuraavalla viikolla jatkan main-luokan metodien jatkototeutusta sekä testausta siinä samalla. Silloin täytyy myös viimeistään saada Checkstyle ja Codecoverage käyttöön.