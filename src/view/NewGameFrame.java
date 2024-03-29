package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class NewGameFrame extends JFrame {

	private JButton inviteButton;
	private JList<String> inviteInput;
	private ArrayList<String> availablePlayers;
	private ArrayList<String> invitedPlayers;
	private ArrayList<JButton> removeButtonsList;
	private JPanel invitedPlayersPanel;
	private JButton createGameButton;
	private JComboBox<String> boardChoice;

	public NewGameFrame(ArrayList<String> availableNames) {
		availablePlayers = availableNames;
		NewGamePanel panel = new NewGamePanel();
		this.add(panel);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}

	public void PackFrame() {
		this.revalidate();
		this.pack();
	}

	public class NewGamePanel extends JPanel {

		private final int LIST_WIDTH = 350;
		private final int LIST_HEIGHT = 150;
		private final int INVITE_BUTTON_WIDTH = 350;
		private final int INVITE_BUTTON_HEIGHT = 30;

		public NewGamePanel() {

			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			createGameButton = new JButton("Spel aanmaken!");
			removeButtonsList = new ArrayList<>();
			boardChoice = new JComboBox<String>(new String[] { "Normaal", "Random" });
			invitedPlayers = new ArrayList<String>();
			invitedPlayersPanel = new InvitedPlayersPanel();
			inviteInput = new JList<String>();
			inviteInput.setMinimumSize(new Dimension(LIST_WIDTH, LIST_HEIGHT));
			inviteInput.setFont(inviteInput.getFont().deriveFont(Font.PLAIN, 17));
			inviteButton = new JButton("Uitnodigen");

			inviteButton.setMaximumSize(new Dimension(INVITE_BUTTON_WIDTH, INVITE_BUTTON_HEIGHT));
			inviteButton.addActionListener((new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					invitedPlayers.add(inviteInput.getSelectedValue());
					UpdateInvitedPlayers();
					PackFrame();
					if (invitedPlayers.size() == 3) {
						inviteButton.setEnabled(false);
					}
				}
			}));
			JLabel header = new JLabel("Nieuw Spel Aanmaken");
			header.setFont(new Font(header.getFont().getFontName(), Font.PLAIN, 20));
			header.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.add(header);
			this.add(new InvitePlayerPanel());
			this.add(invitedPlayersPanel);
		}

		public void UpdateInvitedPlayers() {
			this.remove(invitedPlayersPanel);
			invitedPlayersPanel = new InvitedPlayersPanel();
			this.add(invitedPlayersPanel);
			revalidate();
			PackFrame();
		}

		public class InvitePlayerPanel extends JPanel {
			
			private final int PANEL_MINIMUM_WIDTH = 350;
			private final int PANEL_MINIMUM_HEIGHT = 250;
			private final int SCROLLPANE_WIDTH = 100;
			private final int SCROLLPANE_HEIGHT = 200;
			private final int SCROLLPANE_INCREMENT = 20;
			
			private JScrollPane scrollPane;

			public InvitePlayerPanel() {
				this.setMinimumSize(new Dimension(PANEL_MINIMUM_WIDTH, PANEL_MINIMUM_HEIGHT));

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
				this.add(inviteButton);
			}
		}

		public class InvitedPlayersPanel extends JPanel {
			public InvitedPlayersPanel() {
				removeButtonsList.clear();
				this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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
							if (invitedPlayers.size() < 3) {
								inviteButton.setEnabled(true);
							}
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
}
