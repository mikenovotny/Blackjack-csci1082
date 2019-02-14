# CSCI-1082 Final Project - BlackJack Game

Our final project will be a java blackjack game.  The game will allow for a variable amount of players and use a multi-deck shoe. 

## Classes:
BlackJack Game
---
Will prompt for number of human players
number of total players
begin new game
---
Multi-Shoe Deck
---
Responsible for shuffling deck
Responsible for tracking what cards have been used
---
Human Players
---
Responsible for prompting player for action (hit, stand, bet, etc)
Responsible for tracking players money
---
Computer Players
---
Responsible for logic of computer player moves (hit, stand, bet, etc)
Responsible for tracking computer players money
---
Dealer
---
Responsible for logic of dealer (must hit on X, asking for insurance against blackjack, etc)
Responsible for playing or taking money from players
---
Rules Logic
---
It might be best to split the logic of what moves are available into a seperate class
For instance, it could accept what type of player it is, and what cards the player has, then give back what options are valid
---

## Authors

* **Mike Novotny**
* **Ryan Westling**
