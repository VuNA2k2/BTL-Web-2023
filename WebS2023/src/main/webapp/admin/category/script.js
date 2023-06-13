const fetchURL = "https://localhost/WebS2023_war/api/categories";

function fetchList() {
    //clear the view
    const tbody = document.querySelector("#users-container tbody");
    tbody.innerHTML = "";
    fetch(fetchURL)
        .then(response => response.json())
        .then(data => {
            if (data.code === "success") {
                const categories = data.data;
                const tbody = document.querySelector("#users-container tbody");
                categories.sort((a, b) => a.id - b.id);
                categories.forEach(category => {
                    const row = document.createElement("tr");
                    const idCell = document.createElement("td");
                    idCell.textContent = category.id;
                    idCell.classList.add("mdl-data-table__cell--non-numeric", "align-start", "text-black"); // Add class for alignment
                    row.appendChild(idCell);

                    const nameCell = document.createElement("td");
                    nameCell.textContent = category.name;
                    nameCell.classList.add("mdl-data-table__cell--non-numeric", "align-start", "text-black"); // Add class for alignment
                    row.appendChild(nameCell);

                    const descriptionCell = document.createElement("td");
                    descriptionCell.textContent = category.description;
                    descriptionCell.classList.add("mdl-data-table__cell--non-numeric", "align-start", "text-black"); // Add class for alignment
                    row.appendChild(descriptionCell);

                    const detailsCell = document.createElement("td");
                    detailsCell.classList.add("align-start");

                    const detailsButton = $("<button>")
                        .text("Chi tiết")
                        .addClass("mdl-button mdl-js-button mdl-button--raised mdl-button--colored align-start")
                        .css({
                            "background-color": "green",
                            "border-radius": "4px",
                            "align-items": "start"
                        });
                    detailsButton.click(() => {
                        const editDialog = document.getElementById("edit-dialog");
                        const editNameInput = document.getElementById("edit-name-input");
                        const editDescriptionInput = document.getElementById("edit-description-input");
                        const editIdInput = document.getElementById("edit-id-input");

                        // Set the input field values to the selected category's data
                        editNameInput.value = category.name;
                        editDescriptionInput.value = category.description;
                        editIdInput.value = category.id;

                        // Show the edit dialog
                        editDialog.showModal();
                    });

                    detailsCell.appendChild(detailsButton[0]);
                    row.appendChild(detailsCell);

                    tbody.appendChild(row);
                });
            } else {
                alert("API request failed with error: " + data.message);
            }
        })
        .catch(error => {
            alert("API request failed with error: " + error.message);
        });

}
fetchList()

const createDialog = document.getElementById("create-dialog");

const showCreateDialogBtn = document.getElementById("show-create-dialog-btn");

showCreateDialogBtn.addEventListener("click", () => {
    createDialog.showModal();
});

const createDialogCloseBtn = createDialog.querySelector(".close");

createDialogCloseBtn.addEventListener("click", () => {
    createDialog.close();
});

const saveBtn = createDialog.querySelector("#save-btn");

saveBtn.addEventListener("click", () => {
    const createNameInput = document.getElementById("create-name-input").value;
    const createDescriptionInput = document.getElementById("create-description-input").value;

    // Validate the input
    if (createNameInput.trim() === "" || createDescriptionInput.trim() === "") {
        alert("Xin vui lòng nhập đủ thông tin!");
        return;
    }

    const newCategory = {
        name: createNameInput,
        description: createDescriptionInput
    };

    fetch(fetchURL, {
        method: 'POST', body: JSON.stringify(newCategory), headers: {
            'Content-Type': 'application/json', 'Accept': 'application/json',
            'Authorization': 'Bearer ' + getTokenFromCookie(),

        }
    })
        .then(response => response.json())
        .then(data => {
            if (data.code === "success") {
                fetchList()
                console.log("New category created successfully:", data.message);
                createDialog.close();
            } else {
                alert("API request failed with error: " + data.message);
            }
        })
        .catch(error => {
            alert("API request failed with error: " + data.message);
        });
});

const editDialog = document.getElementById("edit-dialog");
const updateBtn = document.getElementById("update-btn");
const deleteBtn = document.getElementById("delete-btn");
const closeBtn = editDialog.querySelector(".close");
updateBtn.addEventListener("click", () => {
    const editNameInput = document.getElementById("edit-name-input").value;
    const editDescriptionInput = document.getElementById("edit-description-input").value;
    const editIdInput = document.getElementById("edit-id-input").value;

    const updatedCategory = {
        name: editNameInput,
        description: editDescriptionInput
    };

    fetch(`${fetchURL}?id=${editIdInput}`, {
        method: 'PUT',
        body: JSON.stringify(updatedCategory),
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization': 'Bearer ' + getTokenFromCookie(),
        }
    })
        .then(response => response.json())
        .then(data => {
            if (data.code === "success") {
                fetchList();
                console.log("Category updated successfully:", data.message);
                editDialog.close();
            } else {
                alert("API request failed with error: " + data.message);
            }
        })
        .catch(error => {
            alert("API request failed with error: " + error.message);
        });
});

deleteBtn.addEventListener("click", () => {
    const editIdInput = document.getElementById("edit-id-input").value;

    if (confirm("Are you sure you want to delete this category?")) {
        fetch(`${fetchURL}?id=${editIdInput}`, {
            method: 'DELETE',
            headers: {
                'Authorization': 'Bearer ' + getTokenFromCookie(),
            }
        })
            .then(response => response.json())
            .then(data => {
                if (data.code === "success") {
                    fetchList();
                    console.log("Category deleted successfully:", data.message);
                    editDialog.close();
                } else {
                    alert("API request failed with error: " + data.message);
                }
            })
            .catch(error => {
                alert("API request failed with error: " + error.message);
            });
    }
});

closeBtn.addEventListener("click", () => {
    editDialog.close();
})

function getTokenFromCookie() {
    const cookie = document.cookie.split(';');
    const token = cookie[0].substring("token=".length, cookie[0].length);
    return token;
}
