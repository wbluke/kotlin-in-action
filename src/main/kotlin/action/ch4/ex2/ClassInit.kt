package action.ch4.ex2

/**
 * 코틀린은 주 생성자와 부 생성자를 구분한다.
 * 코틀린에서는 초기화 블록을 통해 초기화 로직을 추가할 수 있다.
 *
 * constructor 키워드는 주 생성자나 부 생성자 정의를 시작할 때 사용한다.
 * init 키워드는 초기화 블록을 시작한다.
 */
class User constructor(_nickname: String) {
    val nickname: String

    init {
        nickname = _nickname
    }
}

class User2(val nickname: String)

/**
 * 인터페이스는 생성자가 없기 때문에 상위 인터페이스 이름 뒤에는 괄호가 없다.
 * 괄호의 유무로 기반 클래스와 인터페이스를 쉽게 구분할 수 있다.
 */
open class Button
class RadioButton : Button()

interface Button2
class RadioButton2 : Button2

/**
 * 어떤 클래스를 외부에서 인스턴스화하지 못하게 막고 싶다면 모든 생성자를 private으로 만들면 된다.
 */
class Secretive private constructor() {}

/**
 * 접근자의 본문에서는 field 라는 특별한 식별자를 통해 뒷받침하는 필드에 접근할 수 있다.
 * getter 에서는 field 값을 읽을 수만 있고, setter 에서는 field 값을 읽거나 쓸 수 있다.
 */
class User3(val name: String) {
    var address: String = "unspecified"
        set(value: String) {
            println(
                """
            Address was changed for $name:
            "$field" -> "$value".""".trimIndent()
            )
            field = value
        }
}

/**
 * get이나 set 앞에 가시성 변경자를 추가해서 접근자의 가시성을 변경할 수 있다.
 */
class LengthCounter {
    var counter: Int = 0
        private set

    fun addWord(word: String) {
        counter += word.length
    }
}
