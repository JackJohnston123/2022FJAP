import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class NumPuz extends JFrame {
	static final Integer[] gapList = {3, 4, 5, 6};
	static final String[] options = {"3", "4", "5", "6"};
	static final String[] templateOptions = {"Text", "Numbers"};
	Random ran = new Random();
	int size = 3;
	NumPuz frame;
	JComboBox horGapComboBox;
	JComboBox gridTemplateOptions;
	JComboBox logoBox;
	JButton applyButton = new JButton("Apply size");
	JButton logo = new JButton("logo here");
	JTextArea textField = new JTextArea();
	JButton[][] buttons = new JButton[10][10];
	JButton button;
	JPanel compsToExperiment = new JPanel();
	JPanel controls = new JPanel();
	JPanel topOptions = new JPanel();
	JPanel title = new JPanel();
	static JMenuBar mb = new JMenuBar();
	JMenu m1 = new JMenu("FILE");
	JMenu m2 = new JMenu("Help");

	JPanel sideGrid = new JPanel();
	int maxGap = 10;


	public NumPuz(String name) {
		super(name);
		setResizable(false);
	}

	public void initGaps() {
		horGapComboBox = new JComboBox(gapList);
		gridTemplateOptions = new JComboBox(templateOptions);
		logoBox = new JComboBox(options);
	}


	public void addComponentsToPane(final Container pane) {
		initGaps();
		mb.add(m1);
		mb.add(m2);
		JMenuItem open = new JMenuItem("Open");
		JMenuItem save = new JMenuItem("Save as");
		m1.add(open);
		m1.add(save);
		title.setLayout(new GridLayout(1,1));
		compsToExperiment.setLayout(new GridLayout(size,size));

		topOptions.setLayout(new GridBagLayout());

		GridBagConstraints to = new GridBagConstraints();

		button = new JButton("t 1");

		title.add(button);

		topOptions.setPreferredSize(new Dimension((int)(1 * 9.0)+maxGap,
				(int)(1 * 30.5)+maxGap * 1)); 

		button = new JButton("Mode:");

		to.gridx = 0;
		to.gridy = 1;


		topOptions.add(button, to);
		ButtonGroup mode = new ButtonGroup();
		JRadioButton design =new JRadioButton("Design");    
		mode.add(design);
		to.gridx = 1;
		to.gridy = 1;


		topOptions.add(design, to);

		JRadioButton play =new JRadioButton("Play");    
		mode.add(play);
		to.gridx = 3;
		to.gridy = 1;


		topOptions.add(play, to);
		ButtonGroup template = new ButtonGroup();
		button = new JButton("Set box name:");
		


		to.gridx = 5;
		to.gridy = 1;


		topOptions.add(button, to);

		JRadioButton a =new JRadioButton("text");    
		template.add(a);


		to.gridx = 6;
		to.gridy = 1;


		topOptions.add(a, to);

		JRadioButton c =new JRadioButton("numbers");    
		template.add(c);
		to.gridx = 7;
		to.gridy = 1;

		topOptions.add(c, to);
		
		JButton templateApply = new JButton("Apply size");
		to.gridx = 8;
		to.gridy = 1;
		topOptions.add(templateApply,to);
		
		gridTemplateOptions = new JComboBox(templateOptions);
		to.gridx = 9;
		to.gridy = 1;
		topOptions.add(gridTemplateOptions,to);
		
		topOptions.add(Box.createHorizontalStrut(10), to);
		topOptions.add(mb);


		controls.setLayout(new GridLayout(2,3));

		//Set up components preferred size
		JButton b = new JButton("Just fake button");
		Dimension buttonSize = b.getPreferredSize();
		textField.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 2.0)+maxGap,
				(int)(buttonSize.getHeight() * 20.5)+maxGap * 1)); 
		textField.setEditable(false);
		textField.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JPanel controls = new JPanel();
		JButton applyButton = new JButton("Apply size");
		JButton show = new JButton("save");
		JTextField points = new JTextField("points");
		JTextField time = new JTextField("time");
		JButton load = new JButton("load");
		JButton logo = new JButton();
		
		
		logo.setIcon(new ImageIcon("gamelogo.png"));
		logo.setBorderPainted(false);
		logo.setBackground(Color.white);
		
		
		controls.setLayout(new GridLayout(3,2,100,10));
		controls.setMaximumSize(new Dimension(620, 200));

		horGapComboBox = new JComboBox(gapList);
		controls.add(mb);
		controls.add(logo);
		controls.add(Box.createHorizontalStrut(10));
		controls.add(horGapComboBox);
		controls.add(time);
		//points
		controls.add(show); //show
		controls.add(applyButton); 
		controls.add(points); //points
		//title
		controls.add(load); //load
	
	

		//Add controls to set up horizontal and vertical gaps



		sideGrid.add(textField);
		newGrid(3);


		//Process the Apply gaps button press
		applyButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int size = (int) horGapComboBox.getSelectedItem();
				compsToExperiment.removeAll();          	
            	compsToExperiment.repaint();
            	newGrid(size);
            	
         	  
         	    compsToExperiment.revalidate();
				
				textField();

			}
			
			


		});
		
		templateApply.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent f){
				String option = gridTemplateOptions.getSelectedItem().toString();
				compsToExperiment.removeAll();          	
            	compsToExperiment.repaint();
            	if(option == "Numbers") {
            		newGrid(size);
            	}
            	if(option == "Text") {
            		String word = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            		newGridText(word);
            	}
         	  
         	    compsToExperiment.revalidate();
				
				

			}

			private void newGridText(String word) {
				if(word.length() < size * size) {
					word = "THE TEXT YOU ENTERED IS NOT LONG ENOUGH PLEASE TRY AGAIN AND MAKE IT LONGED IF YOU";
				}
				String[] words = new String[size];
				int count = 0;
				
				compsToExperiment.setLayout(new GridLayout(size,size));
				for(int h = 0; h < size;h++) 
					for(int r = 0; r < size;r++) {
						count++;
						buttons[r][h] = new JButton("" + word.split("(?!^)")[count-1]);
						compsToExperiment.add(buttons[r][h]);
				
			}
			}
			


		});
	
		pane.add(compsToExperiment,BorderLayout.CENTER);
		pane.add(controls, BorderLayout.NORTH);
		pane.add(sideGrid,BorderLayout.EAST);
		pane.add(topOptions,BorderLayout.AFTER_LAST_LINE);

	}
	
	
	
	public void newGrid(int size){
		compsToExperiment.setLayout(new GridLayout(size,size));
		for(int h = 0; h < size;h++) 
			for(int r = 0; r < size;r++) {
				buttons[r][h] = new JButton("row " + r + " height " + h );
				compsToExperiment.add(buttons[r][h]);
			}

	}
	private void textField() {
		textField.setText("Action listener activated" + "\n");
	}

	public static void createAndShowGUI() {
		NumPuz frame = new NumPuz("GridLayoutDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Set up the content pane.
		frame.addComponentsToPane(frame.getContentPane());
		//Display the window.
		frame.setSize(1280,768);

		frame.setVisible(true); 
		frame.setResizable(true);


	}

	public static void main(String[] args) {
		/* Use an appropriate Look and Feel */
		
	
		
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
				
			}
		});
	}

}