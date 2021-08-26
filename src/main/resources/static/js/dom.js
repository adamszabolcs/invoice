let dom = {

    createInvoiceTable: function(invoiceData, invoiceTableBody) {
        for (let i = 0; i < invoiceData.length; i++) {
            invoiceTableBody.innerHTML += `<tr class="table-row" id=${invoiceData[i].id}>
                                            <td><a href="/invoice/${invoiceData[i].id}">${invoiceData[i].customerName}</a></td>
                                            <td>${invoiceData[i].issueDate}</td>
                                            <td>${invoiceData[i].dueDate}</td>
                                            <td>${invoiceData[i].userComment === null ? "-" : invoiceData[i].userComment}</td>
                                            <td>${invoiceData[i].invoiceTotal} HUF</td>
                                            <td>${invoiceData[i].invoiceTotalInEUR} EUR</td>
                                            </tr>`;
        }
    },

    createInvoiceItemPage: function(invoiceData, invoiceItemInfoList) {
        invoiceItemInfoList.innerHTML += `<li><b>Customer name: </b>${invoiceData.customerName}</li>
                                            <li><b>Issue date: </b>${invoiceData.issueDate}</li>
                                            <li><b>Due date: </b>${invoiceData.dueDate}</li>
                                            <li><b>Comment: </b>${invoiceData.userComment === null ? "-" : invoiceData.userComment}</li>
                                            <li><b>Invoice total: </b>${invoiceData.invoiceTotal} HUF </li>
                                            <li><b>Invoice total in EUR: </b>${invoiceData.invoiceTotalInEUR} EUR</li>`
    },

    createInvoiceItemTable: function(invoiceItemData, invoiceItemTable) {
        for (let i = 0; i < invoiceItemData.length; i++) {
            invoiceItemTable.innerHTML += `<tr class="table-row">
                                            <td>${invoiceItemData[i].product.name}</td>
                                            <td>${invoiceItemData[i].product.unitPrice} HUF</td>
                                            <td>${invoiceItemData[i].quantity}</td>
                                            <td>${invoiceItemData[i].totalPrice} HUF</td>
                                            <td>${invoiceItemData[i].totalPriceInEUR} EUR</td>
                                            </tr>`;
        }  
    }

};