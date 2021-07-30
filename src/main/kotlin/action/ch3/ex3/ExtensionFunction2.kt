package action.ch3.ex3

/**
 * 확장 함수는 오버라이드할 수 없다.
 *
 * 확장 함수는 클래스의 일부가 아니다. 확장 함수는 클래스 밖에 선언된다.
 * 이름과 파라미터가 완전히 같은 확장 함수를 기반 클래스와 하위 클래스에 대해 정의해도
 * 실제로는 확장 함수를 호출할 때 수신 객체로 지정한 변수의 정적 타입에 의해 어떤 확장 함수가 호출될지 결정되지,
 * 그 변수에 저장된 객체의 동적인 타입에 의해 확장 함수가 결정되지 않는다.
 */
open class View {
    open fun click() = println("View clicked")
}

class Button : View() {
    override fun click() = println("Button clicked")
}

fun View.showOff() = println("I'm a view!")
fun Button.showOff() = println("I'm a button!")

fun main() {
    val view: View = Button()

    view.click() // Button clicked
    view.showOff() // I'm a view!
}

/**
 * 확장 함수를 첫 번째 인자가 수신 객체인 정적 자바 메서드로 컴파일한다는 사실을 기억한다면 이런 동작을 쉽게 이해할 수 있다.
 * 자바도 호출할 정적 함수를 같은 방식으로 정적으로 결정한다.
 *
 * View view = new Button();
 * ExtensionsKt.showOff(view);
 */

/**
 * 어떤 클래스를 확장한 함수와 그 클래스의 멤버 함수의 이름과 시그니처가 같다면
 * 확장 함수가 아니라 멤버 함수가 호출된다.
 * (멤버 함수의 우선순위가 더 높다.)
 */
