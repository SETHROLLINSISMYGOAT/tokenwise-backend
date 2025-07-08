import os
from flask import Flask, jsonify, send_file
import json

app = Flask(__name__)

@app.route("/api/dashboard")
def dashboard():
    data = {
        "buys": 36,
        "sells": 19,
        "market_trend": "Buy-heavy",
        "protocol_usage": {
            "Jupiter": 23,
            "Raydium": 18,
            "Orca": 14
        },
        "active_wallets": [
            "6AxY...LMno",
            "9BB6...ZqFa",
            "3CcY...Pqt1"
        ]
    }
    return jsonify(data)

@app.route("/api/export")
def export_data():
    data = {
        "buys": 36,
        "sells": 19,
        "market_trend": "Buy-heavy",
        "protocol_usage": {
            "Jupiter": 23,
            "Raydium": 18,
            "Orca": 14
        },
        "active_wallets": [
            "6AxY...LMno",
            "9BB6...ZqFa",
            "3CcY...Pqt1"
        ]
    }
    with open("dashboard_export.json", "w") as f:
        json.dump(data, f)
    return send_file("dashboard_export.json", as_attachment=True)

if __name__ == "__main__":
    port = int(os.environ.get("PORT", 5000))
    print(f"✅ Flask app running on port {port}")
    app.run(host="0.0.0.0", port=port, debug=False)
