import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayPrisons();
});

/**
 * Fetches all prisons and modifies the DOM tree in order to display them.
 */
function fetchAndDisplayPrisons() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayPrisons(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/prisons/', true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display prisons.
 *
 * @param {{prisons: string[]}} prisons
 */
function displayPrisons(prisons) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    prisons.prisons.forEach(prison => {
        tableBody.appendChild(createTableRow(prison));
    })
}

/**
 * Creates single table row for entity.
 *
 * @param {string} prison
 * @returns {HTMLTableRowElement}
 */
function createTableRow(prison) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(prison));
    tr.appendChild(createLinkCell('view', '../prison_view/prison_view.html?prison=' + prison));
    tr.appendChild(createButtonCell('delete', () => deletePrison(prison)));
    tr.appendChild(createLinkCell('add', '../add_prisoner/add_prisoner.html?prison=' + prison));
    return tr;
}

/**
 * Deletes entity from backend and reloads table.
 *
 * @param {string } prison to be deleted
 */
function deletePrison(prison) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayPrisons();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/prisons/' + prison, true);
    xhttp.send();
}
