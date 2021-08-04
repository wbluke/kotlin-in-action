package action.ch5.ex2

/**
 * 수신 객체 지정 람다 : with & apply
 *
 * 자바의 람다에는 없는 코틀린 람다의 독특한 기능이 있다.
 * 바로 수신 객체를 명시하지 않고 람다의 본문 안에서 다른 객체의 메서드를 호출할 수 있게 하는 것이다.
 * 이런 람다를 수신 객체 지정 람다(lambda with receiver)라고 한다.
 */
fun alphabet(): String {
    val result = StringBuilder()
    for (letter in 'A'..'Z') {
        result.append(letter)
    }
    result.append("\nNow I know the alphabet!")
    return result.toString()
}

// with를 사용하여 리팩토링
fun alphabet2(): String {
    val stringBuilder = StringBuilder()
    return with(stringBuilder) {
        for (letter in 'A'..'Z') {
            this.append(letter)
        }
        append("\nNow I know the alphabet!") // this를 명시하지 않아도 된다.
        this.toString()
    }
}

/**
 * with는 언어가 제공하는 특별한 구문처럼 보인다.
 * 하지만 실제로는 파라미터가 2개 있는 함수다.
 * 람다를 괄호 밖으로 빼내는 관례를 사용함에 따라 전체 함수 호출이 언어가 제공하는 특별한 구문처럼 보인다.
 *
 * with 함수는 첫 번째 인자로 받은 객체를 두 번째 인자로 받은 람다의 수신 객체로 만든다.
 * 인자로 받은 람다 본문에서는 this를 사용해 수신 객체에 접근할 수 있다. (물론 생략 가능하다.)
 */

/**
 * 수신 객체 지정 람다와 확장 함수 비교
 * (확장 함수에서도 this가 수신 객체를 가리킨다.)
 *
 * 일반 함수      | 일반 람다
 * -------------------------------
 * 확장 함수      | 수신 객체 지정 람다
 */

/**
 * apply 함수는 거의 with와 같다.
 * 유일한 차이란 apply는 항상 자신에게 전달된 객체를 반환한다는 점뿐이다.
 */
fun alphabet3() = StringBuilder().apply {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")
}.toString()

/**
 * 이런 apply 함수는 객체의 인스턴스를 만들면서 즉시 프로퍼티 중 일부를 초기화해야 하는 경우 유용하다.
 * 자바에서는 보통 별도의 Builder 객체가 이런 역할을 담당한다.
 *
 * fun createViewWithCustomAttributes(context: Context) =
 *   TextView(context).apply {
 *     text = "Sample Text"
 *     textSize = 20.0
 *     setPadding(10, 0, 0, 0)
 * }
 */

/**
 * with와 apply는 수신 객체 지정 람다를 사용하는 일반적인 예제 중 하나다.
 * 더 구체적인 함수를 비슷한 패턴으로 활용할 수 있다.
 */
fun alphabet4() = buildString {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!") // this를 명시하지 않아도 된다.
}
