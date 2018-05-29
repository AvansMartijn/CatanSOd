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

	private final int LIST_WIDTH = 350;
	private final int LIST_HEIGHT = 150;
	private final int INVITE_BUTTON_WIDTH = 350;
	private final int INVITE_BUTTON_HEIGHT = 30;

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
		inviteInput.setMaximumSize(new Dimension(LIST_WIDTH, LIST_HEIGHT));
		inviteInput.setFont(inviteInput.getFont().deriveFont(Font.PLAIN, 17));
		inviteButton = new JButton("Uitnodigen");
		inviteButton.addActionListener((new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = inviteInput.getSelectedValue();
				if (!isAlreadyInvited(name)) {
					invitedPlayers.add(name);
					UpdateInvitedPlayers();
				}
			}
		}));
		inviteButton.setMaximumSize(new Dimension(INVITE_BUTTON_WIDTH, INVITE_BUTTON_HEIGHT));

		JLabel header = new JLabel("Nieuwe Game Aanmaken");
		header.setFont(new Font(header.getFont().getFontName(), Font.PLAIN, 20));
		header.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(header);
		this.add(new InvitePlayerPanel());
		this.add(invitedPlayersPanel);
		this.setVisible(true);

		UpdateInvitedPlayers();
	}

	private boolean isAlreadyInvited(String name) {

		for (String s : invitedPlayers) {
			if (s.equals(name)) {
				return true;
			}
		}
		return false;
	}

	public void UpdateInvitedPlayers() {
		this.remove(invitedPlayersPanel);
		invitedPlayersPanel = new InvitedPlayersPanel();
		this.add(invitedPlayersPanel);
		revalidate();
	}

	public class InvitePlayerPanel extends JPanel {

		private final int SCROLLPANE_WIDTH = 100;
		private final int SCROLLPANE_HEIGHT = 200;
		private final int SCROLLPANE_INCREMENT = 20;

		private JScrollPane scrollPane;

		public InvitePlayerPanel() {
			scrollPane = new JScrollPane(inviteInput, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.getHorizontalScrollBar().setUnitIncrement(SCROLLPANE_INCREMENT);
			scrollPane.setPreferredSize(new Dimension(SCROLLPANE_WIDTH, SCROLLPANE_HEIGHT));
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
			this.add(scrollPane);
			inviteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.add(Box.createVerticalGlue());
			this.add(inviteButton);
		}
	}

	public class InvitedPlayersPanel extends JPanel {
		public InvitedPlayersPanel() {
			this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			removeButtonsList.clear();
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

	public Object getBoardChoice() {
		return boardChoice.getSelectedItem();
	}

	public JButton getCreateGameButton() {
		return createGameButton;
	}

	public ArrayList<String> getInvitedPlayers() {
		return invitedPlayers;
	}

}