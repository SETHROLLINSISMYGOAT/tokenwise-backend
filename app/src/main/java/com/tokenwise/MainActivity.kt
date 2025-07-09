package com.tokenwise

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.File
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var buyCountText: TextView
    private lateinit var sellCountText: TextView
    private lateinit var marketDirectionText: TextView
    private lateinit var protocolStatsText: TextView
    private lateinit var walletListView: ListView
    private lateinit var exportButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        buyCountText = findViewById(R.id.buyCount)
        sellCountText = findViewById(R.id.sellCount)
        marketDirectionText = findViewById(R.id.marketDirection)
        protocolStatsText = findViewById(R.id.protocolStats)
        walletListView = findViewById(R.id.walletListView)
        exportButton = findViewById(R.id.exportButton)


        fetchDashboardData()


        exportButton.setOnClickListener {
            exportDashboardJson()
        }
    }

    private fun fetchDashboardData() {
        lifecycleScope.launch {
            try {
                val apiUrl = "https://tokenwise-backend-3.onrender.com/api/dashboard"
                val response = withContext(Dispatchers.IO) {
                    URL(apiUrl).readText()
                }

                val json = JSONObject(response)

                val buys = json.getInt("buys")
                val sells = json.getInt("sells")
                val direction = json.getString("market_trend")
                val protocolUsage = json.getJSONObject("protocol_usage")
                val wallets = json.getJSONArray("active_wallets")

                // Update UI on the main thread
                buyCountText.text = "Buys: $buys"
                sellCountText.text = "Sells: $sells"
                marketDirectionText.text = "Market Trend: $direction"

                val protoText = "Jupiter: ${protocolUsage.getInt("Jupiter")}, " +
                        "Raydium: ${protocolUsage.getInt("Raydium")}, " +
                        "Orca: ${protocolUsage.getInt("Orca")}"
                protocolStatsText.text = protoText

                val walletList = ArrayList<String>()
                for (i in 0 until wallets.length()) {
                    walletList.add(wallets.getString(i))
                }

                val adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, walletList)
                walletListView.adapter = adapter

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@MainActivity, "❌ Failed to load data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun exportDashboardJson() {
        lifecycleScope.launch {
            try {
                val exportUrl = "https://tokenwise-backend-3.onrender.com/api/export"
                val exportedJson = withContext(Dispatchers.IO) {
                    URL(exportUrl).readText()
                }

                val filename = "dashboard_export.json"
                withContext(Dispatchers.IO) {
                    openFileOutput(filename, MODE_PRIVATE).use {
                        it.write(exportedJson.toByteArray())
                    }
                }

                val filePath = File(filesDir, filename).absolutePath
                Toast.makeText(this@MainActivity, "✅ Exported to: $filePath", Toast.LENGTH_LONG).show()

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@MainActivity, "❌ Export failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
