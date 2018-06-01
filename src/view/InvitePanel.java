package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Catan;
import model.Player;

@SuppressWarnings("serial")
public class InvitePanel extends JPanel {

	private final int PANEL_WIDTH = 600;
	private final int PANEL_HEIGHT = 500;
	private final int BUTTON_WIDTH = 195;
	private final int BUTTON_HEIGHT = 50;
	private final int LIST_WIDTH = 600;
	private final int LIST_HEIGHT = 400;

	private Color backgroundColor = new Color(240, 226, 223);
	private Color buttonColor = new Color(189, 133, 100);

	private ArrayList<Catan> invitedList;
	private ArrayList<Catan> ableToInviteList;
	private JButton acceptButton, declineButton, inviteButton, refreshButton, returnButton;
	private JList<String> invitedJList;

	private int invitedListSelectedIndex = -1;

	public InvitePanel(ArrayList<Catan> invitedList) {
		this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		this.invitedList = new ArrayList<>(invitedList);
		invitedJList = new JList<String>();

		this.add(new TextPanel());
		this.add(new ListOfInvitesPanel());
		this.add(new ButtonPanel());
	}

	public class TextPanel extends JPanel {
		public TextPanel() {
			Font bold = new Font("bold", Font.BOLD, 20);
			this.setLayout(new BorderLayout());
			this.setBackground(backgroundColor);
			JLabel receiveInviteLabel = new JLabel("Mijn uitnodigingen");
			receiveInviteLabel.setFont(bold);
			this.add(receiveInviteLabel);
		}
	}

	public class ButtonPanel extends JPanel {
		public ButtonPanel() {
			this.setBackground(backgroundColor);
			acceptButton = new JButton("Accepteren");
			declineButton = new JButton("Weigeren");
			refreshButton = new JButton("Verversen");

			JButton[] buttonArray = { acceptButton, declineButton, refreshButton };
			for (JButton button : buttonArray) {
				button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
				button.setBackground(buttonColor);
				button.setForeground(Color.WHITE);
				this.add(button);
			}
		}
	}

	public class ListOfInvitesPanel extends JPanel {
		public ListOfInvitesPanel() {
			this.setBackground(backgroundColor);
			JList<String> list = null;
			ArrayList<Catan> gameList = null;
			list = invitedJList;
			gameList = invitedList;
			DefaultListModel<String> model = new DefaultListModel<>();
			
			for (Catan game : gameList) {

				Player challenger = null;
				for (Player p : game.getPlayers()) {
					if (p.getPlayStatus().toString().toLowerCase().equals("uitdager")) {
						challenger = p;
					}
				}
				String line = String.format("Game ID: %d Uitdager: %s Status: %s ", game.getIdGame(),
						challenger.getUsername(), game.getSelfPlayer().getPlayStatus());
				model.addElement(line);
			}

			list.setModel(model);
			list.setPreferredSize(new Dimension(LIST_WIDTH, LIST_HEIGHT));
			JScrollPane s = new JScrollPane(list);
			s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			JScrollPane scrollableList = new JScrollPane(list); // TODO make use of it!
			list.setBackground(Color.WHITE);
			list.setForeground(Color.DARK_GRAY);

			list.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					JList<?> list = (JList<?>) evt.getSource();
					if (evt.getClickCount() == 1) {
						invitedListSelectedIndex = list.locationToIndex(evt.getPoint());
					}
				}
			});
			this.add(list);
		}
	}

	public ArrayList<Catan> getInvitedList() {
		return invitedList;
	}

	public ArrayList<Catan> getAbleToInviteList() {
		return ableToInviteList;
	}

	public JButton getAcceptButton() {
		return acceptButton;
	}

	public JButton getDeclineButton() {
		return declineButton;
	}

	public JButton getInviteButton() {
		return inviteButton;
	}

	public JButton getRefreshButton() {
		return refreshButton;
	}

	public int getInvitedListSelectedIndex() {
		return invitedListSelectedIndex;
	}

	public JButton getReturnButton() {
		return returnButton;
	}
}
