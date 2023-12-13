document.addEventListener('DOMContentLoaded', function () {
    fetchData();
});

function fetchData() {
    // Replace this API endpoint with your actual API endpoint
    fetch('https://jsonplaceholder.typicode.com/users')
        .then(response => response.json())
        .then(data => {
            renderTable(data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function renderTable(data) {
    const tableBody = document.getElementById('tableBody');

    // Clear existing rows
    tableBody.innerHTML = '';

    data.forEach(item => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.email}</td>
            <td>${item.phone}</td>
            <td>${item.address.city}</td>
            <td>${item.address.country}</td>
            <td>${item.company.name}</td>
        `;
        tableBody.appendChild(row);
    });
}
