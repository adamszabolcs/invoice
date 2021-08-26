let invoices = {
    getInvoicesAndCreateDom: function() {
        let invoiceTableBody = document.getElementById("invoice-table");
        fetch("/api/invoices").then(function(response) {
            return response.json();
        }).then(function(invoiceData) {
            dom.createInvoiceTable(invoiceData, invoiceTableBody);
        })
    }
};

function init() {
    exchangeRate.addToExchangeElement();
    invoices.getInvoicesAndCreateDom();
}

init();

