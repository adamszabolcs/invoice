let invoices = {
    getInvoicesAndCreateDom: function() {
        let invoiceTableBody = document.getElementById("invoice-table");
        fetch("/api/invoices")
            .then((response) => response.json())
            .then((invoiceData) => dom.createInvoiceTable(invoiceData, invoiceTableBody))
            .catch((error) => console.log(error));
    }
};

function init() {
    exchangeRate.addToExchangeElement();
    invoices.getInvoicesAndCreateDom();
}

init();

