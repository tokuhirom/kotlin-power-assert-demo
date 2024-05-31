kotlin の power-assert が最高なので、遊んでみた。
(まだできたてホヤホヤ Experimental です)

assertj/assertK などのアサーションライブラリは便利なのだのだけど、、
- assertion が失敗したときに、どの処理で失敗したかとかがわかりにくい
- 結果的に、デバッガで追わないとよくわからんということになりがち。
- それを避けるために、assertion を段階的に書けたりして、冗長になった

そこで、power-assert ですよ。

assertK でこう書いてたコードが

```kotlin
    val people = listOf(Person(name = "Sue"), Person(name = "Bob"))
    assertThat(people)
        .extracting(Person::name)
        .containsExactly("Sue", "Bob")
```

こう書いたら

```kotlin
    @Test
    fun testComplex() {
        val people = listOf(Person("Sue"), Person("Bob"))
        assert(people.map { it.name } == listOf("Susie", "Bob"))
    }
```

こうだ!

```
    Assertion failed
    assert(people.map { it.name } == listOf("Susie", "Bob"))
           |      |               |  |
           |      |               |  [Susie, Bob]
           |      |               false
           |      [Sue, Bob]
           [Person(name=Sue), Person(name=Bob)]
```

最高!

なお、multiplatform にも対応しているみたい。
