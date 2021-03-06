package eu.goodlike.validate.impl;

import eu.goodlike.test.Fake;
import eu.goodlike.validate.Validate;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringValidatorTest {

    private StringValidator validator;

    @Before
    public void setup() {
        validator = Validate.string();
    }

    @Test
    public void tryEmptyWithEmpty_shouldBeTrue() {
        assertThat(validator.isEmpty().test("")).isTrue();
    }

    @Test
    public void tryEmptyWithBlank_shouldBeFalse() {
        assertThat(validator.isEmpty().test(" ")).isFalse();
    }

    @Test
    public void tryEmptyWithNotEmpty_shouldBeFalse() {
        assertThat(validator.isEmpty().test("NotEmpty")).isFalse();
    }

    @Test
    public void tryBlankWithEmpty_shouldBeTrue() {
        assertThat(validator.isBlank().test("")).isTrue();
    }

    @Test
    public void tryBlankWithBlank_shouldBeTrue() {
        assertThat(validator.isBlank().test(" ")).isTrue();
    }

    @Test
    public void tryBlankWithNotEmpty_shouldBeFalse() {
        assertThat(validator.isBlank().test("NotEmpty")).isFalse();
    }

    @Test
    public void tryNoLargerThanWithNoLargerThan_shouldBeTrue() {
        assertThat(validator.hasAtMostChars(5).test("yes")).isTrue();
    }

    @Test
    public void tryNoLargerThanWithExactSize_shouldBeTrue() {
        assertThat(validator.hasAtMostChars(5).test("size5")).isTrue();
    }

    @Test
    public void tryNoLargerThanWithLargerThan_shouldBeFalse() {
        assertThat(validator.hasAtMostChars(5).test("sizeMoreThan5")).isFalse();
    }

    @Test
    public void tryEmailWithEmail_shouldBeTrue() {
        assertThat(validator.isSimpleEmail().test(Fake.email(1))).isTrue();
    }

    @Test
    public void tryEmailWithNotEmail_shouldBeFalse() {
        assertThat(validator.isSimpleEmail().test(Fake.name(1))).isFalse();
    }

    @Test
    public void tryCommaSeparatedListOfIntegersWithCommaSeparatedListOfIntegers_shouldBeTrue() {
        assertThat(validator.isCommaSeparatedListOfIntegers().test("1,2,3")).isTrue();
    }

    @Test
    public void tryCommaSeparatedListOfIntegersWithCommaAndSpaceSeparatedListOfIntegers_shouldBeFalse() {
        assertThat(validator.isCommaSeparatedListOfIntegers().test("1, 2, 3")).isFalse();
    }

    @Test
    public void tryCommaSeparatedListOfIntegersWithNotCommaSeparatedListOfIntegers_shouldBeFalse() {
        assertThat(validator.isCommaSeparatedListOfIntegers().test("1.2.3")).isFalse();
    }

    @Test
    public void tryCommaSeparatedListOfIntegersWithOne_shouldBeTrue() {
        assertThat(validator.isCommaSeparatedListOfIntegers().test("1")).isTrue();
    }

    @Test
    public void tryNoSmallerThanWithSmaller_shouldBeFalse() {
        assertThat(validator.hasAtLeastChars(10).test("small")).isFalse();
    }

    @Test
    public void tryNoSmallerThanWithExact_shouldBeTrue() {
        String test = "test";
        assertThat(validator.hasAtLeastChars(test.length()).test(test)).isTrue();
    }

    @Test
    public void tryNoSmallerThanWithBigger_shouldBeTrue() {
        assertThat(validator.hasAtLeastChars(2).test("large")).isTrue();
    }

    @Test
    public void tryIntegerWithInteger_shouldBeTrue() {
        assertThat(validator.isInteger().test("123456789")).isTrue();
    }

    @Test
    public void tryIntegerWithNegativeInteger_shouldBeTrue() {
        assertThat(validator.isInteger().test("-123456789")).isTrue();
    }

    @Test
    public void tryIntegerWithNotInteger_shouldBeFalse() {
        assertThat(validator.isInteger().test("not integer")).isFalse();
    }

    @Test
    public void tryIntWithInt_shouldBeTrue() {
        assertThat(validator.isInt().test("123456789")).isTrue();
    }

    @Test
    public void tryIntWithNegativeInt_shouldBeTrue() {
        assertThat(validator.isInt().test("-123456789")).isTrue();
    }

    @Test
    public void tryIntWithNotInt_shouldBeFalse() {
        assertThat(validator.isInt().test("not int")).isFalse();
    }

    @Test
    public void tryIntWithTooBigInt_shouldBeFalse() {
        assertThat(validator.isInt().test("123456789123456789123456789123456789123456789")).isFalse();
    }

    @Test
    public void tryIntWithManyLeadingZeros_shouldBeTrue() {
        assertThat(validator.isInt().test("0000000000000000000000000000000000123456789")).isTrue();
    }

    @Test
    public void tryIntWithManyLeadingZerosAndMinus_shouldBeTrue() {
        assertThat(validator.isInt().test("-0000000000000000000000000000000000123456789")).isTrue();
    }

    @Test
    public void tryLongWithLong_shouldBeTrue() {
        assertThat(validator.isLong().test("123456789")).isTrue();
    }

    @Test
    public void tryLongWithNegativeLong_shouldBeTrue() {
        assertThat(validator.isLong().test("-123456789")).isTrue();
    }

    @Test
    public void tryLongWithNotLong_shouldBeFalse() {
        assertThat(validator.isLong().test("not long")).isFalse();
    }

    @Test
    public void tryLongWithTooBigLong_shouldBeFalse() {
        assertThat(validator.isLong().test("123456789123456789123456789123456789123456789")).isFalse();
    }

    @Test
    public void tryLongWithManyLeadingZeros_shouldBeTrue() {
        assertThat(validator.isLong().test("0000000000000000000000000000000000123456789")).isTrue();
    }

    @Test
    public void tryLongWithManyLeadingZerosAndMinus_shouldBeTrue() {
        assertThat(validator.isLong().test("-0000000000000000000000000000000000123456789")).isTrue();
    }

    @Test
    public void tryIntCustomWithPassingInt_shouldBeTrue() {
        assertThat(validator.isInt(i -> i >= 0).test("123456789")).isTrue();
    }

    @Test
    public void tryIntCustomWithNotPassingInt_shouldBeFalse() {
        assertThat(validator.isInt(i -> i >= 0).test("-123456789")).isFalse();
    }

    @Test
    public void tryIntCustomWithNotInt_shouldBeFalse() {
        assertThat(validator.isInt(i -> i >= 0).test("not integer")).isFalse();
    }

    @Test
    public void tryLongCustomWithPassingLong_shouldBeTrue() {
        assertThat(validator.isLong(i -> i >= 0).test("123456789")).isTrue();
    }

    @Test
    public void tryLongCustomWithNotPassingLong_shouldBeFalse() {
        assertThat(validator.isLong(i -> i >= 0).test("-123456789")).isFalse();
    }

    @Test
    public void tryLongCustomWithNotLong_shouldBeFalse() {
        assertThat(validator.isLong(i -> i >= 0).test("not integer")).isFalse();
    }

    @Test
    public void tryDateWithDate_shouldBeTrue() {
        assertThat(validator.isDate().test("2015-11-10")).isTrue();
    }

    @Test
    public void tryDateWithNotDate_shouldBeFalse() {
        assertThat(validator.isDate().test("not date")).isFalse();
    }

    @Test
    public void tryDateWithPoorlyFormattedDate_shouldBeFalse() {
        assertThat(validator.isDate().test("2015-1-15")).isFalse();
    }

    @Test
    public void tryDateWithImpossibleDate_shouldBeFalse() {
        assertThat(validator.isDate().test("2015-13-15")).isFalse();
    }

    @Test
    public void tryDateWithNegativeZeroYears_shouldBeFalse() {
        assertThat(validator.isDate().test("-0000-11-10")).isFalse();
    }

    @Test
    public void tryDateWithManyLongYearsWithPlus_shouldBeTrue() {
        assertThat(validator.isDate().test("+12345-11-10")).isTrue();
    }

    @Test
    public void tryDateWithManyLongYearsWithoutPlus_shouldBeFalse() {
        assertThat(validator.isDate().test("12345-11-10")).isFalse();
    }

    @Test
    public void tryDateWithFourLongYearsWithPlus_shouldBeFalse() {
        assertThat(validator.isDate().test("+2015-11-10")).isFalse();
    }

    @Test
    public void tryExactlyOfSizeWithExactSize_shouldBeTrue() {
        assertThat(validator.hasChars(4).test("test")).isTrue();
    }

    @Test
    public void tryExactlyOfSizeWithDifferentSize_shouldBeFalse() {
        assertThat(validator.hasChars(10).test("test")).isFalse();
    }

}
