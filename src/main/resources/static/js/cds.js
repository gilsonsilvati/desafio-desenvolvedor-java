$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	
	var codigoPessoa = button.data('codigo');
	var nomePessoa = button.data('nome');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	
	if (!action.endsWith('/')) {
		action += '/';
	}
	
	form.attr('action', action + codigoPessoa);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir a pessoa <strong>' + nomePessoa + '</strong>?');
});

$(function() {
	$('[rel="tooltip"]').tooltip();
	
	$('.js-atualizar-status').on('click', function(event) {
		event.preventDefault();
		
		var botaoAtivar = $(event.currentTarget);
		var urlAtivar = botaoAtivar.attr('href');
		
		var response = $.ajax({
			url: urlAtivar,
			type: 'PUT'
		});
		
		response.done(function(e) {
			var codigoPessoa = botaoAtivar.data('codigo');
			$('[data-role=' + codigoPessoa + ']').html('<span class="label label-success">' + e + '</span>');
			botaoAtivar.hide();
		});
		
		response.fail(function(e) {
			console.log(e);
			alert('Erro ativando pessoa');
		});
		
	});
});