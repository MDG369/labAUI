import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
});

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => addPrisoner(event));
});

function addPrisoner(event) {

    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            // fetchAndDisplayCharacter();
            console.log("Success!")
        }
    };
    xhttp.open("POST", getBackendUrl() + '/api/prisons/' + getParameterByName('prison') + '/prisoners/', true);

    const request = {
        'name': document.getElementById('name').value,
        'surname':  document.getElementById("surname").value,
        'age':  parseInt(document.getElementById("age").value),
        'cell_number': parseInt(document.getElementById("cell_number").value)
        
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
   
}

  
