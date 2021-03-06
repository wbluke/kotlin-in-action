package action.ch3.ex6

/**
 * 코틀린에서는 자바의 split 대신에 여러 가지 다른 조합의 파라미터를 받는 split 확장 함수를 제공함으로써
 * 혼동을 야기하는 메서드를 감춘다.
 * 정규식을 파라미터로 받는 함수는 String 이 아닌 Regex 타입의 값을 받는다.
 */
fun main() {
    println("12.345-6.A".split("\\.|-".toRegex()))

    // 사실 이런 간단한 경우에는 정규식을 쓸 필요가 없다.
    println("12.345-6.A".split(".", "-")) // 구분 문자열을 하나 이상 인자로 받는 함수가 있다.
}

/**
 * 코틀린 표준 라이브러리에는 어떤 문자열에서 구분 문자열이 맨 나중(or 처음)에 나타난 곳 뒤(or 앞)의 부분 문자열을 반환하는 함수가 있다.
 * 파일 경로 파싱할 때 유용
 *
 * String.substringBeforeLast("/")
 * String.substringAfterLast("/")
 */

/**
 * 따로 지정하지 않으면 정규식 엔진은 각 패턴을 가능한 한 가장 긴 부분 문자열과 매치하려고 시도한다.
 * 정규식 문서의 greedy, lazy 참조
 */

/**
 * 3중 따옴표
 *
 * - 역슬래시를 포함한 어떤 문자도 이스케이프할 필요가 없다. \\. -> \.
 * - 여러 줄 선언 가능 -> 테스트에서 유용하다 !
 */
