package view;                                                                                                                                                                    
                                                                                                                                                                                        
import java.awt.Color;                                                                                                                                                                  
import java.awt.Dimension;                                                                                                                                                              
import java.awt.Font;                                                                                                                                                                   
                                                                                                                                                                                        
import javax.swing.JButton;                                                                                                                                                             
import javax.swing.JDialog;                                                                                                                                                             
                                                                                                                                                              
import javax.swing.JLabel;                                                                                                                                                              
import javax.swing.JPanel;                                                                                                                                                              
                                                                                                                                                                                        
@SuppressWarnings("serial")                                                                                                                                                             
public class BuyDialog extends JDialog {                                                                                                                                               
                                                                                                                                                                                        
	private BuildPanel buildPanel;                                                                                                                                                      
                                                                                                                                                                                        
	private final int PANEL_WIDTH = 340;                                                                                                                                                
	private final int PANEL_HEIGHT = 350;                                                                                                                                               
                                                                                                                                                                                        
	private final int BUTTON_WIDTH = 140;                                                                                                                                               
	private final int BUTTON_HEIGHT = 90;                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                                                                                                                                                                                        
	private Color backgroundColor = new Color(189, 133, 100);                                                                                                                           
	private Color textBackgroundColor = new Color(223, 190, 172);                                                                                                                       
	private Color TextColor = new Color(50, 50, 50);                                                                                                                                    
                                                                                                                                                                                        
	private JLabel title;    
	private JLabel subTitle;
                                                                                                                                                      
	private JButton yesButton;                                                                                                                                                         
	private JButton returnButton;                                                                                                                                                       
                                                                                                                                                                                        
	public BuyDialog() {                                                                                                                                                               
                                                                                                                                                                                        
		setUndecorated(true);                                                                                                                                                                                                                                                                                           
                                                                                                                                                                                        
		buildPanel = new BuildPanel();                                                                                                                                                  
                                                                                                                                                                                        
		setContentPane(buildPanel);                                                                                                                                                     
                                                                                                                                                                                        
		pack();                                                                                                                                                                         
		setLocationRelativeTo(null);                                                                                                                                                    
                                                                                                                                                                                        
		setVisible(true);                                                                                                                                                               
		toFront();                                                                                                                                                                      
		requestFocus();                                                                                                                                                                 
                                                                                                                                                                                        
	}                                                                                                                                                                                   
                                                                                                                                                                                        
	public class BuildPanel extends JPanel {                                                                                                                                            
                                                                                                                                                                                        
		public BuildPanel() {                                                                                                                                                           
                                                                                                                                                                                        
			setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));                                                                                                                 
			setBackground(backgroundColor);                                                                                                                                             
                                                                                                                                                                                        
			setLayout(null);                                                                                                                                                            
                                                                                                                                                                                        
			title = new JLabel("KOPEN");                                                                                                                                               
			title.setFont(new Font("SansSerif", Font.BOLD, 35));                                                                                                                        
			title.setForeground(TextColor);                                                                                                                                             
			title.setBounds(110, 20, 180, 40);                                                                                                                                           
			add(title);                                                                                                                                                                 
                                                                                                                                                                                        
			subTitle = new JLabel("Wil je een ontwikkelingskaart kopen?");
			subTitle.setFont(new Font("SansSerif", Font.BOLD, 14));                                                                                                                        
			subTitle.setForeground(TextColor);                                                                                                                                             
			subTitle.setBounds(35, 90, 300, 40);                                                                                                                                           
			add(subTitle);    
                                                                                                                                                                                        
			yesButton = new JButton("JA");                                                                                                                                           
			yesButton.setBounds(20, 180, BUTTON_WIDTH, BUTTON_HEIGHT);                                                                                                    
			yesButton.setFont(new Font("SansSerif", Font.BOLD, 30));                                                                                                                   
			yesButton.setBackground(textBackgroundColor);                                                                                                                              
			yesButton.setForeground(TextColor);                                                                                                                                        
			add(yesButton);                                                                                                                                                            
                                                                                                                                                                                        
			returnButton = new JButton("NEE");                                                                                                                                   
			returnButton.setBounds(180, 180, BUTTON_WIDTH, BUTTON_HEIGHT);                                                                                                  
			returnButton.setFont(new Font("SansSerif", Font.BOLD, 30));                                                                                                                 
			returnButton.setBackground(textBackgroundColor);                                                                                                                            
			returnButton.setForeground(TextColor);                                                                                                                                      
			add(returnButton);                                                                                                                                                          
		}                                                                                                                                                                               
                                                                                                                                                                                        
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
                                                                                                                                                                                        
	public JButton getYesButton() {                                                                                                                                                    
		return yesButton;                                                                                                                                                              
	}                                                                                                                                                                                   
                                                                                                                                                                                        
	public JButton getReturnButton() {                                                                                                                                                  
		return returnButton;                                                                                                                                                            
	}                                                                                                                                                                                   
}                                                                                                                                                                                       
                                                                                                                                                                                        