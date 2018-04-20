package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import display.GameScreen;

public class ControllerServer extends Thread {
	private ServerSocket ss;
	private Socket s;
	private int port = 9000;
	private String ip = "192.168.100.32";
	private PrintStream output;
	private BufferedReader input;
	private String dataIn = "";
	private String dataOut = "";
	private static ControllerServer server;
	private GameScreen game;
	
	public ControllerServer(GameScreen game) {
		this.game = game;
	}
	
	public ControllerServer getServer(GameScreen game) {
		if(server == null) {
			server = new ControllerServer(game);
		}
		return server;
	}
	
	public void init() throws IOException {
		try {
			System.out.println("Searching connection...");
			ss = new ServerSocket(port);
			s = new Socket();
			
			dataOut = String.valueOf(game.getScore()+"/"+game.getLevel().getCurrent().getLineClass()+"/"+game.getLevel().getNext().getLineClass());
			System.out.println(dataOut);
			
			while(true) {
				s = ss.accept();
				System.out.println("Connection established...");
				input = new BufferedReader(new InputStreamReader(s.getInputStream()));
				output = new PrintStream(s.getOutputStream());
				
				dataIn = input.readLine();
				System.out.println(dataIn);
				System.out.println("Data received...");
				dataOut = (String.valueOf(game.getScore()+"/"+game.getLevel().getCurrent().getLineClass()+"/"+game.getLevel().getNext().getLineClass()));
				output.println(dataOut);
				System.out.println("Data sent...");
			}
		} catch(IOException e) {
			System.out.println("Connection failed...");
		}
		finally {
			input.close();
			output.close();
			ss.close();
			s.close();
		}
	}
	
	public String getDataIn() {
		return dataIn;
	}
	
	public GameScreen getGame() {
		return game;
	}
	
	@Override
	public void run() {
		super.run();
		try {
			this.init();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
