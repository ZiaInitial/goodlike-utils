package eu.goodlike.test;

import eu.goodlike.functional.some.Some;
import eu.goodlike.misc.Scaleless;
import eu.goodlike.time.DateConverter;
import eu.goodlike.time.Time;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static eu.goodlike.v2.validate.Validate.string;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

public class FakeTest {

    private final List<Integer> indexes = Arrays.asList(1, 5, 19, 31);

    @Test
    public void tryNames_shouldBeUnique() {
        assertThat(Some.of(Fake::name).fetchStream(10).collect(toSet())).hasSize(10);
    }

    @Test
    public void trySurnames_shouldBeUnique() {
        assertThat(Some.of(Fake::surname).fetchStream(10).collect(toSet())).hasSize(10);
    }

    @Test
    public void tryEmails_shouldBeUnique() {
        assertThat(Some.of(Fake::email).fetchStream(10).collect(toSet())).hasSize(10);
    }

    @Test
    public void tryCities_shouldBeUnique() {
        assertThat(Some.of(Fake::city).fetchStream(10).collect(toSet())).hasSize(10);
    }

    @Test
    public void tryPhones_shouldBeUnique() {
        assertThat(Some.of(Fake::phone).fetchStream(10).collect(toSet())).hasSize(10);
    }

    @Test
    public void tryPictures_shouldBeUnique() {
        assertThat(Some.of(Fake::picture).fetchStream(10).collect(toSet())).hasSize(10);
    }

    @Test
    public void tryDocuments_shouldBeUnique() {
        assertThat(Some.of(Fake::document).fetchStream(10).collect(toSet())).hasSize(10);
    }

    @Test
    public void tryComments_shouldBeUnique() {
        assertThat(Some.of(Fake::comment).fetchStream(10).collect(toSet())).hasSize(10);
    }

    @Test
    public void tryWages_shouldBeUnique() {
        assertThat(Some.of(Fake::wage).fetchStream(10).map(Scaleless::bigDecimal).collect(toSet())).hasSize(10);
    }

    @Test
    public void tryDays_shouldBeUnique() {
        assertThat(Some.of(Fake::day).fetchStream(31).collect(toSet())).hasSize(31);
    }

    @Test
    public void tryWebsites_shouldBeUnique() {
        assertThat(Some.of(Fake::website).fetchStream(10).collect(toSet())).hasSize(10);
    }

    @Test
    public void tryBooleans_shouldBeUnique() {
        assertThat(Some.of(Fake::Boolean).fetchStream(2).collect(toSet())).hasSize(2);
    }

    @Test
    public void tryLanguageLevels_shouldBeUnique() {
        assertThat(Some.of(Fake::languageLevel).fetchStream(10).collect(toSet())).hasSize(10);
    }

    @Test
    public void tryHours_shouldBeUnique() {
        assertThat(Some.of(Fake::hour).getStream(24).collect(toSet())).hasSize(24);
    }

    @Test
    public void tryMinutes_shouldBeUnique() {
        assertThat(Some.of(Fake::minute).getStream(60).collect(toSet())).hasSize(60);
    }

    @Test
    public void tryIds_shouldBeUnique() {
        assertThat(Some.of(Fake::id).fetchStream(10).collect(toSet())).hasSize(10);
    }

    @Test
    public void tryLocalDates_shouldBeUnique() {
        assertThat(Some.of(Fake::localDate).fetchStream(31).collect(toSet())).hasSize(31);
    }

    @Test
    public void tryDayStrings_shouldBeUnique() {
        assertThat(Some.of(Fake::dayString).fetchStream(31).collect(toSet())).hasSize(31);
    }

    @Test
    public void tryLanguages_shouldBeUnique() {
        assertThat(Some.of(Fake::language).getStream(100).collect(toSet())).hasSize(100);
    }

    @Test
    public void tryPasswords_shouldBeUnique() {
        assertThat(Some.of(Fake::password).fetchStream(10).collect(toSet())).hasSize(10);
    }

    @Test
    public void tryDurations_shouldBeUnique() {
        assertThat(Some.of(Fake::duration).fetchStream(31).collect(toSet())).hasSize(31);
    }

    @Test
    public void tryCodes_shouldBeUnique() {
        assertThat(Some.of(Fake::code).getStream(900000).collect(toSet())).hasSize(900000);
    }

    @Test
    public void tryTimes_shouldBeUnique() {
        assertThat(Some.of(Fake::time).fetchStream(31).collect(toSet())).hasSize(31);
    }

    @Test
    public void testEmail_shouldBeEmail() {
        assertThat(Some.of(Fake::email).with(indexes)).filteredOn(string().isEmail()).hasSameSizeAs(indexes);
    }

    @Test
    public void testLocalDate_shouldEndWithDay() {
        assertThat(Some.of(Fake::localDate).stream(indexes).map(LocalDate::getDayOfMonth).collect(toList()))
                .isEqualTo(Some.of(Fake::day).with(indexes));
    }

    @Test
    public void testDayString_shouldBeSameAsDay() {
        assertThat(Some.of(Fake::dayString).stream(indexes).map(Integer::parseInt).collect(toList()))
                .isEqualTo(Some.of(Fake::day).with(indexes));
    }

    @Test
    public void testTime_shouldBeSameAsLocalDate() {
        assertThat(Some.of(Fake::time).stream(indexes).map(Time::convert).map(DateConverter::toLocalDate).collect(toList()))
                .isEqualTo(Some.of(Fake::localDate).with(indexes));
    }

}