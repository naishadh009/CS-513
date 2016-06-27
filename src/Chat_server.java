import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Chat_server extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private static DataInputStream input;
	private static DataOutputStream output;
	private static ServerSocket socket;

	private JPanel contentPane;
	private JTextField textField;
	private static JTextArea textArea;
	private JButton btnSend;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Chat_server().setVisible(true);
			}
		});
		try {
			int port = 9090;
			socket = new ServerSocket(port);
			// Accept the port
			System.out.println(socket + "\nServer Started");
			Socket ss = socket.accept();

			input = new DataInputStream(ss.getInputStream());
			output = new DataOutputStream(ss.getOutputStream());

			String inMessage = "";
			while (inMessage != null) {
				inMessage = input.readUTF();

				textArea.setText(textArea.getText() + "\n" + inMessage.toString());
			}

			// close server
			input.close();
			output.close();
			ss.close();
			System.exit(0);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Server does not connect...");

		}
	}

	private void intiateComponents() {
		// TODO Auto-generated method stub

	}

	/**
	 * Create the frame.
	 */
	public Chat_server() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textArea = new JTextArea();
		textArea.setBounds(10, 11, 414, 178);
		contentPane.add(textArea);

		textField = new JTextField();
		textField.setBounds(10, 200, 315, 50);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setColumns(10);

		btnSend = new JButton("send");
		btnSend.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Server Btn");
				try {
					String userOutput = "Server: ";
					userOutput += textField.getText();
					output.writeUTF(userOutput);
					textArea.setText(textArea.getText()+"\n"+userOutput);
					//textArea.setText(textArea.getText() + "\n" + input.readUTF());
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		});
		btnSend.setBounds(335, 214, 89, 23);
		contentPane.add(btnSend);
	}
}
