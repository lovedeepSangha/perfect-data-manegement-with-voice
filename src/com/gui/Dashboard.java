package com.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

import com.darkprograms.speech.microphone.Microphone;
import com.darkprograms.speech.recognizer.GSpeechDuplex;
import com.darkprograms.speech.recognizer.GSpeechResponseListener;
import com.darkprograms.speech.recognizer.GoogleResponse;

import net.sourceforge.javaflacencoder.FLACFileWriter;

public class Dashboard {

	JFrame frame;
	private Thread thread;
	private Microphone mic;
	private GSpeechDuplex duplex;
	public String output = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Dashboard dashboard = new Dashboard();
		dashboard.frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public Dashboard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mic = new Microphone(FLACFileWriter.FLAC);
		// Don't use the below google api key , make your own !!! :)
		duplex = new GSpeechDuplex("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");

		duplex.setLanguage("en");

		frame.getContentPane().setLayout(null);

		JButton bt3 = new JButton("");
		bt3.setContentAreaFilled(false);
		bt3.setFocusPainted(false);
		bt3.setBorderPainted(false);
		bt3.setBorder(null);
		bt3.setOpaque(true);

		bt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mic.close();
				duplex.stopSpeechRecognition();
				Client c = new Client();
				c.frame.setVisible(true);
				mic.close();
				duplex.stopSpeechRecognition();
				frame.dispose();
			}
		});
		bt3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				bt3.setIcon(new ImageIcon(Dashboard.class.getResource("client.png")));
				// bt3.setIcon(new ImageIcon("D:\\my project\\home\\client.png"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				bt3.setIcon(new ImageIcon(Dashboard.class.getResource("clients.png")));
				// bt3.setIcon(new ImageIcon("D:\\my project\\home\\clients.png"));
			}

		});

		JButton bt4 = new JButton("");
		bt4.setContentAreaFilled(false);
		bt4.setFocusPainted(false);
		bt4.setBorderPainted(false);
		bt4.setBorder(null);
		bt4.setOpaque(true);
		bt4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mic.close();
				duplex.stopSpeechRecognition();
				Welcome w = null;
				try {
					w = new Welcome();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				w.frame.setVisible(true);
				frame.dispose();
				mic.close();
				duplex.stopSpeechRecognition();
				frame.dispose();
			}
		});
		bt4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				bt4.setIcon(new ImageIcon(Dashboard.class.getResource("logout effect.jpg")));
				// bt4.setIcon(new ImageIcon("D:\\my project\\home\\logout effect.jpg"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				bt4.setIcon(new ImageIcon(Dashboard.class.getResource("logout.jpg")));
				// bt4.setIcon(new ImageIcon("D:\\my project\\home\\logout.jpg"));
			}

		});

		JButton bt6 = new JButton("");
		bt6.setContentAreaFilled(false);
		bt6.setFocusPainted(false);
		bt6.setBorderPainted(false);
		bt6.setBorder(null);
		bt6.setOpaque(true);
		bt6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mic.close();
				duplex.stopSpeechRecognition();
				Credits cr = new Credits();
				cr.frame.setVisible(true);
				mic.close();
				duplex.stopSpeechRecognition();
				frame.dispose();

			}
		});
		bt6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				bt6.setIcon(new ImageIcon(Dashboard.class.getResource("credits-12.jpg")));
				// bt6.setIcon(new ImageIcon("D:\\my project\\home\\credits-12.jpg"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				bt6.setIcon(new ImageIcon(Dashboard.class.getResource("creditse-12.jpg")));
				bt6.setIcon(new ImageIcon("D:\\my project\\home\\creditse-12.jpg"));
			}

		});

		JButton bt1 = new JButton("");
		bt1.setContentAreaFilled(false);
		bt1.setFocusPainted(false);
		bt1.setBorderPainted(false);
		bt1.setBorder(null);
		bt1.setOpaque(true);
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mic.close();
				duplex.stopSpeechRecognition();
				Order o = new Order();
				o.frame.setVisible(true);
				mic.close();
				duplex.stopSpeechRecognition();
				frame.dispose();
			}
		});
		bt1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				bt1.setIcon(new ImageIcon(Dashboard.class.getResource("order.png")));
				// bt1.setIcon(new ImageIcon("D:\\my project\\home\\order.png"));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				bt1.setIcon(new ImageIcon(Dashboard.class.getResource("orders2.png")));
				// bt1.setIcon(new ImageIcon("D:\\my project\\home\\orders2.png"));
			}

		});

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBorder(null);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mic.close();
				duplex.stopSpeechRecognition();
				Design d = new Design();
				d.frame.setVisible(true);
				mic.close();
				duplex.stopSpeechRecognition();
				frame.dispose();
			}
		});
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0)

			{
				btnNewButton_2.setIcon(new ImageIcon(Dashboard.class.getResource("design.png")));
				// btnNewButton_2.setIcon(new ImageIcon("D:\\my project\\home\\design.png"));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btnNewButton_2.setIcon(new ImageIcon(Dashboard.class.getResource("designs.png")));
				// btnNewButton_2.setIcon(new ImageIcon("D:\\my project\\home\\designs.png"));
			}
		});

		JButton btnNewButton = new JButton("");
		btnNewButton.setBorder(null);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNewButton.setIcon(new ImageIcon(Dashboard.class.getResource("ac setting10.jpg")));
				// btnNewButton.setIcon(new ImageIcon("D:\\my project\\home\\ac
				// setting10.jpg"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setIcon(new ImageIcon(Dashboard.class.getResource("ac settings.jpg")));
				// btnNewButton.setIcon(new ImageIcon("D:\\my project\\home\\ac settings.jpg"));
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mic.close();
				duplex.stopSpeechRecognition();
				ManageAccount ma = new ManageAccount();
				ma.frame.setVisible(true);
				mic.close();
				duplex.stopSpeechRecognition();
				frame.dispose();
			}
		});

		JLabel lblNewLabel_7 = new JLabel("");

		// lblNewLabel_7.setIcon(new ImageIcon(".\\images\\home\\thread printing.png"));

		lblNewLabel_7.setIcon(new ImageIcon(Dashboard.class.getResource("thread printing.png")));

		lblNewLabel_7.setLocation(0, 0);
		lblNewLabel_7.setSize(1296, 144);
		frame.getContentPane().add(lblNewLabel_7);
		btnNewButton.setIcon(new ImageIcon(Dashboard.class.getResource("ac settings.jpg")));
		// btnNewButton.setIcon(new ImageIcon("D:\\my project\\home\\ac settings.jpg"));
		btnNewButton.setBounds(955, 504, 141, 150);
		frame.getContentPane().add(btnNewButton);
		btnNewButton_2.setLocation(672, 504);
		btnNewButton_2.setIcon(new ImageIcon(Dashboard.class.getResource("designs.png")));
		// btnNewButton_2.setIcon(new ImageIcon("D:\\my project\\home\\designs.png"));
		btnNewButton_2.setSize(150, 150);
		frame.getContentPane().add(btnNewButton_2);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mic.close();
				duplex.stopSpeechRecognition();
				Employee emp = new Employee();
				emp.frame.setVisible(true);
				mic.close();
				duplex.stopSpeechRecognition();
				frame.dispose();
			}
		});
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNewButton_1.setIcon(new ImageIcon(Dashboard.class.getResource("employee.jpg")));
				// btnNewButton_1.setIcon(new ImageIcon("D:\\my project\\home\\employee.jpg"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_1.setIcon(new ImageIcon(Dashboard.class.getResource("employee1.jpg")));
				// btnNewButton_1.setIcon(new ImageIcon("D:\\my project\\home\\employee1.jpg"));
			}
		});
		btnNewButton_1.setLocation(659, 263);
		btnNewButton_1.setIcon(new ImageIcon(Dashboard.class.getResource("employee1.jpg")));
		// btnNewButton_1.setIcon(new ImageIcon("D:\\my project\\home\\employee1.jpg"));
		btnNewButton_1.setSize(141, 179);
		frame.getContentPane().add(btnNewButton_1);

		bt1.setIcon(new ImageIcon(Dashboard.class.getResource("orders2.png")));
		// bt1.setIcon(new ImageIcon("D:\\my project\\home\\orders2.png"));
		bt1.setLocation(405, 306);
		bt1.setSize(99, 141);
		frame.getContentPane().add(bt1);
		bt6.setIcon(new ImageIcon(Dashboard.class.getResource("creditse-12.jpg")));
		// bt6.setIcon(new ImageIcon("D:\\my project\\home\\creditse-12.jpg"));
		bt6.setLocation(955, 293);
		bt6.setSize(126, 158);
		frame.getContentPane().add(bt6);

		JButton bt5 = new JButton("");
		bt5.setContentAreaFilled(false);
		bt5.setFocusPainted(false);
		bt5.setBorderPainted(false);
		bt5.setBorder(null);
		bt5.setOpaque(true);
		bt5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mic.close();
				duplex.stopSpeechRecognition();
				View v = new View();
				v.setVisible(true);
				mic.close();
				duplex.stopSpeechRecognition();
				frame.dispose();
			}
		});
		bt5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				bt5.setIcon(new ImageIcon(Dashboard.class.getResource("View all.png")));
				// bt5.setIcon(new ImageIcon("D:\\my project\\home\\View all.png"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				bt5.setIcon(new ImageIcon(Dashboard.class.getResource("View alls.png")));
				// bt5.setIcon(new ImageIcon("D:\\my project\\home\\View alls.png"));
			}

		});
		bt5.setIcon(new ImageIcon(Dashboard.class.getResource("View alls.png")));
		// bt5.setIcon(new ImageIcon("D:\\my project\\home\\View alls.png"));
		bt5.setLocation(375, 515);
		bt5.setSize(150, 150);
		frame.getContentPane().add(bt5);
		bt4.setIcon(new ImageIcon(Dashboard.class.getResource("logout.jpg")));
		// bt4.setIcon(new ImageIcon("D:\\my project\\home\\logout.jpg"));
		bt4.setLocation(106, 535);
		bt4.setSize(126, 133);
		frame.getContentPane().add(bt4);

		bt3.setIcon(new ImageIcon(Dashboard.class.getResource("clients.png")));
		// bt3.setIcon(new ImageIcon("D:\\my project\\home\\clients.png"));
		bt3.setLocation(106, 293);
		bt3.setSize(150, 150);
		frame.getContentPane().add(bt3);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setRequestFocusEnabled(false);
		lblNewLabel.setBorder(null);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setForeground(Color.WHITE);

		lblNewLabel.setLocation(0, 0);
		lblNewLabel.setIcon(new ImageIcon(Dashboard.class.getResource("Untitled 2.jpg")));
		// lblNewLabel.setIcon(new ImageIcon("D:\\my project\\Untitled 2.jpg"));
		lblNewLabel.setSize(1280, 720);
		frame.getContentPane().add(lblNewLabel);

		// #####################################################voice
		// #################################################recorginitation######

		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					duplex.recognize(mic.getTargetDataLine(), mic.getAudioFormat());
					// Do you task
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		thread.start();

		duplex.addResponseListener(new GSpeechResponseListener() {

			@Override
			public void onResponse(GoogleResponse gr) {

				System.out.println(gr.getResponse());

				switch (gr.getResponse().trim()) {
				case "client":
					mic.close();
					duplex.stopSpeechRecognition();
					System.out.println("client");
					Client c = new Client();
					c.frame.setVisible(true);
					
					frame.dispose();
					try {
						thread.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "order":
					mic.close();
					duplex.stopSpeechRecognition();
					Order order = new Order();
					order.frame.setVisible(true);
					mic.close();
					duplex.stopSpeechRecognition();
					frame.dispose();
					try {
						thread.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "employee":
					mic.close();
					duplex.stopSpeechRecognition();
					Employee emp = new Employee();
					emp.frame.setVisible(true);
					mic.close();
					duplex.stopSpeechRecognition();
					frame.dispose();
					try {
						thread.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "credit":
					mic.close();
					duplex.stopSpeechRecognition();
					Credits credit = new Credits();
					credit.frame.setVisible(true);
					mic.close();
					duplex.stopSpeechRecognition();
					frame.dispose();
					try {
						thread.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "view":
					mic.close();
					duplex.stopSpeechRecognition();
					View view = new View();
					view.setVisible(true);
					mic.close();
					duplex.stopSpeechRecognition();
					frame.dispose();
					try {
						thread.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "design":
					mic.close();
					duplex.stopSpeechRecognition();
					Design design = new Design();
					design.frame.setVisible(true);
					mic.close();
					duplex.stopSpeechRecognition();
					frame.dispose();
					try {
						thread.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "settings":
					mic.close();
					duplex.stopSpeechRecognition();
					ManageAccount manageAccount = new ManageAccount();
					manageAccount.frame.setVisible(true);
					mic.close();
					duplex.stopSpeechRecognition();
					frame.dispose();
					try {
						thread.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "logout":
					Welcome welcome;
					try {
						mic.close();
						duplex.stopSpeechRecognition();
						welcome = new Welcome();
						welcome.frame.setVisible(true);

						frame.dispose();

						thread.join();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					break;
				// case 3:
				// dayString = "Wednesday";
				// break;
				// case 4:
				// dayString = "Thursday";
				// break;
				// case 5:
				// dayString = "Friday";
				// break;
				// case 6:
				// dayString = "Saturday";
				// break;
				// case 7:
				// dayString = "Sunday";
				// break;
				default:
					System.out.println("default");
					break;
				}

				// if (gr.getResponse().trim().equals("login")) {
				//
				// try {
				// thread.join();
				// } catch (InterruptedException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				// }
			}

		});

	}
}
