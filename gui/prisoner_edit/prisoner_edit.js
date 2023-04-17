import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplayPrisoner();
});

/**
 * Fetches currently logged prison's prisoners and updates edit form.
 */
function fetchAndDisplayPrisoner() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/prisons/' + getParameterByName('prison') + '/prisoners/'
        + getParameterByName('prisoner'), true);
    xhttp.send();
}

/**
 * Action event handled for updating prisoner info.
 * @param {Event} event dom event
 */
function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayPrisoner();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/prisons/' + getParameterByName('prison') + '/prisoners/'
        + getParameterByName('prisoner'), true);

    const request = {
        'name': document.getElementById('name').value,
        'surname': document.getElementById('surname').value,
        'age': parseInt(document.getElementById('age').value),
        'cell_number': parseInt(document.getElementById('cell_number').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}


