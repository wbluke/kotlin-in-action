package action.ch4.ex1

/**
 * 자바에서는 모든 클래스가 기본적으로 상속 가능하지만, 이로 인해 문제가 생기는 경우가 많다.
 * 기반 클래스를 변경하는 경우 하위 클래스의 동작이 예기치 않게 바뀔 수도 있다는 면에서 기반 클래스는 '취약'하다.
 *
 * 코틀린의 클래스와 메서드는 기본적으로 final이다.
 * 클래스나 메서드의 상속(오버라이드)을 허용하려면 open 변경자를 붙여야 한다.
 */
interface Clickable {
    fun click()
}

open class RichButton : Clickable {
    fun disable() {} // 이 함수는 final이다.
    open fun animate() {} // 이 함수는 열려 있다. override 가능.
    override fun click() {} // override 한 메서드는 기본적으로 열려있다. 다시 하위에서 오버라이드 하지 못하게 하려면 final 선언이 필요하다.
}

/**
 * 클래스의 기본 상속 가능 상태가 final이어서 얻을 수 있는 큰 이익은 스마트 캐스트가 가능하다는 점이다.
 */
