import kotlin.test.Test

data class User(val name: String, val age: Int)

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
}
