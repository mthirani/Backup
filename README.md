# InvertedIndex

This project is implemented for inverted index which will read all the .txt files from the directory and list down all the words alphabetically providing the location and appearance of each word in each of the file.
Input parameters are passed in JSON file which contain the the delimeter to extract the words from each file, path for the input directory to read all the files and output file name to store the resuts.

Input
----------------------------------------------------------------------------------------
1. Make Sure to change the path of JSON file in FileInput.java
2. Make Sure to change the desired parameters in JSON file for reading the input files

To Run
----------------------------------------------------------------------------------------
1. javac InvertedIndexGraphics.java
2. java InvertedIndexGraphics

Sample Output
----------------------------------------------------------------------------------------
a
"D:/Java-program/UtilClasses/MappingDictionary.txt",1
"D:/Java-program/UtilClasses/Reading1.txt",3,9
"D:/Java-program/UtilClasses/Reading3.txt",12

and
"D:/Java-program/UtilClasses/MappingDictionary.txt",20
"D:/Java-program/UtilClasses/Reading3.txt",2

are
"D:/Java-program/UtilClasses/MappingDictionary.txt",33
"D:/Java-program/UtilClasses/Reading3.txt",4
...
..
.

Next Version will be having the following changes:
1. Config JSON will not have to changed in the FileInput.java file; Else it will be passed as an argument to the function.
2. Data Visualization will be improved further in terms of displaying data; Labels will be included to show the statistics.
3. JSON Parser will be used for parsing the JSON config file instead of splitting strings in each line.
4. Efficiency will be improved further by using TreeSet/ TeeeMap instead of ArrayList.

NOTE: Input Directory contains the sample JSON file and Text files and Output Directory conatins the results