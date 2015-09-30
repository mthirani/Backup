import java.io.*;

/*
 * This class stores the word and it's locations in each file.
 *
 * Made by Mayank Thirani. You can edit, copy and do all kind of stuff 
 * except for nefarious ones. Any harm or injury as well as danger caused by the use
 * of this program is not my responsibility. ;)
 * The above link is subject to change, occasionally. So, you may ask me directly for a
 * fresh link to my github via mayank13081988@gmail.com.
 * Github: https://github.com/mthirani. Email: mayank13081988@gmail.com
 * Go enjoy!
 *
 */
class InvertedIndex implements Comparable<InvertedIndex>,Serializable
{
	String word;
	StringBuffer fileLocations;
	InvertedIndex(String s1, StringBuffer sbuf)
	{
		word=s1;
		fileLocations=new StringBuffer(sbuf);
	}
	public int compareTo(InvertedIndex d)
	{
		return word.compareToIgnoreCase(d.word);
	}
	public String toString()
	{
		return word+fileLocations.toString();
	}
}