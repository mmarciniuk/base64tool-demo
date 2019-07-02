function clearForm(elementId) {
    var form = document.getElementById(elementId);

    if (form.tagName === "FORM") {
        var elementsList = form.elements;

        for (i = 0; i < elementsList.length; i++) {
            if (elementsList[i].nodeName === "INPUT" && elementsList[i].type === "text") {
                elementsList[i].value = "";
            }
            if (elementsList[i].nodeName === "TEXTAREA") {
                elementsList[i].value = "";
            }
        }


    } else {
        console.error("Provided element is not a FORM tag");
    }
}