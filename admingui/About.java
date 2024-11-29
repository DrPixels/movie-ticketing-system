package admingui;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;

public class About extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private JLabel systemName;
    private JLabel systemDefinition;
    private JTextArea systemDescription;
    private JLabel developersLabel;
    private JLabel arevaloPic;
    private JLabel arevaloName;
    private JLabel bithaoPic;
    private JLabel bithaoName;
    private JLabel ghazalPic;
    private JLabel ghazalName;
    private JLabel salvadorPic;
    private JLabel salvadorName;


    public About() {
    	setLayout(null);
    	
    	systemName = new JLabel("CineBook");
    	systemName.setHorizontalAlignment(SwingConstants.CENTER);
    	systemName.setFont(new Font("Segoe UI", Font.BOLD, 50));
    	systemName.setBounds(285, 30, 370, 70);
    	    	
    	systemDefinition = new JLabel("Movie Ticketing System");
    	systemDefinition.setHorizontalAlignment(SwingConstants.CENTER);
    	systemDefinition.setFont(new Font("Segoe UI", Font.ITALIC, 18));
    	systemDefinition.setBounds(357, 110, 225, 30); 	
    	
    	systemDescription = new JTextArea();
    	systemDescription.setOpaque(false);
    	systemDescription.setWrapStyleWord(true);
    	systemDescription.setLineWrap(true);
    	systemDescription.setText("CineBook is a user-friendly movie ticketing system tailored for theater administrators and staff, such as cashiers. It streamlines ticket sales and management by providing tools to handle reservations, process payments, assign seats, and monitor available showtimes. Designed for efficiency, CineBook ensures that staff can quickly assist customers, reduce wait times, and maintain accurate records of transactions. ");
    	systemDescription.setFont(new Font("Monospaced", Font.PLAIN, 13));
    	systemDescription.setEditable(false);
    	systemDescription.setBounds(25, 190, 950, 100);	
    	
    	developersLabel = new JLabel("Developers");
    	developersLabel.setHorizontalAlignment(SwingConstants.LEFT);
    	developersLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
    	developersLabel.setBounds(25, 310, 150, 50);
    	
    	arevaloPic = new JLabel();
    	arevaloPic.setBorder(new LineBorder(new Color(0, 0, 0)));
    	arevaloPic.setBounds(110, 370, 120, 120);
    	
    	arevaloName = new JLabel("Arevalo, Samantha Nicole");
    	arevaloName.setHorizontalAlignment(SwingConstants.CENTER);
    	arevaloName.setFont(new Font("Segoe UI", Font.BOLD, 15));
    	arevaloName.setBounds(70, 500, 210, 30);
    	
    	bithaoPic = new JLabel();
    	bithaoPic.setBorder(new LineBorder(new Color(0, 0, 0)));
    	bithaoPic.setBounds(320, 370, 120, 120);
    	
    	bithaoName = new JLabel("Bithao, Mark");
    	bithaoName.setHorizontalAlignment(SwingConstants.CENTER);
    	bithaoName.setFont(new Font("Segoe UI", Font.BOLD, 15));
    	bithaoName.setBounds(305, 500, 150, 30);
    	
    	ghazalPic = new JLabel();
    	ghazalPic.setBorder(new LineBorder(new Color(0, 0, 0)));
    	ghazalPic.setBounds(510, 370, 120, 120);
    	
    	ghazalName = new JLabel("Ghazal, Nabila Maxime");
    	ghazalName.setHorizontalAlignment(SwingConstants.CENTER);
    	ghazalName.setFont(new Font("Segoe UI", Font.BOLD, 15));
    	ghazalName.setBounds(470, 500, 200, 30); 	
    	
    	salvadorPic = new JLabel();
    	salvadorPic.setBorder(new LineBorder(new Color(0, 0, 0)));
    	salvadorPic.setBounds(720, 370, 120, 120); 	
    	
    	salvadorName = new JLabel("Salvador, Rhem Giou ");
    	salvadorName.setHorizontalAlignment(SwingConstants.CENTER);
    	salvadorName.setFont(new Font("Segoe UI", Font.BOLD, 15));
    	salvadorName.setBounds(680, 500, 200, 30);
    	
    	add(systemName);
    	add(systemDefinition);
    	add(systemDescription);
    	add(developersLabel);
    	add(arevaloPic);
    	add(arevaloName);
    	add(bithaoPic);
    	add(bithaoName);
    	add(ghazalPic);
    	add(ghazalName);
    	add(salvadorPic);
    	add(salvadorName);

    }
}
