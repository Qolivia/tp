package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name}, {@code Phone}, {@code Email},
 * {@code Address}, {@code Subject}, {@code Rate}, or {@code Tag} matches any of the keywords given.
 */
public class UniversalSearchPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public UniversalSearchPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordPrefixIgnoreCase(person.getName().fullName, keyword)
                                || StringUtil.containsWordPrefixIgnoreCase(person.getPhone().value, keyword)
                                || StringUtil.containsWordPrefixIgnoreCase(person.getEmail().value, keyword)
                                || StringUtil.containsWordPrefixIgnoreCase(person.getAddress().value, keyword)
                                || person.getSubjects().stream().anyMatch(subject -> StringUtil.containsWordPrefixIgnoreCase(subject.subject, keyword))
                                || StringUtil.containsWordPrefixIgnoreCase(person.getRate().rate, keyword)
                                || person.getTags().stream().anyMatch(tag -> StringUtil.containsWordPrefixIgnoreCase(tag.tagName, keyword))
                );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniversalSearchPredicate)) {
            return false;
        }

        UniversalSearchPredicate otherUniversalSearchPredicate = (UniversalSearchPredicate) other;
        return keywords.equals(otherUniversalSearchPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("keywords", keywords)
                .toString();
    }
}

