import java.sql.Array

fun main() {
    getPrimeNumbers()
}
fun getPrimeNumbers() {
    val primes: ArrayList<Int> = ArrayList()
    for (number in 1..50) {
        if (isPrime(number)) {
            primes.add(number)
        }
    }
    primes.forEach {
        print("$it " +
                "")
    }
}

fun isPrime(n: Int): Boolean {
    if (n <= 1) return false
    if (n == 2 || n == 3) return true
    if (n % 2 == 0 || n % 3 == 0) return false

    val sqrt = kotlin.math.sqrt(n.toDouble()).toInt()
    for (i in 5..sqrt step 6) {
        if (n % i == 0 || n % (i + 2) == 0)
            return false
    }
    return true
}

//Find duplicate elements in an Array
//
//Reverse a number using recursion
//