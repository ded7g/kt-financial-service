package mate.academy

class FinancialService {
    fun transferFunds(
        source: AccountNumber,
        destination: AccountNumber,
        amount: CurrencyAmount,
        currencyCode: CurrencyCode,
        transactionId: TransactionId
    ) : String {
        return "Transferred ${amount.amount} " +
                "${currencyCode.code} from ${source.value} to ${destination.value}. " +
                "Transaction ID: ${transactionId.value}"
    }

    fun convertCurrency(
        amount: CurrencyAmount,
        fromCurrency: CurrencyCode,
        toCurrency: CurrencyCode
    ): CurrencyAmount {
        val exchange = getExchangeRate(fromCurrency, toCurrency)
        val result = amount.amount * exchange
        val currency: CurrencyAmount = CurrencyAmount(result)
        return currency
    }

    private fun getExchangeRate(fromCurrency: CurrencyCode, toCurrency: CurrencyCode): Double {
        // Placeholder exchange rate - in a real application, you'd fetch this from a financial API
        return when {
            fromCurrency.code == "USD" && toCurrency.code == "EUR" -> 0.93
            fromCurrency.code == "USD" && toCurrency.code == "GBP" -> 0.82
            else -> 1.0
        }
    }
}
@JvmInline
value class AccountNumber(val value: String) {
    init {
        require(value.length == 10 && value.all { it.isDigit() }) {"Invalid input"}
    }
}
@JvmInline
value class CurrencyAmount(val amount: Double) {
    init {
        require(amount >= 0) {"Invalid input"}
    }
}
@JvmInline
value class CurrencyCode(val code: String) {
    init {
        require(code.length == 3 && code == code.uppercase()) {"Invalid input"}
    }
}
@JvmInline
value class TransactionId(val value: String?) {
    init {
        require(value != null && value.isNotEmpty()) {"Invalid input"}
    }
}
