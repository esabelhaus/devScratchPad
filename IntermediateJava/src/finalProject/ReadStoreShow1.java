package finalProject;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

@SuppressWarnings("serial")
public class ReadStoreShow1 extends JFrame {
	private static String[] cols = null;
	private static String currentMin;
	
	public static int sizeCheck(int i) throws Exception{
		if(i < 10) {
			throw new Exception("Exception: Too Few Colors Listed, Please list 10 Colors");
		} else if (i > 20) {
			throw new Exception("Exception: Too Many Colors Listed, Please List Fewer than 20 Colors");
		}
		return i;
	}

	protected static JFrame frame;
	private String color;
	
	public static ArrayList<String> colorsHex = new ArrayList<String>();
	public static ArrayList<String> guiColors = new ArrayList<String>();
	
	public static JRadioButton button = new JRadioButton();
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws FileNotFoundException{
		// TODO Auto-generated method stub
		Scanner inFile = new Scanner(new File("C:/Users/Eric/workspace/IntermediateJava/bin/finalProject/ColorHexValues.txt"));
		ArrayList<String> colors = new ArrayList<String>(); {
			/**
			 * File only as many lines as are available into the list
			 */
			while (inFile.hasNextLine()){
				String currentLine = inFile.nextLine();
			    colors.add(currentLine);
			}
			
		}	
		inFile.close();
		
		
			
		int size = 0;
		try {
			size = sizeCheck(colors.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i = 0; i < size; i++){
			/**
			 * using the array list tools to sort the hex value into their own array list
			 */
			cols = colors.get(i).split(" ");
			colorsHex.add(cols[1]);
			
		}
		
		System.out.println(colorsHex);
		System.out.println(colors);
		
		for(int x = 0; x < colors.size(); x++) {
			for(int y = x + 1; y < colors.size(); y++) {
				int result = colorsHex.get(y).compareTo(colorsHex.get(x));
				
				if(result > 0) {
					currentMin = colors.get(y);
					colors.remove(y);
					colors.add(currentMin);
				}
			}
			guiColors.add(colors.get(x));
		}
		
		Iterator<String> paintBrush = colors.iterator();		
		while(paintBrush.hasNext()) {
			String out = paintBrush.next();
			
			System.out.println(out);
		}
		frame = new ReadStoreShow1();
		frame.pack();
		frame.setTitle("Color Selections");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setSize(800,400);
	}
	public ReadStoreShow1() {
		
		
		//Dynamically generate radio button panel
		JPanel rButtons = new JPanel();
		
		rButtons.setLayout(new GridLayout(guiColors.size(), 1));
		ButtonGroup group = new ButtonGroup();
		
		for(int i = 0; i < guiColors.size(); i++){
			cols = guiColors.get(i).split(" ");
			final JRadioButton button = new JRadioButton(i + " " + cols[0]);
			rButtons.add(button);
			group.add(button);
			button.addActionListener(new ActionListener() {
				

				@Override
				public void actionPerformed(ActionEvent e) {
					cols = button.getText().split(" ");
					int index = Integer.parseInt(cols[0]);
					setColor(index);
					Color colorShade = Color.decode(color);
					frame.getContentPane().setBackground(colorShade);
				}
			});
		}
		add(rButtons, BorderLayout.WEST);
		
	}
	
	
	
	private String setColor(int x) {
		cols = guiColors.get(x).split(" ");
		color = cols[1];
		return color;
		
	}

}