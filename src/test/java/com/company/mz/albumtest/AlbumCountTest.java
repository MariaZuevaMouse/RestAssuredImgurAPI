package com.company.mz.albumtest;

import com.company.mz.BaseTest;
import com.company.mz.util.UtilMethods;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

//@Disabled
public class AlbumCountTest extends BaseTest {
    int initialCount, updatedCount;

    @BeforeEach
    void setUp() {
        initialCount = UtilMethods.getAlbumCount();
        UtilMethods.simpleAlbumCreate();
        updatedCount = UtilMethods.getAlbumCount();
    }

    @Test
    @RepeatedTest(10)
    void getAlbumCountTest(){
        assertThat(updatedCount-initialCount, equalTo(1));
    }

    @AfterEach
    void tearDown() {
        UtilMethods.simpleAlbumDelete();
    }

}
