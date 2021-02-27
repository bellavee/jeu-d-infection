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
Personnes : BOUCHEFFA, VU, BENELAM, ADOUMA

## `Classe Move(Coordinate, Coordinate, char)`
`Coordinate getStart()` \
`Coordinate getEnd()` \
`char getAction()` \
`String toString()` \
Personnes : BOUCHEFFA, VU, BENELAM, ADOUMA

## `Classe State(Player, Player, int)`
`State copyState()` \
`void display()` \
`void displayScore()` \
`boolean isValidOnBoard(int, int)` \
`boolean isDifferentColor(int, int)` \
`boolean isValidToPlace(Coordinate)` \
`ArrayList<Move> getMove()` \
`float getScore(Player)` \
`void changePlayer()` \
`void infection(Coordinate)` \
`void play(Move)` \
`boolean isFull()` \
`boolean isOver()` \
`Player getWinner()` \
Personnes : BOUCHEFFA, VU, BENELAM, ADOUMA


## `Classe Main(Player, Player, int)`
`main()` \
Personnes : BOUCHEFFA, VU, BENELAM, ADOUMA

README écrit par VU Nguyen Phuong Vy
