import java.math.BigInteger
import java.util.Random

fun main() {
    println(missingNumberInSequence(arrayOf(1,2,4,5,6)))
    for(num in removeDuplicatesFromArray(arrayOf(1,2,2,3,5,8,1))) println(num)
    println("Is 371 Armstrong: ${isArmstrong(371)}")
    printRightAngledTriangle(5)
    characterSequenceInString("Kaligotla Uma Sai Deepak")
    println()
    for(num in sortArray(arrayOf(9,3,8,1,6,4,0,2,3,5))) {
        print(num)
    }
    println()
    println(fibonacci(5))
    println(factorial(4))
    println("silent & Listen ${if (isAnagram("silent", "Listen")) "- isAnagram" else "- isNotAnagram"}")
    println(reverseString("I Love Coding"))
    println(isPalindrome("Madam"))
    println(isPalindrome(1252))
    println(countVowelsConstants("Kaligotla Uma Sai Deepak"))
    println(findCommon(intArrayOf(1,2,3), intArrayOf(2,3,4)))
    println(mergeAndSort(intArrayOf(2,4), intArrayOf(1, 3)))
    println(countWords("Kaligotla Uma Sai Deepak"))
    println(diagonalSum(arrayOf(intArrayOf(1,2,3), intArrayOf(4,5,6), intArrayOf(7,8,9))))
    println(sumWithoutPlus(1,5))
    println(checkIfPrime(BigInteger.TWO.pow(BigInteger.valueOf(8258).intValueExact()).subtract(BigInteger.ONE)))
    println(rotateRight(intArrayOf(1,2,3,4,5), 2).toList())
}

fun missingNumberInSequence(arr: Array<Int>) : Int {
    val arraySum = arr.sum()
    val logicSum = (arr.last() * (arr.last()+1))/2
    return logicSum - arraySum
}

fun removeDuplicatesFromArray(arr: Array<Int>): IntArray {
    val set = LinkedHashSet<Int>()
    for(num in arr) {
        set.add(num)
    }
    val uniqueArray = set.stream().mapToInt{it}.toArray()
    return uniqueArray //Way 01
    //return arr.toSet().toIntArray() //Way 02
}

fun isArmstrong(n: Int): Boolean {
    var num = n
    var sum = 0
    while(num != 0) {
        val lastDigit = num%10
        sum = sum + Math.pow(lastDigit.toDouble(), numOfDigits(n).toDouble()).toInt()
        num = num/10
    }
    return if(sum == n) true else false
}

fun numOfDigits(n: Int): Int {
    var num = n;
    var count = 0
    while(num!=0) {
        num = num/10
        count++
    }
    return count
}

fun printRightAngledTriangle(n: Int) {
    for(i in 1..n) {
        for(j in 1..i) {
            print("* ")
        }
        println()
    }
}

fun characterSequenceInString(inputString: String) {
    val map = mutableMapOf<Char, Int>()
    for(eachChar in inputString) {
        map[eachChar] = map.getOrDefault(eachChar, 0)+1
    }
    for((key, value) in map) {
        print("$key : $value ")
    }
}

fun sortArray(arr: Array<Int>): Array<Int> {
    val inputArray = arr
    for(i in 0 until inputArray.size-1) {
        for(j in 0 until inputArray.size-i-1) {
            if(inputArray[j] > inputArray[j+1]) {
                val temp = inputArray[j]
                inputArray[j] = inputArray[j+1]
                inputArray[j+1] = temp
            }
        }
    }
    return inputArray
}

fun fibonacci(n: Int): List<Int> {
    val result = mutableListOf<Int>()
    var a = 0
    var b = 1
    for (i in 0 until n) {
        result.add(a)
        val sum = a + b
        a = b
        b = sum
    }
    return result
}

fun factorial(n: Int): Int {
    return if(n<=1) 1 else n * factorial(n-1)
}

fun isAnagram(s1: String, s2: String): Boolean {
    return s1.lowercase().toCharArray().sorted() == s2.lowercase().toCharArray().sorted()
}

fun reverseString(str: String): String {
    return str.trim().split("\\s+".toRegex()).reversed().joinToString(" ")
}

fun <T> isPalindrome(input: T): Boolean {
    val str = input.toString().lowercase()
    return str == str.reversed()
}

fun countVowelsConstants(input: String): Pair<Int, Int> {
    var vowels = 0
    var constants = 0
    for(c in input.lowercase()) {
        if(c.isLetter()) {
            if(c in "aeiou") vowels++ else constants++
        }
    }
    return Pair(vowels, constants)
}

fun findCommon(arr1: IntArray, arr2: IntArray): Set<Int> {
    return arr1.toSet().intersect(arr2.toSet())
}

fun mergeAndSort(arr1: IntArray, arr2: IntArray): List<Int> {
    return (arr1 + arr2).sorted().toList()
}

fun countWords(input: String): Int {
    return input.lowercase().trim().split("\\s+".toRegex()).size
}

fun diagonalSum(input: Array<IntArray>): Int {
    var size = input.size
    for(i in 0 until size) {

    }
    return 0
}

//sum numbers without using '+'
fun sumWithoutPlus(a: Int, b: Int): Int {
    return if (b == 0) a else sumWithoutPlus(a xor b, (a and b) shl 1)
}

fun checkIfPrime(n: BigInteger, k: Int = 5): Boolean {
    if (n == BigInteger.valueOf(2) || n == BigInteger.valueOf(3)) return true
    if (n < BigInteger.valueOf(2) || n % BigInteger.valueOf(2) == BigInteger.ZERO) return false
    var r = 0
    var d = n - BigInteger.ONE
    while (d % BigInteger.valueOf(2) == BigInteger.ZERO) {
        d /= BigInteger.valueOf(2)
        r++
    }
    val random = Random()
    for (i in 0 until k) {
        val a = BigInteger(n.bitLength(), random)
            .mod(n - BigInteger.valueOf(4)) + BigInteger.valueOf(2)
        var x = a.modPow(d, n)
        println("Checking with base: $a, x = $x")
        if (x == BigInteger.ONE || x == n - BigInteger.ONE) continue
        var continueOuter = false
        for (j in 0 until r - 1) {
            x = x.modPow(BigInteger.valueOf(2), n)
            if (x == n - BigInteger.ONE) {
                continueOuter = true
                break
            }
        }
        if (continueOuter) continue
        return false
    }
    return true
}

fun rotateRight(arr: IntArray, k: Int): IntArray {
    val n = arr.size
    val result = IntArray(n)
    for(i in arr.indices) {
        result[(i + k) % n] = arr[i]
    }
    return result
}