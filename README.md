# Game of life

This application is a simple implementation of the game of life in Java.

Pour lire la documentation en français : [Cliquez ici](#jeu-de-la-vie)

## What's the game of life?

The game of life is a cellular automaton created by the British mathematician John Horton Conway in 1970. It is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input. One interacts with the Game of Life by creating an initial configuration and observing how it evolves.
The universe of the Game of Life is a infinite two-dimensional grid of square cells, each of which is in one of two possible states, alive or dead. Every cell interacts with its eight neighbours. At each step in time, all the transitions occur simultaneously depending on certain rules.

## Installation

Java 16 is required to run this program.
Download the .jar file from the [releases tab](https://github.com/IzeLeam/GameOfLife/releases) and run it with the following command:

```bash
java -jar game-of-life.jar
```
 
You also can execute the program by a simple double-click on the .jar file since it's a runnable jar.

## Features

- **Edition mode**: Draw on the grid to while the simulation is either paused or running.
- **Game mode**: Select the rules of the simulation. The default mode is the classic game of life: Conway's rules.

## Usage

The program will open a window with many components. The main component is the grid where the cells are displayed, at the center of the window.
At the top of the window, there are some buttons to control the simulation.
At the left of the window, you will find the edition panel, which allows you to draw on the grid.
At the bottom, there are some additional information about the simulation.

**For a better experience, it is recommended to use a mouse.**

### Controls

- **Left click**: Draw a live cell.
- **Right click**: Draw a dead cell.
- **Mouse wheel**: Zoom in and out.
- **Mouse wheel click**: Move in the grid.

### Simulation controls

- **Play/Pause**: Start or pause the simulation.
- **Next**: Advance one generation.
- **Game mode**: Select the rules of the simulation.
- **Speed**: Change the speed of the simulation by moving the slider.

### Edition controls

- **Edition mode**: Enable or disable the edition mode.
- **Radius**: Change the radius of the drawing tool.
- **Opacity**: Change the opacity of the initial grid. The lower the value, the less live cells will spawn.
- **Reset**: Initialize the grid with the current opacity value.

## More information

The application is developed in Java using the Swing library for the GUI.
The totality of the application and code is in English, including comments and documentation.

# Jeu de la vie

Cette application est une implémentation simple du jeu de la vie en Java.

## Qu'est-ce que le jeu de la vie ?

Le jeu de la vie est un automate cellulaire créé par le mathématicien britannique John Horton Conway en 1970. C'est un jeu à zéro joueur, ce qui signifie que son évolution est déterminée par son état initial, ne nécessitant aucune autre entrée. On interagit avec le jeu de la vie en créant une configuration initiale et en observant comment elle évolue.
L'univers du jeu de la vie est une grille en deux dimensions, infinie de cellules carrées, chacune étant dans l'un des deux états possibles, vivante ou morte. Chaque cellule interagit avec ses huit voisins. À chaque étape dans le temps, toutes les transitions se produisent simultanément en fonction de certaines règles.

## Installation

Java 16 est requis pour exécuter ce programme.
Téléchargez le fichier .jar depuis l'onglet des [releases](https://github.com/IzeLeam/GameOfLife/releases) et exécutez-le avec la commande suivante :

```bash
java -jar game-of-life.jar
```

Vous pouvez également exécuter le programme en double-cliquant sur le fichier .jar puisqu'il est exécutable.

## Fonctionnalités

- **Mode édition** : Dessinez sur la grille pendant que la simulation est en pause ou en cours d'exécution.
- **Mode de jeu** : Sélectionnez les règles de la simulation. Le mode par défaut est le jeu de la vie classique : les règles de Conway.
- **Patterns prédéfinis** : Choisissez parmi une liste de patterns prédéfinis pour les insérer dans la grille.

## Utilisation

Le programme ouvrira une fenêtre avec de plusieurs composants. Le composant principal est la grille où les cellules sont affichées, au centre de la fenêtre.
En haut de la fenêtre, il y a des boutons pour contrôler la simulation.
À gauche de la fenêtre, vous trouverez le panneau d'édition, qui vous permet de dessiner sur la grille.
En bas se trouve les informations supplémentaires sur la simulation.

Pour vous déplacer dans la grille, vous pouvez cliquer sur la molette de la souris et déplacer le curseur. Vous pouvez également vous déplacer avec le click gauche de la souris uniquement si le mode mouvement est activé.
Pour zoomer il suffit d'utiliser la molette de la souris.

En mode édition, vous pouvez dessiner des cellules vivantes ou mortes sur la grille. Vous pouvez également insérer des patterns prédéfinis en cliquant sur la grille.
Sélectionnez un pattern dans la liste déroulante et cliquez sur la grille pour l'insérer.
Si un pattern est sélectionné, vous ne pourrez plus dessiner librement sur la grille. Il faudra d'abord sélectionner le pattern "None" pour revenir en mode dessin.

**Pour une meilleure expérience, il est recommandé d'utiliser une souris.**

### Contrôles

- **Clic gauche** : Dessiner une cellule vivante ou insérer le pattern prédéfini sélectionné.
- **Clic droit** : Dessiner une cellule morte.
- **Molette de la souris** : Zoom avant et arrière.
- **Clic molette de la souris** : Déplacement dans la grille.

### Contrôles de la simulation

- **Play/Pause** : Démarrer ou mettre en pause la simulation.
- **Next** : Avancer d'une génération.
- **Game mode** : Sélectionnez les règles de la simulation.
- **Speed** : Changez la vitesse de la simulation en déplaçant le curseur.

### Contrôles d'édition

- **Edition mode** : Activer ou désactiver le mode édition.
- **Move mode** : Activer ou désactiver le mode déplacement.
- **Radius** : Changer le rayon de l'outil de dessin.
- **Opacity** : Changer l'opacité de la grille initiale. Plus la valeur est basse, moins de cellules vivantes apparaîtront.
- **Reset** : Initialiser la grille avec la valeur d'opacité actuelle.

## Plus d'informations

L'application est développée en Java en utilisant la bibliothèque Swing pour l'interface graphique.
L'entièreté de l'application et du code est en anglais, y compris les commentaires et la documentation.