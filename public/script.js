function showAlert() {
    alert("Hello from JavaScript!");
}

// Function to close Chrome Custom Tab
function closeTab() {
    window.close();
}

function sendDataToApp() {
    const data = "HelloFromWeb"; // Example data to send
    window.location.href = `myapp://receivedata?message=${encodeURIComponent(data)}`;
}
