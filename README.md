# caillou-breton

Implementation of the menhir card game 


Pour lancer le jeu sous Windows, lancer le script menhir.sh

Ce que le jeu contient actuellement : 

	- Partie rapide fonctionelle jusqu'à 6 joueurs avec IA et joueur humain
	- Partie avancée fonctionelle jusqu'à 6 joueurs humains, les IA ne peuvent pour l'instant pas jouer d'Allié
	- Le chien de garde reste en jeu tout le long d'une manche et peut-être joué en réponse à un Farfadet en plus d'être joué normalement
	
Ce que le jeu ne contient pas encore :

	- Une implémentation effective des IA pour une partie avancée
	- La possibilité de jouer les Taupes Géantes pendant les tours adverses et ce pour plusieurs raison : 
			*Cette possibilité se traduit bien mieux en programmation evenementielle (Livrable 3)
			*Bien qu'elle soit implémentable par un thread dédié pour le mode console, cela semblait contradictoire par rapport au paradigme de programmation 
					choisit pour le reste du programme
			*L'interêt au niveau du joueur est plus que limité puisque tout se passe sur le même clavier...
	- Une implémentation des exceptions vraiment effective et spécifique au jeu, pour le moment on peut en effet faire des parties de 0 et 1 joueur
			et les parties critiques du programme (distribution des cartes, choix des actions, executions des actions etc...) mériteraient une gestion
			des exceptions
				