package com.cydeo.tests.day08_hamcrest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class HamcrestMatchersIntro {
    @Test
    public void numbersTest() {
        //MatcherAssert.assertThat(1 + 3, Matchers.is(4));
        assertThat(1 + 3, is(4));
        assertThat(1 + 3, equalTo(4));
        assertThat(1 + 3, is(equalTo(4)));
        assertThat(1 + 3, is(greaterThan(9)));

        //JUnit
        assertTrue(100 + 4 > 99);
        int num = 10 + 2;
        assertThat(num, is(not(10)));
        assertThat(num, is(not(equalTo(9))));
    }

    @Test
    public void StringTest() {
        String word = "wooden spoon";
        assertThat(word, is("wooden spoon"));
        assertThat(word, equalTo("wooden spoon"));

        //startsWith/endsWith
        assertThat(word, startsWith("wood"));
        assertThat(word, startsWithIgnoringCase("WOOD"));
        assertThat("It's not ending with 'n'", word, endsWith("n"));

        //contains
        assertThat(word, containsString("den"));
        assertThat(word, containsStringIgnoringCase("SPOON"));

        //empty string
        String str = "";
        assertThat(str, is(blankString()));
        assertThat(str.replace(" ", ""), is(emptyOrNullString()));
        assertThat(str.trim(), is(emptyOrNullString()));
    }

    @Test
    public void listsTest() {
        List<Integer> nums = Arrays.asList(5, 20);
        List<String> words = Arrays.asList("Lina", "David", "Elijah", "Noah");

        //size
        assertThat(nums, hasSize(2));
        assertThat(words, hasSize(4));

        //contains
        assertThat(nums, hasItem(5));
        assertThat(words, hasItem("Lina"));

        //every item
        assertThat(nums,containsInAnyOrder(20,5));

        //everyItem
        assertThat(nums,everyItem(greaterThanOrEqualTo(5)));
        assertThat(words,everyItem(not(blankString())));
    }

}
