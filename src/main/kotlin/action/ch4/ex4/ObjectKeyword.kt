package action.ch4.ex4

import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

/**
 * object 키워드
 *
 * 클래스를 정의하면서 동시에 인스턴스를 생성한다.
 *
 * - 객체 선언(object declaration)은 싱글턴을 정의하는 방법 중 하나다.
 * - 동반 객체(companion object)는 인스턴스 메서드는 아니지만 어떤 클래스와 관련 있는 메서드와 팩토리 메서드를 담을 때 쓰인다.
 * - 객체 식은 자바의 무명 내부 클래스 대신 쓰인다.
 */

/**
 * 객체 선언
 *
 * 코틀린은 객체 선언 기능을 통해 싱글턴을 언어에서 기본 지원한다.
 * 객체 선언은 클래스 선언과 그 클래스에 속한 단일 인스턴스의 선언을 합친 선언이다.
 *
 * 생성자(주, 부 모두)는 객체 선언에 쓸 수 없다.
 */
class Person

object Payroll {
    val allEmployees = arrayListOf<Person>()

    fun calculateSalary() {
        for (person in allEmployees) {
            // ...
        }
    }
}

/**
 * 동반 객체
 *
 * 코틀린 언어는 자바 static 키워드를 지원하지 않는다.
 * 그 대신 코틀린에서는 패키지 수준의 최상위 함수와 객체 선언을 활용한다.
 * 대부분의 경우 최상위 함수를 활용하는 편을 더 권장한다.
 *
 * 하지만 최상위 함수는 private으로 표시된 클래스 비공개 멤버에 접근할 수 없다.
 * 그래서 클래스의 인스턴스와 관계없이 호출해야 하지만, 클래스 내부 정보에 접근해야 하는 함수가 필요할 때는
 * 클래스에 중첩된 객체 선언의 멤버 함수로 정의해야 한다.
 *
 * 동반 객체의 프로퍼티나 메서드에 접근하려면 그 동반 객체가 정의된 클래스 이름을 사용한다.
 * 그 결과 동반 객체의 멤버를 사용하는 구문은 자바의 정적 메서드 호출이나 정적 필드 사용 구문과 같아진다.
 *
 * 동반 객체는 private 생성자를 호출할 수 있기 때문에 팩토리 패턴을 구현하기 가장 적합한 위치다.
 */
class A {
    companion object {
        fun bar() {
            println("Companion object called")
        }
    }
}
// A.bar()

/**
 * 동반 객체 확장
 *
 * 동반 객체에 대한 확장 함수를 작성할 수 있으려면 원래 클래스에 동반 객체를 꼭 선언해야 한다.
 */
class Person2(val firstName: String, val lastName: String) {
    companion object {

    }
}

fun Person2.Companion.fromJSON(json: String): Person2 {
    // ...
    return Person2("", "")
}

/**
 * 무명 객체
 */
val listener = object : MouseAdapter() {
    override fun mouseClicked(e: MouseEvent?) {
        super.mouseClicked(e)
    }

    override fun mouseEntered(e: MouseEvent?) {
        super.mouseEntered(e)
    }
}
