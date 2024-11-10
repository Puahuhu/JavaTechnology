function resetFilters() {
    document.getElementById("category").selectedIndex = 0;
    document.getElementById("genre").selectedIndex = 0;
    document.getElementById("studio").selectedIndex = 0;

    document.getElementById("priceMin").value = 0;
    document.getElementById("priceMax").value = 0;
}
