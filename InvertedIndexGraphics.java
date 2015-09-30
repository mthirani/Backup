import java.awt.*;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import javax.swing.JScrollBar;

/*
 * This Class creates a 2D plot for Frequency/ Word for the Driver Class.
 *
 * Made by Mayank Thirani. You can edit, copy and do all kind of stuff 
 * except for nefarious ones. Any harm or injury as well as danger caused by the use
 * of this program is not my responsibility. ;)
 * The above link is subject to change, occasionally. So, you may ask me directly for a
 * fresh link to my github via mayank13081988@gmail.com.
 * Github: https://github.com/mthirani. Email: mayank13081988@gmail.com
 * Go enjoy!
 */
 
class InvertedIndexGraphics
{
	static Driver driverObj1;
	static String []arrayWords;
	static int []frequencyWords;
	static int xPos;
	static int yPos;
	static int width;
	static int height;
	static class CustomPaintComponent extends JComponent
	{
		public void paint(Graphics g) 
		{
			int max=0;
			yPos=650;
			width=20;
			int red[]=new int[arrayWords.length];
			int blue[]=new int[arrayWords.length];
			int green[]=new int[arrayWords.length];
			
			/***** Get the maximum word count ****/
			for(int i=0; i<arrayWords.length; i++)
			{
				if(frequencyWords[i]>max)
				{
					max=frequencyWords[i];
				}
			}
			/***** Draw Vertical Bars ****/
			for(int i=0; i<arrayWords.length; i++)
			{
				xPos=75+(i*30);
				height=frequencyWords[i]*5;
				red[i]=(int)(Math.random()*255+1);
				blue[i]=(int)(Math.random()*255+1);
				green[i]=(int)(Math.random()*255+1);
				g.setColor(new Color(red[i], blue[i], green[i]));
				g.fillRect(xPos, yPos, width, -height);
				g.setColor(new Color(0, 0, 0));
				Graphics2D g2d=(Graphics2D) g;
				g2d.setStroke(new BasicStroke(5));
				g2d.drawLine(72, yPos-(max*5+20), 72, yPos);
				g2d.drawLine(72, yPos, 1260, yPos);
			}
			for(int i=40; i<=(yPos-(max*3)); i=i+40)
			{
				int j=yPos-i;
				Graphics2D g2d=(Graphics2D)g.create();
				Stroke dashed=new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{3}, 0);
				g2d.setStroke(dashed);
				g2d.drawString(String.valueOf(i/5), 45, j);
				g2d.drawLine(72, j, 1260, j);
				g2d.dispose();
			}
			/***** Create Axis Labels ****/
			g.setFont(new Font("Arial", Font.BOLD, 16));
			g.drawString("Words ---->", 1260/2, yPos+20);
			Graphics2D g2d=(Graphics2D)g;
			AffineTransform defaultAt=g2d.getTransform();
			AffineTransform at=new AffineTransform();
			at.rotate(-Math.PI/2);
			g2d.setTransform(at);
			g2d.drawString("Frequency Count ---->", -(yPos-max-20), 30);
		}
	}	
	public static void main(String[] args) throws IOException
	{
		driverObj1=Driver.display();
		int size=Driver.alInvertedIndex.size();
		System.out.println(size);
		arrayWords=new String[size];
		frequencyWords=new int[size];
		int count=0;
		JFrame window=new JFrame();
		
		/***** Counting the Frequency Of Each Words ****/
		for(InvertedIndex invIndex: Driver.alInvertedIndex)
		{
			arrayWords[count]=invIndex.word;
			String wordLocs=invIndex.fileLocations.toString();
			String []words=wordLocs.split("[\n]");
			int total=0;
			for(String word:words)
			{
				String []freqwords=word.split(",");
				for(String takeCount:freqwords)
				{
					if(takeCount.indexOf(".txt")<0)
					{
						total=total+1;
					}
				}
			}
			frequencyWords[count]=total;
			count=count+1;
		}
		
		class MyAdjustmentListener implements AdjustmentListener 
		{
            public void adjustmentValueChanged(AdjustmentEvent e) 
			{
                window.repaint();
            }
        }
		window.setBounds(0, 0, 1260, 690);
		window.getContentPane().add(new CustomPaintComponent());
		JScrollBar hbar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 1260);
		hbar.setPreferredSize(new Dimension(0, 20));
		hbar.addAdjustmentListener(new MyAdjustmentListener( ));
		window.add(hbar, BorderLayout.SOUTH);
		window.setVisible(true);
		window.setTitle("Frequency Count in Each Word");
	}
}