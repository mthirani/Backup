import java.util.*;
import java.io.*;

/*
 * This class is used for storing the input parameters.
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
class FileInput
{
	String path;
	String delimiter;
	String outputFileName;
	
	public void getInputs() throws FileNotFoundException
	{
		int counter=0;
		FileInputStream fInput=new FileInputStream("config.json");
		Scanner sInput=new Scanner(fInput);
		sInput.useDelimiter("\"");
		while(sInput.hasNext())
		{
			if(sInput.next().indexOf(":")!=-1)
			{
				counter++;
				if(sInput.hasNext()&&counter==1)
					path=sInput.next();
				if(sInput.hasNext()&&counter==2)
					outputFileName=sInput.next();
				if(sInput.hasNext()&&counter==3)
					delimiter=sInput.next();
			}
		}
	}
	public void displayInputs()
	{
		System.out.println("Path to Navigate: "+path);
		System.out.println("outPutFileName: "+outputFileName);
		System.out.println("digitDelimiter: "+delimiter);
	}
}
class Filter implements FilenameFilter			/* This class is an implementation of FilenameFilter interface */
{
	public boolean accept(File dir, String name)
	{
		return name.endsWith(".txt");
	}
}