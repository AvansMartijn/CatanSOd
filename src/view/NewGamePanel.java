package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
 
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
 
@SuppressWarnings("serial")
public class NewGamePanel extends JPanel {
    private JButton inviteButton;
    private JList<String> inviteInput;
    private ArrayList<String> availablePlayers;
    private ArrayList<String> invitedPlayers;
    private ArrayList<JButton> removeButtonsList;
    private JPanel invitedPlayersPanel;
    private JButton createGameButton;
    private JComboBox<String> boardChoice;
 
    public NewGamePanel(ArrayList<String> availablePlayers, String selfUsername) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.availablePlayers = availablePlayers;
        createGameButton = new JButton("Game aanmaken!");
        removeButtonsList = new ArrayList<>();
        boardChoice = new JComboBox<String>(new String[] { "Normaal", "Random" });
        invitedPlayers = new ArrayList<String>();
        invitedPlayers.add(selfUsername);
        invitedPlayersPanel = new InvitedPlayersPanel();
        inviteInput = new JList<String>();
        inviteInput.setMaximumSize(new Dimension(350, 150));
        inviteInput.setFont(inviteInput.getFont().deriveFont(Font.PLAIN, 17));
        inviteButton = new JButton("Uitnodigen");
        inviteButton.addActionListener((new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 invitedPlayers.add(inviteInput.getSelectedValue());
				 UpdateInvitedPlayers();
			}
		}));
        inviteButton.setMaximumSize(new Dimension(350, 30));
 
        JLabel header = new JLabel("Nieuwe Game Aanmaken");
        header.setFont(new Font(header.getFont().getFontName(), Font.PLAIN, 20));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(header);
        this.add(new InvitePlayerPanel());
        this.add(invitedPlayersPanel);
        this.setVisible(true);
 
//        invitedPlayers.add("naam1");
//        invitedPlayers.add("naam2");
//        invitedPlayers.add("naam3");
//        invitedPlayers.add("naam4");
// 
        UpdateInvitedPlayers();
    }
 
    public void UpdateInvitedPlayers() {
        this.remove(invitedPlayersPanel);
        invitedPlayersPanel = new InvitedPlayersPanel();
        this.add(invitedPlayersPanel);
        revalidate();
    }
 
	public class InvitePlayerPanel extends JPanel {
		JScrollPane scrollPane;
        public InvitePlayerPanel() {
//        	int height = currentGames.getGamePanels().size() * 110;
//    		currentGames.setPreferredSize(new Dimension(400, height));
    		scrollPane = new JScrollPane(inviteInput, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
    				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    		scrollPane.getHorizontalScrollBar().setUnitIncrement(20);
    		scrollPane.setPreferredSize(new Dimension(100, 200));
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            DefaultListModel<String> listModel = new DefaultListModel<String>();
            DefaultListCellRenderer renderer = (DefaultListCellRenderer) inviteInput.getCellRenderer();
            renderer.setHorizontalAlignment(SwingConstants.CENTER);
            for (String user : availablePlayers) {
                listModel.addElement(user);
            }
            inviteInput.setModel(listModel);
            JLabel label = new JLabel("Selecteer een naam en druk op 'Uitnodigen'");
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(label);
            inviteInput.setAlignmentX(Component.CENTER_ALIGNMENT);
//            this.add(inviteInput);
            this.add(scrollPane);
            inviteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(Box.createVerticalGlue());
            this.add(inviteButton);
        }
    }
 
    public class InvitedPlayersPanel extends JPanel {
        public InvitedPlayersPanel() {
//        	this.setPreferredSize(new Dimension(100, 300));
            this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            for (String username : invitedPlayers) {
                JPanel panel = new JPanel();
                JLabel name = new JLabel(username);
                name.setFont(name.getFont().deriveFont(Font.BOLD, 16));
                JButton remove = new JButton("X");
                removeButtonsList.add(remove);
                remove.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int indexOf = removeButtonsList.indexOf(remove);
						removeButtonsList.remove(indexOf);
						invitedPlayers.remove(indexOf);
						UpdateInvitedPlayers();
					}
				});
                panel.add(name);
                panel.add(remove);
                this.add(panel);
            }
            this.add(new GameOptionsPanel());
        }
    }
 
    public class GameOptionsPanel extends JPanel {
        public GameOptionsPanel() {
            JPanel buttons = new JPanel();
            JLabel label = new JLabel("Bord keuze: ");
            buttons.add(label);
            buttons.add(boardChoice);
            this.add(buttons);
            this.add(createGameButton);
        }
    }
    
    public JButton getCreateGameButton() {
    	return createGameButton;
    }

	public ArrayList<String> getInvitedPlayers() {
		return invitedPlayers;
	}
    
    
 
}