import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import javax.swing.text.*;

public class ExperimentFrame extends JFrame implements ActionListener {

	String templateDNA;
	JButton enterSeq;
	JTextField input;
	JLabel templateDNAlabel;
	JLabel title;

	JPanel panelA;
	JPanel panelT;
	JPanel panelG;
	JPanel panelC;
	
	JLabel labelA;
	JLabel labelT;
	JLabel labelG;
	JLabel labelC;

	ExperimentFrame() {
		ImageIcon icon = new ImageIcon("icon.png");
		Image iconImage = icon.getImage();

		JFrame frame = new JFrame();
		frame.setSize(1850, 1080);
		//frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(iconImage);
		frame.setTitle("Experiment Window");
		frame.getContentPane().setBackground(new Color(7, 1, 66));
		frame.setLayout(new BorderLayout());

		// Input Text er jonne
		JLabel inputText = new JLabel();
		inputText.setText("Enter The DNA Sequence:");
		inputText.setSize(100, 100);
		inputText.setFont(new Font("Arial", Font.BOLD, 18));
		inputText.setForeground(new Color(238, 238, 238));

		// Input Field
		input = new JTextField(100); // column set korsi
		((AbstractDocument) input.getDocument()).setDocumentFilter(new ATGCDocumentFilter());
		input.setPreferredSize(new Dimension(700, 30));
		input.setFont(new Font("Arial", Font.BOLD, 20));
		input.setBackground(Color.white);
		input.setForeground(new Color(59, 30, 84));

		// Enter Sequence Button
		enterSeq = new JButton("ENTER");
		enterSeq.setFocusable(false);
		enterSeq.setBackground(new Color(51, 255, 133));
		enterSeq.setForeground(new Color(18, 33, 24));
		enterSeq.addActionListener(this);

		// Making the enter button automatically press on the enter JButton
		// jate keyboard e enter click korlei button e click pore :3
		input.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("ENTER"), "pressEnter");
		input.getActionMap().put("pressEnter", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enterSeq.doClick(); // Simulate a button click
			}
		});

		// panels
		JPanel topPanel = new JPanel();
		JPanel midPanel = new JPanel();
		JPanel bottomPanel = new JPanel();

		topPanel.setBackground(new Color(7, 1, 66));
		midPanel.setBackground(new Color(7, 1, 66));
		bottomPanel.setBackground(new Color(7, 1, 66));

		topPanel.setPreferredSize(new Dimension(1920, 300));
		midPanel.setPreferredSize(new Dimension(1920, 200));
		bottomPanel.setPreferredSize(new Dimension(1920, 500));

		bottomPanel.setLayout(new GridLayout(1, 4, 10, 0)); // 1 row, 4 columns, with 10px horizontal gap
		// bottomPanel.setLayout(new GridLayout(1, 4));
// PANEL A //
		panelA = new JPanel();
		panelA.setFont(new Font("Arial", Font.BOLD, 20));
		panelA.setBackground(new Color(39, 11, 61));
		panelA.setLayout(new BoxLayout(panelA, BoxLayout.Y_AXIS));
		labelA = new JLabel("      ddATP Fragments");
		labelA.setForeground(new Color(221, 130, 224));
		labelA.setFont(new Font("Arial", Font.BOLD, 15));
		panelA.add(labelA);
		panelA.setPreferredSize(new Dimension(100, 100));
// PANEL T //
		panelT = new JPanel();
		panelT.setBackground(new Color(39, 11, 61));
		panelT.setLayout(new BoxLayout(panelT, BoxLayout.Y_AXIS));
		labelT = new JLabel("   ddTTP Fragments");
		labelT.setForeground(new Color(123, 203, 237));
		labelT.setFont(new Font("Arial", Font.BOLD, 15));
		panelT.add(labelT);
		panelT.setPreferredSize(new Dimension(100, 100));
// PANEL G //
		panelG = new JPanel();
		panelG.setBackground(new Color(39, 11, 61));
		panelG.setLayout(new BoxLayout(panelG, BoxLayout.Y_AXIS));
		labelG = new JLabel("   ddGTP Fragments");
		labelG.setForeground(new Color(214, 21, 99));
		labelG.setFont(new Font("Arial", Font.BOLD, 15));
		panelG.add(labelG);
		panelG.setPreferredSize(new Dimension(100, 100));
// PANEL C //
		panelC = new JPanel();
		panelC.setBackground(new Color(39, 11, 61));
		panelC.setLayout(new BoxLayout(panelC, BoxLayout.Y_AXIS));
		labelC = new JLabel("  ddCTP Fragments");
		labelC.setForeground(new Color(210, 255, 61));
		labelC.setFont(new Font("Arial", Font.BOLD, 15));
		panelC.add(labelC);
		panelC.setPreferredSize(new Dimension(100, 100));

		bottomPanel.add(panelA);
		bottomPanel.add(panelT);
		bottomPanel.add(panelG);
		bottomPanel.add(panelC);

		ImageIcon tube01 = new ImageIcon("test-tube01.png");
		ImageIcon tube02 = new ImageIcon("test-tube02.png");
		ImageIcon tube03 = new ImageIcon("test-tube03.png");
		ImageIcon tube04 = new ImageIcon("test-tube04.png");

		ImageIcon loadBar01 = new ImageIcon("custom-load-bar01.png");
		ImageIcon loadBar02 = new ImageIcon("custom-load-bar02.png");
		ImageIcon loadBar03 = new ImageIcon("custom-load-bar03.png");
		ImageIcon loadBar04 = new ImageIcon("custom-load-bar04.png");

		JLabel label1 = new JLabel(loadBar01);
		JLabel label2 = new JLabel(loadBar02);
		JLabel label3 = new JLabel(loadBar03);
		JLabel label4 = new JLabel(loadBar04);

		JLabel testTube1 = new JLabel(tube01);
		JLabel testTube2 = new JLabel(tube02);
		JLabel testTube3 = new JLabel(tube03);
		JLabel testTube4 = new JLabel(tube04);

		title = new JLabel();
		title.setText("Template DNA Sequence: ");
		title.setFont(new Font("Arial", Font.BOLD, 18));
		title.setForeground(Color.white);

		templateDNAlabel = new JLabel();
		templateDNAlabel.setText("");
		templateDNAlabel.setFont(new Font("Arial", Font.BOLD, 18));
		templateDNAlabel.setForeground(Color.white);

		topPanel.add(inputText);
		topPanel.add(input);
		topPanel.add(enterSeq);

		topPanel.add(label1);
		topPanel.add(label2);
		topPanel.add(label3);
		topPanel.add(label4);
		topPanel.add(title);
		topPanel.add(templateDNAlabel);

		midPanel.add(testTube1);
		midPanel.add(testTube2);
		midPanel.add(testTube3);
		midPanel.add(testTube4);

		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(midPanel, BorderLayout.CENTER);
		frame.add(bottomPanel, BorderLayout.SOUTH);

		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == enterSeq) {

			templateDNA = input.getText();
			
			panelA.removeAll();
	        panelT.removeAll();
	        panelG.removeAll();
	        panelC.removeAll();
	        
	        panelA.add(labelA);
	        panelT.add(labelT);
	        panelG.add(labelG);
	        panelC.add(labelC);

			String coloredDNA = coloringFragments(templateDNA);

			// Set the formatted text to the JLabel
			templateDNAlabel.setText(coloredDNA);
			/*
			 * Fragments of ddATP added to panelA
			 */
			String[] A = fragmentsA(templateDNA);
			int numberA = 1;
			for (String option : A) {
				String colored = coloringFragments(option);
				JLabel fragmentsLabel = new JLabel(colored);
				fragmentsLabel.setFont(new Font("Arial", Font.BOLD, 18));
				fragmentsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
				// fragmentsLabel.setForeground(Color.black);

				panelA.add(fragmentsLabel);
				numberA++;

			}

			/*
			 * Fragments of ddTTP added to panelT
			 */
			String[] T = fragmentsT(templateDNA);
			
			for (String option : T) {

				String colored = coloringFragments(option);
				JLabel fragmentsLabel = new JLabel(colored);
				fragmentsLabel.setFont(new Font("Arial", Font.BOLD, 18));
				fragmentsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
				// fragmentsLabel.setForeground(Color.black);

				panelT.add(fragmentsLabel);
				

			}

			/*
			 * Fragments of ddGTP added to panelT
			 */
			String[] G = fragmentsG(templateDNA);
			int numberG = 1;
			for (String option : G) {

				String colored = coloringFragments(option);
				JLabel fragmentsLabel = new JLabel(colored);
				fragmentsLabel.setFont(new Font("Arial", Font.BOLD, 18));
				fragmentsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
				// fragmentsLabel.setForeground(Color.black);

				panelG.add(fragmentsLabel);
				numberG++;

			}

			/*
			 * Fragments of ddGTP added to panelT
			 */ 
			String[] C = fragmentsC(templateDNA);
			int numberC = 1;
			for (String option : C) {

				String colored = coloringFragments(option);
				JLabel fragmentsLabel = new JLabel(colored);
				fragmentsLabel.setFont(new Font("Arial", Font.BOLD, 18));
				fragmentsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
				// fragmentsLabel.setForeground(Color.black);

				panelC.add(fragmentsLabel);
				numberC++;

			}
			
			panelA.revalidate();
	        panelT.revalidate();
	        panelG.revalidate();
	        panelC.revalidate();

	        panelA.repaint();
	        panelT.repaint();
	        panelG.repaint();
	        panelC.repaint();

		}

	}

	public String coloringFragments(String dnaSequence) {
		StringBuilder coloredDNA = new StringBuilder("<html><span style='color: white;'></span>");
		for (char nucleotide : dnaSequence.toCharArray()) {
			switch (nucleotide) {
			case 'A':
				coloredDNA.append("<span style='color:rgb(196, 126, 237);'>A</span>");
				break;
			case 'T':
				coloredDNA.append("<span style='color:rgb(113, 231, 235);'>T</span>");
				break;
			case 'G':
				coloredDNA.append("<span style='color:rgb(255, 3, 82);'>G</span>");
				break;
			case 'C':
				coloredDNA.append("<span style='color:rgb(220, 255, 23);'>C</span>");
				break;
			default:
				coloredDNA.append(nucleotide); // Keep other characters as-is
			}
		}
		coloredDNA.append("</html>");

		return (coloredDNA.toString());

	}

	/*
	 * Methods to trim DNA sequence
	 */

	// the algorithm to trim the strings
	// took me one night, awesome experience

	public String[] fragmentsA(String dnaSequence) {
		int n = 0; // number of 'T' in the String
		// karon ddATP 'T' te giyei shesh lagbe

		for (int i = 0; i < dnaSequence.length(); i++) {
			if (dnaSequence.charAt(i) == 'T') {
				n++;
			}
		}

		int[] places = new int[n];
		int index = 0;

		for (int j = 0; j < dnaSequence.length(); j++) {

			if (dnaSequence.charAt(j) == 'T') {
				places[index] = j;
				index++;

			}
		}

		String[] fragments = new String[n];

		StringBuilder fragment = new StringBuilder();

		for (int j = 0; j < n; j++) {
			for (int i = 0; i < dnaSequence.length(); i++) {
				if (i == places[j]) {
					fragment.append('A');
					break;
				} else if (dnaSequence.charAt(i) == 'T') {
					fragment.append('A');
				} else if (dnaSequence.charAt(i) == 'A') {
					fragment.append('T');
				} else if (dnaSequence.charAt(i) == 'G') {
					fragment.append('C');
				} else if (dnaSequence.charAt(i) == 'C') {
					fragment.append('G');
				}

			}

			fragments[j] = fragment.toString();
			fragment.setLength(0);
		}

		return fragments;

	}

	public String[] fragmentsT(String dnaSequence) {
		int n = 0;

		for (int i = 0; i < dnaSequence.length(); i++) {
			if (dnaSequence.charAt(i) == 'A') {
				n++;
			}
		}

		int[] places = new int[n];
		int index = 0;

		for (int j = 0; j < dnaSequence.length(); j++) {

			if (dnaSequence.charAt(j) == 'A') {
				places[index] = j;
				index++;

			}
		}

		String[] fragments = new String[n];

		StringBuilder fragment = new StringBuilder();

		for (int j = 0; j < n; j++) {
			for (int i = 0; i < dnaSequence.length(); i++) {
				if (i == places[j]) {
					fragment.append('T');
					break;
				} else if (dnaSequence.charAt(i) == 'T') {
					fragment.append('A');
				} else if (dnaSequence.charAt(i) == 'A') {
					fragment.append('T');
				} else if (dnaSequence.charAt(i) == 'G') {
					fragment.append('C');
				} else if (dnaSequence.charAt(i) == 'C') {
					fragment.append('G');
				}

			}

			fragments[j] = fragment.toString();
			fragment.setLength(0);
		}

		return fragments;

	}

	public String[] fragmentsG(String dnaSequence) {
		int n = 0;

		for (int i = 0; i < dnaSequence.length(); i++) {
			if (dnaSequence.charAt(i) == 'C') {
				n++;
			}
		}

		int[] places = new int[n];
		int index = 0;

		for (int j = 0; j < dnaSequence.length(); j++) {

			if (dnaSequence.charAt(j) == 'C') {
				places[index] = j;
				index++;

			}
		}

		String[] fragments = new String[n];

		StringBuilder fragment = new StringBuilder();

		for (int j = 0; j < n; j++) {
			for (int i = 0; i < dnaSequence.length(); i++) {
				if (i == places[j]) {
					fragment.append('G');
					break;
				} else if (dnaSequence.charAt(i) == 'T') {
					fragment.append('A');
				} else if (dnaSequence.charAt(i) == 'A') {
					fragment.append('T');
				} else if (dnaSequence.charAt(i) == 'G') {
					fragment.append('C');
				} else if (dnaSequence.charAt(i) == 'C') {
					fragment.append('G');
				}

			}

			fragments[j] = fragment.toString();
			fragment.setLength(0);
		}

		return fragments;

	}
	
	public String[] fragmentsC(String dnaSequence) {
		int n = 0;

		for (int i = 0; i < dnaSequence.length(); i++) {
			if (dnaSequence.charAt(i) == 'G') {
				n++;
			}
		}

		int[] places = new int[n];
		int index = 0;

		for (int j = 0; j < dnaSequence.length(); j++) {

			if (dnaSequence.charAt(j) == 'G') {
				places[index] = j;
				index++;

			}
		}

		String[] fragments = new String[n];

		StringBuilder fragment = new StringBuilder();

		for (int j = 0; j < n; j++) {
			for (int i = 0; i < dnaSequence.length(); i++) {
				if (i == places[j]) {
					fragment.append('C');
					break;
				} else if (dnaSequence.charAt(i) == 'T') {
					fragment.append('A');
				} else if (dnaSequence.charAt(i) == 'A') {
					fragment.append('T');
				} else if (dnaSequence.charAt(i) == 'G') {
					fragment.append('C');
				} else if (dnaSequence.charAt(i) == 'C') {
					fragment.append('G');
				}

			}

			fragments[j] = fragment.toString();
			fragment.setLength(0);
		}

		return fragments;

	}

}
