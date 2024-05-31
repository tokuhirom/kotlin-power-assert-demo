import kotlin.test.Test

data class User(val name: String, val age: Int)
data class Person(val name: String)

fun foo(p: String): String? {
    if ("HOGE" == p) {
        return null
    } else {
        return p.uppercase()
    }
}

class MyTest {
    /*
    Incorrect length
    assert(hello.length == world.substring(1, 4).length) { "Incorrect length" }
           |     |      |  |     |               |
           |     |      |  |     |               3
           |     |      |  |     orl
           |     |      |  world!
           |     |      false
           |     5
           Hello
     */
    @Test
    fun testSimple() {
        val hello = "Hello"
        val world = "world!"
        assert(hello.length == world.substring(1, 4).length) { "Incorrect length" }
    }

    /*
    Incorrect name
    assert(user == User("John Doe", 20)) { "Incorrect name" }
           |    |  |
           |    |  User(name=John Doe, age=20)
           |    false
           User(name=John, Doe, age=29)
     */
    @Test
    fun testUser() {
        val user = User("John, Doe", 29)
        assert(user == User("John Doe", 20)) { "Incorrect name" }
    }

    /*
    In assertk:
    val people = listOf(Person(name = "Sue"), Person(name = "Bob"))
    assertThat(people)
        .extracting(Person::name)
        .containsExactly("Sue", "Bob")
    */

    /*
    Assertion failed
    assert(people.map { it.name } == listOf("Susie", "Bob"))
           |      |               |  |
           |      |               |  [Susie, Bob]
           |      |               false
           |      [Sue, Bob]
           [Person(name=Sue), Person(name=Bob)]
     */
    @Test
    fun testComplex() {
        val people = listOf(Person("Sue"), Person("Bob"))
        assert(people.map { it.name } == listOf("Susie", "Bob"))
    }

    /*
    Assertion failed
    assert("Dan" in listOf("Susie", "Bob"))
                 |  |
                 |  [Susie, Bob]
                 false
     */
    @Test
    fun testIn() {
        val expected = listOf("Susie", "Bob")
        assert("Susie" in expected)
        assert("Dan" in expected)
    }
}
