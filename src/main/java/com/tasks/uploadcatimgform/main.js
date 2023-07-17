var myFormData = new FormData();

function uploadMichiPhoto() {

    var api_url = document.getElementById("urlreq").value.toString()
    var api_key = document.getElementById("apikey").value.toString()

    // get the inputted
    var file_input = document.getElementById("file")
    var sub_id_input = document.getElementById("sub_id")
    // add them to the FormData object
    myFormData.append('file', file_input.files[0]);
    myFormData.append('sub_id', sub_id_input.value);

    var xmlhttp = new XMLHttpRequest;

    xmlhttp.onreadystatechange = function() {
      if (xmlhttp.status == 201) {
        updateResponseOutput(xmlhttp.responseText);
        updateResponseOutput2(xmlhttp.status);
      } else if (xmlhttp.status == 400) {
        updateResponseOutput("Error: " + xmlhttp.responseText);
        updateResponseOutput2(xmlhttp.status);
      } else {
        updateResponseOutput(xmlhttp.responseText);
        updateResponseOutput2(xmlhttp.status);
      }

    };
    xmlhttp.onload = function() {
      updateStatusOutput("Uploaded");
    };

    xmlhttp.open('POST', api_url, true);

    // add your API key to the request header - this is needed to authorise an upload
    xmlhttp.setRequestHeader('x-api-key', api_key);
    xmlhttp.send(myFormData);
    updateStatusOutput("Uploading...");
}

    function updateStatusOutput(msg) {
        document.getElementById("status").innerHTML = msg;
    }

    function updateResponseOutput(msg) {
        document.getElementById("response").innerHTML = msg;
    }

    function updateResponseOutput2(msg) {
            document.getElementById("statuscode").value = msg;
        }

    var form = document.getElementById("upload_form");
    form.addEventListener("submit", onUploadBtnClick, true);
