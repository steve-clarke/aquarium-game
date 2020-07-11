# Aquarium

## About
Aquarium is a modern puzzle game. 
The player is required to determine the water levels of a set of aquariums to
collectively satisfy a number of constraints.

A typical empty puzzle and solution is shown below.

![example](https://raw.githubusercontent.com/steve-clarke/aquarium-game/master/src/main/resources/solutions/Example.png)

The game takes place on a square grid of spaces.
Each aquarium is marked by thick red lines. The above puzzle has six aquariums.

Aquariums can be filled with air (red dots) or water (blue squares).
Gravity acts down the screen. The player must not add water above air in any given aquarium.

The player must add water to each aquarium such that the number of water cells adds up to the column and row totals shown beside the puzzle board.


## Puzzles
Puzzles are read by the program in the form of text files. Anyone can create a puzzle to share with their friends.

Text files have the following format (this one corresponds to the example above):

```bash
2 3 4 5 2 1
2 4 1 3 2 5

1 1 2 2 2 2
1 1 1 2 6 6
4 5 3 2 6 6
4 5 3 3 3 6
4 4 3 3 6 6
4 3 3 6 6 6
```

When writing a puzzle, please note:
 - The first two lines are the column and row totals, respectively;
 - The numbers on the lines following the space represent an aquarium;
 - The last aquarium MUST have a space allocated at the bottom-right cell;
 - The aquariums and totals MUST be separated by a space.


## Authors & Credits
This project was written by Steve Clarke, with the exceptions of the following:

 - The SimpleCanvas class, written by the BlueJ team Michael Kölling and David J. Barnes,
with modifications by Gordon Royle and Lyndon While
 - The FileIO Class, written by Lyndon While

This project was originally written in Kölling & Rosenberg's BlueJ IDE, and was later migrated to Maven with IntelliJ.

The original Aquarium puzzle game was created by [Puzzle-Team-Club](https://twitter.com/PuzzleTeamClub) and can be found online [here](https://www.puzzle-aquarium.com/).

## Contact
To collaborate on a project, or to learn more, message [Steve Clarke](https://github.com/steve-clarke) on Github.
