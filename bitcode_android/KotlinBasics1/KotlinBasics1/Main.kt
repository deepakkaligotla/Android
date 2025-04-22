import java.time.LocalDate
import java.time.Period

fun main() {
    println("Hello World!")
    bigLine("Variables")
    variables()
    bigLine("Incremental Operators")
    incrementalOperators()
    bigLine("Type Inference")
    typeInference()
    bigLine("Fun with arguments and return type")
    println("2 + 3 = ${add(2,3)}")
    bigLine("Ranges")
    ranges()
    bigLine("Array Of")
    arrayOfMethod()
    bigLine("Lambda Function")
    lambdaFun()
    bigLine("Array Classes")
    arrayClasses()
    bigLine("conditions")
    conditions(31)
}

fun bigLine(name: String) {
    println("----------------------$name-------------------------")
}

fun smallLine(name: String) {
    println("-----------$name------------")
}

fun emptyLine() {
    println()
}

//var is used to create variable which are mutable
//val is used to create constants which are immutable
fun variables() {
    var i: Int = 0
    val j: String = "Deepak"
    var k: Double = 0.24
    val l: Float = 345.23F
    var m: Boolean = false

    i = 2
    //j = 3 cant change as it is constant

    println("i value is $i -- j value is $j")
    println("k value is $k -- l value is $l")
    println("m value is $m")
}

fun incrementalOperators() {
    var i: Int = 0
    val j: Int = 0

    println("i value post increment ${i++}")
    println("i value pre increment ${++i}")
    println("i value post decrement ${i--}")
    println("i value pre decrement ${--i}")
    // println("i value pre increment ${++j}") - Val cannot be reassigned
}

fun typeInference() {
    val company = "BitCode" //Smart high level language, no need of DataType to be explicitly mentioned
    var name: String = "Deepak"
    println("$name works at $company")
}

fun add(n1: Int, n2: Int) : Int {
    return n1+n2
}

fun ranges() {
    var range = 1..5 //in Swift it is 1...10
    for (num in range) {
        println("5 x $num = ${5*num}")
    }
    smallLine("Reversed Range")
    var reverseRange = 5.downTo(1) //in Swift range.reversed()
    for (num in reverseRange) {
        println("5 x $num = ${5*num}")
    }
    smallLine("Stepped Range")
    var stepedRange = (1..5).step(2)
    for (step in stepedRange) {
        println(step)
    }
    smallLine("Reverse Step Range")
    var reversedStepRange = (-5..5).reversed().step(3)
    for (step in reversedStepRange) {
        println(step)
    }
}

fun arrayOfMethod() {
    var names = arrayOf("Deepak", "Akash", "Tanuja", "Radha")
    for(name in names) {
        print(" Name is $name,")
    }
    emptyLine()
    var ids = arrayOf(101, 102, 103, 104)
    for(id in ids) {
        print(" $id,")
    }
    emptyLine()
}

//Lambda function - special type of functions in Kotlin using this we can pass function as argument to another function
//(input) -> output
//In Swift Handler in Kotlin it is init
//Lambda function is always the last argument in the argument list of a function
//"it" is representing index as default placeholder, called property for only one input argument
//Lambda should be moved out of the parenthesis

fun lambdaFun() {
//    var numbers1 = Array<Int>(5, {i -> i + 10})
    var numbers = Array<Int>(5) {i -> i + 10}
    for (number in numbers) {
        print(" $number,")
    }
    emptyLine()
    var names = Array<String>(4) {i -> "Name $i"}
    for (name in names) {
        print(" $name,")
    }
    emptyLine()
    var fees = Array<Double>(4) {
        29800.00 + it
    }
    for (fee in fees) {
        print(" $fee,")
    }
    emptyLine()
}

fun arrayClasses() {
    var integers : IntArray = IntArray(4) //with all elements initialized to zero
    var floats: FloatArray = FloatArray(4) //with all elements initialized to zero.
    var doubles: DoubleArray = DoubleArray(4) //with all elements initialized to zero.
    val chars: CharArray = CharArray(94) { '!' + it } //ASCII values 33 to 126
    for(char in chars) {
        print("$char ")
    }
    emptyLine()
}

fun conditions(age: Int) {
    smallLine("If Else")
    var legal: String = (age > 18) ? "Major" : "Minor"
    var checkMajor: String = if(age>18) { "Major, can vote" } else "Minor, can't vote"
    print(checkMajor)
    when(34) {
        in 0..35 -> print("Failed")
        in 35..69 -> print("Good")
        in 70..89 -> print("Very Good")
        in 90..99 -> print("Topped")
        100 -> print("Aced")
        else -> print("Absent")
    }
    emptyLine()
}
