package step2

object StringCalculator {

    private const val SPACE_DELIMITER = " "

    fun calculate(expression: String?): Number {
        if (expression.isNullOrBlank()) {
            throw IllegalArgumentException("입력값은 null 이거나 빈 공백 문자일수 없습니다.")
        }

        return expression
            .split(SPACE_DELIMITER)
            .let {
                calculateRecursive(it[0].toLong(), it.drop(1))
            }
    }

    private tailrec fun calculateRecursive(currentNumber: Long, remainList: List<String>): Long {
        if (remainList.isEmpty()) {
            return currentNumber
        }
        val operator = Operator.of(remainList[0])
        val nextNumber = remainList[1].toLong()

        return calculateRecursive(operator.operate(currentNumber, nextNumber), remainList.drop(2))
    }
}
