var numberButtons = document.getElementsByClassName("numberButton");
var inputScreen = document.getElementById("inputFromCalculator");

// Copy, copys the result screen, to the math screen.
function copy() {
    inputScreen.value += document.getElementById("result").innerText;
}

// Adds the Listener to each Buttons value.
function addListeners() {
    for (var i = 0; i < numberButtons.length; i++) {
        setEventListener(numberButtons[i].value, numberButtons[i]);
    }
}

// Sets listeners value.
function setEventListener(input, button) {
    button.addEventListener('click', function () {
        inputScreen.value += input;
    })
}

// MAIN
function main() {
    addListeners();
    document.getElementById("buttonCopy").addEventListener('click', function () {
        copy();
    });
}

main();
