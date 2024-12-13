import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class MainFrame extends JFrame implements ActionListener{
	// Added Fonts
    static Font mono = new Font("Monospaced", Font.BOLD, 18);
    static Font sansSerifBig = new Font("SansSerif", Font.BOLD, 25);
    static Font sansSerif = new Font("SansSerif", Font.BOLD, 15);
    static Font sansSerifSmall = new Font("SansSerif", Font.BOLD, 14);
    static Font dialog = new Font("Dialog", Font.BOLD, 18);

    // Our Color Palette
    static Color c1 = new Color(59, 30, 84); // dark purple
    static Color c2 = new Color(155, 126, 189); // lighter
    static Color c3 = new Color(212, 190, 228); // more lighter
    static Color c4 = new Color(238, 238, 238); // more lighter

	// declaration
    JButton method1;
	JFrame base;
	JLabel title;
	GridBagConstraints gbc;
	
    MainFrame(){
		base = new JFrame();
        base.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Window Size
        base.setSize(950, 1000);
        base.setResizable(false);;

        // Title
        base.setTitle("DNA Sanger's Principle Simulation");

        // Icon
        ImageIcon image = new ImageIcon("icon.png");
        base.setIconImage(image.getImage());

        // Background
        base.getContentPane().setBackground(new Color(27, 27, 51));

        // Set GridBagLayout for centering
        base.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some space around components
        gbc.gridx = 0; // Center horizontally
        gbc.gridy = 0; // Center vertically
        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        // Base Frame Label
        title = new JLabel("DNA Sequencing");
        title.setFont(sansSerifBig);
        title.setForeground(Color.white);
        title.setBackground(new Color(27, 27, 51));
        title.setOpaque(true);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);

        // Button 1
        method1 = new JButton();
        method1.setText("START");
        method1.setFont(sansSerifSmall);
        method1.setBackground(new Color(0, 255, 98));
        method1.setForeground(new Color(18, 33, 24));
        method1.setFocusable(false);
        method1.setPreferredSize(new Dimension(300, 50)); 

        method1.addActionListener(this);
        
        
        gbc.anchor = GridBagConstraints.CENTER;
        base.add(title, gbc); // Add the label to the center

        
        gbc.gridy = 1; 
        base.add(Box.createVerticalStrut(20), gbc); 

       
        base.add(method1, gbc); 

        // Set the frame visible
        base.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == method1) {
			// System.out.println("Clicked");
			base.dispose();
			ExperimentFrame frame = new ExperimentFrame();

		}
		
	}
}
