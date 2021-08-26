let invoice = {

    _invoiceId: null,

    setInvoiceId: function() {
        let urlArr = window.location.href.split("/");
        this._invoiceId = urlArr[urlArr.length - 1];
    },

    getInvoiceInfoAndCreateDom: function() {
        let invoiceItemInfoList = document.getElementById("invoice-info-list");
        fetch("/api/invoice/"+this._invoiceId).then(function(response) {
            return response.json();
        }).then(function(invoiceData) {
            console.log(invoiceData);
            dom.createInvoiceItemPage(invoiceData, invoiceItemInfoList);
        })
    },

    getInvoiceItemInfoAndCreateDom: function() {
        let invoiceItemTable = document.getElementById("invoice-item-table");
        fetch("/api/invoice-items/"+this._invoiceId).then(function(response) {
            return response.json();
        }).then(function(invoiceItemData) {
            dom.createInvoiceItemTable(invoiceItemData, invoiceItemTable);
        })
    }
};

function init() {
    exchangeRate.addToExchangeElement();
    invoice.setInvoiceId();
    invoice.getInvoiceInfoAndCreateDom();
    invoice.getInvoiceItemInfoAndCreateDom();
}

init();
