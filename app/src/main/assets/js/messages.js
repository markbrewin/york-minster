function showToast(msg){
    console.log(msg);
    'use strict';

    var snackbarContainer = document.querySelector('#toast');

    var data = {message: "WIKITUDEWIKITUDE - " + msg};
    snackbarContainer.MaterialSnackbar.showSnackbar(data);
}

function openInfoCard(title, info) {
    document.getElementById('infoCard').style.display = "inherit";
    document.getElementById('infoCardTitle').innerHTML = title;
    document.getElementById('infoCardInfo').innerHTML = info;
}

function closeInfoCard() {
    document.getElementById('infoCard').style.display = "none";
}