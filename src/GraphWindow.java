import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GraphWindow extends JFrame {
    private Map<String, int[]> data;
    public JLabel predictedLabel;
    public String predictedString;

    public GraphWindow(String[] ddATP, String[] ddTTP, String[] ddGTP, String[] ddCTP) {
        // Prepare data
        data = new HashMap<>();
        data.put("ddATP", calculateLengths(ddATP));
        data.put("ddTTP", calculateLengths(ddTTP));
        data.put("ddGTP", calculateLengths(ddGTP));
        data.put("ddCTP", calculateLengths(ddCTP));

        ImageIcon image = new ImageIcon("icon.png");
        setIconImage(image.getImage());
        // Set up the frame
        setTitle("Chromatogram");
        setSize(800, 900);
        setLayout(new BorderLayout());
        
        
        
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        predictedLabel = new JLabel();
        predictedLabel.setText(predictedString);
        predictedLabel.setForeground(Color.white);
        // Add graph panel
        add(new GraphPanel(), BorderLayout.CENTER);
        add(predictedLabel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private int[] calculateLengths(String[] fragments) {
        int[] lengths = new int[fragments.length];
        for (int i = 0; i < fragments.length; i++) {
            lengths[i] = fragments[i] != null ? fragments[i].length() : 0;
        }
        return lengths;
    }

    private class GraphPanel extends JPanel {
    	public GraphPanel() {
            setBackground(new Color(38, 35, 41)); // Set the background to black
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            
            getContentPane().setBackground(new Color(38, 35, 41));
            
            g2d.setColor(Color.WHITE);

            // Draw axes
            g2d.drawLine(50, 500, 750, 500); // X-axis
            g2d.drawLine(50, 500, 50, 50);  // Y-axis

            // Draw axis labels
            g2d.drawString("Position", 380, 530);
            g2d.drawString("Length", 10, 60);

            // Define colors for each dataset
            Color colorA = new Color(196, 126, 237);
            Color colorT = new Color(113, 231, 235);
            Color colorG = new Color(255, 3, 82);
            Color colorC = new Color(220, 255, 23);
            
            Map<String, Color> colors = new HashMap<>();
            
            colors.put("ddATP", colorA);
            colors.put("ddTTP", colorT);
            colors.put("ddGTP", colorG);
            colors.put("ddCTP", colorC);

            // Plot data points
            int xSpacing = 60;
            for (Map.Entry<String, int[]> entry : data.entrySet()) {
                String name = entry.getKey();
                int[] lengths = entry.getValue();
                g2d.setColor(colors.get(name));

                for (int i = 0; i < lengths.length; i++) {
                    int x = 50 + (i + 1) * xSpacing;
                    int y = 500 - lengths[i] * 40; // Scale length to fit the graph
                    g2d.fillOval(x - 5, y - 5, 10, 10); // Draw a point
                }

                // Add legend
                g2d.setColor(colors.get(name));
                g2d.fillRect(700, 100 + 20 * new java.util.ArrayList<>(colors.keySet()).indexOf(name), 10, 10);
                g2d.setColor(Color.WHITE);
                g2d.drawString(name, 720, 110 + 20 * new java.util.ArrayList<>(colors.keySet()).indexOf(name));
            }

            // Draw Y-axis markers and labels
            g2d.setColor(Color.WHITE);
            for (int i = 0; i <= 10; i++) {
                int y = 500 - i * 40; // Calculate Y positions
                g2d.drawLine(45, y, 50, y); // Draw tick mark
                g2d.drawString(Integer.toString(i), 30, y + 5); // Add label
            }
        }
    }
    public void predictedSeq(String dnaSequence) {
		int size = dnaSequence.length();
		StringBuilder pre = new StringBuilder();
		for(int i = 0; i < size; i++) {
			
			if (dnaSequence.charAt(i) == 'T') {
				pre.append('A');
			} else if (dnaSequence.charAt(i) == 'A') {
				pre.append('T');
			} else if (dnaSequence.charAt(i) == 'G') {
				pre.append('C');
			} else if (dnaSequence.charAt(i) == 'C') {
				pre.append('G');
			}
		}
		String predict = pre.toString();
		this.predictedString = predict;
	
	}
    
}
