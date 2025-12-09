const api = "/api";

function showTab(id) {
    document.querySelectorAll(".tab").forEach(b => b.classList.remove("active"));
    document.querySelectorAll(".tab-content").forEach(c => c.classList.remove("active"));

    event.target.classList.add("active");
    document.getElementById(id).classList.add("active");
}

/* ТОВАРЫ */

async function createProduct() {
    const body = {
        name: pname.value,
        size: parseInt(psize.value),
        price: parseFloat(pprice.value),
        stock: parseInt(pstock.value)
    };

    await fetch(api + "/products", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(body)
    });

    loadProducts();
}

async function loadProducts() {
    const data = await fetch(api + "/products").then(r => r.json());
    let html =
        `<table>
            <tr>
                <th>ID</th><th>Название</th><th>Размер</th><th>Цена</th><th>Остаток</th><th></th>
            </tr>`;

    data.forEach(p => {
        html += `
            <tr>
                <td>${p.id}</td>
                <td>${p.name}</td>
                <td>${p.size}</td>
                <td>${p.price}</td>
                <td>${p.stock}</td>
                <td>
                    <button class="danger-btn" onclick="deleteProduct('${p.id}')">Удалить</button>
                </td>
            </tr>`;
    });

    html += "</table>";
    products.innerHTML = html;
}

/* ПРОДАЖИ */

async function makeSale() {
    const body = {
        productId: sproduct.value,
        quantity: parseInt(squantity.value)
    };

    const res = await fetch(api + "/sales", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(body)
    });

    if (!res.ok) alert("Ошибка: недостаточно товара");

    loadProducts();
    loadSales();
}

async function loadSales() {
    const data = await fetch(api + "/sales").then(r => r.json());
    let html =
        `<table>
            <tr>
                <th>ID</th><th>Товар</th><th>Кол-во</th><th>Сумма</th><th>Дата</th>
            </tr>`;

    data.forEach(s => {
        html += `
            <tr>
                <td>${s.id}</td>
                <td>${s.productId}</td>
                <td>${s.quantity}</td>
                <td>${s.totalPrice}</td>
                <td>${new Date(s.timestamp).toLocaleString()}</td>
            </tr>`;
    });

    html += "</table>";
    sales.innerHTML = html;
}

async function clearSales() {
    await fetch(api + "/sales", { method: "DELETE" });
    loadSales();
}

/* Удаление товара */

async function deleteProduct(id) {
    await fetch(api + "/products/" + id, { method: "DELETE" });
    loadProducts();
}

loadProducts();
loadSales();