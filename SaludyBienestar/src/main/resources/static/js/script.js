function openModal() {
    $.get("/salud-bienestar/modal-content", function(data) {
        $("#modalContainer").html(data);
        $("#myModal").modal("show");
    });

}

function closeModal() {
    $("#myModal").modal("hide");
    $("#modalContainer").empty();
   $.get("/salud-bienestar/inicio")
        .done(function(response) {
            // Redirigir a la URL deseada
            window.location.href = "/salud-bienestar/inicio";
        })
        .fail(function(xhr, status, error) {
            // Manejar errores si es necesario
            console.error("Error al realizar la solicitud GET:", error);
        }); $.get("/salud-bienestar/inicio")
}