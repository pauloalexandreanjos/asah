var hostService = "http://" + window.location.host + "/asah/services/"

function atualizaSonho(data) {
	id = data.id;
	
	data.meta = { "id": data.meta}
	
	data = "{\"sonho\":" + JSON.stringify(data) + "}";
	$.ajax({
		url : hostService + 'sonhos/' + id,
		type : 'PUT',
		contentType : 'application/json',
		data : data,
		success : function(data) {
			alert("Incluído com sucesso!");
			$("#sonhoForm")[0].reset();
			listarSonhos();
		},
		error : function(data) {
			alert("Ocorreu um erro: " + data.status + " " + data.statusText);
		}
	});
}

function criaSonho(data) {
	data.meta = { "id": data.meta}
	
	data = "{\"sonho\":" + JSON.stringify(data) + "}";
	$.ajax({
		url : hostService + 'sonhos',
		type : 'POST',
		contentType : 'application/json',
		data : data,
		success : function(data) {
			alert("Incluído com sucesso!");
			$("#sonhoForm")[0].reset();
			listarSonhos();
		},
		error : function(data) {
			alert("Ocorreu um erro: " + data.status + " " + data.statusText);
		}
	});
}

function adicionaSonho() {
	var data = $("#sonhoForm").serializeJSON();

	if (data.id) {
		atualizaSonho(data);
	} else {
		criaSonho(data);
	}
}

function listarSonhos() {

	$.ajax({
		url : hostService + 'sonhos',
		type : 'GET',
		headers : {
			Accept : 'application/json'
		},
		success : function(data) {

			console.log(data)
			
			$('#grid tr:gt(0)').remove();
			if ($.isArray(data.sonhos.link)) {
				for (var i = 0; i < data.sonhos.link.length; i++) {
					var link = data.sonhos.link[i]['@href'];
					segueLinkSonho(link);
				}
			} else {
				var link = data.sonhos.link['@href'];
				segueLinkSonho(link);
			}

		},
		error : function(data) {
			alert("Erro na invocação");
		}
	});
}

function segueLinkSonho(link) {
	$.ajax({
		url : hostService + link,
		type : 'GET',
		success : function(data) {
			adicionaSonhoNovoAoGrid(data.sonho);
		},
		error : function(data) {
			alert("Ocorreu um erro");
		}
	});
}

function apagaSonho(id) {
	$.ajax({
		url : hostService + 'sonhos/' + id,
		type : 'DELETE',
		success : function(data) {
			listarSonhos();
		},
		error : function(data) {
			alert("Ocorreu um erro: " + data.status + " " + data.statusText);
		}
	});
}

function carregaSonho(id) {
	$.ajax({
		url : hostService + 'sonhos/' + id,
		type : 'GET',
		success : function(data) {
			var frm = $("#sonhoForm");
			$.each(data.sonho, function(key, value) {
				$('[name=' + key + ']', frm).val(value);
			});
		},
		error : function(data) {
			alert("Ocorreu um erro: " + data.status + " " + data.statusText);
		}
	});
}

function adicionaSonhoNovoAoGrid(sonho) {

	var lin = document.createElement("tr");

	var col1 = document.createElement("td");
	col1.innerText = sonho.id

	var col2 = document.createElement("td");
	col2.innerText = sonho.valor

	var col3 = document.createElement("td");
	col3.innerText = sonho.previsao

	var col4 = document.createElement("td");
	col4.innerText = sonho.descricao

	var col5 = document.createElement("td");
	var edita = document.createElement("input");
	$(edita).attr("type", "button");
	$(edita).attr("onclick", "carregaSonho(" + sonho.id + ")");
	$(edita).val("Editar")

	var apaga = document.createElement("input");
	$(apaga).attr("type", "button");
	$(apaga).attr("onclick", "apagaSonho(" + sonho.id + ")");
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

function carregarComboMetas() {

	$.ajax({
		url : hostService + 'metas',
		type : 'GET',
		headers : {
			Accept : 'application/json'
		},
		success : function(data) {

			$('#combo-metas option').remove();
			if ($.isArray(data.metas.link)) {
				for (var i = 0; i < data.metas.link.length; i++) {
					var link = data.metas.link[i]['@href'];
					segueLinkMetaCombo(link);
				}
			} else {
				var link = data.metas.link['@href'];
				segueLinkMetaCombo(link);
			}

		},
		error : function(data) {
			alert("Erro na invocação");
		}
	});
}

function segueLinkMetaCombo(link) {
	
	$.ajax({
		url : hostService + link,
		type : 'GET',
		success : function(data) {
			adicionaMetaAoCombo(data.meta);
		},
		error : function(data) {
			alert("Ocorreu um erro");
		}
	});
}

function adicionaMetaAoCombo(meta) {
	var option = "<option value="+meta.id+">"+meta.descricao+"</option>";
	
	$("#combo-metas").append(option);
}