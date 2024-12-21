package be.bomberman.main.net;

import java.net.*;

import be.bomberman.main.*;

public class GameClient extends Thread{
	
	private InetAddress ipAddress;
	private DatagramSocket socket;
	private Bomberman bomberman;
	private String message;
	
	public GameClient(Bomberman bomberman,String ipAddress)
	{
		this.bomberman = bomberman;
		try
		{
			this.socket = new DatagramSocket(); //creation d'un datagram socket. On utilise aussi un port, pas nptq comme dans l'exemple.ça sert a rien je crois :p
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
	public void sendData(byte[] data) //fonction pour envoyer des messages au serveur
	{
		try {
			DatagramPacket packet = new DatagramPacket(data,data.length,ipAddress,bomberman.portServers[bomberman.choixPort]); //on declare une nouvelle datagram packet, avec notre buffer, l'adresse ip du serveur, et le port 
			socket.send(packet);//on envoie le paquet
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public void update()
	{
		//System.out.println("on attend");
		byte[] data = new byte[1024]; //notre buffer, on y mettra les donnees a recevoir du serveur
		DatagramPacket packet = new DatagramPacket(data,data.length); //declaration de notre pacquet, ça sert juste a recevoir l'info
		try
		{
			/*if (bomberman.player2.isMoving == true){
				Thread.sleep(17);
			}*/
			
			socket.receive(packet);//on reçoi le paquet, qui va se sauvegarder dans "data" (notre buffer)
		}catch (Exception e){
			e.printStackTrace();
		}
		
		message = new String(packet.getData());
		System.out.println("SERVER > " + message); //on raconte ce qu'il nous arrive
		
		
		//il faudrait creer un nouvel objet avec le string reçu pour ordonner au personnage de bouger
		//System.out.println("on update");
		//on verifie si il faut placer une bombe
		//on verifie si il faut placer une bombe
		if((message.trim().split("-")[1].trim().split("_")[2]).equals("o")){
			//si c'est le cas, on indique qu'il faut la placer
			bomberman.coordBombe[0] = Integer.valueOf(message.trim().split("-")[1].trim().split("_")[0]);
			bomberman.coordBombe[1] = Integer.valueOf(message.trim().split("-")[1].trim().split("_")[1]);
			bomberman.ilFautPlacerUneBombe = true;
		}
		bomberman.msg = message;
		
	}
		
}

