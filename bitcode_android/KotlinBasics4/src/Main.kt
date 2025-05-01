interface PaymentMethod {
    val amount: Double
    fun processPayment(): Boolean
    fun printReceipt()
}

abstract class DigitalPayment(
    override val amount: Double
) : PaymentMethod {
    fun validateAmount(): Boolean {
        return amount > 0
    }
    override fun printReceipt() {
        println("Receipt: Payment of ₹$amount successful.")
    }
}

class CardPayment(
    amount: Double,
    private val cardNumber: String,
    private val cardHolder: String
) : DigitalPayment(amount) {
    override fun processPayment(): Boolean {
        if (!validateAmount()) return false
        println("Processing card payment of ₹$amount for $cardHolder")
        return true
    }
    override fun printReceipt() {
        println("Receipt: ₹$amount paid using Card ending ${cardNumber.takeLast(4)}")
    }
}

class UpiPayment(
    amount: Double,
    private val upiId: String
) : DigitalPayment(amount) {
    override fun processPayment(): Boolean {
        if (!validateAmount()) return false
        println("Processing UPI payment of ₹$amount to $upiId")
        return true
    }
}

fun main() {
    val payments: List<PaymentMethod> = listOf(
        CardPayment(1200.0, "4567123412345678", "Deepak"),
        UpiPayment(750.0, "deepak@upi")
    )
    for (payment in payments) {
        if (payment.processPayment()) {
            payment.printReceipt()
        }
    }
}