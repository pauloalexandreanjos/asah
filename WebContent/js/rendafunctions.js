var hostService = "http://" + window.location.host + "/asah/services/"

function atualizaRenda(data) {
	id = data.id;
	data = "{\"renda\":" + JSON.stringify(data) + "}";
	$.ajax({
		url : hostService + 'rendas/' + id,
		type : 'PUT',
		contentType : 'application/json',
		data : data,
		success : function(data) {
			alert("Incluído com sucesso!");
			$("#rendaForm")[0].reset();
			listarTodasRendas();
		},
		error : function(data) {
			console.log(data);
			alert("Ocorreu um erro: " + data.status + " " + data.statusText);
		}
	});
}

function criaRenda(data) {
	data = "{\"renda\":" + JSON.stringify(data) + "}";
	$.ajax({
		url : hostService + 'rendas',
		type : 'POST',
		contentType : 'application/json',
		data : data,
		success : function(data) {
			alert("Incluído com sucesso!");
			$("#rendaForm")[0].reset();
			listarTodasRendas();
		},
		error : function(data) {
			console.log(data);
			alert("Ocorreu um erro: " + data.status + " " + data.statusText);
		}
	});
}

function adicionaRenda() {
	var data = $("#rendaForm").serializeJSON();

	if (data.id) {
		atualizaRenda(data);
	} else {
		criaRenda(data);
	}
}

function listarTodasRendas() {

	$.ajax({
		url : hostService + 'rendas',
		type : 'GET',
		headers : {
			Accept : 'application/json'
		},
		success : function(data) {

			$('#grid tr:gt(0)').remove();
			if ($.isArray(data.rendas.link)) {
				for (var i = 0; i < data.rendas.link.length; i++) {
					var link = data.rendas.link[i]['@href'];
					segueLinkRenda(link);
				}
			} else {
				var link = data.rendas.link['@href'];
				segueLinkRenda(link);
			}

		},
		error : function(data) {
			alert("Erro na invocação");
		}
	});
}

function segueLinkRenda(link) {
	$.ajax({
		url : hostService + link,
		type : 'GET',
		success : function(data) {
			adicionaRendaNovaAoGrid(data.renda);
		},
		error : function(data) {
			alert("Ocorreu um erro");
		}
	});
}

function apagaRenda(id) {
	$.ajax({
		url : hostService + 'rendas/' + id,
		type : 'DELETE',
		success : function(data) {
			listarTodasRendas();
		},
		error : function(data) {
			console.log(data);
			alert("Ocorreu um erro: " + data.status + " " + data.statusText);
		}
	});
}

function carregaRenda(id) {
	$.ajax({
		url : hostService + 'rendas/' + id,
		type : 'GET',
		success : function(data) {
			var frm = $("#rendaForm");
			$.each(data.renda, function(key, value) {
				$('[name=' + key + ']', frm).val(value);
			});
		},
		error : function(data) {
			console.log(data);
			alert("Ocorreu um erro: " + data.status + " " + data.statusText);
		}
	});
}

function adicionaRendaNovaAoGrid(renda) {

	var lin = document.createElement("tr");

	var col1 = document.createElement("td");
	col1.innerText = renda.id

	var col2 = document.createElement("td");
	col2.innerText = renda.valor

	var col3 = document.createElement("td");
	col3.innerText = renda.periodicidade

	var col4 = document.createElement("td");
	col4.innerText = renda.descricao

	var col5 = document.createElement("td");
	var edita = document.createElement("input");
	$(edita).attr("type", "button");
	$(edita).attr("onclick", "carregaRenda(" + renda.id + ")");
	$(edita).val("Editar")

	var apaga = document.createElement("input");
	$(apaga).attr("type", "button");
	$(apaga).attr("onclick", "apagaRenda(" + renda.id + ")");
	$(apaga).val("Apagar")

	col5.append(edita);
	col5.append(apaga);

	lin.append(col1);
	lin.append(col2);
	lin.append(col3);
	lin.append(col4);
	lin.append(col5);

	$("#grid").append(lin);
}