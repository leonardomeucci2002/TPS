/*Meucci Leonardo 4Ai 
Progarmma che permette ad un client di comunicare con un server inserendo la password 'ciao', e di fargli svolgere le operazione 
matematiche basilari. La comunicazione tra i due si interrompe alla scelta dell'opzione 'exit'
*/


package client;

import java.util.Scanner;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    Socket miosocket = null;
    
    int porta = 8888;    //porta del server
        
    DataInputStream in;
    DataOutputStream out;
    
    //input bufferizzato
    BufferedReader tastiera;
    String messaggio;
    int messaggioint;
    String ricevuta;
    
    //quando vale 1 la comunicazione si interrompe
    int uscita=0;
    
    public void comunica(){
        Scanner inn = new Scanner(System.in);  // Crea un oggetto scanner
        
        try {
            //faccio inserire una password per poter utilizzare il server
            tastiera=new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Inserisci la password: ");
            messaggio= tastiera.readLine();
            System.out.println("Invio la password: "+messaggio);
            out.writeBytes(messaggio+"\n");
            System.out.println("In attesa di una risposta...");
            ricevuta=in.readLine();
            System.out.println("Risposta del server: "+ricevuta);
            
            if(ricevuta.compareTo("Password corretta")==0){
                //continua a comunicare fino a quando non scrive exit
                do{
                    System.out.println("\n\nChe operazione vuoi far svolgere al server:\n1)addizzione\n2)sottrazzione\n3)moltiplicazione\n4)divisione\n5)exit\nInserisci il numero relativo alla tua scelta:  ");
                    int scelta;
                    do{
                        scelta=inn.nextInt();
                        if(scelta<1 || scelta>5){
                            System.out.print("Scelta non valida\nReinserisci: ");
                        }
                    }while(scelta<1 || scelta>5);
                    
                    if(scelta==5)
                        uscita=1;

                    messaggioint=uscita;
                    System.out.println("Invio: "+messaggioint);
                    out.writeBytes(messaggioint+"\n");


                    System.out.println("In attesa di una risposta...");
                    ricevuta=in.readLine();
                    System.out.println("Risposta del server: "+ricevuta);
                    
                    if(scelta!=5){
                        System.out.print("Inserisci primo numero: ");
                        int n1=inn.nextInt();
                        System.out.print("Inserisci secondo numero: ");
                        int n2=inn.nextInt();

                        System.out.println("Messaggi da inviare al server: ");
                        switch(scelta){
                            case 1:
                                messaggio= "+";
                                break;
                            case 2:
                                messaggio= "-";
                                break;
                            case 3:
                                messaggio= "*";
                                break;
                            case 4:
                                messaggio= "/";
                        }
                        
                        System.out.println("Invio: "+n1);
                        out.writeBytes(n1+"\n");
                        
                        System.out.println("Invio: "+messaggio);
                        out.writeBytes(messaggio+"\n");
                        
                        System.out.println("Invio: "+n2);
                        out.writeBytes(n2+"\n");
                        
                        System.out.println("In attesa di una risposta...");
                        ricevuta=in.readLine();
                        System.out.println("Risposta del server: "+ricevuta);
                    }
                    
                }while(messaggioint!=1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Socket connetti(){
        try {                                       //per evitare possibili errori
            System.out.println("Provo a connettermi al server...");
            //servername= InetAddress.getLocalHost(); funzione che restituisce l'ID del pc che si sta usando
            miosocket = new Socket(InetAddress.getLocalHost(), porta);
            
            System.out.println("Conesso al server!");
            in= new DataInputStream(miosocket.getInputStream());;
            out= new DataOutputStream(miosocket.getOutputStream());;
            
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return miosocket;
    }
    
    
    public static void main(String[] args) {
        Client c =new Client();
        c.connetti();
        c.comunica();
    }
    
}
