# Sécurité et aide à décision

## Jeu d'infection - L2 Informatique - Groupe 2A
- VU Nguyen Phuong Vy - 21911658
- BOUCHEFFA Badis - 21914662
- BENELAM Kamel - 21913417
- ADOUMA Hassan Brahim - 21901741

## Organizer les classes
- Interface Player
- Class AI implements Player
- Class Coordinate
- Class Move
- Class State
- Class Main

## `Interface Player`
`Move getBestMove(State)` \
`int getNodeCount()` \
Personnes : BOUCHEFFA, VU, BENELAM, ADOUMA

## `Class AI(int, boolean) implements Player`
`int getNodeCount()` \
`String toString()` \
`float minmax(State, int)` \
`float alphabeta(State, int, float, float)` \
`float getBestMove(State)`\
Personnes : BOUCHEFFA, VU, BENELAM, ADOUMA


## `Classe Coordinate(int, int)`
`int getX()` \
`int getY()` \
`String toString()` \
Personnes : BOUCHEFFA, VU

## `Classe Move(Coordinate, Coordinate, char)`
`Coordinate getStart()` \
`Coordinate getEnd()` \
`char getAction()` \
`String toString()` \
Personnes : BOUCHEFFA, VU, ADOUMA

## `Classe State(Player, Player, int)`
`State copyState()` VU\
`void display()` BOUCHEFFA, VU, ADOUMA\
`void displayScore()` BOUCHEFFA, VU\
`boolean isValidOnBoard(int, int)` VU\
`boolean isDifferentColor(int, int)` VU\
`boolean isValidToPlace(Coordinate)` VU\
`ArrayList<Move> getMove()` BOUCHEFFA, VU, ADOUMA\
`float getScore(Player)` BOUCHEFFA, VU, ADOUMA\
`void changePlayer()` BOUCHEFFA, VU, ADOUMA\
`void infection(Coordinate)` VU\
`void play(Move)` BOUCHEFFA, VU, ADOUMA\
`boolean isFull()` BOUCHEFFA, VU, ADOUMA\
`boolean isOver()` BOUCHEFFA, VU, ADOUMA\
`Player getWinner()` BOUCHEFFA, VU



## `Classe Main(Player, Player, int)`
`main()` \
Personnes : BOUCHEFFA, VU, BENELAM, ADOUMA

README écrit par VU Nguyen Phuong Vy
