from flask import Flask, jsonify
from flask_cors import CORS

app = Flask(__name__)
CORS(app)

@app.route('/api/dashboard', methods=['GET'])
def get_dashboard_data():
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
    print("Sending mock dashboard data...")
    return jsonify(data)

print("✅ Flask app loaded successfully")

if __name__ == '__main__':
    print("🚀 Starting Flask development server...")
    app.run(debug=True, port=5000)
