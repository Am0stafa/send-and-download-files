# Send and download files


This project is a Java-based file-sharing application that allows a client to send files to a server. The server can preview and choose to download these files. The application employs Java Swing for the graphical user interface and Java Sockets for network communication.

## Table of Contents
1. [How It Works](#how-it-works)
2. [Video Demonstration](#video-demonstration)
3. [Getting Started](#getting-started)
4. [Usage](#usage)
5. [Contributing](#contributing)
6. [License](#license)
7. [Contact](#contact)

## How It Works

### Client-Side:
- The client-side interface provides options to choose and send a file to the server.
- On clicking "Choose File", a file chooser dialog appears for file selection. The name of the selected file is displayed on the GUI.
- On clicking "Send File", the application establishes a socket connection with the server and sends the file name and file data over the connection.

### Server-Side:
- The server continuously listens for incoming connections from clients.
- Upon receiving a file, the server stores the file data and updates the GUI to list the received file.
- Clicking on a file entry in the list opens a new window with options to download or discard the file. A preview of the file content is provided for text files and images.
- Clicking "Yes" on the download prompt writes the file data to disk, thus completing the download.

## Video Demonstration

[![File Sharing Application Demo](https://img.youtube.com/vi/YOUR_VIDEO_ID/0.jpg)](https://www.youtube.com/watch?v=YOUR_VIDEO_ID)

## Getting Started

To get a local copy up and running, follow these simple steps:

1. Clone the repository:
```bash
git clone https://github.com/Am0stafa/send-and-download-files.git
```
2. Navigate to the project directory:
```bash
cd send-and-download-files
```
3. Compile the Java files:
```bash
javac *.java
```
4. Run the Server and Client applications in separate terminal windows:
```bash
java Server
java Client
```

## Usage

This application can be utilized for simple file sharing between a client and server within a local network.

## Contributing

Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request
