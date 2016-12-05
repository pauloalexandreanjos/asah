var hostService = "http://" + window.location.host + "/asah/services/";

function atualizaGasto(data) {
	id = data.id;
	data = "{\"gasto\":" + JSON.stringify(data) + "}";
	$.ajax({
		url : hostService + 'gastos/' + id,
		type : 'PUT',
		contentType : 'application/json',
		data : data,
		success : function(data) {
			alert("Incluído com sucesso!");
			$("#gastoForm")[0].reset();
			listarGastos();
		},
		error : function(data) {
			console.log(data);
			alert("Ocorreu um erro: " + data.status + " " + data.statusText);
		}
	});
}

function atualizaParcela(data) {
	var idGasto = data.gasto.id;
	var id = data.id;
	data = "{\"parcela\":" + JSON.stringify(data) + "}";
	$.ajax({
		url : hostService + 'parcelas/' + id,
		type : 'PUT',
		contentType : 'application/json',
		data : data,
		success : function(dataSucess) {
			alert("Incluído com sucesso!");
			$("#parcelaForm")[0].reset();
			listarParcelas(idGasto);
		},
		error : function(data) {
			console.log(data);
			alert("Ocorreu um erro: " + data.status + " " + data.statusText);
		}
	});
}

function criaGasto(data) {
	data = "{\"gasto\":" + JSON.stringify(data) + "}";
	$.ajax({
		url : hostService + 'gastos',
		type : 'POST',
		contentType : 'application/json',
		data : data,
		success : function(data) {
			alert("Incluído com sucesso!");
			$("#gastoForm")[0].reset();
			listarGastos();
		},
		error : function(data) {
			console.log(data);
			alert("Ocorreu um erro: " + data.status + " " + data.statusText);
		}
	});
}

function criaParcela(data) {
	var idGasto = data.gasto.id;
	data = "{\"parcela\":" + JSON.stringify(data) + "}";
	$.ajax({
		url : hostService + 'parcelas',
		type : 'POST',
		contentType : 'application/json',
		data : data,
		success : function(dataSucess) {
			alert("Incluído com sucesso!");
			$("#parcelaForm")[0].reset();
			listarParcelas(idGasto);
		},
		error : function(dataError) {
			console.log(data);
			alert("Ocorreu um erro: " + data.status + " " + data.statusText);
		}
	});
}

function adicionaGasto() {
	var data = $("#gastoForm").serializeJSON();
	
	if (data.id) {
		atualizaGasto(data);
	} else {
		criaGasto(data);
	}
}

function adicionaParcela() {
	var data = $("#parcelaForm").serializeJSON();
	data.gasto = { "id": $("#id-gasto").val()};
	
	if (data.id) {
		atualizaParcela(data);
	} else {
		criaParcela(data);
	}
}

function listarGastos() {

	$.ajax({
		url : hostService + 'gastos',
		type : 'GET',
		headers : {
			Accept : 'application/json'
		},
		success : function(data) {

			$('#grid tr:gt(0)').remove();
			if ($.isArray(data.gastos.link)) {
				for (var i = 0; i < data.gastos.link.length; i++) {
					var link = data.gastos.link[i]['@href'];
					segueLinkGasto(link);
				}
			} else {
				var link = data.gastos.link['@href'];
				segueLinkGasto(link);
			}

		},
		error : function(data) {
			alert("Erro na invocação");
		}
	});
}

function listarParcelas(gasto) {

	$.ajax({
		url : hostService + 'parcelas/gasto/'+gasto,
		type : 'GET',
		headers : {
			Accept : 'application/json'
		},
		success : function(data) {

			$('#gridParcela tr:gt(0)').remove();
			if ($.isArray(data.parcelas.link)) {
				for (var i = 0; i < data.parcelas.link.length; i++) {
					var link = data.parcelas.link[i]['@href'];
					segueLinkParcela(link);
				}
			} else {
				var link = data.parcelas.link['@href'];
				segueLinkParcela(link);
			}

		},
		error : function(data) {
			alert("Erro na invocação");
		}
	});
}

function segueLinkGasto(link) {
	$.ajax({
		url : hostService + link,
		type : 'GET',
		success : function(data) {
			adicionaGastoNovoAoGrid(data.gasto);
		},
		error : function(data) {
			alert("Ocorreu um erro");
		}
	});
}

function segueLinkParcela(link) {
	$.ajax({
		url : hostService + link,
		type : 'GET',
		success : function(data) {
			adicionaParcelaNovaAoGrid(data.parcela);
		},
		error : function(data) {
			alert("Ocorreu um erro");
		}
	});
}

function apagaGasto(id) {
	$.ajax({
		url : hostService + 'gastos/' + id,
		type : 'DELETE',
		success : function(data) {
			listarGastos();
		},
		error : function(data) {
			console.log(data);
			alert("Ocorreu um erro: " + data.status + " " + data.statusText);
		}
	});
}

function apagaParcela(id,gasto) {
	$.ajax({
		url : hostService + 'parcelas/' + id,
		type : 'DELETE',
		success : function(data) {
			listarParcelas(gasto);
		},
		error : function(data) {
			console.log(data);
			alert("Ocorreu um erro: " + data.status + " " + data.statusText);
		}
	});
}

function carregaGasto(id) {
	$.ajax({
		url : hostService + 'gastos/' + id,
		type : 'GET',
		success : function(data) {
			var frm = $("#gastoForm");
			$.each(data.gasto, function(key, value) {
				$('[name=' + key + ']', frm).val(value);
			});
			listarParcelas(data.gasto.id);
		},
		error : function(data) {
			console.log(data);
			alert("Ocorreu um erro: " + data.status + " " + data.statusText);
		}
	});
}

function carregaParcela(id) {
	$.ajax({
		url : hostService + 'parcela/' + id,
		type : 'GET',
		success : function(data) {
			var frm = $("#parcelaForm");
			$.each(data.parcela, function(key, value) {
				$('[name=' + key + ']', frm).val(value);
			});
		},
		error : function(data) {
			console.log(data);
			alert("Ocorreu um erro: " + data.status + " " + data.statusText);
		}
	});
}

function adicionaGastoNovoAoGrid(gasto) {

	var lin = document.createElement("tr");

	var col1 = document.createElement("td");
	col1.innerText = gasto.id
	
	var col2 = document.createElement("td");
	col2.innerText = gasto.descricao

	var col3 = document.createElement("td");
	var edita = document.createElement("input");
	
	$(edita).attr("type", "button");
	$(edita).attr("onclick", "carregaGasto(" + gasto.id + ")");
	$(edita).val("Editar")

	var apaga = document.createElement("input");
	$(apaga).attr("type", "button");
	$(apaga).attr("onclick", "apagaGasto(" + gasto.id + ")");
	$(apaga).val("Apagar")

	col3.append(edita);
	col3.append(apaga);

	lin.append(col1);
	lin.append(col2);
	lin.append(col3);

	$("#grid").append(lin);
}

function adicionaParcelaNovaAoGrid(parcela) {
	
	var lin = document.createElement("tr");

	var col1 = document.createElement("td");
	col1.innerText = parcela.id
	
	var col2 = document.createElement("td");
	col2.innerText = parcela.valor

	var col3 = document.createElement("td");
	col3.innerText = parcela.data
	
	var col4 = document.createElement("td");

	var apaga = document.createElement("input");
	
	$(apaga).attr("type", "button");
	$(apaga).attr("onclick", "apagaParcela(" + parcela.id + ","+parcela.gasto.id+")");
	$(apaga).val("Apagar")

	col4.append(apaga);

	lin.append(col1);
	lin.append(col2);
	lin.append(col3);
	lin.append(col4);
	
	$("#gridParcela").append(lin);
}