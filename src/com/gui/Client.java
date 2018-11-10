package com.gui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.Client.EditClient;
import com.Client.NewClient;
import com.Client.SearchClient;
import com.darkprograms.speech.microphone.Microphone;
import com.darkprograms.speech.recognizer.GSpeechDuplex;
import com.darkprograms.speech.recognizer.GSpeechResponseListener;
import com.darkprograms.speech.recognizer.GoogleResponse;

import net.sourceforge.javaflacencoder.FLACFileWriter;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Client {

	public JFrame frame;
	private Thread thread;
	private Microphone mic;
	private GSpeechDuplex duplex;
	public String output = "";

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public Client() {
		initialize();
	}

	public static void main(String[] args) {
		Client client = new Client();
		client.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	void initialize() {
		frame = new JFrame();
		frame.setLocation(250, 150);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		mic = new Microphone(FLACFileWriter.FLAC);
		// Don't use the below google api key , make your own !!! :)
		duplex = new GSpeechDuplex("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");

		duplex.setLanguage("en");
		frame.getContentPane().setLayout(null);
		// frame.setUndecorated(true);
		JButton bt1 = new JButton("");
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mic.close();
				duplex.stopSpeechRecognition();
				Dashboard dashboard = new Dashboard();
				dashboard.frame.setVisible(true);
				mic.close();
				duplex.stopSpeechRecognition();

				frame.dispose();
			}
		});
		bt1.setContentAreaFilled(false);
		bt1.setFocusPainted(false);
		bt1.setBorderPainted(false);
		bt1.setBorder(null);
		bt1.setOpaque(true);
		bt1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// bt1.setIcon(new ImageIcon(Client.class.getResource("home1.jpg")));

				bt1.setIcon(new ImageIcon("D:\\my project\\home\\home1.jpg"));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// bt1.setIcon(new ImageIcon(Client.class.getResource("home.jpg")));

				bt1.setIcon(new ImageIcon("D:\\my project\\home\\home.jpg"));
			}

		});

		JButton newclient = new JButton("");
		newclient.setBorder(null);
		newclient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mic.close();
				duplex.stopSpeechRecognition();
				NewClient nc = new NewClient();
				nc.frame.setVisible(true);

				frame.dispose();
			}
		});

		newclient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				newclient.setIcon(new ImageIcon(Client.class.getResource("new client.png")));

				// newclient.setIcon(new ImageIcon("D:\\my project\\home\\new client.png"));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				bt1.setIcon(new ImageIcon(Client.class.getResource("new client1.png")));

				// newclient.setIcon(new ImageIcon("D:\\my project\\home\\new client1.png"));
			}
		});

		JButton editclient = new JButton("");
		editclient.setBorder(null);
		editclient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mic.close();
				duplex.stopSpeechRecognition();
				EditClient ec = new EditClient();
				ec.frame.setVisible(true);
				mic.close();
				duplex.stopSpeechRecognition();

				frame.dispose();
			}
		});
		editclient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				editclient.setIcon(new ImageIcon(Client.class.getResource("editclient.png")));

				// editclient.setIcon(new ImageIcon("D:\\my
				// project\\home\\editclient.png"));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				editclient.setIcon(new ImageIcon(Client.class.getResource("editclient1.png")));

				// editclient.setIcon(new ImageIcon("D:\\my
				// project\\home\\editclient1.png"));
			}
		});

		JButton searchclient = new JButton("");
		searchclient.setBorder(null);
		searchclient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mic.close();
				duplex.stopSpeechRecognition();
				SearchClient sc = new SearchClient();
				sc.frame.setVisible(true);
				mic.close();
				duplex.stopSpeechRecognition();

				frame.dispose();
			}
		});

		searchclient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				searchclient.setIcon(new ImageIcon(Client.class.getResource("view-1.png")));

				// searchclient.setIcon(new ImageIcon("D:\\my project\\home\\view-1.png"));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				searchclient.setIcon(new ImageIcon(Client.class.getResource("viewe-1.png")));

				// searchclient.setIcon(new ImageIcon("D:\\my project\\home\\viewe-1.png"));
			}
		});
		searchclient.setIcon(new ImageIcon(Client.class.getResource("viewe-1.png")));

		// searchclient.setIcon(new ImageIcon("D:\\my project\\home\\viewe-1.png"));
		searchclient.setBounds(157, 294, 173, 157);
		frame.getContentPane().add(searchclient);
		editclient.setLocation(278, 113);
		editclient.setIcon(new ImageIcon(Client.class.getResource("editclient1.png")));

		// editclient.setIcon(new ImageIcon("D:\\my
		// project\\home\\editclient1.png"));
		editclient.setSize(162, 142);
		frame.getContentPane().add(editclient);

		newclient.setIcon(new ImageIcon(Client.class.getResource("new client1.png")));

		// newclient.setIcon(new ImageIcon("D:\\my project\\home\\new client1.png"));
		newclient.setBounds(66, 130, 140, 125);
		frame.getContentPane().add(newclient);

		bt1.setIcon(new ImageIcon(Client.class.getResource("home.jpg")));

		// bt1.setIcon(new ImageIcon("D:\\my project\\home\\home.jpg"));
		bt1.setLocation(10, 11);
		bt1.setSize(100, 100);
		frame.getContentPane().add(bt1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setLocation(0, 0);
		lblNewLabel.setIcon(new ImageIcon(Client.class.getResource("Untitled1.jpg")));

		lblNewLabel.setIcon(new ImageIcon("D:\\my project\\Untitled1.jpg"));
		lblNewLabel.setSize(500, 500);
		frame.getContentPane().add(lblNewLabel);

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
				case "newclient":
					System.out.println("new client client");
					mic.close();
					duplex.stopSpeechRecognition();
					NewClient c = new NewClient();
					c.frame.setVisible(true);
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
				case "new client":
					mic.close();
					duplex.stopSpeechRecognition();
					NewClient order = new NewClient();
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
				case "searchclient":
					mic.close();
					duplex.stopSpeechRecognition();
					SearchClient emp = new SearchClient();
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
				case "view client":
					mic.close();
					duplex.stopSpeechRecognition();
					SearchClient credit = new SearchClient();
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
				case "edit client":
					mic.close();
					duplex.stopSpeechRecognition();
					EditClient view = new EditClient();
					view.frame.setVisible(true);

					frame.dispose();
					try {
						thread.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "editclient":
					mic.close();
					duplex.stopSpeechRecognition();
					EditClient design = new EditClient();
					design.frame.setVisible(true);

					frame.dispose();
					try {
						thread.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "back":
					mic.close();
					duplex.stopSpeechRecognition();
					Dashboard manageAccount = new Dashboard();
					manageAccount.frame.setVisible(true);

					frame.dispose();
					try {
						thread.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "dasboard":
					mic.close();
					duplex.stopSpeechRecognition();
					Dashboard manageAccount1 = new Dashboard();
					manageAccount1.frame.setVisible(true);

					frame.dispose();
					try {
						thread.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				
				default:
					System.out.println("default");
					break;
				}

			}

		});

	}

}
