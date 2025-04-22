fun main() {
    println(missingNumberInSequence(arrayOf(1,2,4,5,6)))
    for(num in removeDuplicatesFromArray(arrayOf(1,2,2,3,5,8,1))) println(num)
    println("Is 153 Armstrong: ${isArmstrong(153)}")
    printRightAngledTriangle(5)
    characterSequenceInString("Kaligotla Uma Sai Deepak")
    println()
    for(num in sortArray(arrayOf(9,3,8,1,6,4,0,2,3,5))) {
        print(num)
    }
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