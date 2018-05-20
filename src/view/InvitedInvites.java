package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.jws.WebParam.Mode;
import javax.swing.*;

@SuppressWarnings("serial")
public class InvitedInvites extends JPanel {
	private JPanel listOfInvites = new JPanel();
	private JPanel buttons = new JPanel();
	private JPanel text = new JPanel();
	private String name;
	private String status;
	// <speelstatus> = "uitdager" , "uitgedaagde", "geaccepteerd", "geweigerd",
	// "uitgespeeld", "afgebroken"
	private String idGame;

	private String subject[] = { "Name: " + getName() + " / IdGame: " + getIdGame() + " / Status: " + getStatus() };
	private String subject2[] = { "Name: " + getName() + " / IdGame: " + getIdGame() + " / Status: " + getStatus() };
	DefaultListModel<String> model;
	private Font bold = new Font("bold", Font.BOLD, 20);
	private Color w = makeTransparent(Color.GREEN, 60);
	private JList<String> list2 = new JList<String>(subject2);
	private JList<String> list = new JList<String>(subject);

	public InvitedInvites() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		makeJlist();
		this.add(text);
		this.add(listOfInvites);
		this.add(buttons);
		but();
		makeJListInvitesOntvangen();
		buttons.setBackground(w);
		listOfInvites.setBackground(w);
		makeJListInvitesverzenden();
	}


	public void makeJlist() {
		text.setLayout(new BorderLayout());
		text.setBackground(w);
		JLabel invites = new JLabel("invites ontvangen");
		invites.setFont(bold);
		text.add(invites);
		JLabel invites2 = new JLabel("invites sturen");
		invites2.setFont(bold);
		text.add(invites2, BorderLayout.EAST);
	}

	public void makeJListInvitesOntvangen() {

		list.setPreferredSize(new Dimension(400, 400));
		JScrollPane s = new JScrollPane(list);
		s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		JScrollPane scrollableList = new JScrollPane(list);
		list.setBackground(Color.WHITE);
		list.setForeground(Color.DARK_GRAY);
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 1) {
					setStatus("done");
					int index = list.locationToIndex(evt.getPoint());
					System.out.println("hoi");
				} else if (evt.getClickCount() == 3) {

					// Triple-click detected
					int index = list.locationToIndex(evt.getPoint());
				}
			}
		});
		listOfInvites.add(scrollableList);
	}

	public void makeJListInvitesverzenden() {

		list2.setPreferredSize(new Dimension(400, 400));
		JScrollPane s = new JScrollPane(list2);
		s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		JScrollPane scrollableList = new JScrollPane(list2);
		list2.setBackground(Color.WHITE);
		list2.setForeground(Color.DARK_GRAY);
		list2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 1) {

					int index = list.locationToIndex(evt.getPoint());
					System.out.println("hoi");
				} else if (evt.getClickCount() == 3) {

					// Triple-click detected
					int index = list.locationToIndex(evt.getPoint());
				}
			}
		});
		listOfInvites.add(scrollableList);
	}

	public void but() {
		JButton accept = new JButton("accept");
		JButton decline = new JButton("decline");
		JButton invite = new JButton("invite");
		JButton refresh = new JButton("refresh");
		accept.setPreferredSize(new Dimension(200, 50));
		decline.setPreferredSize(new Dimension(200, 50));
		invite.setPreferredSize(new Dimension(200, 50));
		refresh.setPreferredSize(new Dimension(200, 50));
		accept.setBackground(Color.green);
		decline.setBackground(Color.green);
		invite.setBackground(Color.green);
		refresh.setBackground(Color.green);
		accept.setForeground(Color.DARK_GRAY);
		decline.setForeground(Color.DARK_GRAY);
		invite.setForeground(Color.DARK_GRAY);
		refresh.setForeground(Color.DARK_GRAY);
		buttons.add(accept);
		buttons.add(decline);
		buttons.add(invite);
		buttons.add(refresh);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdGame() {
		return idGame;
	}

	public void setIdGame(String idGame) {
		this.idGame = idGame;
	}

	public String[] getSubject() {
		return subject;
	}

	public void setSubject(String[] subject) {
		this.subject = subject;
	}

	public static Color makeTransparent(Color source, int alpha) {
		return new Color(source.getRed(), source.getGreen(), source.getBlue(), alpha);
	}

	public void accept() {

	}

	public void decline() {

	}

	public void invite() {

	}

	public void refresh() {
		//TODO dus als er iets niet wil doorkomen hierop klikken om het te laten zien 
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void add(String name, String idgame, String status) {


		// "Name: " + name + " / " + "IdGame: " + idgame + " / " + "Status: " + status
	}

}
