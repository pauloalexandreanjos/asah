var host = "http://" + window.location.host + "/asah/sistema/"

var addFormControl = function() {
	$("input").each(function(ele, val) {
		$(val).addClass("form-control");
	});
};

var addClickNavBar = function() {
	$("#nav-relatorio").click(loadPag);
	$("#nav-renda").click(loadPag);
	$("#nav-gasto").click(loadPag);
	$("#nav-sonho").click(loadPag);
	$("#nav-meta").click(loadPag);
};

var loadNav = function() {

	$.ajax({
		url : host + 'menu.html?' + Math.random(),
		type : 'GET',
		success : function(data) {
			$('#nav-ajax').html(data);
			addClickNavBar();
		},
		error : function(data) {
			alert('Página não encontrada!');
		}
	});

};

var loadPag = function() {
	$.ajax({
		url : host + $(this).attr("page") + '.html',
		type : 'GET',
		success : function(data, status, jqXHR) {

			if (status == "302") {
				window.location.assign();
			}

			$('#content-pane').html(data);
			addFormControl();
		},
		error : function(data) {
			alert('Página não encontrada!');
		},
		statusCode : {
			302 : function() {
				alert("page not found");
			},
			404 : function() {
				alert("page not found");
			}
		}
	});
};

$(document).ready(function() {
	loadNav();
});