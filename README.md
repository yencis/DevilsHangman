# DevilsHangman
Classic hangman with a twist: The program will keep changing the word to dodge your guesses. Code in Java.

The program uses Regex to create filtered word-pools.
1. Everytime you guess a letter, 2 wordpools are formed.
  * Word-pool 1: Words that contain that letter.
  * Word-pool 2: Words that do not contain that letter.
2. The program then checks the size of each word-pool to see if it should accept the letter or not. (Larger word-list is harder)
3. Cycle repeats until only one word is left.

The main difficulty for this project was learning effective usage of Java regular expressions. I do not intend to implement graphics.
