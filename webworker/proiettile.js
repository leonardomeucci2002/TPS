var giasparato=0;
var xx,yy;

//riceve messaggio
onmessage = function(event) {
	if(giasparato==0){
		giasparato=1;
		xx=event.data.x;
		yy=event.data.y;
		
		f();
	}
}

f = function() {
	if(giasparato==1){
		if(xx >0)
		{
			xx--;
		}
		else{
			giasparato=0;
		}
		
		let pospro = {
			x: xx,
			y: yy,
		}
		//invia messaggio
		postMessage(pospro);
		
		setTimeout(f, 40);		//millisecondi ogni quanto vieni modificata la posizione del proiettile
	}
}



