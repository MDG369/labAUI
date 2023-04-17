import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    createImageCell,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayPrison();
    fetchAndDisplayPrisoners();
});

/**
 * Fetches all prisons and modifies the DOM tree in order to display them.
 */
function fetchAndDisplayPrisoners() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayPrisoners(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/prisons/' + getParameterByName('prison') + '/prisoners', true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display prisoners.
 *
 * @param {{prisoners: {id: number, name:string}[]}} prisoners
 */
function displayPrisoners(prisoners) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    prisoners.prisoners.forEach(prisoner => {
        tableBody.appendChild(createTableRow(prisoner));
    })
}

/**
 * Creates single table row for entity.
 *
 * @param {{id: number, name: string}} prisoner
 * @returns {HTMLTableRowElement}
 */
function createTableRow(prisoner) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(prisoner.name));
    tr.appendChild(createLinkCell('edit', '../prisoner_edit/prisoner_edit.html?prison='
        + getParameterByName('prison') + '&prisoner=' + prisoner.id));
    tr.appendChild(createButtonCell('delete', () => deletePrisoner(prisoner.id)));
    return tr;
}

/**
 * Deletes entity from backend and reloads table.
 *
 * @param {number} prisoner to be deleted
 */
function deletePrisoner(prisoner) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayPrisoners();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/prisons/' + getParameterByName('prison')
        + '/prisoners/' + prisoner, true);
    xhttp.send();
}


/**
 * Fetches single prison and modifies the DOM tree in order to display it.
 */
function fetchAndDisplayPrison() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayprison(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/prisons/' + getParameterByName('prison'), true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display prison.
 *
 * @param {{name: string, size: int}} prison
 */
function displayprison(prison) {
    setTextNode('name', prison.name);
    setTextNode('size', prison.size);
}
