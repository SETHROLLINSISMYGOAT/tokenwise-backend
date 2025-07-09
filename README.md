# 📱 Tokenwise

**Tokenwise** is an Android app that connects to a real-time crypto market dashboard, powered by the [Tokenwise Backend](https://github.com/SETHROLLINSISMYGOAT/tokenwise-backend.git).  
It displays live trading metrics, active wallets, and protocol usage statistics.

---

## 🚀 Features

- 📊 Displays live **buy/sell** counts
- 🔁 Shows **market trend direction**
- 🧠 Visualizes **protocol usage** (Jupiter, Raydium, Orca)
- 👛 Lists **active wallets**
- 📁 Allows **exporting dashboard data** to local storage as JSON

---

## 🛠️ Build Instructions

1. Clone this project:
   ```bash
   git clone https://github.com/SETHROLLINSISMYGOAT/tokenwise-backend.git
   cd tokenwise-backend
   Open the Android project in Android Studio.

Let Gradle sync automatically.

Click Run (▶️) to build and launch on a device or emulator.

📦 Tech Stack
Kotlin (Android)

REST API with JSON parsing

StrictMode (temporary for network access)

org.json.JSONObject

Native UI components (ListView, TextView, etc.)

🌐 API Endpoints
Endpoint	Description
/api/dashboard	Returns live buy/sell data, trend, protocol usage, wallets
/api/export	Exports full dashboard JSON snapshot

Base URL: https://tokenwise-backend-3.onrender.com/api/dashboard

🤝 Contributing
Contributions are welcome!
Feel free to fork the repo and open pull requests.

📄 License
MIT License. See LICENSE file for details.

📸 Screenshots
Coming soon...

📍 Repository Links
Frontend (Android app) – You are here

Backend – github.com/SETHROLLINSISMYGOAT/tokenwise-backend
