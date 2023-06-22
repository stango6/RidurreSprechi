function handleRuoloChange() {
        var ruoloSelect = document.getElementById("ruolo");
        var nomeLabel = document.getElementById("labelNome");

        if (ruoloSelect.value === "azienda") {
            nomeLabel.innerText = "Nome azienda";
            document.getElementById("cognome").value = "pippo";
            document.getElementById("cognomeField").style.display = "none";

        } else {
            nomeLabel.innerText = "Nome";
            document.getElementById("cognomeField").style.display = "block";
            document.getElementById("cognome").value = "";
        }
}