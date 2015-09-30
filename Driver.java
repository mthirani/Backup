import java.util.*;
import java.io.*;

/*
 * This class encapsulates the objects of InvertedIndex Class and executes the whole thing to calculate the Frequency count
 * of each words appearing in all files of directory as mentioned in JSON. Also sort them alphabetically.
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
class Driver
{
	ArrayList<String> al;		//Every words of the File
	ArrayList<String> findIndex;	//Unique Word in the File
	static ArrayList<InvertedIndex> alInvertedIndex;		//Storing Each Word with It's Location sorted by name
	static fileInput fInput;
	static String []listFiles;	//Contaning the list of all the file names
	static ArrayList<TopWords> topWords;
	
	Driver()
	{
		findIndex=new ArrayList<String>();
		al=new ArrayList<String>();
		topWords=new ArrayList<TopWords>();
	}
	public static Driver display() throws IOException
	{
		fInput=new fileInput();
		alInvertedIndex=new ArrayList<InvertedIndex>();
		fInput.getInputs();
		fInput.displayInputs();
		File fObj=new File(fInput.path);
		FilenameFilter fObjFilter=new Filter();
		listFiles=fObj.list(fObjFilter);
		Driver driverObj1=new Driver();
		for(int indexFile=0;indexFile<listFiles.length;indexFile++)
		{
			String fileName=fInput.path+listFiles[indexFile];
			driverObj1.readEachFile(fileName);
			driverObj1.uniqueWord();
			driverObj1.find(indexFile);
			driverObj1.clearAll();
		}
		driverObj1.dsiplayInvertedIndex(fInput.outputFileName);
		
		return driverObj1;
	}
	public void readEachFile(String str) throws IOException		/* Reading the Tokens of each file */
	{
		String st;
		FileInputStream fIn=new FileInputStream(str);
		Scanner s=new Scanner(fIn);
		if(fInput.delimiter.equals("false"))
			s.useDelimiter("[^A-Za-z]");
		else
			s.useDelimiter("[0-9]");
		while(s.hasNext())
		{
			st=s.next();
			if(!st.equals(""))
				al.add(st);;
		}
		fIn.close();
	}
	public void clearAll()				/* Clearing the Lists */
	{
		al.clear();
		findIndex.clear();
	}
	public void uniqueWord()			/* Finding the Unique Word Of Each File */
	{
		for(String str1:al)
		{
			if(findIndex.indexOf(str1.toLowerCase())==-1)
			{
				findIndex.add(str1.toLowerCase());
			}
		}
	}
	public void find(int i) throws IOException		/* Finding the Index value of each unique record in Each File */
	{
		int index;
		boolean marker,findValue;
		StringBuffer sbuf=new StringBuffer();
		for(String a:findIndex)
		{
			findValue=getValuesInvertedIndex(a);
			if(findValue==false)
			{
				Driver driverObj2=new Driver();
				for(int j=i;j<listFiles.length;j++)
				{
					String fileName=fInput.path+listFiles[j];
					driverObj2.readEachFile(fileName);
					index=1;
					marker=false;
					for(String b:driverObj2.al)
					{
						if(a.equalsIgnoreCase(b)&&marker==true)
							sbuf.append(",").append(index);
						if(a.equalsIgnoreCase(b)&&marker==false)
						{
							sbuf.append("\n").append("\"").append(fileName).append("\"").append(",").append(index);
							marker=true;
						}
						index=index+1;
					}
					driverObj2.clearAll();
				}
				driverObj2=null;
				alInvertedIndex.add(new InvertedIndex(a,sbuf));
				sbuf.delete(0,sbuf.length());
			}
		}
	}
	public void dsiplayInvertedIndex(String outputFile) throws IOException		/* Writing Of InvertedIndex Class in a new File*/ 
	{
		File fOutcheck=new File(outputFile);
		if(fOutcheck.exists())
		{
			System.out.println("File Already Exists and will be deleted");
			boolean a=fOutcheck.delete();
		}
		Collections.sort(alInvertedIndex);
		FileWriter fOut=new FileWriter(outputFile);
		BufferedWriter bufOut=new BufferedWriter(fOut);
		for(InvertedIndex d:alInvertedIndex)
		{
			bufOut.write(d.word);
			String wordLocs=d.fileLocations.toString();
			String []words=wordLocs.split("[\n]");
			int total=0;
			for(String word:words)
			{
				bufOut.write(word);
				bufOut.newLine();
			}
			bufOut.newLine();
		}
		bufOut.close();
		fOut.close();
		
		System.out.println("File Successfully written");
	}
	public boolean getValuesInvertedIndex(String st)		/* Checking the Unique Word in InvertedIndex */
	{
		for(InvertedIndex d:alInvertedIndex)
		{
			if(st.equalsIgnoreCase(d.word))
				return true;
		}
		return false;
	}
}