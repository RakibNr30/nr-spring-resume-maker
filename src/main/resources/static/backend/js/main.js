document.addEventListener('DOMContentLoaded', () => {
    const requiredElements = document.querySelectorAll('[required]');

    requiredElements.forEach(function (requiredElement) {
        const label = document.querySelector('label[for="' + requiredElement.id + '"]');
        label.innerHTML += '<span style="color: red;"> *</span>';
    });

    const toggleSection = (formId, btnId) => {
        const form = document.getElementById(formId);
        const btn = document.getElementById(btnId);
        const isHidden = form.classList.toggle("d-none");
        btn.innerHTML = isHidden ? "<i class=\"fa fa-plus\"></i> Add" : "<i class=\"fa fa-times\"></i> Close";
        btn.classList.toggle("btn-primary", isHidden);
        btn.classList.toggle("btn-danger", !isHidden);
    }

    const addBtn = document.getElementById("add-btn");
    addBtn.addEventListener("click", () => {
        toggleSection("add-form", "add-btn");
    });
});