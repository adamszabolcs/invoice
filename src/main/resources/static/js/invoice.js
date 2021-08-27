let invoice = {

    _invoiceId: null,

    setInvoiceId: function() {
        let urlArr = location.pathname.split("/");
        this._invoiceId = urlArr[urlArr.length - 1];
    },

    getInvoiceInfoAndCreateDom: function() {
        let invoiceItemInfoList = document.getElementById("invoice-info-list");
        fetch("/api/invoice/"+this._invoiceId)
        .then((response) => response.json())
        .then((invoiceData) => dom.createInvoiceItemPage(invoiceData, invoiceItemInfoList))
        .catch((error) => console.log(error));
    },

    getInvoiceItemInfoAndCreateDom: function() {
        let invoiceItemTable = document.getElementById("invoice-item-table");
        fetch("/api/invoice-items/"+this._invoiceId)
        .then((response) => response.json())
        .then((invoiceItemData) => dom.createInvoiceItemTable(invoiceItemData, invoiceItemTable))
        .catch((error) => console.log(error));
    }
};

function init() {
    exchangeRate.addToExchangeElement();
    invoice.setInvoiceId();
    invoice.getInvoiceInfoAndCreateDom();
    invoice.getInvoiceItemInfoAndCreateDom();
}

init();
