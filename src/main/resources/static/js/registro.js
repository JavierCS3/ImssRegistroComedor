document.addEventListener("DOMContentLoaded", () => {

    const formulario =
        document.getElementById("registroForm");

    formulario.addEventListener("submit", registrarEntrada);

});

function registrarEntrada(e){

    e.preventDefault();

    const nombre =
        document.getElementById("nombreEmpleado")
            .value
            .trim();

    const empleadoId =
        document.getElementById("empleadoId")
            .value
            .trim();

    const mensaje =
        document.getElementById("mensaje");

    if(nombre === "" || empleadoId === ""){

        mensaje.className = "mensaje error";

        mensaje.innerHTML =
            "Debe completar todos los campos.";

        return;
    }

    fetch("/registrar",{
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify({
            nombre:nombre,
            empleadoId:empleadoId
        })
    })
        .then(response => {

            if(response.ok){

                mensaje.className =
                    "mensaje success";

                mensaje.innerHTML =
                    "✓ Entrada registrada correctamente";

                document
                    .getElementById("registroForm")
                    .reset();

            }else{

                mensaje.className =
                    "mensaje error";

                mensaje.innerHTML =
                    "✗ No fue posible registrar la entrada";
            }

        })
        .catch(() => {

            mensaje.className =
                "mensaje error";

            mensaje.innerHTML =
                "✗ Error de conexión";

        });

}