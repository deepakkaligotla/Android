fun main() {
    val d1 = D()
    d1.foo()
    d1.bar()
}

interface A {
    fun foo() = println("A.foo")
    fun bar() = println("A.bar")
}

interface B {
    fun foo() = println("B.foo")
    fun bar() = println("B.bar")
}

class D: A, B {
    override fun foo() {
        super<A>.foo()
        super<B>.foo()
        println("D.foo")
    }

    override fun bar() {
        super<A>.bar()
        super<B>.bar()
        print("D.bar")
    }
}