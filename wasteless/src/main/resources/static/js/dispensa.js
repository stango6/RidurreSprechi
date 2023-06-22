document.getElementById("modalNewItem").onclick = function(){
	document.getElementById("modal-add").style.display = "block";
}

document.getElementById("closeAdd").onclick = function() {
	document.getElementById("modal-add").style.display = "none";
}

document.getElementById("closeMod").onclick = function(){
	document.getElementById("modal-mod").style.display = "none";
}

document.getElementById("closeSell").onclick = function(){
    document.getElementById("modal-sell").style.display = "none";
}

window.onclick = function(event){
    if(event.target == document.getElementById("modal-mod"))
        document.getElementById("modal-mod").style.display = "none";
    if(event.target == document.getElementById("modal-add"))
        document.getElementById("modal-add").style.display = "none";
    if(event.target == document.getElementById("modal-sell"))
        document.getElementById("modal-sell").style.display = "none";
}

function modProdotto(element) {
    document.getElementById("modal-mod").style.display = "block";

    document.getElementById("modId").value = element.getAttribute('data-id');
    document.getElementById("modNome").value = element.getAttribute('data-nome');
    document.getElementById("modDataScadenza").value = element.getAttribute('data-dataScadenza');
    document.getElementById("modQuantita").value = element.getAttribute('data-quantita');
    document.getElementById("modDescrizione").value = element.getAttribute('data-descrizione');
    document.getElementById("modPrezzo").value = element.getAttribute('data-prezzo');
    document.getElementById("modAlimento").checked = element.getAttribute('data-alimento');
    document.getElementById("modStato").value = element.getAttribute('data-stato');
    document.getElementById("modIdUtente").value = element.getAttribute('data-idUtente');
}

function showEdibleProducts() {
    console.log("sono dentro la function");
    // Ottieni la tabella dei prodotti
    var table = document.getElementById("elenco");
    console.log(table);

    // Verifica se la tabella è già filtrata
    var isFiltered = table.getAttribute("data-filtered");

    // Se la tabella è già filtrata, ripristina la visualizzazione originale
    if (isFiltered === "true") {
        resetTable(table);
    } else {
        // Itera attraverso le righe della tabella
        for (var i = 0; i < table.rows.length; i++) {
            var row = table.rows[i];
            console.log(row);
            // Ottieni lo stato del prodotto nella colonna corrispondente
            var stato = row.cells[7].innerText.toLowerCase();

            // Nascondi o mostra la riga in base allo stato del prodotto
            if (stato === "edibile" || stato === "in scadenza") {
                row.style.display = "table-row";
            } else {
                row.style.display = "none";
            }
        }

        // Aggiungi l'attributo "data-filtered" per indicare che la tabella è stata filtrata
        table.setAttribute("data-filtered", "true");
    }
}

function resetTable(table) {
    // Itera attraverso le righe della tabella
    for (var i = 0; i < table.rows.length; i++) {
        var row = table.rows[i];
        // Ripristina la visualizzazione originale di tutte le righe
        row.style.display = "table-row";
    }

    // Rimuovi l'attributo "data-filtered" per indicare che la tabella non è più filtrata
    table.removeAttribute("data-filtered");
}


    var addDataScadenzaInput = document.getElementById("addDataScadenza");

    // Ottieni la data corrente in formato "YYYY-MM-DD"
    var dataCorrente = new Date().toISOString().split("T")[0];

    // Imposta il valore di "min" sull'elemento dell'input della data di scadenza
    addDataScadenzaInput.setAttribute("min", dataCorrente);

    var modDataScadenzaInput = document.getElementById("modDataScadenza");


    // Imposta il valore di "min" sull'elemento dell'input della data di scadenza
    modDataScadenzaInput.setAttribute("min", dataCorrente);


  let isSorted = false;

  function sortProductsByExpirationDate() {
      const table = document.getElementById("elenco");
      const rows = Array.from(table.getElementsByTagName("tr"));
      const sortButton = document.getElementById("sortButton");

      if (isSorted) {
          rows.sort((row1, row2) => {
              const date1 = new Date(row1.getElementsByTagName("td")[2].textContent);
              const date2 = new Date(row2.getElementsByTagName("td")[2].textContent);
              return date1 - date2;
          });
          isSorted = false;
          sortButton.innerHTML = "▲";
      } else {
          rows.sort((row1, row2) => {
              const date1 = new Date(row1.getElementsByTagName("td")[2].textContent);
              const date2 = new Date(row2.getElementsByTagName("td")[2].textContent);
              return date2 - date1; // Ordine inverso per ordinamento decrescente
          });
          isSorted = true;
          sortButton.innerHTML = "▼";
      }

      rows.forEach(row => table.appendChild(row));
  }

function vendiProdotto(element) {
    document.getElementById("modal-sell").style.display = "block";

    document.getElementById("sellId").value = element.getAttribute('data-id');
    document.getElementById("sellNome").value = element.getAttribute('data-nome');
    document.getElementById("sellDataScadenza").value = element.getAttribute('data-dataScadenza');
    document.getElementById("sellQuantita").value = element.getAttribute('data-quantita');
    document.getElementById("sellDescrizione").value = element.getAttribute('data-descrizione');
    document.getElementById("sellPrezzo").value = element.getAttribute('data-prezzo');
    document.getElementById("sellAlimento").checked = element.getAttribute('data-alimento');
    document.getElementById("sellStato").value = element.getAttribute('data-stato');
    document.getElementById("sellIdUtente").value = element.getAttribute('data-idUtente');

}

function showExpiredProducts() {
    console.log("sono dentro la function");
    // Ottieni la tabella dei prodotti
    var table = document.getElementById("elenco");
    console.log(table);

    // Verifica se la tabella è già filtrata
    var isFiltered = table.getAttribute("data-filtered");

    // Se la tabella è già filtrata, ripristina la visualizzazione originale
    if (isFiltered === "true") {
        resetTable(table);
    } else {
        // Itera attraverso le righe della tabella
        for (var i = 0; i < table.rows.length; i++) {
            var row = table.rows[i];
            console.log(row);
            // Ottieni lo stato del prodotto nella colonna corrispondente
            var stato = row.cells[7].innerText.toLowerCase();

            // Nascondi o mostra la riga in base allo stato del prodotto
            if (stato === "scaduto") {
                row.style.display = "table-row";
            } else {
                row.style.display = "none";
            }
        }

        // Aggiungi l'attributo "data-filtered" per indicare che la tabella è stata filtrata
        table.setAttribute("data-filtered", "true");
    }

    function searchProducts() {
        var searchValue = document.getElementById("searchInput").value.toLowerCase();
        var tableRows = document.getElementsByTagName("tbody")[0].getElementsByTagName("tr");

        for (var i = 0; i < tableRows.length; i++) {
            var productName = tableRows[i].getElementsByTagName("td")[1].textContent.toLowerCase();

            if (productName.includes(searchValue)) {
                tableRows[i].style.display = "";
            } else {
                tableRows[i].style.display = "none";
            }
        }
    }

    function clearSearch() {
        var tableRows = document.getElementsByTagName("tbody")[0].getElementsByTagName("tr");

        for (var i = 0; i < tableRows.length; i++) {
            tableRows[i].style.display = "";
        }
        document.getElementById("searchInput").value = "";
    }

}