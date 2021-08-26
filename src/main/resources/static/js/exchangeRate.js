let exchangeRate = {

    addToExchangeElement: function() {
        let exchangeIdElement = document.getElementById("exchange");
        fetch("/api/get-exchange-rate").then(function(response) {
            return response.json();
        }).then(function(data) {
            exchangeIdElement.innerHTML += data + " HUF";
        })
    }

}