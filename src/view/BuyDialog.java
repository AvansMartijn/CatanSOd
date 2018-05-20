package view;                                                                                                                                                                    
                                                                                                                                                           
import javax.swing.JDialog;                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
                                                                                                                                                                                        
@SuppressWarnings("serial")                                                                                                                                                             
public class BuyDialog extends JDialog {                                                                                                                                               
                                                                                                                                                                                        
	private BuyPanel buyPanel;                                                                                                                                                      
                                                                                                                                                                            
	public BuyDialog(PlayerActionPanel playerActionPanel) {                                                                                                                                                               
                                                                                                                                                                                        
		setUndecorated(true);                                                                                                                                                                                                                                                                                           
                                                                                                                                                                                        
		buyPanel = new BuyPanel();                                                                                                                                                  
                                                                                                                                                                                        
		setContentPane(buyPanel);                                                                                                                                                     
                                                                                                                                                                                        
		pack();                                                                                                                                                                         
		setLocationRelativeTo(playerActionPanel);                                                                                                                                                    
                                                                                                                                                                                        
		toFront();                                                                                                                                                                      
		requestFocus();                                                                                                                                                                 
		setVisible(true);                                                                                                                                                               
                                                                                                                                                                                        
	}                                                                                                                                                                                                                                                                                                                
}                                                                                                                                                                                       
                                                                                                                                                                                        