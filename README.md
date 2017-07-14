# Archiver
## Overview
`Archiver.java` is a program that handles both compression and decompression for the input text to Main.java using the Move-to-Front heuristic.

### Objectives
The objectives of this project are to: 
  1. Implement an algorithm to compress and decompress text files using linked lists with the move-to-front heuristic. 
  2. Collect experimental data to evaluate the performance of the compression algorithm and compare it to well-known file compression utilities. 

### Move-To-Front Heuristic
In normal language usage, some words appear much more often than others. For effective compression, we represent frequent words by small numbers. The move-to-front heuristic causes frequent words to be near the front of the list more often then those that do not. Therefore these words are likely to be represented by small numbers. 

The move-to-front heuristic also exploits a concept called "locality of reference." In a long English text, for example, the set of frequently occurring words shifts as the author progresses though multiple subjects. In a Java program, for comparison, variable names are used frequently in the method in which they are declared, but not at all outside it. Furthermore, uses tend to clump together even within methods. The move-to-front technique adapts well to these changes in relative frequency. Words gradually move further back in the list as they fall into disuse. 


### Input
 To simplify the project in it's current state, input is restricted as follows:
  1. The file may span across multiple lines.
  2. The lines consist of "words" made up of upper- and lower-case letters separated by blanks and special characters such `.,/%&!@#$?-_><`.
  3. Special characters are not to be compressed, they are simply copied from input file to output file.
  4. The file contains no digits, `0123456789`.

### Guidelines
The archive (compressed) file is identical to the text (uncompressed) one,
except as follows:

  1. A zero and a blank `"0 "` are prepended to the first line.
  2. The first occurrence of each word remains as is, but all subsequent occurrences are replaced by positive integers, as specified further on.
  3. After the last line of text, a new line beginning with `"0 "` is added. The rest of this line is a comment containing statistics on the compression (as shown in the example below).

  _Note_: Since the most commonly occurring word will stay near the list top, we ensure short words like "of" and "the" are not replaced by a larger number with more characters than they themselves contain.


## Usage
  1. File Main.java contains the main method and is used to launch the program.
  2. Compile all three java files, `Main.java`, `Archiver.java`, and `LinkedList.java`.
  3. Finally run via `$ java Main < input.txt`, where `input.txt` is the file that you would like valuated.

## Modes of Operation

### Compression Mode
The program keeps track of how many characters it reads and writes (between the two zeros) for the statistics line. 

#### Output
In both modes, the program maintains a linked list of words that have appeared in the input. In compression mode, the program reads the next word from the input file and searches for it in the list.

  1. If not found, the program writes the word to the output file and inserts it at the front of the list.
  2. If found in the _i_-th position in the list, the program writes out _i_, removes the word from the list, then reinserts it at the front (this is the move-to-front feature). 

The program also keeps track of how many characters it reads and writes (between the two zeros) for the statistics line.

### Decompression Mode
  1. As a word is read from input, it is written out again and inserted at the front of the list.
  2. When the number _i_ is read, the word in the _i_-th position is written out.
  3. Next, the word is deleted from the list and reinserted at the front.
  4. Decompression ends when the number `0` is read.

The decompressed version should be identical to the original in every respect. In Unix, the `diff` utility is recommended for checking this.

#### Output
As mentioned above, the program maintains a linked list of words that have appeared in the input in both modes.
  1. A word read from input is written out again and inserted at the front of the list.
  2. When the number _i_ is read, the word in the _i_-th position is written out, then the word is deleted from the list and reinserted at the front.

Decompression ends when the number 0 is read. The decompressed version should be indistinguishable from the original.