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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Catan;
import model.Player;

@SuppressWarnings("serial")
public class InvitePanel extends JPanel {
	
	private final int PANEL_WIDTH = 900;
	private final int PANEL_HEIGHT = 500;
	private final int BUTTON_WIDTH = 200;
	private final int BUTTON_HEIGHT = 50;
	private final int LIST_WIDTH = 400;
	private final int LIST_HEIGHT = 400;
	
	private Color backgroundColor = new Color(240, 226, 223);
	private Color buttonColor = new Color(189, 133, 100);
	
	private ArrayList<Catan> invitedList;
	private ArrayList<Catan> ableToInviteList;
	private JButton acceptButton, declineButton, inviteButton, refreshButton, returnButton;
	private JList<String> invitedJList, ableToInviteJList;
	
	private int invitedListSelectedIndex = -1;
	private int ableToInviteListSelectedIndex = -1;

	public InvitePanel(ArrayList<Catan> invitedList, ArrayList<Catan> ableToInviteList) {
		this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		this.invitedList = new ArrayList<>(invitedList);
		this.ableToInviteList = new ArrayList<>(ableToInviteList);
		invitedJList = new JList<String>();
		ableToInviteJList = new JList<String>();

		this.add(new TextPanel());
		this.add(new ListOfInvitesPanel());
		this.add(new ButtonPanel());
	}

	public static Color makeTransparent(Color source, int alpha) {
		return new Color(source.getRed(), source.getGreen(), source.getBlue(), alpha);
	}

	public class TextPanel extends JPanel {
		public TextPanel() {
			Font bold = new Font("bold", Font.BOLD, 20);
			this.setLayout(new BorderLayout());
			this.setBackground(backgroundColor);
			JLabel receiveInviteLabel = new JLabel("Uitnodigingen ontvangen");
			receiveInviteLabel.setFont(bold);
			this.add(receiveInviteLabel);
			JLabel sendInviteLabel = new JLabel("Uitnodigingen sturen");
			sendInviteLabel.setFont(bold);
			this.add(sendInviteLabel, BorderLayout.EAST);
		}
	}

	public class ButtonPanel extends JPanel {
		public ButtonPanel() {
			this.setBackground(backgroundColor);
			acceptButton = new JButton("Accepteren");
			declineButton = new JButton("Weigeren");
			refreshButton = new JButton("Verversen");
			returnButton = new JButton("Terug");
			
			JButton[] buttonArray = { acceptButton, declineButton, refreshButton, returnButton };
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
			for (int i = 0; i < 2; i++) {
				JList<String> list = null;
				ArrayList<Catan> gameList = null;
				if (i == 0) {
					list = invitedJList;
					gameList = invitedList;
				} else if (i == 1) {
					list = ableToInviteJList;
					gameList = ableToInviteList;
				}
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
				int idx = i;
				list.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						JList<?> list = (JList<?>) evt.getSource();
						if (evt.getClickCount() == 1) {
							int loopIdx = idx;
							if (loopIdx == 0) {
								invitedListSelectedIndex = list.locationToIndex(evt.getPoint());
							} else {
								ableToInviteListSelectedIndex = list.locationToIndex(evt.getPoint());
							}
						}
					}
				});
				this.add(list);
			}
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

	public int getAbleToInviteListSelectedIndex() {
		return ableToInviteListSelectedIndex;
	}
	
	public JButton getReturnButton() {
		return returnButton;
	}
}
