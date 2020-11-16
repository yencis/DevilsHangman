# DevilsHangman
Classic hangman with a twist: The program will keep changing the word to dodge your guesses. Code in Java.

The program uses Regex to create filtered word-pools.
1. Everytime you guess a letter, 2 wordpools are formed.
  * Word-pool 1: Words that contain that letter.
  * Word-pool 2: Words that do not contain that letter.
2. The program then checks the size of each word-pool to see if it should accept the letter or not. (Larger word-list is harder)
3. Cycle repeats until only one word is left.

The main difficulty for this project was learning effective usage of Java regular expressions. 

**Concerning graphics:**
 * Due to the simplicity of this program, we were able to use the code and create a fully functioning Android app with the help of https://github.com/mysticworks. (Not on store)
 * This Android app has all the functionalities of the original program with the addition of a dictionary definition of the final word. (This was added because we had no idea what the words meant)
 * The dictionary implementation uses the Online Dictionary API and XML file reading. Code here: https://github.com/yencis/XMLReader/blob/master/README.md
