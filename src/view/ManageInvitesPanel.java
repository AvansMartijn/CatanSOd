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

import model.Catan;
import model.Player;
import view.NewGameFrame.NewGamePanel.GameOptionsPanel;

@SuppressWarnings("serial")
public class ManageInvitesPanel extends JPanel {
	private JButton inviteButton;
	private JList<String> inviteInput;
	private ArrayList<String> availablePlayers;
	private ArrayList<String> invitedPlayers;
	private ArrayList<JButton> removeButtonsList;
	private JPanel invitedPlayersPanel;
	private JButton saveInvitesButton;

	public ManageInvitesPanel(String selfUsername, ArrayList<String> availablePlayers, Catan game) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.availablePlayers = availablePlayers;
		saveInvitesButton = new JButton("Opslaan");
		removeButtonsList = new ArrayList<>();

		invitedPlayers = new ArrayList<String>();
		addPlayer(selfUsername);
		for(Player p : game.getPlayers()) {
			if(!p.getUsername().equals(selfUsername)) {
				addPlayer(p.getUsername());
			}
		}
		invitedPlayersPanel = new InvitedPlayersPanel();
		inviteInput = new JList<String>();
		inviteInput.setMaximumSize(new Dimension(350, 150));
		inviteInput.setFont(inviteInput.getFont().deriveFont(Font.PLAIN, 17));
		inviteButton = new JButton("Uitnodigen");
		inviteButton.addActionListener((new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addPlayer(inviteInput.getSelectedValue());
				if (invitedPlayers.size() == 4) {
					inviteButton.setEnabled(false);
				}
			}
		}));
		inviteButton.setMaximumSize(new Dimension(350, 30));

		JLabel header = new JLabel("Invites Aanpassen");
		header.setFont(new Font(header.getFont().getFontName(), Font.PLAIN, 20));
		header.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(header);
		this.add(new InvitePlayerPanel());
		this.add(invitedPlayersPanel);
		this.setVisible(true);

		// invitedPlayers.add("naam1");
		// invitedPlayers.add("naam2");
		// invitedPlayers.add("naam3");
		// invitedPlayers.add("naam4");
		//

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
			// int height = currentGames.getGamePanels().size() * 110;
			// currentGames.setPreferredSize(new Dimension(400, height));
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
			// this.add(inviteInput);
			this.add(scrollPane);
			inviteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.add(Box.createVerticalGlue());
			this.add(inviteButton);
		}
	}

	public class InvitedPlayersPanel extends JPanel {
		public InvitedPlayersPanel() {
			// this.setPreferredSize(new Dimension(100, 300));
			removeButtonsList.clear();
			int idx = 0;
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			for (String username : invitedPlayers) {
				JPanel panel = new JPanel();
				JLabel name = new JLabel(username);
				name.setFont(name.getFont().deriveFont(Font.BOLD, 16));
				JButton remove = new JButton("X");
				if (idx == 0) {
					remove.setEnabled(false);
				}
				removeButtonsList.add(remove);
				remove.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int indexOf = removeButtonsList.indexOf(remove);
						removeButtonsList.remove(indexOf);
						invitedPlayers.remove(indexOf);
						UpdateInvitedPlayers();
						if (invitedPlayers.size() < 3) {
							inviteButton.setEnabled(true);
						}
					}
				});
				panel.add(name);
				panel.add(remove);
				this.add(panel);
				idx++;
			}
			this.add(new GameOptionsPanel());
		}
	}

	public class GameOptionsPanel extends JPanel {
		public GameOptionsPanel() {
			JPanel buttons = new JPanel();
			this.add(buttons);
			this.add(saveInvitesButton);
		}
	}

	public JButton getSaveInvitesButton() {
		return saveInvitesButton;
	}

	public ArrayList<String> getInvitedPlayers() {
		return invitedPlayers;
	}

	private void addPlayer(String username) {

		invitedPlayers.add(username);
		UpdateInvitedPlayers();
		if (invitedPlayers.size() == 1) {
			removeButtonsList.get(1).setEnabled(false);
		}
		UpdateInvitedPlayers();
	}

}
