## Scala Play! simple HangMan Game

[![Build Status](https://travis-ci.org/Raffaello/scala-play-demo.svg?branch=master)](https://travis-ci.org/Raffaello/scala-play-demo)
[![Scala.js](https://www.scala-js.org/assets/badges/scalajs-0.6.17.svg)](https://www.scala-js.org)

- CI on TravisCI
- CD on Heroku

### Hangperson DEMO at:

[Base](https://lit-spire-71369.herokuapp.com/)

[HangPerson Game](https://lit-spire-71369.herokuapp.com/hangperson)

[HangPerson Game SPA](https://lit-spire-71369.herokuapp.com/hangperson/spa)

### HangPerson Game

- store UUID in session/cookie for multiple users
and store the state of the game assoicate to the UUID in the cache
- restore from cache the game state based on UUID retrieve from session

### HangPerson Game SPA

- it download the compiled scalaJS (javascript) built in ReactJS
and the game will be generate in browser (off-line). The Server is no more required at this point.
