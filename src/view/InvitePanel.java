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

public class InvitePanel extends JPanel {
	private Color bgColor = makeTransparent(Color.GREEN, 60);

	// We beginnen met het maken van de variablen die gevuld moeten worden en
	// waaruit
	// we vervolgens de data kunnen pakken. In dit geval moeten we twee
	// verschillende lijsten
	// met games tonen. Dus hebben we twee lijsten met Catan objecten nodig
	private ArrayList<Catan> invitedList; // De lijst met games waarvoor je uitgenodigd bent.
	private ArrayList<Catan> ableToInviteList; // De lijst met games waar je mensen voor wilt uitnodigen
	// Alle buttons waar buiten de view actionlisteners op gezet moeten worden
	private JButton acceptButton, declineButton, inviteButton, refreshButton;
	// Lists die je met data gaat vullen dmv de ArrayLists met Catan objecten
	// hierboven.
	private JList invitedJList, ableToInviteJList;

	// We hebben nu alle objecten die we nodig hebben dus kunnen we een constructor
	// gaan maken
	// Die direct alle data laat zien
	private int invitedListSelectedIndex = -1;
	private int ableToInviteListSelectedIndex = -1;

	public InvitePanel(ArrayList<Catan> invitedList, ArrayList<Catan> ableToInviteList) {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		// Ik moet nog een goed aantal dingen veranderen dus na t eten ben ik hier wss
		// Nog een uurtje mee bezig. Zal ik laten weten wanneer ik weer begin of vind
		// Je t wel mooi geweest?
		this.invitedList = new ArrayList<>(invitedList);
		this.ableToInviteList = new ArrayList<>(ableToInviteList);
		invitedJList = new JList();
		ableToInviteJList = new JList();

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
			this.setBackground(bgColor);
			JLabel receiveInviteLabel = new JLabel("Invites ontvangen");
			receiveInviteLabel.setFont(bold);
			this.add(receiveInviteLabel);
			JLabel sendInviteLabel = new JLabel("Invites sturen");
			sendInviteLabel.setFont(bold);
			this.add(sendInviteLabel, BorderLayout.EAST);
		}
	}

	public class ButtonPanel extends JPanel {
		public ButtonPanel() {
			this.setBackground(bgColor);
			acceptButton = new JButton("Accept");
			declineButton = new JButton("Decline");
			inviteButton = new JButton("Invite");
			refreshButton = new JButton("Refresh");
			JButton[] buttonArray = { acceptButton, declineButton, inviteButton, refreshButton };
			for (JButton btn : buttonArray) {
				btn.setPreferredSize(new Dimension(200, 50));
				btn.setBackground(Color.green);
				btn.setForeground(Color.DARK_GRAY);
				this.add(btn);
			}
		}
	}

	public class ListOfInvitesPanel extends JPanel {
		public ListOfInvitesPanel() {
			// Hier moet dat gebeuren. Je wilt namelijk bij t aanmaken van je JPanel
			// De data in die panel krijgen en in dit geval dus een JList
			this.setBackground(bgColor);
			for (int i = 0; i < 2; i++) {
				JList list = null;
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
					// Je kan namelijk kijken dmv de index
					Player challenger = null;
					for (Player p : game.getPlayers()) {
						if (p.getPlayStatus().equals("uitdager")) {
							challenger = p;
						}
					}
					String line = String.format("Game ID: %d Uitdager: %s Status: %s ", game.getIdGame(),
							challenger.getUsername(), game.getSelfPlayer().getPlayStatus());
					model.addElement(line);
				}

				list.setModel(model);
				list.setPreferredSize(new Dimension(400, 400));
				JScrollPane s = new JScrollPane(list);
				s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				JScrollPane scrollableList = new JScrollPane(list);
				list.setBackground(Color.WHITE);
				list.setForeground(Color.DARK_GRAY);
				int idx = i;
				list.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						JList list = (JList) evt.getSource();
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

}
