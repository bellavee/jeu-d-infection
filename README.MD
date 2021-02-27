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
VU

## `Class AI(int, boolean) implements Player`
`int getNodeCount()` \
`String toString()` \
`float minmax(State, int)` \
`float alphabeta(State, int, float, float)` \
`float getBestMove(State)`\
VU, BOUCHEFFA, BENELAM, ADOUMA


## `Classe Coordinate(int, int)`
`int getX()` \
`int getY()` \
`String toString()` \
VU, BOUCHEFFA

## `Classe Move(Coordinate, Coordinate, char)`
`Coordinate getStart()` \
`Coordinate getEnd()` \
`char getAction()` \
`String toString()` \
VU, BOUCHEFFA, BOUCHEFFA, ADOUMA

## `Classe State(Player, Player, int)`
`State copyState()` VU\
`void display()` VU, BOUCHEFFA, ADOUMA\
`void displayScore()` VU, BOUCHEFFA\
`boolean isValidOnBoard(int, int)` VU, BOUCHEFFA, ADOUMA\
`boolean isDifferentColor(int, int)` VU, BOUCHEFFA, ADOUMA\
`boolean isValidToPlace(Coordinate)` VU, BOUCHEFFA, ADOUMA\
`ArrayList<Move> getMove()` VU, BOUCHEFFA, ADOUMA\
`float getScore(Player)` VU, BOUCHEFFA, ADOUMA\
`void changePlayer()` VU, BOUCHEFFA, ADOUMA\
`void infection(Coordinate)` VU\
`void play(Move)` VU, BOUCHEFFA, ADOUMA\
`boolean isFull()` VU, BOUCHEFFA, ADOUMA\
`boolean isOver()` VU, BOUCHEFFA, ADOUMA\
`Player getWinner()` VU, BOUCHEFFA



## `Classe Main()`
`main()` \
VU, BOUCHEFFA

README écrit par VU Nguyen Phuong Vy
