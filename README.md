# ascii-game-of-life
John Conway's "Game of Life" implemented with Jcurses

Depends on libncurses5

Build with ./build.sh
Run using java -jar Main.jar SEED REFRESH CHANCE
where SEED is a long used to seed the rng, REFRESH is the amount of milliseconds between updates, and CHANCE is the approximate proportion of the board that will start alive.
