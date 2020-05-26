package server;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    ServerSocket server = null;
    Socket socketClient = null;
    
    int porta = 8888;    //maggiore di 1024 e minore di 65535
        
    DataInputStream in;
    DataOutputStream out;
    
    String letto;
    String letto1;
    int lettoint;
    int letto2,letto3;
    String risposta;
    int risposta2;
    
    public void comunica(){
        
        try {
            System.out.println("Aspetto un messaggio...");
            letto=in.readLine();
            System.out.println("Messaggio ricevuto: "+letto);
            //converto in maiuscolo e confronto
            if(letto.compareTo("ciao")==0){
                risposta="Password corretta";
                System.out.println("Rispondo con : "+risposta);
                out.writeBytes(risposta+"\n");
                
                //rimango in attesa
                do{
                    
                    System.out.println("Aspetto un messaggio...");
                    lettoint=Integer.parseInt(in.readLine());
                    System.out.println("Messaggio ricevuto: "+lettoint);
                    if(lettoint==1){
                        //chiudo connessione
                        risposta="1";
                    }
                    else
                        risposta="0";
                    System.out.println("Rispondo con : "+risposta);
                    out.writeBytes(risposta+"\n");
                    
                    if(lettoint!=1){
                        //rimango in attesa
                        //ricevo primo numero
                        System.out.println("Aspetto tre messaggi...");
                        //converto in int
                        letto2=Integer.parseInt(in.readLine());
                        System.out.println("Messaggio ricevuto: "+letto2);

                        //ricevo tipo di operazione
                        letto1=in.readLine();
                        System.out.println("Messaggio ricevuto: "+letto1);

                        //ricevo secondo numero
                        //converto in int
                        letto3=Integer.parseInt(in.readLine());
                        System.out.println("Messaggio ricevuto: "+letto3);

                        if(letto.compareTo("exit")==0){
                            String risposta=letto;
                            System.out.println("Rispondo con : "+risposta);
                            out.writeBytes(risposta+"\n");
                        }
                        else{
                            //svolgo le operazioni
                            if(letto1.compareTo("+")==0)
                                risposta2=letto2+letto3;
                            if(letto1.compareTo("-")==0)
                                risposta2=letto2-letto3;
                            if(letto1.compareTo("*")==0)
                                risposta2=letto2*letto3;
                            if(letto1.compareTo("/")==0)
                                risposta2=letto2/letto3;
                            //invio la risposta
                            System.out.println("Rispondo con : "+risposta2);
                            out.writeBytes(risposta2+"\n");
                        }
                    }
                    
                    //chiudo la conenssione se ricevo il messaggio 1 all'inizio
                }while(lettoint!=1);
                socketClient.close();
                System.out.println("Chiudo la connessione con quel client!");
                
            }
            else{
                risposta="Password errata";
                System.out.println("Rispondo con : "+risposta);
                out.writeBytes(risposta+"\n");

                //chiudo la connessione con quel client
                socketClient.close();
                System.out.println("Chiudo la connessione con quel client!");
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Socket attendi(){
        try {
            System.out.println("Server inizializzato...");
            //inizializzo il servizio
            server = new ServerSocket(porta);
            
            System.out.println("Server pronto, in ascolto sulla porta "+porta+"...");
            //si mette in ascolto sulla porta che ho aperto
            socketClient = server.accept();
            
            System.out.println("Connessione stabilita con un client!");
            
            //per evitare connessioni multiple useremmo
            server.close();
            
            in= new DataInputStream(socketClient.getInputStream());
            out= new DataOutputStream(socketClient.getOutputStream());
                    
            
        } catch (IOException e) {   //per eventuali errori che possono verificarsi
            System.out.println("Error : " + e);
        }
        return socketClient;
    }
    
    public static void main(String[] args) {
        //facciamo un istanza
        Server s = new Server();
        s.attendi();
        s.comunica();
    }
    
}
