function toggleTheme() {
    const isDark = document.getElementById("themeToggle").checked;
    document.getElementById("darkTheme").disabled = !isDark;
}