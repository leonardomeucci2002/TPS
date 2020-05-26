var x=2, y=5; 		//variabili che indicano la posizione dell'aereo avversario
var rand = 0;		//var per la direzione da assumere
var cambioDir;      //var per il cambio della direzione

//vettori per le direzioni
var dir=1;

var movimento;


f = function() 
{
		do{
			//cambio di direzione casuale
			rand = Math.floor(Math.random() * 10);
			if(rand<4){
				y+=dir;
			}
			else{
				dir=-dir;
				y+=dir;
			}
			
			if(y < 0)
			{
				y++;
			}
			if(y > 9)
			{
				y--;
			}
		}while(y < 0 || y > 9);
	
	
	movimento = {
		"x": x,
		"y": y
	}
	
	postMessage(movimento);
	
	setTimeout(f, 200);	//millisecondi ogni quanto vieni modificata la posizione del bambino
}

f();