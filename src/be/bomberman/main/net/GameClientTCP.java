package be.bomberman.main.net;

import java.net.*;
import java.io.*;

import be.bomberman.main.Bomberman;

public class GameClientTCP extends Thread{
	private InetAddress ipAddress;
	private Socket socket;
	private Bomberman bomberman;
	private String message;
	private PrintWriter out;
	
	public GameClientTCP(Bomberman bomberman,String ipAddress,int port)
	{
		this.bomberman = bomberman;
		try
		{
			this.socket = new Socket(ipAddress,port); //creation d'un datagram socket. On utilise aussi un port, pas nptq comme dans l'exemple.ça sert a rien je crois :p
			this.out = new PrintWriter(this.socket.getOutputStream(), true);
			this.ipAddress = InetAddress.getByName(ipAddress);//recuperation de l'addresse ip 
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void run() //methode run, on est tjrs a l'ecoute du serveur, de ce qu'il pourrais nous dire
	{
		while(true){
			update();
		}
	}
	public void sendData(String data) //fonction pour envoyer des messages au serveur
	{
		try {
            out.println(data);
		}catch (Exception e){
            e.printStackTrace();
		}
	}
	public void update()
	{
		try{
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while((message = in.readLine()) != null){
            	System.out.println("On a bien reçu le message");
            	break;
            }
        }catch (Exception e){
			e.printStackTrace();
		}
		
		System.out.println("SERVER > " + message); //on raconte ce qu'il nous arrive
		
		//si tous les joueurs sont connectés
		if(message.trim().equals("OK")){
			System.out.println("on peut commencer la partie");
			bomberman.start();
		}else{
			
			//on verifie si il faut placer une bombe
			if((message.trim().split("-")[1].trim().split("_")[2]).equals("o")){
				//si c'est le cas, on indique qu'il faut la placer
				bomberman.coordBombe[0] = Integer.valueOf(message.trim().split("-")[1].trim().split("_")[0]);
				bomberman.coordBombe[1] = Integer.valueOf(message.trim().split("-")[1].trim().split("_")[1]);
				bomberman.ilFautPlacerUneBombe = true;
			}
			bomberman.msg = message;
			
		}
		
		//il faudrait creer un nouvel objet avec le string reçu pour ordonner au personnage de bouger
		//System.out.println("on update");
	}
}
