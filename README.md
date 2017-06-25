## Scala Play! simple HangMan Game

[![Build Status](https://travis-ci.org/Raffaello/scala-play-demo.svg?branch=master)](https://travis-ci.org/Raffaello/scala-play-demo)
[![Dependencies](https://app.updateimpact.com/badge/876107321439293440/scala-play-demo.svg?config=compile)](https://app.updateimpact.com/latest/876107321439293440/scala-play-demo)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/1d5be75a95544f8e940aa355f2aa5698)](https://www.codacy.com/app/Raffaello/scala-play-demo?utm_source=github.com&utm_medium=referral&utm_content=Raffaello/scala-play-demo&utm_campaign=badger)
[![Scala.js](https://www.scala-js.org/assets/badges/scalajs-0.6.17.svg)](https://www.scala-js.org)
[![GitHub tag](https://img.shields.io/github/release/raffaello/scala-play-demo.svg)](https://github.com/raffaello/scala-play-demo/releases/latest)

- CI on TravisCI
- CD on Heroku

### Hangperson DEMO at:

[Base](https://lit-spire-71369.herokuapp.com/)

[HangPerson Game](https://lit-spire-71369.herokuapp.com/hangperson)

[HangPerson Game SPA](https://lit-spire-71369.herokuapp.com/hangperson/spa)

### HangPerson Game

- store UUID in session/cookie for multiple users
and store the state of the game assoicate to the UUID in the cache
- restore from cache the game state based on UUID retrieve from the session

### HangPerson Game SPA

- it download the compiled scalaJS (javascript) built with ReactJS
and the game will be generate in browser (off-line). The Server is no more required at this point except from the random word.
