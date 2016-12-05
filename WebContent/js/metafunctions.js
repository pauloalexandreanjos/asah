var hostService = "http://" + window.location.host + "/asah/services/"

function atualizaMeta(data) {
	id = data.id;
	data = "{\"meta\":" + JSON.stringify(data) + "}";
	$.ajax({
		url : hostService + 'metas/' + id,
		type : 'PUT',
		contentType : 'application/json',
		data : data,
		success : function(data) {
			alert("Incluído com sucesso!");
			$("#metaForm")[0].reset();
			listarMetas();
		},
		error : function(data) {
			console.log(data);
			alert("Ocorreu um erro: " + data.status + " " + data.statusText);
		}
	});
}

function criaMeta(data) {
	data = "{\"meta\":" + JSON.stringify(data) + "}";
	$.ajax({
		url : hostService + 'metas',
		type : 'POST',
		contentType : 'application/json',
		data : data,
		success : function(data) {
			alert("Incluído com sucesso!");
			$("#metaForm")[0].reset();
			listarMetas();
		},
		error : function(data) {
			console.log(data);
			alert("Ocorreu um erro: " + data.status + " " + data.statusText);
		}
	});
}

function adicionaMeta() {
	var data = $("#metaForm").serializeJSON();

	if (data.id) {
		atualizaMeta(data);
	} else {
		criaMeta(data);
	}
}

function listarMetas() {

	$.ajax({
		url : hostService + 'metas',
		type : 'GET',
		headers : {
			Accept : 'application/json'
		},
		success : function(data) {

			$('#grid tr:gt(0)').remove();
			if ($.isArray(data.metas.link)) {
				for (var i = 0; i < data.metas.link.length; i++) {
					var link = data.metas.link[i]['@href'];
					segueLinkMeta(link);
				}
			} else {
				var link = data.metas.link['@href'];
				segueLinkMeta(link);
			}

		},
		error : function(data) {
			alert("Erro na invocação");
		}
	});
}

function segueLinkMeta(link) {
	$.ajax({
		url : hostService + link,
		type : 'GET',
		success : function(data) {
			adicionaMetaNovaAoGrid(data.meta);
		},
		error : function(data) {
			alert("Ocorreu um erro");
		}
	});
}

function apagaMeta(id) {
	$.ajax({
		url : hostService + 'metas/' + id,
		type : 'DELETE',
		success : function(data) {
			listarMetas();
		},
		error : function(data) {
			alert("Ocorreu um erro: " + data.status + " " + data.statusText);
		}
	});
}

function carregaMeta(id) {
	$.ajax({
		url : hostService + 'metas/' + id,
		type : 'GET',
		success : function(data) {
			var frm = $("#metaForm");
			$.each(data.meta, function(key, value) {
				$('[name=' + key + ']', frm).val(value);
			});
		},
		error : function(data) {
			alert("Ocorreu um erro: " + data.status + " " + data.statusText);
		}
	});
}

function adicionaMetaNovaAoGrid(meta) {

	var lin = document.createElement("tr");

	var col1 = document.createElement("td");
	col1.innerText = meta.id

	var col2 = document.createElement("td");
	col2.innerText = meta.valor

	var col3 = document.createElement("td");
	col3.innerText = meta.descricao

	var col4 = document.createElement("td");
	var edita = document.createElement("input");
	$(edita).attr("type", "button");
	$(edita).attr("onclick", "carregaMeta(" + meta.id + ")");
	$(edita).val("Editar")

	var apaga = document.createElement("input");
	$(apaga).attr("type", "button");
	$(apaga).attr("onclick", "apagaMeta(" + meta.id + ")");
	$(apaga).val("Apagar")

	col4.append(edita);
	col4.append(apaga);

	lin.append(col1);
	lin.append(col2);
	lin.append(col3);
	lin.append(col4);

	$("#grid").append(lin);
}