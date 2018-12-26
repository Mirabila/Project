var initialization = false
var names = [];
var isOpened = [];
var isOpen;
var children = [];
var len = [];

if (initialization == false) {
    var kinds = document.getElementsByClassName("kind");
	
    for (var i = 0; i < kinds.length; i++) {
        names[i] = kinds[i].id;
        isOpened[i] = false;
	isOpen = true;
        document.getElementById("b" + i).innerHTML = "Open";
    }
    children = new Array(names.length);
    for (var i = 0; i < names.length; i++) {
        var k = document.getElementById(names[i]);
        children[i] = new Array(k.childElementCount);
        if (k.hasChildNodes()) {
	var j = 0;
            for (j = k.childElementCount; j > 1; j--) {
                children[i][j] = k.childNodes[j].innerHTML;
                k.removeChild(k.childNodes[j]);
            }
	len[i] = j;
        }
    }
    initialization = true;
}
function m(event) {
    idButton = event.target.id;
    numButton = idButton[1];
    if (isOpened[numButton] == true) { 
        document.getElementById(idButton).innerHTML = "Open";
        for (var i = 0; i < names.length; i++) {
            var k = document.getElementById(numButton);
            if (k.hasChildNodes()) {
                for (var j = k.childElementCount; j > 1; j--) {
                    k.removeChild(k.lastChild);
                }
            }
        }
        isOpened[numButton] = false;
    } else {    
        document.getElementById(idButton).innerHTML = "Close";
        var m = document.getElementById(numButton);
        for(var i = 2; i < children[numButton].length; i++){
            var node = document.createElement("div");
            var textnode = document.createTextNode(children[numButton][i]);
		if(children[numButton][i] != undefined){
		    node.appendChild(textnode);
		    m.appendChild(node);
		}
        }
        isOpened[numButton] = true;
    }

}
function n(event) {
  for (var i = 0; i < names.length; i++) {
	if (isOpened[i] == true) {
		document.getElementById("b" + i).innerHTML = "+";
		var k = document.getElementById(names[i]);
		isOpened[i] = false;
		if (k.hasChildNodes()) {
		    for (var j = k.childElementCount; j > 1; j--) {
		        k.removeChild(k.childNodes[j]);
		    }
		
		}
	}
    }
} 
function l(event) {
    a = new Array(names.length); 
     for (var i = 0; i < names.length; i++) {
       if (isOpened[i] == false) {
		document.getElementById("b" + i).innerHTML = "-";
		var k = document.getElementById(names[i]);
		a[i] = new Array(k.childElementCount);
		isOpened[i] = true;
		if (k.hasChildNodes()) {
		    for(var j = 2; j < children[names[i]].length; j++){
			    var node = document.createElement("div");
			    var textnode = document.createTextNode(children[names[i]][j]);
				if(children[names[i]][j] != undefined){
				    node.appendChild(textnode);
				    k.appendChild(node);
				}
			}
		}
	}
    }
}
function ev(event) {
	for (var i = 0; i < children.length; i++) {
		document.getElementById("b" + i).innerHTML = "+";
		var k = document.getElementById(names[i]);
		isOpened[i] = false;
		if (k.hasChildNodes()) {
		    for (var j = k.childElementCount; j > 1; j--) {
		        k.removeChild(k.childNodes[j]);
		    }
		
		}
		
		 for(var j = 2; j < children[i].length; j++) {
			delete children[i][j];
	    }
	}

    
} 
