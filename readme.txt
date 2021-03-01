

TRAVAUX PRATIQUES I.A EN SECURITE ET AIDE A LA DECISION : JEU D'INFECTION
----------------------------------------------------------------------



A - Numéros étudiants & noms :
----------------------------------------------------------------------

    1 - VU Nguyen Phuong Vy - 21911658
    2 - BOUCHHEFA Badis - 21914662  
    3 - ADOUMA Hassan Brahim - 21901741 
    4 - BENELAM Kamel - 21913417 




B - COMPILATION
----------------------------------------------------------------------

 	>>> La marche à suivre pout compilé : 
1 ---> En ouvrant le CMD, et en rentrant dans le répertoire ........
2 ---> Saisissez la commande : javac -d "build" src/modules/*.java




C - EXECUTION
----------------------------------------------------------------------

	>>> La marche à suivre pour exécuter : 
1 -> Après avoir compilé on tape cette commande :
	---> java -cp "build" modules.Main 
2 -> Puis on saisit ces arguments : 
	- argument 1 : la taille de la grille
	- argument 2 : la profondeur de raisonnement du premier joueur "bleu"
	- argument 3 : la profondeur de raisonnement du deuxième joueur "rouge"
	- argument 4 : le type d'algorithme (1 : MinMax / 2 : AlphaBeta)




D – PRINCIPE :
----------------------------------------------------------------------
Le gagnant c’est le joueur qui a plus de pions que l’autre joueur, si le nombre de pions du premier joueur est égale au nombre de pions du deuxième joueur donc c’est un match nul.  Les deux joueurs ont le droit de cloner et de sauter dans 8 directions différentes, si un des deux joueurs ne peut pas jouer donc il doit passer son tour. Le nouveau pion crée ou bien déplacer il infecte les pions adversaires qui sont à cote de lui et change leur couleur a ca couleur lui-même.  

