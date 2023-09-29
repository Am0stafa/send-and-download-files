import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {

        // First thing we need to do declare the file that we are going to send. 
        final File[] fileToSend = new File[1];

        JFrame jFrame = new JFrame("Client for sharing files.");
        jFrame.setSize(350, 350);
        
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel jlTitle = new JLabel("Choose file(s) to share");
        jlTitle.setFont(new Font("Arial", Font.BOLD, 26));
        jlTitle.setBorder(new EmptyBorder(20,0,10,0));
        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel jlFileName = new JLabel("Choose a file to send.");
        jlFileName.setFont(new Font("Arial", Font.BOLD, 20));
        jlFileName.setForeground(Color.DARK_GRAY);
        jlFileName.setBorder(new EmptyBorder(50, 0, 0, 0));
        jlFileName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel jpButton = new JPanel();
        jpButton.setBorder(new EmptyBorder(75, 0, 10, 0));
        JButton jbSendFile = new JButton("Send File");
        jbSendFile.setPreferredSize(new Dimension(150, 75));
        jbSendFile.setFont(new Font("Arial", Font.BOLD, 20));
        jbSendFile.setBackground(new Color(70, 130, 180));
        jbSendFile.setForeground(Color.WHITE);
        jbSendFile.setFocusPainted(false);
        jbSendFile.setBorder(BorderFactory.createRaisedBevelBorder());

        JButton jbChooseFile = new JButton("Choose File");
        jbChooseFile.setPreferredSize(new Dimension(150, 75));
        jbChooseFile.setFont(new Font("Arial", Font.BOLD, 20));
        jbChooseFile.setBackground(new Color(70, 130, 180));  // SteelBlue color
        jbChooseFile.setForeground(Color.WHITE);
        jbChooseFile.setFocusPainted(false);
        jbChooseFile.setBorder(BorderFactory.createRaisedBevelBorder());


        jpButton.add(jbSendFile);
        jpButton.add(jbChooseFile);

        jbChooseFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbChooseFile.setBackground(new Color(135, 206, 250));  // LightSteelBlue color on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbChooseFile.setBackground(new Color(70, 130, 180));  // Revert to original color on exit
            }
        });

        jbSendFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbSendFile.setBackground(new Color(135, 206, 250));  // LightSteelBlue color on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbSendFile.setBackground(new Color(70, 130, 180));  // Revert to original color on exit
            }
        });

        // Button action for choosing the file.
        jbChooseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a file chooser to open the dialog to choose a file.
                JFileChooser jFileChooser = new JFileChooser();
                // Set the title of the dialog.
                jFileChooser.setDialogTitle("Choose a file to send.");
                // Show the dialog and if a file is chosen from the file chooser execute the following statements.
                if (jFileChooser.showOpenDialog(null)  == JFileChooser.APPROVE_OPTION) {
                    // Get the selected file.
                    fileToSend[0] = jFileChooser.getSelectedFile();
                    // Change the text of the java swing label to have the file name.
                    jlFileName.setText("The file you want to send is: " + fileToSend[0].getName());
                }else{
                    // If no file is chosen then display this message.
                    jlFileName.setText("No file chosen.");
                }

            }
        });


        // Sends the file when the button is clicked.
        jbSendFile.addActionListener(new ActionListener() {
            @Override
            @SuppressWarnings("resource")
            public void actionPerformed(ActionEvent e) {
                // If a file has not yet been selected then display this message.
                if (fileToSend[0] == null) {
                    jlFileName.setText("Please choose a file to send first!");
                    // If a file has been selected then do the following.
                } else {
                    try {
                        // Allowing us to into this file
                        FileInputStream fileInputStream = new FileInputStream(fileToSend[0].getAbsolutePath());
                        // Create a socket connection to connect with the server.
                        Socket socket = new Socket("localhost", 1234);
                        // Create an output stream to write to write to the server over the socket connection.
                        // To basically send a file to our server.
                        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                        // Get the name of the file you want to send and store it.
                        String fileName = fileToSend[0].getName();
                        // Convert the name of the file into an array of bytes to be sent to the server.
                        byte[] fileNameBytes = fileName.getBytes();
                        // Create a byte array the size of the file so don't send too little or too much data to the server.
                        byte[] fileBytes = new byte[(int)fileToSend[0].length()];
                        // Put the contents of the file into the array of bytes to be sent so these bytes can be sent to the server. Read into fileBytes as it is empty.
                        fileInputStream.read(fileBytes);
                        // Send the length of the name of the file so server knows when to stop reading.
                        dataOutputStream.writeInt(fileNameBytes.length);
                        // Send the file name.
                        dataOutputStream.write(fileNameBytes);
                        // Send the length of the byte array so the server knows when to stop reading.
                        dataOutputStream.writeInt(fileBytes.length);
                        // Send the actual file.
                        dataOutputStream.write(fileBytes);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(jlTitle);
        mainPanel.add(jlFileName);
        mainPanel.add(jpButton);

        jFrame.add(mainPanel);
        jFrame.setVisible(true);

    }
}