# Candy-Crush-Java
CS AB Final Project: Candy Crush implemented in Java

## Overview
This game is a Java take on the popular game Candy Crush. The data is stored in a 2D array, with each fruit being a different number from 1-5. If there is no fruit in a 2D array element, then it is marked with a 0. This game runs until stopped by the user. The front-end is done through the GUI included in Java

## Instructions for use
1. Clone file
2. Replace the access to the fruit images with the correct path
3. Enjoy!

## GUI

### Front-End
1. The front end uses a Grid to store an array of JButtons
2. Each JButton holds a fruit image that randomly changes each time the program resets

## Plan for Methods

### Initalize
1. Create random elements
2. Add them to the array, and make sure it doesnt result in a score
3. Start the game

### Replace
1. Find the column that is being adjusted
2. Iterate through until there are no "0"s left
3. Slide elements down using swap method
4. Refresh each element after swaping

### Check
1. Find element that needs to be checked
2. Start 3 Recursions: Vertical, Horizontal, and Diagonal
3. If a recursion results in a success (at least 3 in a row), remove the row (set it to 0 in the array)
4. Remove the starting element
5. Call the replace method on all the columns that have to be adjusted

