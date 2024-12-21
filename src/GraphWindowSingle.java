import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class GraphWindowSingle{
    private Map<String, int[]> data;
    public JLabel predictedLabel;
    public JLabel statement;
    public String predictedString;
    JFrame frame;

    public GraphWindowSingle(String[] ddATP, String[] ddTTP, String[] ddGTP, String[] ddCTP, String predictedString) {
    	this.predictedString = predictedString;
        // Prepare data
        data = new HashMap<>();
        data.put("ddATP", calculateLengths(ddATP));
        data.put("ddTTP", calculateLengths(ddTTP));
        data.put("ddGTP", calculateLengths(ddGTP));
        data.put("ddCTP", calculateLengths(ddCTP));

        ImageIcon image = new ImageIcon("icon.png");
        
        frame = new JFrame();
        frame.setIconImage(image.getImage());
        
        // Set up the frame
        frame.setTitle("Chromatogram");
        frame.setResizable(false);
        frame.setSize(800, 900);
        frame.setLayout(new BorderLayout());
        
        
        //setLocationRelativeTo(null);

        predictedLabel = new JLabel();
        
        predictedLabel.setText(predictedString != null ? predictedString : "No prediction available");
        predictedLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        predictedLabel.setForeground(Color.WHITE);
        predictedLabel.setBackground(Color.BLACK); // Add a background color to make it more visible
        predictedLabel.setOpaque(true); // Required for background color
        predictedLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align text
        predictedLabel.setPreferredSize(new Dimension(frame.getWidth(), 100));
        
        statement = new JLabel();
        statement.setText("Predicted DNA sequence");
        statement.setFont(new Font("Arial", Font.PLAIN, 20));
        statement.setForeground(Color.white);
        statement.setBackground(Color.BLACK); // Add a background color to make it more visible
        statement.setOpaque(true); // Required for background color
        statement.setHorizontalAlignment(SwingConstants.CENTER); // Center align text
        statement.setPreferredSize(new Dimension(frame.getWidth(), 20));
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.black);
        panel.setPreferredSize(new Dimension(frame.getWidth(), 150));
        
        if(predictedString != null) panel.add(statement);
        panel.add(predictedLabel);
        
        
        // Add graph panel
        frame.add(new GraphPanel(), BorderLayout.CENTER);
        // Use CENTER for the main panel
     
        frame.add(panel, BorderLayout.SOUTH); // SOUTH for the label

        frame.setVisible(true);

        // Force revalidation and repaint
        frame.revalidate();
        frame.repaint();
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
            setBackground(new Color(38, 35, 41));
            setPreferredSize(new Dimension(800, 700));
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            
            frame.getContentPane().setBackground(new Color(38, 35, 41));
            
            g2d.setColor(Color.WHITE);

            
            g2d.drawLine(50, 500, 750, 500); // X-axis
            g2d.drawLine(50, 500, 50, 50);  // Y-axis

            // Draw axis labels

            // g2d.drawString("A", 150, 530);
            // g2d.drawString("T", 250, 530);
            // g2d.drawString("G", 350, 530);
            // g2d.drawString("C", 450, 530);
            g2d.drawString("Length", 10, 60);

           
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
            int xSpacing = 100;
            for (Map.Entry<String, int[]> entry : data.entrySet()) {
                String name = entry.getKey();
                int[] lengths = entry.getValue();
                g2d.setColor(colors.get(name));

                for (int i = 0; i < lengths.length; i++) {
                    // int x = 50 + (i + 1) * xSpacing;
                    int y = 500 - lengths[i] * 40; 
                    // g2d.fillOval(x - 5, y - 5, 10, 10); 
                    g2d.fillRect(xSpacing -5, y-5, 40, 8);
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
                int y = 500 - i * 40; 
                g2d.drawLine(45, y, 50, y); 
                g2d.drawString(Integer.toString(i), 30, y + 5);
            }
        }
    }
    
    
}
