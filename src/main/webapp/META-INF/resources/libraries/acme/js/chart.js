//a un color tipo rgba(1,1,1,a) le cambia a
function cambiaColor(color,a){
  if(a.match(/^([0-1]||0\.\d)$/)){//Comprueba que a es correcto
    return color.replace(/\d\)/, a+")");//cambia el a de rgba
  }
  console.log("Fallo al cambiar color "+color+" con "+a);
  return color;
}

window.chartColors = {
	red: 'rgba(255, 99, 132, 1)',
	orange: 'rgba(255, 159, 64, 1)',
	yellow: 'rgba(255, 205, 86, 1)',
	green: 'rgba(75, 192, 192, 1)',
	blue: 'rgba(54, 162, 235, 1)',
	purple: 'rgba(153, 102, 255, 1)',
	grey: 'rgba(201, 203, 207, 1)'
};
//var COLORS = ['#4dc9f6','#f67019','#f53794','#537bc4','#acc236','#166a8f','#00a950','#58595b','#8549ba'];


function construyeGrafica(chart, tipo, dataY,dataX, labels, titulo, stacked){
  chart.height = 350;  chart.width = 350;
  return grafica(chart, tipo, dataY,dataX, labels, titulo, stacked[0], stacked[1]);
}


function grafica(ctx, tipo, dataY, dataX, labels, titulo, yStacked, xStacked){
	var data = datosGrafica(dataY, dataX, labels);
	var options = {
			scales: {
        yAxes: [{
          stacked: yStacked,
	        ticks: {
	        	suggestedMin: 0,
	        	//suggestedMax: 5,
				stepSize: 1
	        }/*,
	        afterBuildTicks: function(humdaysChart) {    
	            humdaysChart.ticks = [0,1,2,3,4,5];
	        }*/
        }],
        xAxes: [{
          stacked: xStacked
        }]
      },
      tooltips:{
        enabled:false
      },
			title:{
				display:false,
				text:titulo,
				fontSize: 20
			},
			elements: {
          line: {
            tension: 0 // disables bezier curves
          }
        },
			legend:{
				display:true
			},
			responsive : false
		}

	Chart.defaults.global.defaultFontColor = '#3a3737';
	Chart.defaults.global.defaultFontFamily = 'Lato';
  //Chart.defaults.global.defaultFontSize = 12;


	var c = new Chart(ctx, {
		type: tipo,
		data: data,
		options: options
		});
	return c
}


//Coge los datos y los transforma para hacerlos compatibles con Chart.js

function datosGrafica(dataY,dataX, labels){
	//El método ordena da igual si los inversores y compañias tienen los mismos sectores en la BD
  //var datos = ordena(dataY,dataX); 
	
  var datos = [dataX[0], [dataY[0], dataY[1]]];//[sectores, [numComp, numInv]] 
  
  var red = window.chartColors.red, blue = window.chartColors.blue; 
  
	return {
		labels: datos[0],
		datasets: [{
      label: labels[0],//Nombre en la leyenda
			data: datos[1][0],
      backgroundColor: cambiaColor(red,"0.6"),
      hoverBackgroundColor:cambiaColor(red,"0.8"),
			borderColor:cambiaColor(red,"1"),
			borderWidth: 1
		},
    {
      label: labels[1],//Nombre en la leyenda
      data: datos[1][1],
      backgroundColor: cambiaColor(blue,"0.6"),
      hoverBackgroundColor:cambiaColor(blue,"0.8"),
			borderColor:cambiaColor(blue,"1"),
			borderWidth: 1
		}
  ]}
}

//Este método mezcla los sectores de las compañias e inversores y los ordena.
//Si inversor/compania no tiene un sector que compañia/compania si, le añadimos un 0 en la posición correcta de manera que los datos sean correctos.

function ordena(dataY,datosX){
	  //Copiamos los arrays
	  var sectores = datosX[0].slice(), sectoresCompanias = datosX[0].slice(), sectoresInversores = datosX[1].slice();//Sectores
	  var datosCompanias = dataY[0].slice(), datosInversores = dataY[1].slice();//nº compañia y nº inversores 
	  
	  //Por cada sector del segundo array de sectores lo metemos en el otro si no lo tiene.
	  
	  for(var i=0 ; i< sectoresInversores.length ; i++){
	    var str = sectoresInversores[i];
	    if(!sectores.includes(str)){
	    	sectores.push(str);
	    }
	  }
	  sectores.sort();//Lo ordenamos

	  // Luego creamos los datos data1 y data2 de manera que tenga los datos correctos de nº inversores y compañias, añadiendo 0 por
	  // cada sector que no tenga inversor  / compañia.
	  for(var i=0 ; i<sectores.length ; i++)
	  {
	    var str = sectores[i];
	    
	    if(!sectoresCompanias.includes(str))  datosCompanias.splice(i, 0, 0);

	    if(!sectoresInversores.includes(str))  datosInversores.splice(i, 0, 0);
	  }
	  
	  var res = [sectores,[datosCompanias,datosInversores]];
	  console.log(res);
	  return res;
	}
