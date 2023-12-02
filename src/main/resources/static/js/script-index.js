$(document).ready(function() {
    // Certifique-se de que o Bootstrap manipule os eventos de dropdown
    // Se você quiser adicionar alguma lógica adicional, pode fazê-lo aqui
    // Por exemplo, se você quiser fazer algo quando um dropdown é mostrado ou escondido, você pode usar:
    $('#cadastrarDropdown').on('show.bs.dropdown', function () {
        // Código para executar quando o dropdown de cadastrar é mostrado
    });

    $('#cadastrarDropdown').on('hide.bs.dropdown', function () {
        // Código para executar quando o dropdown de cadastrar é escondido
    });

    $('#listarDropdown').on('show.bs.dropdown', function () {
        // Código para executar quando o dropdown de listar é mostrado
    });

    $('#listarDropdown').on('hide.bs.dropdown', function () {
        // Código para executar quando o dropdown de listar é escondido
    });

    // Se você não precisa de lógica adicional, você pode remover este código
});

